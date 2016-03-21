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


public class ImJoinremindQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** 用户ID */
	private java.lang.Integer userId;
	/** 群ID */
	private java.lang.Integer mucId;
	/** 类型 1加入群 */
	private java.lang.Integer type;
	/** 操作人 */
	private java.lang.Integer operatorId;
	/** 操作时间 */
	private java.util.Date operatorDate;
	/** 状态 0，已读，1未读 */
	private java.lang.String status;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getMucId() {
		return this.mucId;
	}
	
	public void setMucId(java.lang.Integer value) {
		this.mucId = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.Integer value) {
		this.operatorId = value;
	}
	
	
	public java.util.Date getOperatorDate() {
		return operatorDate;
	}

	public void setOperatorDate(java.util.Date operatorDate) {
		this.operatorDate = operatorDate;
	}

	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

