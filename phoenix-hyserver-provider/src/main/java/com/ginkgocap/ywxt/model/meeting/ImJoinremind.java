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


public class ImJoinremind extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ImJoinremind";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_MUC_ID = "群ID";
	public static final String ALIAS_TYPE = "类型 1加入群";
	public static final String ALIAS_OPERATOR_ID = "操作人";
	public static final String ALIAS_OPERATOR_DATE = "操作时间";
	public static final String ALIAS_STATUS = "状态 0，已读，1未读";
	
	//date formats
	public static final String FORMAT_OPERATOR_DATE = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: Id 
     */	
	
	private java.lang.Integer id;
    /**
     * 用户ID       db_column: user_Id 
     */	
	
	private java.lang.Integer userId;
    /**
     * 群ID       db_column: muc_Id 
     */	
	
	private java.lang.Integer mucId;
    /**
     * 类型 1加入群       db_column: type 
     */	
	
	private java.lang.Integer type;
    /**
     * 操作人       db_column: operator_Id 
     */	
	
	private java.lang.Integer operatorId;
    /**
     * 操作时间       db_column: operator_Date 
     */	
	
	private java.util.Date operatorDate;
    /**
     * 状态 0，已读，1未读       db_column: status 
     */	
	@Length(max=255)
	private java.lang.String status;
	//columns END

	public ImJoinremind(){
	}

	public ImJoinremind(
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
	public void setMucId(java.lang.Integer value) {
		this.mucId = value;
	}
	
	public java.lang.Integer getMucId() {
		return this.mucId;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setOperatorId(java.lang.Integer value) {
		this.operatorId = value;
	}
	
	public java.lang.Integer getOperatorId() {
		return this.operatorId;
	}
	public String getOperatorDateString() {
		return DateConvertUtils.format(getOperatorDate(), FORMAT_OPERATOR_DATE);
	}
	public void setOperatorDateString(String value) {
		setOperatorDate(DateConvertUtils.parse(value, FORMAT_OPERATOR_DATE,java.util.Date.class));
	}
	
	public void setOperatorDate(java.util.Date value) {
		this.operatorDate = value;
	}
	
	public java.util.Date getOperatorDate() {
		return this.operatorDate;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
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
			.append("MucId",getMucId())
			.append("Type",getType())
			.append("OperatorId",getOperatorId())
			.append("OperatorDate",getOperatorDate())
			.append("Status",getStatus())
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
			.append("'mucId':'"+getMucId()+"',")
			.append("'type':'"+getType()+"',")
			.append("'operatorId':'"+getOperatorId()+"',")
			.append("'operatorDate':'"+getOperatorDate()+"',")
			.append("'status':'"+getStatus()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ImJoinremind == false) return false;
		if(this == obj) return true;
		ImJoinremind other = (ImJoinremind)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

