/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.ImMucinfoDao;
import com.ginkgocap.ywxt.model.meeting.ImMucinfo;
import com.ginkgocap.ywxt.service.meeting.ImMucinfoService;

@Service
@Transactional
public class ImMucinfoServiceImpl implements ImMucinfoService{
	@Autowired
	private ImMucinfoDao imMucinfoDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImMucinfo getById(Long property) {
	return imMucinfoDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imMucinfoDao.delete(property);
}
	/**
	 * 名称: getByTitle
	 * 描述: 根据title查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByTitle(java.lang.String property) {
		return imMucinfoDao.getByTitle(property);
	}	
	/**
	 * 名称: getBySubject
	 * 描述: 根据subject查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getBySubject(java.lang.String property) {
		return imMucinfoDao.getBySubject(property);
	}	
	/**
	 * 名称: getByType
	 * 描述: 根据type查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByType(java.lang.Integer property) {
		return imMucinfoDao.getByType(property);
	}	
	/**
	 * 名称: getByOrganizationId
	 * 描述: 根据organizationId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByOrganizationId(java.lang.Long property) {
		return imMucinfoDao.getByOrganizationId(property);
	}	
	/**
	 * 名称: getByContent
	 * 描述: 根据content查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByContent(java.lang.String property) {
		return imMucinfoDao.getByContent(property);
	}	
	/**
	 * 名称: getByMax
	 * 描述: 根据max查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByMax(java.lang.Integer property) {
		return imMucinfoDao.getByMax(property);
	}	
	/**
	 * 名称: getByStickType
	 * 描述: 根据stickType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByStickType(java.lang.Integer property) {
		return imMucinfoDao.getByStickType(property);
	}	
	/**
	 * 名称: getByAutoSaveType
	 * 描述: 根据autoSaveType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByAutoSaveType(java.lang.Integer property) {
		return imMucinfoDao.getByAutoSaveType(property);
	}	
	/**
	 * 名称: getByTime
	 * 描述: 根据time查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByTime(java.util.Date property) {
		return imMucinfoDao.getByTime(property);
	}	
	/**
	 * 名称: getByStatus
	 * 描述: 根据status查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByStatus(java.lang.String property) {
		return imMucinfoDao.getByStatus(property);
	}	
	/**
	 * 名称: getByCreateUserId
	 * 描述: 根据createUserId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucinfo> getByCreateUserId(java.lang.Integer property) {
		return imMucinfoDao.getByCreateUserId(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImMucinfo entity){
	 imMucinfoDao.saveOrUpdate(entity);
}
}