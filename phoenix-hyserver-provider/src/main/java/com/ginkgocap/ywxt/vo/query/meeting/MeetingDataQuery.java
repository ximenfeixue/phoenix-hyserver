/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

import java.util.*;

import javacommon.base.*;


import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;


public class MeetingDataQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 资料序号 */
	private java.lang.Long id;
	/** 会议序号 */
	private java.lang.Long meetingId;
	/** 会议名称 */
	private String meetingName;
	/** 资料名称 */
	private java.lang.String dataName;
	/** 资料ID */
	private java.lang.Long dataId;
	/** 资料类型 0需求，1知识 */
	private java.lang.Integer dataType;
	/** 1-资讯，2-投融工具，3-行业，4-经典案例，5-图书报告，6-资产管理，7-宏观，8-观点，9-判例，10-法律法规，11-文章,12-投资，13-融资 */
	private java.lang.Integer dataReqType;
	/** 资料对应的url */
	private java.lang.String dataUrl;
	/** 资料创建时间 */
	private Date createTime;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getMeetingId() {
		return this.meetingId;
	}
	
	public void setMeetingId(java.lang.Long value) {
		this.meetingId = value;
	}
	
	public java.lang.String getDataName() {
		return this.dataName;
	}
	
	public void setDataName(java.lang.String value) {
		this.dataName = value;
	}
	
	public java.lang.Long getDataId() {
		return this.dataId;
	}
	
	public void setDataId(java.lang.Long value) {
		this.dataId = value;
	}
	
	public java.lang.Integer getDataType() {
		return this.dataType;
	}
	
	public void setDataType(java.lang.Integer value) {
		this.dataType = value;
	}
	
	public java.lang.Integer getDataReqType() {
		return this.dataReqType;
	}
	
	public void setDataReqType(java.lang.Integer value) {
		this.dataReqType = value;
	}
	
	public java.lang.String getDataUrl() {
		return this.dataUrl;
	}
	
	public void setDataUrl(java.lang.String value) {
		this.dataUrl = value;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

