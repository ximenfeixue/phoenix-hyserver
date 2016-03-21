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


public class FileIndexQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.String id;
	/** 文件存放物的理路径 */
	private java.lang.String filePath;
	/** 文件标题 */
	private java.lang.String fileTitle;
	/** 文件大小 */
	private java.lang.Long fileSize;
	/** 文件使用状态 */
	private java.lang.Boolean status;
	/** 创建者ID */
	private java.lang.Long authorId;
	/** 加密形式 */
	private java.lang.String md5;
	/** 标示ID */
	private java.lang.String taskId;
	/** 模块类型 */
	private java.lang.Integer moduleType;
	/** 创建时间 */
	private java.util.Date ctime;
	/** 创建人名称 */
	private java.lang.String authorName;
	/** 解压编码 */
	private java.lang.String crc32;

	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getFilePath() {
		return this.filePath;
	}
	
	public void setFilePath(java.lang.String value) {
		this.filePath = value;
	}
	
	public java.lang.String getFileTitle() {
		return this.fileTitle;
	}
	
	public void setFileTitle(java.lang.String value) {
		this.fileTitle = value;
	}
	
	public java.lang.Long getFileSize() {
		return this.fileSize;
	}
	
	public void setFileSize(java.lang.Long value) {
		this.fileSize = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Long getAuthorId() {
		return this.authorId;
	}
	
	public void setAuthorId(java.lang.Long value) {
		this.authorId = value;
	}
	
	public java.lang.String getMd5() {
		return this.md5;
	}
	
	public void setMd5(java.lang.String value) {
		this.md5 = value;
	}
	
	public java.lang.String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(java.lang.String value) {
		this.taskId = value;
	}
	
	public java.lang.Integer getModuleType() {
		return this.moduleType;
	}
	
	public void setModuleType(java.lang.Integer value) {
		this.moduleType = value;
	}
	
	public java.util.Date getCtime() {
		return ctime;
	}

	public void setCtime(java.util.Date ctime) {
		this.ctime = ctime;
	}

	public java.lang.String getAuthorName() {
		return this.authorName;
	}
	
	public void setAuthorName(java.lang.String value) {
		this.authorName = value;
	}
	
	public java.lang.String getCrc32() {
		return this.crc32;
	}
	
	public void setCrc32(java.lang.String value) {
		this.crc32 = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

