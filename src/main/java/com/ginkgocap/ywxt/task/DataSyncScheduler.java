package com.ginkgocap.ywxt.task;

import com.ginkgocap.ywxt.model.meeting.DataSync;
import com.ginkgocap.ywxt.service.meeting.DataSyncService;
import com.ginkgocap.ywxt.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wang fei on 2017/10/27.
 */
public class DataSyncScheduler implements InitializingBean {


    private final Logger logger = LoggerFactory.getLogger(DataSyncScheduler.class);

    @Autowired
    DataSyncService dataSyncService;

    @Autowired
    DataSyncTask dataSyncTask;

    private int count = 0;

    public void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ++count;
                dataSyncTask();
                String nowDate = DateUtil.convertDateToString(new Date());
                logger.info("时间=" + nowDate + " 执行了" + count + "次"); // 1次
            }
        };

        Timer timer = new Timer();
        int period = 5 * 60 * 1000;
        //// 定制每天的24:00:00执行， 每天的date时刻执行task，每隔5 min 重复执行
        // timer.schedule(task, getDate(24), period);

        // 每天的date时刻执行task, 仅执行一次
        // timer.schedule(task, 1000);

        // 立即开始执行, 5 min 执行一次
        timer.schedule(task, 1000, period);
    }

    public void dataSyncTask() {
        List<DataSync> dataSyncList = dataSyncService.getDataSyncList();
        if (CollectionUtils.isNotEmpty(dataSyncList)) {
            for (DataSync data : dataSyncList) {
                dataSyncTask.addQueue(data);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("dataSyncScheduler start ----------");
        new Thread(dataSyncTask).start();
        startTimer();
        logger.info("dataSyncScheduler end ----------");
    }
}
