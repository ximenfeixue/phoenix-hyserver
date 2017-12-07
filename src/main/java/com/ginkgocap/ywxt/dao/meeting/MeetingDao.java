/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ginkgocap.ywxt.model.meeting.Live;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingAdQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingCommonQuery;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingMemberListQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.ginkgocap.ywxt.vo.query.social.Social;

@Repository
public interface MeetingDao{
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
	public void delete(Long property,Long memberId);
	/**
	 * 名称: getByMeetingName
	 * 描述: 根据meetingName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingName(String property) ;	
	/**
	 * 名称: getByMeetingAddress
	 * 描述: 根据meetingAddress查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingAddress(String property) ;	
	/**
	 * 名称: getByMeetingAddressPos
	 * 描述: 根据meetingAddressPos查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingAddressPos(String property) ;	
	/**
	 * 名称: getByStartTime
	 * 描述: 根据startTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByStartTime(Date property) ;	
	/**
	 * 名称: getByEndTime
	 * 描述: 根据endTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByEndTime(Date property) ;	
	/**
	 * 名称: getByMeetingType
	 * 描述: 根据meetingType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingType(Integer property) ;	
	/**
	 * 名称: getByMeetingStatus
	 * 描述: 根据meetingStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingStatus(Integer property) ;	
	/**
	 * 名称: getByIsSecrecy
	 * 描述: 根据isSecrecy查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByIsSecrecy(Boolean property) ;	
	/**
	 * 名称: getByMemberCount
	 * 描述: 根据memberCount查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMemberCount(Integer property) ;	
	/**
	 * 名称: getByMeetingDesc
	 * 描述: 根据meetingDesc查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingDesc(String property) ;	
	/**
	 * 名称: getByCrateId
	 * 描述: 根据crateId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByCrateId(Long property) ;	
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByTaskId(String property) ;	
	/**
	 * 名称: getByCrateTime
	 * 描述: 根据crateTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByCrateTime(Date property) ;	
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
	 * 名称: listOtherMeeting
	 * 描述:  获取最后通知时间之后5的成员的会议列表和邀请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberIdAfterNotice(Long memberId,Date lastNoticeUpdateDate,boolean invitationBeforeNotice);
	/**
	 * 名称: getMeetingSquare
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
	 * 名称: 
	 * 描述:  根据时间和memberId 获取会议
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception 
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
	 * 名称: getPigeonholeMeetingListByMemberIdAndDate
	 * 描述:  根据时间和memberId 获取归档会议
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingMemberListQuery> getPigeonholeMeetingListByMemberIdAndDate(Date beginDate,Date endDate,Long memberId) throws Exception;
	/**
	 * 名称: getMyForwardingMeeting
	 * 描述:  获取我的可转发会议
	 * @since  2014-11-24
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingQuery> getMyForwardingMeeting(Long userId) throws Exception;
	/**
	 * 名称: deleteBatchOther
	 * 描述: 批量删除集合意外的对象
	 * @since  2014-11-24
	 * @author qingc
	 * @throws Exception 
	 */
	public  void deleteBatchOther(List<Long> list,Long id,Integer type)throws Exception;
	/*
	 * 2015-1-13
	 * 获取会议首页的会议
	 */
	public List<Map<String,Object>>  selectmeetingindex(long userId);
	/**
	 * 邀请函
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>>  selectinviteindex(long userId);
	/**
     * 名称: getMeetingListAndInvitation
     * 描述:  获取用户的会议和邀请函
     * @since  2014-01-22
     * @author qingc
     * @throws Exception 
     */
    public List<Social>  getMeetingListAndInvitation(Long userId);
    
    /**
     * 获取我的会议列表
     * @param userId 用户id
     * @return
     */
    public List<Social>  getMeetingListByUserId(Long userId);
    
    /**
     * 名称: getLatestInvitation
     * 描述:  获取最新的一条邀请函
     * @throws Exception 
     */
    public Social  getLatestInvitation(Long userId);
    
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
	 * 查询我参加的会议数量
	 * @param param
	 * @return
	 */
	public Long getMyAttendAndCreateMeetingCount(Map<String, Object> param);
	/**
	 * 批量查询会议
	 * @param meetingIdList
	 * @return
	 */
	public List<Meeting> getByIds(List<Long> meetingIdList);
	/**
	 * 批量删除我创建的会议
	 * @param meetingIdList
	 */
	public void deleteBatch(List<Long> meetingIdList);

	/**
	 * 置顶 会议
	 * @param id
	 */
	void addTop(long id);

	/**
	 * 取消置顶 会议
	 * @param id
	 */
	void deleteTop(long id);

	/**
	 * 禁用 会议
	 * @param id
	 */
	void disable(long id);

	/**
	 * 启用 会议
	 * @param id
	 */
	void enable(long id);

	/**
	 * 获取 筛选的会议 （运营后台）
	 * @param meetingCommonQuery
	 * @return
	 * @throws Exception
	 */
	List<Meeting> getCommonMeetingList(MeetingCommonQuery meetingCommonQuery) throws Exception;

	/**
	 * 获取 筛选的会议 总个数 （运营后台）
	 * @param meetingCommonQuery
	 * @return
	 * @throws Exception
	 */
	long getCommonMeetingCount(MeetingCommonQuery meetingCommonQuery) throws Exception;

	/**
	 * 获取 会议的发现列表 广告位
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	List<MeetingAdQuery> getTops(int index, final int size) throws Exception;

	/**
	 * 开通直播
	 * @param live
	 */
	void updateLive(Live live);

	/**
	 * 更新直播状态
	 * @param liveStatus
	 */
	void updateLiveStatus(final Long id, final Integer liveStatus);

	/**
	 * 更新直播结束时间
	 * @param liveEndTime
	 */
	void updateLiveEndTime(final Long id, final Date liveEndTime);
}
