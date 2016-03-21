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
public class ImNotifypushmessageDaoImpl extends SqlSessionDaoSupport implements ImNotifypushmessageDao,ApplicationContextAware{
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
	public ImNotifypushmessage getById(Long property) {
		return (ImNotifypushmessage)getSqlSession().selectOne("ImNotifypushmessage.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("ImNotifypushmessage.delete",property);
	}
	
	/**
	 * 名称: getByUserId
	 * 描述: 根据userId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImNotifypushmessage> getByUserId(java.lang.Integer property) {
		return getSqlSession().selectList("ImNotifypushmessage.getByUserId",property);
	}	
	
	/**
	 * 名称: getByNotifyType
	 * 描述: 根据notifyType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImNotifypushmessage> getByNotifyType(java.lang.Integer property) {
		return getSqlSession().selectList("ImNotifypushmessage.getByNotifyType",property);
	}	
	
	/**
	 * 名称: getByCount
	 * 描述: 根据count查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImNotifypushmessage> getByCount(java.lang.Long property) {
		return getSqlSession().selectList("ImNotifypushmessage.getByCount",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImNotifypushmessage entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("ImNotifypushmessage.insert", entity);
		else 
			getSqlSession().update("ImNotifypushmessage.update", entity);
	}
}
