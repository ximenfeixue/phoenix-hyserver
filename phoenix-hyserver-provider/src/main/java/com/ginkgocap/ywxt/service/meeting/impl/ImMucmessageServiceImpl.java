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

import com.ginkgocap.ywxt.dao.meeting.ImMucmessageDao;
import com.ginkgocap.ywxt.model.meeting.ImMucmessage;
import com.ginkgocap.ywxt.service.meeting.ImMucmessageService;

@Service
@Transactional
public class ImMucmessageServiceImpl implements ImMucmessageService{
	@Autowired
	private ImMucmessageDao imMucmessageDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImMucmessage getById(Long property) {
	return imMucmessageDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imMucmessageDao.delete(property);
}
	/**
	 * 名称: getByMucid
	 * 描述: 根据mucid查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByMucid(java.lang.Integer property) {
		return imMucmessageDao.getByMucid(property);
	}	
	/**
	 * 名称: getBySenderId
	 * 描述: 根据senderId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getBySenderId(java.lang.Integer property) {
		return imMucmessageDao.getBySenderId(property);
	}	
	/**
	 * 名称: getBySenderType
	 * 描述: 根据senderType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getBySenderType(java.lang.Integer property) {
		return imMucmessageDao.getBySenderType(property);
	}	
	/**
	 * 名称: getByMsg
	 * 描述: 根据msg查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByMsg(java.lang.String property) {
		return imMucmessageDao.getByMsg(property);
	}	
	/**
	 * 名称: getByMsgType
	 * 描述: 根据msgType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByMsgType(java.lang.Integer property) {
		return imMucmessageDao.getByMsgType(property);
	}	
	/**
	 * 名称: getByTime
	 * 描述: 根据time查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByTime(java.util.Date property) {
		return imMucmessageDao.getByTime(property);
	}	
	/**
	 * 名称: getByMessageId
	 * 描述: 根据messageId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByMessageId(java.lang.String property) {
		return imMucmessageDao.getByMessageId(property);
	}	
	/**
	 * 名称: getByJtFileUrl
	 * 描述: 根据jtFileUrl查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileUrl(java.lang.String property) {
		return imMucmessageDao.getByJtFileUrl(property);
	}	
	/**
	 * 名称: getByJtFileSuffixName
	 * 描述: 根据jtFileSuffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileSuffixName(java.lang.String property) {
		return imMucmessageDao.getByJtFileSuffixName(property);
	}	
	/**
	 * 名称: getByJtFileType
	 * 描述: 根据jtFileType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileType(java.lang.String property) {
		return imMucmessageDao.getByJtFileType(property);
	}	
	/**
	 * 名称: getByJtFileName
	 * 描述: 根据jtFileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileName(java.lang.String property) {
		return imMucmessageDao.getByJtFileName(property);
	}	
	/**
	 * 名称: getByJtFileSize
	 * 描述: 根据jtFileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileSize(java.lang.Integer property) {
		return imMucmessageDao.getByJtFileSize(property);
	}	
	/**
	 * 名称: getByJtFileModuleType
	 * 描述: 根据jtFileModuleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileModuleType(java.lang.Integer property) {
		return imMucmessageDao.getByJtFileModuleType(property);
	}	
	/**
	 * 名称: getByJtFileTaskId
	 * 描述: 根据jtFileTaskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileTaskId(java.lang.String property) {
		return imMucmessageDao.getByJtFileTaskId(property);
	}	
	/**
	 * 名称: getByJtFileReserved1
	 * 描述: 根据jtFileReserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileReserved1(java.lang.String property) {
		return imMucmessageDao.getByJtFileReserved1(property);
	}	
	/**
	 * 名称: getByJtFileReserved2
	 * 描述: 根据jtFileReserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileReserved2(java.lang.String property) {
		return imMucmessageDao.getByJtFileReserved2(property);
	}	
	/**
	 * 名称: getByJtFileReserved3
	 * 描述: 根据jtFileReserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getByJtFileReserved3(java.lang.String property) {
		return imMucmessageDao.getByJtFileReserved3(property);
	}	
	/**
	 * 名称: getBySequence
	 * 描述: 根据sequence查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImMucmessage> getBySequence(java.lang.Integer property) {
		return imMucmessageDao.getBySequence(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImMucmessage entity){
	 imMucmessageDao.saveOrUpdate(entity);
}
}