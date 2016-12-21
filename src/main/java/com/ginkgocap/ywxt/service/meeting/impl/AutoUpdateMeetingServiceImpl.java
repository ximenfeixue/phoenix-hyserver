package com.ginkgocap.ywxt.service.meeting.impl;


import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.utils.Constant;
import com.ginkgocap.ywxt.utils.Utils;


/**
 * @Description: 自动更新会议任务
 * @Author: qinguochao
 * @CreateDate: 2014-11-18
 * @Version: [v1.0]
 */
public class AutoUpdateMeetingServiceImpl {

	private static Log log = LogFactory.getLog(AutoUpdateMeetingServiceImpl.class);
	private static ResourceBundle resource =  ResourceBundle.getBundle("gintongService");
    @Autowired
    private ThreadPoolTaskExecutor automatedQueue;
    @Autowired
    private MeetingService meetingService;
    /**
     * 自动任务运行间隔
     */
    private long autoIntervalSecond = 10; //seconds
    /**
     * 是否开启自动更新会议
     */
    private boolean openAutoRepay = true;
    public synchronized void start() {
    	// 配置文件中中设置是否开启自动更新会议开关
		String interfaceName = resource.getString("openAutoRepay");
    	if(!Utils.isNullOrEmpty(interfaceName)&&"0".equals(interfaceName)){
    		openAutoRepay=false;
    		return;
    	}
        if (openAutoRepay) {
        	String autoIntervalSecondStr = resource.getString("autoIntervalSecond");
        	if(!Utils.isNullOrEmpty(autoIntervalSecondStr)){
        		try {
					Long intervalSecond=Long.parseLong(autoIntervalSecondStr);
					this.setAutoIntervalSecond(intervalSecond);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
        	}
            // 自动更新会议状态
            final Thread autoUpdateMeetingStatusThread = new Thread(new Runnable() {
                public void run() {
                	while(true){
                		try {
                			Thread.sleep(autoIntervalSecond * Constant.ONE_SECOND_TIMES);
                			//获取所有未开始的会议
                			List<Meeting> listMeeting = meetingService.getAllNotBeginMeetng();
                			if(!Utils.isNullOrEmpty(listMeeting)){
                				for(Meeting meeting:listMeeting){
                					if(!Utils.isNullOrEmpty(meeting)){
                						Date date=new Date();
                						if(meeting.getStartTime().before(date)||meeting.getStartTime().getTime()-date.getTime()<=(autoIntervalSecond * Constant.ONE_SECOND_TIMES)){
                							// 修改会议为已开始
                							boolean flag;
                							try {
                								flag = meetingService.changeMeetingStatusToBegin(meeting.getId());
                								if(flag){
                									//会议开始通知暂不实现
                									//GinTongInterface.pushToAttendMeetingMember(null, String.valueOf(meeting.getId()), "会议 "+meeting.getMeetingName()+"已经开始",true);
                								}
                							} catch (Exception e) {
                								e.printStackTrace();
                							}
                							
                						}
                					}
                				}
                			}
                		} catch (Exception ex) {
                			log.info("自动更新会议状态失败", ex);
                		}
                	}
                }
            }, "autoUpdateMeetingStatusThread");
            autoUpdateMeetingStatusThread.setDaemon(true);
            autoUpdateMeetingStatusThread.start();
        }
    }
    @ManagedAttribute
    public boolean isOpenAutoRepay() {
        return openAutoRepay;
    }
    @ManagedAttribute
    public void setOpenAutoRepay(boolean openAutoRepay) {
        this.openAutoRepay = openAutoRepay;
    }
	public long getAutoIntervalSecond() {
		return autoIntervalSecond;
	}
	public void setAutoIntervalSecond(long autoIntervalSecond) {
		this.autoIntervalSecond = autoIntervalSecond;
	}
}