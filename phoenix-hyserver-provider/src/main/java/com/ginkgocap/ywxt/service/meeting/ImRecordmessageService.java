/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.ChatMini;
import com.ginkgocap.ywxt.model.meeting.ImRecordmessage;
import com.ginkgocap.ywxt.model.meeting.MeetingVo;
import com.ginkgocap.ywxt.model.meeting.SocialListReq;
import com.ginkgocap.ywxt.vo.query.community.Community;
import com.ginkgocap.ywxt.vo.query.social.CommunityNewCount;
import com.ginkgocap.ywxt.vo.query.social.Social;

public interface ImRecordmessageService {
	/**
	 * 名称: getById 描述: 根据id查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public ImRecordmessage getById(Long property);

	/**
	 * 名称: delete 描述: 根据id删除
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void delete(Long property);

	/**
	 * 名称: getByUserId 描述: 根据userId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByUserId(java.lang.Integer property);

	/**
	 * 名称: getByUserId2 描述: 根据userId2查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByUserId2(java.lang.Integer property);

	/**
	 * 名称: getByChatMessageId 描述: 根据chatMessageId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByChatMessageId(java.lang.Integer property);

	/**
	 * 名称: getByMucId 描述: 根据mucId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMucId(java.lang.Integer property);

	/**
	 * 名称: getByMucCreateUserId 描述: 根据mucCreateUserId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMucCreateUserId(java.lang.Integer property);

	/**
	 * 名称: getByMucMessageId 描述: 根据mucMessageId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMucMessageId(java.lang.Integer property);

	/**
	 * 名称: getByMucStartDate 描述: 根据mucStartDate查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMucStartDate(java.util.Date property);

	/**
	 * 名称: getByType 描述: 根据type查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByType(java.lang.Integer property);

	/**
	 * 名称: getByLastMessageDate 描述: 根据lastMessageDate查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByLastMessageDate(java.util.Date property);

	/**
	 * 名称: getByNewCount 描述: 根据newCount查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByNewCount(java.lang.Integer property);

	/**
	 * 名称: getByMessageStartTime 描述: 根据messageStartTime查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMessageStartTime(java.util.Date property);

	/**
	 * 名称: getByStatus 描述: 根据status查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByStatus(java.lang.String property);

	/**
	 * 名称: saveOrUpdate 描述: 修改或者新增
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImRecordmessage entity);

	public List<MeetingVo> getChatMobileIndex(long user_id);

	public List<ChatMini> getChatMessage(long user_id);

	/**
	 * 名称: getPrivateChatAndGroupChat 描述: 获取私聊和群聊
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<Social> getPrivateChatAndGroupChat(SocialListReq property);
	
	/**
	 * 获取社群
	 * @author 周仕奇
	 * @date 2016年1月29日 上午10:07:37
	 * @param property
	 * @return
	 */
	public List<Community> getPrivateCommunity(SocialListReq property);
	
	public Integer getPrivateChatAndGroupChatNewCount(SocialListReq property);

	public Integer getAllMeetingNewCount(Long property);
	
	public List<CommunityNewCount> getCommunityNewCountByUserId(Integer userId);
}