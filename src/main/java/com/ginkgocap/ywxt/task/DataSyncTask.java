package com.ginkgocap.ywxt.task;

import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.payment.model.PayOrder;
import com.ginkgocap.ywxt.payment.service.PayOrderService;
import com.ginkgocap.ywxt.payment.utils.PayStatus;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.utils.Constant;
import com.ginkgocap.ywxt.utils.GinTongInterface;
import com.ginkgocap.ywxt.utils.ThreadPoolUtils;
import com.ginkgocap.ywxt.utils.type.NoticeType;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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
    private NoticeFieldService noticeFieldService;

    @Autowired
    private MeetingMemberService meetingMemberService;

    @Override
    public void run() {
        List<PayOrder> payOrderList = null;
        try {
            while(true) {
                DataSync dataSync = dataSyncQueue.take();
                if (dataSync != null) {
                    boolean result = false;
                    Object data = dataSync.getData();
                    if (data != null) {
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
                                    if (meetingNotice.getNoticeType() == NoticeType.NO_REVIEW_MEETING.code()) {
                                        if (meetingId != null) {
                                            Meeting meeting = meetingService.getById(meetingId);
                                            if (meeting != null) {
                                                // 报名活动不需要审核直接审核
                                                if (payOrder.getUserId() > 0) {
                                                    List<MeetingMember> list = meetingMemberService.getByMeetingIdAndMemberId(meetingId, payOrder.getUserId());
                                                    MeetingMember meetingMember = list.get(0);
                                                    meetingMember.setExcuteMeetSign(1);
                                                    // 会议不需要签到直接签到
                                                    meetingMember.setIsSign(meeting.getIsSign() == 0 ? 1 : 0);
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
                                            } else {
                                                logger.info("该活动不存在或已删除");
                                            }
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
            logger.error("member update failed");
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
            dataSyncService.saveDataSync(data);
            addQueue(data);
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
}
