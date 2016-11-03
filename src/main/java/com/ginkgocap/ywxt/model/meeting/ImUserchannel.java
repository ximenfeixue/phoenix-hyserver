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


public class ImUserchannel extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ImUserchannel";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "用户id，该表中， 一个userID只能保存一条记录，如果有新的channel过来， 覆盖老的记录。";
	public static final String ALIAS_CHANNEL_ID = "百度云推送用户识别id，对应一个确定手机";
	public static final String ALIAS_SECRET_KEY = "DES加密私钥";
	public static final String ALIAS_BAIDU_USER_ID = "baiduUserId";
	public static final String ALIAS_CHANNEL_TYPE = "客户端类型 1: web, 2: pc, 3:android,  4:ios, 5:wp";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: Id 
     */	
	
	private java.lang.Integer id;
    /**
     * 用户id，该表中， 一个userID只能保存一条记录，如果有新的channel过来， 覆盖老的记录。       db_column: user_Id 
     */	
	
	private java.lang.Integer userId;
    /**
     * 百度云推送用户识别id，对应一个确定手机       db_column: channel_Id 
     */	
	@Length(max=255)
	private java.lang.String channelId;
    /**
     * DES加密私钥       db_column: secret_Key 
     */	
	@Length(max=255)
	private java.lang.String secretKey;
    /**
     * baiduUserId       db_column: baidu_User_Id 
     */	
	@Length(max=255)
	private java.lang.String baiduUserId;
    /**
     * 客户端类型 1: web, 2: pc, 3:android,  4:ios, 5:wp       db_column: channel_Type 
     */	
	
	private java.lang.Integer channelType;
	//columns END

	public ImUserchannel(){
	}

	public ImUserchannel(
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
	public void setChannelId(java.lang.String value) {
		this.channelId = value;
	}
	
	public java.lang.String getChannelId() {
		return this.channelId;
	}
	public void setSecretKey(java.lang.String value) {
		this.secretKey = value;
	}
	
	public java.lang.String getSecretKey() {
		return this.secretKey;
	}
	public void setBaiduUserId(java.lang.String value) {
		this.baiduUserId = value;
	}
	
	public java.lang.String getBaiduUserId() {
		return this.baiduUserId;
	}
	public void setChannelType(java.lang.Integer value) {
		this.channelType = value;
	}
	
	public java.lang.Integer getChannelType() {
		return this.channelType;
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
			.append("ChannelId",getChannelId())
			.append("SecretKey",getSecretKey())
			.append("BaiduUserId",getBaiduUserId())
			.append("ChannelType",getChannelType())
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
			.append("'channelId':'"+getChannelId()+"',")
			.append("'secretKey':'"+getSecretKey()+"',")
			.append("'baiduUserId':'"+getBaiduUserId()+"',")
			.append("'channelType':'"+getChannelType()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ImUserchannel == false) return false;
		if(this == obj) return true;
		ImUserchannel other = (ImUserchannel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

