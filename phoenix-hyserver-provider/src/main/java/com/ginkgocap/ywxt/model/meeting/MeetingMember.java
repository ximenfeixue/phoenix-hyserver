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
import com.ginkgocap.ywxt.utils.Utils;

@SuppressWarnings("rawtypes")
public class MeetingMember extends BaseEntity implements java.io.Serializable,Comparable{
	private static final long serialVersionUID = 5454155825314635342L;
	//成员序号
	private Long id;
    //人员ID
	private Long memberId;
    //会议序号
	private Long meetingId;
    //会议角色 0：嘉宾，1：群众
	private Integer memberType;
    //人员姓名
	private String memberName;
    //人员图片
	private String memberPhoto;
    //人员处理会议的状态 0.未答复 1接受邀请2拒绝邀请，3签到 4 报名 5取消报名
	private Integer memberMeetStatus;
    //参会状态 0.参会状态	0.未答复 1接受邀请2拒绝邀请， 4 报名 5取消报名
	private Integer attendMeetStatus;
	//是否签到 0：未签，1：已签
	private Integer isSign;
	//签到距离      2千米以内显示距离，2千米以外显示地区
	private String signDistance;
	//参会方式 0邀请，1报名
	private Integer attendMeetType;
	//处理会议报名：0：未处理：1：同意报名，2：拒绝报名
	private Integer excuteMeetSign;
	//参会时间
	private Date attendMeetTime;
	//是否显示邀请函 0：不显示，1：显示，默认显示
	private Long isShowInvitation = 1L;
	//删除参会人
	private int isDelete = 0;
	//环信群组id
	private String groupId;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	
	public MeetingMember() {}

	public MeetingMember(Long id) {
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	
	public void setMeetingId(Long value) {
		this.meetingId = value;
	}
	
	public Long getMeetingId() {
		return this.meetingId;
	}
	
	public void setMemberType(Integer value) {
		this.memberType = value;
	}
	
	public Integer getMemberType() {
		return this.memberType;
	}
	
	public void setMemberName(String value) {
		this.memberName = value;
	}
	
	public String getMemberName() {
		return this.memberName;
	}
	
	public void setMemberPhoto(String value) {
		this.memberPhoto = value;
	}
	
	public String getMemberPhoto() {
		return this.memberPhoto;
	}
	
	public void setMemberMeetStatus(Integer value) {
		this.memberMeetStatus = value;
	}
	
	public Integer getMemberMeetStatus() {
		return this.memberMeetStatus;
	}
	
	public void setAttendMeetStatus(Integer value) {
		this.attendMeetStatus = value;
	}
	
	public Integer getAttendMeetStatus() {
		return this.attendMeetStatus;
	}
	
	public void setAttendMeetType(Integer value) {
		this.attendMeetType = value;
	}
	
	public Integer getAttendMeetType() {
		return this.attendMeetType;
	}
	
	public String getSignDistance() {
		return signDistance;
	}

	public void setSignDistance(String signDistance) {
		this.signDistance = signDistance;
	}
	
	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public Integer getExcuteMeetSign() {
		return excuteMeetSign;
	}

	public void setExcuteMeetSign(Integer excuteMeetSign) {
		this.excuteMeetSign = excuteMeetSign;
	}
 
	public Date getAttendMeetTime() {
		return attendMeetTime;
	}

	public void setAttendMeetTime(Date attendMeetTime) {
		this.attendMeetTime = attendMeetTime;
	}

	public Long getIsShowInvitation() {
		return isShowInvitation;
	}

	public void setIsShowInvitation(Long isShowInvitation) {
		this.isShowInvitation = isShowInvitation;
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
			.append("MemberId",getMemberId())
			.append("MeetingId",getMeetingId())
			.append("MemberType",getMemberType())
			.append("MemberName",getMemberName())
			.append("MemberPhoto",getMemberPhoto())
			.append("MemberMeetStatus",getMemberMeetStatus())
			.append("AttendMeetStatus",getAttendMeetStatus())
			.append("AttendMeetType",getAttendMeetType())
			.append("ExcuteMeetSign",getExcuteMeetSign())
			.append("IsShowInvitation",getIsShowInvitation())
			.append("GroupId",getGroupId())
			.append("IsDelete",getIsDelete())
			.append("CreateTime",getCreateTime())
			.append("Update",getUpdateTime())
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
			.append("'memberId':'"+getMemberId()+"',")
			.append("'meetingId':'"+getMeetingId()+"',")
			.append("'memberType':'"+getMemberType()+"',")
			.append("'memberName':'"+getMemberName()+"',")
			.append("'memberPhoto':'"+getMemberPhoto()+"',")
			.append("'memberMeetStatus':'"+getMemberMeetStatus()+"',")
			.append("'attendMeetStatus':'"+getAttendMeetStatus()+"',")
			.append("'attendMeetType':'"+getAttendMeetType()+"',")
			.append("'excuteMeetSign':'"+getExcuteMeetSign())
			.append("'isShowInvitation':'"+getIsShowInvitation())
			.append("'groupId':'"+getGroupId())
			.append("'isDelete':'"+getGroupId())
			.append("'createTime':'"+getCreateTime())
			.append("'updateTime':'"+getUpdateTime())
			.append("}}")
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId()).toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MeetingMember == false) return false;
		if(this == obj) return true;
		MeetingMember other = (MeetingMember)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public int compareTo(Object o) {
		MeetingMember meetingMember=(MeetingMember)o;  
		if(Utils.isNullOrEmpty(this.getId())||Utils.isNullOrEmpty(meetingMember.getId())){
			return 1;
		}else{
			if(this.getId()< meetingMember.getId()){
				return -1;
			}else if(this.getId()>meetingMember.getId()){
				return 1;
			}else{
				return 1;
			}
		}
	}
}