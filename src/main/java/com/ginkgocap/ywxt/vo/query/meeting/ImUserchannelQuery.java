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


public class ImUserchannelQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Integer id;
	/** 用户id，该表中， 一个userID只能保存一条记录，如果有新的channel过来， 覆盖老的记录。 */
	private java.lang.Integer userId;
	/** 百度云推送用户识别id，对应一个确定手机 */
	private java.lang.String channelId;
	/** DES加密私钥 */
	private java.lang.String secretKey;
	/** baiduUserId */
	private java.lang.String baiduUserId;
	/** 客户端类型 1: web, 2: pc, 3:android,  4:ios, 5:wp */
	private java.lang.Integer channelType;

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
	
	public java.lang.String getChannelId() {
		return this.channelId;
	}
	
	public void setChannelId(java.lang.String value) {
		this.channelId = value;
	}
	
	public java.lang.String getSecretKey() {
		return this.secretKey;
	}
	
	public void setSecretKey(java.lang.String value) {
		this.secretKey = value;
	}
	
	public java.lang.String getBaiduUserId() {
		return this.baiduUserId;
	}
	
	public void setBaiduUserId(java.lang.String value) {
		this.baiduUserId = value;
	}
	
	public java.lang.Integer getChannelType() {
		return this.channelType;
	}
	
	public void setChannelType(java.lang.Integer value) {
		this.channelType = value;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

