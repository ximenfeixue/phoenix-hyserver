/**

 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.model.meeting.MeetingPic;

@Repository
public interface MeetingPicDao{
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingPic getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: deleteByMeetingId
	 * 描述: 根据meetingId删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByMeetingId(Long property);
	/**
	 * 根据模块ID删除
	 * @param moduleId 模块ID
	 * @param moduleType 模块类型 1：会议 2：议题 3：笔记
	 */
	public void deleteByModuleId(Long moduleId, Long moduleType, List<Long> listMeetingPicId);
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByMeetingId(Long property);
	/**
	 * 根据模块ID查询图片
	 * @param moduleId 模块ID
	 * @param moduleId 模块类型 1：会议 2：议题 3：笔记
	 * @return 图片列表
	 */
	public List<MeetingPic> getByModuleId(Long moduleId, Long moduleType);
	/**
	 * 名称: getCoverByMeetingId
	 * 描述: 根据会议id查找封面
	 * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingPic getCoverByMeetingId(Long property);
	/**
	 * 名称: getByPicPath
	 * 描述: 根据picPath查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	
	public List<MeetingPic> getByPicPath(String property) ;	
	/**
	 * 名称: getByPicName
	 * 描述: 根据picName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicName(String property) ;	
	/**
	 * 名称: getByPicRealName
	 * 描述: 根据picRealName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicRealName(String property) ;	
	/**
	 * 名称: getByPicDesc
	 * 描述: 根据picDesc查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicDesc(String property) ;	
	/**
	 * 名称: getByFileIndexId
	 * 描述: 根据taskId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByFileIndexId(String taskId) ;	
	/**
	 * 名称: getByIshomePage
	 * 描述: 根据ishomePage查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByIshomePage(String property) ;	
	/**
	 * 名称: getByCreateUserId
	 * 描述: 根据createUserId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateUserId(Long property) ;	
	/**
	 * 名称: getByCreateUserName
	 * 描述: 根据createUserName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateUserName(String property) ;	
	/**
	 * 名称: getByCreateDate
	 * 描述: 根据createDate查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateDate(Date property) ;	
	/**
	 * 名称: getByPicStatus
	 * 描述: 根据picStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicStatus(String property) ;	
	/**
	 * 名称: getByPicDel
	 * 描述: 根据picDel查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicDel(String property) ;	
	/**
	 * 名称: getByUpdateDate
	 * 描述: 根据updateDate查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByUpdateDate(Date property) ;	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingPic entity) ;
	/**
	 * 描述: 新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public Integer save(MeetingPic entity) ;
	/**
	 * 查询会议的首页图片
	 * @param list
	 * @return
	 */
	public List<MeetingPic> getMeetingFrontPage(List<Long> list);

}
