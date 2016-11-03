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

import com.ginkgocap.ywxt.dao.meeting.ImChatmessageDao;
import com.ginkgocap.ywxt.model.meeting.ImChatmessage;
import com.ginkgocap.ywxt.service.meeting.ImChatmessageService;

@Service
@Transactional
public class ImChatmessageServiceImpl implements ImChatmessageService{
	@Autowired
	private ImChatmessageDao imChatmessageDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImChatmessage getById(Long property) {
	return imChatmessageDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imChatmessageDao.delete(property);
}
	/**
	 * 名称: getByUserId1
	 * 描述: 根据userId1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByUserId1(java.lang.Integer property) {
		return imChatmessageDao.getByUserId1(property);
	}	
	/**
	 * 名称: getByUserId2
	 * 描述: 根据userId2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByUserId2(java.lang.Integer property) {
		return imChatmessageDao.getByUserId2(property);
	}	
	/**
	 * 名称: getByUserId1ReadStatus
	 * 描述: 根据userId1ReadStatus查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByUserId1ReadStatus(java.lang.String property) {
		return imChatmessageDao.getByUserId1ReadStatus(property);
	}	
	/**
	 * 名称: getByUserId2ReadStatus
	 * 描述: 根据userId2ReadStatus查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByUserId2ReadStatus(java.lang.String property) {
		return imChatmessageDao.getByUserId2ReadStatus(property);
	}	
	/**
	 * 名称: getBySenderId
	 * 描述: 根据senderId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getBySenderId(java.lang.Integer property) {
		return imChatmessageDao.getBySenderId(property);
	}	
	/**
	 * 名称: getByMsg
	 * 描述: 根据msg查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByMsg(java.lang.String property) {
		return imChatmessageDao.getByMsg(property);
	}	
	/**
	 * 名称: getByMsgType
	 * 描述: 根据msgType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByMsgType(java.lang.Integer property) {
		return imChatmessageDao.getByMsgType(property);
	}	
	/**
	 * 名称: getByMessageId
	 * 描述: 根据messageId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByMessageId(java.lang.String property) {
		return imChatmessageDao.getByMessageId(property);
	}	
	/**
	 * 名称: getByTime
	 * 描述: 根据time查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByTime(java.util.Date property) {
		return imChatmessageDao.getByTime(property);
	}	
	/**
	 * 名称: getByJtFileUrl
	 * 描述: 根据jtFileUrl查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileUrl(java.lang.String property) {
		return imChatmessageDao.getByJtFileUrl(property);
	}	
	/**
	 * 名称: getByJtFileSuffixName
	 * 描述: 根据jtFileSuffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileSuffixName(java.lang.String property) {
		return imChatmessageDao.getByJtFileSuffixName(property);
	}	
	/**
	 * 名称: getByJtFileType
	 * 描述: 根据jtFileType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileType(java.lang.Integer property) {
		return imChatmessageDao.getByJtFileType(property);
	}	
	/**
	 * 名称: getByJtFileName
	 * 描述: 根据jtFileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileName(java.lang.String property) {
		return imChatmessageDao.getByJtFileName(property);
	}	
	/**
	 * 名称: getByJtFileSize
	 * 描述: 根据jtFileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileSize(java.lang.Integer property) {
		return imChatmessageDao.getByJtFileSize(property);
	}	
	/**
	 * 名称: getByJtFileModuleType
	 * 描述: 根据jtFileModuleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileModuleType(java.lang.Integer property) {
		return imChatmessageDao.getByJtFileModuleType(property);
	}	
	/**
	 * 名称: getByJtFileTaskId
	 * 描述: 根据jtFileTaskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileTaskId(java.lang.String property) {
		return imChatmessageDao.getByJtFileTaskId(property);
	}	
	/**
	 * 名称: getByJtFileReserved1
	 * 描述: 根据jtFileReserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileReserved1(java.lang.String property) {
		return imChatmessageDao.getByJtFileReserved1(property);
	}	
	/**
	 * 名称: getByJtFileReserved2
	 * 描述: 根据jtFileReserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileReserved2(java.lang.String property) {
		return imChatmessageDao.getByJtFileReserved2(property);
	}	
	/**
	 * 名称: getByJtFileReserved3
	 * 描述: 根据jtFileReserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getByJtFileReserved3(java.lang.String property) {
		return imChatmessageDao.getByJtFileReserved3(property);
	}	
	/**
	 * 名称: getBySequence
	 * 描述: 根据sequence查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImChatmessage> getBySequence(java.lang.Integer property) {
		return imChatmessageDao.getBySequence(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImChatmessage entity){
	 imChatmessageDao.saveOrUpdate(entity);
}
}