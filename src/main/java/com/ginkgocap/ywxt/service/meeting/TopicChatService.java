/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ginkgocap.ywxt.model.meeting.TopicChat;

public interface TopicChatService {
/**
 * 名称: getById
 * 描述: 根据id查找
 * @since  2014-09-14
 * @author qingc
 */
public TopicChat getById(Long property) ;
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
	public List<TopicChat> getByMeetingId(java.lang.Long property);	
	/**
	 * 名称: getBySenderType
	 * 描述: 根据senderType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getBySenderType(java.lang.Integer property);	
	/**
	 * 名称: getByTopicId
	 * 描述: 根据topicId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByTopicId(java.lang.Long property);	
	/**
	 * 名称: getByChatContent
	 * 描述: 根据chatContent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByChatContent(java.lang.String property);	
	/**
	 * 名称: getByChatType
	 * 描述: 根据chatType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByChatType(java.lang.Integer property);	
	/**
	 * 名称: getByMessageId
	 * 描述: 根据messageId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByMessageId(java.lang.String property);	
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByMemberId(java.lang.Long property);	
	/**
	 * 名称: getByJtfileUrl
	 * 描述: 根据jtfileUrl查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileUrl(java.lang.String property);	
	/**
	 * 名称: getByJtfileSuffixName
	 * 描述: 根据jtfileSuffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileSuffixName(java.lang.String property);	
	/**
	 * 名称: getByJtfileType
	 * 描述: 根据jtfileType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileType(java.lang.String property);	
	/**
	 * 名称: getByJtfileName
	 * 描述: 根据jtfileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileName(java.lang.String property);	
	/**
	 * 名称: getByJtfileSize
	 * 描述: 根据jtfileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileSize(java.lang.Integer property);	
	/**
	 * 名称: getByJtFileModuleType
	 * 描述: 根据jtFileModuleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtFileModuleType(java.lang.Integer property);	
	/**
	 * 名称: getByJtfileTaskId
	 * 描述: 根据jtfileTaskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtfileTaskId(java.lang.String property);	
	/**
	 * 名称: getByPublishTime
	 * 描述: 根据publishTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByPublishTime(java.lang.String property);	
	/**
	 * 名称: getByJtFileReserved1
	 * 描述: 根据jtFileReserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtFileReserved1(java.lang.String property);	
	/**
	 * 名称: getByJtFileReserved2
	 * 描述: 根据jtFileReserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtFileReserved2(java.lang.String property);	
	/**
	 * 名称: getByJtFileReserved3
	 * 描述: 根据jtFileReserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<TopicChat> getByJtFileReserved3(java.lang.String property);	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(TopicChat entity) ;
	/**
	 * 名称: sendingSystemMessage
	 * 描述: 发送系统消息
	  * @since  2015-01-04
	 * @author qingc
	 */
	public void sendingSystemMessage(TopicChat topicChat);
	
	
	public TopicChat selectByMeetingId(Long property);
	/**
	 * 名称: getLastNoticeTime
	 * 描述: 获取最后消息时间
	  * @since  2015-01-22
	 * @author qingc
	 */
	public Date  getLastNoticeTime(Long meetingId);
	public List<Map<String, Object>> getLastNoticeTimes(Map<String, Object> map);
	/**
	 * 分页查询
	 * @param param
	 */
	public List<TopicChat> getMeetingMessageByPage(Map<String, Object> param);
	public int getMeetingMessageCount(Map<String, Object> param);
}