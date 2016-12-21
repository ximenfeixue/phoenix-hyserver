/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingPeopleDao;
import com.ginkgocap.ywxt.model.meeting.MeetingPeople;
import com.ginkgocap.ywxt.service.meeting.MeetingPeopleService;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingPeopleQuery;

@Service
@Transactional
public class MeetingPeopleServiceImpl implements MeetingPeopleService{
	@Autowired
	private MeetingPeopleDao meetingPeopleDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public MeetingPeople getById(Long property) {
	return meetingPeopleDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 meetingPeopleDao.delete(property);
}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPeople> getByMeetingId(java.lang.Long property) {
		return meetingPeopleDao.getByMeetingId(property);
	}
	/**
	 * 根据会议ID列表获取相关人脉
	 * @param meetingIdList
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MeetingPeople> getByMeetingIdList(List<Long> meetingIdList) {
		return meetingPeopleDao.getByMeetingIdList(meetingIdList);
	}
	/**
	 * 名称: getByPeopleId
	 * 描述: 根据peopleId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPeople> getByPeopleId(java.lang.Long property) {
		return meetingPeopleDao.getByPeopleId(property);
	}	
	/**
	 * 名称: getByPeopleName
	 * 描述: 根据peopleName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPeople> getByPeopleName(java.lang.String property) {
		return meetingPeopleDao.getByPeopleName(property);
	}	
	/**
	 * 名称: getByPeoplePhoto
	 * 描述: 根据peoplePhoto查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPeople> getByPeoplePhoto(java.lang.String property) {
		return meetingPeopleDao.getByPeoplePhoto(property);
	}	
	/**
	 * 名称: getByPeopleDesc
	 * 描述: 根据peopleDesc查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPeople> getByPeopleDesc(java.lang.String property) {
		return meetingPeopleDao.getByPeopleDesc(property);
	}	
	/**
	 * 名称: getByIsShare
	 * 描述: 根据isShare查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPeople> getByIsShare(java.lang.Boolean property) {
		return meetingPeopleDao.getByIsShare(property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingPeople entity){
		 meetingPeopleDao.saveOrUpdate(entity);
	}
	/**
	 * 名称: getMeetingPeople
	 * 描述: 获取我的会议人脉
	 * @since  2014-09-19
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPeopleQuery> getMeetingPeople(Date beginDate,Date endDate,Long memberId){
		return meetingPeopleDao.getMeetingPeople( beginDate, endDate, memberId);
	}
}