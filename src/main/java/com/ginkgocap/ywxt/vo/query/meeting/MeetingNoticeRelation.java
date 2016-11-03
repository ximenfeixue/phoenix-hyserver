/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;


import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;
public class MeetingNoticeRelation implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
	/** 通知内容 */
	private String noticeContent;
	/** 会议ID */
	private Long meetingId;
	//会议图片
	private String meetingPic;
	/** 会议ID */
	private Long memberId;
	/** 会议名字 */
	private String meetingName;
	/** 创建时间 */
	private Date createTime;
	/** 最后修改时间 */
	private Date updateTime;
	/** 通知明细  */
	private TreeSet<MeetingNoticeQuery> setMeetingNoticeQuery;
	/** 创建者名字 */
	private Long meetingCreateId;
	/** 创建者名字 */
	private String meetingCreateName;
	/**
	 * 群组id
	 */
	private String groupId;
	
	public Long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
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
	public TreeSet<MeetingNoticeQuery> getSetMeetingNoticeQuery() {
		return setMeetingNoticeQuery;
	}
	public void setSetMeetingNoticeQuery(
			TreeSet<MeetingNoticeQuery> setMeetingNoticeQuery) {
		this.setMeetingNoticeQuery = setMeetingNoticeQuery;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeContent() {
		return this.noticeContent;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getMeetingCreateId() {
		return meetingCreateId;
	}
	public void setMeetingCreateId(Long meetingCreateId) {
		this.meetingCreateId = meetingCreateId;
	}
	public String getMeetingCreateName() {
		return meetingCreateName;
	}
	public void setMeetingCreateName(String meetingCreateName) {
		this.meetingCreateName = meetingCreateName;
	}
	public String getMeetingPic() {
		return meetingPic;
	}
	public void setMeetingPic(String meetingPic) {
		this.meetingPic = meetingPic;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}