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

import com.ginkgocap.ywxt.dao.meeting.ImChatmessageDao;
import com.ginkgocap.ywxt.model.meeting.ImChatmessage;
@Repository
public class ImChatmessageDaoImpl extends SqlSessionDaoSupport implements ImChatmessageDao,ApplicationContextAware{
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
	public ImChatmessage getById(Long property) {
		return (ImChatmessage)getSqlSession().selectOne("ImChatmessage.getById",property);
	}
	public List<ImChatmessage> getByIds(Map<String, Object> map) {
		return getSqlSession().selectList("ImChatmessage.getByIds",map);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("ImChatmessage.delete",property);
	}
	
	/**
	 * 名称: getByUserId1
	 * 描述: 根据userId1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByUserId1(java.lang.Integer property) {
		return getSqlSession().selectList("ImChatmessage.getByUserId1",property);
	}	
	
	/**
	 * 名称: getByUserId2
	 * 描述: 根据userId2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByUserId2(java.lang.Integer property) {
		return getSqlSession().selectList("ImChatmessage.getByUserId2",property);
	}	
	
	/**
	 * 名称: getByUserId1ReadStatus
	 * 描述: 根据userId1ReadStatus查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByUserId1ReadStatus(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByUserId1ReadStatus",property);
	}	
	
	/**
	 * 名称: getByUserId2ReadStatus
	 * 描述: 根据userId2ReadStatus查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByUserId2ReadStatus(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByUserId2ReadStatus",property);
	}	
	
	/**
	 * 名称: getBySenderId
	 * 描述: 根据senderId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getBySenderId(java.lang.Integer property) {
		return getSqlSession().selectList("ImChatmessage.getBySenderId",property);
	}	
	
	/**
	 * 名称: getByMsg
	 * 描述: 根据msg查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByMsg(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByMsg",property);
	}	
	
	/**
	 * 名称: getByMsgType
	 * 描述: 根据msgType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByMsgType(java.lang.Integer property) {
		return getSqlSession().selectList("ImChatmessage.getByMsgType",property);
	}	
	
	/**
	 * 名称: getByMessageId
	 * 描述: 根据messageId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByMessageId(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByMessageId",property);
	}	
	
	/**
	 * 名称: getByTime
	 * 描述: 根据time查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByTime(java.util.Date property) {
		return getSqlSession().selectList("ImChatmessage.getByTime",property);
	}	
	
	/**
	 * 名称: getByJtFileUrl
	 * 描述: 根据jtFileUrl查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileUrl(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileUrl",property);
	}	
	
	/**
	 * 名称: getByJtFileSuffixName
	 * 描述: 根据jtFileSuffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileSuffixName(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileSuffixName",property);
	}	
	
	/**
	 * 名称: getByJtFileType
	 * 描述: 根据jtFileType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileType(java.lang.Integer property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileType",property);
	}	
	
	/**
	 * 名称: getByJtFileName
	 * 描述: 根据jtFileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileName(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileName",property);
	}	
	
	/**
	 * 名称: getByJtFileSize
	 * 描述: 根据jtFileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileSize(java.lang.Integer property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileSize",property);
	}	
	
	/**
	 * 名称: getByJtFileModuleType
	 * 描述: 根据jtFileModuleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileModuleType(java.lang.Integer property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileModuleType",property);
	}	
	
	/**
	 * 名称: getByJtFileTaskId
	 * 描述: 根据jtFileTaskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileTaskId(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileTaskId",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved1
	 * 描述: 根据jtFileReserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileReserved1(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileReserved1",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved2
	 * 描述: 根据jtFileReserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileReserved2(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileReserved2",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved3
	 * 描述: 根据jtFileReserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getByJtFileReserved3(java.lang.String property) {
		return getSqlSession().selectList("ImChatmessage.getByJtFileReserved3",property);
	}	
	
	/**
	 * 名称: getBySequence
	 * 描述: 根据sequence查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImChatmessage> getBySequence(java.lang.Integer property) {
		return getSqlSession().selectList("ImChatmessage.getBySequence",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImChatmessage entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("ImChatmessage.insert", entity);
		else 
			getSqlSession().update("ImChatmessage.update", entity);
	}
}
