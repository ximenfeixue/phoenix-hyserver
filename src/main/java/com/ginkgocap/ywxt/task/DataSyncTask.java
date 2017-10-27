package com.ginkgocap.ywxt.task;

import com.ginkgocap.ywxt.model.meeting.DataSync;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.NoticeField;
import com.ginkgocap.ywxt.payment.model.PayOrder;
import com.ginkgocap.ywxt.payment.service.PayOrderService;
import com.ginkgocap.ywxt.payment.utils.PayStatus;
import com.ginkgocap.ywxt.service.meeting.DataSyncService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoticeService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.Constant;
import com.ginkgocap.ywxt.utils.type.NoticeReceiverType;
import com.ginkgocap.ywxt.utils.type.NoticeType;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
                                    result = addMeetingNotice(meetingNotice);
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
        return true;
    }

    public boolean saveDataNeedSync(DataSync data)
    {
        try {
            dataSyncService.saveDataSync(data);
            addQueue(data);
        } catch (Exception ex) {
            logger.error("save sync data failed: dataSync: {}",data);
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
