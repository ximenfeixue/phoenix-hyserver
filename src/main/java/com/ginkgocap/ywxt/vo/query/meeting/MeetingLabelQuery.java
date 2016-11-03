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




import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.common.base.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;


public class MeetingLabelQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 标签主键 */
	private java.lang.Long id;
	/** 标签名字 */
	private java.lang.String labelName;
	/** 标签创建者ID */
	private java.lang.Long createId;
	/** 标签创建者名字 */
	private java.lang.String createName;
	/** 标签创建时间 */
	private java.util.Date createTime;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getLabelName() {
		return this.labelName;
	}
	
	public void setLabelName(java.lang.String value) {
		this.labelName = value;
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
		 * 名称: toJson
		 * 描述: 生成json串
		  * @since  2014-09-14
		 * @author qingc
		 */
		public String toJson() {
			 return new StringBuilder()
			.append("{ 'meeting':{")
				.append("'id':'"+getId()+"',")
				.append("'labelName':'"+getLabelName()+"',")
				.append("'createId':'"+getCreateId()+"',")
				.append("'createName':'"+getCreateName()+"',")
				.append("'createTime':'"+getCreateTime()+"',")
				.append("}}")
				.toString().replaceAll(",}", "}");
		}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

