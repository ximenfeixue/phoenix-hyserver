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

import com.ginkgocap.ywxt.dao.meeting.ImSeqDao;
import com.ginkgocap.ywxt.model.meeting.ImSeq;
import com.ginkgocap.ywxt.service.meeting.ImSeqService;

@Service
@Transactional
public class ImSeqServiceImpl implements ImSeqService{
	@Autowired
	private ImSeqDao imSeqDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImSeq getById(Long property) {
	return imSeqDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imSeqDao.delete(property);
}
	/**
	 * 名称: getBySeq
	 * 描述: 根据seq查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImSeq> getBySeq(java.lang.Long property) {
		return imSeqDao.getBySeq(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImSeq entity){
	 imSeqDao.saveOrUpdate(entity);
}
}