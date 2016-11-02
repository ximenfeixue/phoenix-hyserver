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
public class ImJtfileDaoImpl extends SqlSessionDaoSupport implements ImJtfileDao,ApplicationContextAware{
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
	public ImJtfile getById(Long property) {
		return (ImJtfile)getSqlSession().selectOne("ImJtfile.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("ImJtfile.delete",property);
	}
	
	/**
	 * 名称: getByParentId
	 * 描述: 根据parentId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByParentId(java.lang.Integer property) {
		return getSqlSession().selectList("ImJtfile.getByParentId",property);
	}	
	
	/**
	 * 名称: getByParentType
	 * 描述: 根据parentType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByParentType(java.lang.Integer property) {
		return getSqlSession().selectList("ImJtfile.getByParentType",property);
	}	
	
	/**
	 * 名称: getByUrl
	 * 描述: 根据url查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByUrl(java.lang.String property) {
		return getSqlSession().selectList("ImJtfile.getByUrl",property);
	}	
	
	/**
	 * 名称: getBySuffixName
	 * 描述: 根据suffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getBySuffixName(java.lang.String property) {
		return getSqlSession().selectList("ImJtfile.getBySuffixName",property);
	}	
	
	/**
	 * 名称: getByType
	 * 描述: 根据type查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByType(java.lang.Integer property) {
		return getSqlSession().selectList("ImJtfile.getByType",property);
	}	
	
	/**
	 * 名称: getByFileName
	 * 描述: 根据fileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByFileName(java.lang.String property) {
		return getSqlSession().selectList("ImJtfile.getByFileName",property);
	}	
	
	/**
	 * 名称: getByFileSize
	 * 描述: 根据fileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByFileSize(java.lang.Integer property) {
		return getSqlSession().selectList("ImJtfile.getByFileSize",property);
	}	
	
	/**
	 * 名称: getByModuleType
	 * 描述: 根据moduleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByModuleType(java.lang.Integer property) {
		return getSqlSession().selectList("ImJtfile.getByModuleType",property);
	}	
	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByTaskId(java.lang.String property) {
		return getSqlSession().selectList("ImJtfile.getByTaskId",property);
	}	
	
	/**
	 * 名称: getByReserved1
	 * 描述: 根据reserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByReserved1(java.lang.String property) {
		return getSqlSession().selectList("ImJtfile.getByReserved1",property);
	}	
	
	/**
	 * 名称: getByReserved2
	 * 描述: 根据reserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByReserved2(java.lang.String property) {
		return getSqlSession().selectList("ImJtfile.getByReserved2",property);
	}	
	
	/**
	 * 名称: getByReserved3
	 * 描述: 根据reserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByReserved3(java.lang.String property) {
		return getSqlSession().selectList("ImJtfile.getByReserved3",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImJtfile entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("ImJtfile.insert", entity);
		else 
			getSqlSession().update("ImJtfile.update", entity);
	}
}
