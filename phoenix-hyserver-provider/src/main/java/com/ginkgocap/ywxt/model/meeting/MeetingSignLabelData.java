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


public class MeetingSignLabelData extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//用户报名信息主键
	private Long id;
    //会议必填标签ID
	private Long mslabelId;
    //用户必填信息
	private String labelContent;
    //参会成员ID
	private Long memberId;
    //参会成员名字
	private String memberName;
    //创建者ID
	private Long createId;
    //创建者名字
	private String createName;
    //创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	
	public MeetingSignLabelData() {}

	public MeetingSignLabelData(Long id) {
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setMslabelId(Long value) {
		this.mslabelId = value;
	}
	
	public Long getMslabelId() {
		return this.mslabelId;
	}
	
	public void setLabelContent(String value) {
		this.labelContent = value;
	}
	
	public String getLabelContent() {
		return this.labelContent;
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
			.append("MslabelId",getMslabelId())
			.append("LabelContent",getLabelContent())
			.append("MemberId",getMemberId())
			.append("MemberName",getMemberName())
			.append("CreateId",getCreateId())
			.append("CreateName",getCreateName())
			.append("CreateTime",getCreateTime())
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
			.append("'mslabelId':'"+getMslabelId()+"',")
			.append("'labelContent':'"+getLabelContent()+"',")
			.append("'memberId':'"+getMemberId()+"',")
			.append("'memberName':'"+getMemberName()+"',")
			.append("'createId':'"+getCreateId()+"',")
			.append("'createName':'"+getCreateName()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MeetingSignLabelData == false) return false;
		if(this == obj) return true;
		MeetingSignLabelData other = (MeetingSignLabelData)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}