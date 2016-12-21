/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.model.meeting.MeetingSignLabel;

@Repository
public interface MeetingSignLabelDao{
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingSignLabel getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	public void deleteByMeetingId(Long property) ;
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByMeetingId(java.lang.Long property) ;	
	/**
	 * 名称: getByLabelName
	 * 描述: 根据labelName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByLabelName(java.lang.String property) ;	
	/**
	 * 名称: getByIsCustom
	 * 描述: 根据isCustom查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByIsCustom(Integer property) ;	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByCreateId(java.lang.Long property) ;	
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByCreateName(java.lang.String property) ;	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabel> getByCreateTime(java.util.Date property) ;	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingSignLabel entity) ;

}
