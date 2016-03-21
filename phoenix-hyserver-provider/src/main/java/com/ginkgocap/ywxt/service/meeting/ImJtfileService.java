/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.ImJtfile;

public interface ImJtfileService {
/**
 * 名称: getById
 * 描述: 根据id查找
 * @since  2014-09-14
 * @author qingc
 */
public ImJtfile getById(Long property) ;
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
public void delete(Long property) ;
	/**
	 * 名称: getByParentId
	 * 描述: 根据parentId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByParentId(java.lang.Integer property);	
	/**
	 * 名称: getByParentType
	 * 描述: 根据parentType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByParentType(java.lang.Integer property);	
	/**
	 * 名称: getByUrl
	 * 描述: 根据url查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByUrl(java.lang.String property);	
	/**
	 * 名称: getBySuffixName
	 * 描述: 根据suffixName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getBySuffixName(java.lang.String property);	
	/**
	 * 名称: getByType
	 * 描述: 根据type查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByType(java.lang.Integer property);	
	/**
	 * 名称: getByFileName
	 * 描述: 根据fileName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByFileName(java.lang.String property);	
	/**
	 * 名称: getByFileSize
	 * 描述: 根据fileSize查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByFileSize(java.lang.Integer property);	
	/**
	 * 名称: getByModuleType
	 * 描述: 根据moduleType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByModuleType(java.lang.Integer property);	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByTaskId(java.lang.String property);	
	/**
	 * 名称: getByReserved1
	 * 描述: 根据reserved1查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByReserved1(java.lang.String property);	
	/**
	 * 名称: getByReserved2
	 * 描述: 根据reserved2查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByReserved2(java.lang.String property);	
	/**
	 * 名称: getByReserved3
	 * 描述: 根据reserved3查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<ImJtfile> getByReserved3(java.lang.String property);	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
public void saveOrUpdate(ImJtfile entity) ;
}