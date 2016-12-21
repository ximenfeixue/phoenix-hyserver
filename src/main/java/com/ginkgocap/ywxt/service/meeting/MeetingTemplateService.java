package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingTemplate;

public interface MeetingTemplateService {
	/**
	 * 根据ID查询模板
	 * @param id
	 * @return
	 */
	public MeetingTemplate getById(Long id);
	/**
	 * 根据ID删除模板
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 删除用户的全部模板
	 * @param userId
	 */
	public void deleteByUserId(Long userId);
	/**
	 * 根据用户ID查找模板
	 * @param userId
	 * @return
	 */
	public List<MeetingTemplate> getByUserId(Long userId);
	/**
	 * 修改或者新增
	 * @param meetingTemplate
	 */
	public void saveOrUpdate(MeetingTemplate meetingTemplate);
}