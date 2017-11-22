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
                    if (data != null) {
                        if (data instanceof MeetingFreeChat) {
                            final MeetingFreeChat meetingFreeChat = (MeetingFreeChat) data;
                            if (meetingFreeChat == null) {
                                continue;
                            }
                            final Meeting meeting = meetingService.getById(meetingFreeChat.getMeetingId());
                            ThreadPoolUtils.getExecutorService().execute(new Runnable() {
                                @Override
                                public void run() {
                                    final Meeting updateMeeting = meeting;
                                    String groupId = createFreeChatGroup(meetingFreeChat);
                                    if (!StringUtils.isNullOrEmpty(groupId)) {
                                        updateMeeting.setGroupId(groupId);
                                        meetingService.saveOrUpdate(updateMeeting);
                                    }
                                }
                            });
                            Meeting meetingDb = meetingService.getById(meeting.getId());
                            if (StringUtils.isNullOrEmpty(meetingDb.getGroupId())) {
                                result = true;
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
                                                meetingMemberService.saveOrUpdate(meetingMember);
                                            }
                                            final String groupId = meeting.getGroupId();
                                            final Long userId = meetingNotice.getCreateId();
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
                        }
                    }
                    if (result) {
                        dataSyncService.deleteDataSync(dataSync.getId());
                    }
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
}
