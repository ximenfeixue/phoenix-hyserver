package com.ginkgocap.ywxt.task;

import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.payment.model.PayOrder;
import com.ginkgocap.ywxt.payment.service.PayOrderService;
import com.ginkgocap.ywxt.payment.utils.PayStatus;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.service.meeting.impl.MeetingNotifyService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.utils.Constant;
import com.ginkgocap.ywxt.utils.GinTongInterface;
import com.ginkgocap.ywxt.utils.ThreadPoolUtils;
import com.ginkgocap.ywxt.utils.type.NoticeType;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingFreeChat;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.gintong.frame.cache.redis.RedisClient;
import org.apache.commons.collections.CollectionUtils;
import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by wang fei on 2017/10/27.
 */
@Repository("dataSyncTask")
public class DataSyncTask implements Runnable{

    private final Logger logger = LoggerFactory.getLogger(DataSyncTask.class);
    private static final int MAX_QUEUE_NUM = 2000;

    private BlockingQueue<DataSync> dataSyncQueue = new ArrayBlockingQueue<DataSync>(MAX_QUEUE_NUM);

    @Autowired
    DataSyncService dataSyncService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private MeetingNoticeService meetingNoticeService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoticeFieldService noticeFieldService;

    @Autowired
    private MeetingNotifyService meetingNotifyService;

    @Autowired
    private MeetingMemberService meetingMemberService;

    @Autowired
    private RedisClient redisClient;

    private static final String meetingFreeChatKey = "meeting_free_chat_";

    @Override
    public void run() {
        List<PayOrder> payOrderList = null;
        try {
            while(true) {
                logger.info("task 循环日志。");
                DataSync dataSync = dataSyncQueue.take();
                if (dataSync != null) {
                    boolean result = false;
                    Object data = dataSync.getData();
                    String id = dataSync.getId();
                    if (data != null) {
                        if (data instanceof MeetingFreeChat) {
                            final MeetingFreeChat meetingFreeChat = (MeetingFreeChat) data;
                            if (meetingFreeChat == null)
                                continue;
                            // 加锁
                            boolean success = getLock(id);
                            // 成功获得锁
                            if (success) {
                                // 设置活动的 畅聊id， 返回是否可删除该数据的标志
                                result = saveMeetingGroupId(meetingFreeChat);
                            }
                        }
                        if (data instanceof MeetingNotice) {
                            MeetingNotice meetingNotice = (MeetingNotice) data;
                            Long meetingId = meetingNotice.getMeetingId();
                            Long createId = meetingNotice.getCreateId();
                            try {
                                payOrderList = payOrderService.getPayOrderByUserIdAndSourceId(createId, meetingId);
                            } catch (Exception e) {
                                logger.error("invoke payOrderService failed ! method : {getPayOrderByUserIdAndSourceId}. userId : " + createId);
                            }
                            // 处理订单表数据, 并对活动备份通知数据处理，加入活动成员等处理
                            handlePayOrder(payOrderList, meetingNotice);
                        }
                    }
                    if (result) {
                        dataSyncService.deleteDataSync(id);
                    }
                    // 释放锁
                    unLock(id);
                } else {
                    logger.info("data is null, so skip to send.");
                }
            }
        } catch (InterruptedException ex) {
            logger.error("queues thread interrupted. so exit this thread.");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("update member status failed" + e.getMessage());
        }
    }

