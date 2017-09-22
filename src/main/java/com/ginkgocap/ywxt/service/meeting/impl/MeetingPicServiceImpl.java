/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.List;

import com.ginkgocap.parasol.file.model.FileIndex;
import com.ginkgocap.parasol.file.service.FileIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingPicDao;
// import com.ginkgocap.ywxt.file.model.FileIndex;
// import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.service.meeting.MeetingPicService;
import com.ginkgocap.ywxt.utils.Utils;

@Service
@Transactional
public class MeetingPicServiceImpl implements MeetingPicService{
	@Autowired
	private MeetingPicDao meetingPicDao;
	@Autowired
	private FileIndexService fileIndexService;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public MeetingPic getById(Long property) {
	return meetingPicDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 meetingPicDao.delete(property);
}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByMeetingId(java.lang.Long property) {
		return meetingPicDao.getByMeetingId(property);
	}	
	/**
	 * 名称: getCoverByMeetingId
	 * 描述: 根据会议id查找封面
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public MeetingPic getCoverByMeetingId(java.lang.Long property){
		return meetingPicDao.getCoverByMeetingId(property);
	}
	/**
	 * 名称: getByPicPath
	 * 描述: 根据picPath查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByPicPath(java.lang.String property) {
		return meetingPicDao.getByPicPath(property);
	}	
	/**
	 * 名称: getByPicName
	 * 描述: 根据picName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByPicName(java.lang.String property) {
		return meetingPicDao.getByPicName(property);
	}	
	/**
	 * 名称: getByPicRealName
	 * 描述: 根据picRealName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByPicRealName(java.lang.String property) {
		return meetingPicDao.getByPicRealName(property);
	}	
	/**
	 * 名称: getByPicDesc
	 * 描述: 根据picDesc查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByPicDesc(java.lang.String property) {
		return meetingPicDao.getByPicDesc(property);
	}	
	/**
	 * 名称: getByIshomePage
	 * 描述: 根据ishomePage查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByIshomePage(java.lang.String property) {
		return meetingPicDao.getByIshomePage(property);
	}	
	/**
	 * 名称: getByCreateUserId
	 * 描述: 根据createUserId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByCreateUserId(java.lang.Long property) {
		return meetingPicDao.getByCreateUserId(property);
	}	
	/**
	 * 名称: getByCreateUserName
	 * 描述: 根据createUserName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByCreateUserName(java.lang.String property) {
		return meetingPicDao.getByCreateUserName(property);
	}	
	/**
	 * 名称: getByCreateDate
	 * 描述: 根据createDate查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByCreateDate(java.util.Date property) {
		return meetingPicDao.getByCreateDate(property);
	}	
	/**
	 * 名称: getByPicStatus
	 * 描述: 根据picStatus查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByPicStatus(java.lang.String property) {
		return meetingPicDao.getByPicStatus(property);
	}	
	/**
	 * 名称: getByPicDel
	 * 描述: 根据picDel查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByPicDel(java.lang.String property) {
		return meetingPicDao.getByPicDel(property);
	}	
	/**
	 * 名称: getByUpdateDate
	 * 描述: 根据updateDate查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingPic> getByUpdateDate(java.util.Date property) {
		return meetingPicDao.getByUpdateDate(property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingPic entity){
		 meetingPicDao.saveOrUpdate(entity);
	}
	/**
	 * 名称: save
	 * 描述: 新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer save(MeetingPic entity) {
		
		return meetingPicDao.save(entity);
	}
	/**
	 * 名称: removeMeetingPic
	 * 描述: 移除会议图片
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public boolean removeMeetingPic(MeetingPic meetingPic,FileIndex fileIndex)throws Exception{
		boolean flag=false;
		try {
			if(!Utils.isNullOrEmpty(meetingPic)&&!Utils.isNullOrEmpty(meetingPic.getId())){
				Long meetingId=meetingPic.getMeetingId();
				// 用来标记删除的图片是否封面
				boolean isDeleteHomepageFlag=false;
				if(!Utils.isNullOrEmpty(meetingPic.getIshomePage())){
					// 图片为封面是置为true
					if(meetingPic.getIshomePage()==1){
						isDeleteHomepageFlag=true;
					}
				}
				this.delete(meetingPic.getId());
				// 重新设置封面
				if(isDeleteHomepageFlag){
					List<MeetingPic> list=meetingPicDao.getByMeetingId(meetingId);
					if(!Utils.isNullOrEmpty(list)){
						MeetingPic meetingPicTemp=list.get(0);
						if(!Utils.isNullOrEmpty(meetingPicTemp)){
							// 设置为封面
							meetingPicTemp.setIshomePage(1);
							meetingPicDao.saveOrUpdate(meetingPicTemp);
						}
					}
				}
			}
			if(!Utils.isNullOrEmpty(fileIndex)&&!Utils.isNullOrEmpty(fileIndex.getId())){
				fileIndexService.deleteFileIndexById(meetingPic.getCreateUserId(),fileIndex.getId());
			}
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	/**
	 * 根据模块ID查询图片
	 * @param moduleId
	 * @param moduleType 模块类型 1：会议 2：议题 3：笔记
	 * @return
	 */
	public List<MeetingPic> getByModuleId(Long moduleId, Long moduleType) {
		return meetingPicDao.getByModuleId(moduleId, moduleType);
	}
	/**
	 * 根据模块ID删除图片
	 * @param moduleId
	 * @param moduleType 模块类型 1：会议 2：议题 3：笔记
	 * @param listMeetingPicId
	 */
	public void deleteByModuleId(Long moduleId, Long moduleType, List<Long> listMeetingPicId) {
		meetingPicDao.deleteByModuleId(moduleId, moduleType, listMeetingPicId);
	}
}