/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.model.meeting.MeetingPeople;

public class MeetingMiniQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 会议序号 */
	private java.lang.Long meetingId;
	/** 会议名称 */
	private String meetingName;
	/** 会议开始时间 */
	private Date startTime;
	/** 会议对应人脉 */
	private List<MeetingPeople> listMeetingPeople;
	/** 会议对应需求*/
	private List<MeetingData> listMeetingRequirement;
	/** 会议对应知识 */
	private List<MeetingData> listMeetingKnowledge;
	/** 会议对应会议笔记 */
	private List<MeetingNoteQuery> listMeetingNoteQuery;
	/** 人脉数量 */
	private Integer peopleCount;
	/** 需求数量 */
	private Integer requirementCount;
	/** 知识数量 */
	private Integer knowledgeCount;
	/** 会议表记数量 */
	private Integer noteCount;
	
	/**
	 * 环信群组id
	 */
	private String groupId;
	
	public java.lang.Long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(java.lang.Long meetingId) {
		this.meetingId = meetingId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public List<MeetingPeople> getListMeetingPeople() {
		return listMeetingPeople;
	}
	public void setListMeetingPeople(List<MeetingPeople> listMeetingPeople) {
		this.listMeetingPeople = listMeetingPeople;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Integer getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	public Integer getNoteCount() {
		return noteCount;
	}
	public void setNoteCount(Integer noteCount) {
		this.noteCount = noteCount;
	}
	public List<MeetingNoteQuery> getListMeetingNoteQuery() {
		return listMeetingNoteQuery;
	}
	public void setListMeetingNoteQuery(List<MeetingNoteQuery> listMeetingNoteQuery) {
		this.listMeetingNoteQuery = listMeetingNoteQuery;
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
	public Integer getRequirementCount() {
		return requirementCount;
	}
	public void setRequirementCount(Integer requirementCount) {
		this.requirementCount = requirementCount;
	}
	public Integer getKnowledgeCount() {
		return knowledgeCount;
	}
	public void setKnowledgeCount(Integer knowledgeCount) {
		this.knowledgeCount = knowledgeCount;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}

