/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingSignLabelDao;
import com.ginkgocap.ywxt.model.meeting.MeetingSignLabel;
@Repository
public class MeetingSignLabelDaoImpl extends SqlSessionDaoSupport implements MeetingSignLabelDao,ApplicationContextAware{
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
	public MeetingSignLabel getById(Long property) {
		return (MeetingSignLabel)getSqlSession().selectOne("MeetingSignLabel.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("MeetingSignLabel.delete",property);
	}
	public void deleteByMeetingId(Long property) {
		getSqlSession().delete("MeetingSignLabel.deleteByMeetingId",property);
	}
	
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByMeetingId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingSignLabel.getByMeetingId",property);
	}	
	
	/**
	 * 名称: getByLabelName
	 * 描述: 根据labelName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByLabelName(java.lang.String property) {
		return getSqlSession().selectList("MeetingSignLabel.getByLabelName",property);
	}	
	
	/**
	 * 名称: getByIsCustom
	 * 描述: 根据isCustom查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByIsCustom(Integer property) {
		return getSqlSession().selectList("MeetingSignLabel.getByIsCustom",property);
	}	
	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByCreateId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingSignLabel.getByCreateId",property);
	}	
	
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByCreateName(java.lang.String property) {
		return getSqlSession().selectList("MeetingSignLabel.getByCreateName",property);
	}	
	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByCreateTime(java.util.Date property) {
		return getSqlSession().selectList("MeetingSignLabel.getByCreateTime",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingSignLabel entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("MeetingSignLabel.insert", entity);
		else 
			getSqlSession().update("MeetingSignLabel.update", entity);
	}
}
