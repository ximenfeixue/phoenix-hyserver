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


public class ImMucmessage extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ImMucmessage";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MUCID = "会议ID";
	public static final String ALIAS_SENDER_ID = "发送方id，为userID，系统通知的用户id为0，比如某某邀请某某进入群";
	public static final String ALIAS_SENDER_TYPE = "0-系统用户发送，此时userID无效；1-普通用户发送，具体用户此时参考senderID";
	public static final String ALIAS_MSG = "消息内容";
	public static final String ALIAS_MSG_TYPE = "/**0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement*/";
	public static final String ALIAS_TIME = "消息生产时间， 以服务器时间为准";
	public static final String ALIAS_MESSAGE_ID = "消息id串，客户端随机生成，每条记录唯一";
	public static final String ALIAS_JT_FILE_URL = "文件地址";
	public static final String ALIAS_JT_FILE_SUFFIX_NAME = "后缀名 jpg,png,amr,pdf等";
	public static final String ALIAS_JT_FILE_TYPE = "内容type";
	public static final String ALIAS_JT_FILE_NAME = "文件名";
	public static final String ALIAS_JT_FILE_SIZE = "文件大小";
	public static final String ALIAS_JT_FILE_MODULE_TYPE = "0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构";
	public static final String ALIAS_JT_FILE_TASK_ID = "附件索引";
	public static final String ALIAS_JT_FILE_RESERVED1 = "备用1";
	public static final String ALIAS_JT_FILE_RESERVED2 = "备用2";
	public static final String ALIAS_JT_FILE_RESERVED3 = "备用3";
	public static final String ALIAS_SEQUENCE = "消息顺序号：按群活会议分组后的顺序号";
	
	//date formats
	public static final String FORMAT_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: Id 
     */	
	
	private java.lang.Integer id;
    /**
     * 会议ID       db_column: MUC_Id 
     */	
	
	private java.lang.Integer mucid;
    /**
     * 发送方id，为userID，系统通知的用户id为0，比如某某邀请某某进入群       db_column: sender_Id 
     */	
	
	private java.lang.Integer senderId;
    /**
     * 0-系统用户发送，此时userID无效；1-普通用户发送，具体用户此时参考senderID       db_column: sender_Type 
     */	
	
	private java.lang.Integer senderType;
    /**
     * 消息内容       db_column: msg 
     */	
	@Length(max=65535)
	private java.lang.String msg;
    /**
     * 0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement*       db_column: msg_Type 
     */	
	
	private java.lang.Integer msgType;
    /**
     * 消息生产时间， 以服务器时间为准       db_column: time 
     */	
	
	private java.util.Date time;
    /**
     * 消息id串，客户端随机生成，每条记录唯一       db_column: message_Id 
     */	
	@Length(max=255)
	private java.lang.String messageId;
    /**
     * 文件地址       db_column: jtFile_Url 
     */	
	@Length(max=65535)
	private java.lang.String jtFileUrl;
    /**
     * 后缀名 jpg,png,amr,pdf等       db_column: jtFile_Suffix_Name 
     */	
	@Length(max=65535)
	private java.lang.String jtFileSuffixName;
    /**
     * 内容type       db_column: jtFile_Type 
     */	
	@Length(max=255)
	private java.lang.String jtFileType;
    /**
     * 文件名       db_column: jtFile_Name 
     */	
	@Length(max=255)
	private java.lang.String jtFileName;
    /**
     * 文件大小       db_column: jtFile_Size 
     */	
	
	private java.lang.Integer jtFileSize;
    /**
     * 0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构       db_column: jtFile_Module_Type 
     */	
	
	private java.lang.Integer jtFileModuleType;
    /**
     * 附件索引       db_column: jtFile_Task_Id 
     */	
	@Length(max=255)
	private java.lang.String jtFileTaskId;
    /**
     * 备用1       db_column: jtFile_Reserved1 
     */	
	@Length(max=255)
	private java.lang.String jtFileReserved1;
    /**
     * 备用2       db_column: jtFile_Reserved2 
     */	
	@Length(max=255)
	private java.lang.String jtFileReserved2;
    /**
     * 备用3       db_column: jtFile_Reserved3 
     */	
	@Length(max=255)
	private java.lang.String jtFileReserved3;
    /**
     * 消息顺序号：按群活会议分组后的顺序号       db_column: sequence 
     */	
	
	private java.lang.Integer sequence;
	//columns END

	public ImMucmessage(){
	}

	public ImMucmessage(
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
	public void setMucid(java.lang.Integer value) {
		this.mucid = value;
	}
	
	public java.lang.Integer getMucid() {
		return this.mucid;
	}
	public void setSenderId(java.lang.Integer value) {
		this.senderId = value;
	}
	
	public java.lang.Integer getSenderId() {
		return this.senderId;
	}
	public void setSenderType(java.lang.Integer value) {
		this.senderType = value;
	}
	
	public java.lang.Integer getSenderType() {
		return this.senderType;
	}
	public void setMsg(java.lang.String value) {
		this.msg = value;
	}
	
	public java.lang.String getMsg() {
		return this.msg;
	}
	public void setMsgType(java.lang.Integer value) {
		this.msgType = value;
	}
	
	public java.lang.Integer getMsgType() {
		return this.msgType;
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
	public void setMessageId(java.lang.String value) {
		this.messageId = value;
	}
	
	public java.lang.String getMessageId() {
		return this.messageId;
	}
	public void setJtFileUrl(java.lang.String value) {
		this.jtFileUrl = value;
	}
	
	public java.lang.String getJtFileUrl() {
		return this.jtFileUrl;
	}
	public void setJtFileSuffixName(java.lang.String value) {
		this.jtFileSuffixName = value;
	}
	
	public java.lang.String getJtFileSuffixName() {
		return this.jtFileSuffixName;
	}
	public void setJtFileType(java.lang.String value) {
		this.jtFileType = value;
	}
	
	public java.lang.String getJtFileType() {
		return this.jtFileType;
	}
	public void setJtFileName(java.lang.String value) {
		this.jtFileName = value;
	}
	
	public java.lang.String getJtFileName() {
		return this.jtFileName;
	}
	public void setJtFileSize(java.lang.Integer value) {
		this.jtFileSize = value;
	}
	
	public java.lang.Integer getJtFileSize() {
		return this.jtFileSize;
	}
	public void setJtFileModuleType(java.lang.Integer value) {
		this.jtFileModuleType = value;
	}
	
	public java.lang.Integer getJtFileModuleType() {
		return this.jtFileModuleType;
	}
	public void setJtFileTaskId(java.lang.String value) {
		this.jtFileTaskId = value;
	}
	
	public java.lang.String getJtFileTaskId() {
		return this.jtFileTaskId;
	}
	public void setJtFileReserved1(java.lang.String value) {
		this.jtFileReserved1 = value;
	}
	
	public java.lang.String getJtFileReserved1() {
		return this.jtFileReserved1;
	}
	public void setJtFileReserved2(java.lang.String value) {
		this.jtFileReserved2 = value;
	}
	
	public java.lang.String getJtFileReserved2() {
		return this.jtFileReserved2;
	}
	public void setJtFileReserved3(java.lang.String value) {
		this.jtFileReserved3 = value;
	}
	
	public java.lang.String getJtFileReserved3() {
		return this.jtFileReserved3;
	}
	public void setSequence(java.lang.Integer value) {
		this.sequence = value;
	}
	
	public java.lang.Integer getSequence() {
		return this.sequence;
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
			.append("Mucid",getMucid())
			.append("SenderId",getSenderId())
			.append("SenderType",getSenderType())
			.append("Msg",getMsg())
			.append("MsgType",getMsgType())
			.append("Time",getTime())
			.append("MessageId",getMessageId())
			.append("JtFileUrl",getJtFileUrl())
			.append("JtFileSuffixName",getJtFileSuffixName())
			.append("JtFileType",getJtFileType())
			.append("JtFileName",getJtFileName())
			.append("JtFileSize",getJtFileSize())
			.append("JtFileModuleType",getJtFileModuleType())
			.append("JtFileTaskId",getJtFileTaskId())
			.append("JtFileReserved1",getJtFileReserved1())
			.append("JtFileReserved2",getJtFileReserved2())
			.append("JtFileReserved3",getJtFileReserved3())
			.append("Sequence",getSequence())
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
			.append("'mucid':'"+getMucid()+"',")
			.append("'senderId':'"+getSenderId()+"',")
			.append("'senderType':'"+getSenderType()+"',")
			.append("'msg':'"+getMsg()+"',")
			.append("'msgType':'"+getMsgType()+"',")
			.append("'time':'"+getTime()+"',")
			.append("'messageId':'"+getMessageId()+"',")
			.append("'jtFileUrl':'"+getJtFileUrl()+"',")
			.append("'jtFileSuffixName':'"+getJtFileSuffixName()+"',")
			.append("'jtFileType':'"+getJtFileType()+"',")
			.append("'jtFileName':'"+getJtFileName()+"',")
			.append("'jtFileSize':'"+getJtFileSize()+"',")
			.append("'jtFileModuleType':'"+getJtFileModuleType()+"',")
			.append("'jtFileTaskId':'"+getJtFileTaskId()+"',")
			.append("'jtFileReserved1':'"+getJtFileReserved1()+"',")
			.append("'jtFileReserved2':'"+getJtFileReserved2()+"',")
			.append("'jtFileReserved3':'"+getJtFileReserved3()+"',")
			.append("'sequence':'"+getSequence()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ImMucmessage == false) return false;
		if(this == obj) return true;
		ImMucmessage other = (ImMucmessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

