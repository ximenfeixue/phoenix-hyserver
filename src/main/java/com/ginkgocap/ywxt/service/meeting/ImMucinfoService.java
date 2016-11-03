/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.ImMucinfo;

public interface ImMucinfoService {
/**
 * 名称: getById
 * 描述: 根据id查找
 * @since  2014-09-14
 * @author qingc
 */
public ImMucinfo getById(Long property) ;
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
public void delete(Long property) ;
	/**
	 * 名称: getByTitle
	 * 描述: 根据title查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByTitle(java.lang.String property);	
	/**
	 * 名称: getBySubject
	 * 描述: 根据subject查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getBySubject(java.lang.String property);	
	/**
	 * 名称: getByType
	 * 描述: 根据type查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByType(java.lang.Integer property);	
	/**
	 * 名称: getByOrganizationId
	 * 描述: 根据organizationId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByOrganizationId(java.lang.Long property);	
	/**
	 * 名称: getByContent
	 * 描述: 根据content查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByContent(java.lang.String property);	
	/**
	 * 名称: getByMax
	 * 描述: 根据max查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByMax(java.lang.Integer property);	
	/**
	 * 名称: getByStickType
	 * 描述: 根据stickType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByStickType(java.lang.Integer property);	
	/**
	 * 名称: getByAutoSaveType
	 * 描述: 根据autoSaveType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByAutoSaveType(java.lang.Integer property);	
	/**
	 * 名称: getByTime
	 * 描述: 根据time查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByTime(java.util.Date property);	
	/**
	 * 名称: getByStatus
	 * 描述: 根据status查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByStatus(java.lang.String property);	
	/**
	 * 名称: getByCreateUserId
	 * 描述: 根据createUserId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImMucinfo> getByCreateUserId(java.lang.Integer property);	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
public void saveOrUpdate(ImMucinfo entity) ;
}