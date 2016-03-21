/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingMemberDao;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.utils.Utils;
@Repository
public class MeetingMemberDaoImpl extends SqlSessionDaoSupport implements MeetingMemberDao,ApplicationContextAware{
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
	public MeetingMember getById(Long property) {
		return (MeetingMember)getSqlSession().selectOne("MeetingMember.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().update("MeetingMember.delete",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据会议id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByMeetingId(Long property) {
		getSqlSession().update("MeetingMember.deleteByMeetingId",property);
	}
	/**
	 * 名称: deleteByMeetingIdAndMeetingtype
	 * 描述: 根据会议id和参会人类型删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByMeetingIdAndMeetingtype(Long property,Integer memberType) {
		Map<String,Object>paraMap=new HashMap<String,Object>();
		paraMap.put("meetingId", property);
		paraMap.put("memberType", memberType);
		getSqlSession().update("MeetingMember.deleteByMeetingIdAndMeetingtype",paraMap);
	}
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingMember.getByMemberId",property);
	}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMeetingId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingMember.getByMeetingId",property);
	}	
	/**
	 * 名称: getVisitantByMeetingId
	 * 描述: 根据meetingId查找嘉宾
	 * @since  2014-09-18
	 * @author qingc
	 */
	public List<MeetingMember> getVisitantByMeetingId(Long property){
		return getSqlSession().selectList("MeetingMember.getVisitantByMeetingId",property);
	}
	/**
	 * 名称: getByMemberType
	 * 描述: 根据memberType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberType(java.lang.Integer property) {
		return getSqlSession().selectList("MeetingMember.getByMemberType",property);
	}
	/**
	 * 名称: getByMemberName
	 * 描述: 根据memberName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberName(java.lang.String property) {
		return getSqlSession().selectList("MeetingMember.getByMemberName",property);
	}
	/**
	 * 名称: getByMemberPhoto
	 * 描述: 根据memberPhoto查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberPhoto(java.lang.String property) {
		return getSqlSession().selectList("MeetingMember.getByMemberPhoto",property);
	}
	/**
	 * 名称: getByMemberMeetStatus
	 * 描述: 根据memberMeetStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByMemberMeetStatus(java.lang.Integer property) {
		return getSqlSession().selectList("MeetingMember.getByMemberMeetStatus",property);
	}
	/**
	 * 名称: getByAttendMeetStatus
	 * 描述: 根据attendMeetStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByAttendMeetStatus(java.lang.Integer property) {
		return getSqlSession().selectList("MeetingMember.getByAttendMeetStatus",property);
	}
	/**
	 * 名称: getByAttendMeetType
	 * 描述: 根据attendMeetType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingMember> getByAttendMeetType(java.lang.Integer property) {
		return getSqlSession().selectList("MeetingMember.getByAttendMeetType",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingMember entity)throws Exception {
		if(entity.getId() == null) {
			getSqlSession().insert("MeetingMember.insert", entity);
		} else { 
			getSqlSession().update("MeetingMember.update", entity);
		}
	}
	/**
	 * 名称: save
	 * 描述: 新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void save(MeetingMember entity) {
		getSqlSession().insert("MeetingMember.insert", entity);
	}
	/**
	 * 名称: save
	 * 描述: 新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByMeetingId(MeetingMember entity) {
		getSqlSession().insert("MeetingMember.insert", entity);
	}
	/**
	 * 名称: getByMeetingIdAndID
	 * 描述: 根据meetingId和成员memberId查找
	 * @since  2014-09-18
	 * @author qingc
	 */
	public List<MeetingMember> getByMeetingIdAndMemberId(Long meetingId,Long memberId) {
		final Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("meetingId", meetingId);
		mapParam.put("memberId", memberId);
		List<MeetingMember>list= getSqlSession().selectList("MeetingMember.getByMeetingIdAndMemberId",mapParam);
		return list;
	}
	/**
	 * 名称: getSignUpMemberCount
	 * 描述:获取会议报名人数 
	 * @since  2014-09-18
	 * @author qingc
	 */
	public Integer getSignUpMemberCount(Long meetingId){
		Integer count=(Integer)getSqlSession().selectOne("MeetingMember.getSignUpMemberCount",meetingId);
		return count;
	}
	/**
	 * 名称: getAttendMeetingCount
	 * 描述:获取参会总人数
	 * @since  2014-09-18
	 * @author qingc
	 */
	public Integer getAttendMeetingCount(Long meetingId){
		Integer count=(Integer)getSqlSession().selectOne("MeetingMember.getAttendMeetingCount",meetingId);
		return count;
	}
	public Map<String, Integer> getAttendMeetingCount(List<Long> meetingIds) {
		List<MeetingMember> list = getSqlSession().selectList("getAttendMeetingsCount", meetingIds);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(list!=null && list.size()>0) {
			for(MeetingMember member : list) {
				String key = ""+member.getMeetingId();
				int count = 1;
				if(map.get(key) != null) {
					count = map.get(key) + 1;
				}
				map.put(key, count);
			}
		}
		return map;
	}
	/**
	 * 名称: getAttendMeetingCount
	 * 描述:获取签到总人数
	 * @since  2014-09-18
	 * @author qingc
	 */
	public Integer getSignInCount(Long meetingId){
		Integer count=(Integer)getSqlSession().selectOne("MeetingMember.getAndSignInCount",meetingId);
		return count;
	}
	/**
	 * 根据会议ID获取参会成员
	 * @param meetingId
	 * @return
	 */
	public List<MeetingMember> getAttendMemberByMeetingId(Long meetingId) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("meetingId", meetingId);
		return getSqlSession().selectList("MeetingMember.getAttendMemberByMeetingId", mapParam);
	}
	/**
	 * 删除其他会议成员
	 * @param meetingId
	 * @param extendIdList
	 */
	public void deleteMemberByMeetingId(Long meetingId, List<Long> extendIdList) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("meetingId", meetingId);
		if(!Utils.isNullOrEmpty(extendIdList)) {
			param.put("extendIdList", extendIdList);
		}
		getSqlSession().update("MeetingMember.deleteMemberByMeetingId", param);
	}
	/**
	 * 批量查询会议成员
	 * @param idList
	 * @return
	 */
	public List<MeetingMember> getByIdList(List<Long> idList) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("idList", idList);
		return getSqlSession().selectList("MeetingMember.getByIdList", param);
	}
	/**
	 * 批量删除我参加的会议
	 * @param attendIdList
	 */
	public void deleteAttendMeetingBatch(List<Long> attendIdList) {
		getSqlSession().update("MeetingMember.deleteAttendMeetingBatch", attendIdList);
	}
}