
/** 
* Copyright (c) 2014 北京金桐网投资有限公司. 
* All Rights Reserved. 保留所有权利. 
*/ 
package com.ginkgocap.ywxt.model.meeting; 

import com.gintong.ywxt.im.model.JTFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 
 * @author limengyang@gintong.com 
 * @date 2015年1月14日 下午3:14:34 
 * @desc 
 */
public class MeetingIndex implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int type;         //类型    1.会议  2.通知  3.邀请函  4.私聊  5.群聊
	private transient Date sort_time;     //按照此字段进行排序
	private List<String> list_image; //封面图
	private String commtent;    //最后一次会议聊天内容
	private String title;    //标题
	private int status;//会议的状态 1-未开始  2-正在进行  3-已结束
	private String last_chat_time;
	private String create_time;  //创建时间
	private String start_time; //会议开始时间
	private String end_time;  //会议结束时间
	private long meeting_id; //会议ID
	
	private long senderID;  //发送者ID
	private String senderName; //发送者名字
	
	private long  recvID;  //接收者ID  私聊时
	private int messageType;  //内容类型
	private long index;        //内部聊天序号
	private String messageID;  //消息ID串  由客户端生产
	private int  newCount;     //未读消息数
	private JTFile jtFile;
	
	
	public MeetingIndex() {
		super();
	}
	
	public MeetingIndex(int type, Date sort_time,
			String commtent, String title, int status, String last_chat_time,
			String create_time, String start_time, String end_time,
			long meeting_id) {
		super();
		this.type = type;
		this.sort_time = sort_time;
	
		this.commtent = commtent;
		this.title = title;
		this.status = status;
		this.last_chat_time = last_chat_time;
		this.create_time = create_time;
		this.start_time = start_time;
		this.end_time = end_time;
		this.meeting_id = meeting_id;
	}
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getSort_time() {
		return sort_time;
	}
	public void setSort_time(Date sort_time) {
		this.sort_time = sort_time;
	}
	
	
	
	public List<String> getList_image() {
		return list_image;
	}

	public void setList_image(List<String> list_image) {
		this.list_image = list_image;
	}

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	
	public String getCommtent() {
		return commtent;
	}
	public void setCommtent(String commtent) {
		this.commtent = commtent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLast_chat_time() {
		return last_chat_time;
	}
	public void setLast_chat_time(String last_chat_time) {
		this.last_chat_time = last_chat_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public long getMeeting_id() {
		return meeting_id;
	}
	public void setMeeting_id(long meeting_id) {
		this.meeting_id = meeting_id;
	}

	public long getSenderID() {
		return senderID;
	}

	public void setSenderID(long senderID) {
		this.senderID = senderID;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public long getRecvID() {
		return recvID;
	}

	public void setRecvID(long recvID) {
		this.recvID = recvID;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public JTFile getJtFile() {
		return jtFile;
	}

	public void setJtFile(JTFile jtFile) {
		this.jtFile = jtFile;
	}

	

}
