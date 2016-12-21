/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.utils.Utils;

import java.io.Serializable;
import java.util.List;





public class MonthMeetingQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 会议序号 */
	private String month;
	// 会议列表
	private List<MeetingMiniQuery> listMeetingMiniQuery;
	// 我创建和我参加会议列表
	private List<MeetingMemberListQuery> listMeetingMemberListQuery;
	// 我创建的会议列表
	private List<MeetingMemberListQuery> listMeetingMemberListQueryMyCreate;
	/** 人脉数量 */
	private Integer peopleCount=0;
	/** 需求数量 */
	private Integer requirementCount=0;
	/**知识数量 */
	private Integer knowledgeCount=0;
	/** 会议表记数量 */
	private Integer noteCount=0;
	/** 我创建和我参加会议数量 */
	private Integer attendAndCreateCount=0;
	/** 我创建会议数量 */
	private Integer createCount=0;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	public Integer getAttendAndCreateCount() {
		return attendAndCreateCount;
	}
	public void setAttendAndCreateCount(Integer attendAndCreateCount) {
		this.attendAndCreateCount = attendAndCreateCount;
	}
	public Integer getCreateCount() {
		return createCount;
	}
	public void setCreateCount(Integer createCount) {
		this.createCount = createCount;
	}
	public List<MeetingMemberListQuery> getListMeetingMemberListQuery() {
		return listMeetingMemberListQuery;
	}
	public void setListMeetingMemberListQuery(
			List<MeetingMemberListQuery> listMeetingMemberListQuery) {
		this.listMeetingMemberListQuery = listMeetingMemberListQuery;
	}
	public List<MeetingMiniQuery> getListMeetingMiniQuery() {
		return listMeetingMiniQuery;
	}
	public void setListMeetingMiniQuery(List<MeetingMiniQuery> listMeetingMiniQuery) {
		this.listMeetingMiniQuery = listMeetingMiniQuery;
	}
	public List<MeetingMemberListQuery> getListMeetingMemberListQueryMyCreate() {
		return listMeetingMemberListQueryMyCreate;
	}
	public void setListMeetingMemberListQueryMyCreate(
			List<MeetingMemberListQuery> listMeetingMemberListQueryMyCreate) {
		this.listMeetingMemberListQueryMyCreate = listMeetingMemberListQueryMyCreate;
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
}

