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


public class ImMucmemberQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** MUCInfo中的id，用来标识一个会议，一个MUCID在该表中会有n条记录，对于n个参与成员，包括主持人 */
	private java.lang.Integer mucid;
	/** 成员id，平台能力层提供该id，通过移动业务层模块获取 */
	private java.lang.Integer userId;
	/** 主持人，创建人  0否，1是 */
	private java.lang.Integer compereType;
	/** 加入时间 */
	private java.util.Date joinTime;
	/** 退出时间，null表示未退出 */
	private java.util.Date exitTime;
	/** 接受群聊消息通知  0否，1是 */
	private java.lang.Integer notifyType;
	/** 新消息条数，该用户在该会议中未读消息数 */
	private java.lang.Integer newMessageCount;

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
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getCompereType() {
		return this.compereType;
	}
	
	public void setCompereType(java.lang.Integer value) {
		this.compereType = value;
	}
	
	public java.util.Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(java.util.Date joinTime) {
		this.joinTime = joinTime;
	}

	public java.util.Date getExitTime() {
		return exitTime;
	}

	public void setExitTime(java.util.Date exitTime) {
		this.exitTime = exitTime;
	}

	public java.lang.Integer getNotifyType() {
		return this.notifyType;
	}
	
	public void setNotifyType(java.lang.Integer value) {
		this.notifyType = value;
	}
	
	public java.lang.Integer getNewMessageCount() {
		return this.newMessageCount;
	}
	
	public void setNewMessageCount(java.lang.Integer value) {
		this.newMessageCount = value;
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
				.append("'userId':'"+getUserId()+"',")
				.append("'compereType':'"+getCompereType()+"',")
				.append("'joinTime':'"+getJoinTime()+"',")
				.append("'exitTime':'"+getExitTime()+"',")
				.append("'notifyType':'"+getNotifyType()+"',")
				.append("'newMessageCount':'"+getNewMessageCount()+"',")
				.append("}}")
				.toString().replaceAll(",}", "}");
		}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

