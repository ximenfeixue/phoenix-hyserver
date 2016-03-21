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

import com.ginkgocap.ywxt.dao.meeting.ImMucmemberDao;
import com.ginkgocap.ywxt.model.meeting.ImMucmember;
@Repository
public class ImMucmemberDaoImpl extends SqlSessionDaoSupport implements ImMucmemberDao,ApplicationContextAware{
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
	public ImMucmember getById(Long property) {
		return (ImMucmember)getSqlSession().selectOne("ImMucmember.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("ImMucmember.delete",property);
	}
	
	/**
	 * 名称: getByMucid
	 * 描述: 根据mucid查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmember> getByMucid(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmember.getByMucid",property);
	}	
	public List<Map<String, Object>> getByMucids(Map<String, Object> map) {
		return getSqlSession().selectList("ImMucmember.getMembersByMucids", map);
	}
	
	/**
	 * 名称: getByUserId
	 * 描述: 根据userId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmember> getByUserId(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmember.getByUserId",property);
	}	
	
	/**
	 * 名称: getByCompereType
	 * 描述: 根据compereType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmember> getByCompereType(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmember.getByCompereType",property);
	}	
	
	/**
	 * 名称: getByJoinTime
	 * 描述: 根据joinTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmember> getByJoinTime(java.util.Date property) {
		return getSqlSession().selectList("ImMucmember.getByJoinTime",property);
	}	
	
	/**
	 * 名称: getByExitTime
	 * 描述: 根据exitTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmember> getByExitTime(java.util.Date property) {
		return getSqlSession().selectList("ImMucmember.getByExitTime",property);
	}	
	
	/**
	 * 名称: getByNotifyType
	 * 描述: 根据notifyType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmember> getByNotifyType(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmember.getByNotifyType",property);
	}	
	
	/**
	 * 名称: getByNewMessageCount
	 * 描述: 根据newMessageCount查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucmember> getByNewMessageCount(java.lang.Integer property) {
		return getSqlSession().selectList("ImMucmember.getByNewMessageCount",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImMucmember entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("ImMucmember.insert", entity);
		else 
			getSqlSession().update("ImMucmember.update", entity);
	}
}
