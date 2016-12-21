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

import com.ginkgocap.ywxt.dao.meeting.NoticeFieldDao;
import com.ginkgocap.ywxt.model.meeting.NoticeField;
import com.ginkgocap.ywxt.service.meeting.NoticeFieldService;

@Service
@Transactional
public class NoticeFieldServiceImpl implements NoticeFieldService{
	@Autowired
	private NoticeFieldDao noticeFieldDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public NoticeField getById(Long property) {
	return noticeFieldDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 noticeFieldDao.delete(property);
}
	/**
	 * 名称: getByNoticeId
	 * 描述: 根据noticeId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<String> getByNoticeId(java.lang.Long property) {
		return noticeFieldDao.getByNoticeId(property);
	}	
	/**
	 * 名称: getByField
	 * 描述: 根据field查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<NoticeField> getByField(java.lang.String property) {
		return noticeFieldDao.getByField(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(NoticeField entity){
	 noticeFieldDao.saveOrUpdate(entity);
}
}