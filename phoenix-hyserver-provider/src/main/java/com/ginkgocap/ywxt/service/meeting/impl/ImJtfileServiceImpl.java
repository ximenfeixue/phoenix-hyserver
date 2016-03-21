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

import com.ginkgocap.ywxt.dao.meeting.ImJtfileDao;
import com.ginkgocap.ywxt.model.meeting.ImJtfile;
import com.ginkgocap.ywxt.service.meeting.ImJtfileService;

@Service
@Transactional
public class ImJtfileServiceImpl implements ImJtfileService{
	@Autowired
	private ImJtfileDao imJtfileDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImJtfile getById(Long property) {
	return imJtfileDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imJtfileDao.delete(property);
}
	/**
	 * 名称: getByParentId
	 * 描述: 根据parentId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByParentId(java.lang.Integer property) {
		return imJtfileDao.getByParentId(property);
	}	
	/**
	 * 名称: getByParentType
	 * 描述: 根据parentType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByParentType(java.lang.Integer property) {
		return imJtfileDao.getByParentType(property);
	}	
	/**
	 * 名称: getByUrl
	 * 描述: 根据url查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByUrl(java.lang.String property) {
		return imJtfileDao.getByUrl(property);
	}	
	/**
	 * 名称: getBySuffixName
	 * 描述: 根据suffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getBySuffixName(java.lang.String property) {
		return imJtfileDao.getBySuffixName(property);
	}	
	/**
	 * 名称: getByType
	 * 描述: 根据type查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByType(java.lang.Integer property) {
		return imJtfileDao.getByType(property);
	}	
	/**
	 * 名称: getByFileName
	 * 描述: 根据fileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByFileName(java.lang.String property) {
		return imJtfileDao.getByFileName(property);
	}	
	/**
	 * 名称: getByFileSize
	 * 描述: 根据fileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByFileSize(java.lang.Integer property) {
		return imJtfileDao.getByFileSize(property);
	}	
	/**
	 * 名称: getByModuleType
	 * 描述: 根据moduleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByModuleType(java.lang.Integer property) {
		return imJtfileDao.getByModuleType(property);
	}	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByTaskId(java.lang.String property) {
		return imJtfileDao.getByTaskId(property);
	}	
	/**
	 * 名称: getByReserved1
	 * 描述: 根据reserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByReserved1(java.lang.String property) {
		return imJtfileDao.getByReserved1(property);
	}	
	/**
	 * 名称: getByReserved2
	 * 描述: 根据reserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByReserved2(java.lang.String property) {
		return imJtfileDao.getByReserved2(property);
	}	
	/**
	 * 名称: getByReserved3
	 * 描述: 根据reserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImJtfile> getByReserved3(java.lang.String property) {
		return imJtfileDao.getByReserved3(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImJtfile entity){
	 imJtfileDao.saveOrUpdate(entity);
}
}