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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.model.meeting.MeetingNoteDetail;


public class MeetingNoteQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 笔记序号 */
	private Long id;
	/** 会议序号 */
	private Long meetingId;
	//会议名称
	private String meetingName;
	/** 笔记标题 */
	private String meetingNoteTitle;
	/** 所属人 */
	private Long creater;
	/** 会议笔记详细 **/
	private List<MeetingNoteDetail> listMeetingNoteDetail;
	/** 会议笔记详细 包含附件**/
	private List<MeetingNoteDetailQuery> listMeetingNoteDetailQuery;
	/** 笔记创建时间 */
	private Date createTime;

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
	
	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getMeetingNoteTitle() {
		return this.meetingNoteTitle;
	}
	
	public void setMeetingNoteTitle(String value) {
		this.meetingNoteTitle = value;
	}
	
	public Long getCreater() {
		return this.creater;
	}
	
	public void setCreater(Long value) {
		this.creater = value;
	}
	
	public List<MeetingNoteDetail> getListMeetingNoteDetail() {
		return listMeetingNoteDetail;
	}

	public void setListMeetingNoteDetail(
			List<MeetingNoteDetail> listMeetingNoteDetail) {
		this.listMeetingNoteDetail = listMeetingNoteDetail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public List<MeetingNoteDetailQuery> getListMeetingNoteDetailQuery() {
		return listMeetingNoteDetailQuery;
	}

	public void setListMeetingNoteDetailQuery(
			List<MeetingNoteDetailQuery> listMeetingNoteDetailQuery) {
		this.listMeetingNoteDetailQuery = listMeetingNoteDetailQuery;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

