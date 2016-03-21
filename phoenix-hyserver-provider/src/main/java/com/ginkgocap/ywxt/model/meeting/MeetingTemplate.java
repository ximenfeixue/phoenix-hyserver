package com.ginkgocap.ywxt.model.meeting;

import java.io.Serializable;
import java.util.Date;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MeetingTemplate extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 3156188240045452150L;
	//模板ID
	private Long id;
	//模板名称
	private String templateName;
	//用户ID
	private Long userId;
	//包含模块 邀请参会人、邀请主讲人、填写报名信息，逗号隔开
	private String modules;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getModules() {
		return modules;
	}
	public void setModules(String modules) {
		this.modules = modules;
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
	 * 重写toString
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("TemplateName",getTemplateName())
			.append("Modules",getModules())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	/**
	 * 名称: toJson
	 * 描述: 生成json串
	 */
	public String toJson() {
		return new StringBuilder()
			.append("{ 'meetingTemplate':{")
			.append("'id':'"+getId()+"',")
			.append("'userId':'"+getUserId()+"',")
			.append("'templateName':'"+getTemplateName()+"',")
			.append("'modules':'"+getModules()+"',")
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
		if(obj instanceof Meeting == false) return false;
		if(this == obj) return true;
		Meeting other = (Meeting)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}