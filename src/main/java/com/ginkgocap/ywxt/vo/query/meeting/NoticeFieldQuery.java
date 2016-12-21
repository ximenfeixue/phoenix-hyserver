/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;



public class NoticeFieldQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 通知字段id */
	private java.lang.Long id;
	/** 通知ID */
	private java.lang.Long noticeId;
	/** 对应字段 */
	private java.lang.String field;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getNoticeId() {
		return this.noticeId;
	}
	
	public void setNoticeId(java.lang.Long value) {
		this.noticeId = value;
	}
	
	public java.lang.String getField() {
		return this.field;
	}
	
	public void setField(java.lang.String value) {
		this.field = value;
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
				.append("}}")
				.toString().replaceAll(",}", "}");
		}

	
}

