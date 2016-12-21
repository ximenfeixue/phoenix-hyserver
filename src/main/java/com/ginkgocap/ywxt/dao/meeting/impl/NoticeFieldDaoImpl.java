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
public class NoticeFieldDaoImpl extends SqlSessionDaoSupport implements NoticeFieldDao,ApplicationContextAware{
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
	public NoticeField getById(Long property) {
		return (NoticeField)getSqlSession().selectOne("NoticeField.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("NoticeField.delete",property);
	}
	
	/**
	 * 名称: getByNoticeId
	 * 描述: 根据noticeId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<String> getByNoticeId(java.lang.Long property) {
		return getSqlSession().selectList("NoticeField.getByNoticeId",property);
	}	
	
	/**
	 * 名称: getByField
	 * 描述: 根据field查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<NoticeField> getByField(java.lang.String property) {
		return getSqlSession().selectList("NoticeField.getByField",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(NoticeField entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("NoticeField.insert", entity);
		else 
			getSqlSession().update("NoticeField.update", entity);
	}
}
