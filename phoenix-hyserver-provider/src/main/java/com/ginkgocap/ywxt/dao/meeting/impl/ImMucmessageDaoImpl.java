/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.*;

import javacommon.base.*;


import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;


import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.mybatis.spring.support.SqlSessionDaoSupport;
@Repository
public class ImMucmessageDaoImpl extends SqlSessionDaoSupport implements ImMucmessageDao,ApplicationContextAware{
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
	public ImMucmessage getById(Long property) {
		return (ImMucmessage)getSqlSession().selectOne("ImMucmessage.getById",property);
	}
	public List<ImMucmessage> getByIds(Map<String, Object> map) {
		return getSqlSession().selectList("ImMucmessage.getByIds",map);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("ImMucmessage.delete",property);
	}
	
	/**
	 * 名称: getByMucid
	 * 描述: 根据mucid查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByMucid(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmessage.getByMucid",property);
	}	
	
	/**
	 * 名称: getBySenderId
	 * 描述: 根据senderId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getBySenderId(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmessage.getBySenderId",property);
	}	
	
	/**
	 * 名称: getBySenderType
	 * 描述: 根据senderType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getBySenderType(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmessage.getBySenderType",property);
	}	
	
	/**
	 * 名称: getByMsg
	 * 描述: 根据msg查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByMsg(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByMsg",property);
	}	
	
	/**
	 * 名称: getByMsgType
	 * 描述: 根据msgType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByMsgType(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmessage.getByMsgType",property);
	}	
	
	/**
	 * 名称: getByTime
	 * 描述: 根据time查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByTime(java.util.Date property) {
		return getSqlSession().selectList("ImMucmessage.getByTime",property);
	}	
	
	/**
	 * 名称: getByMessageId
	 * 描述: 根据messageId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByMessageId(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByMessageId",property);
	}	
	
	/**
	 * 名称: getByJtFileUrl
	 * 描述: 根据jtFileUrl查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileUrl(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileUrl",property);
	}	
	
	/**
	 * 名称: getByJtFileSuffixName
	 * 描述: 根据jtFileSuffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileSuffixName(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileSuffixName",property);
	}	
	
	/**
	 * 名称: getByJtFileType
	 * 描述: 根据jtFileType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileType(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileType",property);
	}	
	
	/**
	 * 名称: getByJtFileName
	 * 描述: 根据jtFileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileName(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileName",property);
	}	
	
	/**
	 * 名称: getByJtFileSize
	 * 描述: 根据jtFileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileSize(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileSize",property);
	}	
	
	/**
	 * 名称: getByJtFileModuleType
	 * 描述: 根据jtFileModuleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileModuleType(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileModuleType",property);
	}	
	
	/**
	 * 名称: getByJtFileTaskId
	 * 描述: 根据jtFileTaskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileTaskId(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileTaskId",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved1
	 * 描述: 根据jtFileReserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileReserved1(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileReserved1",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved2
	 * 描述: 根据jtFileReserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileReserved2(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileReserved2",property);
	}	
	
	/**
	 * 名称: getByJtFileReserved3
	 * 描述: 根据jtFileReserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getByJtFileReserved3(java.lang.String property) {
		return getSqlSession().selectList("ImMucmessage.getByJtFileReserved3",property);
	}	
	
	/**
	 * 名称: getBySequence
	 * 描述: 根据sequence查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmessage> getBySequence(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmessage.getBySequence",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImMucmessage entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("ImMucmessage.insert", entity);
		else 
			getSqlSession().update("ImMucmessage.update", entity);
	}
}
