package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingTemplateDao;
import com.ginkgocap.ywxt.model.meeting.MeetingTemplate;
import com.ginkgocap.ywxt.service.meeting.MeetingTemplateService;

@Service
@Transactional
public class MeetingTemplateServiceImpl implements MeetingTemplateService {
	@Autowired
	private MeetingTemplateDao meetingTemplateDao;
	/**
	 * 根据ID查询模板
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public MeetingTemplate getById(Long id) {
		return meetingTemplateDao.getById(id);
	}
	/**
	 * 根据用户ID查找模板
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MeetingTemplate> getByUserId(Long userId) {
		return meetingTemplateDao.getByUserId(userId);
	}
	/**
	 * 根据ID删除模板
	 * @param id
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long id){
		meetingTemplateDao.delete(id);
	}
	/**
	 * 删除用户的全部模板
	 * @param userId
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteByUserId(Long userId){
		meetingTemplateDao.deleteByUserId(userId);
	}
	/**
	 * 修改或者新增
	 * @param meetingTemplate
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingTemplate meetingTemplate){
		meetingTemplateDao.saveOrUpdate(meetingTemplate);
	}
}