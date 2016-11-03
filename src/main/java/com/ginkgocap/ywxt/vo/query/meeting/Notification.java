package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 通知vo
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5120363775270238187L;
	/**通知信息代号*/
	private String notifCode;
	/**通知信息描述*/
	private String notifInfo;
	
	
	
	public static final String FIELD_Notification_NOTIFCODE = "notifCode";
	public static final String FIELD_Notification_NOTIFINFO = "notifInfo";
	
	
	public String getNotifCode() {
		return notifCode;
	}
	public void setNotifCode(String notifCode) {
		this.notifCode = notifCode;
	}
	public String getNotifInfo() {
		return notifInfo;
	}
	public void setNotifInfo(String notifInfo) {
		this.notifInfo = notifInfo;
	}
	


	
}
