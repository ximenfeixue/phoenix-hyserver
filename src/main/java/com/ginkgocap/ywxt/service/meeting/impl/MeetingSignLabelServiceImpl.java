/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingSignLabelDao;
import com.ginkgocap.ywxt.model.meeting.MeetingSignLabel;
import com.ginkgocap.ywxt.service.meeting.MeetingSignLabelService;

@Service
@Transactional
public class MeetingSignLabelServiceImpl implements MeetingSignLabelService{
	@Autowired
	private MeetingSignLabelDao meetingSignLabelDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public MeetingSignLabel getById(Long property) {
	return meetingSignLabelDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 meetingSignLabelDao.delete(property);
}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabel> getByMeetingId(java.lang.Long property) {
		return meetingSignLabelDao.getByMeetingId(property);
	}	
	/**
	 * 名称: getByLabelName
	 * 描述: 根据labelName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabel> getByLabelName(java.lang.String property) {
		return meetingSignLabelDao.getByLabelName(property);
	}	
	/**
	 * 名称: getByIsCustom
	 * 描述: 根据isCustom查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabel> getByIsCustom(Integer property) {
		return meetingSignLabelDao.getByIsCustom(property);
	}	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabel> getByCreateId(java.lang.Long property) {
		return meetingSignLabelDao.getByCreateId(property);
	}	
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabel> getByCreateName(java.lang.String property) {
		return meetingSignLabelDao.getByCreateName(property);
	}	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabel> getByCreateTime(java.util.Date property) {
		return meetingSignLabelDao.getByCreateTime(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(MeetingSignLabel entity){
	 meetingSignLabelDao.saveOrUpdate(entity);
}
}