package com.ginkgocap.ywxt.vo.query.meeting;

import java.util.Date;

/**
 * @Description: 修改会议推送vo
 * @Author: qinguochao
 * @CreateDate: 2014-10-2
 * @Version: [v1.0]
 */
public class MeetingMemberPush{

	/**会议id*/
	private Integer meetingId;
	/**会议id*/
	private Integer meetingCreateId;
	/**推送内容*/
	private String content;
	private UserBean user;
	/**通知时间 */
	private String date;
	/**是否包含创建者*/
	private boolean isHaveCreate;
	public Integer getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public Integer getMeetingCreateId() {
		return meetingCreateId;
	}
	public void setMeetingCreateId(Integer meetingCreateId) {
		this.meetingCreateId = meetingCreateId;
	}
	public boolean isHaveCreate() {
		return isHaveCreate;
	}
	public void setHaveCreate(boolean isHaveCreate) {
		this.isHaveCreate = isHaveCreate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}