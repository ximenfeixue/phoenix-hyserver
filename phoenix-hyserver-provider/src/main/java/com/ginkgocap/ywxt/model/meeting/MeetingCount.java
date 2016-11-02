package com.ginkgocap.ywxt.model.meeting;

import com.ginkgocap.ywxt.common.base.BaseEntity;


public class MeetingCount extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	//记录ID
	private long id;
	//会议ID
	private long meetingId;
	//被阅读次数
	private long readCount = 0;
	//被分享次数
	private long shareCount = 0;
	//被收藏次数
	private long collectCount = 0;
	//被评价次数
	private long evaluateCount = 0;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(long meetingId) {
		this.meetingId = meetingId;
	}
	public long getReadCount() {
		return readCount;
	}
	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}
	public long getShareCount() {
		return shareCount;
	}
	public void setShareCount(long shareCount) {
		this.shareCount = shareCount;
	}
	public long getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(long collectCount) {
		this.collectCount = collectCount;
	}
	public long getEvaluateCount() {
		return evaluateCount;
	}
	public void setEvaluateCount(long evaluateCount) {
		this.evaluateCount = evaluateCount;
	}
}