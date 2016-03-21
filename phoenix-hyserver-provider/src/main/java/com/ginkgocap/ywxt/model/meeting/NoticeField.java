/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import java.util.Date;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NoticeField extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//ID
	private Long id;
    //通知ID
	private Long noticeId;
    //内容
	private String field;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	public NoticeField() {}

	public NoticeField(Long id) {
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setNoticeId(Long value) {
		this.noticeId = value;
	}
	
	public Long getNoticeId() {
		return this.noticeId;
	}
	public void setField(String value) {
		this.field = value;
	}
	
	public String getField() {
		return this.field;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
			.append("NoticeId",getNoticeId())
			.append("Field",getField())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
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
			.append("'noticeId':'"+getNoticeId()+"',")
			.append("'field':'"+getField()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'updateTime':'"+getUpdateTime()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof NoticeField == false) return false;
		if(this == obj) return true;
		NoticeField other = (NoticeField)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}