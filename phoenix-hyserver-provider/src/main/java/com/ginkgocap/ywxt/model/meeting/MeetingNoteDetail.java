/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import java.util.Date;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MeetingNoteDetail extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//笔记明细id
	private Long id;
    //笔记序号
	private Long meetingNoteId;
    //引用聊天记录
	private Long meetingChatId;
    //笔记内容
	private String meetingNoteContent;
	//带格式的笔记内容
	private String formatedContent;
    //会议笔记明细时间
	private Date meetingNoteTime;
    //附件id
	private String taskId;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	public MeetingNoteDetail() {}

	public MeetingNoteDetail(Long id) {
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setMeetingNoteId(Long value) {
		this.meetingNoteId = value;
	}
	
	public Long getMeetingNoteId() {
		return this.meetingNoteId;
	}
	
	public void setMeetingNoteContent(String value) {
		this.meetingNoteContent = value;
	}
	
	public String getMeetingNoteContent() {
		return this.meetingNoteContent;
	}
	
	public String getFormatedContent() {
		return formatedContent;
	}

	public void setFormatedContent(String formatedContent) {
		this.formatedContent = formatedContent;
	}
	
	public void setMeetingNoteTime(Date value) {
		this.meetingNoteTime = value;
	}
	
	public Date getMeetingNoteTime() {
		return this.meetingNoteTime;
	}
	
	public void setTaskId(String value) {
		this.taskId = value;
	}
	
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setMeetingChatId(Long value) {
		this.meetingChatId = value;
	}
	
	public Long getMeetingChatId() {
		return this.meetingChatId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 名称: toString
	 * 描述: 重写toString方法
	 * @since  2014-09-14
	 * @author qingc
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MeetingNoteId",getMeetingNoteId())
			.append("MeetingNoteContent",getMeetingNoteContent())
			.append("MeetingNoteTime",getMeetingNoteTime())
			.append("TaskId",getTaskId())
			.append("FormatedContent",getFormatedContent())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	/**
	 * 名称: toJson
	 * 描述: 生成json串
	 * @since  2014-09-14
	 * @author qingc
	 */
	public String toJson() {
		return new StringBuilder()
			.append("{ 'meeting':{")
			.append("'id':'"+getId()+"',")
			.append("'meetingNoteId':'"+getMeetingNoteId()+"',")
			.append("'meetingNoteContent':'"+getMeetingNoteContent()+"',")
			.append("'meetingNoteTime':'"+getMeetingNoteTime()+"',")
			.append("'taskId':'"+getTaskId()+"',")
			.append("'formatedContent':'"+getFormatedContent()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'updateTime':'"+getUpdateTime()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MeetingNoteDetail == false) return false;
		if(this == obj) return true;
		MeetingNoteDetail other = (MeetingNoteDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}