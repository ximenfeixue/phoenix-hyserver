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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.MeetingNoticeDao;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.NoticeField;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoticeRelation;
@Repository
public class MeetingNoticeDaoImpl extends SqlSessionDaoSupport implements MeetingNoticeDao,ApplicationContextAware{
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
	public MeetingNotice getById(Long property) {
		return (MeetingNotice)getSqlSession().selectOne("MeetingNotice.getById",property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) {
		getSqlSession().delete("MeetingNotice.delete",property);
	}
	
	/**
	 * 名称: getByReceiverType
	 * 描述: 根据receiverType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByReceiverType(Integer property) {
		return getSqlSession().selectList("MeetingNotice.getByReceiverType",property);
	}	
	
	/**
	 * 名称: getByReceiver
	 * 描述: 根据receiver查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByReceiver(Long property) {
		return getSqlSession().selectList("MeetingNotice.getByReceiver",property);
	}	
	
	/**
	 * 名称: getByNoticeType
	 * 描述: 根据noticeType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByNoticeType(Integer property) {
		return getSqlSession().selectList("MeetingNotice.getByNoticeType",property);
	}	
	
	/**
	 * 名称: getByNoticeContent
	 * 描述: 根据noticeContent查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByNoticeContent(String property) {
		return getSqlSession().selectList("MeetingNotice.getByNoticeContent",property);
	}	
	
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByMeetingId(Long property) {
		return getSqlSession().selectList("MeetingNotice.getByMeetingId",property);
	}
	/**
	 * 查找通知
	 * @param param
	 * @return
	 */
	public List<MeetingNotice> getByParam(Map<String, Object> param) {
		return getSqlSession().selectList("MeetingNotice.getByParam", param);
	}
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByCreateId(Long property) {
		return getSqlSession().selectList("MeetingNotice.getByCreateId",property);
	}	
	/**
	 * 获取未读通知数
	 * @param memberId
	 * @return
	 */
	public Integer getUnReadNoticeCount(Long memberId) {
		return (Integer)getSqlSession().selectOne("MeetingNotice.getUnReadNoticeCount",memberId);
	}
	/**
	 * 名称: getNewNotice
	 * 描述: 获取用户最新的通知
	 * 	  * @since  2014-11-4
	 * @author qingc
	 */
	public MeetingNotice getNewNotice(Long memberId){
		return (MeetingNotice)getSqlSession().selectOne("MeetingNotice.getNewNotice",memberId);
	}
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByCreateName(String property) {
		return getSqlSession().selectList("MeetingNotice.getByCreateName",property);
	}	
	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByCreateTime(Date property) {
		return getSqlSession().selectList("MeetingNotice.getByCreateTime",property);
	}	
	/**
	 * 名称: getMyNoticeByMeetingIdAndNoticeType
	 * 描述: 根据会议ID和通知类型查找通知
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getMyNoticeByMeetingIdAndReceiverType(Long receiver,Long meetingId,Integer receiverType){
		Map<String,Object> mapParam=new HashMap<String,Object>();
		mapParam.put("receiver", receiver);
		mapParam.put("meetingId", meetingId);
		mapParam.put("receiverType", receiverType);
		return getSqlSession().selectList("MeetingNotice.getMyNoticeByMeetingIdAndReceiverType",mapParam);
	}
	/**
	 * 名称: getMyNoticeByMeetingIdAndNoticeType
	 * 描述: 根据会议ID和通知类型查找通知
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getMyNoticeByMeetingIdAndNoticeType(Long receiver,Long meetingId,Integer NoticeType){
		Map<String,Object> mapParam=new HashMap<String,Object>();
		mapParam.put("receiver", receiver);
		mapParam.put("meetingId", meetingId);
		mapParam.put("NoticeType", NoticeType);
		return getSqlSession().selectList("MeetingNotice.getMyNoticeByMeetingIdAndNoticeType",mapParam);
	}
	/**
	 * 名称: getMeetingNoticeRelation
	 * 描述: 根据用户Id获取的会议通知列表
	* @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoticeRelation> getMeetingNoticeRelation(Long memberId){
		return getSqlSession().selectList("MeetingNotice.getMeetingNoticeRelation", memberId);
	}
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingNotice entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("MeetingNotice.insert", entity);
		else 
			getSqlSession().update("MeetingNotice.update", entity);
	}
	/**
	 * 名称: addNotice
	 * 描述: 增加通知信息
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void addNotice(MeetingNotice meetingNotice, NoticeField noticeField){
		List<MeetingNotice> list = this.getNoticeByProperty(meetingNotice.getReceiverType(), meetingNotice.getReceiver(),
				meetingNotice.getNoticeType(), meetingNotice.getIsShow(), meetingNotice.getMeetingId(), meetingNotice.getCreateId());
		if (!Utils.isNullOrEmpty(list) && !Utils.isNullOrEmpty(list.get(0))){
			MeetingNotice meetingNoticeTemp = list.get(0);
			if(!Utils.isNullOrEmpty(meetingNoticeTemp.getId())){
				meetingNotice.setId(meetingNoticeTemp.getId());
				this.saveOrUpdate(meetingNotice);
				noticeField.setNoticeId(meetingNotice.getId());
				this.deleteByNoticeId(meetingNoticeTemp.getId());
				this.saveOrUpdate(noticeField);
			}
		} else {
			this.saveOrUpdate(meetingNotice);
			noticeField.setNoticeId(meetingNotice.getId());
			this.saveOrUpdate(noticeField);
		}
	}
	
	/**
	 * 名称: getNoticeByproperty
	 * 描述: 查找是否存在通知
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List <MeetingNotice> getNoticeByProperty(Integer receiverType,Long receiver,Integer noticeType,Integer isShow,Long meetingId,Long createId){
		Map<String,Object> mapParam=new HashMap<String,Object>();
		mapParam.put("receiverType", receiverType);
		mapParam.put("receiver", receiver);
		mapParam.put("meetingId", meetingId);
		mapParam.put("noticeType", noticeType);
		mapParam.put("isShow", isShow);
		mapParam.put("createId", createId);
		return getSqlSession().selectList("MeetingNotice.getNoticeByProperty",mapParam);
	}

	/**
	 * 名称: addNotice
	 * 描述: 增加通知字段信息
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(NoticeField entity) {
		if(entity.getId() == null) 
			getSqlSession().insert("NoticeField.insert", entity);
		else 
			getSqlSession().update("NoticeField.update", entity);
	}
	/**
	 * 名称: deleteByNoticeId
	 * 描述: 按通知Id删除通知字段
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void deleteByNoticeId(Long noticeId) {
			getSqlSession().insert("NoticeField.deleteByNoticeId", noticeId);
	}
	/**
	 * 名称: getSignUpNotice
	 * 描述: 获取报名的通知
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getSignUpNotice(String field){
		return  getSqlSession().selectList("MeetingNotice.getSignUpNotice", field);
	}
	/**
	 * 名称: getSignUpNotice
	 * 描述: 根据创建人获取报名的通知
	 * @since  2014-09-14
	 * @author qingc
	 * @return 
	 */
	public List<MeetingNotice> getSignUpNotice(Long createId,Long meetingId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("createId", createId);
		map.put("meetingId", meetingId);
		return  getSqlSession().selectList("MeetingNotice.getSignUpNoticeByCreateId", map);
	}

	public List<Map<String, Object>> selectnoticeindex(long user_id) {
		 return  getSqlSession().selectList("MeetingNotice.selectnoticeindex",user_id);
	}

	@Override
	public Integer updateMeetingIdRead(List<Long> ids) {
		return getSqlSession().update("MeetingNotice.updateMeetingIdRead",ids);
	}
}