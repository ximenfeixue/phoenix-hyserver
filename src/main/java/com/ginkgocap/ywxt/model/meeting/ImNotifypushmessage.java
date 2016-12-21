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


public class ImNotifypushmessage extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ImNotifypushmessage";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "userId";
	public static final String ALIAS_NOTIFY_TYPE = "通知类型 1-新动态，2-新好友，3-新的群";
	public static final String ALIAS_COUNT = "通知数量";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: Id 
     */	
	
	private java.lang.Integer id;
    /**
     * userId       db_column: user_Id 
     */	
	
	private java.lang.Integer userId;
    /**
     * 通知类型 1-新动态，2-新好友，3-新的群       db_column: notify_Type 
     */	
	
	private java.lang.Integer notifyType;
    /**
     * 通知数量       db_column: count 
     */	
	
	private java.lang.Long count;
	//columns END

	public ImNotifypushmessage(){
	}

	public ImNotifypushmessage(
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
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setNotifyType(java.lang.Integer value) {
		this.notifyType = value;
	}
	
	public java.lang.Integer getNotifyType() {
		return this.notifyType;
	}
	public void setCount(java.lang.Long value) {
		this.count = value;
	}
	
	public java.lang.Long getCount() {
		return this.count;
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
			.append("UserId",getUserId())
			.append("NotifyType",getNotifyType())
			.append("Count",getCount())
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
			.append("'userId':'"+getUserId()+"',")
			.append("'notifyType':'"+getNotifyType()+"',")
			.append("'count':'"+getCount()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ImNotifypushmessage == false) return false;
		if(this == obj) return true;
		ImNotifypushmessage other = (ImNotifypushmessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

