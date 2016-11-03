package com.ginkgocap.ywxt.model.meeting;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.common.base.BaseEntity;
import com.ginkgocap.ywxt.utils.Utils;

public class MeetingOrgan extends BaseEntity implements java.io.Serializable, Comparable<MeetingOrgan>{
	private static final long serialVersionUID = -8396871482182184342L;
	//会议关联组织ID
	private Long id;
	//会议ID
	private Long meetingId;
	//组织ID
	private Long organId;
	//组织名称
	private String organName;
	//组织图片
	private String organPhoto;
	//类型 1：组织、2：机构
	private int organType;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//关联关系
	private String relation = "组织关联";
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
	public Long getOrganId() {
		return organId;
	}
	public void setOrganId(Long organId) {
		this.organId = organId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrganPhoto() {
		return organPhoto;
	}
	public void setOrganPhoto(String organPhoto) {
		this.organPhoto = organPhoto;
	}
	public int getOrganType() {
		return organType;
	}
	public void setOrganType(int organType) {
		this.organType = organType;
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
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	/**
	 * 重写toString
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MeetingId",getMeetingId())
			.append("OrganId",getOrganId())
			.append("OrganName",getOrganName())
			.append("OrganPhoto",getOrganPhoto())
			.append("OrganType",getOrganType())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Relation",getRelation())
			.toString();
	}
	/**
	 * 名称: toJson
	 * 描述: 生成json串
	 */
	public String toJson() {
		return new StringBuilder()
			.append("{ 'meeting':{")
			.append("'id':'"+getId()+"',")
			.append("'meetingId':'"+getMeetingId()+"',")
			.append("'organId':'"+getOrganId()+"',")
			.append("'organName':'"+getOrganName()+"',")
			.append("'organPhoto':'"+getOrganPhoto()+"',")
			.append("'organType':'"+getOrganType()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'updateTime':'"+getUpdateTime()+"',")
			.append("'relation':'"+getRelation()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Meeting == false) return false;
		if(this == obj) return true;
		Meeting other = (Meeting)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Override
	public int compareTo(MeetingOrgan o) {
		if(Utils.isNullOrEmpty(this.getId())||Utils.isNullOrEmpty(o.getId())){
			return 1;
		}else{
			if(this.getId()< o.getId()){
				return -1;
			}else if(this.getId()>o.getId()){
				return 1;
			}else{
				return 1;
			}
		}
	}
}