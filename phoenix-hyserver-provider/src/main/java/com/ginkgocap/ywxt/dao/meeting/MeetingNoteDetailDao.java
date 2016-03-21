/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

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

@Repository
public interface MeetingNoteDetailDao{
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingNoteDetail getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: getByMeetingNoteId
	 * 描述: 根据meetingNoteId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByMeetingNoteId(java.lang.Long property) ;	
	/**
	 * 名称: getByMeetingNoteId
	 * 描述: 根据meetingNoteId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetailQuery> getQueryByMeetingNoteId(java.lang.Long property) ;	
	/**
	 * 名称: getByMeetingNoteContent
	 * 描述: 根据meetingNoteContent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByMeetingNoteContent(java.lang.String property) ;	
	/**
	 * 名称: getByMeetingNoteTime
	 * 描述: 根据meetingNoteTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByMeetingNoteTime(java.util.Date property) ;	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByTaskId(java.lang.String property) ;	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingNoteDetail entity) ;
	/**
	 * 名称: deleteBatchOther
	 * 描述: 批量删除其他的数据
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteBatchOther(List<Long> list,Long meetingNoteId);
}
