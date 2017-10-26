/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.common.base.BaseEntity;

public class Meeting extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	//会议序号
	private Long id;
    //会议名称
	private String meetingName;
    //会议地点
	private String meetingAddress;
    //会议地点X坐标
	private String meetingAddressPosX;
	//会议地点Y坐标
	private String meetingAddressPosY;
	//会议地点国家
	public int country;
	//会议地点省份
	public String province;
	//会议地点市
	public String city;
	//会议地点城镇
	public String town;
    //会议开始时间
	private Date startTime;
    //会议结束时间
	private Date endTime;
    //会议类型    0：无议题1：有议题
	private Integer meetingType;
    //会议状态    0：草稿，1：发起
	private Integer meetingStatus;
    //是否保密
	private Boolean isSecrecy;
	//创建者姓名
	private String createName;
    //会议人数
	private Integer memberCount;
    //会议介绍
	private String meetingDesc;
    //创建人ID
	private Long createId;
    //附件id
	private String taskId;
    //创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//行业
	private String industry;
	//删除标识位(0：默认、1：删除)
	private int isDelete = 0;
	//环信生成的group id
	private String groupId;
	//会议描述 图文混排
	private String formatedDesc;
	//创建者类型 1：用户 2：组织
	private int createUserType = 1;
	//置顶 0：未置顶 1：已置顶
	private byte top;
	//禁用 0：未禁用 1：已禁用
	private byte disable;
	//付费 ：0：免费 1：付费
	private byte isPay;
	//支付金额
	private BigDecimal payMoney;
	// 是否会议开始时畅聊
	private int sendFalg;
	// 公开活动 0 ：默认不需要审核 1：需要审核
	private Byte reviewFlag;
	// 签到提醒时间 （0 —— 120）
	private Integer signReminderTime;

	public Meeting() {}

	public Meeting(Long id) {
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setMeetingName(String value) {
		this.meetingName = value;
	}
	
	public String getMeetingName() {
		return this.meetingName;
	}
	
	public void setMeetingAddress(String value) {
		this.meetingAddress = value;
	}
	
	public String getMeetingAddress() {
		return this.meetingAddress;
	}
	
	public String getMeetingAddressPosX() {
		return meetingAddressPosX;
	}

	public void setMeetingAddressPosX(String meetingAddressPosX) {
		this.meetingAddressPosX = meetingAddressPosX;
	}

	public String getMeetingAddressPosY() {
		return meetingAddressPosY;
	}

	public void setMeetingAddressPosY(String meetingAddressPosY) {
		this.meetingAddressPosY = meetingAddressPosY;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
	public void setStartTime(Date value) {
		this.startTime = value;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	
	public void setEndTime(Date value) {
		this.endTime = value;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setMeetingType(Integer value) {
		this.meetingType = value;
	}
	
	public Integer getMeetingType() {
		return this.meetingType;
	}
	
	public void setMeetingStatus(Integer value) {
		this.meetingStatus = value;
	}
	
	public Integer getMeetingStatus() {
		return this.meetingStatus;
	}
	
	public void setIsSecrecy(Boolean value) {
		this.isSecrecy = value;
	}
	
	public Boolean getIsSecrecy() {
		return this.isSecrecy;
	}
	
	public void setMemberCount(Integer value) {
		this.memberCount = value;
	}
	
	public Integer getMemberCount() {
		return this.memberCount;
	}
	
	public void setMeetingDesc(String value) {
		this.meetingDesc = value;
	}
	
	public String getMeetingDesc() {
		return this.meetingDesc;
	}
	
	public void setTaskId(String value) {
		this.taskId = value;
	}
	
	public String getTaskId() {
		return this.taskId;
	}
		
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public String getCreateName() {
		return createName;
	}

	public void setCraeteName(String createName) {
		this.createName = createName;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getFormatedDesc() {
		return formatedDesc;
	}

	public void setFormatedDesc(String formatedDesc) {
		this.formatedDesc = formatedDesc;
	}

	public int getCreateUserType() {
		return createUserType;
	}

	public void setCreateUserType(int createUserType) {
		this.createUserType = createUserType;
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
			.append("MeetingName",getMeetingName())
			.append("MeetingAddress",getMeetingAddress())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("MeetingType",getMeetingType())
			.append("MeetingStatus",getMeetingStatus())
			.append("IsSecrecy",getIsSecrecy())
			.append("MemberCount",getMemberCount())
			.append("MeetingDesc",getMeetingDesc())
			.append("CreateId",getCreateId())
			.append("TaskId",getTaskId())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("Industry",getIndustry())
			.append("GroupId",getGroupId())
			.append("IsDelete",getIsDelete())
			.append("FormatedDesc",getFormatedDesc())
			.append("CreateUserType",getCreateUserType())
			.append("Top", getTop())
			.append("Disable", getDisable())
			.append("IsPay", getIsPay())
			.append("PayMoney", getPayMoney())
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
			.append("'meetingName':'"+getMeetingName()+"',")
			.append("'meetingAddress':'"+getMeetingAddress()+"',")
			.append("'startTime':'"+getStartTime()+"',")
			.append("'endTime':'"+getEndTime()+"',")
			.append("'meetingType':'"+getMeetingType()+"',")
			.append("'meetingStatus':'"+getMeetingStatus()+"',")
			.append("'isSecrecy':'"+getIsSecrecy()+"',")
			.append("'memberCount':'"+getMemberCount()+"',")
			.append("'meetingDesc':'"+getMeetingDesc()+"',")
			.append("'createId':'"+getCreateId()+"',")
			.append("'taskId':'"+getTaskId()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'updateTime':'"+getUpdateTime()+"',")
			.append("'industry':'"+getIndustry()+"',")
			.append("'groupId':'"+getGroupId()+"',")
			.append("'isDelete':'"+getIsDelete()+"',")
			.append("'formatedDesc':'"+getFormatedDesc()+"',")
			.append("'createUserType':'"+getCreateUserType()+"',")
			.append("'top':'" + getTop() + "',")
			.append("'disable':'" + getDisable() + "',")
			.append("'isPay':'" + getIsPay() + "',")
			.append("'payMoney':'" + getPayMoney() + "',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Meeting == false) return false;
		if(this == obj) return true;
		Meeting other = (Meeting)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public byte getTop() {
		return top;
	}

	public void setTop(byte top) {
		this.top = top;
	}

	public byte getDisable() {
		return disable;
	}

	public void setDisable(byte disable) {
		this.disable = disable;
	}

	public byte getIsPay() {
		return isPay;
	}

	public void setIsPay(byte isPay) {
		this.isPay = isPay;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public int getSendFalg() {
		return sendFalg;
	}

	public void setSendFalg(int sendFalg) {
		this.sendFalg = sendFalg;
	}

	public Byte getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(Byte reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public Integer getSignReminderTime() {
		return signReminderTime;
	}

	public void setSignReminderTime(Integer signReminderTime) {
		this.signReminderTime = signReminderTime;
	}
}