/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.Date;
import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingDataQuery;

public interface MeetingDataService {
	/**
	 * 名称: getById 描述: 根据id查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public MeetingData getById(Long property);

	/**
	 * 名称: delete 描述: 根据id删除
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void delete(Long property);

	/**
	 * 名称: getByMeetingId 描述: 根据meetingId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByMeetingId(java.lang.Long property);
	/**
	 * 批量查询会议相关知识、需求
	 * @param meetingIdList
	 * @return
	 */
	public List<MeetingData> getByMeetingIdList(List<Long> meetingIdList);
	/**
	 * 名称: getByDataName 描述: 根据dataName查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataName(java.lang.String property);

	/**
	 * 名称: getByDataId 描述: 根据dataId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataId(java.lang.Long property);

	/**
	 * 名称: getByDataType 描述: 根据dataType查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataType(java.lang.Integer property);

	/**
	 * 名称: getByDataReqType 描述: 根据dataReqType查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataReqType(java.lang.Integer property);

	/**
	 * 名称: getByDataUrl 描述: 根据dataUrl查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataUrl(java.lang.String property);

	/**
	 * 名称: saveOrUpdate 描述: 修改或者新增
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingData entity);

	/**
	 * 名称: getRequirementByMeetingId 描述: 获取会议分享的需求
	 * 
	 * @since 2014-09-18
	 * @author qingc
	 */
	public List<MeetingData> getRequirementByMeetingId(Long meetingId);

	/**
	 * 名称: getKnowledgeByMeetingId 描述: 获取会议分享的知识
	 * 
	 * @since 2014-09-18
	 * @author qingc
	 */
	public List<MeetingData> getKnowledgeByMeetingId(Long meetingId);
	/**
	 * 名称: getMyMeetingData
	 * 描述: 获取我的会议资料
	 * @since  2014-09-19
	 * @author qingc
	 */
	public List<MeetingDataQuery> getMyMeetingData(Date beginDate,Date endDate,Long memberId);
}