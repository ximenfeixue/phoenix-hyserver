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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ginkgocap.parasol.file.model.FileIndex;
import com.ginkgocap.parasol.file.service.FileIndexService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingMemberDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingPicDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingTopicDao;
import com.ginkgocap.ywxt.dao.meeting.TopicChatDao;
import com.ginkgocap.ywxt.dao.meeting.UserDao;
// import com.ginkgocap.ywxt.file.model.FileIndex;
// import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingFile;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.model.meeting.MeetingTopic;
import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.service.meeting.MeetingTopicService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.utils.EmojiUtil;
import com.ginkgocap.ywxt.utils.JsonToBean;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingTopicQuery;
import com.ginkgocap.ywxt.vo.query.meeting.TopicChatQuery;

@Service
@Transactional
public class MeetingTopicServiceImpl implements MeetingTopicService{
	@Autowired
	private MeetingTopicDao meetingTopicDao;
	@Autowired
	private MeetingDao meetingDao;
	@Autowired
	private MeetingMemberDao meetingMemberDao;
	@Autowired
	private TopicChatDao topicChatDao;
	@Autowired
	private FileIndexService fileIndexService;
	@Autowired
	private UserService userService;
	@Autowired
	private MeetingPicDao meetingPicDao;
	@Autowired
	private UserDao userDao;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public MeetingTopic getById(Long property) {
		return meetingTopicDao.getById(property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long property){
		 meetingTopicDao.delete(property);
	}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getByMeetingId(java.lang.Long property) {
		return meetingTopicDao.getByMeetingId(property);
	}	
	/**
	 * 名称: getForwardingTopicByMeetingId 
	 * 描述: 根据会议id获取转发的议题列表
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getForwardingTopicByMeetingId(java.lang.Long property){
		return meetingTopicDao.getForwardingTopicByMeetingId(property);

	}
	/**
	 * 名称: getByTopicCoutent
	 * 描述: 根据topicCoutent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getByTopicCoutent(java.lang.String property) {
		return meetingTopicDao.getByTopicCoutent(property);
	}	
	/**
	 * 名称: getByTopicTime
	 * 描述: 根据topicTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getByTopicTime(java.lang.String property) {
		return meetingTopicDao.getByTopicTime(property);
	}	
	/**
	 * 名称: getByTopicDesc
	 * 描述: 根据topicDesc查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getByTopicDesc(java.lang.String property) {
		return meetingTopicDao.getByTopicDesc(property);
	}	
	/**
	 * 名称: getByTopicFileName
	 * 描述: 根据topicFileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getByTopicFileName(java.lang.String property) {
		return meetingTopicDao.getByTopicFileName(property);
	}	
	/**
	 * 名称: getByTopicFilePath
	 * 描述: 根据topicFilePath查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getByTopicFilePath(java.lang.String property) {
		return meetingTopicDao.getByTopicFilePath(property);
	}	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getByTaskId(java.lang.String property) {
		return meetingTopicDao.getByTaskId(property);
	}	
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingTopic> getByMemberId(java.lang.Long property) {
		return meetingTopicDao.getByMemberId(property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
		@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingTopic entity){
		 meetingTopicDao.saveOrUpdate(entity);
	}
	/**
	 * 名称: getMeetingTopicList 描述: 获取会议议题列表
	 * 
	 * @since 2014-09-17
	 * @author qingc
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	@Transactional(readOnly=true)
	public MeetingQuery getMeetingTopicList(Long meetingId) throws Exception{
		MeetingQuery meetingQuery=new MeetingQuery();
		Meeting meeting =meetingDao.getById(meetingId);
		if(Utils.isNullOrEmpty(meeting)){
			logger.error("会议不存在");
			throw new Exception("会议不存在");
		}else{
			if(Utils.isNullOrEmpty(meeting.getMeetingStatus())||meeting.getMeetingStatus()<=1){
				if(!new Date().before(meeting.getStartTime()))
					// 会议已开始
					meeting.setMeetingStatus(2);
			}
		}
		BeanUtils.copyProperties(meetingQuery, meeting);
		// 获取议题集合
		List<MeetingTopic> listTopic=meetingTopicDao.getByMeetingId(meetingId);
		List<MeetingTopicQuery> listTopicQuery = new ArrayList<MeetingTopicQuery>();
		if(!Utils.isNullOrEmpty(listTopic)){
			List<Long> userIdList = new ArrayList<Long>();
			for(MeetingTopic meetingTopic:listTopic){
				if(!Utils.isNullOrEmpty(meetingTopic)){
					userIdList.add(meetingTopic.getCreateId());
				}
			}
			Map<String, User> userMap = userDao.getUserMapByIds(userIdList);
			for(MeetingTopic meetingTopic:listTopic){
				if(!Utils.isNullOrEmpty(meetingTopic)&&!Utils.isNullOrEmpty(meetingTopic.getId())){
					MeetingTopicQuery meetingTopicQuery= new MeetingTopicQuery();
					boolean startFlag = false,endFlag=false,createFlag=false,updateFlag=false;
					// 转换vo便于封装聊天记录
					if(Utils.isNullOrEmpty(meetingTopic.getTopicStartTime())){
						meetingTopic.setTopicStartTime(new Date());
						startFlag=true;
					}
					if(Utils.isNullOrEmpty(meetingTopic.getTopicEndTime())){
						meetingTopic.setTopicEndTime(new Date());
						endFlag=true;
					}
					if(Utils.isNullOrEmpty(meetingTopic.getCreateTime())){
						meetingTopic.setCreateTime(new Date());
						createFlag=true;
					}
					if(Utils.isNullOrEmpty(meetingTopic.getUpdateTime())){
						meetingTopic.setUpdateTime(new Date());
						updateFlag=true;
					}
					BeanUtils.copyProperties(meetingTopicQuery, meetingTopic);
					if(startFlag){
						meetingTopicQuery.setTopicStartTime(null);
					}
					if(endFlag){
						meetingTopicQuery.setTopicEndTime(null);
					}
					if(createFlag){
						meetingTopicQuery.setCreateTime(null);
					}
					if(updateFlag){
						meetingTopicQuery.setUpdateTime(null);
					}
					//封装图片
					List<MeetingPic> listMeetingPic = meetingPicDao.getByModuleId(meetingTopic.getId(), MeetingPic.MODULE_TYPE_TOPIC);
					List<String> listFileIndexId = new ArrayList<String>();
					if(!Utils.isNullOrEmpty(listMeetingPic)) {
						for(MeetingPic meetingPic : listMeetingPic) {
							listFileIndexId.add(meetingPic.getFileIndexId());
//							if(Utils.isNullOrEmpty(meetingPic.getWidth())
//									|| Utils.isNullOrEmpty(meetingPic.getHeight())) {
//								BufferedImage bufferedImage = MeetingPicUtil.getBufferedImage(meetingPic.getPicPath());
//								if(!Utils.isNullOrEmpty(bufferedImage)) {
//									meetingPic.setWidth(""+bufferedImage.getWidth());
//									meetingPic.setHeight(""+bufferedImage.getHeight());
//								}
//							}
							if(meetingPic.getIshomePage() == 1) {
								meetingTopicQuery.setPath(meetingPic.getPicPath());
							}
						}
						meetingTopicQuery.setListMeetingPic(listMeetingPic);
					}
					// 获取附件列表
					if(!Utils.isNullOrEmpty(meetingTopic.getTaskId())){
						List<FileIndex> listFileIndex=fileIndexService.getFileIndexesByTaskId(meetingTopic.getTaskId());
						List<MeetingFile> listMeetingFile = new ArrayList<MeetingFile>();
						if(!Utils.isNullOrEmpty(listFileIndex)){
							for(FileIndex fileIndex:listFileIndex){
								if(!Utils.isNullOrEmpty(fileIndex)
										&& !listFileIndexId.contains(fileIndex.getId())){
									MeetingFile meetingFile = JsonToBean.apiFileIndexToMeetingFile(fileIndex);
									listMeetingFile.add(meetingFile);
								}
							}
						}
						// 封装附件列表
						if(!Utils.isNullOrEmpty(listMeetingFile)){
							meetingTopicQuery.setListMeetingFile(listMeetingFile);	
						}
					}
					// 获取议题聊天记录
					List<TopicChat> listChat=topicChatDao.getByTopicId(meetingTopic.getId());
					// 封装议题聊天记录
					if(!Utils.isNullOrEmpty(listChat)){
						List<Long> listUserId = new ArrayList<Long>();
						for (TopicChat tc : listChat) {
							listUserId.add(tc.getMemberId());
							tc.setChatContent(EmojiUtil.emojiRecover(tc.getChatContent()));//处理emoji表情
						}
						List<User> listUser = userDao.getByIds(listUserId);
						meetingTopicQuery.setListTopicChat(TopicChatQuery.copyList(listChat, listUser));
					}
					// 获取 用户头像 和名字
					User user = userMap.get(""+meetingTopic.getMemberId());
					if(!Utils.isNullOrEmpty(user)){
						meetingTopicQuery.setCreateName(user.getName());
						meetingTopicQuery.setMemberName(user.getName());
						meetingTopicQuery.setMemberPic(user.getPicPath());
						meetingTopicQuery.setMemberDesc(user.getCompanyName());
					}
					listTopicQuery.add(meetingTopicQuery);
				}
			}
			meetingQuery.setListMeetingTopicQuery(listTopicQuery);
			// 获取参会人数和签到人数
			Integer attendMeetingCount=meetingMemberDao.getAttendMeetingCount(meetingId);
			Integer signInCount=meetingMemberDao.getSignInCount(meetingId);
			meetingQuery.setAttendMeetingCount(attendMeetingCount);
			meetingQuery.setSignInCount(signInCount);
		}
		return meetingQuery;
	}
	/**
	 * 名称: getTopicChatList 
	 * 描述: 获取议题聊天列表
	 * @since 2014-09-17
	 * @author qingc
	 * @throws Exception 
	 */
	@Transactional(readOnly=true)
	public MeetingTopicQuery getTopicChatList(Long topicId) throws IllegalAccessException, InvocationTargetException, Exception{
		MeetingTopicQuery meetingTopicQuery=new MeetingTopicQuery();
		MeetingTopic meetingTopic =meetingTopicDao.getById(topicId);
		if(Utils.isNullOrEmpty(meetingTopic)){
			logger.error("议题不存在");
			throw new Exception("议题不存在");
		}
		boolean startFlag = false,endFlag=false;
		// 转换vo便于封装聊天记录
		if(Utils.isNullOrEmpty(meetingTopic.getTopicStartTime())){
			meetingTopic.setTopicStartTime(new Date());
			startFlag=true;
		}
		if(Utils.isNullOrEmpty(meetingTopic.getTopicEndTime())){
			meetingTopic.setTopicEndTime(new Date());
			endFlag=true;
		}
		org.apache.commons.beanutils.BeanUtils.copyProperties(meetingTopicQuery, meetingTopic);
		if(startFlag){
			meetingTopicQuery.setTopicStartTime(null);
		}
		if(endFlag){
			meetingTopicQuery.setTopicEndTime(null);
		}
		// 获取聊天记录
		List<TopicChat> listChat=topicChatDao.getByTopicId(meetingTopic.getId());
		// 封装议题聊天记录
		if(!Utils.isNullOrEmpty(listChat)){
			List<Long> listUserId = new ArrayList<Long>();
			for (TopicChat tc : listChat) {
				listUserId.add(tc.getMemberId());
				tc.setChatContent(EmojiUtil.emojiRecover(tc.getChatContent()));//处理emoji表情
			}
			List<User> listUser = userDao.getByIds(listUserId);
			meetingTopicQuery.setListTopicChat(TopicChatQuery.copyList(listChat, listUser));
		}
		return meetingTopicQuery;
	}
	/**
	 * 批量查询会议的议题
	 * @param listMeetingId
	 * @return
	 */
	public List<MeetingTopic> getByMeetingIds(List<Long> listMeetingId) {
		return meetingTopicDao.getByMeetingIds(listMeetingId);
	}
}