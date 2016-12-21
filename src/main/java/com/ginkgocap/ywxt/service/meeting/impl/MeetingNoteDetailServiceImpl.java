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

import com.ginkgocap.ywxt.dao.meeting.MeetingNoteDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingNoteDetailDao;
import com.ginkgocap.ywxt.dao.meeting.TopicChatDao;
import com.ginkgocap.ywxt.model.meeting.MeetingNote;
import com.ginkgocap.ywxt.model.meeting.MeetingNoteDetail;
import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.service.meeting.MeetingNoteDetailService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoteQuery;
import com.sun.star.uno.Exception;

@Service
@Transactional
public class MeetingNoteDetailServiceImpl implements MeetingNoteDetailService{
	@Autowired
	private MeetingNoteDetailDao meetingNoteDetailDao;
	@Autowired
	private TopicChatDao topicChatDao;
	@Autowired
	private MeetingNoteDao meetingNoteDao;
/**
 * 名称: getById
 * 描述: 根据id查找
 * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public MeetingNoteDetail getById(Long property) {
	return meetingNoteDetailDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
 * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 meetingNoteDetailDao.delete(property);
}
	/**
	 * 名称: getByMeetingNoteId
	 * 描述: 根据meetingNoteId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNoteDetail> getByMeetingNoteId(java.lang.Long property) {
		return meetingNoteDetailDao.getByMeetingNoteId(property);
	}	
	/**
	 * 名称: getByMeetingNoteContent
	 * 描述: 根据meetingNoteContent查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNoteDetail> getByMeetingNoteContent(java.lang.String property) {
		return meetingNoteDetailDao.getByMeetingNoteContent(property);
	}	
	/**
	 * 名称: getByMeetingNoteTime
	 * 描述: 根据meetingNoteTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNoteDetail> getByMeetingNoteTime(java.util.Date property) {
		return meetingNoteDetailDao.getByMeetingNoteTime(property);
	}	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNoteDetail> getByTaskId(java.lang.String property) {
		return meetingNoteDetailDao.getByTaskId(property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingNoteDetail entity){
		 meetingNoteDetailDao.saveOrUpdate(entity);
	}
	@Transactional(rollbackFor=Exception.class)
	public void saveMeetingNoteDetail(String meetingId, String messageId,
		String taskId,User user) throws Exception {
		List<MeetingNoteQuery> meetingNoteQuery=meetingNoteDao.getByMeetingIdAndMemberId(Long.parseLong(meetingId), user.getId());
		if(meetingNoteQuery==null || meetingNoteQuery.size()==0){//没有会议笔记，则插入新的会议笔记
			MeetingNote meetingNote=new MeetingNote();
			meetingNote.setCreater(user.getId());
			meetingNote.setCreateTime(new Date());
			meetingNote.setMeetingId(Long.parseLong(meetingId));
			meetingNoteDao.saveOrUpdate(meetingNote);
		}
		List<MeetingNoteQuery> meetingNoteQueryNew=meetingNoteDao.getByMeetingIdAndMemberId(Long.parseLong(meetingId), user.getId());
		List<TopicChat> topicChats=topicChatDao.getByMessageId(messageId);
		Long topicChatId=null;
		if(topicChats!=null&&topicChats.size()>0){
			topicChatId=topicChats.get(0).getId();
		} else {
			throw new Exception("聊天记录不存在");
		}
		MeetingNoteDetail mnd=new MeetingNoteDetail();
		mnd.setMeetingChatId(topicChatId);
		if(meetingNoteQueryNew!=null && meetingNoteQuery.size()>0) {
			mnd.setMeetingNoteId(meetingNoteQueryNew.get(0).getId());
		}
		mnd.setMeetingNoteTime(new Date());
		mnd.setTaskId(taskId);
		meetingNoteDetailDao.saveOrUpdate(mnd);
	}
}