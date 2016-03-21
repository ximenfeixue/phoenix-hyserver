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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.mybatis.spring.support.SqlSessionDaoSupport;
@Repository
public class MeetingLabelDaoImpl extends SqlSessionDaoSupport implements MeetingLabelDao,ApplicationContextAware{
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
	public MeetingLabel getById(Long property) {
		return (MeetingLabel)getSqlSession().selectOne("MeetingLabel.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("MeetingLabel.delete",property);
	}
	
	/**
	 * 名称: getByLabelName
	 * 描述: 根据labelName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingLabel> getByLabelName(java.lang.String property) {
		return getSqlSession().selectList("MeetingLabel.getByLabelName",property);
	}	
	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingLabel> getByCreateId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingLabel.getByCreateId",property);
	}	
	/**
	 * 名称: getByCreateIdAndLabelName
	 * 描述: 根据createId和标签名字查找标签是否存在
	  * @since  2014-10-29
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingLabel> getByCreateIdAndLabelName(java.lang.Long property,java.lang.String labelName) {
		Map<String ,Object>paramMap =new HashMap<String ,Object>();
		paramMap.put("createId", property);
		paramMap.put("labelName", labelName);
		return getSqlSession().selectList("MeetingLabel.getByCreateIdAndLabelName",paramMap);
	}
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingLabel> getByCreateName(java.lang.String property) {
		return getSqlSession().selectList("MeetingLabel.getByCreateName",property);
	}	
	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingLabel> getByCreateTime(java.util.Date property) {
		return getSqlSession().selectList("MeetingLabel.getByCreateTime",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingLabel entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("MeetingLabel.insert", entity);
		else 
			getSqlSession().update("MeetingLabel.update", entity);
	}
}
