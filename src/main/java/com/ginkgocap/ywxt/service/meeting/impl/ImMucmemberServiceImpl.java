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

import com.ginkgocap.ywxt.dao.meeting.ImMucmemberDao;
import com.ginkgocap.ywxt.model.meeting.ImMucmember;
import com.ginkgocap.ywxt.service.meeting.ImMucmemberService;

@Service
@Transactional
public class ImMucmemberServiceImpl implements ImMucmemberService{
	@Autowired
	private ImMucmemberDao imMucmemberDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImMucmember getById(Long property) {
	return imMucmemberDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imMucmemberDao.delete(property);
}
	/**
	 * 名称: getByMucid
	 * 描述: 根据mucid查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmember> getByMucid(java.lang.Integer property) {
		return imMucmemberDao.getByMucid(property);
	}	
	/**
	 * 名称: getByUserId
	 * 描述: 根据userId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmember> getByUserId(java.lang.Integer property) {
		return imMucmemberDao.getByUserId(property);
	}	
	/**
	 * 名称: getByCompereType
	 * 描述: 根据compereType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmember> getByCompereType(java.lang.Integer property) {
		return imMucmemberDao.getByCompereType(property);
	}	
	/**
	 * 名称: getByJoinTime
	 * 描述: 根据joinTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmember> getByJoinTime(java.util.Date property) {
		return imMucmemberDao.getByJoinTime(property);
	}	
	/**
	 * 名称: getByExitTime
	 * 描述: 根据exitTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmember> getByExitTime(java.util.Date property) {
		return imMucmemberDao.getByExitTime(property);
	}	
	/**
	 * 名称: getByNotifyType
	 * 描述: 根据notifyType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmember> getByNotifyType(java.lang.Integer property) {
		return imMucmemberDao.getByNotifyType(property);
	}	
	/**
	 * 名称: getByNewMessageCount
	 * 描述: 根据newMessageCount查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmember> getByNewMessageCount(java.lang.Integer property) {
		return imMucmemberDao.getByNewMessageCount(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImMucmember entity){
	 imMucmemberDao.saveOrUpdate(entity);
}
}