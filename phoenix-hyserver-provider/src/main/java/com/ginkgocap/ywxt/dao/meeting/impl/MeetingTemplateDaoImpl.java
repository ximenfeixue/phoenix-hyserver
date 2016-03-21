package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingTemplateDao;
import com.ginkgocap.ywxt.model.meeting.MeetingTemplate;
@Repository
public class MeetingTemplateDaoImpl extends SqlSessionDaoSupport implements MeetingTemplateDao, ApplicationContextAware{
	@Autowired
	private ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	/**
	 * 根据ID查询模板
	 * @param id
	 * @return
	 */
	public MeetingTemplate getById(Long id) {
		return (MeetingTemplate)getSqlSession().selectOne("MeetingTemplate.getById", id);
	}
	/**
	 * 根据ID删除模板
	 * @param id
	 */
	public void delete(Long id) {
		getSqlSession().update("MeetingTemplate.delete", id);
	}
	/**
	 * 删除用户的全部模板
	 * @param userId 用户ID
	 */
	public void deleteByUserId(Long userId) {
		getSqlSession().update("MeetingTemplate.deleteByUserId", userId);
	}
	/**
	 * 根据用户ID查找模板
	 * @param userId
	 * @return
	 */
	public List<MeetingTemplate> getByUserId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingTemplate.getByUserId",property);
	}	
	/**
	 * 修改或者新增
	 * @param meetingTemplate
	 */
	public void saveOrUpdate(MeetingTemplate meetingTemplate) {
		if(meetingTemplate.getId() == null) {
			getSqlSession().insert("MeetingTemplate.insert", meetingTemplate);
		} else {
			getSqlSession().update("MeetingTemplate.update", meetingTemplate);
		}
	}
}