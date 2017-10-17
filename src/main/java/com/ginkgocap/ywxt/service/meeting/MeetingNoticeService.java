/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.MeetingVo;
import com.ginkgocap.ywxt.model.meeting.NoticeField;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoticeRelation;

public interface MeetingNoticeService {
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	* @since  2014-09-14
	 * @author qingc
	 */
	public MeetingNotice getById(Long property) ;
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void delete(Long property) ;
	/**
	 * 名称: getByReceiverType
	 * 描述: 根据receiverType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByReceiverType(Integer property);	
	/**
	 * 名称: getByReceiver
	 * 描述: 根据receiver查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByReceiver(Long property);	
	/**
	 * 名称: getByNoticeType
	 * 描述: 根据noticeType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByNoticeType(Integer property);	
	/**
	 * 名称: getByNoticeContent
	 * 描述: 根据noticeContent查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByNoticeContent(String property);	
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByMeetingId(Long property);
	/**
	 * 查找通知
	 * @param param
	 * @return
	 */
	public List<MeetingNotice> getByParam(Map<String, Object> param);
	/**
	 * 名称: getByCreateId
	 * 描述: 根据createId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByCreateId(Long property);	
	/**
	 * 名称: getByCreateName
	 * 描述: 根据createName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByCreateName(String property);	
	/**
	 * 名称: getNewNotice
	 * 描述: 获取用户最新的通知
	 * 	  * @since  2014-11-4
	 * @author qingc
	 */
	public MeetingNotice getNewNotice(Long memberId);	
	
	/**
	 * 名称: getByCreateTime
	 * 描述: 根据createTime查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getByCreateTime(Date property);	
	/**
	 * 名称: getMyNoticeByMeetingIdAndNoticeType
	 * 描述: 根据会议ID和通知类型查找通知
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNotice> getMyNoticeByMeetingIdAndReceiverType(Long receiver,Long meetingId,Integer receiverType) ;	
	/**
	 * 名称: getMeetingNoticeRelation
	 * 描述: 根据用户Id获取的会议通知列表
	 * @since  2014-09-14
	 * @author qingc
	 */
	public List<MeetingNoticeRelation> getMeetingNoticeRelation(Long memberId);
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void saveOrUpdate(MeetingNotice entity) ;
	/**
	 * 名称: addNotice
	 * 描述: 增加通知信息
	 * @since  2014-09-14
	 * @author qingc
	 */
	public void addNotice(MeetingNotice meetingNotice,NoticeField noticeField);
	/**
	 * 名称: getSignUpNotice
	 * 描述: 获取报名的通知
	 * @since  2014-09-14
	 * @author qingc
	 * @return 
	 */
	public List<MeetingNotice> getSignUpNotice(String field);
	
	public MeetingVo selectnoticeindex(long user_id);
	/**
	 * 获取用户信息-性别
	 * @param listUserId
	 * @return
	 */
	public Map<String, User> getUserMap(List<Long> listUserId);
	/**
	 * 获取未读通知数
	 * @param memberId
	 * @return
	 */
	public Integer getUnReadNoticeCount(Long memberId);

	/**
	 * 将通知设为已读
	 * @param ids
	 * @return
	 */
	public Integer updateMeetingIdRead(List<Long> ids);
}