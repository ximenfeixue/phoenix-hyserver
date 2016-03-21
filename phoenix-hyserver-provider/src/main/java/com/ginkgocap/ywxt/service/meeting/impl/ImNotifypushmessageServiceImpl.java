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

import com.ginkgocap.ywxt.dao.meeting.ImNotifypushmessageDao;
import com.ginkgocap.ywxt.model.meeting.ImNotifypushmessage;
import com.ginkgocap.ywxt.service.meeting.ImNotifypushmessageService;

@Service
@Transactional
public class ImNotifypushmessageServiceImpl implements ImNotifypushmessageService{
	@Autowired
	private ImNotifypushmessageDao imNotifypushmessageDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImNotifypushmessage getById(Long property) {
	return imNotifypushmessageDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imNotifypushmessageDao.delete(property);
}
	/**
	 * 名称: getByUserId
	 * 描述: 根据userId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImNotifypushmessage> getByUserId(java.lang.Integer property) {
		return imNotifypushmessageDao.getByUserId(property);
	}	
	/**
	 * 名称: getByNotifyType
	 * 描述: 根据notifyType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImNotifypushmessage> getByNotifyType(java.lang.Integer property) {
		return imNotifypushmessageDao.getByNotifyType(property);
	}	
	/**
	 * 名称: getByCount
	 * 描述: 根据count查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImNotifypushmessage> getByCount(java.lang.Long property) {
		return imNotifypushmessageDao.getByCount(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImNotifypushmessage entity){
	 imNotifypushmessageDao.saveOrUpdate(entity);
}
}