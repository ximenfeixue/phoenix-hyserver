/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.TopicChatDao;
import com.ginkgocap.ywxt.model.meeting.TopicChat;
@Repository
public class TopicChatDaoImpl extends SqlSessionDaoSupport implements TopicChatDao,ApplicationContextAware{
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
	public TopicChat getById(Long property) {
		return (TopicChat)getSqlSession().selectOne("TopicChat.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("TopicChat.delete",property);
	}
	
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByMeetingId(java.lang.Long property) {
		return getSqlSession().selectList("TopicChat.getByMeetingId",property);
	}	
	
	/**
	 * 名称: getBySenderType
	 * 描述: 根据senderType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getBySenderType(java.lang.Integer property) {
		return getSqlSession().selectList("TopicChat.getBySenderType",property);
	}	
	
	/**
	 * 名称: getByTopicId
	 * 描述: 根据topicId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByTopicId(java.lang.Long property) {
		return getSqlSession().selectList("TopicChat.getByTopicId",property);
	}	
	
	/**
	 * 名称: getByChatContent
	 * 描述: 根据chatContent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByChatContent(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByChatContent",property);
	}	
	
	/**
	 * 名称: getByChatType
	 * 描述: 根据chatType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByChatType(java.lang.Integer property) {
		return getSqlSession().selectList("TopicChat.getByChatType",property);
	}	
	
	/**
	 * 名称: getByMessageId
	 * 描述: 根据messageId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByMessageId(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByMessageId",property);
	}	
	
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByMemberId(java.lang.Long property) {
		return getSqlSession().selectList("TopicChat.getByMemberId",property);
	}	
	
	/**
	 * 名称: getByJtfileUrl
	 * 描述: 根据jtfileUrl查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileUrl(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByJtfileUrl",property);
	}	
	
	/**
	 * 名称: getByJtfileSuffixName
	 * 描述: 根据jtfileSuffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileSuffixName(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByJtfileSuffixName",property);
	}	
	
	/**
	 * 名称: getByJtfileType
	 * 描述: 根据jtfileType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileType(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByJtfileType",property);
	}	
	
	/**
	 * 名称: getByJtfileName
	 * 描述: 根据jtfileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileName(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByJtfileName",property);
	}	
	
	/**
	 * 名称: getByJtfileSize
	 * 描述: 根据jtfileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileSize(java.lang.Integer property) {
		return getSqlSession().selectList("TopicChat.getByJtfileSize",property);
	}	
	
	/**
	 * 名称: getByJtFileModuleType
	 * 描述: 根据jtFileModuleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtFileModuleType(java.lang.Integer property) {
		return getSqlSession().selectList("TopicChat.getByJtFileModuleType",property);
	}	
	
	/**
	 * 名称: getByJtfileTaskId
	 * 描述: 根据jtfileTaskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileTaskId(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByJtfileTaskId",property);
	}	
	
	/**
	 * 名称: getByPublishTime
	 * 描述: 根据publishTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByPublishTime(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByPublishTime",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved1
	 * 描述: 根据jtFileReserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtFileReserved1(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByJtFileReserved1",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved2
	 * 描述: 根据jtFileReserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtFileReserved2(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByJtFileReserved2",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved3
	 * 描述: 根据jtFileReserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtFileReserved3(java.lang.String property) {
		return getSqlSession().selectList("TopicChat.getByJtFileReserved3",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(TopicChat entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("TopicChat.insert", entity);
		else 
			getSqlSession().update("TopicChat.update", entity);
	}

	
	public TopicChat selectByMeetingId(Long property) {
		return getSqlSession().selectOne("TopicChat.selectByMeetingId",property);
	}
	
	/**
	 * 名称: getLastNoticeTime
	 * 描述: 获取最后消息时间
	  * @since  2015-01-22
	 * @author qingc
	 */
	public Date  getLastNoticeTime(Long meetingId){
		return getSqlSession().selectOne("TopicChat.getLastNoticeTime",meetingId);	
	}
	public List<Map<String, Object>>  getLastNoticeTimes(Map<String, Object> map){
		return getSqlSession().selectList("TopicChat.getLastNoticeTimes",map);	
	}
	/**
	 * 查询会议聊天记录
	 * @param param
	 * @return
	 */
	public List<TopicChat> getMeetingMessageByPage(Map<String, Object> param) {
		return getSqlSession().selectList("TopicChat.getMeetingMessageByPage", param);
	}
	public int getMeetingMessageCount(Map<String, Object> param) {
		return (Integer)getSqlSession().selectOne("TopicChat.getMeetingMessageCount", param);
	}
}
