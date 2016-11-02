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

public class MeetingSignLabel extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//会议报名必填标签
	private Long id;
    //会议ID
	private Long meetingId;
    //标签名字
	private String labelName;
    //是否自定义
	private Integer isCustom;
    //创建者ID
	private Long createId;
    //创建者名字
	private String createName;
    //创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	public MeetingSignLabel() {}

	public MeetingSignLabel(Long id) {
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
	
	public void setLabelName(String value) {
		this.labelName = value;
	}
	
	public String getLabelName() {
		return this.labelName;
	}
	
	public void setIsCustom(Integer value) {
		this.isCustom = value;
	}
	
	public Integer getIsCustom() {
		return this.isCustom;
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
			.append("LabelName",getLabelName())
			.append("IsCustom",getIsCustom())
			.append("CreateId",getCreateId())
			.append("CreateName",getCreateName())
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
			.append("'labelName':'"+getLabelName()+"',")
			.append("'isCustom':'"+getIsCustom()+"',")
			.append("'createId':'"+getCreateId()+"',")
			.append("'createName':'"+getCreateName()+"',")
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
		if(obj instanceof MeetingSignLabel == false) return false;
		if(this == obj) return true;
		MeetingSignLabel other = (MeetingSignLabel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}