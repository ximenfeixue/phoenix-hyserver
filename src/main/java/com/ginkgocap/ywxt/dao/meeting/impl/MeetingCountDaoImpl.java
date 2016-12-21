package com.ginkgocap.ywxt.dao.meeting.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingCountDao;
import com.ginkgocap.ywxt.model.meeting.MeetingCount;
@Repository
public class MeetingCountDaoImpl extends SqlSessionDaoSupport implements MeetingCountDao,ApplicationContextAware{
	@Autowired
	private ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	/**
	 * 查询会议点击、分享、收藏、评价次数
	 * @param meetingId
	 * @return
	 */
	public MeetingCount getByMeetingId(Long meetingId) {
		return (MeetingCount)getSqlSession().selectOne("MeetingCount.getByMeetingId", meetingId);
	}
	/**
	 * 插入会议点击、分享、收藏、评价次数
	 * @param meetingCount
	 */
	public void insert(MeetingCount meetingCount) {
		getSqlSession().insert("MeetingCount.insert", meetingCount);
	}
	/**
	 * 增加会议点击次数
	 * @param meetingId
	 */
	public void updateReadCount(Long meetingId) {
		getSqlSession().update("MeetingCount.updateReadCount", meetingId);
	}
	/**
	 * 增加会议分享次数
	 * @param meetingId
	 */
	public void updateShareCount(Long meetingId) {
		getSqlSession().update("MeetingCount.updateShareCount", meetingId);
	}
	/**
	 * 增加会议收藏次数
	 * @param meetingId
	 */
	public void updateCollectCount(Long meetingId) {
		getSqlSession().update("MeetingCount.updateCollectCount", meetingId);
	}
}