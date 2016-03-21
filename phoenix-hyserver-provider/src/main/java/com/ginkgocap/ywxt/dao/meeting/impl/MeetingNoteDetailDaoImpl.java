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
public class MeetingNoteDetailDaoImpl extends SqlSessionDaoSupport implements MeetingNoteDetailDao,ApplicationContextAware{
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
	public MeetingNoteDetail getById(Long property) {
		return (MeetingNoteDetail)getSqlSession().selectOne("MeetingNoteDetail.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("MeetingNoteDetail.delete",property);
	}
	
	/**
	 * 名称: getByMeetingNoteId
	 * 描述: 根据meetingNoteId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByMeetingNoteId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingNoteDetail.getByMeetingNoteId",property);
	}	
	/**
	 * 名称: getByMeetingNoteId
	 * 描述: 根据meetingNoteId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetailQuery> getQueryByMeetingNoteId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingNoteDetail.getQueryByMeetingNoteId",property);
	}	
	
	/**
	 * 名称: getByMeetingNoteContent
	 * 描述: 根据meetingNoteContent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByMeetingNoteContent(java.lang.String property) {
		return getSqlSession().selectList("MeetingNoteDetail.getByMeetingNoteContent",property);
	}	
	
	/**
	 * 名称: getByMeetingNoteTime
	 * 描述: 根据meetingNoteTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByMeetingNoteTime(java.util.Date property) {
		return getSqlSession().selectList("MeetingNoteDetail.getByMeetingNoteTime",property);
	}	
	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByTaskId(java.lang.String property) {
		return getSqlSession().selectList("MeetingNoteDetail.getByTaskId",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingNoteDetail entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("MeetingNoteDetail.insert", entity);
		else 
			getSqlSession().update("MeetingNoteDetail.update", entity);
	}
	/**
	 * 名称: deleteBatchOther
	 * 描述: 批量删除其他的数据
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteBatchOther(List<Long> list,Long meetingNoteId){
		final Map<String,Object> map=new HashMap<String, Object>();
		if(Utils.isNullOrEmpty(list)){
			map.put("meetingNoteId", meetingNoteId);
			getSqlSession().delete("MeetingNoteDetail.batchRemoveAllByNoteId",map);
		}else{
			map.put("list", list);
			map.put("meetingNoteId", meetingNoteId);	
			getSqlSession().delete("MeetingNoteDetail.batchRemoveUserByPks",map);
		}
		
	}
}
