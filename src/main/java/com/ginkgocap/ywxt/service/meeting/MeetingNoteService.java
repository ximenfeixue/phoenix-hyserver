/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingNote;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoteQuery;

public interface MeetingNoteService {
	/**
	 * 名称: getById 
	 * 描述: 根据id查找
	 * @since 2014-09-14
	 * @author qingc
	 */
	public MeetingNote getById(Long property);

	/**
	 * 名称: delete 
	 * 描述: 根据id删除
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void delete(Long property);

	/**
	 * 名称: getByMeetingId 
	 * 描述: 根据meetingId查找
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByMeetingId(Long property);
	/**
	 * 批量查询会议相关笔记
	 * @param meetingIdList
	 * @param userId
	 * @return
	 */
	public List<MeetingNote> getByMeetingIdList(List<Long> meetingIdList, String userId);
	/**
	 * 名称: getByMeetingNoteTitle 
	 * 描述: 根据meetingNoteTitle查找
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByMeetingNoteTitle(String property);

	/**
	 * 名称: getByMeetingChatId 描述: 根据meetingChatId查找
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByMeetingChatId(Long property);

	/**
	 * 名称: getByCareater 
	 * 描述: 根据careater查找
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByCareater(Long property);

	/**
	 * 名称: getByCreateTime 描述: 根据createTime查找
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByCreateTime(Date property);
	/**
	 * 名称: saveOrUpdate 
	 * 描述: 修改或者新增
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingNote entity);
	/**
	 * 名称: saveNote 
	 * 描述: 修改或者新增
	 * @since 2014-09-18
	 * @author qingc
	 */
	public void saveNote(MeetingNoteQuery entity)throws IllegalAccessException, InvocationTargetException;
	/**
	 * 名称: getMyMeetingNote
	 * 描述: 获取我的会议-会议笔记
	 * @since  2014-09-19
	 * @author qingc
	 */
	public List<MeetingNoteQuery> getMyMeetingNote(Date beginDate,Date endDate,Long memberId);
	/**
	 * 名称: getNoteAndDetailtById
	 * 描述: 根据笔记id获取会议笔记及详细
	 * @since  2014-09-19
	 * @author qingc
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	public MeetingNoteQuery getNoteAndDetailtById(Long noteId) throws IllegalAccessException, InvocationTargetException, Exception;
	
	/**
	 * 名称: getNoteAndDetailtByMeetingIdAndCreater
	 * 描述: 根据会议id和用户id获取会议笔记及详细
	 * @since  2014-09-19
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingNoteQuery> getNoteAndDetailtByMeetingIdAndCreater(Long meetingId,Long userId) throws  Exception;
	/**
	 * 名称: getByMeetingIdAndMemberId
	 * 描述:根据member和meetingid 获取会议笔记
	 * @since  2014-10-02
	 * @author qingc
	 */
	public List<MeetingNoteQuery>  getByMeetingIdAndMemberId(Long meetingId,Long memberId);	 
}