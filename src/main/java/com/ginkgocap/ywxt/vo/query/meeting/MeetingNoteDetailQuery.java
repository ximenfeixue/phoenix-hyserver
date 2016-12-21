/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.model.mobile.JTFile;


public class MeetingNoteDetailQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
	/** 笔记明细id */
	private Long id;
	/** 笔记序号 */
	private Long meetingNoteId;
	/** 笔记内容 */
	private String meetingNoteContent;
	/** 会议笔记明细时间 */
	private Date meetingNoteTime;
	/** 引用聊天记录 */
	private Long meetingChatId;
	/** 引用聊天记录 */
	private TopicChat  topicChat;
	/** 附件集合列表 */
	private List<JTFile> listJTFile;
	/** 附件id */
	private String taskId;
	//带格式的笔记内容
	private String formatedContent;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getMeetingNoteId() {
		return this.meetingNoteId;
	}
	
	public void setMeetingNoteId(Long value) {
		this.meetingNoteId = value;
	}
	
	public String getMeetingNoteContent() {
		return this.meetingNoteContent;
	}
	
	public void setMeetingNoteContent(String value) {
		this.meetingNoteContent = value;
	}
	
	
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String value) {
		this.taskId = value;
	}
	
	public Date getMeetingNoteTime() {
		return meetingNoteTime;
	}
	public Long getMeetingChatId() {
		return this.meetingChatId;
	}
	
	public void setMeetingChatId(Long value) {
		this.meetingChatId = value;
	}
	
	public void setMeetingNoteTime(Date meetingNoteTime) {
		this.meetingNoteTime = meetingNoteTime;
	}
	public TopicChat getTopicChat() {
		return topicChat;
	}

	public void setTopicChat(TopicChat topicChat) {
		this.topicChat = topicChat;
	}

	public List<JTFile> getListJTFile() {
		return listJTFile;
	}

	public void setListJTFile(List<JTFile> listJTFile) {
		this.listJTFile = listJTFile;
	}

	public String getFormatedContent() {
		return formatedContent;
	}

	public void setFormatedContent(String formatedContent) {
		this.formatedContent = formatedContent;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}