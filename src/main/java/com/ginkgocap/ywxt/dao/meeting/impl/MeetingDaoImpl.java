/**

 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ginkgocap.ywxt.vo.query.meeting.MeetingCommonQuery;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingDao;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingMemberListQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.ginkgocap.ywxt.vo.query.social.Social;
@Repository
public class MeetingDaoImpl extends SqlSessionDaoSupport implements MeetingDao,ApplicationContextAware{
	@Autowired
	private ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	* @since  2014-09-14
	 * @author qingc
	 */
	public Meeting getById(Long property) {
		return (Meeting)getSqlSession().selectOne("Meeting.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	* @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().update("Meeting.delete",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除我创建的会议
	* @since  2014-09-14
	 * @author qingc
	 * @throws Exception 
	 */
	public void delete(Long property,Long memberId){
		final Map<String,Object> map =new HashMap<String, Object>();
		map.put("id", property);
		map.put("memberId",memberId );
		getSqlSession().update("Meeting.deleteByIdAndCreateId",map);
	}
	/**
	 * 名称: getByMeetingName
	 * 描述: 根据meetingName查找
	* @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingName(java.lang.String property) {
		return getSqlSession().selectList("Meeting.getByMeetingName",property);
	}
	/**
	 * 名称: getByMeetingAddress
	 * 描述: 根据meetingAddress查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingAddress(java.lang.String property) {
		return getSqlSession().selectList("Meeting.getByMeetingAddress",property);
	}
	/**
	 * 名称: getByMeetingAddressPos
	 * 描述: 根据meetingAddressPos查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingAddressPos(java.lang.String property) {
		return getSqlSession().selectList("Meeting.getByMeetingAddressPos",property);
	}
	/**
	 * 名称: getByStartTime
	 * 描述: 根据startTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByStartTime(java.util.Date property) {
		return getSqlSession().selectList("Meeting.getByStartTime",property);
	}
	/**
	 * 名称: getByEndTime
	 * 描述: 根据endTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByEndTime(java.util.Date property) {
		return getSqlSession().selectList("Meeting.getByEndTime",property);
	}
	/**
	 * 名称: getByMeetingType
	 * 描述: 根据meetingType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingType(java.lang.Integer property) {
		return getSqlSession().selectList("Meeting.getByMeetingType",property);
	}
	/**
	 * 名称: getByMeetingStatus
	 * 描述: 根据meetingStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingStatus(java.lang.Integer property) {
		return getSqlSession().selectList("Meeting.getByMeetingStatus",property);
	}
	/**
	 * 名称: getByIsSecrecy
	 * 描述: 根据isSecrecy查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByIsSecrecy(java.lang.Boolean property) {
		return getSqlSession().selectList("Meeting.getByIsSecrecy",property);
	}	
	
	/**
	 * 名称: getByMemberCount
	 * 描述: 根据memberCount查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMemberCount(java.lang.Integer property) {
		return getSqlSession().selectList("Meeting.getByMemberCount",property);
	}
	/**
	 * 名称: getByMeetingDesc
	 * 描述: 根据meetingDesc查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByMeetingDesc(java.lang.String property) {
		return getSqlSession().selectList("Meeting.getByMeetingDesc",property);
	}
	/**
	 * 名称: getByCrateId
	 * 描述: 根据crateId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByCrateId(java.lang.Long property) {
		return getSqlSession().selectList("Meeting.getByCrateId",property);
	}
	/**
	 * 名称: getByTaskId
	 * 描述: 根据taskId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByTaskId(java.lang.String property) {
		return getSqlSession().selectList("Meeting.getByTaskId",property);
	}	
	
	/**
	 * 名称: getByCrateTime
	 * 描述: 根据crateTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<Meeting> getByCrateTime(java.util.Date property) {
		return getSqlSession().selectList("Meeting.getByCrateTime",property);
	}
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(Meeting entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("Meeting.insert", entity);
		else 
			getSqlSession().update("Meeting.update", entity);
	}
	/**
	 * 名称: save
	 * 描述: 新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public Long save(Meeting entity) {
		getSqlSession().insert("Meeting.insert", entity);
		return 	entity.getId();
	}
	/**
	 * 名称: getInMeetingByMemberId
	 * 描述:  获取会议中的会议列表
	 * @since  2014-11-3
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getInMeetingByMemberId(Long memberId){
		return getSqlSession().selectList("Meeting.getInMeetingByMemberId",memberId);
	}
	/**
	 * 名称: getMeetingListByMemberId
	 * 描述: 获取成员的会议列表和邀请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberId(Long memberId,String beforeOrAfter,Date lastNoticeUpdateDate,boolean invitationBeforeNotice){
		 final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		 mapParam.put("beforeOrAfter", beforeOrAfter);
		 mapParam.put("lastNoticeUpdateDate", lastNoticeUpdateDate);
		 mapParam.put("invitationBeforeNotice", invitationBeforeNotice);
		 List<MeetingMemberListQuery>list= getSqlSession().selectList("Meeting.getMeetingListByMemberId",mapParam);
		return list;
	}
	/**
	 * 名称: getListOtherMeeting
	 * 描述:  获取未开始和已结束的会议
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getListOtherMeeting(Long memberId){
		 final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		return getSqlSession().selectList("Meeting.getListOtherMeeting",mapParam);
	}
	/**
	 * 名称: getMeetingListByMemberIdAfterNotice
	 * 描述:  获取最后通知时间之后5的成员的会议列表和邀请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberIdAfterNotice(Long memberId,Date lastNoticeUpdateDate,boolean invitationBeforeNotice){
		 final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		 mapParam.put("lastNoticeUpdateDate", lastNoticeUpdateDate);
		 mapParam.put("invitationBeforeNotice", invitationBeforeNotice);
		return getSqlSession().selectList("Meeting.getMeetingListByMemberIdAfterNotice",mapParam);
	}
	/**
	 * 名称: getMeetingSquare
	 * 描述:  获取会议广场
	 * @since  2014-09-22
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMyMeetingSquare(Long memberId,Date beginDate,Date endDate,Integer index,Integer size,Integer isCurrent,String city,Double addressPosX,Double addressPosY, String industry, String keyword){
		final Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("memberId", memberId);
		mapParam.put("beginDate", beginDate);
		mapParam.put("endDate", endDate);
		mapParam.put("startRow", index*size);
		mapParam.put("size", size);
		mapParam.put("isCurrent", isCurrent);
		mapParam.put("city", city);
		mapParam.put("addressPosX", addressPosX);
		mapParam.put("addressPosY", addressPosY);
		mapParam.put("industry", industry);
		mapParam.put("keyword", keyword);
		List<MeetingMemberListQuery>list= getSqlSession().selectList("Meeting.getMyMeetingSquare",mapParam);
		return list;
	}
	/**
	 * 名称: getMyMeetingSquareCount
	 * 描述:  获取会议广场条数
	 * @since  2014-09-22
	 * @author qingc
	 */
	public Integer getMyMeetingSquareCount(Long memberId,Date beginDate,Date endDate,Integer isCurrent,String city,Double addressPosX,Double addressPosY, String industry, String keyword){
		final Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("memberId", memberId);
		mapParam.put("beginDate", beginDate);
		mapParam.put("endDate", endDate);
		mapParam.put("isCurrent", isCurrent);
		mapParam.put("city", city);
		mapParam.put("addressPosX", addressPosX);
		mapParam.put("addressPosY", addressPosY);
		mapParam.put("industry", industry);
		mapParam.put("keyword", keyword);
		Integer count=(Integer)getSqlSession().selectOne("Meeting.getMyMeetingSquareCount",mapParam);
		return count;
	}
	/**
	 * 获取我参加的和我创建的会议列表
	 * @param param
	 * @return
	 */
	public List<MeetingMemberListQuery> getMyAttendAndCreateMeeting(Map<String, Object> param) {
		return getSqlSession().selectList("Meeting.getMyAttendAndCreateMeeting", param);
	}
	/**
	 * 查询我参加的会议数量
	 * @param param
	 * @return
	 */
	public Long getMyAttendAndCreateMeetingCount(Map<String, Object> param) {
		return getSqlSession().selectOne("Meeting.getMyAttendAndCreateMeetingCount", param);
	}
	/**
	 * 名称: getMyCreateMeeting
	 * 描述:  获取我创建的会议列表
	 * @since  2014-09-18
	 * @author qingc 
	 */
	public List<MeetingMemberListQuery> getMyCreateMeeting(Long memberId,Integer year,Integer month){
		 final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		 mapParam.put("year", year);
		 mapParam.put("month", month);
		 //mapParam.put("status", currentPage);
		 //mapParam.put("startRow", currentPage*pageSize);
		 //mapParam.put("pageSize", pageSize);
		 List<MeetingMemberListQuery>list= getSqlSession().selectList("Meeting.getMyCreateMeeting",mapParam);
		return list;
	}
	/**
	 * 名称: getMeetingListByMemberIdCount
	 * 描述: 获取我的会议总数
	 * @since  2014-09-17
	 * @author qingc
	 */
	public Long getMeetingListByMemberIdCount(Long userId){
		 final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", userId);
		 Long count=(Long)getSqlSession().selectOne("Meeting.getMeetingListByMemberIdCount",mapParam);
		return count;
		
	}
	/**
	 * 名称: getInvitationCount
	 * 描述:  获取邀请函数量
	 * @since  2014-09-17
	 * @author qingc
	 */
	public Integer getInvitationCount(Long memberId){
		return (Integer)getSqlSession().selectOne("Meeting.getInvitationCount",memberId);
	}
	/**
	 * 名称: getMyInvitation
	 * 描述:  获取邀我的请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMyInvitation(Long memberId){
		 final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		List<MeetingMemberListQuery>list= getSqlSession().selectList("Meeting.getMyInvitation",memberId);
		return list;
	}
	/**
	 * 名称: getMyLastInvitation
	 * 描述:  获取邀我的最后一条请函
	 * @since  2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMyLastInvitation(Long memberId){
		 final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		List<MeetingMemberListQuery>list= getSqlSession().selectList("Meeting.getMyLastInvitation",memberId);
		return list;
	}
	/**
	 * 名称: 
	 * 描述:  根据时间和memberId 获取会议
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberIdAndDate(Date beginDate,Date endDate,Long memberId) throws Exception{
		final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		 mapParam.put("beginDate", beginDate);
		 mapParam.put("endDate", endDate);
		List<MeetingMemberListQuery>list= getSqlSession().selectList("Meeting.getMeetingListByMemberIdAndDate",mapParam);
		return list;
	}
	/**
	 * 名称: getAllNotBeginMeetng
	 * 描述:  获取所有未开始的会议
	 * @since  2014-10-01
	 * @author qingc
	 */
	public List<Meeting> getAllNotBeginMeetng(){
		return getSqlSession().selectList("Meeting.getAllNotBeginMeetng",null);
	}
	/**
	 * 名称: getPigeonholeMeetingListByMemberIdAndDate
	 * 描述:  根据时间和memberId 获取归档会议
	 * @since  2014-10-01
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingMemberListQuery> getPigeonholeMeetingListByMemberIdAndDate(Date beginDate,Date endDate,Long memberId) throws Exception{
		final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		 mapParam.put("beginDate", beginDate);
		 mapParam.put("endDate", endDate);
		List<MeetingMemberListQuery> list= getSqlSession().selectList("Meeting.getPigeonholeMeetingListByMemberIdAndDate",mapParam);
		return list;
	}
	/**
	 * 名称: getMyForwardingMeeting
	 * 描述:  获取我的可转发会议
	 * @since  2014-11-24
	 * @author qingc
	 * @throws Exception 
	 */
	public List<MeetingQuery> getMyForwardingMeeting(Long userId) throws Exception{
		return getSqlSession().selectList("Meeting.getMyForwardingMeeting",userId);
	}
	/**
	 * 名称: deleteBatchOther
	 * 描述: 批量删除集合意外的对象
	 * @since  2014-11-24
	 * @author qingc
	 * @throws Exception 
	 */
	public void deleteBatchOther(List<Long> list,Long id,Integer type)throws Exception{
		final Map<String,Object> map=new HashMap<String, Object>();
		map.put("meetingId", id);
		if(!Utils.isNullOrEmpty(type)){
			switch(type){
			case 0:map.put("tableName", "tb_meeting_member");break;
			case 1:map.put("tableName", "tb_meeting_topic");break;
			case 2:map.put("tableName", "tb_meeting_time");break;
			case 3:map.put("tableName", "tb_meeting_people");break;
			case 4:map.put("tableName", "tb_meeting_data");break;
			case 5:map.put("tableName", "tb_meeting_organ");break;
			case 6:map.put("tableName", "tb_meeting_sign_label");break;
			}
			// 修改后的为空，移除所有的
			if(Utils.isNullOrEmpty(list)){
				getSqlSession().delete("Meeting.deleteAllByMeetingId",map);
			}else{
				// 修改后的不为空，移除集合以外的
				String inStr="(";
				for(Long mid:list){
					inStr+=mid+",";
				}
				inStr=inStr.substring(0,inStr.lastIndexOf(","));
				inStr+=")";
				map.put("list", inStr);
				
				getSqlSession().delete("Meeting.deleteBatchOtherByMeetingId",map);
			}
		}
	}
	public List<Map<String, Object>> selectmeetingindex(long userId) {
		return getSqlSession().selectList("Meeting.selectmeetingindex", userId);
	}
	public List<Map<String, Object>> selectinviteindex(long userId) {
		return getSqlSession().selectList("Meeting.selectinviteindex", userId);
	}
	/**
     * 名称: getMeetingListAndInvitation
     * 描述:  获取用户的会议和邀请函
     * @since  2014-01-22
     * @author qingc
     * @throws Exception 
     */
    public List<Social>  getMeetingListAndInvitation(Long userId){
    	return getSqlSession().selectList("Meeting.getMeetingListAndInvitation", userId);
    }
    
    @Override
	public List<Social> getMeetingListByUserId(Long userId) {
    	return getSqlSession().selectList("Meeting.getMeetingListByUserId", userId);
	}
    
	@Override
	public Social getLatestInvitation(Long userId) {
    	return getSqlSession().selectOne("Meeting.getLatestInvitation", userId);
	}
	/**
     * 名称: getMyForwardingInMeeting
     * 描述:  获取我的可转发的进行中会议
     * @since  2015-01-24
     * @author qingc
     * @throws Exception 
     */
    public List<Social> getMyForwardingInMeeting(Long userId){
    	return getSqlSession().selectList("Meeting.getMyForwardingInMeeting", userId);
    }
    /**
	 * 批量查询会议
	 * @param meetingIdList
	 * @return
	 */
	public List<Meeting> getByIds(List<Long> meetingIdList) {
		return getSqlSession().selectList("Meeting.getByIds", meetingIdList);
	}
	/**
	 * 批量删除我创建的会议
	 * @param meetingIdList
	 */
	public void deleteBatch(List<Long> meetingIdList) {
		getSqlSession().update("Meeting.deleteBatch", meetingIdList);
	}

	@Override
	public void addTop(long id) {
	 	getSqlSession().update("Meeting.addTop", id);
	}

	@Override
	public void deleteTop(long id) {
		getSqlSession().update("Meeting.deleteTop", id);
	}

	@Override
	public void disable(long id) {
		getSqlSession().update("Meeting.disable", id);
	}

	@Override
	public void enable(long id) {
		getSqlSession().update("Meeting.enable", id);
	}

	@Override
	public List<Meeting> getCommonMeetingList(MeetingCommonQuery meetingCommonQuery) throws Exception {

		// 设置开始行数
		meetingCommonQuery.setStartRow(meetingCommonQuery.getIndex() * meetingCommonQuery.getSize());
		return getSqlSession().selectList("Meeting.getCommonMeetingList", meetingCommonQuery);
	}

	@Override
	public long getCommonMeetingCount(MeetingCommonQuery meetingCommonQuery) throws Exception {
		long total = getSqlSession().selectOne("Meeting.getCommonMeetingCount", meetingCommonQuery);
		return total;
	}

	/**
	 * 名称: getMyAttendAndCreateMeeting
	 * 描述:  获取我参加的和我创建的会议列表
	 * @since  2014-09-18
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMyAttendAndCreateMeetingOld(Long memberId,Integer year,Integer month){
		 final Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("memberId", memberId);
		 mapParam.put("year", year);
		 mapParam.put("month", month);
		 //mapParam.put("status", currentPage);
		 //mapParam.put("startRow", currentPage*pageSize);
		 //mapParam.put("pageSize", pageSize);
		 List<MeetingMemberListQuery>list= getSqlSession().selectList("Meeting.getMyAttendAndCreateMeetingOld",mapParam);
		return list;
	}
	
	@Override
	public Social getMeetingWithLatestMessage(Long userId) {
		return getSqlSession().selectOne("Meeting.getMeetingWithLatestMessage", userId);
	}
}
