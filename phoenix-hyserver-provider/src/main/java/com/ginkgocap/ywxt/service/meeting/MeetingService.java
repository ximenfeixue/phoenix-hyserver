/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingVo;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.vo.query.meeting.BigDataQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingMemberListQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.ginkgocap.ywxt.vo.query.social.Social;

public interface MeetingService {
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public Meeting getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除我创建的会议
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property,Long memberId) throws Exception;
	/**
	 * 名称: getByMeetingName
	 * 描述: 根据meetingName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingName(java.lang.String property);	
	/**
	 * 名称: getByMeetingAddress
	 * 描述: 根据meetingAddress查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingAddress(java.lang.String property);	
	/**
	 * 名称: getByMeetingAddressPos
	 * 描述: 根据meetingAddressPos查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingAddressPos(java.lang.String property);	
	/**
	 * 名称: getByStartTime
	 * 描述: 根据startTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByStartTime(java.util.Date property);	
	/**
	 * 名称: getByEndTime
	 * 描述: 根据endTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByEndTime(java.util.Date property);	
	/**
	 * 名称: getByMeetingType
	 * 描述: 根据meetingType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingType(java.lang.Integer property);	
	/**
	 * 名称: getByMeetingStatus
	 * 描述: 根据meetingStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingStatus(java.lang.Integer property);	
	/**
	 * 名称: getByIsSecrecy
	 * 描述: 根据isSecrecy查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByIsSecrecy(java.lang.Boolean property);	
	/**
	 * 名称: getByMemberCount
	 * 描述: 根据memberCount查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMemberCount(java.lang.Integer property);	
	/**
	 * 名称: getByMeetingDesc
	 * 描述: 根据meetingDesc查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingDesc(java.lang.String property);	
	/**
	 * 名称: getByCrateId
	 * 描述: 根据crateId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByCrateId(java.lang.Long property);	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByTaskId(java.lang.String property);	
	/**
	 * 名称: getByCrateTime
	 * 描述: 根据crateTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByCrateTime(java.util.Date property);	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(Meeting entity) ;
	/**
	 * 名称: save
	 * 描述: 新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public Long save(Meeting entity) ;

	/**
	 * 名称: saveMeetingInterfix
	 * 描述: 新增会议以及会议相关的数据
	 * @since  2014-09-14
	 * @author qingc
	 */
	public Long saveMeetingInterfix(MeetingQuery entity,User user,String url) throws Exception ;
	/**
	 * 名称: updateMeetingInterfix
	 * 描述: 修改会议以及会议相关的数据
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void updateMeetingInterfix(MeetingQuery entity,User user,String url) throws Exception ;
	
	/**
	 * 名称: getInMeetingByMemberId
	 * 描述:  获取会议中的会议列表
	 * @since  2014-11-3
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getInMeetingByMemberId(Long memberId);
	/**
	 * 名称: getMeetingListByMemberId
	 * 描述:  获取成员的会议列表和邀请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberId(Long memberId,String beforeOrAfter,Date lastNoticeUpdateDate,boolean invitationBeforeNotice);
	/**
	 * 名称: getListOtherMeeting
	 * 描述:  获取未开始和已结束的会议
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getListOtherMeeting(Long memberId);
	/**
	 * 名称: getMeetingListByMemberIdAfterNotice
	 * 描述:  获取最后通知时间之后5的成员的会议列表和邀请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberIdAfterNotice(Long memberId,Date lastNoticeUpdateDate,boolean invitationBeforeNotice);
	/**
	 * 名称: getMyMeetingSquare
	 * 描述:  获取会议广场
	 * @since  2014-09-22
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMyMeetingSquare(Long memberId,Date beginDate,Date endDate,Integer index,Integer size,Integer isCurrent,String city,Double addressPosX,Double addressPosY, String industry, String keyword);
	/**
	 * 名称: getMyMeetingSquareCount
	 * 描述:  获取会议广场条数
	 * @since  2014-09-22
	 * @author qingc
	 */
	public Integer getMyMeetingSquareCount(Long memberId,Date beginDate,Date endDate,Integer isCurrent,String city,Double addressPosX,Double addressPosY, String industry, String keyword);
	/**
	 * 获取我参加的和我创建的会议列表
	 * @param param
	 * @return
	 */
	public List<MeetingMemberListQuery> getMyAttendAndCreateMeeting(Map<String, Object> param);
	/**
	 * 查询我参加的会议数量
	 * @param param
	 * @return
	 */
	public Long getMyAttendAndCreateMeetingCount(Map<String, Object> param);
	/**
	 * 名称: getMyCreateMeeting
	 * 描述:  获取我创建的会议列表
	 * @since  2014-09-18
	 * @author qingc 
	 */
	public List<MeetingMemberListQuery> getMyCreateMeeting(Long memberId,Integer year,Integer month);
	/**
	 * 名称: getInvitationCount
	 * 描述:  获取邀请函数量
	 * @since  2014-09-17
	 * @author qingc
	 */
	public Integer getInvitationCount(Long memberId);
	
	/**
	 * 名称: getMyInvitation
	 * 描述:  获取邀我的请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMyInvitation(Long memberId);
	/**
	 * 名称: getMyLastInvitation
	 * 描述:  获取邀我的最后一条请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMyLastInvitation(Long memberId);
	/**
	 * 名称: getMeetingByIdAndMemberId
	 * 描述:  根据会议和成员id获取会议详情
	 * @since  2014-09-17
	 * @author qingc
	 */
	public  MeetingQuery getMeetingByIdAndMemberId(Long id,Long memberId) throws IllegalAccessException, InvocationTargetException;
	/**
	 * 名称: changeMeetingStatus
	 * 描述:  改变会议状态
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception 
	 */
	public boolean  changeMeetingStatus(Long meetingId,Integer meetingStatus,Long userId) throws Exception;
	/**
	 * 名称: 
	 * 描述:  根据时间和memberId 获取会议
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception getPigeonholeMeetingListByMemberIdAndDate
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberIdAndDate(Date beginDate,Date endDate,Long memberId) throws Exception;
	/**
	 * 名称: getAllNotBeginMeetng
	 * 描述:  获取所有未开始的会议
	 * @since  2014-10-01
	 * @author qingc
	 */
	public List<Meeting> getAllNotBeginMeetng();
	/**
	 * 名称: changeMeetingStatus
	 * 描述:  把会议状态改为会议已开始
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception 
	 */
	public boolean changeMeetingStatusToBegin(Long meetingId) throws Exception;
	/**
	 * 名称: 
	 * 描述:  根据时间和memberId 获取归档会议
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingMemberListQuery> getPigeonholeMeetingListByMemberIdAndDate(Date beginDate,Date endDate,Long memberId) throws Exception;
	/**
	 * 名称: cancelNotBenginMeeting
	 * 描述:  取消未开始会议
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception 
	 */
	public void cancelNotBenginMeeting(Long meetingId,Long memberIdStr) throws Exception;
	/**
	 * 名称: getMyForwardingMeeting
	 * 描述:  根据会议id和议题id获取转发会议的详细信息
	 * @since  2015-1-24
	 * @author qingc
	 * @throws Exception 
	 */
	public MeetingQuery getForwardingMeetingData(Long meetingId,Long topicId) throws Exception;
	/**
	 * 名称: getMyForwardingMeeting
	 * 描述:  获取我的可转发会议
	 * @since  2014-11-24
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingQuery> getMyForwardingMeeting(Long UserId) throws Exception;
	
	public List<MeetingVo> getMeetingList(long user_id);
	
	public List<MeetingVo> getInviteList(long user_id);
	/**
	 * 名称: getMeetingListAndInvitation
	 * 描述:  获取用户的会议和邀请函
	 * @since  2014-01-22
	 * @author qingc
	 * @throws Exception 
	 */
	public List<Social>  getMeetingListAndInvitation(Long userID);
	
	/**
	 * 获取会议列表根据用户id
	 * @param userId
	 * @return
	 */
	public List<Social> getMeetingListByUserId(Long userId);
	
	/**
	 * 获取最新的邀请函
	 * @param userID
	 * @return
	 */
	public Social  getLatestInvitation(Long userID);
	
	/**
	 * 获取会议中的最后一条消息
	 * @param userId
	 * @return
	 */
	public Social  getMeetingWithLatestMessage(Long userId);
	
	/**
	 * 名称: getMyForwardingInMeeting
	 * 描述:  获取我的可转发的进行中会议
	 * @since  2015-01-24
	 * @author qingc
	 * @throws Exception 
	 */
	public List<Social> getMyForwardingInMeeting(Long userId);
	/**
	 * 查询大数据推送会议的图片、参会人数、创建人头像
	 * @param list
	 * @return
	 */
	public List<BigDataQuery> getAttendCountAndPic(List<BigDataQuery> list);
	/**
	 * 批量查询会议
	 * @param meetingIdList
	 * @return
	 */
	public List<Meeting> getByIds(List<Long> meetingIdList);
	/**
	 * 批量删除我的会议
	 * @param meetingIdList
	 * @param memberId
	 */
	public void deleteMyMeetingBatch(List<Long> meetingIdList, Long memberId) throws Exception;
}
