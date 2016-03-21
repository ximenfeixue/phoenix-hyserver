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

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingDataDao;
import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingDataQuery;
@Repository
public class MeetingDataDaoImpl extends SqlSessionDaoSupport implements MeetingDataDao,ApplicationContextAware{
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
	public MeetingData getById(Long property) {
		return (MeetingData)getSqlSession().selectOne("MeetingData.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("MeetingData.delete",property);
	}
	/**
	 * 名称: deleteByMeetingId
	 * 描述: 根据会议id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByMeetingId(Long property){
		getSqlSession().delete("MeetingData.deleteByMeetingId",property);
	}
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByMeetingId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingData.getByMeetingId",property);
	}	
	/**
	 * 批量查询会议相关知识、需求
	 * @param meetingIdList
	 * @return
	 */
	public List<MeetingData> getByMeetingIdList(List<Long> meetingIdList) {
		return getSqlSession().selectList("MeetingData.getByMeetingIdList", meetingIdList);
	}
	/**
	 * 名称: getByDataName
	 * 描述: 根据dataName查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataName(java.lang.String property) {
		return getSqlSession().selectList("MeetingData.getByDataName",property);
	}	
	
	/**
	 * 名称: getByDataId
	 * 描述: 根据dataId查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataId(java.lang.Long property) {
		return getSqlSession().selectList("MeetingData.getByDataId",property);
	}	
	
	/**
	 * 名称: getByDataType
	 * 描述: 根据dataType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataType(java.lang.Integer property) {
		return getSqlSession().selectList("MeetingData.getByDataType",property);
	}	
	
	/**
	 * 名称: getByDataReqType
	 * 描述: 根据dataReqType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataReqType(java.lang.Integer property) {
		return getSqlSession().selectList("MeetingData.getByDataReqType",property);
	}	
	
	/**
	 * 名称: getByDataUrl
	 * 描述: 根据dataUrl查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingData> getByDataUrl(java.lang.String property) {
		return getSqlSession().selectList("MeetingData.getByDataUrl",property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	  * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingData entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("MeetingData.insert", entity);
		else 
			getSqlSession().update("MeetingData.update", entity);
	}
	/**
	 * 名称: getDataByMeetingIdAndDataType
	 * 描述: 根据meetingId和资料类型查找
	 * dataType: 资料类型,0 需求，1知识
	  * @since  2014-09-18
	 * @author qingc
	 */
	public List<MeetingData> getDataByMeetingIdAndDataType(Long property,Integer dataType) {
		final Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("meetingId", property);
		map.put("dataType", dataType);
		return getSqlSession().selectList("MeetingData.getDataByMeetingIdAndDataType",map);
	}
	/**
	 * 名称: getMyMeetingData
	 * 描述: 获取我的会议资料
	 * @since  2014-09-19
	 * @author qingc
	 */
	public List<MeetingDataQuery> getMyMeetingData(Date beginDate,Date endDate,Long memberId){
		final Map<String,Object> map=new HashMap<String, Object>();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("memberId", memberId);
		return getSqlSession().selectList("MeetingData.getMyMeetingData",map);
	}
}
