package com.ginkgocap.ywxt.dao.meeting;

import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.model.meeting.MeetingCount;

@Repository
public interface MeetingCountDao {
	/**
	 * 查询会议点击、分享、收藏、评价次数
	 * @param meetingId
	 * @return
	 */
	public MeetingCount getByMeetingId(Long meetingId);
	/**
	 * 插入会议点击、分享、收藏、评价次数
	 * @param meetingCount
	 */
	public void insert(MeetingCount meetingCount);
	/**
	 * 增加会议点击次数
	 * @param meetingId
	 */
	public void updateReadCount(Long meetingId);
	/**
	 * 增加会议分享次数
	 * @param meetingId
	 */
	public void updateShareCount(Long meetingId);
	/**
	 * 增加会议收藏次数
	 * @param meetingId
	 */
	public void updateCollectCount(Long meetingId);
}
