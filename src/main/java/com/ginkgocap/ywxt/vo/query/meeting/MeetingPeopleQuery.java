/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MeetingPeopleQuery implements Serializable {
	
    private static final long serialVersionUID = 3148176768559230877L;
	/** 人脉序号 */
	private Long id;
	/** 会议序号 */
	private Long meetingId;
	/** 会议名称 */
	private String meetingName;
	/** 人员ID */
	private Long peopleId;
	/** 人员姓名 */
	private String peopleName;
	/** 人员图片 */
	private String peoplePhoto;
	/** 人脉描述 */
	private String peopleDesc;
	/** 是否分享 */
	private Boolean isShare;
	/** 人脉类型 */
	private int peopleType;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getMeetingId() {
		return this.meetingId;
	}
	
	public void setMeetingId(Long value) {
		this.meetingId = value;
	}
	
	public Long getPeopleId() {
		return this.peopleId;
	}
	
	public void setPeopleId(Long value) {
		this.peopleId = value;
	}
	
	public String getPeopleName() {
		return this.peopleName;
	}
	
	public void setPeopleName(String value) {
		this.peopleName = value;
	}
	
	public String getPeoplePhoto() {
		return this.peoplePhoto;
	}
	
	public void setPeoplePhoto(String value) {
		this.peoplePhoto = value;
	}
	
	public String getPeopleDesc() {
		return this.peopleDesc;
	}
	
	public void setPeopleDesc(String value) {
		this.peopleDesc = value;
	}
	
	public Boolean getIsShare() {
		return this.isShare;
	}
	
	public void setIsShare(Boolean value) {
		this.isShare = value;
	}
	
	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public int getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(int peopleType) {
		this.peopleType = peopleType;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

