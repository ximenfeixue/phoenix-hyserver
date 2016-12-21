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

import com.ginkgocap.ywxt.dao.meeting.MeetingSignLabelDataDao;
import com.ginkgocap.ywxt.model.meeting.MeetingSignLabelData;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignLabelDataQuery;
@Repository
public class MeetingSignLabelDataDaoImpl extends SqlSessionDaoSupport implements MeetingSignLabelDataDao,ApplicationContextAware{
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
	public MeetingSignLabelData getById(Long property) {
		return (MeetingSignLabelData)getSqlSession().selectOne("MeetingSignLabelData.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("MeetingSignLabelData.delete",property);
	}
	public void deleteByMeetingId(Long property) {
		getSqlSession().delete("MeetingSignLabelData.deleteByMeetingId",property);
	}
	
	/**
	 * 名称: getByMslabelId
	 * 描述: 根据mslabelId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByMslabelId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingSignLabelData.getByMslabelId",property);
	}	
	
	/**
	 * 名称: getByLabelContent
	 * 描述: 根据labelContent查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByLabelContent(java.lang.String property) {
		return getSqlSession().selectList("MeetingSignLabelData.getByLabelContent",property);
	}	
	
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByMemberId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingSignLabelData.getByMemberId",property);
	}	
	
	/**
	 * 名称: getByMemberName
	 * 描述: 根据memberName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByMemberName(java.lang.String property) {
		return getSqlSession().selectList("MeetingSignLabelData.getByMemberName",property);
	}	
	
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByCreateId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingSignLabelData.getByCreateId",property);
	}	
	
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByCreateName(java.lang.String property) {
		return getSqlSession().selectList("MeetingSignLabelData.getByCreateName",property);
	}	
	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelData> getByCreateTime(java.util.Date property) {
		return getSqlSession().selectList("MeetingSignLabelData.getByCreateTime",property);
	}	
	/**
	 * 名称: getByMeetingIdAndMemberId
	 * 描述: 根据会议Id和用户Id获取用户必填信息
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingSignLabelDataQuery> getByMeetingIdAndMemberId(Long meetingId,Long memberId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("meetingId", meetingId);
		map.put("memberId", memberId);
		return getSqlSession().selectList("MeetingSignLabelData.getByMeetingIdAndMemberId",map);
	}
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingSignLabelData entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("MeetingSignLabelData.insert", entity);
		else 
			getSqlSession().update("MeetingSignLabelData.update", entity);
	}
	/**
	 * 根据会议ID和成员ID删除报名信息
	 * @param meetingId
	 * @param memberId
	 * @return
	 */
	public void deleteByMeetingIdAndMemberId(Long meetingId, Long memberId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("meetingId", ""+meetingId);
		map.put("memberId", ""+memberId);
		getSqlSession().delete("MeetingSignLabelData.deleteByMeetingIdAndMemberId", map);
	}
}
