/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.model.meeting.MeetingPeople;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingPeopleQuery;

@Repository
public interface MeetingPeopleDao{
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingPeople getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: deleteByMeetingId
	 * 描述: 根据会议id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByMeetingId(Long property) ;
	
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPeople> getByMeetingId(java.lang.Long property) ;
	/**
	 * 根据会议ID列表获取相关人脉
	 * @param meetingIdList
	 * @return
	 */
	public List<MeetingPeople> getByMeetingIdList(List<Long> meetingIdList);
	/**
	 * 名称: getByPeopleId
	 * 描述: 根据peopleId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPeople> getByPeopleId(java.lang.Long property) ;	
	/**
	 * 名称: getByPeopleName
	 * 描述: 根据peopleName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPeople> getByPeopleName(java.lang.String property) ;	
	/**
	 * 名称: getByPeoplePhoto
	 * 描述: 根据peoplePhoto查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPeople> getByPeoplePhoto(java.lang.String property) ;	
	/**
	 * 名称: getByPeopleDesc
	 * 描述: 根据peopleDesc查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPeople> getByPeopleDesc(java.lang.String property) ;	
	/**
	 * 名称: getByIsShare
	 * 描述: 根据isShare查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPeople> getByIsShare(java.lang.Boolean property) ;	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingPeople entity) ;
	/**
	 * 名称: getMeetingPeople
	 * 描述: 获取我的会议人脉
	 * @since  2014-09-19
	 * @author qingc
	 */
	public List<MeetingPeopleQuery> getMeetingPeople(Date beginDate,Date endDate,Long memberId);

}
