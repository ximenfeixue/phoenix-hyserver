/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.common.base.BaseEntity;


public class MeetingLabel extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//标签主键
	private Long id;
    //标签名字
	private String labelName;
    //标签创建者ID
	private Long createId;
    //标签创建者名字
	private String createName;
    //标签创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

	public MeetingLabel() {}

	public MeetingLabel(Long id) {
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setLabelName(String value) {
		this.labelName = value;
	}
	
	public String getLabelName() {
		return this.labelName;
	}
	public void setCreateId(Long value) {
		this.createId = value;
	}
	
	public Long getCreateId() {
		return this.createId;
	}
	public void setCreateName(String value) {
		this.createName = value;
	}
	
	public String getCreateName() {
		return this.createName;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
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
			.append("LabelName",getLabelName())
			.append("CreateId",getCreateId())
			.append("CreateName",getCreateName())
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
			.append("'labelName':'"+getLabelName()+"',")
			.append("'createId':'"+getCreateId()+"',")
			.append("'createName':'"+getCreateName()+"',")
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
		if(obj instanceof MeetingLabel == false) return false;
		if(this == obj) return true;
		MeetingLabel other = (MeetingLabel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}