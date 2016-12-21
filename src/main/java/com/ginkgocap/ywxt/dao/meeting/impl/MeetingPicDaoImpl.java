/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingPicDao;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.utils.Utils;
@Repository
public class MeetingPicDaoImpl extends SqlSessionDaoSupport implements MeetingPicDao,ApplicationContextAware{
	@Autowired
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
	public MeetingPic getById(Long property) {
		return (MeetingPic)getSqlSession().selectOne("MeetingPic.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("MeetingPic.delete",property);
	}
	/**
	 * 名称: deleteByMeetingId
	 * 描述: 根据meetingId删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByMeetingId(Long property) {
		final Map<String,Object> map=new HashMap<String, Object>();
		map.put("meetingId", property);
		getSqlSession().delete("MeetingPic.deleteByMeetingId",map);
	}
	/**
	 * 根据模块ID删除
	 * @param moduleId 模块ID
	 * @param moduleType 模块类型 1：会议 2：议题 3：笔记
	 */
	public void deleteByModuleId(Long moduleId, Long moduleType, List<Long> listMeetingPicId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("moduleId", moduleId);
		map.put("moduleType", moduleType);
		if(!Utils.isNullOrEmpty(listMeetingPicId)) {
			map.put("excludeIds", listMeetingPicId);
		}
		getSqlSession().delete("MeetingPic.deleteByModuleId", map);
	}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByMeetingId(Long property) {
		return getSqlSession().selectList("MeetingPic.getByMeetingId",property);
	}
	/**
	 * 根据模块ID查询图片
	 * @param moduleId 模块ID
	 * @param moduleId 模块类型 1：会议 2：议题 3：笔记
	 * @return 图片列表
	 */
	public List<MeetingPic> getByModuleId(Long moduleId, Long moduleType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("moduleId", moduleId);
		map.put("moduleType", moduleType);
		return getSqlSession().selectList("MeetingPic.getByModuleId", map);
	}
	
	/**
	 * 名称: getCoverByMeetingId
	 * 描述: 根据会议id查找封面
	 * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingPic getCoverByMeetingId(Long property){
		return (MeetingPic)getSqlSession().selectOne("MeetingPic.getCoverByMeetingId",property);
	}
	
	/**
	 * 名称: getByPicPath
	 * 描述: 根据picPath查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicPath(String property) {
		return getSqlSession().selectList("MeetingPic.getByPicPath",property);
	}	
	
	/**
	 * 名称: getByPicName
	 * 描述: 根据picName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicName(String property) {
		return getSqlSession().selectList("MeetingPic.getByPicName",property);
	}	
	
	/**
	 * 名称: getByPicRealName
	 * 描述: 根据picRealName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicRealName(String property) {
		return getSqlSession().selectList("MeetingPic.getByPicRealName",property);
	}	
	
	/**
	 * 名称: getByPicDesc
	 * 描述: 根据picDesc查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicDesc(String property) {
		return getSqlSession().selectList("MeetingPic.getByPicDesc",property);
	}	
	/**
	 * 名称: getByFileIndexId
	 * 描述: 根据taskId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByFileIndexId(String taskId) {
		return getSqlSession().selectList("MeetingPic.getByFileIndexId",taskId);
	}
	/**
	 * 名称: getByIshomePage
	 * 描述: 根据ishomePage查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByIshomePage(String property) {
		return getSqlSession().selectList("MeetingPic.getByIshomePage",property);
	}	
	
	/**
	 * 名称: getByCreateUserId
	 * 描述: 根据createUserId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateUserId(Long property) {
		return getSqlSession().selectList("MeetingPic.getByCreateUserId",property);
	}	
	
	/**
	 * 名称: getByCreateUserName
	 * 描述: 根据createUserName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateUserName(String property) {
		return getSqlSession().selectList("MeetingPic.getByCreateUserName",property);
	}	
	
	/**
	 * 名称: getByCreateDate
	 * 描述: 根据createDate查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateDate(Date property) {
		return getSqlSession().selectList("MeetingPic.getByCreateDate",property);
	}	
	
	/**
	 * 名称: getByPicStatus
	 * 描述: 根据picStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicStatus(String property) {
		return getSqlSession().selectList("MeetingPic.getByPicStatus",property);
	}	
	
	/**
	 * 名称: getByPicDel
	 * 描述: 根据picDel查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicDel(String property) {
		return getSqlSession().selectList("MeetingPic.getByPicDel",property);
	}	
	
	/**
	 * 名称: getByUpdateDate
	 * 描述: 根据updateDate查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByUpdateDate(Date property) {
		return getSqlSession().selectList("MeetingPic.getByUpdateDate",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingPic entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("MeetingPic.insert", entity);
		else 
			getSqlSession().update("MeetingPic.update", entity);
	}
	/**
	 * 名称: save
	 * 描述: 新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public Integer save(MeetingPic entity) {
		return getSqlSession().insert("MeetingPic.insert", entity);
	}
	/**
	 * 查询会议的首页图片
	 * @param list
	 * @return
	 */
	public List<MeetingPic> getMeetingFrontPage(List<Long> list) {
		return getSqlSession().selectList("MeetingPic.getMeetingFrontPage", list);
	}
}
