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


public class MeetingMemberQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

	/** 成员序号 */
	private Long id;
	/** 人员ID */
	private Long memberId;
	/** 会议序号 */
	private Long meetingId;
	/** 会议角色 0：嘉宾，1：群众 */
	private Integer memberType;
	/** 人员姓名 */
	private String memberName;
	/** 人员图片 */
	private String memberPhoto;
	/** 人员处理会议的状态 0：默认，1：归档，2：删除 */
	private Integer memberMeetStatus;
	/** 参会状态 0.未答复 1接受邀请2拒绝邀请，3签到 4 报名 5取消报名*/
	private Integer attendMeetStatus;
	/** 签到距离      2千米以内显示距离，2千米以外显示地区  */
	private String signDistance;
	/** 参会方式 0邀请，1报名 */
	private Integer attendMeetType;
	/**处理会议报名：0：未处理：1：同意报名，2：拒绝报名      */
	private Integer excuteMeetSign;
	/**参会时间      */
	private Date attendMeetTime;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public Long getMeetingId() {
		return this.meetingId;
	}
	
	public void setMeetingId(Long value) {
		this.meetingId = value;
	}
	
	public Integer getMemberType() {
		return this.memberType;
	}
	
	public void setMemberType(Integer value) {
		this.memberType = value;
	}
	
	public String getMemberName() {
		return this.memberName;
	}
	
	public void setMemberName(String value) {
		this.memberName = value;
	}
	
	public String getMemberPhoto() {
		return this.memberPhoto;
	}
	
	public void setMemberPhoto(String value) {
		this.memberPhoto = value;
	}
	
	public Integer getMemberMeetStatus() {
		return this.memberMeetStatus;
	}
	
	public void setMemberMeetStatus(Integer value) {
		this.memberMeetStatus = value;
	}
	
	public Integer getAttendMeetStatus() {
		return this.attendMeetStatus;
	}
	
	public void setAttendMeetStatus(Integer value) {
		this.attendMeetStatus = value;
	}
	
	public Integer getAttendMeetType() {
		return this.attendMeetType;
	}
	
	public void setAttendMeetType(Integer value) {
		this.attendMeetType = value;
	}
	public String getSignDistance() {
		return signDistance;
	}

	public void setSignDistance(String signDistance) {
		this.signDistance = signDistance;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

