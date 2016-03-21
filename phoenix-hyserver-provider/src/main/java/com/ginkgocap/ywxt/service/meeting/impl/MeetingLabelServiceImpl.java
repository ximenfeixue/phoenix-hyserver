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

import com.ginkgocap.ywxt.dao.meeting.MeetingLabelDao;
import com.ginkgocap.ywxt.model.meeting.MeetingLabel;
import com.ginkgocap.ywxt.service.meeting.MeetingLabelService;

@Service
@Transactional
public class MeetingLabelServiceImpl implements MeetingLabelService{
	@Autowired
	private MeetingLabelDao meetingLabelDao;
/**
 * 名称: getById
 * 描述: 根据id查找
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(readOnly=true)
public MeetingLabel getById(Long property) {
	return meetingLabelDao.getById(property);
}
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
@Transactional(rollbackFor=Exception.class)
public void delete(Long property){
	 meetingLabelDao.delete(property);
}
	/**
	 * 名称: getByLabelName
	 * 描述: 根据labelName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingLabel> getByLabelName(java.lang.String property) {
		return meetingLabelDao.getByLabelName(property);
	}	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingLabel> getByCreateId(java.lang.Long property) {
		return meetingLabelDao.getByCreateId(property);
	}	
	/**
	 * 名称: getByCreateIdAndLabelName
	 * 描述: 根据createId和标签名字查找标签是否存在
	  * @since  2014-10-29
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingLabel> getByCreateIdAndLabelName(java.lang.Long property,java.lang.String labelName) {
		return meetingLabelDao.getByCreateIdAndLabelName(property,labelName);
	}	
	
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingLabel> getByCreateName(java.lang.String property) {
		return meetingLabelDao.getByCreateName(property);
	}	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingLabel> getByCreateTime(java.util.Date property) {
		return meetingLabelDao.getByCreateTime(property);
	}	
/**
 * 名称: saveOrUpdate
 * 描述: 修改或者新增
  * @since  2014-09-14
 * @author qingc
 */
	@Transactional(rollbackFor=Exception.class)
public void saveOrUpdate(MeetingLabel entity){
	 meetingLabelDao.saveOrUpdate(entity);
}
}