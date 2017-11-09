/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.Date;
import java.util.List;

import com.ginkgocap.ywxt.utils.*;
import com.ginkgocap.ywxt.utils.type.ExcuteMeetSignType;
import org.apache.commons.lang.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingMemberDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingNoticeDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingSignLabelDataDao;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.NoticeField;
import com.ginkgocap.ywxt.service.meeting.MeetingMemberService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.type.AttendMeetStatusType;
import com.ginkgocap.ywxt.utils.type.NoticeReceiverType;
import com.ginkgocap.ywxt.utils.type.NoticeType;
import com.ginkgocap.ywxt.vo.query.meeting.UserBean;
@Service
@Transactional
public class MeetingMemberServiceImpl implements MeetingMemberService{
	@Autowired
	private MeetingMemberDao meetingMemberDao;
	@Autowired
	private MeetingDao meetingDao;
	@Autowired
	private MeetingNoticeDao meetingNoticeDao;
	@Autowired
	private MeetingSignLabelDataDao meetingSignLabelDataDao;
	@Autowired
	private MeetingNotifyService meetingNotifyService;

	// 不需要审核状态
	private static final Byte reviewFlag = 0;
	/**
	 * 名称: getById
	 * 描述: 根据id查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public MeetingMember getById(Long property) {
		return meetingMemberDao.getById(property);
	}
	/**
	 * 名称: delete
	 * 描述: 根据id删除
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long property){
		 meetingMemberDao.delete(property);
	}
	/**
	 * 名称: getByMemberId
	 * 描述: 根据memberId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByMemberId(java.lang.Long property) {
		return meetingMemberDao.getByMemberId(property);
	}	
	/**
	 * 名称: getByMeetingId
	 * 描述: 根据meetingId查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByMeetingId(java.lang.Long property) {
		return meetingMemberDao.getByMeetingId(property);
	}	
	/**
	 * 名称: getVisitantByMeetingId
	 * 描述: 根据meetingId查找嘉宾
	 * @since  2014-09-18
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getVisitantByMeetingId(Long property){
		return meetingMemberDao.getVisitantByMeetingId(property);
	}
	/**
	 * 名称: getByMemberType
	 * 描述: 根据memberType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	/**
	 * 名称: getByMeetingIdAndID
	 * 描述: 根据meetingId和成员memberId查找
	 * @since  2014-09-18
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByMeetingIdAndMemberId(Long meetingId,Long memberId) {
		return meetingMemberDao.getByMeetingIdAndMemberId(meetingId, memberId);
	}	
	/**
	 * 名称: getByMemberName
	 * 描述: 根据memberName查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByMemberName(java.lang.String property) {
		return meetingMemberDao.getByMemberName(property);
	}	
	/**
	 * 名称: getByMemberPhoto
	 * 描述: 根据memberPhoto查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByMemberPhoto(java.lang.String property) {
		return meetingMemberDao.getByMemberPhoto(property);
	}	
	/**
	 * 名称: getByMemberMeetStatus
	 * 描述: 根据memberMeetStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByMemberMeetStatus(java.lang.Integer property) {
		return meetingMemberDao.getByMemberMeetStatus(property);
	}	
	/**
	 * 名称: getByAttendMeetStatus
	 * 描述: 根据attendMeetStatus查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByAttendMeetStatus(java.lang.Integer property) {
		return meetingMemberDao.getByAttendMeetStatus(property);
	}	
	/**
	 * 名称: getByAttendMeetType
	 * 描述: 根据attendMeetType查找
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByAttendMeetType(java.lang.Integer property) {
		return meetingMemberDao.getByAttendMeetType(property);
	}	
	/**
	 * 名称: saveOrUpdate
	 * 描述: 修改或者新增
	 * @since  2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveOrUpdate(MeetingMember entity)throws Exception{
		entity.setAttendMeetTime(new Date());
		 meetingMemberDao.saveOrUpdate(entity);
	}
	/**
	 * 名称: signUp
	 * 描述: 报名
	 * @since  2014-09-14
	 * @author qingc
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void signUp(Meeting meeting,MeetingMember meetingMember,User user) throws Exception{

		// 添加报名记录表
		MeetingNotice meetingNotice = new MeetingNotice();
		// 封装创建人
		meetingNotice.setCreateId(user.getId());
		meetingNotice.setCreateName(user.getName());
		meetingNotice.setCreateTime(new Date());
		// 封装会议ID
		meetingNotice.setMeetingId(meeting.getId());
		// 封装通知内容
		meetingNotice.setNoticeContent(user.getName()+"报名参加"+meeting.getMeetingName());
		// 封装通知类型 (若不需要审核则 NO_REVIEW_MEETING，审核则 SIGN_UP_APPLY)
		if (reviewFlag.equals(meeting.getReviewFlag())) {
			meetingMember.setExcuteMeetSign(ExcuteMeetSignType.AGREE_SIGN_UP.code());
			meetingNotice.setNoticeType(NoticeType.NO_REVIEW_MEETING.code());
		} else {
			meetingNotice.setNoticeType(NoticeType.SIGN_UP_APPLY.code());
		}
		meetingNotice.setIsShow(1);
		// 封装通知接收人
		meetingNotice.setReceiver(meeting.getCreateId());
		meetingNotice.setReceiverName(meeting.getCreateName());
		// 封装接收人类型
		meetingNotice.setReceiverType(NoticeReceiverType.CREATER.code());
		// 封装更新时间
		meetingNotice.setUpdateTime(new Date());
		// 封装通知字段
		NoticeField noticeField = new NoticeField();
		noticeField.setField(user.getId()+Constant.NOTICE_CONTENT_SPLIT_CHAR+user.getName());
		// 添加通知
		addNotice(user,meetingNotice,noticeField,user.getName()+"报名参加"+meeting.getMeetingName(),new Date());
		// 保存成员
		meetingMemberDao.saveOrUpdate(meetingMember);
	}
	/**
	 * 名称: signUpReview
	 * 描述: 报名审核 
	 * @since  2014-09-14
	 * @author qingc
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void signUpReview(Integer status,Meeting meeting,MeetingMember meetingMember,User user) throws Exception{
		meetingMemberDao.saveOrUpdate(meetingMember);
		// 添加报名记录表
		MeetingNotice meetingNotice=new MeetingNotice();
		// 封装创建人
		meetingNotice.setCreateId(user.getId());
		meetingNotice.setCreateName(user.getName());
		meetingNotice.setCreateTime(new Date());
		// 封装会议ID
		meetingNotice.setMeetingId(meeting.getId());
		String content="";
		if(status==0){
			// 封装通知内容
			content=meeting.getCreateName()+"同意您参会";
			meetingNotice.setNoticeContent(content);
			// 封装通知类型
			meetingNotice.setNoticeType(NoticeType.SIGN_UP_SUCCESS.code());
		}else if(status==1){
			// 封装通知内容
			content=meeting.getCreateName()+"拒绝您参会";
			meetingNotice.setNoticeContent(content);
			// 封装通知类型
			meetingNotice.setNoticeType(NoticeType.SIGN_UP_FAILURE.code());
		}
		meetingNotice.setIsShow(1);
		// 封装通知接收人
		meetingNotice.setReceiver(meetingMember.getMemberId());
		meetingNotice.setReceiverName(meetingMember.getMemberName());
		// 封装接收人类型
		meetingNotice.setReceiverType(NoticeReceiverType.PARTICIPANTS.code());
		// 封装更新时间
		meetingNotice.setUpdateTime(new Date());
		// 封装通知字段
		NoticeField noticeField=new NoticeField();
		noticeField.setField(meetingMember.getMemberId()+Constant.NOTICE_CONTENT_SPLIT_CHAR+meetingMember.getMemberName());
//		添加通知
		addNotice(user,meetingNotice,noticeField,content,new Date());
		// 添加会议创建人通知记录表
		MeetingNotice meetingNoticeCreater=new MeetingNotice();
		// 封装创建人
		meetingNoticeCreater.setCreateId(user.getId());
		meetingNoticeCreater.setCreateName(user.getName());
		meetingNoticeCreater.setCreateTime(new Date());
		// 封装会议ID
		meetingNoticeCreater.setMeetingId(meeting.getId());
		if(status==0){
			// 封装通知内容
//			content="你已同意"+meetingMember.getMemberName()+"参会";
			meetingNoticeCreater.setNoticeContent(meetingMember.getMemberName());
			// 封装通知类型
			meetingNoticeCreater.setNoticeType(NoticeType.EXAMINE_SIGN_UP_SUCCESS.code());
		}else if(status==1){
			// 封装通知内容
//			content="你已拒绝"+meetingMember.getMemberName()+"参会";
			meetingNoticeCreater.setNoticeContent(meetingMember.getMemberName());
			// 封装通知类型
			meetingNoticeCreater.setNoticeType(NoticeType.EXAMINE_SIGN_UP_FAILURE.code());
		}
		meetingNoticeCreater.setIsShow(1);
		// 封装通知接收人
		meetingNoticeCreater.setReceiver(meeting.getCreateId());
		meetingNoticeCreater.setReceiverName(meeting.getCreateName());
		// 封装接收人类型
		meetingNoticeCreater.setReceiverType(NoticeReceiverType.CREATER.code());
		// 封装更新时间
		meetingNoticeCreater.setUpdateTime(new Date());
		// 封装通知字段
		NoticeField noticeFieldCreater=new NoticeField();
		noticeFieldCreater.setField(meetingMember.getMemberId()+Constant.NOTICE_CONTENT_SPLIT_CHAR+meetingMember.getMemberName());
//		添加通知
		addNotice(user,meetingNoticeCreater,noticeFieldCreater,"你已同意参会人:"+meetingMember.getMemberName(),new Date());
		updateSignUpNoticeStatus(meetingMember.getMemberId(),meeting.getId());
	}
	/**
	 * 添加通知
	 * @param user
	 * @param meetingNotice
	 * @param noticeField
	 */
	@Transactional(rollbackFor=Exception.class)
	public void addNotice(User user,MeetingNotice meetingNotice,NoticeField noticeField,String content,Date date){
		meetingNoticeDao.addNotice(meetingNotice,noticeField);
		//推送报名通知
		/*
		UserBean userBean = new UserBean();
		userBean.setId(user.getId());
		userBean.setName(user.getName());
		String dateStr=DateUtil.convertDateToStringForChina(new Date());
		GinTongInterface.pushToMeetingCreater(userBean, String.valueOf(meetingNotice.getMeetingId()),content,dateStr);
		*/
	}
	/**
	 * 把报名申请通知设置为不可见
	 * @param meetingId
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateSignUpNoticeStatus(Long createId,Long meetingId){
		List<MeetingNotice> listMeetingNotice=meetingNoticeDao.getSignUpNotice(createId,meetingId);
		if(!Utils.isNullOrEmpty(listMeetingNotice)){
			MeetingNotice meetingNoticeTemp=listMeetingNotice.get(0);
			meetingNoticeTemp.setIsShow(0);
			meetingNoticeTemp.setUpdateTime(new Date());
			meetingNoticeDao.saveOrUpdate(meetingNoticeTemp);
		}
	}
	/**
	 * 名称: changeAttendMeetStatus
	 * 描述: 改变成员参会状态 ：0.未答复 1接受邀请 2拒绝邀请，  5取消报名
	 * @since  2014-09-18
	 * @author qingc
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void changeAttendMeetStatus(Long meetingId,Long memberId,Integer type,User user) throws Exception{
		Meeting meeting = meetingDao.getById(meetingId);
		if (Utils.isNullOrEmpty(meeting)){
			throw new Exception("会议不存在");
		}
		List<MeetingMember> list = this.getByMeetingIdAndMemberId(meetingId, memberId);
		
		if (!Utils.isNullOrEmpty(list)) {
			MeetingMember meetingMember = list.get(0);
			if(!Utils.isNullOrEmpty(meetingMember)){
				// 封装通知列表
				MeetingNotice meetingNotice = new MeetingNotice();
				// 封装创建人
				meetingNotice.setCreateId(user.getId());
				meetingNotice.setCreateName(user.getName());
				meetingNotice.setCreateTime(new Date());
				// 封装会议ID
				meetingNotice.setMeetingId(meeting.getId());
				meetingNotice.setIsShow(1);
				// 封装通知接收人
				meetingNotice.setReceiver(meeting.getCreateId());
				meetingNotice.setReceiverName(meeting.getCreateName());
				// 封装接收人类型
				meetingNotice.setReceiverType(NoticeReceiverType.CREATER.code());
				// 封装更新时间
				meetingNotice.setUpdateTime(new Date());
				// 封装通知字段
				NoticeField noticeField = new NoticeField();
				noticeField.setField(user.getName());
				//添加通知
				String content = "";
				// 接受邀请
				if (AttendMeetStatusType.ACCEPT_INVITATION.code() == type){//接受邀请
					// 封装通知内容
					content = user.getName() + " 接受了您的邀请";
					meetingNotice.setNoticeContent(content);
					// 封装通知类型
					meetingNotice.setNoticeType(NoticeType.ACCEPT_INVITATION.code());
					meetingMember.setAttendMeetStatus(type);
					/** 私密的活动免签 接受邀请直接签到 **/
					if (meeting.getIsSign() == 0 || meeting.getIsSecrecy() == false)
						meetingMember.setIsSign(1);
					this.saveOrUpdate(meetingMember);
					meetingNotifyService.addMeetingNotify(meeting.getCreateId(), user, content, meeting);
				} else if (AttendMeetStatusType.REFUSE_INVITATION.code() == type){// 拒绝邀请
					meetingMember.setAttendMeetStatus(type);	
					// 封装通知内容
					content=user.getName() + " 拒绝了您的邀请";
					meetingNotice.setNoticeContent(content);
					// 封装通知类型
					meetingNotice.setNoticeType(NoticeType.REFUSE_INVITATION.code());
					meetingMember.setAttendMeetStatus(type);
					this.saveOrUpdate(meetingMember);
                    meetingNotifyService.addMeetingNotify(meeting.getCreateId(), user, content, meeting);
				} else if (AttendMeetStatusType.CANCEL_SIGN_UP.code()==type
						|| AttendMeetStatusType.QUIT_MEETING.code()==type){//取消报名，退出会议
					// 取消参会
					if(meeting.getMeetingStatus() <= 2){
						meetingMemberDao.delete(meetingMember.getId());
					} else if (meeting.getMeetingStatus() == 3){
						throw new Exception("会议已经结束不能取消参会");
					}
					// 封装通知类型
					if(AttendMeetStatusType.CANCEL_SIGN_UP.code()==type) {
						meetingNotice.setNoticeType(NoticeType.CANCEL_SIGN_UP.code());
						content = user.getName() + "取消报名";
					} else {
						meetingNotice.setNoticeType(NoticeType.QUIT_MEETING.code());
						content = user.getName() + "退出了会议";
					}
					// 封装通知内容
					meetingNotice.setNoticeContent(content);
					// 退出活动畅聊
					final String groupId = meeting.getGroupId();
					final Long userId = Long.valueOf(memberId);
					ThreadPoolUtils.getExecutorService().execute(new Runnable() {
						@Override
						public void run() {
							if (StringUtils.isNotEmpty(groupId)) {
								GinTongInterface.removeMuc(userId, groupId, userId);
							}
						}
					});
				}
				addNotice(user, meetingNotice, noticeField, content, new Date());
			} else {
				throw new Exception("会议没有邀请您或者您没有报名");
			}
		}
	}
	/**
	 * 名称: changeMyMemberMeetStatus
	 * 描述: 改变成员参会状态 ：0.保存 1 删除  
	 * * @since  2014-09-18
	 * @author qingc
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void changeMyMemberMeetStatus(Long meetingId,Long memberId,Integer type) throws Exception{
		Meeting meeting=meetingDao.getById(meetingId);
		if(Utils.isNullOrEmpty(meeting)){
			throw new Exception("会议不存在");
		}
		MeetingMember meetingMember = null;
		List<MeetingMember> list=this.getByMeetingIdAndMemberId(meetingId, memberId);
		if(!Utils.isNullOrEmpty(list)){
			meetingMember=list.get(0);
		} else {
			throw new Exception("已删除会议");
		}
		if(!Utils.isNullOrEmpty(meetingMember)){
			switch(type){
			case 1:
				// 归档我的会议
				{
					meetingMember.setMemberMeetStatus(type);
					this.saveOrUpdate(meetingMember);
				}
				break;
			case 2:
				if(meeting.getMeetingStatus()!=2){
					if(meeting.getMeetingStatus()==3){
						// 已结束会议移除会议列表
						meetingMember.setMemberMeetStatus(type);
						this.saveOrUpdate(meetingMember);
					}
					if(meeting.getStartTime().after(new Date())){
						// 会议开始前参会是 取消参会
						this.delete(meetingMember.getId());
					}
				}else{
					throw new Exception("不能删除进行中会议");
				}
				break;
			}
		}
	}
	/**
	 * 名称: getSignUpMemberCount
	 * 描述:获取会议报名人数 
	 * @since  2014-09-18
	 * @author qingc
	 */
	@Transactional(readOnly=true)
	public Integer getSignUpMemberCount(Long meetingId){
		return meetingMemberDao.getSignUpMemberCount(meetingId);
	}
	/**
	 * 批量查询会议成员
	 * @param idList
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MeetingMember> getByIdList(List<Long> idList) {
		return meetingMemberDao.getByIdList(idList);
	}
	/**
	 * 批量删除我参加的会议
	 * @param attendIdList
	 */
	public void deleteAttendMeetingBatch(List<Long> attendIdList) {
		meetingMemberDao.deleteAttendMeetingBatch(attendIdList);
	}

	@Override
	public Integer getAttendMeetingCount(Long meetingId) {
		return meetingMemberDao.getAttendMeetingCount(meetingId);
	}
}