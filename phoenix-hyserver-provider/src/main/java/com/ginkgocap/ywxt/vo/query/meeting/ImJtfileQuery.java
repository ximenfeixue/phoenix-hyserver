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


public class ImJtfileQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** 上级ID */
	private java.lang.Integer parentId;
	/** 父级类型：1.MUCInfoBean会议 */
	private java.lang.Integer parentType;
	/** 文件地址 */
	private java.lang.String url;
	/** 后缀名 jpg,png,amr,pdf等 */
	private java.lang.String suffixName;
	/** 类型 */
	private java.lang.Integer type;
	/** 文件名 */
	private java.lang.String fileName;
	/** 文件大小 */
	private java.lang.Integer fileSize;
	/** 0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构 */
	private java.lang.Integer moduleType;
	/** 附件索引 */
	private java.lang.String taskId;
	/** 备用1 */
	private java.lang.String reserved1;
	/** 备用2 */
	private java.lang.String reserved2;
	/** 备用3 */
	private java.lang.String reserved3;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	public java.lang.Integer getParentType() {
		return this.parentType;
	}
	
	public void setParentType(java.lang.Integer value) {
		this.parentType = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getSuffixName() {
		return this.suffixName;
	}
	
	public void setSuffixName(java.lang.String value) {
		this.suffixName = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	
	public java.lang.Integer getFileSize() {
		return this.fileSize;
	}
	
	public void setFileSize(java.lang.Integer value) {
		this.fileSize = value;
	}
	
	public java.lang.Integer getModuleType() {
		return this.moduleType;
	}
	
	public void setModuleType(java.lang.Integer value) {
		this.moduleType = value;
	}
	
	public java.lang.String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(java.lang.String value) {
		this.taskId = value;
	}
	
	public java.lang.String getReserved1() {
		return this.reserved1;
	}
	
	public void setReserved1(java.lang.String value) {
		this.reserved1 = value;
	}
	
	public java.lang.String getReserved2() {
		return this.reserved2;
	}
	
	public void setReserved2(java.lang.String value) {
		this.reserved2 = value;
	}
	
	public java.lang.String getReserved3() {
		return this.reserved3;
	}
	
	public void setReserved3(java.lang.String value) {
		this.reserved3 = value;
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
				.append("'parentId':'"+getParentId()+"',")
				.append("'parentType':'"+getParentType()+"',")
				.append("'url':'"+getUrl()+"',")
				.append("'suffixName':'"+getSuffixName()+"',")
				.append("'type':'"+getType()+"',")
				.append("'fileName':'"+getFileName()+"',")
				.append("'fileSize':'"+getFileSize()+"',")
				.append("'moduleType':'"+getModuleType()+"',")
				.append("'taskId':'"+getTaskId()+"',")
				.append("'reserved1':'"+getReserved1()+"',")
				.append("'reserved2':'"+getReserved2()+"',")
				.append("'reserved3':'"+getReserved3()+"',")
				.append("}}")
				.toString().replaceAll(",}", "}");
		}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

