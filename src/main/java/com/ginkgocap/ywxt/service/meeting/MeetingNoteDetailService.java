/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingNoteDetail;
import com.ginkgocap.ywxt.user.model.User;
import com.sun.star.uno.Exception;

public interface MeetingNoteDetailService {
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
	public List<MeetingNoteDetail> getByMeetingNoteId(java.lang.Long property);	
	/**
	 * 名称: getByMeetingNoteContent
	 * 描述: 根据meetingNoteContent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByMeetingNoteContent(java.lang.String property);	
	/**
	 * 名称: getByMeetingNoteTime
	 * 描述: 根据meetingNoteTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByMeetingNoteTime(java.util.Date property);	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoteDetail> getByTaskId(java.lang.String property);	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
public void saveOrUpdate(MeetingNoteDetail entity) ;
/**
 * 生成会议笔记明细
 * @param meetingId 会议id
 * @param messageId 消息id串，客户端随机生成，每条记录唯一
 * @param taskId
 * @param user 当前用户
 * @author wangfeiliang
 */
public void saveMeetingNoteDetail(String meetingId,String messageId,String taskId,User user)throws Exception;

}