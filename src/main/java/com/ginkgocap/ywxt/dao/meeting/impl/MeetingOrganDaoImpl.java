package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingOrganDao;
import com.ginkgocap.ywxt.model.meeting.MeetingOrgan;
@Repository
public class MeetingOrganDaoImpl extends SqlSessionDaoSupport implements MeetingOrganDao,ApplicationContextAware{
	private static final String SQLMAP_NAMESPACE = "MeetingOrgan";
	@Autowired
	private ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
		
	}
	/**
	 * 根据id查找
	 * @param property ID
	 * @return 会议组织
	 */
	public MeetingOrgan getById(Long property) {
		return (MeetingOrgan)getSqlSession().selectOne(SQLMAP_NAMESPACE+".getById",property);
	}
	/**
	 * 根据会议ID查找会议关联的组织
	 * @param meetingId
	 * @return 会议关联的组织列表
	 */
	public List<MeetingOrgan> getByMeetingId(Long meetingId) {
		return getSqlSession().selectList(SQLMAP_NAMESPACE+".getByMeetingId", meetingId);
	}
	/**
	 * 批量查询会议相关组织
	 * @param meetingIdList
	 * @return
	 */
	public List<MeetingOrgan> getByMeetingIdList(List<Long> meetingIdList) {
		return getSqlSession().selectList(SQLMAP_NAMESPACE+".getByMeetingIdList", meetingIdList);
	}
	/**
	 * 根据ID删除
	 * @param property 会议组织ID
	 */
	public void delete(Long property) {
		getSqlSession().delete(SQLMAP_NAMESPACE+".delete", property);
	}
	/**
	 * 根据ID批量删除
	 * @param ids 会议组织ID列表
	 */
	public void delete(List<Long> ids) {
		getSqlSession().delete(SQLMAP_NAMESPACE+".deleteBatch", ids);
	}
	/**
	 * 根据会议ID删除
	 * @param meetingId 会议ID
	 */
	public void deleteByMeetingId(Long meetingId) {
		getSqlSession().delete(SQLMAP_NAMESPACE+".deleteByMeetingId", meetingId);
	}
	/**
	 * 新增会议关联的组织
	 * @param meetingOrgan
	 */
	public void saveOrUpdate(MeetingOrgan meetingOrgan) {
		if(meetingOrgan.getId() == null) 
			getSqlSession().insert(SQLMAP_NAMESPACE+".insert", meetingOrgan);
		else 
			getSqlSession().update(SQLMAP_NAMESPACE+".update", meetingOrgan);
	}
	/**
	 * 批量新增会议关联的组织
	 * @param meetingOrgan
	 */
	public void save(MeetingOrgan meetingOrgan) {
		getSqlSession().insert(SQLMAP_NAMESPACE+".insert", meetingOrgan);
	}
}
