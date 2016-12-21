/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.common.base.BaseEntity;


public class MeetingTopic extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//议题序号
	private Long id;
    //会议序号
	private Long meetingId;
    //议题内容
	private String topicContent;
    //议题开始时间
	private Date topicStartTime;
    //议题结束时间
	private Date topicEndTime;
    //议题介绍
	private String topicDesc;
    //附件id
	private String taskId;
    //主讲人ID
	private Long memberId;
    //主讲人名字
	private String memberName;
    //主讲人头像
	private String memberPic;
    //主讲人描述
	private String memberDesc;
    //创建人Id
	private Long createId;
    //创建人名字
	private String createName;
    //创建时间
	private Date createTime;
    //更新时间
	private Date updateTime;
	//删除标识位(0：默认、1：删除)
	private int isFinished = 0;
	
	public MeetingTopic() {}

	public MeetingTopic(Long id) {
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
	
	public void setTopicContent(String value) {
		this.topicContent = value;
	}
	
	public String getTopicContent() {
		return this.topicContent;
	}
	
	public void setTopicStartTime(Date value) {
		this.topicStartTime = value;
	}
	
	public Date getTopicStartTime() {
		return this.topicStartTime;
	}
	
	public void setTopicEndTime(Date value) {
		this.topicEndTime = value;
	}
	
	public Date getTopicEndTime() {
		return this.topicEndTime;
	}
	
	public void setTopicDesc(String value) {
		this.topicDesc = value;
	}
	
	public String getTopicDesc() {
		return this.topicDesc;
	}
	
	public void setTaskId(String value) {
		this.taskId = value;
	}
	
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	
	public void setMemberName(String value) {
		this.memberName = value;
	}
	
	public String getMemberName() {
		return this.memberName;
	}
	
	public String getMemberPic() {
		return memberPic;
	}

	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}

	public void setMemberDesc(String value) {
		this.memberDesc = value;
	}
	
	public String getMemberDesc() {
		return this.memberDesc;
	}
	
	public void setCreateId(Long value) {
		this.createId = value;
	}
	
	public Long getCreateId() {
		return this.createId;
	}
	
	public void setCreateName(String value) {
		this.createName = value;
	}
	
	public String getCreateName() {
		return this.createName;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public int getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
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
			.append("TopicContent",getTopicContent())
			.append("TopicStartTime",getTopicStartTime())
			.append("TopicEndTime",getTopicEndTime())
			.append("TopicDesc",getTopicDesc())
			.append("TaskId",getTaskId())
			.append("MemberId",getMemberId())
			.append("MemberName",getMemberName())
			.append("MemberDesc",getMemberDesc())
			.append("CreateId",getCreateId())
			.append("CreateName",getCreateName())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("IsFinished",getIsFinished())
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
			.append("'topicContent':'"+getTopicContent()+"',")
			.append("'topicStartTime':'"+getTopicStartTime()+"',")
			.append("'topicEndTime':'"+getTopicEndTime()+"',")
			.append("'topicDesc':'"+getTopicDesc()+"',")
			.append("'taskId':'"+getTaskId()+"',")
			.append("'memberId':'"+getMemberId()+"',")
			.append("'memberName':'"+getMemberName()+"',")
			.append("'memberDesc':'"+getMemberDesc()+"',")
			.append("'createId':'"+getCreateId()+"',")
			.append("'createName':'"+getCreateName()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'updateTime':'"+getUpdateTime()+"',")
			.append("'isFinished':'"+getIsFinished()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MeetingTopic == false) return false;
		if(this == obj) return true;
		MeetingTopic other = (MeetingTopic)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}