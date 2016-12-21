/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MeetingSignLabelDataQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
	/** 用户报名信息主键 */
	private Long id;
	/** 会议必填标签ID */
	private Long mslabelId;
	/** 会议必填标签名字 */
	private String mslabelName;
	/** 用户必填信息 */
	private String labelContent;
	/** 参会成员ID */
	private Long memberId;
	/** 参会成员名字 */
	private String memberName;
	/** 是否自定义字段 */
	private Integer isCustomer;
	/** 是否人脉数据 */
	private Integer isPeopleData;
	/** 创建者ID */
	private Long createId;
	/** 创建者名字 */
	private String createName;
	/** 创建者时间 */
	private Date createTime;
	
	public MeetingSignLabelDataQuery() {
		
	}

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getMslabelId() {
		return this.mslabelId;
	}
	
	public void setMslabelId(Long value) {
		this.mslabelId = value;
	}
	
	public String getLabelContent() {
		return this.labelContent;
	}
	
	public void setLabelContent(String value) {
		this.labelContent = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public String getMemberName() {
		return this.memberName;
	}
	
	public void setMemberName(String value) {
		this.memberName = value;
	}
	
	public Long getCreateId() {
		return this.createId;
	}
	
	public void setCreateId(Long value) {
		this.createId = value;
	}
	
	public String getCreateName() {
		return this.createName;
	}
	
	public void setCreateName(String value) {
		this.createName = value;
	}
	
	
	
	
	public String getMslabelName() {
		return mslabelName;
	}

	public void setMslabelName(String mslabelName) {
		this.mslabelName = mslabelName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsPeopleData() {
		return isPeopleData;
	}

	public void setIsPeopleData(Integer isPeopleData) {
		this.isPeopleData = isPeopleData;
	}

	public Integer getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Integer isCustomer) {
		this.isCustomer = isCustomer;
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
				.append("MslabelId",getMslabelId())
				.append("LabelContent",getLabelContent())
				.append("MemberId",getMemberId())
				.append("MemberName",getMemberName())
				.append("CreateId",getCreateId())
				.append("CreateName",getCreateName())
				.append("CreateTime",getCreateTime())
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
				.append("'mslabelId':'"+getMslabelId()+"',")
				.append("'labelContent':'"+getLabelContent()+"',")
				.append("'memberId':'"+getMemberId()+"',")
				.append("'memberName':'"+getMemberName()+"',")
				.append("'createId':'"+getCreateId()+"',")
				.append("'createName':'"+getCreateName()+"',")
				.append("'createTime':'"+getCreateTime()+"',")
				.append("}}")
				.toString().replaceAll(",}", "}");
		}
		
}

