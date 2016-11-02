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
public class ImUserchannelDaoImpl extends SqlSessionDaoSupport implements ImUserchannelDao,ApplicationContextAware{
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
	public ImUserchannel getById(Long property) {
		return (ImUserchannel)getSqlSession().selectOne("ImUserchannel.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("ImUserchannel.delete",property);
	}
	
	/**
	 * 名称: getByUserId
	 * 描述: 根据userId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getByUserId(java.lang.Integer property) {
		return getSqlSession().selectList("ImUserchannel.getByUserId",property);
	}	
	
	/**
	 * 名称: getByChannelId
	 * 描述: 根据channelId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getByChannelId(java.lang.String property) {
		return getSqlSession().selectList("ImUserchannel.getByChannelId",property);
	}	
	
	/**
	 * 名称: getBySecretKey
	 * 描述: 根据secretKey查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getBySecretKey(java.lang.String property) {
		return getSqlSession().selectList("ImUserchannel.getBySecretKey",property);
	}	
	
	/**
	 * 名称: getByBaiduUserId
	 * 描述: 根据baiduUserId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getByBaiduUserId(java.lang.String property) {
		return getSqlSession().selectList("ImUserchannel.getByBaiduUserId",property);
	}	
	
	/**
	 * 名称: getByChannelType
	 * 描述: 根据channelType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getByChannelType(java.lang.Integer property) {
		return getSqlSession().selectList("ImUserchannel.getByChannelType",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImUserchannel entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("ImUserchannel.insert", entity);
		else 
			getSqlSession().update("ImUserchannel.update", entity);
	}
}
