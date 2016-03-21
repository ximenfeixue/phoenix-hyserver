/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.model.meeting.ImRecordmessage;
import com.ginkgocap.ywxt.model.meeting.ImRecordmessageForCommunity;
import com.ginkgocap.ywxt.model.meeting.SocialListReq;
import com.ginkgocap.ywxt.vo.query.social.CommunityNewCount;

@Repository
public interface ImRecordmessageDao{
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

	public void batchSave(Map<String, List<ImRecordmessage>> entities);

	public void batchDelete(Map<String, Object> entities);

	/**
	 * map中传递userId即可
	 * 
	 * @param map
	 * @return
	 */
	public List<ImRecordmessage> getMeetingRecordMessage(Long property);

	public Integer getAllMeetingNewCount(Long property);

	public List<ImRecordmessage> getByUserIdAndStatus(java.lang.Integer property);

	public List<ImRecordmessage> getByUserIdAndStatusWithoutAffair(SocialListReq property);

	/**
	 * 提取社群聊天记录
	 * @author 周仕奇
	 * @date 2016年1月29日 上午9:56:25
	 * @param property
	 * @return
	 */
	public List<ImRecordmessageForCommunity> getCommunityByUserIdAndStatus(SocialListReq property);

	public Integer getNewCountOfMeetingInvitation(java.lang.Long property);

	public void updateMeetingInvitationForReadStatus(java.lang.Long property);

	public List<ImRecordmessage> getByUserIdGroup(java.lang.Long property);
	
	/**
	 * 获取社群的未读消息数
	 * @param userId 用户id
	 * @return 用户所在的所有社群未读消息数
	 */
	List<CommunityNewCount> getCommunityNewCountByUserId(Integer userId);
	
	List<ImRecordmessage> getRecordMessageListByUserId(Integer userId);
	
}
