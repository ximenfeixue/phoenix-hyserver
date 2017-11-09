package com.ginkgocap.ywxt.model.meeting;

import com.gintong.ywxt.im.model.JTFile;

/**
 * 会议传输对象
 * @author edwin
 *
 */
public class MUCMessage{

	
	/**聊天的序号,id主键*/
	private String index;
	/**会议id*/
	private Integer id;
	/**会议议题id*/
	private Integer topicId;
	/**发送者用户id,系统通知为0*/
	private Integer senderID;
	/**发送者用户名称*/
	private String senderName;
	/**内容type*/
	private Integer type;
	/**聊天的具体内容,根据type 来定*/
	private String content;
	/**消息id串，客户端随机生成，每条记录唯一*/
	private String messageID;
	/**时间*/
	private String time;
	/**文件对象*/
	private JTFile jtFile;
	
	public static final int TYPE_TEXT = 0;
	public static final int TYPE_AUDIO = 1;
	public static final int TYPE_IMAGE = 2;
	
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSenderID() {
		return senderID;
	}
	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public JTFile getJtFile() {
		return jtFile;
	}
	public void setJtFile(JTFile jtFile) {
		this.jtFile = jtFile;
	}
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	
}