    private boolean handlePayOrder(List<PayOrder> payOrderList, MeetingNotice meetingNotice) {

        boolean result = false;
        Long createId = meetingNotice.getCreateId();
        Long meetingId = meetingNotice.getMeetingId();
        if (CollectionUtils.isNotEmpty(payOrderList)) {
            PayOrder payOrder = payOrderList.get(0);
            if (payOrder.getStatus() == PayStatus.PAY_SUCCESS.getValue()) {
                // 支付成功发通知
                result = addMeetingNotice(meetingNotice);
                // 若该报名通知的活动不需要审核，则将该成员加到畅聊里
                Meeting meeting = meetingService.getById(meetingId);
                if (meeting == null) {
                    logger.error("该活动不存在或已删除. meetingId: " + meetingId);
                } else {
                    if (meetingNotice.getNoticeType() == NoticeType.NO_REVIEW_MEETING.code()) {
                        // 报名活动不需要审核直接审核
                        logger.info("进入修改流程。。。。。 userId : " + payOrder.getUserId());

                        List<MeetingMember> list = meetingMemberService.getByMeetingIdAndMemberId(meetingId, payOrder.getUserId());
                        logger.info("list size  : " +  list.size());
                        if (null != list && list.size() > 0) {
                            MeetingMember meetingMember = list.get(0);
                            meetingMember.setExcuteMeetSign(1);
                            // 会议不需要签到直接签到
                            meetingMember.setIsSign(meeting.getIsSign() == 0 ? 1 : 0);
                            logger.info("修改成员 " + meetingMember.getMemberName() + "isSign: " + meetingMember.getIsSign() +
                                    "excuteMeetSign : " + meetingMember.getExcuteMeetSign());
                            try {
                                meetingMemberService.saveOrUpdate(meetingMember);
                            } catch (Exception e) {
                                logger.error("invoke meetingMemberService failed. method {saveOrUpdate}" , e.getMessage());
                            }
                        }
                        final String groupId = meeting.getGroupId();
                        final Long userId = createId;
                        final long creatorUserId = meeting.getCreateId();
                        ThreadPoolUtils.getExecutorService().execute(new Runnable() {
                            @Override
                            public void run() {
                                GinTongInterface.invite2MUC(creatorUserId, Arrays.asList(userId), groupId);
                            }
                        });
                    }
                    try {
                        String fromName = meetingNotice.getCreateName();
                        final String title = fromName + " 报名参加 " + meeting.getMeetingName();
                        final User applyUser = userService.getUserById(createId);
                        if (meeting.getReviewFlag() == 0) {
                            meetingNotifyService.addMeetingNotify(meeting.getCreateId(), applyUser, title, meeting);
                        } else if (meeting.getReviewFlag() == 1) {
                            meetingNotifyService.addApplyMeetingNotify(applyUser, title, meeting);
                        }
                    } catch (Exception ex) {
                        logger.error("send notification failed. error: meetingId: " + meetingId + " applyUserId: " + createId + ex.getMessage());
                    }
                }
            }
        }
        return result;
    }

    private boolean addMeetingNotice(MeetingNotice meetingNotice) {

        // 添加通知
        try {
            meetingNoticeService.saveOrUpdate(meetingNotice);
        } catch (Exception e) {
            logger.error("invoke meetingNoticeService failed! method :{saveOrUpdate} result --> send notice failed!");
            return false;
        }
        NoticeField noticeField = new NoticeField();
        String field = meetingNotice.getCreateId() + Constant.NOTICE_CONTENT_SPLIT_CHAR + meetingNotice.getCreateName();
        noticeField.setField(field);
        noticeField.setNoticeId(meetingNotice.getId());
        try {
            noticeFieldService.saveOrUpdate(noticeField);
        } catch (Exception e) {
            logger.error("invoke meetingNoticeService failed! method :{saveOrUpdate} result --> send notice failed! {}", e.getMessage());
        }
        return true;
    }

    public boolean saveDataNeedSync(DataSync data)
    {
        try {
            DataSync dataSync = dataSyncService.saveDataSync(data);
            addQueue(dataSync);
        } catch (Exception ex) {
            logger.error("save sync data failed: dataSync: {}", data.getId());
            return false;
        }
        return true;
    }

