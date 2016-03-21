package com.ginkgocap.ywxt.model.meeting;

/**
 * 单人回话传输对象
 * @author edwin
 *
 */
public class ChatMessage  {

	
	/**聊天的序号,id主键*/
	private Integer index;
	/**接受者id*/
	private Integer recvID;
	/**发送者用户id*/
	private Integer senderID;
	/**发送者名称*/
	private String senderName;
	/**内容type*/
	private Integer type;
	/**聊天的序号,id主键*/
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
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getRecvID() {
		return recvID;
	}
	public void setRecvID(Integer recvID) {
		this.recvID = recvID;
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
}
