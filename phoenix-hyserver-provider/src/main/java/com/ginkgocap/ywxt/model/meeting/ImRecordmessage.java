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

public class ImRecordmessage extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// alias
	public static final String TABLE_ALIAS = "ImRecordmessage";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "本人";
	public static final String ALIAS_USER_ID2 = "私聊对方";
	public static final String ALIAS_CHAT_MESSAGE_ID = "私聊信息表ID";
	public static final String ALIAS_MUC_ID = "会议，群ID";
	public static final String ALIAS_MUC_CREATE_USER_ID = "会议，群 创建者ID";
	public static final String ALIAS_MUC_MESSAGE_ID = "会议，群聊消息表ID";
	public static final String ALIAS_MUC_START_DATE = "会议开始时间";
	public static final String ALIAS_TYPE = "1-私聊，2-群聊";
	public static final String ALIAS_LAST_MESSAGE_DATE = "最后一条消息发送时间";
	public static final String ALIAS_NEW_COUNT = "未读消息，新消息记录数";
	public static final String ALIAS_MESSAGE_START_TIME = "消息记录开始时间";
	public static final String ALIAS_STATUS = "1-正常；0-已解散，解散后用户不可见 ; 2-畅聊列表不显示";

	// date formats
	public static final String FORMAT_MUC_START_DATE = DATE_FORMAT;
	public static final String FORMAT_LAST_MESSAGE_DATE = DATE_FORMAT;
	public static final String FORMAT_MESSAGE_START_TIME = DATE_FORMAT;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	/**
	 * id db_column: Id
	 */

	private java.lang.Integer id;
	/**
	 * 本人 db_column: user_Id
	 */

	private java.lang.Integer userId;
	/**
	 * 私聊对方 db_column: user_Id2
	 */

	private java.lang.Integer userId2;
	/**
	 * 私聊信息表ID db_column: chat_Message_Id
	 */

	private java.lang.Integer chatMessageId;
	/**
	 * 会议，群ID db_column: muc_Id
	 */

	private java.lang.Integer mucId;
	/**
	 * 会议，群 创建者ID db_column: muc_Create_User_Id
	 */

	private java.lang.Integer mucCreateUserId;
	/**
	 * 会议，群聊消息表ID db_column: muc_Message_Id
	 */

	private java.lang.Integer mucMessageId;
	/**
	 * 会议开始时间 db_column: muc_Start_Date
	 */

	private java.util.Date mucStartDate;
	/**
	 * 1-私聊，2-群聊 db_column: type
	 */

	private java.lang.Integer type;
	/**
	 * 最后一条消息发送时间 db_column: last_Message_Date
	 */

	private java.util.Date lastMessageDate;
	/**
	 * 未读消息，新消息记录数 db_column: new_Count
	 */

	private java.lang.Integer newCount;
	/**
	 * 消息记录开始时间 db_column: message_Start_Time
	 */

	private java.util.Date messageStartTime;
	/**
	 * 1-正常；0-已解散，解散后用户不可见 ; 2-畅聊列表不显示 db_column: status
	 */
	@Length(max = 255)
	private java.lang.String status;

	/**
	 * 0 邀请函未读 1邀请函已读
	 */
	private Byte ifRead;

	public ImRecordmessage() {
	}

	public ImRecordmessage(java.lang.Integer id) {
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}

	public java.lang.Integer getUserId() {
		return this.userId;
	}

	public void setUserId2(java.lang.Integer value) {
		this.userId2 = value;
	}

	public java.lang.Integer getUserId2() {
		return this.userId2;
	}

	public void setChatMessageId(java.lang.Integer value) {
		this.chatMessageId = value;
	}

	public java.lang.Integer getChatMessageId() {
		return this.chatMessageId;
	}

	public void setMucId(java.lang.Integer value) {
		this.mucId = value;
	}

	public java.lang.Integer getMucId() {
		return this.mucId;
	}

	public void setMucCreateUserId(java.lang.Integer value) {
		this.mucCreateUserId = value;
	}

	public java.lang.Integer getMucCreateUserId() {
		return this.mucCreateUserId;
	}

	public void setMucMessageId(java.lang.Integer value) {
		this.mucMessageId = value;
	}

	public java.lang.Integer getMucMessageId() {
		return this.mucMessageId;
	}

	public String getMucStartDateString() {
		return DateConvertUtils.format(getMucStartDate(), FORMAT_MUC_START_DATE);
	}

	public void setMucStartDateString(String value) {
		setMucStartDate(DateConvertUtils.parse(value, FORMAT_MUC_START_DATE, java.util.Date.class));
	}

	public void setMucStartDate(java.util.Date value) {
		this.mucStartDate = value;
	}

	public java.util.Date getMucStartDate() {
		return this.mucStartDate;
	}

	public void setType(java.lang.Integer value) {
		this.type = value;
	}

	public java.lang.Integer getType() {
		return this.type;
	}

	public String getLastMessageDateString() {
		return DateConvertUtils.format(getLastMessageDate(), FORMAT_LAST_MESSAGE_DATE);
	}

	public void setLastMessageDateString(String value) {
		setLastMessageDate(DateConvertUtils.parse(value, FORMAT_LAST_MESSAGE_DATE, java.util.Date.class));
	}

	public void setLastMessageDate(java.util.Date value) {
		this.lastMessageDate = value;
	}

	public java.util.Date getLastMessageDate() {
		return this.lastMessageDate;
	}

	public void setNewCount(java.lang.Integer value) {
		this.newCount = value;
	}

	public java.lang.Integer getNewCount() {
		return this.newCount;
	}

	public String getMessageStartTimeString() {
		return DateConvertUtils.format(getMessageStartTime(), FORMAT_MESSAGE_START_TIME);
	}

	public void setMessageStartTimeString(String value) {
		setMessageStartTime(DateConvertUtils.parse(value, FORMAT_MESSAGE_START_TIME, java.util.Date.class));
	}

	public void setMessageStartTime(java.util.Date value) {
		this.messageStartTime = value;
	}

	public java.util.Date getMessageStartTime() {
		return this.messageStartTime;
	}

	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	public java.lang.String getStatus() {
		return this.status;
	}

	public Byte getIfRead() {
		return ifRead;
	}

	public void setIfRead(Byte ifRead) {
		this.ifRead = ifRead;
	}

	/**
	 * 名称: toString 描述: 重写toString方法
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("Id", getId())
				.append("UserId", getUserId()).append("UserId2", getUserId2())
				.append("ChatMessageId", getChatMessageId()).append("MucId", getMucId())
				.append("MucCreateUserId", getMucCreateUserId()).append("MucMessageId", getMucMessageId())
				.append("MucStartDate", getMucStartDate()).append("Type", getType())
				.append("LastMessageDate", getLastMessageDate()).append("NewCount", getNewCount())
				.append("MessageStartTime", getMessageStartTime()).append("Status", getStatus()).toString();
	}

	/**
	 * 名称: toJson 描述: 生成json串
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public String toJson() {
		return new StringBuilder().append("{ 'meeting':{").append("'id':'" + getId() + "',")
				.append("'userId':'" + getUserId() + "',").append("'userId2':'" + getUserId2() + "',")
				.append("'chatMessageId':'" + getChatMessageId() + "',").append("'mucId':'" + getMucId() + "',")
				.append("'mucCreateUserId':'" + getMucCreateUserId() + "',")
				.append("'mucMessageId':'" + getMucMessageId() + "',")
				.append("'mucStartDate':'" + getMucStartDate() + "',").append("'type':'" + getType() + "',")
				.append("'lastMessageDate':'" + getLastMessageDate() + "',")
				.append("'newCount':'" + getNewCount() + "',")
				.append("'messageStartTime':'" + getMessageStartTime() + "',")
				.append("'status':'" + getStatus() + "',").append("}}").toString().replaceAll(",}", "}");
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof ImRecordmessage == false)
			return false;
		if (this == obj)
			return true;
		ImRecordmessage other = (ImRecordmessage) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
