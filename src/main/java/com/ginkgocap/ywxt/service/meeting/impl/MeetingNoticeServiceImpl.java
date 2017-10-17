/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingNoticeDao;
import com.ginkgocap.ywxt.dao.meeting.NoticeFieldDao;
import com.ginkgocap.ywxt.dao.meeting.UserDao;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.MeetingVo;
import com.ginkgocap.ywxt.model.meeting.NoticeField;
import com.ginkgocap.ywxt.model.meeting.NoticeMini;
import com.ginkgocap.ywxt.service.meeting.MeetingNoticeService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoticeRelation;

@Service
@Transactional
public class MeetingNoticeServiceImpl implements MeetingNoticeService{
	@Autowired
	private MeetingNoticeDao meetingNoticeDao;
	@Autowired
	private NoticeFieldDao noticeFieldDao;
	@Autowired
	private UserDao userDao;
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public MeetingNotice getById(Long property) {
		return meetingNoticeDao.getById(property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long property){
		 meetingNoticeDao.delete(property);
	}
	/**
	 * 名称: getByReceiverType
	 * 描述: 根据receiverType查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByReceiverType(Integer property) {
		return meetingNoticeDao.getByReceiverType(property);
	}	
	/**
	 * 名称: getByReceiver
	 * 描述: 根据receiver查找
	  * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByReceiver(java.lang.Long property) {
		return meetingNoticeDao.getByReceiver(property);
	}	
	/**
	 * 名称: getByNoticeType
	 * 描述: 根据noticeType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByNoticeType(Integer property) {
		return meetingNoticeDao.getByNoticeType(property);
	}	
	/**
	 * 名称: getByNoticeContent
	 * 描述: 根据noticeContent查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByNoticeContent(java.lang.String property) {
		return meetingNoticeDao.getByNoticeContent(property);
	}	
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByMeetingId(java.lang.Long property) {
		return meetingNoticeDao.getByMeetingId(property);
	}
	/**
	 * 查找通知
	 * @param param
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByParam(Map<String, Object> param) {
		return meetingNoticeDao.getByParam(param);
	}
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByCreateId(java.lang.Long property) {
		return meetingNoticeDao.getByCreateId(property);
	}	
	/**
	 * 名称: getNewNotice
	 * 描述: 获取用户最新的通知
	 * @since  2014-11-4
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public MeetingNotice getNewNotice(Long memberId){
		return meetingNoticeDao.getNewNotice(memberId);
	}
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByCreateName(java.lang.String property) {
		return meetingNoticeDao.getByCreateName(property);
	}	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getByCreateTime(java.util.Date property) {
		return meetingNoticeDao.getByCreateTime(property);
	}	
	/**
	 * 名称: getMyNoticeByMeetingIdAndNoticeType
	 * 描述: 根据会议ID和通知类型查找通知
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getMyNoticeByMeetingIdAndReceiverType(Long receiver,Long meetingId,Integer receiverType) {
		return meetingNoticeDao.getMyNoticeByMeetingIdAndReceiverType(receiver, meetingId, receiverType);
	}
	/**
	 * 获取用户信息-性别
	 * @param listUserId
	 * @return
	 */
	public Map<String, User> getUserMap(List<Long> listUserId) {
		List<User> userList = userDao.getByIds(listUserId);
		Map<String, User> userMap = new HashMap<String, User>();
		for(User user : userList) {
			userMap.put(""+user.getId(), user);
		}
		return userMap;
	}
	/**
	 * 名称: getMeetingNoticeRelation
	 * 描述: 根据用户Id获取的会议通知列表
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNoticeRelation> getMeetingNoticeRelation(Long memberId){
		return meetingNoticeDao.getMeetingNoticeRelation(memberId);
	}
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingNotice entity){
		 meetingNoticeDao.saveOrUpdate(entity);
	}
	/**
	 * 名称: getSignUpNotice
	 * 描述: 获取报名的通知
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingNotice> getSignUpNotice(String field){
		return meetingNoticeDao.getSignUpNotice(field);
	}
	public void addNotice(MeetingNotice meetingNotice, NoticeField noticeField) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 获取最近一条通知
	 */
	public MeetingVo selectnoticeindex(long user_id) {
		List<Map<String,Object>> list = meetingNoticeDao.selectnoticeindex(user_id);
		if(list == null || list.size() == 0) return null;
		
	    Map<String,Object> map = list.get(0);
	    if(map == null) return null;
	    
	    MeetingVo  mv = new MeetingVo();
	    
	    NoticeMini  mi = new NoticeMini();
	    
	   
	    mi.setReceiver(Long.parseLong(map.get("receiver") + ""));
	    mi.setReceiver_name(map.get("receiver_name") + "");
	    mi.setNotice_content(map.get("notice_content") + "");
	    mi.setMeeting_id(Long.parseLong(map.get("meeting_id") + ""));
	    mi.setCreate_time(Utils.DateFormat((Date)map.get("create_time")));
	    
	    mv.setSort((Date)map.get("create_time"));
		mv.setType(2);
		mv.setNotice(mi);
		return mv;
	}
	/**
	 * 获取未读通知数
	 * @param memberId
	 * @return
	 */
	public Integer getUnReadNoticeCount(Long memberId) {
		return meetingNoticeDao.getUnReadNoticeCount(memberId);
	}

	@Override
	public Integer updateMeetingIdRead(List<Long> ids) {
		return meetingNoticeDao.updateMeetingIdRead(ids);
	}
}