/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.*;




import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.common.base.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Repository
public interface ImUserchannelDao{
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public ImUserchannel getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: getByUserId
	 * 描述: 根据userId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getByUserId(java.lang.Integer property) ;	
	/**
	 * 名称: getByChannelId
	 * 描述: 根据channelId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getByChannelId(java.lang.String property) ;	
	/**
	 * 名称: getBySecretKey
	 * 描述: 根据secretKey查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getBySecretKey(java.lang.String property) ;	
	/**
	 * 名称: getByBaiduUserId
	 * 描述: 根据baiduUserId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getByBaiduUserId(java.lang.String property) ;	
	/**
	 * 名称: getByChannelType
	 * 描述: 根据channelType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImUserchannel> getByChannelType(java.lang.Integer property) ;	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImUserchannel entity) ;

}
