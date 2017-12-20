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

import com.ginkgocap.ywxt.model.meeting.*;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.openqa.selenium.By;

public class MeetingQuery extends Live implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

	/** 会议名称 */
	private String meetingName;
	/** 会议地点 */
	private String meetingAddress;
	 /** 会议地点X坐标  */
	private String meetingAddressPosX;
	 /** 会议地点Y坐标  */	
	private String meetingAddressPosY;
	 /**  会议地点国家    */	
	private int country;
	 /**会议地点省份  */	
	private String province;
	 /** 会议地点市 */	
	private String city;
	 /**会议地点城镇  */	
	private String town;
	/** 会议开始时间 */
	private Date startTime;
	/** 会议结束时间 */
	private Date endTime;
	/** 会议类型    0：无议题1：有议题*/
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
	private Long createId;
	/** 创建人姓名 */
	private String createName;
	/** 创建人头像 */
	private String createImage;
	//创建人公司
	private String createCompany;
	//创建人职位
	private String createJob;
	/** 附件id */
	private String taskId;
	/** 创建时间 */
	private Date createTime;
	/** 删除标识位(0：默认、1：删除)*/
	private int isDelete = 0;
	/** 支付状态 0:默认支付失败 1:支付成功 */
	private Byte payStatus = 0;
	/**会议成员**/
	private List<MeetingMember>  listMeetingMember;
	/**会议成员**/
	private List<MeetingMember>  listSpeaker;
	/**会议图片**/
	private List<MeetingPic>  listMeetingPic;
	/** 附件列表 **/
	private List<MeetingFile> listMeetingFile;
	/**会议时间**/
	private List<MeetingTime> listMeetingTime;
	/**会议议题**/
	private List<MeetingTopic> listMeetingTopic;
	/**会议资料**/
	private List<MeetingData> listMeetingData;
	/**会议需求**/
	private List<MeetingData> listMeetingRequirement;
	/**会议知识**/
	private List<MeetingData> listMeetingKnowledge;
	/**会议人脉**/
	private List<MeetingPeople> listMeetingPeople;
	/**会议组织**/
	private List<MeetingOrgan> listMeetingOrgan;
	/**会议议题VO**/
	private List<MeetingTopicQuery> listMeetingTopicQuery;
	/**会议详情小模块**/
	private List<MeetingDetail> listMeetingDetail;
	/**用户报名会议必填字段**/
	private List<MeetingSignLabel> listMeetingSignLabel;
	/**参会总人数**/
	private Integer attendMeetingCount;
	/**签到人数**/
	private Integer signInCount;
	/** 类型（0是会议，1是邀请函）*/
	private String  type;
	/** 是否签到 0：未签，1：已签  */
	private Integer isSign;
	/** 类型（0是会议，1是邀请函）*/
	private String  isSignUp;
	/** 会议封面图片*/
	private String path;
	/** 是否归档 人员处理会议的状态	0：默认，1：归档，2：删除*/
	private String memberMeetStatus;
	/** 行业列表 */
	private List<String> listIndustry;
	//会议笔记
	private List<MeetingNoteQuery> listMeetingNoteQuery;
	//环信群聊的groupid
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
	private BigDecimal payMoney = new BigDecimal(0);
	// 是否会议开始时畅聊  0 是   1 不是
	private int sendFalg;
	// 公开活动 0 ：默认不需要审核 1：需要审核
	private Byte reviewFlag;
	// 签到提醒时间 （0 —— 120）
	private Integer signReminderTime;

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
	
	
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String value) {
		this.taskId = value;
	}

	public List<MeetingMember> getListMeetingMember() {
		return listMeetingMember;
	}

	public void setListMeetingMember(List<MeetingMember> listMeetingMember) {
		this.listMeetingMember = listMeetingMember;
	}

	public List<MeetingPic> getListMeetingPic() {
		return listMeetingPic;
	}

	public void setListMeetingPic(List<MeetingPic> listMeetingPic) {
		this.listMeetingPic = listMeetingPic;
	}

	public List<MeetingFile> getListMeetingFile() {
		return listMeetingFile;
	}

	public void setListMeetingFile(List<MeetingFile> listMeetingFile) {
		this.listMeetingFile = listMeetingFile;
	}

	public List<MeetingTime> getListMeetingTime() {
		return listMeetingTime;
	}

	public void setListMeetingTime(List<MeetingTime> listMeetingTime) {
		this.listMeetingTime = listMeetingTime;
	}

	public List<MeetingTopic> getListMeetingTopic() {
		return listMeetingTopic;
	}

	public void setListMeetingTopic(List<MeetingTopic> listMeetingTopic) {
		this.listMeetingTopic = listMeetingTopic;
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

	public List<MeetingTopicQuery> getListMeetingTopicQuery() {
		return listMeetingTopicQuery;
	}

	public void setListMeetingTopicQuery(
			List<MeetingTopicQuery> listMeetingTopicQuery) {
		this.listMeetingTopicQuery = listMeetingTopicQuery;
	}
	

	public Integer getAttendMeetingCount() {
		return attendMeetingCount;
	}

	public void setAttendMeetingCount(Integer attendMeetingCount) {
		this.attendMeetingCount = attendMeetingCount;
	}
	
	public Integer getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(Integer signInCount) {
		this.signInCount = signInCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsSignUp() {
		return isSignUp;
	}

	public void setIsSignUp(String isSignUp) {
		this.isSignUp = isSignUp;
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
	public List<MeetingSignLabel> getListMeetingSignLabel() {
		return listMeetingSignLabel;
	}

	public void setListMeetingSignLabel(List<MeetingSignLabel> listMeetingSignLabel) {
		this.listMeetingSignLabel = listMeetingSignLabel;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public List<MeetingMember> getListSpeaker() {
		return listSpeaker;
	}

	public void setListSpeaker(List<MeetingMember> listSpeaker) {
		this.listSpeaker = listSpeaker;
	}


	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
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

	public String getCreateCompany() {
		return createCompany;
	}

	public void setCreateCompany(String createCompany) {
		this.createCompany = createCompany;
	}

	public String getCreateJob() {
		return createJob;
	}

	public void setCreateJob(String createJob) {
		this.createJob = createJob;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<MeetingData> getListMeetingRequirement() {
		return listMeetingRequirement;
	}

	public void setListMeetingRequirement(List<MeetingData> listMeetingRequirement) {
		this.listMeetingRequirement = listMeetingRequirement;
	}

	public List<MeetingData> getListMeetingKnowledge() {
		return listMeetingKnowledge;
	}

	public void setListMeetingKnowledge(List<MeetingData> listMeetingKnowledge) {
		this.listMeetingKnowledge = listMeetingKnowledge;
	}

	public List<String> getListIndustry() {
		return listIndustry;
	}

	public void setListIndustry(List<String> listIndustry) {
		this.listIndustry = listIndustry;
	}

	public List<MeetingNoteQuery> getListMeetingNoteQuery() {
		return listMeetingNoteQuery;
	}

	public void setListMeetingNoteQuery(List<MeetingNoteQuery> listMeetingNoteQuery) {
		this.listMeetingNoteQuery = listMeetingNoteQuery;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
			.append("FormatedDesc",getFormatedDesc())
			.append("CreateUserType",getCreateUserType())
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
			.append("'crateTime':'"+getCreateTime()+"',")
			.append("'groupId':'"+getGroupId()+"',")
			.append("'formatedDesc':'"+getFormatedDesc()+"',")
			.append("'createUserType':'"+getCreateUserType())
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

	public List<MeetingDetail> getListMeetingDetail() {
		return listMeetingDetail;
	}

	public void setListMeetingDetail(List<MeetingDetail> listMeetingDetail) {
		this.listMeetingDetail = listMeetingDetail;
	}

	public Boolean getSecrecy() {
		return isSecrecy;
	}

	public void setSecrecy(Boolean secrecy) {
		isSecrecy = secrecy;
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

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}
}