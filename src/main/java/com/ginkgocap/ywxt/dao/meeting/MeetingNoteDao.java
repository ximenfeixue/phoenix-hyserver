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

import com.ginkgocap.ywxt.model.meeting.MeetingNote;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoteQuery;

@Repository
public interface MeetingNoteDao{
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingNote getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByMeetingId(java.lang.Long property);
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
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByMeetingNoteTitle(java.lang.String property) ;	
	/**
	 * 名称: getByMeetingChatId
	 * 描述: 根据meetingChatId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByMeetingChatId(java.lang.Long property) ;	
	/**
	 * 名称: getByCareater
	 * 描述: 根据careater查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByCareater(java.lang.Long property) ;	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNote> getByCreateTime(java.util.Date property) ;	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingNote entity) ;
	/**
	 * 名称: save
	 * 描述: 新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public Integer save(MeetingNote entity) ;
	/**
	 * 名称: getMyMeetingNote
	 * 描述: 获取我的会议-会议笔记
	 * @since  2014-09-19
	 * @author qingc
	 */
	public List<MeetingNoteQuery> getMyMeetingNote(Date beginDate,Date endDate, Long memberId);
	/**
	 * 名称: getByMeetingIdAndMemberId
	 * 描述:根据member和meetingid 获取会议笔记
	 * @since  2014-10-02
	 * @author qingc
	 */
	 public List<MeetingNoteQuery> getByMeetingIdAndMemberId(Long meetingId,Long memberId);
}
