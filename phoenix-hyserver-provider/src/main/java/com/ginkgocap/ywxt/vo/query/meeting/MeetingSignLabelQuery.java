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


public class MeetingSignLabelQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 会议报名必填标签 */
	private java.lang.Long id;
	/** 会议ID */
	private java.lang.Long meetingId;
	/** 标签名字 */
	private java.lang.String labelName;
	/** 是否自定义 */
	private Integer isCustom;
	/** 创建者ID */
	private java.lang.Long createId;
	/** 创建者名字 */
	private java.lang.String createName;
	/** 创建时间 */
	private java.util.Date createTime;

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
	
	public java.lang.String getLabelName() {
		return this.labelName;
	}
	
	public void setLabelName(java.lang.String value) {
		this.labelName = value;
	}
	
	public Integer getIsCustom() {
		return this.isCustom;
	}
	
	public void setIsCustom(Integer value) {
		this.isCustom = value;
	}
	
	public java.lang.Long getCreateId() {
		return this.createId;
	}
	
	public void setCreateId(java.lang.Long value) {
		this.createId = value;
	}
	
	public java.lang.String getCreateName() {
		return this.createName;
	}
	
	public void setCreateName(java.lang.String value) {
		this.createName = value;
	}
	
	
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
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
				.append("}}")
				.toString().replaceAll(",}", "}");
		}
}

