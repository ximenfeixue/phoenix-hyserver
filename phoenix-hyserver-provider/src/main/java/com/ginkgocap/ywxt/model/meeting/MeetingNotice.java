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

public class MeetingNotice extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//通知ID
	private Long id;
    //接收人类型 0:发起者1：参会人
	private Integer receiverType;
    //接收人
	private Long receiver;
	//接收人名字
	private String receiverName;
    //通知类型
	private Integer noticeType;
	//是否显示：0：不显示，1：显示
	private Integer isShow;
    //通知内容
	private String noticeContent;
    //会议ID
	private Long meetingId;
    //创建者ID
	private Long createId;
    //创建者名字
	private String createName;
    //创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//是否已读取 0：未读，1：已读，默认为未读
	private Long isRead = 0L;
	//读取时间
	private Date readTime;

	public MeetingNotice() {}

	public MeetingNotice(Long id) {
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setReceiverType(Integer value) {
		this.receiverType = value;
	}
	
	public Integer getReceiverType() {
		return this.receiverType;
	}
	
	public void setReceiver(Long value) {
		this.receiver = value;
	}
	
	public Long getReceiver() {
		return this.receiver;
	}
	
	public void setNoticeType(Integer value) {
		this.noticeType = value;
	}
	
	public Integer getNoticeType() {
		return this.noticeType;
	}
	
	public void setNoticeContent(String value) {
		this.noticeContent = value;
	}
	
	public String getNoticeContent() {
		return this.noticeContent;
	}
	
	public void setMeetingId(Long value) {
		this.meetingId = value;
	}
	
	public Long getMeetingId() {
		return this.meetingId;
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
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Long getIsRead() {
		return isRead;
	}

	public void setIsRead(Long isRead) {
		this.isRead = isRead;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
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
			.append("ReceiverType",getReceiverType())
			.append("Receiver",getReceiver())
			.append("NoticeType",getNoticeType())
			.append("NoticeContent",getNoticeContent())
			.append("MeetingId",getMeetingId())
			.append("CreateId",getCreateId())
			.append("CreateName",getCreateName())
			.append("CreateTime",getCreateTime())
			.append("IsRead",getIsRead())
			.append("ReadTime",getReadTime())
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
			.append("'receiverType':'"+getReceiverType()+"',")
			.append("'receiver':'"+getReceiver()+"',")
			.append("'noticeType':'"+getNoticeType()+"',")
			.append("'noticeContent':'"+getNoticeContent()+"',")
			.append("'meetingId':'"+getMeetingId()+"',")
			.append("'createId':'"+getCreateId()+"',")
			.append("'createName':'"+getCreateName()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'isRead':'"+getIsRead()+"',")
			.append("'readTime':'"+getReadTime()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MeetingNotice == false) return false;
		if(this == obj) return true;
		MeetingNotice other = (MeetingNotice)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

