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


public class ImMucmember extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ImMucmember";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MUCID = "MUCInfo中的id，用来标识一个会议，一个MUCID在该表中会有n条记录，对于n个参与成员，包括主持人";
	public static final String ALIAS_USER_ID = "成员id，平台能力层提供该id，通过移动业务层模块获取";
	public static final String ALIAS_COMPERE_TYPE = "主持人，创建人  0否，1是";
	public static final String ALIAS_JOIN_TIME = "加入时间";
	public static final String ALIAS_EXIT_TIME = "退出时间，null表示未退出";
	public static final String ALIAS_NOTIFY_TYPE = "接受群聊消息通知  0否，1是";
	public static final String ALIAS_NEW_MESSAGE_COUNT = "新消息条数，该用户在该会议中未读消息数";
	
	//date formats
	public static final String FORMAT_JOIN_TIME = DATE_FORMAT;
	public static final String FORMAT_EXIT_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: Id 
     */	
	
	private java.lang.Integer id;
    /**
     * MUCInfo中的id，用来标识一个会议，一个MUCID在该表中会有n条记录，对于n个参与成员，包括主持人       db_column: MUC_Id 
     */	
	@NotNull 
	private java.lang.Integer mucid;
    /**
     * 成员id，平台能力层提供该id，通过移动业务层模块获取       db_column: user_Id 
     */	
	
	private java.lang.Integer userId;
    /**
     * 主持人，创建人  0否，1是       db_column: compere_Type 
     */	
	
	private java.lang.Integer compereType;
    /**
     * 加入时间       db_column: join_Time 
     */	
	
	private java.util.Date joinTime;
    /**
     * 退出时间，null表示未退出       db_column: exit_Time 
     */	
	
	private java.util.Date exitTime;
    /**
     * 接受群聊消息通知  0否，1是       db_column: notify_Type 
     */	
	
	private java.lang.Integer notifyType;
    /**
     * 新消息条数，该用户在该会议中未读消息数       db_column: new_Message_Count 
     */	
	
	private java.lang.Integer newMessageCount;
	//columns END

	public ImMucmember(){
	}

	public ImMucmember(
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
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setCompereType(java.lang.Integer value) {
		this.compereType = value;
	}
	
	public java.lang.Integer getCompereType() {
		return this.compereType;
	}
	public String getJoinTimeString() {
		return DateConvertUtils.format(getJoinTime(), FORMAT_JOIN_TIME);
	}
	public void setJoinTimeString(String value) {
		setJoinTime(DateConvertUtils.parse(value, FORMAT_JOIN_TIME,java.util.Date.class));
	}
	
	public void setJoinTime(java.util.Date value) {
		this.joinTime = value;
	}
	
	public java.util.Date getJoinTime() {
		return this.joinTime;
	}
	public String getExitTimeString() {
		return DateConvertUtils.format(getExitTime(), FORMAT_EXIT_TIME);
	}
	public void setExitTimeString(String value) {
		setExitTime(DateConvertUtils.parse(value, FORMAT_EXIT_TIME,java.util.Date.class));
	}
	
	public void setExitTime(java.util.Date value) {
		this.exitTime = value;
	}
	
	public java.util.Date getExitTime() {
		return this.exitTime;
	}
	public void setNotifyType(java.lang.Integer value) {
		this.notifyType = value;
	}
	
	public java.lang.Integer getNotifyType() {
		return this.notifyType;
	}
	public void setNewMessageCount(java.lang.Integer value) {
		this.newMessageCount = value;
	}
	
	public java.lang.Integer getNewMessageCount() {
		return this.newMessageCount;
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
			.append("UserId",getUserId())
			.append("CompereType",getCompereType())
			.append("JoinTime",getJoinTime())
			.append("ExitTime",getExitTime())
			.append("NotifyType",getNotifyType())
			.append("NewMessageCount",getNewMessageCount())
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
			.append("'userId':'"+getUserId()+"',")
			.append("'compereType':'"+getCompereType()+"',")
			.append("'joinTime':'"+getJoinTime()+"',")
			.append("'exitTime':'"+getExitTime()+"',")
			.append("'notifyType':'"+getNotifyType()+"',")
			.append("'newMessageCount':'"+getNewMessageCount()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ImMucmember == false) return false;
		if(this == obj) return true;
		ImMucmember other = (ImMucmember)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

