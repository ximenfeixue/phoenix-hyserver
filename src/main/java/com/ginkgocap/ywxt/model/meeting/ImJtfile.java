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




import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.common.base.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;


public class ImJtfile extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ImJtfile";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PARENT_ID = "上级ID";
	public static final String ALIAS_PARENT_TYPE = "父级类型：1.MUCInfoBean会议";
	public static final String ALIAS_URL = "文件地址";
	public static final String ALIAS_SUFFIX_NAME = "后缀名 jpg,png,amr,pdf等";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_FILE_NAME = "文件名";
	public static final String ALIAS_FILE_SIZE = "文件大小";
	public static final String ALIAS_MODULE_TYPE = "0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构";
	public static final String ALIAS_TASK_ID = "附件索引";
	public static final String ALIAS_RESERVED1 = "备用1";
	public static final String ALIAS_RESERVED2 = "备用2";
	public static final String ALIAS_RESERVED3 = "备用3";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: Id 
     */	
	
	private java.lang.Integer id;
    /**
     * 上级ID       db_column: parent_Id 
     */	
	
	private java.lang.Integer parentId;
    /**
     * 父级类型：1.MUCInfoBean会议       db_column: parent_Type 
     */	
	
	private java.lang.Integer parentType;
    /**
     * 文件地址       db_column: url 
     */	
	@Length(max=65535)
	private java.lang.String url;
    /**
     * 后缀名 jpg,png,amr,pdf等       db_column: suffix_Name 
     */	
	@Length(max=65535)
	private java.lang.String suffixName;
    /**
     * 类型       db_column: type 
     */	
	
	private java.lang.Integer type;
    /**
     * 文件名       db_column: file_Name 
     */	
	@Length(max=255)
	private java.lang.String fileName;
    /**
     * 文件大小       db_column: file_Size 
     */	
	
	private java.lang.Integer fileSize;
    /**
     * 0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构       db_column: module_Type 
     */	
	
	private java.lang.Integer moduleType;
    /**
     * 附件索引       db_column: task_Id 
     */	
	@Length(max=255)
	private java.lang.String taskId;
    /**
     * 备用1       db_column: reserved1 
     */	
	@Length(max=255)
	private java.lang.String reserved1;
    /**
     * 备用2       db_column: reserved2 
     */	
	@Length(max=255)
	private java.lang.String reserved2;
    /**
     * 备用3       db_column: reserved3 
     */	
	@Length(max=255)
	private java.lang.String reserved3;
	//columns END

	public ImJtfile(){
	}

	public ImJtfile(
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
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	public void setParentType(java.lang.Integer value) {
		this.parentType = value;
	}
	
	public java.lang.Integer getParentType() {
		return this.parentType;
	}
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setSuffixName(java.lang.String value) {
		this.suffixName = value;
	}
	
	public java.lang.String getSuffixName() {
		return this.suffixName;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	
	public java.lang.String getFileName() {
		return this.fileName;
	}
	public void setFileSize(java.lang.Integer value) {
		this.fileSize = value;
	}
	
	public java.lang.Integer getFileSize() {
		return this.fileSize;
	}
	public void setModuleType(java.lang.Integer value) {
		this.moduleType = value;
	}
	
	public java.lang.Integer getModuleType() {
		return this.moduleType;
	}
	public void setTaskId(java.lang.String value) {
		this.taskId = value;
	}
	
	public java.lang.String getTaskId() {
		return this.taskId;
	}
	public void setReserved1(java.lang.String value) {
		this.reserved1 = value;
	}
	
	public java.lang.String getReserved1() {
		return this.reserved1;
	}
	public void setReserved2(java.lang.String value) {
		this.reserved2 = value;
	}
	
	public java.lang.String getReserved2() {
		return this.reserved2;
	}
	public void setReserved3(java.lang.String value) {
		this.reserved3 = value;
	}
	
	public java.lang.String getReserved3() {
		return this.reserved3;
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
			.append("ParentId",getParentId())
			.append("ParentType",getParentType())
			.append("Url",getUrl())
			.append("SuffixName",getSuffixName())
			.append("Type",getType())
			.append("FileName",getFileName())
			.append("FileSize",getFileSize())
			.append("ModuleType",getModuleType())
			.append("TaskId",getTaskId())
			.append("Reserved1",getReserved1())
			.append("Reserved2",getReserved2())
			.append("Reserved3",getReserved3())
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
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ImJtfile == false) return false;
		if(this == obj) return true;
		ImJtfile other = (ImJtfile)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

