/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.user.model.User;

public interface MeetingMemberService {
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public MeetingMember getById(Long property);
	/**
	 * 批量查询会议成员
	 * @param idList
	 * @return
	 */
	public List<MeetingMember> getByIdList(List<Long> idList);
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberId(java.lang.Long property);
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMeetingId(java.lang.Long property);
	/**
	 * 名称: getVisitantByMeetingId
	 * 描述: 根据meetingId查找嘉宾
	 * @since  2014-09-18
	 * @author qingc
	 */
	public List<MeetingMember> getVisitantByMeetingId(Long property);
	/**
	 * 名称: getByMeetingIdAndID
	 * 描述: 根据meetingId和成员memberId查找
	 * @since  2014-09-18
	 * @author qingc
	 */
	public List<MeetingMember> getByMeetingIdAndMemberId(Long meetingId,Long memberId);
	/**
	 * 名称: getByMemberName
	 * 描述: 根据memberName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberName(java.lang.String property);
	/**
	 * 名称: getByMemberPhoto
	 * 描述: 根据memberPhoto查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberPhoto(java.lang.String property);
	/**
	 * 名称: getByMemberMeetStatus
	 * 描述: 根据memberMeetStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberMeetStatus(java.lang.Integer property);
	/**
	 * 名称: getByAttendMeetStatus
	 * 描述: 根据attendMeetStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByAttendMeetStatus(java.lang.Integer property);
	/**
	 * 名称: getByAttendMeetType
	 * 描述: 根据attendMeetType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByAttendMeetType(java.lang.Integer property);
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 * @throws Exception 
	 */
	public void saveOrUpdate(MeetingMember entity) throws Exception;
	/**
	 * 名称: signUp
	 * 描述: 报名
	 * @since  2014-09-14
	 * @author qingc
	 * @throws Exception 
	 */
	public void signUp(Meeting meeting,MeetingMember meetingMember,User user) throws Exception;
	/**
	 * 名称: signUpReview
	 * 描述: 报名审核 
	 * @since  2014-09-14
	 * @author qingc
	 * @throws Exception 
	 */
	public void signUpReview(Integer status,Meeting meeting,MeetingMember meetingMember,User user) throws Exception;
	/**
	 * 名称: changeAttendMeetStatus
	 * 描述: 改变成员参会状态 ：0.未答复 1接受邀请2拒绝邀请， 5取消报名
	 * @since  2014-09-18
	 * @author qingc
	 * @throws Exception 
	 */
	public void changeAttendMeetStatus(Long meetingId,Long memberId,Integer type,User user) throws Exception;
	/**
	 * 名称: changeMyMemberMeetStatus
	 * 描述: 改变成员参会状态 ：0.归档 1 删除  * @since  2014-09-18
	 * @author qingc
	 * @throws Exception 
	 */
	public void changeMyMemberMeetStatus(Long meetingId,Long memberId,Integer type) throws Exception;
	
	/**
	 * 名称: getSignUpMemberCount
	 * 描述:获取会议报名人数 
	 * @since  2014-09-18
	 * @author qingc
	 */
	public Integer getSignUpMemberCount(Long meetingId);
	/**
	 * 批量删除我参加的会议
	 * @param attendIdList
	 */
	public void deleteAttendMeetingBatch(List<Long> attendIdList);
	/**
	 * 获取参会人数
	 * @param meetingId
	 * @return
	 */
	Integer getAttendMeetingCount(Long meetingId);

}