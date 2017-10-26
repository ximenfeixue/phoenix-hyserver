/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.model.meeting.MeetingOrgan;
import com.ginkgocap.ywxt.model.meeting.MeetingPeople;
import com.ginkgocap.ywxt.model.meeting.MeetingTopic;

public class MeetingMemberListQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
	/** 会议序号 */
	private Long id;
	/** 会议名称 */
	private String meetingName;
	/** 会议地点 */
	private String meetingAddress;
	 /** 会议地点X坐标  */
	private String meetingAddressPosX;
	 /** 会议地点Y坐标  */	
	private String meetingAddressPosY;
	 /**  会议地点国家    */	
	public String country;
	 /**会议地点省份  */	
	public String province;
	 /** 会议地点市 */	
	public String city;
	 /**会议地点城镇  */	
	public String town;
	/** 会议开始时间 */
	private Date startTime;
	/** 会议结束时间 */
	private Date endTime;
	/** 会议类型    1：无嘉宾2：无主讲 */
	private Integer meetingType;
	/** 会议状态    0：草稿，1：发起 */
	private Integer meetingStatus;
	/** 是否保密 */
	private Boolean isSecrecy;
	/** 会议人数 */
	private Integer memberCount;
	/** 会议介绍 */
	private String meetingDesc;
	/** 创建人ID */
	private java.lang.Long createId;
	/** 创建人姓名 */
	private String createName;
	/** 创建人头像 */
	private String createImage;
	/** 附件id */
	private String taskId;
	/** 创建时间 */
	private Date createTime;
	/** 类型（0是会议，1是邀请函）*/
	private String  type;
	/** 类型（0是会议，1是邀请函）*/
	private String  isSignUp;
	/** 会议封面图片*/
	private String path;
	/** 是否归档 人员处理会议的状态	0：默认，1：归档，2：删除*/
	private String memberMeetStatus;
	/** 会议距离我的距离 */
	private Double distance;
	/** 参会时间 */
	private Date attendMeetTime;
	//会议删除标记 0：默认、1：删除
	private int meetingDeleteFlag = 0;
	//参会人删除标记 0：默认、1：删除
	private int memberDeleteFlag = 0;
	//参会状态 0:未答复 1:接受邀请 2:拒绝邀请
	private int attendMeetStatus;
	//人脉
	private List<MeetingPeople> listMeetingPeople;
	//组织
	private List<MeetingOrgan> listMeetingOrgan;
	//知识和需求
	private List<MeetingData> listMeetingData;
	//知识
	private List<MeetingData> listMeetingKnowledge;
	//需求
	private List<MeetingData> listMeetingRequirement;
	//笔记
	private List<MeetingNoteQuery> listMeetingNoteQuery;
	//议题
	private List<MeetingTopic> listMeetingTopic;
	//参会人数
	private int count = 1;
	/**
	 * 环信的群组id
	 */
	private String groupId;
	//置顶 0：未置顶 1：已置顶
	private byte top;
	//禁用 0：未禁用 1：已禁用
	private byte disable;
	//付费 ：0：免费 1：付费
	private byte isPay;
	//支付金额
	private BigDecimal payMoney;
	// 公开活动 0 ：默认不需要审核 1：需要审核
	private Byte reviewFlag;
	// 签到提醒时间 （0 —— 120）
	private Integer signReminderTime;
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public String getMeetingName() {
		return this.meetingName;
	}
	
	public void setMeetingName(String value) {
		this.meetingName = value;
	}
	
	public String getMeetingAddress() {
		return this.meetingAddress;
	}
	
	public void setMeetingAddress(String value) {
		this.meetingAddress = value;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
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

	public Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(Date value) {
		this.startTime = value;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date value) {
		this.endTime = value;
	}
	
	public Integer getMeetingType() {
		return this.meetingType;
	}
	
	public void setMeetingType(Integer value) {
		this.meetingType = value;
	}
	
	public Integer getMeetingStatus() {
		return this.meetingStatus;
	}
	
	public void setMeetingStatus(Integer value) {
		this.meetingStatus = value;
	}
	
	public Boolean getIsSecrecy() {
		return this.isSecrecy;
	}
	
	public void setIsSecrecy(Boolean value) {
		this.isSecrecy = value;
	}
	
	public Integer getMemberCount() {
		return this.memberCount;
	}
	
	public void setMemberCount(Integer value) {
		this.memberCount = value;
	}
	
	public String getMeetingDesc() {
		return this.meetingDesc;
	}
	
	public void setMeetingDesc(String value) {
		this.meetingDesc = value;
	}
	public java.lang.Long getCreateId() {
		return createId;
	}

	public void setCreateId(java.lang.Long createId) {
		this.createId = createId;
	}

	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String value) {
		this.taskId = value;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMemberMeetStatus() {
		return memberMeetStatus;
	}

	public void setMemberMeetStatus(String memberMeetStatus) {
		this.memberMeetStatus = memberMeetStatus;
	}

	public String getIsSignUp() {
		return isSignUp;
	}

	public void setIsSignUp(String isSignUp) {
		this.isSignUp = isSignUp;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateImage() {
		return createImage;
	}

	public void setCreateImage(String createImage) {
		this.createImage = createImage;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
 
	public Date getAttendMeetTime() {
		return attendMeetTime;
	}

	public void setAttendMeetTime(Date attendMeetTime) {
		this.attendMeetTime = attendMeetTime;
	}
	
	public int getMeetingDeleteFlag() {
		return meetingDeleteFlag;
	}

	public void setMeetingDeleteFlag(int meetingDeleteFlag) {
		this.meetingDeleteFlag = meetingDeleteFlag;
	}

	public int getMemberDeleteFlag() {
		return memberDeleteFlag;
	}

	public void setMemberDeleteFlag(int memberDeleteFlag) {
		this.memberDeleteFlag = memberDeleteFlag;
	}

	public int getAttendMeetStatus() {
		return attendMeetStatus;
	}

	public void setAttendMeetStatus(int attendMeetStatus) {
		this.attendMeetStatus = attendMeetStatus;
	}

	public List<MeetingPeople> getListMeetingPeople() {
		return listMeetingPeople;
	}

	public void setListMeetingPeople(List<MeetingPeople> listMeetingPeople) {
		this.listMeetingPeople = listMeetingPeople;
	}

	public List<MeetingOrgan> getListMeetingOrgan() {
		return listMeetingOrgan;
	}

	public void setListMeetingOrgan(List<MeetingOrgan> listMeetingOrgan) {
		this.listMeetingOrgan = listMeetingOrgan;
	}

	public List<MeetingData> getListMeetingData() {
		return listMeetingData;
	}

	public void setListMeetingData(List<MeetingData> listMeetingData) {
		this.listMeetingData = listMeetingData;
		if(listMeetingData != null) {
			List<MeetingData> listKnowledge = new ArrayList<MeetingData>();
			List<MeetingData> listRequirement = new ArrayList<MeetingData>();
			for(MeetingData meetingData : listMeetingData) {
				if(meetingData != null) {
					if(meetingData.getDataType() == 1) {
						listKnowledge.add(meetingData);
					} else if(meetingData.getDataType() == 0) {
						listRequirement.add(meetingData);
					}
				}
			}
			this.setListMeetingKnowledge(listKnowledge);
			this.setListMeetingRequirement(listRequirement);
		}
	}

	public List<MeetingData> getListMeetingKnowledge() {
		return listMeetingKnowledge;
	}

	public void setListMeetingKnowledge(List<MeetingData> listMeetingKnowledge) {
		this.listMeetingKnowledge = listMeetingKnowledge;
	}

	public List<MeetingData> getListMeetingRequirement() {
		return listMeetingRequirement;
	}

	public void setListMeetingRequirement(List<MeetingData> listMeetingRequirement) {
		this.listMeetingRequirement = listMeetingRequirement;
	}

	public List<MeetingNoteQuery> getListMeetingNoteQuery() {
		return listMeetingNoteQuery;
	}

	public void setListMeetingNoteQuery(List<MeetingNoteQuery> listMeetingNoteQuery) {
		this.listMeetingNoteQuery = listMeetingNoteQuery;
	}

	public List<MeetingTopic> getListMeetingTopic() {
		return listMeetingTopic;
	}

	public void setListMeetingTopic(List<MeetingTopic> listMeetingTopic) {
		this.listMeetingTopic = listMeetingTopic;
	}
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
			.append("CrateId",getCreateId())
			.append("TaskId",getTaskId())
			.append("CrateTime",getCreateTime())
			.append("GroupId",getGroupId())
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
			.append("'crateId':'"+getCreateId()+"',")
			.append("'taskId':'"+getTaskId()+"',")
			.append("'groupId':'"+getGroupId()+"',")
			.append("'crateTime':'"+getCreateTime()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
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