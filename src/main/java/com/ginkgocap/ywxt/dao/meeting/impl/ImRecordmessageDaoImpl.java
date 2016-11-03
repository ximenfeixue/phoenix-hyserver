/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.ImRecordmessageDao;
import com.ginkgocap.ywxt.model.meeting.ImRecordmessage;
import com.ginkgocap.ywxt.model.meeting.ImRecordmessageForCommunity;
import com.ginkgocap.ywxt.model.meeting.SocialListReq;
import com.ginkgocap.ywxt.vo.query.social.CommunityNewCount;

@Repository
public class ImRecordmessageDaoImpl extends SqlSessionDaoSupport implements ImRecordmessageDao, ApplicationContextAware {
	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 名称: getById 描述: 根据id查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public ImRecordmessage getById(Long property) {
		return (ImRecordmessage) getSqlSession().selectOne("ImRecordmessage.getById", property);
	}

	/**
	 * 名称: delete 描述: 根据id删除
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("ImRecordmessage.delete", property);
	}

	/**
	 * 名称: getByUserId 描述: 根据userId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByUserId(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByUserId", property);
	}

	/**
	 * 名称: getByUserId2 描述: 根据userId2查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByUserId2(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByUserId2", property);
	}

	/**
	 * 名称: getByChatMessageId 描述: 根据chatMessageId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByChatMessageId(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByChatMessageId", property);
	}

	/**
	 * 名称: getByMucId 描述: 根据mucId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMucId(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByMucId", property);
	}

	/**
	 * 名称: getByMucCreateUserId 描述: 根据mucCreateUserId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMucCreateUserId(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByMucCreateUserId", property);
	}

	/**
	 * 名称: getByMucMessageId 描述: 根据mucMessageId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMucMessageId(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByMucMessageId", property);
	}

	/**
	 * 名称: getByMucStartDate 描述: 根据mucStartDate查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMucStartDate(java.util.Date property) {
		return getSqlSession().selectList("ImRecordmessage.getByMucStartDate", property);
	}

	/**
	 * 名称: getByType 描述: 根据type查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByType(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByType", property);
	}

	/**
	 * 名称: getByLastMessageDate 描述: 根据lastMessageDate查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByLastMessageDate(java.util.Date property) {
		return getSqlSession().selectList("ImRecordmessage.getByLastMessageDate", property);
	}

	/**
	 * 名称: getByNewCount 描述: 根据newCount查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByNewCount(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByNewCount", property);
	}

	/**
	 * 名称: getByMessageStartTime 描述: 根据messageStartTime查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByMessageStartTime(java.util.Date property) {
		return getSqlSession().selectList("ImRecordmessage.getByMessageStartTime", property);
	}

	/**
	 * 名称: getByStatus 描述: 根据status查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<ImRecordmessage> getByStatus(java.lang.String property) {
		return getSqlSession().selectList("ImRecordmessage.getByStatus", property);
	}

	/**
	 * 名称: saveOrUpdate 描述: 修改或者新增
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImRecordmessage entity) {
		if (entity.getId() == null)
			getSqlSession().insert("ImRecordmessage.insert", entity);
		else
			getSqlSession().update("ImRecordmessage.update", entity);
	}

	public List<ImRecordmessage> getByUserIdAndStatus(java.lang.Integer property) {
		return getSqlSession().selectList("ImRecordmessage.getByUserIdAndStatus", property);
	}

	@Override
	public List<ImRecordmessage> getByUserIdAndStatusWithoutAffair(SocialListReq property) {
		return getSqlSession().selectList("ImRecordmessage.getByUserIdAndStatusWithoutAffair", property);
	}
	
	@Override
	public List<ImRecordmessageForCommunity> getCommunityByUserIdAndStatus(SocialListReq property) {
		return getSqlSession().selectList("ImRecordmessage.getCommunityByUserIdAndStatus", property);
	}
	
	public List<ImRecordmessage> getByUserIdGroup(java.lang.Long property) {
		return getSqlSession().selectList("ImRecordmessage.getByUserIdGroup", property);
	}

	@Override
	public void batchSave(Map<String, List<ImRecordmessage>> entities) {
		getSqlSession().insert("ImRecordmessage.batchInsert", entities);
	}

	@Override
	public List<ImRecordmessage> getMeetingRecordMessage(Long property) {
		return getSqlSession().selectList("ImRecordmessage.getMeetingRecordMessage", property);
	}
	
	@Override
	public Integer getAllMeetingNewCount(Long property) {
		return getSqlSession().selectOne("ImRecordmessage.getAllMeetingNewCount", property);
	}

	@Override
	public void batchDelete(Map<String, Object> entities) {
		getSqlSession().selectList("ImRecordmessage.batchDelete", entities);
	}

	/**
	 * 获取邀请函的未读消息数
	 */
	@Override
	public Integer getNewCountOfMeetingInvitation(Long property) {
		return getSqlSession().selectOne("ImRecordmessage.getNewCountOfMeetingInvitation", property);
	}

	@Override
	public void updateMeetingInvitationForReadStatus(Long property) {
		 getSqlSession().update("ImRecordmessage.updateMeetingInvitationForReadStatus", property);
	}

	@Override
	public List<CommunityNewCount> getCommunityNewCountByUserId(Integer userId) {
		return getSqlSession().selectList("ImRecordmessage.getCommunityNewCountByUserId", userId);
	}

	@Override
	public List<ImRecordmessage> getRecordMessageListByUserId(Integer userId) {
		return getSqlSession().selectList("ImRecordmessage.getRecordMessageListByUserId", userId);
	}
	
}
