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

import com.ginkgocap.ywxt.dao.meeting.MeetingTimeDao;
import com.ginkgocap.ywxt.model.meeting.MeetingTime;
import com.ginkgocap.ywxt.service.meeting.MeetingTimeService;

@Service
@Transactional
public class MeetingTimeServiceImpl implements MeetingTimeService{
	@Autowired
	private MeetingTimeDao meetingTimeDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public MeetingTime getById(Long property) {
	return meetingTimeDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 meetingTimeDao.delete(property);
}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTime> getByMeetingId(java.lang.Long property) {
		return meetingTimeDao.getByMeetingId(property);
	}	
	/**
	 * 名称: getByStartTime
	 * 描述: 根据startTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTime> getByStartTime(java.util.Date property) {
		return meetingTimeDao.getByStartTime(property);
	}	
	/**
	 * 名称: getByEndTime
	 * 描述: 根据endTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTime> getByEndTime(java.util.Date property) {
		return meetingTimeDao.getByEndTime(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(MeetingTime entity){
	 meetingTimeDao.saveOrUpdate(entity);
}
}