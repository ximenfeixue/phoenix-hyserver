/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.*;




import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.common.base.*;
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
public class ImMucinfoDaoImpl extends SqlSessionDaoSupport implements ImMucinfoDao,ApplicationContextAware{
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
	public ImMucinfo getById(Long property) {
		return (ImMucinfo)getSqlSession().selectOne("ImMucinfo.getById",property);
	}
	public List<ImMucinfo> getByIds(Map<String, Object> map) {
		return getSqlSession().selectList("ImMucinfo.getByIds", map);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("ImMucinfo.delete",property);
	}
	
	/**
	 * 名称: getByTitle
	 * 描述: 根据title查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByTitle(java.lang.String property) {
		return getSqlSession().selectList("ImMucinfo.getByTitle",property);
	}	
	
	/**
	 * 名称: getBySubject
	 * 描述: 根据subject查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getBySubject(java.lang.String property) {
		return getSqlSession().selectList("ImMucinfo.getBySubject",property);
	}	
	
	/**
	 * 名称: getByType
	 * 描述: 根据type查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByType(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucinfo.getByType",property);
	}	
	
	/**
	 * 名称: getByOrganizationId
	 * 描述: 根据organizationId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByOrganizationId(java.lang.Long property) {
		return getSqlSession().selectList("ImMucinfo.getByOrganizationId",property);
	}	
	
	/**
	 * 名称: getByContent
	 * 描述: 根据content查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByContent(java.lang.String property) {
		return getSqlSession().selectList("ImMucinfo.getByContent",property);
	}	
	
	/**
	 * 名称: getByMax
	 * 描述: 根据max查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByMax(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucinfo.getByMax",property);
	}	
	
	/**
	 * 名称: getByStickType
	 * 描述: 根据stickType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByStickType(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucinfo.getByStickType",property);
	}	
	
	/**
	 * 名称: getByAutoSaveType
	 * 描述: 根据autoSaveType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByAutoSaveType(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucinfo.getByAutoSaveType",property);
	}	
	
	/**
	 * 名称: getByTime
	 * 描述: 根据time查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByTime(java.util.Date property) {
		return getSqlSession().selectList("ImMucinfo.getByTime",property);
	}	
	
	/**
	 * 名称: getByStatus
	 * 描述: 根据status查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByStatus(java.lang.String property) {
		return getSqlSession().selectList("ImMucinfo.getByStatus",property);
	}	
	
	/**
	 * 名称: getByCreateUserId
	 * 描述: 根据createUserId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByCreateUserId(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucinfo.getByCreateUserId",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImMucinfo entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("ImMucinfo.insert", entity);
		else 
			getSqlSession().update("ImMucinfo.update", entity);
	}
}
