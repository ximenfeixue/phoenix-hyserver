package com.ginkgocap.ywxt.vo.query.social;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingTopic;

/**
 * @Description: 社交vo
 * @Author: qinguochao
 * @CreateDate: 2015-01-22
 * @Version: [v1.0]
 */
public class Social implements Serializable {
	private static final long serialVersionUID = 2435010501495390620L;
	/** 私聊为对方的用户id，群聊为muc的id",会议为会议的id */
	private java.lang.Long id;
	/** 社交显示标题 如果群聊，为群聊或者会议的主题；私聊，为好友名称，通知显示为“通知”*/
	private java.lang.String title;
	 /** 1-私聊，2-群聊，3-进行中的会议，4-未开始的会议 5-已结束的会议，6-通知，7-邀请函  */
	private java.lang.Integer type;
	 /** 会议类型 0：无主讲  1：有主讲  */
	private java.lang.Integer meetingType;
	 /** 群聊：主持人id，会议 ：会议发起人  */	
	private java.lang.Long compereId;
	 /** 群聊：主持人姓名，会议 ：会议发起人  */	
	private java.lang.String compereName;
	 /** 社交列表右侧显示时间    */	
	public Date time;
	 /**会议地点省份  */	
	public Date orderTime;
	 /**会议对应的议题列表  */	
	private List<MeetingTopic>listMeetingTopic;
	 /** 社交明细*/	
	public SocialDetail socialDetail;
	/** 社交图片*/
	private String path;
	
	/**未读消息条数,默认为0*/
	private Integer newCount = 0;
	
	/**
	 * 最后@我的人的名称
	 */
	private String atName;
	
	/**
	 * 最后@我的消息的id（对应ImMucmessage.getMessageId）
	 */
	private String atMsgId;
	
	private Integer userType;//1个人好友 2组织好友
	
	/**getXXX() and SetXX()*/
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public java.lang.Integer getType() {
		return type;
	}
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	public java.lang.String getCompereName() {
		return compereName;
	}
	public void setCompereName(java.lang.String compereName) {
		this.compereName = compereName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public SocialDetail getSocialDetail() {
		return socialDetail;
	}
	public void setSocialDetail(SocialDetail socialDetail) {
		this.socialDetail = socialDetail;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<MeetingTopic> getListMeetingTopic() {
		return listMeetingTopic;
	}
	public void setListMeetingTopic(List<MeetingTopic> listMeetingTopic) {
		this.listMeetingTopic = listMeetingTopic;
	}
	public java.lang.Integer getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(java.lang.Integer meetingType) {
		this.meetingType = meetingType;
	}
	public java.lang.Long getCompereId() {
		return compereId;
	}
	public void setCompereId(java.lang.Long compereId) {
		this.compereId = compereId;
	}
	public String getAtName() {
		return atName;
	}
	public void setAtName(String atName) {
		this.atName = atName;
	}
	public String getAtMsgId() {
		return atMsgId;
	}
	public void setAtMsgId(String atMsgId) {
		this.atMsgId = atMsgId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getNewCount() {
		return newCount;
	}
	public void setNewCount(Integer newCount) {
		this.newCount = newCount;
	}
}