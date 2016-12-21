/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.NoticeField;

public interface NoticeFieldService {
/**
 * 名称: getById
 * 描述: 根据id查找
 * @since  2014-09-14
 * @author qingc
 */
public NoticeField getById(Long property) ;
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
public void delete(Long property) ;
	/**
	 * 名称: getByNoticeId
	 * 描述: 根据noticeId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<String> getByNoticeId(java.lang.Long property);	
	/**
	 * 名称: getByField
	 * 描述: 根据field查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<NoticeField> getByField(java.lang.String property);	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
public void saveOrUpdate(NoticeField entity) ;
}