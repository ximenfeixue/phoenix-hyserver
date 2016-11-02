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


public class ImChatmessage extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ImChatmessage";
	public static final String ALIAS_ID = "主键、递增";
	public static final String ALIAS_USER_ID1 = "私聊参与方,跟userID2构成一个聊天";
	public static final String ALIAS_USER_ID2 = "私聊参与方";
	public static final String ALIAS_USER_ID1_READ_STATUS = "对于id1，该条消息是否为新消息（未读为0,已读1）";
	public static final String ALIAS_USER_ID2_READ_STATUS = "对于id2，该条消息是否为新消息（未读为0,已读1）";
	public static final String ALIAS_SENDER_ID = "发送方id，为userID1和userID2中的一个";
	public static final String ALIAS_MSG = "msg";
	public static final String ALIAS_MSG_TYPE = "0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement";
	public static final String ALIAS_MESSAGE_ID = "消息id串，客户端随机生成，每条记录唯一";
	public static final String ALIAS_TIME = "消息生产时间， 以服务器时间为准";
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
	public static final String ALIAS_SEQUENCE = "消息顺序号：按私聊的用户组合后再分组后的顺序号";
	
	//date formats
	public static final String FORMAT_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 主键、递增       db_column: Id 
     */	
	
	private java.lang.Integer id;
    /**
     * 私聊参与方,跟userID2构成一个聊天       db_column: user_Id1 
     */	
	
	private java.lang.Integer userId1;
    /**
     * 私聊参与方       db_column: user_Id2 
     */	
	
	private java.lang.Integer userId2;
    /**
     * 对于id1，该条消息是否为新消息（未读为0,已读1）       db_column: userId1_Read_Status 
     */	
	@Length(max=2)
	private java.lang.String userId1ReadStatus;
    /**
     * 对于id2，该条消息是否为新消息（未读为0,已读1）       db_column: userId2_Read_Status 
     */	
	@Length(max=2)
	private java.lang.String userId2ReadStatus;
    /**
     * 发送方id，为userID1和userID2中的一个       db_column: sender_Id 
     */	
	
	private java.lang.Integer senderId;
    /**
     * msg       db_column: msg 
     */	
	@Length(max=65535)
	private java.lang.String msg;
    /**
     * 0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement       db_column: msg_Type 
     */	
	
	private java.lang.Integer msgType;
    /**
     * 消息id串，客户端随机生成，每条记录唯一       db_column: message_Id 
     */	
	@Length(max=255)
	private java.lang.String messageId;
    /**
     * 消息生产时间， 以服务器时间为准       db_column: time 
     */	
	
	private java.util.Date time;
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
	
	private java.lang.Integer jtFileType;
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
     * 消息顺序号：按私聊的用户组合后再分组后的顺序号       db_column: sequence 
     */	
	
	private java.lang.Integer sequence;
	//columns END

	public ImChatmessage(){
	}

	public ImChatmessage(
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
	public void setUserId1(java.lang.Integer value) {
		this.userId1 = value;
	}
	
	public java.lang.Integer getUserId1() {
		return this.userId1;
	}
	public void setUserId2(java.lang.Integer value) {
		this.userId2 = value;
	}
	
	public java.lang.Integer getUserId2() {
		return this.userId2;
	}
	public void setUserId1ReadStatus(java.lang.String value) {
		this.userId1ReadStatus = value;
	}
	
	public java.lang.String getUserId1ReadStatus() {
		return this.userId1ReadStatus;
	}
	public void setUserId2ReadStatus(java.lang.String value) {
		this.userId2ReadStatus = value;
	}
	
	public java.lang.String getUserId2ReadStatus() {
		return this.userId2ReadStatus;
	}
	public void setSenderId(java.lang.Integer value) {
		this.senderId = value;
	}
	
	public java.lang.Integer getSenderId() {
		return this.senderId;
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
	public void setMessageId(java.lang.String value) {
		this.messageId = value;
	}
	
	public java.lang.String getMessageId() {
		return this.messageId;
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
	public void setJtFileType(java.lang.Integer value) {
		this.jtFileType = value;
	}
	
	public java.lang.Integer getJtFileType() {
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
			.append("UserId1",getUserId1())
			.append("UserId2",getUserId2())
			.append("UserId1ReadStatus",getUserId1ReadStatus())
			.append("UserId2ReadStatus",getUserId2ReadStatus())
			.append("SenderId",getSenderId())
			.append("Msg",getMsg())
			.append("MsgType",getMsgType())
			.append("MessageId",getMessageId())
			.append("Time",getTime())
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
			.append("'userId1':'"+getUserId1()+"',")
			.append("'userId2':'"+getUserId2()+"',")
			.append("'userId1ReadStatus':'"+getUserId1ReadStatus()+"',")
			.append("'userId2ReadStatus':'"+getUserId2ReadStatus()+"',")
			.append("'senderId':'"+getSenderId()+"',")
			.append("'msg':'"+getMsg()+"',")
			.append("'msgType':'"+getMsgType()+"',")
			.append("'messageId':'"+getMessageId()+"',")
			.append("'time':'"+getTime()+"',")
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
		if(obj instanceof ImChatmessage == false) return false;
		if(this == obj) return true;
		ImChatmessage other = (ImChatmessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

