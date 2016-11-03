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


public class ImMucmessageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** 会议ID */
	private java.lang.Integer mucid;
	/** 发送方id，为userID，系统通知的用户id为0，比如某某邀请某某进入群 */
	private java.lang.Integer senderId;
	/** 0-系统用户发送，此时userID无效；1-普通用户发送，具体用户此时参考senderID */
	private java.lang.Integer senderType;
	private Date time;
	/** 消息内容 */
	private java.lang.String msg;
	/** /**0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement */
	private java.lang.Integer msgType;
	/** 消息生产时间， 以服务器时间为准 */
	private java.util.Date timeBegin;
	private java.util.Date timeEnd;
	/** 消息id串，客户端随机生成，每条记录唯一 */
	private java.lang.String messageId;
	/** 文件地址 */
	private java.lang.String jtFileUrl;
	/** 后缀名 jpg,png,amr,pdf等 */
	private java.lang.String jtFileSuffixName;
	/** 内容type */
	private java.lang.String jtFileType;
	/** 文件名 */
	private java.lang.String jtFileName;
	/** 文件大小 */
	private java.lang.Integer jtFileSize;
	/** 0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构 */
	private java.lang.Integer jtFileModuleType;
	/** 附件索引 */
	private java.lang.String jtFileTaskId;
	/** 备用1 */
	private java.lang.String jtFileReserved1;
	/** 备用2 */
	private java.lang.String jtFileReserved2;
	/** 备用3 */
	private java.lang.String jtFileReserved3;
	/** 消息顺序号：按群活会议分组后的顺序号 */
	private java.lang.Integer sequence;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getMucid() {
		return this.mucid;
	}
	
	public void setMucid(java.lang.Integer value) {
		this.mucid = value;
	}
	public java.lang.Integer getSenderId() {
		return this.senderId;
	}
	
	public void setSenderId(java.lang.Integer value) {
		this.senderId = value;
	}
	public java.lang.Integer getSenderType() {
		return this.senderType;
	}
	
	public void setSenderType(java.lang.Integer value) {
		this.senderType = value;
	}
	public java.lang.String getMsg() {
		return this.msg;
	}
	
	public void setMsg(java.lang.String value) {
		this.msg = value;
	}
	public java.lang.Integer getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(java.lang.Integer value) {
		this.msgType = value;
	}
	public java.util.Date getTime() {
		return this.time;
	}
	
	public void setTime(java.util.Date value) {
		this.time = value;
	}
	public java.lang.String getMessageId() {
		return this.messageId;
	}
	
	public void setMessageId(java.lang.String value) {
		this.messageId = value;
	}
	public java.lang.String getJtFileUrl() {
		return this.jtFileUrl;
	}
	
	public void setJtFileUrl(java.lang.String value) {
		this.jtFileUrl = value;
	}
	public java.lang.String getJtFileSuffixName() {
		return this.jtFileSuffixName;
	}
	
	public void setJtFileSuffixName(java.lang.String value) {
		this.jtFileSuffixName = value;
	}
	public java.lang.String getJtFileType() {
		return this.jtFileType;
	}
	
	public void setJtFileType(java.lang.String value) {
		this.jtFileType = value;
	}
	public java.lang.String getJtFileName() {
		return this.jtFileName;
	}
	
	public void setJtFileName(java.lang.String value) {
		this.jtFileName = value;
	}
	public java.lang.Integer getJtFileSize() {
		return this.jtFileSize;
	}
	
	public void setJtFileSize(java.lang.Integer value) {
		this.jtFileSize = value;
	}
	public java.lang.Integer getJtFileModuleType() {
		return this.jtFileModuleType;
	}
	
	public void setJtFileModuleType(java.lang.Integer value) {
		this.jtFileModuleType = value;
	}
	public java.lang.String getJtFileTaskId() {
		return this.jtFileTaskId;
	}
	
	public void setJtFileTaskId(java.lang.String value) {
		this.jtFileTaskId = value;
	}
	public java.lang.String getJtFileReserved1() {
		return this.jtFileReserved1;
	}
	
	public void setJtFileReserved1(java.lang.String value) {
		this.jtFileReserved1 = value;
	}
	public java.lang.String getJtFileReserved2() {
		return this.jtFileReserved2;
	}
	
	public void setJtFileReserved2(java.lang.String value) {
		this.jtFileReserved2 = value;
	}
	public java.lang.String getJtFileReserved3() {
		return this.jtFileReserved3;
	}
	
	public void setJtFileReserved3(java.lang.String value) {
		this.jtFileReserved3 = value;
	}
	public java.lang.Integer getSequence() {
		return this.sequence;
	}
	
	public void setSequence(java.lang.Integer value) {
		this.sequence = value;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

