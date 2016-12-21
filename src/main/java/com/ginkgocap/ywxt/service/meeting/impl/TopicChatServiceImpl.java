/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.Utils;

@Service
@Transactional
public class TopicChatServiceImpl implements TopicChatService{
	@Autowired
	private TopicChatDao topicChatDao;
	@Autowired
	private UserDao userDao;
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public TopicChat getById(Long property) {
		return topicChatDao.getById(property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long property){
		 topicChatDao.delete(property);
	}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByMeetingId(java.lang.Long property) {
		return topicChatDao.getByMeetingId(property);
	}	
	/**
	 * 名称: getBySenderType
	 * 描述: 根据senderType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getBySenderType(java.lang.Integer property) {
		return topicChatDao.getBySenderType(property);
	}	
	/**
	 * 名称: getByTopicId
	 * 描述: 根据topicId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByTopicId(java.lang.Long property) {
		return topicChatDao.getByTopicId(property);
	}	
	/**
	 * 名称: getByChatContent
	 * 描述: 根据chatContent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByChatContent(java.lang.String property) {
		return topicChatDao.getByChatContent(property);
	}	
	/**
	 * 名称: getByChatType
	 * 描述: 根据chatType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByChatType(java.lang.Integer property) {
		return topicChatDao.getByChatType(property);
	}	
	/**
	 * 名称: getByMessageId
	 * 描述: 根据messageId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByMessageId(java.lang.String property) {
		return topicChatDao.getByMessageId(property);
	}	
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByMemberId(java.lang.Long property) {
		return topicChatDao.getByMemberId(property);
	}	
	/**
	 * 名称: getByJtfileUrl
	 * 描述: 根据jtfileUrl查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtfileUrl(java.lang.String property) {
		return topicChatDao.getByJtfileUrl(property);
	}	
	/**
	 * 名称: getByJtfileSuffixName
	 * 描述: 根据jtfileSuffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtfileSuffixName(java.lang.String property) {
		return topicChatDao.getByJtfileSuffixName(property);
	}	
	/**
	 * 名称: getByJtfileType
	 * 描述: 根据jtfileType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtfileType(java.lang.String property) {
		return topicChatDao.getByJtfileType(property);
	}	
	/**
	 * 名称: getByJtfileName
	 * 描述: 根据jtfileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtfileName(java.lang.String property) {
		return topicChatDao.getByJtfileName(property);
	}	
	/**
	 * 名称: getByJtfileSize
	 * 描述: 根据jtfileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtfileSize(java.lang.Integer property) {
		return topicChatDao.getByJtfileSize(property);
	}	
	/**
	 * 名称: getByJtFileModuleType
	 * 描述: 根据jtFileModuleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtFileModuleType(java.lang.Integer property) {
		return topicChatDao.getByJtFileModuleType(property);
	}	
	/**
	 * 名称: getByJtfileTaskId
	 * 描述: 根据jtfileTaskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtfileTaskId(java.lang.String property) {
		return topicChatDao.getByJtfileTaskId(property);
	}	
	/**
	 * 名称: getByPublishTime
	 * 描述: 根据publishTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByPublishTime(java.lang.String property) {
		return topicChatDao.getByPublishTime(property);
	}	
	/**
	 * 名称: getByJtFileReserved1
	 * 描述: 根据jtFileReserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtFileReserved1(java.lang.String property) {
		return topicChatDao.getByJtFileReserved1(property);
	}	
	/**
	 * 名称: getByJtFileReserved2
	 * 描述: 根据jtFileReserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtFileReserved2(java.lang.String property) {
		return topicChatDao.getByJtFileReserved2(property);
	}	
	/**
	 * 名称: getByJtFileReserved3
	 * 描述: 根据jtFileReserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<TopicChat> getByJtFileReserved3(java.lang.String property) {
		return topicChatDao.getByJtFileReserved3(property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(TopicChat entity){
		 topicChatDao.saveOrUpdate(entity);
	}
	/**
	 * 名称: sendingSystemMessage
	 * 描述: 发送系统消息
	  * @since  2015-01-04
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void sendingSystemMessage(TopicChat topicChat){
		topicChat.setPublishTime(new Date());
		topicChat.setChatType(0);
		topicChat.setSenderType(0);
		topicChat.setMemberId(0l);
		topicChatDao.saveOrUpdate(topicChat);
	}
	@Transactional(readOnly=true)
	public TopicChat selectByMeetingId(Long property) {
		return topicChatDao.selectByMeetingId(property);
	}
	
	/**
	 * 名称: getLastNoticeTime
	 * 描述: 获取最后消息时间
	  * @since  2015-01-22
	 * @author qingc
	 */
	public Date  getLastNoticeTime(Long meetingId){
		return topicChatDao.getLastNoticeTime(meetingId);
	}
	public List<Map<String, Object>> getLastNoticeTimes(Map<String, Object> map) {
		return topicChatDao.getLastNoticeTimes(map);
	}
	public List<TopicChat> getMeetingMessageByPage(Map<String, Object> param) {
		List<TopicChat> list = topicChatDao.getMeetingMessageByPage(param);
		//设置用户头像、名称
		if(!Utils.isNullOrEmpty(list)) {
			List<Long> userIdList = new ArrayList<Long>();
			for(TopicChat chat : list) {
				userIdList.add(chat.getMemberId());
			}
			Map<String, User> userMap = userDao.getUserMapByIds(userIdList);
			for(TopicChat chat : list) {
				User user = userMap.get(""+chat.getMemberId());
				if(!Utils.isNullOrEmpty(user)) {
					chat.setMemberName(user.getName());
					chat.setMemberPic(user.getPicPath());
				}
			}
		}
		return list;
	}
	public int getMeetingMessageCount(Map<String, Object> param) {
		return topicChatDao.getMeetingMessageCount(param);
	}
}