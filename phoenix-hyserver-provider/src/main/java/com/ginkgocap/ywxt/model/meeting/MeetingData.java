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


public class MeetingData extends BaseEntity implements java.io.Serializable,Comparable<MeetingData>{
	private static final long serialVersionUID = 5454155825314635342L;
	//资料序号
	private Long id;
    //会议序号
	private Long meetingId;
    //资料名称
	private String dataName;
    //资料ID
	private Long dataId;
    //资料类型 0需求，1知识
	private Integer dataType;
    //1-资讯，2-投融工具，3-行业，4-经典案例，5-图书报告，6-资产管理，7-宏观，8-观点，9-判例，10-法律法规，11-文章,12-投资，13-融资
	private Integer dataReqType;
    //资料对应的url
	private String dataUrl;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//关联关系
	private String relation;

	public MeetingData() {}

	public MeetingData(Long id) {
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
	
	public void setDataName(String value) {
		this.dataName = value;
	}
	
	public String getDataName() {
		return this.dataName;
	}
	
	public void setDataId(Long value) {
		this.dataId = value;
	}
	
	public Long getDataId() {
		return this.dataId;
	}
	
	public void setDataType(Integer value) {
		this.dataType = value;
	}
	
	public Integer getDataType() {
		return this.dataType;
	}
	
	public void setDataReqType(Integer value) {
		this.dataReqType = value;
	}
	
	public Integer getDataReqType() {
		return this.dataReqType;
	}
	
	public void setDataUrl(String value) {
		this.dataUrl = value;
	}
	
	public String getDataUrl() {
		return this.dataUrl;
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
		if(this.relation == null) {
			if(this.dataType != null 
					&& this.dataType.intValue() == 0) {
				relation = "需求关联";
			} else {
				relation = "知识关联";
			}
		}
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
			.append("DataName",getDataName())
			.append("DataId",getDataId())
			.append("DataType",getDataType())
			.append("DataReqType",getDataReqType())
			.append("DataUrl",getDataUrl())
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
			.append("'dataName':'"+getDataName()+"',")
			.append("'dataId':'"+getDataId()+"',")
			.append("'dataType':'"+getDataType()+"',")
			.append("'dataReqType':'"+getDataReqType()+"',")
			.append("'dataUrl':'"+getDataUrl()+"',")
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
		if(obj instanceof MeetingData == false) return false;
		if(this == obj) return true;
		MeetingData other = (MeetingData)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	public int compareTo(MeetingData meetingData) {
		if(Utils.isNullOrEmpty(this.getId())||Utils.isNullOrEmpty(meetingData.getId())){
			return 1;
		}else{
			if(this.getId()< meetingData.getId()){
				return -1;
			}else if(this.getId()>meetingData.getId()){
				return 1;
			}else{
				return 1;
			}
		}
	}
}