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

import com.ginkgocap.ywxt.dao.meeting.ImUserchannelDao;
import com.ginkgocap.ywxt.model.meeting.ImUserchannel;
import com.ginkgocap.ywxt.service.meeting.ImUserchannelService;

@Service
@Transactional
public class ImUserchannelServiceImpl implements ImUserchannelService{
	@Autowired
	private ImUserchannelDao imUserchannelDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public ImUserchannel getById(Long property) {
	return imUserchannelDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 imUserchannelDao.delete(property);
}
	/**
	 * 名称: getByUserId
	 * 描述: 根据userId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImUserchannel> getByUserId(java.lang.Integer property) {
		return imUserchannelDao.getByUserId(property);
	}	
	/**
	 * 名称: getByChannelId
	 * 描述: 根据channelId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImUserchannel> getByChannelId(java.lang.String property) {
		return imUserchannelDao.getByChannelId(property);
	}	
	/**
	 * 名称: getBySecretKey
	 * 描述: 根据secretKey查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImUserchannel> getBySecretKey(java.lang.String property) {
		return imUserchannelDao.getBySecretKey(property);
	}	
	/**
	 * 名称: getByBaiduUserId
	 * 描述: 根据baiduUserId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImUserchannel> getByBaiduUserId(java.lang.String property) {
		return imUserchannelDao.getByBaiduUserId(property);
	}	
	/**
	 * 名称: getByChannelType
	 * 描述: 根据channelType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<ImUserchannel> getByChannelType(java.lang.Integer property) {
		return imUserchannelDao.getByChannelType(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(ImUserchannel entity){
	 imUserchannelDao.saveOrUpdate(entity);
}
}