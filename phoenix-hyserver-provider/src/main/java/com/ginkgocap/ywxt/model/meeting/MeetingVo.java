
/** 
* Copyright (c) 2014 北京金桐网投资有限公司. 
* All Rights Reserved. 保留所有权利. 
*/ 
package com.ginkgocap.ywxt.model.meeting; 

import java.io.Serializable;
import java.util.Date;

/** 
 * @author limengyang@gintong.com 
 * @date 2015年1月16日 下午2:07:56 
 * @desc 
 */
public class MeetingVo implements Serializable {
	
	private int type; //类型    1.会议  2.通知  3.邀请函  4.私聊  5.群聊
	private transient Date sort;
    private MeetingMini meeting;
    private NoticeMini notice;
    private ChatMini  chat;
    private MeetingMini invite;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getSort() {
		return sort;
	}
	public void setSort(Date sort) {
		this.sort = sort;
	}
	
	public NoticeMini getNotice() {
		return notice;
	}
	public void setNotice(NoticeMini notice) {
		this.notice = notice;
	}
	public ChatMini getChat() {
		return chat;
	}
	public void setChat(ChatMini chat) {
		this.chat = chat;
	}
	public MeetingMini getMeeting() {
		return meeting;
	}
	public void setMeeting(MeetingMini meeting) {
		this.meeting = meeting;
	}
	public MeetingMini getInvite() {
		return invite;
	}
	public void setInvite(MeetingMini invite) {
		this.invite = invite;
	}
	

	
	
	

}
