/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.*;

import javacommon.base.*;


import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;


import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.mybatis.spring.support.SqlSessionDaoSupport;
@Repository
public class MeetingTimeDaoImpl extends SqlSessionDaoSupport implements MeetingTimeDao,ApplicationContextAware{
	private ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
		
	}
	
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingTime getById(Long property) {
		return (MeetingTime)getSqlSession().selectOne("MeetingTime.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("MeetingTime.delete",property);
	}
	/**
	 * 名称: deleteByMeetingId
	 * 描述: 根据会议id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByMeetingId(Long property) {
		getSqlSession().delete("MeetingTime.deleteByMeetingId",property);
	}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingTime> getByMeetingId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingTime.getByMeetingId",property);
	}	
	
	/**
	 * 名称: getByStartTime
	 * 描述: 根据startTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingTime> getByStartTime(java.util.Date property) {
		return getSqlSession().selectList("MeetingTime.getByStartTime",property);
	}	
	
	/**
	 * 名称: getByEndTime
	 * 描述: 根据endTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingTime> getByEndTime(java.util.Date property) {
		return getSqlSession().selectList("MeetingTime.getByEndTime",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingTime entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("MeetingTime.insert", entity);
		else 
			getSqlSession().update("MeetingTime.update", entity);
	}
}
