/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

import javacommon.base.*;


import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;


public class ImMucinfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ImMucinfo";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "会议标题";
	public static final String ALIAS_SUBJECT = "会议主题（只有会议才有 主题，普通群聊无主题）";
	public static final String ALIAS_TYPE = "0-内部机构;1-普通群聊;2-会议";
	public static final String ALIAS_ORGANIZATION_ID = "机构ID";
	public static final String ALIAS_CONTENT = "会议内容";
	public static final String ALIAS_MAX = "人数上限";
	public static final String ALIAS_STICK_TYPE = "是否置顶 0否，1是";
	public static final String ALIAS_AUTO_SAVE_TYPE = "是否保存聊天记录，0否，1是，目前是自动保存";
	public static final String ALIAS_TIME = "会议开始时间，预约时间可调整， 及时已召开，相当于再次预约。预约时间没到的会议自动置顶";
	public static final String ALIAS_STATUS = "1-正常召开；2-预约中，等待开始；3-已解散，解散后用户不可见";
	public static final String ALIAS_CREATE_USER_ID = "create_User_Id";
	
	//date formats
	public static final String FORMAT_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: Id 
     */	
	
	private java.lang.Integer id;
    /**
     * 会议标题       db_column: title 
     */	
	@Length(max=255)
	private java.lang.String title;
    /**
     * 会议主题（只有会议才有 主题，普通群聊无主题）       db_column: subject 
     */	
	@Length(max=255)
	private java.lang.String subject;
    /**
     * 0-内部机构;1-普通群聊;2-会议       db_column: type 
     */	
	
	private java.lang.Integer type;
    /**
     * 机构ID       db_column: organization_Id 
     */	
	
	private java.lang.Long organizationId;
    /**
     * 会议内容       db_column: content 
     */	
	@Length(max=255)
	private java.lang.String content;
    /**
     * 人数上限       db_column: max 
     */	
	
	private java.lang.Integer max;
    /**
     * 是否置顶 0否，1是       db_column: stick_Type 
     */	
	
	private java.lang.Integer stickType;
    /**
     * 是否保存聊天记录，0否，1是，目前是自动保存       db_column: auto_Save_Type 
     */	
	
	private java.lang.Integer autoSaveType;
    /**
     * 会议开始时间，预约时间可调整， 及时已召开，相当于再次预约。预约时间没到的会议自动置顶       db_column: time 
     */	
	
	private java.util.Date time;
    /**
     * 1-正常召开；2-预约中，等待开始；3-已解散，解散后用户不可见       db_column: status 
     */	
	@Length(max=255)
	private java.lang.String status;
    /**
     * create_User_Id       db_column: create_User_Id 
     */	
	
	private java.lang.Integer createUserId;
	//columns END

	private Long affairId;
	
	public ImMucinfo(){
	}

	public ImMucinfo(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setSubject(java.lang.String value) {
		this.subject = value;
	}
	
	public java.lang.String getSubject() {
		return this.subject;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setOrganizationId(java.lang.Long value) {
		this.organizationId = value;
	}
	
	public java.lang.Long getOrganizationId() {
		return this.organizationId;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setMax(java.lang.Integer value) {
		this.max = value;
	}
	
	public java.lang.Integer getMax() {
		return this.max;
	}
	public void setStickType(java.lang.Integer value) {
		this.stickType = value;
	}
	
	public java.lang.Integer getStickType() {
		return this.stickType;
	}
	public void setAutoSaveType(java.lang.Integer value) {
		this.autoSaveType = value;
	}
	
	public java.lang.Integer getAutoSaveType() {
		return this.autoSaveType;
	}
	public String getTimeString() {
		return DateConvertUtils.format(getTime(), FORMAT_TIME);
	}
	public void setTimeString(String value) {
		setTime(DateConvertUtils.parse(value, FORMAT_TIME,java.util.Date.class));
	}
	
	public void setTime(java.util.Date value) {
		this.time = value;
	}
	
	public java.util.Date getTime() {
		return this.time;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setCreateUserId(java.lang.Integer value) {
		this.createUserId = value;
	}
	
	public java.lang.Integer getCreateUserId() {
		return this.createUserId;
	}
	
	public Long getAffairId() {
		return affairId;
	}

	public void setAffairId(Long affairId) {
		this.affairId = affairId;
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
			.append("Title",getTitle())
			.append("Subject",getSubject())
			.append("Type",getType())
			.append("OrganizationId",getOrganizationId())
			.append("Content",getContent())
			.append("Max",getMax())
			.append("StickType",getStickType())
			.append("AutoSaveType",getAutoSaveType())
			.append("Time",getTime())
			.append("Status",getStatus())
			.append("CreateUserId",getCreateUserId())
			.append("affairId",getAffairId())
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
			.append("'title':'"+getTitle()+"',")
			.append("'subject':'"+getSubject()+"',")
			.append("'type':'"+getType()+"',")
			.append("'organizationId':'"+getOrganizationId()+"',")
			.append("'content':'"+getContent()+"',")
			.append("'max':'"+getMax()+"',")
			.append("'stickType':'"+getStickType()+"',")
			.append("'autoSaveType':'"+getAutoSaveType()+"',")
			.append("'time':'"+getTime()+"',")
			.append("'status':'"+getStatus()+"',")
			.append("'createUserId':'"+getCreateUserId()+"',")
			.append("'affairId':'"+getAffairId()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ImMucinfo == false) return false;
		if(this == obj) return true;
		ImMucinfo other = (ImMucinfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

