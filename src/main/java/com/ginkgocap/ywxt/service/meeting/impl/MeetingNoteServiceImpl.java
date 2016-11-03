/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingNoteDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingNoteDetailDao;
import com.ginkgocap.ywxt.dao.meeting.TopicChatDao;
import com.ginkgocap.ywxt.dao.meeting.UserDao;
import com.ginkgocap.ywxt.file.model.FileIndex;
import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.model.meeting.MeetingNote;
import com.ginkgocap.ywxt.model.meeting.MeetingNoteDetail;
import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.model.mobile.JTFile;
import com.ginkgocap.ywxt.service.meeting.MeetingNoteService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.JsonToBean;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoteDetailQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoteQuery;

@Service
@Transactional
public class MeetingNoteServiceImpl implements MeetingNoteService{
	@Autowired
	private MeetingNoteDao meetingNoteDao;
	@Autowired
	private MeetingNoteDetailDao meetingNoteDetailDao;
	@Autowired
	private TopicChatDao topicChatDao;
	@Autowired
	private FileIndexService fileIndexService;
	@Autowired
	private UserDao userDao;
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public MeetingNote getById(Long property) {
		return meetingNoteDao.getById(property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long property){
		 meetingNoteDao.delete(property);
	}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNote> getByMeetingId(java.lang.Long property) {
		return meetingNoteDao.getByMeetingId(property);
	}
	/**
	 * 批量查询会议相关笔记
	 * @param meetingIdList
	 * @param userId
	 * @return
	 */
	public List<MeetingNote> getByMeetingIdList(List<Long> meetingIdList, String userId) {
		return meetingNoteDao.getByMeetingIdList(meetingIdList, userId);
	}
	/**
	 * 名称: getByMeetingNoteTitle
	 * 描述: 根据meetingNoteTitle查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNote> getByMeetingNoteTitle(java.lang.String property) {
		return meetingNoteDao.getByMeetingNoteTitle(property);
	}	
	/**
	 * 名称: getByMeetingChatId
	 * 描述: 根据meetingChatId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNote> getByMeetingChatId(java.lang.Long property) {
		return meetingNoteDao.getByMeetingChatId(property);
	}	
	/**
	 * 名称: getByCareater
	 * 描述: 根据careater查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNote> getByCareater(java.lang.Long property) {
		return meetingNoteDao.getByCareater(property);
	}	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNote> getByCreateTime(java.util.Date property) {
		return meetingNoteDao.getByCreateTime(property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingNote entity){
		 meetingNoteDao.saveOrUpdate(entity);
	}
	/**
	 * 名称: saveNote 
	 * 描述: 修改或者新增
	 * @since 2014-09-18
	 * @author qingc
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveNote(MeetingNoteQuery entity) throws IllegalAccessException, InvocationTargetException{
		MeetingNote meetingNote=new MeetingNote();
		Utils.setPrimarykeyZoroToNull(entity);
		if(Utils.isNullOrEmpty(entity.getCreateTime())){
			entity.setCreateTime(new Date());
		}
		BeanUtils.copyProperties(meetingNote, entity);
		Utils.setPrimarykeyZoroToNull(meetingNote);
		// 保存 会议笔记
		meetingNoteDao.saveOrUpdate(meetingNote);
		List<MeetingNoteDetail> list=entity.getListMeetingNoteDetail();
		List<Long> listNoteDetailId=new ArrayList<Long>();
		if(!Utils.isNullOrEmpty(list)){
			for(MeetingNoteDetail meetingNoteDetail:list){
				if(!Utils.isNullOrEmpty(meetingNoteDetail)){
					Utils.setPrimarykeyZoroToNull(meetingNoteDetail);
					meetingNoteDetail.setMeetingNoteId(meetingNote.getId());
					meetingNoteDetail.setMeetingNoteTime(new Date());
					meetingNoteDetailDao.saveOrUpdate(meetingNoteDetail);
					listNoteDetailId.add(meetingNoteDetail.getId());
				}
			}
		}
		// 删除已经移除的会议笔记明细
		if(!Utils.isNullOrEmpty(meetingNote)&&!Utils.isNullOrEmpty(meetingNote.getId())){
			meetingNoteDetailDao.deleteBatchOther(listNoteDetailId,meetingNote.getId());
		}
	}
	/**
	 * 名称: getMyMeetingNote
	 * 描述: 获取我的会议-会议笔记
	 * @since  2014-09-19
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNoteQuery> getMyMeetingNote(Date beginDate,Date endDate,Long memberId){
		return meetingNoteDao.getMyMeetingNote(beginDate,endDate,memberId);
	}
	/**
	 * 名称: getNoteAndDetailtById
	 * 描述: 根据笔记id获取会议笔记及详细
	 * @since  2014-09-19
	 * @author qingc
	 * @throws Exception 
	 */
	@Transactional(readOnly=true)
	public MeetingNoteQuery getNoteAndDetailtById(Long noteId) throws Exception{
		MeetingNoteQuery meetingNoteQuery=new MeetingNoteQuery();
		MeetingNote meetingNote=this.getById(noteId);
		if(!Utils.isNullOrEmpty(meetingNote)){
			boolean flag=false;
			if(Utils.isNullOrEmpty(meetingNote.getCreateTime())){
				meetingNote.setCreateTime(new Date());
				flag=true;
			}
			org.apache.commons.beanutils.BeanUtils.copyProperties(meetingNoteQuery, meetingNote);
			if(flag){
				meetingNoteQuery.setCreateTime(null);
			}
			List<MeetingNoteDetail> list=meetingNoteDetailDao.getByMeetingNoteId(noteId);
			if(!Utils.isNullOrEmpty(list)){
				meetingNoteQuery.setListMeetingNoteDetail(list);
			}
			return meetingNoteQuery;
		}else{
			throw new Exception("会议笔记不存在");
		}
	}
	
	/**
	 * 名称: getNoteAndDetailtByMeetingIdAndCreater
	 * 描述: 根据会议id和用户id获取会议笔记及详细
	 * @since  2014-09-19
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingNoteQuery> getNoteAndDetailtByMeetingIdAndCreater(Long meetingId,Long userId) throws  Exception{
		List<MeetingNoteQuery> listMeetingNoteQuery = meetingNoteDao.getByMeetingIdAndMemberId(meetingId,userId);
		if(!Utils.isNullOrEmpty(listMeetingNoteQuery)) {
			for(MeetingNoteQuery meetingNoteQuery : listMeetingNoteQuery) {
				if(!Utils.isNullOrEmpty(meetingNoteQuery)){
					List<MeetingNoteDetail> listMeetingNoteDetail = meetingNoteDetailDao.getByMeetingNoteId(meetingNoteQuery.getId());
					meetingNoteQuery.setListMeetingNoteDetail(listMeetingNoteDetail);
					List<MeetingNoteDetailQuery> list=meetingNoteDetailDao.getQueryByMeetingNoteId(meetingNoteQuery.getId());
					List<MeetingNoteDetailQuery> listResult=new ArrayList<MeetingNoteDetailQuery>();
					if(!Utils.isNullOrEmpty(list)){
						for(MeetingNoteDetailQuery meetingNoteDetailQuery:list){
							// 封装会议笔记明细
							if(!Utils.isNullOrEmpty(meetingNoteDetailQuery)){
								// 如果引用聊天记录不为空
								if(!Utils.isNullOrEmpty(meetingNoteDetailQuery.getMeetingChatId())){
									TopicChat topicChat =topicChatDao.getById(meetingNoteDetailQuery.getMeetingChatId());
									// 如果引用聊天记录不为空，封装聊天记录
									if(!Utils.isNullOrEmpty(topicChat)){
										if(!Utils.isNullOrEmpty(topicChat.getMemberId())){
											// 获取发送人名字
											List<Long> listUserIds = new ArrayList<Long>();
											listUserIds.add(topicChat.getMemberId());
											List<User> listUser = userDao.getByIds(listUserIds);
											if(listUser !=null && listUser.size()>0){
												for (User user : listUser) {
													if(!Utils.isNullOrEmpty(user)&&!Utils.isNullOrEmpty(user.getName())){
														topicChat.setMemberName(user.getName());
														break;
													}
												}
											}
										}
										meetingNoteDetailQuery.setTopicChat(topicChat);
									}
								}
								// 如果附件Id不为空 获取附件
								if(!Utils.isNullOrEmpty(meetingNoteDetailQuery.getTaskId())){
									//List<FileIndex> listFileIndex=fileIndexDao.getByTaskId(meetingNoteDetailQuery.getTaskId());
									List<FileIndex> listFileIndex=fileIndexService.selectByTaskId(meetingNoteDetailQuery.getTaskId(),"1");
									if(!Utils.isNullOrEmpty(listFileIndex)){
										List<JTFile>listJTFile=new ArrayList<JTFile>();
										for(FileIndex fileIndex:listFileIndex){
											if(!Utils.isNullOrEmpty(fileIndex)){
												JTFile jTFile=JsonToBean.fileIndexToJTfile(fileIndex);
												if(!Utils.isNullOrEmpty(jTFile)){
													listJTFile.add(jTFile);
												}
											}
										}
										// 封装附件集合
										if(!Utils.isNullOrEmpty(listJTFile)){
											meetingNoteDetailQuery.setListJTFile(listJTFile);
										}
									}
								}
							}
							listResult.add(meetingNoteDetailQuery);
						}
						if(!Utils.isNullOrEmpty(listResult)){
							meetingNoteQuery.setListMeetingNoteDetailQuery(listResult);
						}
					}
				}
			}
		}
		return listMeetingNoteQuery;
	}
	/**
	 * 名称: getByMeetingIdAndMemberId
	 * 描述:根据member和meetingid 获取会议笔记
	 * @since  2014-10-02
	 * @author qingc
	 */
	public List<MeetingNoteQuery> getByMeetingIdAndMemberId(Long meetingId,Long memberId){
		List<MeetingNoteQuery> listMeetingNoteQuery = meetingNoteDao.getByMeetingIdAndMemberId(meetingId,memberId);
		if(!Utils.isNullOrEmpty(listMeetingNoteQuery)) {
			for(MeetingNoteQuery meetingNoteQuery : listMeetingNoteQuery) {
				if(!Utils.isNullOrEmpty(meetingNoteQuery)) {
					List<MeetingNoteDetail> listMeetingNoteDetail = meetingNoteDetailDao.getByMeetingNoteId(meetingNoteQuery.getId());
					if(!Utils.isNullOrEmpty(listMeetingNoteDetail)) {
						meetingNoteQuery.setListMeetingNoteDetail(listMeetingNoteDetail);
					}
				}
			}
		}
		return listMeetingNoteQuery;
	}
}
