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

import com.ginkgocap.ywxt.utils.Utils;


public class MeetingTime extends BaseEntity implements Serializable, Comparable<MeetingTime>{
	private static final long serialVersionUID = 5454155825314635342L;
	//会议时间id
	private Long id;
    //会议id
	private Long meetingId;
    //开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	public MeetingTime() {}

	public MeetingTime(Long id) {
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
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
			.append("MeetingId",getMeetingId())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
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
			.append("'startTime':'"+getStartTime()+"',")
			.append("'endTime':'"+getEndTime()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'updateTime':'"+getUpdateTime()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.append(getMeetingId())
				.append(getStartTime())
				.append(getEndTime())
				.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MeetingTime == false) return false;
		if(this == obj) return true;
		MeetingTime other = (MeetingTime)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.append(getMeetingId(),other.getMeetingId())
				.append(getStartTime(),other.getStartTime())
				.append(getEndTime(),other.getEndTime())
				.isEquals();
	}

	public int compareTo(MeetingTime meetingTime) {
		if(Utils.isNullOrEmpty(this.getId())||Utils.isNullOrEmpty(meetingTime.getId())){
			return 1;
		}else{
			if(this.getId()< meetingTime.getId()){
				return -1;
			}else if(this.getId()>meetingTime.getId()){
				return 1;
			}else{
				return 1;
			}
			
		}
	}
}