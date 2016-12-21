/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingSignLabelData;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignLabelDataQuery;

public interface MeetingSignLabelDataService {
/**
 * 名称: getById
 * 描述: 根据id查找
 * @since  2014-09-14
 * @author qingc
 */
public MeetingSignLabelData getById(Long property) ;
/**
 * 名称: delete
 * 描述: 根据id删除
  * @since  2014-09-14
 * @author qingc
 */
public void delete(Long property) ;
	/**
	 * 名称: getByMslabelId
	 * 描述: 根据mslabelId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByMslabelId(java.lang.Long property);	
	/**
	 * 名称: getByLabelContent
	 * 描述: 根据labelContent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByLabelContent(java.lang.String property);	
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByMemberId(java.lang.Long property);	
	/**
	 * 名称: getByMemberName
	 * 描述: 根据memberName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByMemberName(java.lang.String property);	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByCreateId(java.lang.Long property);	
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByCreateName(java.lang.String property);	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByCreateTime(java.util.Date property);	
	/**
	 * 名称: getByMeetingIdAndMemberId
	 * 描述: 根据会议Id和用户Id获取用户必填信息
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelDataQuery> getByMeetingIdAndMemberId(Long meetingID,Long MemberId);	
	/**
	 * 名称: addBatchMeetingSignLabelData
	 * 描述: 批量插入用户报名必填信息
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void addBatchMeetingSignLabelData(List<MeetingSignLabelDataQuery> list) throws Exception;
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingSignLabelData entity) ;
	/**
	 * 根据会议ID和成员ID删除报名信息
	 * @param meetingId
	 * @param memberId
	 * @return
	 */
	public void deleteByMeetingIdAndMemberId(Long meetingId, Long memberId);
}