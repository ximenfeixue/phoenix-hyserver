/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingTopic;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingTopicQuery;

public interface MeetingTopicService {
	/**
	 * 名称: getById 描述: 根据id查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public MeetingTopic getById(Long property);

	/**
	 * 名称: delete 描述: 根据id删除
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void delete(Long property);

	/**
	 * 名称: getByMeetingId 描述: 根据meetingId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getByMeetingId(Long property);
	/**
	 * 批量查询会议的议题
	 * @param listMeetingId
	 * @return
	 */
	public List<MeetingTopic> getByMeetingIds(List<Long> listMeetingId);
	/**
	 * 名称: getForwardingTopicByMeetingId 
	 * 描述: 根据会议id获取转发的议题列表
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getForwardingTopicByMeetingId(Long property);
	
	/**
	 * 名称: getByTopicCoutent 描述: 根据topicCoutent查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getByTopicCoutent(java.lang.String property);

	/**
	 * 名称: getByTopicTime 描述: 根据topicTime查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getByTopicTime(java.lang.String property);

	/**
	 * 名称: getByTopicDesc 描述: 根据topicDesc查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getByTopicDesc(java.lang.String property);

	/**
	 * 名称: getByTopicFileName 描述: 根据topicFileName查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getByTopicFileName(java.lang.String property);

	/**
	 * 名称: getByTopicFilePath 描述: 根据topicFilePath查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getByTopicFilePath(java.lang.String property);

	/**
	 * 名称: getByTaskId 描述: 根据taskId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getByTaskId(java.lang.String property);

	/**
	 * 名称: getByMemberId 描述: 根据memberId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingTopic> getByMemberId(Long property);

	/**
	 * 名称: saveOrUpdate 描述: 修改或者新增
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingTopic entity);

	/**
	 * 名称: getMeetingTopicList 
	 * 描述: 获取会议议题列表
	 * @since 2014-09-17
	 * @author qingc
	 * @throws Exception 
	 */
	public MeetingQuery getMeetingTopicList(Long meetingId) throws Exception;
	/**
	 * 名称: getTopicChatList 
	 * 描述: 获取议题聊天列表
	 * @since 2014-09-17
	 * @author qingc
	 * @throws Exception 
	 */
	public MeetingTopicQuery getTopicChatList(Long topicId) throws Exception;
	
}