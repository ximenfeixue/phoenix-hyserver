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


public class ImRecordmessageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** 本人 */
	private java.lang.Integer userId;
	/** 私聊对方 */
	private java.lang.Integer userId2;
	/** 私聊信息表ID */
	private java.lang.Integer chatMessageId;
	/** 会议，群ID */
	private java.lang.Integer mucId;
	/** 会议，群 创建者ID */
	private java.lang.Integer mucCreateUserId;
	/** 会议，群聊消息表ID */
	private java.lang.Integer mucMessageId;
	/** 会议开始时间 */
	private java.util.Date mucStartDate;
	/** 1-私聊，2-群聊 */
	private java.lang.Integer type;
	/** 最后一条消息发送时间 */
	private java.util.Date lastMessageDate;
	/** 未读消息，新消息记录数 */
	private java.lang.Integer newCount;
	/** 消息记录开始时间 */
	private java.util.Date messageStartTime;
	/** 1-正常；0-已解散，解散后用户不可见 ; 2-畅聊列表不显示 */
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
	
	public java.lang.Integer getUserId2() {
		return this.userId2;
	}
	
	public void setUserId2(java.lang.Integer value) {
		this.userId2 = value;
	}
	
	public java.lang.Integer getChatMessageId() {
		return this.chatMessageId;
	}
	
	public void setChatMessageId(java.lang.Integer value) {
		this.chatMessageId = value;
	}
	
	public java.lang.Integer getMucId() {
		return this.mucId;
	}
	
	public void setMucId(java.lang.Integer value) {
		this.mucId = value;
	}
	
	public java.lang.Integer getMucCreateUserId() {
		return this.mucCreateUserId;
	}
	
	public void setMucCreateUserId(java.lang.Integer value) {
		this.mucCreateUserId = value;
	}
	
	public java.lang.Integer getMucMessageId() {
		return this.mucMessageId;
	}
	
	public void setMucMessageId(java.lang.Integer value) {
		this.mucMessageId = value;
	}
	
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	
	
	public java.lang.Integer getNewCount() {
		return this.newCount;
	}
	
	public void setNewCount(java.lang.Integer value) {
		this.newCount = value;
	}
	
	
	public java.util.Date getMucStartDate() {
		return mucStartDate;
	}

	public void setMucStartDate(java.util.Date mucStartDate) {
		this.mucStartDate = mucStartDate;
	}

	public java.util.Date getLastMessageDate() {
		return lastMessageDate;
	}

	public void setLastMessageDate(java.util.Date lastMessageDate) {
		this.lastMessageDate = lastMessageDate;
	}

	public java.util.Date getMessageStartTime() {
		return messageStartTime;
	}

	public void setMessageStartTime(java.util.Date messageStartTime) {
		this.messageStartTime = messageStartTime;
	}

	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
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
				.append("'userId':'"+getUserId()+"',")
				.append("'userId2':'"+getUserId2()+"',")
				.append("'chatMessageId':'"+getChatMessageId()+"',")
				.append("'mucId':'"+getMucId()+"',")
				.append("'mucCreateUserId':'"+getMucCreateUserId()+"',")
				.append("'mucMessageId':'"+getMucMessageId()+"',")
				.append("'mucStartDate':'"+getMucStartDate()+"',")
				.append("'type':'"+getType()+"',")
				.append("'lastMessageDate':'"+getLastMessageDate()+"',")
				.append("'newCount':'"+getNewCount()+"',")
				.append("'messageStartTime':'"+getMessageStartTime()+"',")
				.append("'status':'"+getStatus()+"',")
				.append("}}")
				.toString().replaceAll(",}", "}");
		}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

