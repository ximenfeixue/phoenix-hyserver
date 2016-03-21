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

import com.ginkgocap.ywxt.utils.Utils;


public class MeetingPeople extends BaseEntity implements java.io.Serializable,Comparable<MeetingPeople>{
	private static final long serialVersionUID = 5454155825314635342L;
	//人脉序号
	private Long id;
    //会议序号
	private Long meetingId;
    //人员ID
	private Long peopleId;
    //人员姓名
	private String peopleName;
    //人员图片
	private String peoplePhoto;
    //人脉描述
	private String peopleDesc;
    //是否分享
	private Boolean isShare;
	//类型(1：人脉、2：用户)
	private int peopleType;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//关联关系
	private String relation = "人脉关联";

	public MeetingPeople() {}

	public MeetingPeople(Long id) {
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
	
	public void setPeopleId(Long value) {
		this.peopleId = value;
	}
	
	public Long getPeopleId() {
		return this.peopleId;
	}
	
	public void setPeopleName(String value) {
		this.peopleName = value;
	}
	
	public String getPeopleName() {
		return this.peopleName;
	}
	
	public void setPeoplePhoto(String value) {
		this.peoplePhoto = value;
	}
	
	public String getPeoplePhoto() {
		return this.peoplePhoto;
	}
	
	public void setPeopleDesc(String value) {
		this.peopleDesc = value;
	}
	
	public String getPeopleDesc() {
		return this.peopleDesc;
	}
	
	public void setIsShare(Boolean value) {
		this.isShare = value;
	}
	
	public Boolean getIsShare() {
		return this.isShare;
	}

	public int getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(int peopleType) {
		this.peopleType = peopleType;
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
	 * 名称: toString
	 * 描述: 重写toString方法
	 * @since  2014-09-14
	 * @author qingc
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MeetingId",getMeetingId())
			.append("PeopleId",getPeopleId())
			.append("PeopleName",getPeopleName())
			.append("PeoplePhoto",getPeoplePhoto())
			.append("PeopleDesc",getPeopleDesc())
			.append("IsShare",getIsShare())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Relation",getRelation())
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
			.append("'peopleId':'"+getPeopleId()+"',")
			.append("'peopleName':'"+getPeopleName()+"',")
			.append("'peoplePhoto':'"+getPeoplePhoto()+"',")
			.append("'peopleDesc':'"+getPeopleDesc()+"',")
			.append("'isShare':'"+getIsShare()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'updateTime':'"+getUpdateTime()+"',")
			.append("'relation':'"+getRelation()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getMeetingId())
			.append(getPeopleId())
			.append(getPeopleName())
			.append(getPeoplePhoto())
			.append(getPeopleDesc())
			.append(getIsShare())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MeetingPeople == false) return false;
		if(this == obj) return true;
		MeetingPeople other = (MeetingPeople)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getMeetingId(),other.getMeetingId())
			.append(getPeopleId(),other.getPeopleId())
			.append(getPeopleName(),other.getPeopleName())
			.append(getPeoplePhoto(),other.getPeoplePhoto())
			.append(getPeopleDesc(),other.getPeopleDesc())
			.append(getIsShare(),other.getIsShare())
			.isEquals();
	}
	
	public int compareTo(MeetingPeople meetingPeople) {
		if(Utils.isNullOrEmpty(this.getId())||Utils.isNullOrEmpty(meetingPeople.getId())){
			return 1;
		}else{
			if(this.getId()< meetingPeople.getId()){
				return -1;
			}else if(this.getId()>meetingPeople.getId()){
				return 1;
			}else{
				return 1;
			}
			
		}
	}
}
