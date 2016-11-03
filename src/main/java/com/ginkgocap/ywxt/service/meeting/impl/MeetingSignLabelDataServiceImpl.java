/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingSignLabelDataDao;
import com.ginkgocap.ywxt.model.meeting.MeetingSignLabelData;
import com.ginkgocap.ywxt.service.meeting.MeetingSignLabelDataService;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignLabelDataQuery;

@Service
@Transactional
public class MeetingSignLabelDataServiceImpl implements MeetingSignLabelDataService{
	@Autowired
	private MeetingSignLabelDataDao meetingSignLabelDataDao;
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public MeetingSignLabelData getById(Long property) {
		return meetingSignLabelDataDao.getById(property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long property){
		 meetingSignLabelDataDao.delete(property);
	}
	/**
	 * 名称: getByMslabelId
	 * 描述: 根据mslabelId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabelData> getByMslabelId(java.lang.Long property) {
		return meetingSignLabelDataDao.getByMslabelId(property);
	}	
	/**
	 * 名称: getByLabelContent
	 * 描述: 根据labelContent查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabelData> getByLabelContent(java.lang.String property) {
		return meetingSignLabelDataDao.getByLabelContent(property);
	}	
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabelData> getByMemberId(java.lang.Long property) {
		return meetingSignLabelDataDao.getByMemberId(property);
	}	
	/**
	 * 名称: getByMemberName
	 * 描述: 根据memberName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabelData> getByMemberName(java.lang.String property) {
		return meetingSignLabelDataDao.getByMemberName(property);
	}	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabelData> getByCreateId(java.lang.Long property) {
		return meetingSignLabelDataDao.getByCreateId(property);
	}	
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabelData> getByCreateName(java.lang.String property) {
		return meetingSignLabelDataDao.getByCreateName(property);
	}	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabelData> getByCreateTime(java.util.Date property) {
		return meetingSignLabelDataDao.getByCreateTime(property);
	}
	/**
	 * 名称: getByMeetingIdAndMemberId
	 * 描述: 根据会议Id和用户Id获取用户必填信息
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingSignLabelDataQuery> getByMeetingIdAndMemberId(Long meetingID,Long MemberId){
		return meetingSignLabelDataDao.getByMeetingIdAndMemberId(meetingID, MemberId);
	}
	/**
	 * 名称: addBatchMeetingSignLabelData
	 * 描述: 批量插入用户报名必填信息
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void addBatchMeetingSignLabelData(List<MeetingSignLabelDataQuery> list) throws Exception{
		for(MeetingSignLabelDataQuery meetingSignLabelDataQuery:list){
			// 批量插入报名用户信息数据
			if(!Utils.isNullOrEmpty(meetingSignLabelDataQuery)){
				MeetingSignLabelData meetingSignLabelData = new MeetingSignLabelData();
				meetingSignLabelDataQuery.setCreateTime(new Date());
				org.apache.commons.beanutils.BeanUtils.copyProperties(meetingSignLabelData, meetingSignLabelDataQuery);
				Utils.setPrimarykeyZoroToNull(meetingSignLabelData);
				meetingSignLabelDataDao.saveOrUpdate(meetingSignLabelData);
			}
		}
	}
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingSignLabelData entity){
		 meetingSignLabelDataDao.saveOrUpdate(entity);
	}
	/**
	 * 根据会议ID和成员ID删除报名信息
	 * @param meetingId
	 * @param memberId
	 * @return
	 */
	public void deleteByMeetingIdAndMemberId(Long meetingId, Long memberId) {
		meetingSignLabelDataDao.deleteByMeetingIdAndMemberId(meetingId, memberId);
	}
}