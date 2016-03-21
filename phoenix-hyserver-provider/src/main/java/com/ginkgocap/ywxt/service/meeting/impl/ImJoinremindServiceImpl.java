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

import com.ginkgocap.ywxt.dao.meeting.ImJoinremindDao;
import com.ginkgocap.ywxt.model.meeting.ImJoinremind;
import com.ginkgocap.ywxt.service.meeting.ImJoinremindService;

@Service
@Transactional
public class ImJoinremindServiceImpl implements ImJoinremindService{
	@Autowired
	private ImJoinremindDao imJoinremindDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImJoinremind getById(Long property) {
	return imJoinremindDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imJoinremindDao.delete(property);
}
	/**
	 * 名称: getByUserId
	 * 描述: 根据userId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJoinremind> getByUserId(java.lang.Integer property) {
		return imJoinremindDao.getByUserId(property);
	}	
	/**
	 * 名称: getByMucId
	 * 描述: 根据mucId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJoinremind> getByMucId(java.lang.Integer property) {
		return imJoinremindDao.getByMucId(property);
	}	
	/**
	 * 名称: getByType
	 * 描述: 根据type查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJoinremind> getByType(java.lang.Integer property) {
		return imJoinremindDao.getByType(property);
	}	
	/**
	 * 名称: getByOperatorId
	 * 描述: 根据operatorId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJoinremind> getByOperatorId(java.lang.Integer property) {
		return imJoinremindDao.getByOperatorId(property);
	}	
	/**
	 * 名称: getByOperatorDate
	 * 描述: 根据operatorDate查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJoinremind> getByOperatorDate(java.util.Date property) {
		return imJoinremindDao.getByOperatorDate(property);
	}	
	/**
	 * 名称: getByStatus
	 * 描述: 根据status查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJoinremind> getByStatus(java.lang.String property) {
		return imJoinremindDao.getByStatus(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImJoinremind entity){
	 imJoinremindDao.saveOrUpdate(entity);
}
}