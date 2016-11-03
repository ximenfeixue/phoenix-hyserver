/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 *//*
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


public class FileIndex extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "FileIndex";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_FILE_PATH = "文件存放物的理路径";
	public static final String ALIAS_FILE_TITLE = "文件标题";
	public static final String ALIAS_FILE_SIZE = "文件大小";
	public static final String ALIAS_STATUS = "文件使用状态";
	public static final String ALIAS_AUTHOR_ID = "创建者ID";
	public static final String ALIAS_MD5 = "加密形式";
	public static final String ALIAS_TASK_ID = "标示ID";
	public static final String ALIAS_MODULE_TYPE = "模块类型";
	public static final String ALIAS_CTIME = "创建时间";
	public static final String ALIAS_AUTHOR_NAME = "创建人名称";
	public static final String ALIAS_CRC32 = "解压编码";
	
	//date formats
	public static final String FORMAT_CTIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    *//**
     * id       db_column: id 
     *//*	
	@Length(max=20)
	private java.lang.String id;
    *//**
     * 文件存放物的理路径       db_column: file_path 
     *//*	
	@Length(max=255)
	private java.lang.String filePath;
    *//**
     * 文件标题       db_column: file_title 
     *//*	
	@Length(max=255)
	private java.lang.String fileTitle;
    *//**
     * 文件大小       db_column: file_size 
     *//*	
	
	private java.lang.Long fileSize;
    *//**
     * 文件使用状态       db_column: status 
     *//*	
	
	private java.lang.Boolean status;
    *//**
     * 创建者ID       db_column: author_id 
     *//*	
	
	private java.lang.Long authorId;
    *//**
     * 加密形式       db_column: md5 
     *//*	
	@Length(max=255)
	private java.lang.String md5;
    *//**
     * 标示ID       db_column: task_id 
     *//*	
	@Length(max=255)
	private java.lang.String taskId;
    *//**
     * 模块类型       db_column: module_type 
     *//*	
	
	private java.lang.Integer moduleType;
    *//**
     * 创建时间       db_column: ctime 
     *//*	
	
	private java.util.Date ctime;
    *//**
     * 创建人名称       db_column: author_name 
     *//*	
	@Length(max=255)
	private java.lang.String authorName;
    *//**
     * 解压编码       db_column: crc32 
     *//*	
	@Length(max=24)
	private java.lang.String crc32;
	//columns END

	public FileIndex(){
	}

	public FileIndex(
		java.lang.String id
	){
		this.id = id;
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setFilePath(java.lang.String value) {
		this.filePath = value;
	}
	
	public java.lang.String getFilePath() {
		return this.filePath;
	}
	public void setFileTitle(java.lang.String value) {
		this.fileTitle = value;
	}
	
	public java.lang.String getFileTitle() {
		return this.fileTitle;
	}
	public void setFileSize(java.lang.Long value) {
		this.fileSize = value;
	}
	
	public java.lang.Long getFileSize() {
		return this.fileSize;
	}
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	public void setAuthorId(java.lang.Long value) {
		this.authorId = value;
	}
	
	public java.lang.Long getAuthorId() {
		return this.authorId;
	}
	public void setMd5(java.lang.String value) {
		this.md5 = value;
	}
	
	public java.lang.String getMd5() {
		return this.md5;
	}
	public void setTaskId(java.lang.String value) {
		this.taskId = value;
	}
	
	public java.lang.String getTaskId() {
		return this.taskId;
	}
	public void setModuleType(java.lang.Integer value) {
		this.moduleType = value;
	}
	
	public java.lang.Integer getModuleType() {
		return this.moduleType;
	}
	public String getCtimeString() {
		return DateConvertUtils.format(getCtime(), FORMAT_CTIME);
	}
	public void setCtimeString(String value) {
		setCtime(DateConvertUtils.parse(value, FORMAT_CTIME,java.util.Date.class));
	}
	
	public void setCtime(java.util.Date value) {
		this.ctime = value;
	}
	
	public java.util.Date getCtime() {
		return this.ctime;
	}
	public void setAuthorName(java.lang.String value) {
		this.authorName = value;
	}
	
	public java.lang.String getAuthorName() {
		return this.authorName;
	}
	public void setCrc32(java.lang.String value) {
		this.crc32 = value;
	}
	
	public java.lang.String getCrc32() {
		return this.crc32;
	}
*//**
 * 名称: toString
 * 描述: 重写toString方法
  * @since  2014-09-14
 * @author qingc
 *//*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("FilePath",getFilePath())
			.append("FileTitle",getFileTitle())
			.append("FileSize",getFileSize())
			.append("Status",getStatus())
			.append("AuthorId",getAuthorId())
			.append("Md5",getMd5())
			.append("TaskId",getTaskId())
			.append("ModuleType",getModuleType())
			.append("Ctime",getCtime())
			.append("AuthorName",getAuthorName())
			.append("Crc32",getCrc32())
			.toString();
	}
	*//**
	 * 名称: toJson
	 * 描述: 生成json串
	  * @since  2014-09-14
	 * @author qingc
	 *//*
	public String toJson() {
		 return new StringBuilder()
		.append("{ 'meeting':{")
			.append("'id':'"+getId()+"',")
			.append("'filePath':'"+getFilePath()+"',")
			.append("'fileTitle':'"+getFileTitle()+"',")
			.append("'fileSize':'"+getFileSize()+"',")
			.append("'status':'"+getStatus()+"',")
			.append("'authorId':'"+getAuthorId()+"',")
			.append("'md5':'"+getMd5()+"',")
			.append("'taskId':'"+getTaskId()+"',")
			.append("'moduleType':'"+getModuleType()+"',")
			.append("'ctime':'"+getCtime()+"',")
			.append("'authorName':'"+getAuthorName()+"',")
			.append("'crc32':'"+getCrc32()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FileIndex == false) return false;
		if(this == obj) return true;
		FileIndex other = (FileIndex)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

*/