package com.ginkgocap.ywxt.service.meeting.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingCountDao;
import com.ginkgocap.ywxt.model.meeting.MeetingCount;
import com.ginkgocap.ywxt.service.meeting.MeetingCountService;
import com.ginkgocap.ywxt.utils.Utils;

@Service
@Transactional
public class MeetingCountServiceImpl implements MeetingCountService {
	@Autowired
	private MeetingCountDao meetingCountDao;
	/**
	 * 增加会议阅读数
	 * @param meetingId
	 */
	@Transactional(rollbackFor=Exception.class)
	public void addReadCount(Long meetingId) {
		getByMeetingId(meetingId);
		meetingCountDao.updateReadCount(meetingId);
	}
	/**
	 * 增加会议转发数
	 * @param meetingId
	 */
	@Transactional(rollbackFor=Exception.class)
	public void addShareCount(Long meetingId) {
		getByMeetingId(meetingId);
		meetingCountDao.updateShareCount(meetingId);
	}
	/**
	 * 增加会议收藏数
	 * @param meetingId
	 */
	@Transactional(rollbackFor=Exception.class)
	public void addCollectCount(Long meetingId) {
		getByMeetingId(meetingId);
		meetingCountDao.updateCollectCount(meetingId);
	}
	@Transactional(readOnly=true)
	private MeetingCount getByMeetingId(Long meetingId) {
		MeetingCount meetingCount = meetingCountDao.getByMeetingId(meetingId);
		if(Utils.isNullOrEmpty(meetingCount)) {
			meetingCount = new MeetingCount();
			meetingCount.setMeetingId(meetingId);
			meetingCountDao.insert(meetingCount);
		}
		return meetingCount;
	}
}