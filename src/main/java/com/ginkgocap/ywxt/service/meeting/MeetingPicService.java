/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

// import com.ginkgocap.ywxt.file.model.FileIndex;
import com.ginkgocap.parasol.file.model.FileIndex;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;

public interface MeetingPicService {
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
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByMeetingId(java.lang.Long property);	
	/**
	 * 名称: getCoverByMeetingId
	 * 描述: 根据会议id查找封面
	  * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingPic getCoverByMeetingId(java.lang.Long property);	
	/**
	 * 名称: getByPicPath
	 * 描述: 根据picPath查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicPath(java.lang.String property);	
	/**
	 * 名称: getByPicName
	 * 描述: 根据picName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicName(java.lang.String property);	
	/**
	 * 名称: getByPicRealName
	 * 描述: 根据picRealName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicRealName(java.lang.String property);	
	/**
	 * 名称: getByPicDesc
	 * 描述: 根据picDesc查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicDesc(java.lang.String property);	
	/**
	 * 名称: getByIshomePage
	 * 描述: 根据ishomePage查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByIshomePage(java.lang.String property);	
	/**
	 * 名称: getByCreateUserId
	 * 描述: 根据createUserId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateUserId(java.lang.Long property);	
	/**
	 * 名称: getByCreateUserName
	 * 描述: 根据createUserName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateUserName(java.lang.String property);	
	/**
	 * 名称: getByCreateDate
	 * 描述: 根据createDate查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByCreateDate(java.util.Date property);	
	/**
	 * 名称: getByPicStatus
	 * 描述: 根据picStatus查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicStatus(java.lang.String property);	
	/**
	 * 名称: getByPicDel
	 * 描述: 根据picDel查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByPicDel(java.lang.String property);	
	/**
	 * 名称: getByUpdateDate
	 * 描述: 根据updateDate查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingPic> getByUpdateDate(java.util.Date property);	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingPic entity) ;
	/**
	 * 名称: save
	 * 描述: 新增
	  * @since  2014-09-14
	 * @author qingc
	 * @return 
	 */
	public Integer save(MeetingPic entity) ;
	/**
	 * 名称: removeMeetingPic
	 * 描述: 移除会议图片
	  * @since  2014-09-14
	 * @author qingc
	 * @throws Exception 
	 */
	public boolean removeMeetingPic(MeetingPic meetingPic,FileIndex fileIndex) throws Exception;
	/**
	 * 根据模块ID查询图片
	 * @param moduleId
	 * @param moduleType 模块类型 1：会议 2：议题 3：笔记
	 * @return
	 */
	public List<MeetingPic> getByModuleId(Long moduleId, Long moduleType);
	/**
	 * 根据模块ID删除图片
	 * @param moduleId
	 * @param moduleType 模块类型 1：会议 2：议题 3：笔记
	 * @param listMeetingPicId
	 */
	public void deleteByModuleId(Long moduleId, Long moduleType, List<Long> listMeetingPicId);
}