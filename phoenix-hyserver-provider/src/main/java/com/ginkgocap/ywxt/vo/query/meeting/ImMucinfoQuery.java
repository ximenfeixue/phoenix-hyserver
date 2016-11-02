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


public class ImMucinfoQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** 会议标题 */
	private java.lang.String title;
	/** 会议主题（只有会议才有 主题，普通群聊无主题） */
	private java.lang.String subject;
	/** 0-内部机构;1-普通群聊;2-会议 */
	private java.lang.Integer type;
	/** 机构ID */
	private java.lang.Long organizationId;
	/** 会议内容 */
	private java.lang.String content;
	/** 人数上限 */
	private java.lang.Integer max;
	/** 是否置顶 0否，1是 */
	private java.lang.Integer stickType;
	/** 是否保存聊天记录，0否，1是，目前是自动保存 */
	private java.lang.Integer autoSaveType;
	/** 会议开始时间，预约时间可调整， 及时已召开，相当于再次预约。预约时间没到的会议自动置顶 */
	private java.util.Date time;
	/** 1-正常召开；2-预约中，等待开始；3-已解散，解散后用户不可见 */
	private java.lang.String status;
	/** create_User_Id */
	private java.lang.Integer createUserId;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getSubject() {
		return this.subject;
	}
	
	public void setSubject(java.lang.String value) {
		this.subject = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Long getOrganizationId() {
		return this.organizationId;
	}
	
	public void setOrganizationId(java.lang.Long value) {
		this.organizationId = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.Integer getMax() {
		return this.max;
	}
	
	public void setMax(java.lang.Integer value) {
		this.max = value;
	}
	
	public java.lang.Integer getStickType() {
		return this.stickType;
	}
	
	public void setStickType(java.lang.Integer value) {
		this.stickType = value;
	}
	
	public java.lang.Integer getAutoSaveType() {
		return this.autoSaveType;
	}
	
	public void setAutoSaveType(java.lang.Integer value) {
		this.autoSaveType = value;
	}
	
	
	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.Integer getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.Integer value) {
		this.createUserId = value;
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
				.append("'title':'"+getTitle()+"',")
				.append("'subject':'"+getSubject()+"',")
				.append("'type':'"+getType()+"',")
				.append("'organizationId':'"+getOrganizationId()+"',")
				.append("'content':'"+getContent()+"',")
				.append("'max':'"+getMax()+"',")
				.append("'stickType':'"+getStickType()+"',")
				.append("'autoSaveType':'"+getAutoSaveType()+"',")
				.append("'time':'"+getTime()+"',")
				.append("'status':'"+getStatus()+"',")
				.append("'createUserId':'"+getCreateUserId()+"',")
				.append("}}")
				.toString().replaceAll(",}", "}");
		}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

