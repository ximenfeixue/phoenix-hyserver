/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import java.io.Serializable;
import java.util.Date;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class MeetingNote extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	//笔记序号
	private Long id;
    //会议序号
	private Long meetingId;
    //笔记标题
	private String meetingNoteTitle;
	//所属人
	private Long creater;
    //笔记创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	public MeetingNote() {}

	public MeetingNote(Long id) {
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setMeetingId(Long value) {
		this.meetingId = value;
	}
	
	public Long getMeetingId() {
		return this.meetingId;
	}
	
	public void setMeetingNoteTitle(String value) {
		this.meetingNoteTitle = value;
	}
	
	public String getMeetingNoteTitle() {
		return this.meetingNoteTitle;
	}
	
	public void setCreater(Long value) {
		this.creater = value;
	}
	
	public Long getCreater() {
		return this.creater;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
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
			.append("MeetingId",getMeetingId())
			.append("MeetingNoteTitle",getMeetingNoteTitle())
			.append("Creater",getCreater())
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
			.append("'meetingId':'"+getMeetingId()+"',")
			.append("'meetingNoteTitle':'"+getMeetingNoteTitle()+"',")
			.append("'creater':'"+getCreater()+"',")
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
		if(obj instanceof MeetingNote == false) return false;
		if(this == obj) return true;
		MeetingNote other = (MeetingNote)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}