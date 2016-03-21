/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.*;

import javacommon.base.*;


import com.ginkgocap.ywxt.model.meeting.*;
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
public interface ImJoinremindDao{
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public ImJoinremind getById(Long property) ;
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
	public List<ImJoinremind> getByUserId(java.lang.Integer property) ;	
	/**
	 * 名称: getByMucId
	 * 描述: 根据mucId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJoinremind> getByMucId(java.lang.Integer property) ;	
	/**
	 * 名称: getByType
	 * 描述: 根据type查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJoinremind> getByType(java.lang.Integer property) ;	
	/**
	 * 名称: getByOperatorId
	 * 描述: 根据operatorId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJoinremind> getByOperatorId(java.lang.Integer property) ;	
	/**
	 * 名称: getByOperatorDate
	 * 描述: 根据operatorDate查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJoinremind> getByOperatorDate(java.util.Date property) ;	
	/**
	 * 名称: getByStatus
	 * 描述: 根据status查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJoinremind> getByStatus(java.lang.String property) ;	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(ImJoinremind entity) ;

}
