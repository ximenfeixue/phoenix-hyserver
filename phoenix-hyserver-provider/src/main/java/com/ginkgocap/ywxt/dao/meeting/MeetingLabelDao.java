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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Repository
public interface MeetingLabelDao{
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingLabel getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: getByLabelName
	 * 描述: 根据labelName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingLabel> getByLabelName(java.lang.String property) ;	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingLabel> getByCreateId(java.lang.Long property) ;	
	/**
	 * 名称: getByCreateIdAndLabelName
	 * 描述: 根据createId和标签名字查找标签是否存在
	  * @since  2014-10-29
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingLabel> getByCreateIdAndLabelName(java.lang.Long property,java.lang.String labelName);
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingLabel> getByCreateName(java.lang.String property) ;	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingLabel> getByCreateTime(java.util.Date property) ;	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingLabel entity) ;

}
