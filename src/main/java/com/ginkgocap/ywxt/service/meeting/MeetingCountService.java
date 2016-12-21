package com.ginkgocap.ywxt.service.meeting;


public interface MeetingCountService {
	/**
	 * 增加会议阅读数
	 * @param meetingId
	 */
	public void addReadCount(Long meetingId);
	/**
	 * 增加会议转发数
	 * @param meetingId
	 */
	public void addShareCount(Long meetingId);
	/**
	 * 增加会议收藏数
	 * @param meetingId
	 */
	public void addCollectCount(Long meetingId);
}