    /**
     * 加锁
     * @param id
     * @return
     */
    public boolean getLock(String id) {

        // 1分钟 循环获取锁的等待超时时间，在此时间内会一直尝试获取锁直到超时，为0表示失败后直接返回不等待
        Long lockTimeout = 0l;
        Long lock = 0l;
        while (lock != 1) {
            Long currentTime = System.currentTimeMillis();
            Long timeout = 1 * 60 * 1000l + currentTime;
            Long expireTime = 10 * 60 * 1000l + currentTime;
            lock = redisClient.setnx(meetingFreeChatKey + id, timeout);
            // 获取锁的过期时间
            lockTimeout = (Long) redisClient.getObjectByKey(meetingFreeChatKey + id);
            // 获取之前锁的过期时间 并设置最新锁的过期时间
            Long oldTimeout = (Long) redisClient.getSet(meetingFreeChatKey + id, timeout);
            // 若直接加锁成功 或 该锁过期时，第一次重新设置过期时间接替锁的持有者
            if (lock == 1 || (currentTime > lockTimeout && currentTime > oldTimeout)) {
                // 对 key 设置过期时间
                redisClient.expireAt(meetingFreeChatKey + id, expireTime);
                break;
            } else {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 释放锁
     * @param id
     */
    public void unLock(String id) {

        Long currentTime = System.currentTimeMillis();
        Long timeout = (Long) redisClient.getObjectByKey(meetingFreeChatKey + id);
        // 当前锁还未过期 则直接删除 key
        if (currentTime < timeout)
            redisClient.del(meetingFreeChatKey + id);
    }

    public boolean batchSaveDataNeedSync(List<DataSync> dataList) {

        List<DataSync> list = null;
        try {
            dataSyncService.batchSaveDataSync(dataList);
            batchAddQueue(dataList);
        } catch (Exception e) {
            logger.error("batch save data failed", dataList);
            return false;
        }
        return true;
    }

    public void addQueue(DataSync data) {
        if (data != null) {
            try {
                dataSyncQueue.put(data);
            } catch (Exception ex) {
                logger.error("add sync data to queue failed.");
            }
        } else {
            logger.error("sync object is null, so skip it.");
        }
    }

    public void batchAddQueue(List<DataSync> dataList) {

        if (CollectionUtils.isNotEmpty(dataList)) {
            for (DataSync data : dataList) {
                if (data == null)
                    continue;
                try {
                    dataSyncQueue.put(data);
                } catch (Exception e) {
                    logger.error("add sync data to queue failed!");
                }
            }
        } else {
            logger.error("sync list is null, so skip it");
        }
    }
    private String createFreeChatGroup(MeetingFreeChat meetingFreeChat) {

        Long meetingId = meetingFreeChat.getMeetingId();
        MeetingQuery entity = meetingFreeChat.getMeetingQuery();
        List<Long> memberIds = meetingFreeChat.getUserIds();
        Long ownerId = meetingFreeChat.getOwnerId();
        String meetingName = entity.getMeetingName();
        String meetingDesc = StringUtils.isNullOrEmpty(entity.getMeetingDesc()) ? meetingName : entity.getMeetingDesc().trim();
        int roomSize = 1000;

        if (memberIds == null) {
            memberIds = Collections.emptyList();
        }
        return GinTongInterface.createMUC(meetingId, meetingName, meetingDesc, roomSize, ownerId, memberIds);
    }

    private boolean saveMeetingGroupId(MeetingFreeChat meetingFreeChat) {

        boolean result = false;
        logger.info("meetingId :" + meetingFreeChat.getMeetingId());
        final Meeting meeting = meetingService.getById(meetingFreeChat.getMeetingId());
        if (null == meeting) {
            logger.info("meeting is delete ...");
            return result;
        }
        // 备份的活动数据 若 groupId 不为 null，不需执行备份数据后的处理工作
        if (StringUtils.isNullOrEmpty(meeting.getGroupId())) {
            logger.info("check data .......meetingId: {}", meeting.getId());
            String groupId = createFreeChatGroup(meetingFreeChat);
            meeting.setGroupId(groupId);
            meetingService.saveOrUpdate(meeting);
        }
        Meeting meetingDb = meetingService.getById(meeting.getId());
        if (!StringUtils.isNullOrEmpty(meetingDb.getGroupId())) {
            logger.info("remove sync success meeting data. meetingId :" + meeting.getId());
            result = true;
        }
        return result;
    }
}
