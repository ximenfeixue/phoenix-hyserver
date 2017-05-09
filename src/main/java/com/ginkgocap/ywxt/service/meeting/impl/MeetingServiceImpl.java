/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;
import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.common.base.BaseServiceImpl;
import com.ginkgocap.ywxt.dao.meeting.ImRecordmessageDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingDataDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingMemberDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingNoticeDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingOrganDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingPeopleDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingPicDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingSignLabelDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingSignLabelDataDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingTimeDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingTopicDao;
import com.ginkgocap.ywxt.dao.meeting.NoticeFieldDao;
import com.ginkgocap.ywxt.dao.meeting.UserDao;
import com.ginkgocap.ywxt.file.model.FileIndex;
import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.model.meeting.ImRecordmessage;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.model.meeting.MeetingFile;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.model.meeting.MeetingMini;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.MeetingOrgan;
import com.ginkgocap.ywxt.model.meeting.MeetingPeople;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.model.meeting.MeetingSignLabel;
import com.ginkgocap.ywxt.model.meeting.MeetingTime;
import com.ginkgocap.ywxt.model.meeting.MeetingTopic;
import com.ginkgocap.ywxt.model.meeting.MeetingVo;
import com.ginkgocap.ywxt.model.meeting.NoticeField;
import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.service.meeting.MeetingMemberService;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.service.meeting.TopicChatService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.model.UserConfig;
import com.ginkgocap.ywxt.user.service.DynamicNewsService;
import com.ginkgocap.ywxt.user.service.UserConfigService;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.utils.DateUtil;
import com.ginkgocap.ywxt.utils.EmojiUtil;
import com.ginkgocap.ywxt.utils.GinTongInterface;
import com.ginkgocap.ywxt.utils.JsonToBean;
import com.ginkgocap.ywxt.utils.MeetingDict;
import com.ginkgocap.ywxt.utils.ThreadPoolUtils;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.utils.type.AttendMeetStatusType;
import com.ginkgocap.ywxt.utils.type.AttendMeetType;
import com.ginkgocap.ywxt.utils.type.ExcuteMeetSignType;
import com.ginkgocap.ywxt.utils.type.MeetingStatusType;
import com.ginkgocap.ywxt.utils.type.MeetingType;
import com.ginkgocap.ywxt.utils.type.MemberMeetStatusType;
import com.ginkgocap.ywxt.utils.type.MemberType;
import com.ginkgocap.ywxt.utils.type.ModifyMeetingNoticeType;
import com.ginkgocap.ywxt.utils.type.NoticeReceiverType;
import com.ginkgocap.ywxt.utils.type.NoticeType;
import com.ginkgocap.ywxt.vo.query.meeting.BigDataQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingMemberListQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingTopicQuery;
import com.ginkgocap.ywxt.vo.query.meeting.TopicChatQuery;
import com.ginkgocap.ywxt.vo.query.meeting.UserBean;
import com.ginkgocap.ywxt.vo.query.social.Social;
//import com.gintong.easemob.server.httpclient.api.EasemobChatGroupsHandler;

@Service
@Transactional
public class MeetingServiceImpl extends BaseServiceImpl<Meeting, Long> implements MeetingService {
	@Autowired
	private MeetingDao meetingDao;
	@Autowired
	private MeetingDataDao meetingDataDao;
	@Autowired
	private MeetingNoticeDao meetingNoticeDao;
	@Autowired
	private NoticeFieldDao noticeFieldDao;
	@Autowired
	private MeetingSignLabelDao meetingSignLabelDao;
	@Autowired
	private MeetingSignLabelDataDao meetingSignLabelDataDao;
	@Autowired
	private MeetingPicDao meetingPicDao;
	@Autowired
	private MeetingTopicDao meetingTopicDao;
	@Autowired
	private MeetingTimeDao meetingTimeDao;
	@Autowired
	private MeetingMemberDao meetingMemberDao;
	@Autowired
	private MeetingPeopleDao meetingPeopleDao;
	@Autowired
	private FileIndexService fileIndexService;
	@Autowired
	private TopicChatService topicChatService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MeetingDict meetingDict;
	@Autowired
	private MeetingOrganDao meetingOrganDao;
	@Autowired
	private ImRecordmessageDao imRecordmessageDao;
	@Autowired
	private DynamicNewsService dynamicNewsService;
	@Autowired
	private MeetingMemberService meetingMemberService;
	@Autowired
	private UserConfigService userConfigService;

	public interface Callback<T> {
		void callback(T t) throws Exception;
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 名称: getById 描述: 根据id查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public Meeting getById(Long property) {
		return meetingDao.getById(property);
	}
	/**
	 * 名称: delete 描述: 根据id删除
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long property) {
		meetingDao.delete(property);
	}
	/**
	 * 名称: delete 描述: 根据id删除我创建的会议
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long property, Long memberId) throws Exception {
		Meeting meet = this.getById(property);
		if (!Utils.isNullOrEmpty(meet)) {
			logger.error("会议不存在");
			return;
		}
		if (Utils.isNullOrEmpty(meet.getStartTime())) {
			logger.error("会议开始时间不存在");
			return;
		} else {
			if (new Date().after(meet.getStartTime())) {
				throw new Exception("会议已开始，不能删除");
			} else {
				meetingDao.delete(property, memberId);
			}
		}

	}
	/**
	 * 名称: getByMeetingName 描述: 根据meetingName查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByMeetingName(java.lang.String property) {
		return meetingDao.getByMeetingName(property);
	}
	/**
	 * 名称: getByMeetingAddress 描述: 根据meetingAddress查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByMeetingAddress(java.lang.String property) {
		return meetingDao.getByMeetingAddress(property);
	}
	/**
	 * 名称: getByMeetingAddressPos 描述: 根据meetingAddressPos查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByMeetingAddressPos(java.lang.String property) {
		return meetingDao.getByMeetingAddressPos(property);
	}
	/**
	 * 名称: getByStartTime 描述: 根据startTime查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByStartTime(java.util.Date property) {
		return meetingDao.getByStartTime(property);
	}
	/**
	 * 名称: getByEndTime 描述: 根据endTime查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByEndTime(java.util.Date property) {
		return meetingDao.getByEndTime(property);
	}
	/**
	 * 名称: getByMeetingType 描述: 根据meetingType查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByMeetingType(java.lang.Integer property) {
		return meetingDao.getByMeetingType(property);
	}
	/**
	 * 名称: getByMeetingStatus 描述: 根据meetingStatus查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByMeetingStatus(java.lang.Integer property) {
		return meetingDao.getByMeetingStatus(property);
	}
	/**
	 * 名称: getByIsSecrecy 描述: 根据isSecrecy查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByIsSecrecy(java.lang.Boolean property) {
		return meetingDao.getByIsSecrecy(property);
	}
	/**
	 * 名称: getByMemberCount 描述: 根据memberCount查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByMemberCount(java.lang.Integer property) {
		return meetingDao.getByMemberCount(property);
	}
	/**
	 * 名称: getByMeetingDesc 描述: 根据meetingDesc查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByMeetingDesc(java.lang.String property) {
		return meetingDao.getByMeetingDesc(property);
	}
	/**
	 * 名称: getByCrateId 描述: 根据crateId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByCrateId(java.lang.Long property) {
		return meetingDao.getByCrateId(property);
	}
	/**
	 * 名称: getByTaskId 描述: 根据taskId查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByTaskId(java.lang.String property) {
		return meetingDao.getByTaskId(property);
	}
	/**
	 * 名称: getByCrateTime 描述: 根据crateTime查找
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByCrateTime(java.util.Date property) {
		return meetingDao.getByCrateTime(property);
	}
	/**
	 * 名称: saveOrUpdate 描述: 修改或者新增
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Meeting entity) {
		meetingDao.saveOrUpdate(entity);
	}
	/**
	 * 名称: saveOrUpdate 描述: 修改或者新增
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 */
	@Transactional(rollbackFor = Exception.class)
	public Long save(Meeting entity) {
		return meetingDao.save(entity);
	}
	/**
	 * 名称: saveMeetingInterfix 描述: 新增会议以及会议相关的数据
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public Long saveMeetingInterfix(MeetingQuery entity, User user, String fileImgUrl) throws Exception {
		if (!Utils.isNullOrEmpty(entity)) {
			Meeting meeting = new Meeting();
			entity.setCreateTime(new Date());
			org.apache.commons.beanutils.BeanUtils.copyProperties(meeting, entity);
			meeting.setCreateId(user.getId());
			meeting.setCreateName(user.getName());
			// 设置会议的行业
			String industry = meetingDict.getDictStr(entity.getListIndustry(), MeetingDict.INDUSTRY);
			meeting.setIndustry(industry);
			// 创建人信息
			Long createId = Utils.isNullOrEmpty(meeting.getCreateId()) ? 0l : meeting.getCreateId();
			String createName = Utils.isNullOrEmpty(meeting.getCreateName()) ? "" : meeting.getCreateName();
			Date date = new Date();
			meeting.setCreateId(createId);
			meeting.setCraeteName(createName);
			meeting.setCreateTime(date);
			if (meeting.getStartTime().after(new Date()) && MeetingStatusType.DRAFT.code() != meeting.getMeetingStatus()) {
				meeting.setMeetingStatus(MeetingStatusType.NOT_BEGIN.code());
			} else if (meeting.getStartTime().before(new Date()) && MeetingStatusType.DRAFT.code() != meeting.getMeetingStatus()) {
				meeting.setMeetingStatus(MeetingStatusType.IN_MEETING.code());
			}
			Long meetingId = this.save(meeting);

			/**
			 * 添加为了IOS获取到未读消息数
			 */
			List<ImRecordmessage> recordMessageList = new ArrayList<ImRecordmessage>();
			ImRecordmessage recordMessageBean = new ImRecordmessage();
			recordMessageBean.setUserId(Integer.valueOf(String.valueOf(user.getId())));
			recordMessageBean.setMucId(Integer.valueOf(String.valueOf(meetingId)));
			recordMessageBean.setMucCreateUserId(Integer.valueOf(String.valueOf(createId)));
			recordMessageBean.setType(3);// 标示会议
			recordMessageBean.setNewCount(0);
			recordMessageBean.setMucStartDate(meeting.getStartTime());
			recordMessageBean.setMessageStartTime(date);
			recordMessageBean.setStatus(String.valueOf(meeting.getMeetingStatus()));
			recordMessageList.add(recordMessageBean);

			/***
			 * 保存会议成员
			 */
			List<Long> listUserId = new ArrayList<Long>();
			List<Long> hxUserId = new ArrayList<Long>();
			if (!Utils.isNullOrEmpty(entity.getListMeetingMember())) {
				// 去除重复参会人
				Map<String, MeetingMember> mapMeetingMember = distinctListMeetingMember(entity);
				if (!Utils.isNullOrEmpty(mapMeetingMember)) {
					UserBean userBean = new UserBean();
					userBean.setId(user.getId());
					userBean.setName(user.getName());
					for (Entry<String, MeetingMember> entry : mapMeetingMember.entrySet()) {
						MeetingMember meetingMember = entry.getValue();
						if (!Utils.isNullOrEmpty(meetingMember)) {
							listUserId.add(meetingMember.getMemberId());
							meetingMember.setIsSign(0);
							meetingMember.setMeetingId(meeting.getId());
							setPrimarykeyZoroToNull(meetingMember);
							// 审核状态
							meetingMember.setExcuteMeetSign(ExcuteMeetSignType.UNTREATED.code());
							meetingMember.setMemberMeetStatus(MemberMeetStatusType.DEFAULT.code());
							meetingMember.setAttendMeetType(AttendMeetType.INVITATION.code());
							if (meetingMember.getMemberType().intValue() == 0 || meetingMember.getMemberType().intValue() == 2) {
								// 会议创建人和主讲人默认接收邀请，且不显示邀请函
								meetingMember.setIsShowInvitation(0L);
								meetingMember.setAttendMeetStatus(1);
								hxUserId.add(meetingMember.getMemberId());
							}
							meetingMember.setAttendMeetTime(new Date());
							meetingMemberDao.saveOrUpdate(meetingMember);

							recordMessageBean = new ImRecordmessage();
							recordMessageBean.setUserId(Integer.valueOf(String.valueOf(meetingMember.getMemberId())));
							recordMessageBean.setMucId(Integer.valueOf(String.valueOf(meetingMember.getMeetingId())));
							recordMessageBean.setMucCreateUserId(Integer.valueOf(String.valueOf(createId)));
							recordMessageBean.setType(3);// 标示会议
							recordMessageBean.setNewCount(0);
							recordMessageBean.setMucStartDate(meeting.getStartTime());
							recordMessageBean.setMessageStartTime(date);
							recordMessageBean.setStatus(String.valueOf(meeting.getMeetingStatus()));
							recordMessageList.add(recordMessageBean);
						}
					}
				}
			}
			Map<String, List<ImRecordmessage>> imRecordMessageList = new HashMap<String, List<ImRecordmessage>>(1);
			imRecordMessageList.put("imRecordMessageList", recordMessageList);
			imRecordmessageDao.batchSave(imRecordMessageList);

			/**
			 * 集成环信：创建会议
			 */
			final MeetingQuery meetingQuery = entity;
			final List<Long> userIds = hxUserId;
			final Long ownerId = createId;
			final Meeting updatedMeeting = meeting;
			final Long meetingid = meetingId;
			ThreadPoolUtils.getExecutorService().execute(new Runnable() {
				@Override
				public void run() {
					String groupId = createFreeChatGroup(meetingid, meetingQuery, userIds, ownerId);
					updatedMeeting.setGroupId(groupId);
					updatedMeeting.setId(meetingid);
					meetingDao.saveOrUpdate(updatedMeeting);
				}
			});

			/***
			 * 保存会议议题 所有会议聊天记录都与议题相关联，无主讲会议给一个默认议题，页面不显示，仅用于关联连天数据
			 */
			// 无主讲
			if (MeetingType.NOT_HAVE_SPEAKER.code() == meeting.getMeetingType()) {
				MeetingTopic meetingTopic = new MeetingTopic();
				meetingTopic.setMeetingId(meeting.getId());
				meetingTopic.setCreateId(createId);
				meetingTopic.setCreateName(createName);
				meetingTopic.setCreateTime(date);
				meetingTopic.setUpdateTime(date);
				meetingTopic.setTopicContent("无主讲会议默认议题");
				meetingTopicDao.saveOrUpdate(meetingTopic);
			} else if (MeetingType.HAVE_SPEAKER.code() == meeting.getMeetingType()) {
				if (!Utils.isNullOrEmpty(entity.getListMeetingTopicQuery())) {
					for (MeetingTopicQuery meetingTopicQuery : entity.getListMeetingTopicQuery()) {
						if (!Utils.isNullOrEmpty(meetingTopicQuery)) {
							MeetingTopic meetingTopic = meetingTopicQuery.createMeetingTopic();
							setPrimarykeyZoroToNull(meetingTopic);
							meetingTopic.setMeetingId(meeting.getId());
							meetingTopic.setCreateId(createId);
							meetingTopic.setCreateName(createName);
							meetingTopic.setCreateTime(date);
							meetingTopic.setUpdateTime(date);
							meetingTopicDao.saveOrUpdate(meetingTopic);
							// 保存议题图片
							if (!Utils.isNullOrEmpty(meetingTopicQuery.getListMeetingPic())) {
								for (int i = 0; i < meetingTopicQuery.getListMeetingPic().size(); i++) {
									MeetingPic meetingPic = meetingTopicQuery.getListMeetingPic().get(i);
									if (!Utils.isNullOrEmpty(meetingPic)) {
										meetingPic.setMeetingId(meeting.getId());
										meetingPic.setModuleId(meetingTopic.getId());
										meetingPic.setModuleType(MeetingPic.MODULE_TYPE_TOPIC);
										meetingPic.setCreateDate(new Date());
										if (i == 0) {
											meetingPic.setIshomePage(1);
										} else {
											meetingPic.setIshomePage(0);
										}
										meetingPicDao.saveOrUpdate(meetingPic);
									}
								}
							}
						}
					}
				}
			}
			/***
			 * 保存会议时间
			 */
			if (!Utils.isNullOrEmpty(entity.getListMeetingTime())) {
				TreeSet<Date> treeSet = new TreeSet<Date>();
				List<MeetingTime> listMeetingTime = entity.getListMeetingTime();
				for (MeetingTime meetingTime : listMeetingTime) {
					if (!Utils.isNullOrEmpty(meetingTime)) {
						// 把时间放入treeSet
						if (!Utils.isNullOrEmpty(meetingTime.getStartTime())) {
							treeSet.add(meetingTime.getStartTime());
						}
						if (!Utils.isNullOrEmpty(meetingTime.getEndTime())) {
							treeSet.add(meetingTime.getEndTime());
						}
						meetingTime.setMeetingId(meeting.getId());
						meetingTimeDao.saveOrUpdate(meetingTime);
					}
				}
			}
			// 保存会议图片
			String homePage = "";
			if (!Utils.isNullOrEmpty(entity.getListMeetingPic())) {
				for (int i = 0; i < entity.getListMeetingPic().size(); i++) {
					MeetingPic meetingPic = entity.getListMeetingPic().get(i);
					if (!Utils.isNullOrEmpty(meetingPic)) {
						meetingPic.setMeetingId(meeting.getId());
						meetingPic.setModuleId(meeting.getId());
						meetingPic.setModuleType(MeetingPic.MODULE_TYPE_MEETING);
						meetingPic.setCreateDate(new Date());
						if (i == 0) {
							homePage = meetingPic.getPicPath();
							meetingPic.setIshomePage(1);
						} else {
							meetingPic.setIshomePage(0);
						}
						meetingPicDao.saveOrUpdate(meetingPic);
					}
				}
			}
			/***
			 * 保存会议人脉
			 */
			if (!Utils.isNullOrEmpty(entity.getListMeetingPeople())) {
				for (MeetingPeople meetingPeople : entity.getListMeetingPeople()) {
					if (!Utils.isNullOrEmpty(meetingPeople)) {
						setPrimarykeyZoroToNull(meetingPeople);
						meetingPeople.setMeetingId(meeting.getId());
						meetingPeopleDao.saveOrUpdate(meetingPeople);
					}
				}
			}
			/***
			 * 保存会议资料
			 */
			if (!Utils.isNullOrEmpty(entity.getListMeetingData())) {
				for (MeetingData meetingData : entity.getListMeetingData()) {
					if (!Utils.isNullOrEmpty(meetingData)) {
						meetingData.setMeetingId(meeting.getId());
						setPrimarykeyZoroToNull(meetingData);
						meetingData.setCreateTime(new Date());
						meetingDataDao.saveOrUpdate(meetingData);
					}
				}
			}
			/***
			 * 保存会议报名必填字段
			 */
			if (!Utils.isNullOrEmpty(entity.getListMeetingSignLabel())) {
				for (MeetingSignLabel meetingSignLabel : entity.getListMeetingSignLabel()) {
					if (!Utils.isNullOrEmpty(meetingSignLabel)) {
						meetingSignLabel.setMeetingId(meeting.getId());
						meetingSignLabel.setCreateName(createName);
						setPrimarykeyZoroToNull(meetingSignLabel);
						meetingSignLabel.setCreateTime(date);
						meetingSignLabelDao.saveOrUpdate(meetingSignLabel);
					}
				}
			}
			/**
			 * 保存会议组织
			 */
			if (!Utils.isNullOrEmpty(entity.getListMeetingOrgan())) {
				for (MeetingOrgan meetingOrgan : entity.getListMeetingOrgan()) {
					if (!Utils.isNullOrEmpty(meetingOrgan)) {
						meetingOrgan.setMeetingId(meeting.getId());
						setPrimarykeyZoroToNull(meetingOrgan);
						meetingOrganDao.saveOrUpdate(meetingOrgan);
					}
				}
			}
			// 发起会议时 增加动态
			if (!Utils.isNullOrEmpty(meeting.getMeetingStatus())
					&& (meeting.getMeetingStatus().intValue() == 1 || meeting.getMeetingStatus().intValue() == 2)) {
				insertNewsAndRelation(meeting, homePage, user, listUserId);
			}
			return meeting.getId();
		}
		return null;
	}

	protected String createFreeChatGroup(Long meetingId, MeetingQuery entity, List<Long> memberIds, Long ownerId) {
		String meetingName = entity.getMeetingName();
		String meetingDesc = StringUtils.isNullOrEmpty(entity.getMeetingDesc()) ? meetingName : entity.getMeetingDesc().trim();
		int roomsize = 1000;

		if (memberIds == null) {
			memberIds = Collections.emptyList();
		}
		return GinTongInterface.createMUC(meetingId, meetingName, meetingDesc, roomsize, ownerId, memberIds);
	}

	/*
	private String createChatGroup(MeetingQuery entity, List<Long> memberIds, Long ownerId) {
		com.fasterxml.jackson.databind.node.ObjectNode dataObjectNode = com.fasterxml.jackson.databind.node.JsonNodeFactory.instance.objectNode();
		String meetingName = entity.getMeetingName();
		String meetingDesc = entity.getMeetingDesc();
		dataObjectNode.put("groupname", meetingName);
		dataObjectNode.put("desc", StringUtils.isNullOrEmpty(meetingDesc) ? meetingName : meetingDesc.trim());
		dataObjectNode.put("approval", true);
		dataObjectNode.put("public", true);
		dataObjectNode.put("maxusers", com.gintong.easemob.server.comm.Constants.MAXUSERS_SIZE);
		dataObjectNode.put("owner", String.valueOf(ownerId));
		com.fasterxml.jackson.databind.node.ArrayNode arrayNode = com.fasterxml.jackson.databind.node.JsonNodeFactory.instance.arrayNode();
		for (Long memberId : memberIds) {
			arrayNode.add(String.valueOf(memberId));
		}
		dataObjectNode.put("members", arrayNode);
		com.fasterxml.jackson.databind.node.ObjectNode creatChatGroupNode = EasemobChatGroupsHandler.creatChatGroups(dataObjectNode);
		logger.info(" Meeting created result from huanxin server : " + creatChatGroupNode);
		return creatChatGroupNode.get("data").get("groupid").asText();
	}*/

	private void insertNewsAndRelation(Meeting meeting, String homePage, User user, List<Long> listUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", "40");
		param.put("lowType", "");
		param.put("createrId", String.valueOf(user.getId()));
		param.put("createrName", user.getName());
		param.put("picPath", user.getPicPath());
		param.put("title", meeting.getMeetingName() + "");
		param.put("content", meeting.getMeetingDesc() + "");
		param.put("targetId", meeting.getId() + "");
		if (meeting.getCreateUserType() == 2) {// 组织创建
			param.put("createType", "2");
			param.put("gender", "0");// 性别未知
		} else {
			param.put("createType", "1");
			param.put("gender", user.getSex());
		}
		// 附件表查询第一张图片
		param.put("imgPath", homePage);
		param.put("forwardingContent", "");
		Map<String, List<Long>> mapUserRight = new HashMap<String, List<Long>>();
		List<Long> dales = new ArrayList<Long>();
		List<Long> zhongles = new ArrayList<Long>();
		List<Long> xiaoles = new ArrayList<Long>();
		for (Long userId : listUserId) {
			if (userId.longValue() == user.getId()) {
				xiaoles.add(userId);
			} else {
				dales.add(userId);
			}
		}
		mapUserRight.put("dale", dales);
		mapUserRight.put("zhongle", zhongles);
		mapUserRight.put("xiaole", xiaoles);
		param.put("receiverIds", mapUserRight);
		dynamicNewsService.insertNewsAndRelationByParam(param);
	}
	/**
	 * 名称: getInMeetingByMemberId 描述: 获取会议中的会议列表
	 * 
	 * @since 2014-11-3
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberId(Long memberId, String beforeOrAfter, Date lastNoticeUpdateDate,
			boolean invitationBeforeNotice) {
		return meetingDao.getMeetingListByMemberId(memberId, beforeOrAfter, lastNoticeUpdateDate, invitationBeforeNotice);
	}
	/**
	 * 名称: getListOtherMeeting 描述: 获取未开始和已结束的会议
	 * 
	 * @since 2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getListOtherMeeting(Long memberId) {
		return meetingDao.getListOtherMeeting(memberId);
	}
	/**
	 * 名称: getMeetingListByMemberIdAfterNotice 描述: 获取最后通知时间之后5的成员的会议列表和邀请函
	 * 
	 * @since 2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberIdAfterNotice(Long memberId, Date lastNoticeUpdateDate, boolean invitationBeforeNotice) {
		return meetingDao.getMeetingListByMemberIdAfterNotice(memberId, lastNoticeUpdateDate, invitationBeforeNotice);
	}
	/**
	 * 名称: getMeetingListByMemberId 描述: 获取成员的会议列表和邀请函
	 * 
	 * @since 2014-09-17
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<MeetingMemberListQuery> getInMeetingByMemberId(Long memberId) {
		return meetingDao.getInMeetingByMemberId(memberId);
	}
	/**
	 * 名称: getMyMeetingSquare 描述: 获取会议广场
	 * 
	 * @since 2014-09-22
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<MeetingMemberListQuery> getMyMeetingSquare(Long memberId, Date beginDate, Date endDate, Integer index, Integer size,
			Integer isCurrent, String city, Double addressPosX, Double addressPosY, String industry, String keyword) {
		List<MeetingMemberListQuery> listMeeting = meetingDao.getMyMeetingSquare(memberId, beginDate, endDate, index, size, isCurrent, city,
				addressPosX, addressPosY, industry, keyword);
		// 设置创建者名称、头像
		if (!Utils.isNullOrEmpty(listMeeting)) {
			List<Long> userIdList = new ArrayList<Long>();
			List<Long> meetingIdList = new ArrayList<Long>();
			for (MeetingMemberListQuery meeting : listMeeting) {
				if (!Utils.isNullOrEmpty(meeting.getCreateId())) {
					userIdList.add(meeting.getCreateId());
				}
				if (!Utils.isNullOrEmpty(meeting.getId())) {
					meetingIdList.add(meeting.getId());
				}
			}
			Map<String, User> userMap = userDao.getUserMapByIds(userIdList);
			Map<String, Integer> attemdCountMap = meetingMemberDao.getAttendMeetingCount(meetingIdList);
			for (MeetingMemberListQuery meeting : listMeeting) {
				User user = userMap.get("" + meeting.getCreateId());
				if (!Utils.isNullOrEmpty(user)) {
					meeting.setCreateName(user.getName());
					meeting.setCreateImage(user.getPicPath());
				}
				if (!Utils.isNullOrEmpty(attemdCountMap.get("" + meeting.getId()))) {
					meeting.setCount(attemdCountMap.get("" + meeting.getId()));
				}
			}
		}
		return listMeeting;
	}
	/**
	 * 名称: getMyMeetingSquareCount 描述: 获取会议广场条数
	 * 
	 * @since 2014-09-22
	 * @author qingc
	 */
	public Integer getMyMeetingSquareCount(Long memberId, Date beginDate, Date endDate, Integer isCurrent, String city, Double addressPosX,
			Double addressPosY, String industry, String keyword) {
		return meetingDao.getMyMeetingSquareCount(memberId, beginDate, endDate, isCurrent, city, addressPosX, addressPosY, industry, keyword);
	}
	/**
	 * 获取我参加的和我创建的会议列表
	 * 
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MeetingMemberListQuery> getMyAttendAndCreateMeeting(Map<String, Object> param) {
		return meetingDao.getMyAttendAndCreateMeeting(param);
	}
	/**
	 * 查询我参加的会议数量
	 * 
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = true)
	public Long getMyAttendAndCreateMeetingCount(Map<String, Object> param) {
		return meetingDao.getMyAttendAndCreateMeetingCount(param);
	}
	/**
	 * 名称: getMyCreateMeeting 描述: 获取我创建的会议列表
	 * 
	 * @since 2014-09-18
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public List<MeetingMemberListQuery> getMyCreateMeeting(Long memberId, Integer year, Integer month) {
		return meetingDao.getMyCreateMeeting(memberId, year, month);
	}
	/**
	 * 名称: getInvitationCount 描述: 获取邀请函数量
	 * 
	 * @since 2014-09-17
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public Integer getInvitationCount(Long memberId) {
		return meetingDao.getInvitationCount(memberId);
	}
	/**
	 * 名称: getMyInvitation 描述: 获取邀我的请函
	 * 
	 * @since 2014-09-17
	 * @author qingc
	 */
	@Transactional
	public List<MeetingMemberListQuery> getMyInvitation(Long memberId) {
		List<MeetingMemberListQuery> result = meetingDao.getMyInvitation(memberId);
		if (null != result && result.size() > 0) {
			List<Long> userIdList = new ArrayList<Long>();
			for (MeetingMemberListQuery query : result) {
				if (!Utils.isNullOrEmpty(query.getCreateId())) {
					userIdList.add(query.getCreateId());
				}
			}
			Map<String, User> userMap = userDao.getUserMapByIds(userIdList);
			for (MeetingMemberListQuery query : result) {
				User user = userMap.get(query.getCreateId());
				if (!Utils.isNullOrEmpty(user)) {
					query.setCreateName(user.getName());
					query.setCreateImage(user.getPicPath());
				}
			}
			imRecordmessageDao.updateMeetingInvitationForReadStatus(memberId);// 更新未读消息
		}
		return result;
	}
	/**
	 * 名称: getMyLastInvitation 描述: 获取邀我的最后一条请函
	 * 
	 * @since 2014-09-17
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMyLastInvitation(Long memberId) {
		return meetingDao.getMyLastInvitation(memberId);
	}
	/**
	 * 名称: getMeetingByIdAndMemberId 描述: 根据会议和成员id获取会议详情
	 * 
	 * @since 2014-09-17
	 * @author qingc
	 */
	@Transactional(readOnly = true)
	public MeetingQuery getMeetingByIdAndMemberId(Long id, Long memberId) throws IllegalAccessException, InvocationTargetException {
		MeetingQuery meetingObj = new MeetingQuery();
		// 封装会议基本信息
		if (!Utils.isNullOrEmpty(id)) {
			Meeting meeting = this.getById(id);
			if (!Utils.isNullOrEmpty(meeting)) {
				boolean flag = false;
				if (Utils.isNullOrEmpty(meeting.getCreateTime())) {
					meeting.setCreateTime(new Date());
					flag = true;
				}
				BeanUtils.copyProperties(meetingObj, meeting);
				if (flag) {
					meetingObj.setCreateTime(null);
				}
				// 设置行业
				if (!Utils.isNullOrEmpty(meeting.getIndustry())) {
					List<String> listIndustry = meetingDict.getDictList(meeting.getIndustry(), MeetingDict.INDUSTRY);
					meetingObj.setListIndustry(listIndustry);
				}
				// 封装会议成员信息
				List<Long> userIdList = new ArrayList<Long>();
				if (!isNullOrEmpty(meeting) && !isNullOrEmpty(meeting.getCreateId())) {
					userIdList.add(meeting.getCreateId());
				}
				List<MeetingMember> listMember = meetingMemberDao.getByMeetingId(id);
				if (!isNullOrEmpty(listMember)) {
					for (MeetingMember meetingMember : listMember) {
						if (!isNullOrEmpty(meetingMember) && !isNullOrEmpty(meetingMember.getMemberId())) {
							userIdList.add(meetingMember.getMemberId());
						}
					}
				}
				if (!userIdList.isEmpty()) {
					Map<String, User> userMap = userDao.getUserMapByIds(userIdList);
					if (!isNullOrEmpty(meeting) && !isNullOrEmpty(meeting.getCreateId())) {
						User user = userMap.get("" + meeting.getCreateId());
						if (!isNullOrEmpty(user)) {
							meetingObj.setCreateName(user.getName());
							meetingObj.setCreateImage(user.getPicPath());
							meetingObj.setCreateCompany(user.getCompanyName());
							meetingObj.setCreateJob(user.getCompanyJob());
						}
					}
					if (!isNullOrEmpty(listMember)) {
						for (MeetingMember meetingMember : listMember) {
							if (!isNullOrEmpty(meetingMember) && !isNullOrEmpty(meetingMember.getMemberId())) {
								User user = userMap.get("" + meetingMember.getMemberId());
								if (!isNullOrEmpty(user)) {
									meetingMember.setMemberName(user.getName());
									meetingMember.setMemberPhoto(user.getPicPath());
								}
							}
						}
					}
				}
				meetingObj.setListMeetingMember(listMember);
				// 封装会议议题信息
				List<MeetingTopic> listTopic = meetingTopicDao.getByMeetingId(id);
				if (!Utils.isNullOrEmpty(listTopic)) {
					List<Long> topicUserIdList = new ArrayList<Long>();
					for (MeetingTopic topic : listTopic) {
						topicUserIdList.add(topic.getCreateId());
					}
					List<User> userList = userDao.getByIds(topicUserIdList);
					Map<String, User> userMap = new HashMap<String, User>();
					if (!Utils.isNullOrEmpty(userList)) {
						for (User user : userList) {
							userMap.put("" + user.getId(), user);
						}
					}
					for (MeetingTopic topic : listTopic) {
						User user = userMap.get("" + topic.getMemberId());
						if (!Utils.isNullOrEmpty(user)) {
							topic.setCreateName(user.getName());
							topic.setMemberName(user.getName());
							topic.setMemberPic(user.getPicPath());
						}
					}
				}
				meetingObj.setListMeetingTopic(listTopic);
				// 封装会议时间信息
				List<MeetingTime> listTime = meetingTimeDao.getByMeetingId(id);
				meetingObj.setListMeetingTime(listTime);
				// 封装会议图片信息
				List<MeetingPic> listPic = meetingPicDao.getByModuleId(id, MeetingPic.MODULE_TYPE_MEETING);
				List<String> listFileIndexId = new ArrayList<String>();
				if (!Utils.isNullOrEmpty(listPic)) {
					for (MeetingPic meetingPic : listPic) {
						if (!Utils.isNullOrEmpty(meetingPic)) {
							listFileIndexId.add(meetingPic.getFileIndexId());
							// if(Utils.isNullOrEmpty(meetingPic.getWidth())
							// || Utils.isNullOrEmpty(meetingPic.getHeight())) {
							// BufferedImage bufferedImage =
							// MeetingPicUtil.getBufferedImage(meetingPic.getPicPath());
							// if(!Utils.isNullOrEmpty(bufferedImage)) {
							// meetingPic.setWidth(""+bufferedImage.getWidth());
							// meetingPic.setHeight(""+bufferedImage.getHeight());
							// }
							// }
							if (1 == meetingPic.getIshomePage()) {
								meetingObj.setPath(meetingPic.getPicPath());
							}
						}
					}
				}
				meetingObj.setListMeetingPic(listPic);
				// 封装会议视频文件
				List<FileIndex> listFileIndex = fileIndexService.selectByTaskId(meeting.getTaskId(), "1");
				List<MeetingFile> listMeetingFile = new ArrayList<MeetingFile>();
				if (!Utils.isNullOrEmpty(listFileIndex)) {
					for (FileIndex fileIndex : listFileIndex) {
						if (!Utils.isNullOrEmpty(fileIndex) && !listFileIndexId.contains(fileIndex.getId())) {
							MeetingFile meetingFile = JsonToBean.fileIndexToMeetingFile(fileIndex);
							listMeetingFile.add(meetingFile);
						}
					}
				}
				if (!Utils.isNullOrEmpty(listMeetingFile)) {
					meetingObj.setListMeetingFile(listMeetingFile);
				}
				// 封装会议人脉信息
				List<MeetingPeople> listPeople = meetingPeopleDao.getByMeetingId(id);
				meetingObj.setListMeetingPeople(listPeople);
				// 封装会议资料信息
				List<MeetingData> listData = meetingDataDao.getByMeetingId(id);
				meetingObj.setListMeetingData(listData);
				// 封装会议组织
				List<MeetingOrgan> listMeetingOrgan = meetingOrganDao.getByMeetingId(id);
				meetingObj.setListMeetingOrgan(listMeetingOrgan);
				// 封装标签列表
				List<MeetingSignLabel> listMeetingSignLabel = meetingSignLabelDao.getByMeetingId(id);
				meetingObj.setListMeetingSignLabel(listMeetingSignLabel);
			} else {
				meetingObj = null;
			}
		}
		return meetingObj;
	}
	/**
	 * 名称: changeMeetingStatus 描述: 改变会议状态
	 * 
	 * @since 2014-10-01
	 * @author qingc
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean changeMeetingStatus(Long meetingId, Integer meetingStatus, Long userId) throws Exception {
		boolean flag = false;
		Meeting meeting = this.getById(meetingId);
		if (Utils.isNullOrEmpty(meeting)) {
			logger.info("会议不存在");
			throw new Exception("会议不存在");
		}
		if (!userId.equals(meeting.getCreateId())) {
			logger.info("只有会议创建者才能修改");
			throw new Exception("只有会议创建者才能修改");
		} else {
			switch (meetingStatus) {
				case 1 : {
					if (Utils.isNullOrEmpty(meeting.getMeetingStatus()) || meeting.getMeetingStatus() != MeetingStatusType.DRAFT.code()) {
						throw new Exception("只能发起草稿会议");
					}
				}
					break;
				case 2 : {
					if (Utils.isNullOrEmpty(meeting.getStartTime()) || meeting.getStartTime().after(new Date())) {
						throw new Exception("只有在会议开始时间到了才能把会议改为会议进行中");
					}
					if (MeetingStatusType.ENDED.code() == meeting.getMeetingStatus()
							|| MeetingStatusType.IN_MEETING.code() == meeting.getMeetingStatus()) {
						throw new Exception(meeting.getMeetingStatus() == 3 ? "不能把结束的会议改为进行中" : "会议已经在进行中");
					}
				}
					break;
				case 3 : {
					// 修改会议结束时间为操作的时间
					meeting.setEndTime(new Date());
				}
					break;
				default : {
					throw new Exception("meetingStatus类型错误");
				}

			}
			meeting.setMeetingStatus(meetingStatus);
			try {
				this.saveOrUpdate(meeting);
				flag = true;
			} catch (Exception e) {
			}
		}
		return flag;
	}
	/**
	 * 名称: changeMeetingStatus 描述: 把会议状态改为会议已开始
	 * 
	 * @since 2014-10-01
	 * @author qingc
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean changeMeetingStatusToBegin(Long meetingId) throws Exception {
		boolean flag = false;
		Meeting meeting = this.getById(meetingId);
		if (Utils.isNullOrEmpty(meeting)) {
			logger.info("会议不存在");
			throw new Exception("会议不存在");
		}
		if (MeetingStatusType.ENDED.code() == meeting.getMeetingStatus() || MeetingStatusType.IN_MEETING.code() == meeting.getMeetingStatus()) {
			throw new Exception(meeting.getMeetingStatus() == 3 ? "不能把结束的会议改为进行中" : "会议已经在进行中");
		}
		meeting.setMeetingStatus(MeetingStatusType.IN_MEETING.code());
		try {
			this.saveOrUpdate(meeting);
			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}
	/**
	 * 名称: getAllNotBeginMeetng 描述: 获取所有未开始的会议
	 * 
	 * @since 2014-10-01
	 * @author qingc
	 */
	public List<Meeting> getAllNotBeginMeetng() {
		return meetingDao.getAllNotBeginMeetng();
	}
	/**
	 * 名称: getMeetingListByMemberIdAndDate 描述: 根据时间和memberId 获取会议
	 * 
	 * @since 2014-10-01
	 * @author qingc
	 */
	public List<MeetingMemberListQuery> getMeetingListByMemberIdAndDate(Date beginDate, Date endDate, Long memberId) throws Exception {
		return meetingDao.getMeetingListByMemberIdAndDate(beginDate, endDate, memberId);
	}
	/**
	 * 名称: getPigeonholeMeetingListByMemberIdAndDate 描述: 根据时间和memberId 获取归档会议
	 * 
	 * @since 2014-10-01
	 * @author qingc
	 * @throws Exception
	 */
	public List<MeetingMemberListQuery> getPigeonholeMeetingListByMemberIdAndDate(Date beginDate, Date endDate, Long memberId) throws Exception {
		return meetingDao.getPigeonholeMeetingListByMemberIdAndDate(beginDate, endDate, memberId);
	}
	/**
	 * 名称: cancelNotBenginMeeting 描述: 取消未开始会议
	 * 
	 * @since 2014-10-01
	 * @author qingc
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void cancelNotBenginMeeting(Long meetingId, Long memberId) throws Exception {
		Meeting meeting = meetingDao.getById(meetingId);
		if (Utils.isNullOrEmpty(meeting)) {
			throw new Exception("会议不存在");
		}
		// 会议状态 0：草稿，1：发起,2会议进行中，3会议结束
		if (!Utils.isNullOrEmpty(meeting.getMeetingStatus())) {
			if (meeting.getMeetingStatus() == 1) {
				// 已发起的会议，向参会人发送删除会议通知
				List<MeetingMember> listMeetingMember = meetingMemberDao.getAttendMemberByMeetingId(meetingId);
				if (!Utils.isNullOrEmpty(listMeetingMember)) {
					Map<String, String> userMap = new HashMap<String, String>();
					List<Long> userIds = new ArrayList<Long>();
					for (MeetingMember meetingMember : listMeetingMember) {
						userIds.add(meetingMember.getMemberId());
					}
					List<User> userList = userDao.getByIds(userIds);
					if (!Utils.isNullOrEmpty(userList)) {
						for (User user : userList) {
							userMap.put("" + user.getId(), user.getName());
						}
					}
					for (MeetingMember meetingMember : listMeetingMember) {
						MeetingNotice meetingNotice = new MeetingNotice();
						// 修改会议向参会人发通知
						if (meetingMember.getMemberType().intValue() == 2) {
							meetingNotice.setReceiverType(NoticeReceiverType.CREATER.code());
						} else {
							meetingNotice.setReceiverType(NoticeReceiverType.PARTICIPANTS.code());
						}
						meetingNotice.setNoticeContent(meeting.getCreateName() + "删除了会议");
						meetingNotice.setCreateId(meeting.getCreateId());
						meetingNotice.setCreateName(userMap.get("" + meeting.getCreateId().longValue()));
						meetingNotice.setCreateTime(new Date());
						meetingNotice.setUpdateTime(new Date());
						meetingNotice.setMeetingId(meeting.getId());
						meetingNotice.setIsShow(1);
						meetingNotice.setReceiver(meetingMember.getMemberId());
						meetingNotice.setReceiverName(userMap.get("" + meetingMember.getMemberId().longValue()));
						meetingNotice.setNoticeType(NoticeType.DELETE_MEETING.code());
						meetingNoticeDao.saveOrUpdate(meetingNotice);
					}
				}
			} else if (meeting.getMeetingStatus() == 2) {
				throw new Exception("不能删除进行中会议");
			} else if (meeting.getMeetingStatus() == 3) {
				// 已结束会议从自己的会议列表移除
				List<MeetingMember> list = meetingMemberDao.getByMeetingIdAndMemberId(meetingId, memberId);
				if (!Utils.isNullOrEmpty(list)) {
					MeetingMember meetingMember = list.get(0);
					if (!Utils.isNullOrEmpty(meetingMember)) {
						meetingMember.setMemberMeetStatus(2);// 0：默认，1：归档，2：删除
						meetingMemberDao.saveOrUpdate(meetingMember);
					}
				}
			}
		}
		if (Utils.isNullOrEmpty(meeting.getCreateId()) || meeting.getCreateId().intValue() != memberId.intValue()) {
			throw new Exception("只有创建者才能删除未开始的会议");
		}
		// 设置会议删除标志位
		meetingDao.delete(meetingId);
		// 推送消息
		GinTongInterface.addMeetingIndex(meetingId, "del");
		/**
		 * 集成环信：删除会议
		 */
		final String chatgroupid = meeting.getGroupId();
		final long creatorId = Long.valueOf(memberId);
		if (!Utils.isNullOrEmpty(chatgroupid)) {
			ThreadPoolUtils.getExecutorService().execute(new Runnable() {
				@Override
				public void run() {
					// 2016-03-14 tanmin 同步删除会议对应的畅聊,创建者将自己退出会议即为解散
					GinTongInterface.exitFromMUC(creatorId, creatorId, chatgroupid);
				}
			});
		} else {
			logger.error("执行 " + this.getClass().getName() + " 类中的 cancelNotBenginMeeting 方法时出错：环信的群组 Id ( groupId ) 为空，删除环信服务器上的该群组失败");
		}
	}
	/**
	 * 名称: getMyForwardingMeeting 描述: 根据会议id和议题id获取转发会议的详细信息
	 * 
	 * @since 2015-1-24
	 * @author qingc
	 * @throws Exception
	 */
	public MeetingQuery getForwardingMeetingData(Long meetingId, Long topicId) throws Exception {
		// 获取可转发的会议列表
		MeetingQuery meetingQuery = null;
		Meeting meeting = meetingDao.getById(meetingId);
		if (Utils.isNullOrEmpty(meeting)) {
			throw new Exception("会议不存在");
		} else {
			meetingQuery = new MeetingQuery();
			org.apache.commons.beanutils.BeanUtils.copyProperties(meetingQuery, meeting);
			if (!Utils.isNullOrEmpty(meetingQuery) && !Utils.isNullOrEmpty(meetingQuery.getId())) {
				// 获取议题
				List<MeetingTopic> listMeetingTopic = new ArrayList<MeetingTopic>();
				MeetingTopic meetingTopic = meetingTopicDao.getById(topicId);
				// 封装议题
				if (!Utils.isNullOrEmpty(meetingTopic)) {
					listMeetingTopic.add(meetingTopic);
					meetingQuery.setListMeetingTopic(listMeetingTopic);
				}
				List<MeetingTopicQuery> listMeetingTopicQuery = getMeetingTopicQuerys(listMeetingTopic);
				if (!Utils.isNullOrEmpty(listMeetingTopicQuery)) {
					meetingQuery.setListMeetingTopicQuery(listMeetingTopicQuery);
				}
				// 获取会议成员
				List<MeetingMember> listMeetingMember = meetingMemberDao.getByMeetingId(meetingQuery.getId());
				// 封装会议成员
				if (!Utils.isNullOrEmpty(listMeetingMember)) {
					meetingQuery.setListMeetingMember(listMeetingMember);
				}
			}
		}
		return meetingQuery;
	}
	/**
	 * 名称: getMyForwardingMeeting 描述: 获取我的可转发会议
	 * 
	 * @since 2014-11-24
	 * @author qingc
	 * @throws Exception
	 */
	public List<MeetingQuery> getMyForwardingMeeting(Long userId) throws Exception {
		List<MeetingQuery> listResult = new ArrayList<MeetingQuery>();
		// 获取可转发的会议列表
		List<MeetingQuery> list = meetingDao.getMyForwardingMeeting(userId);
		if (Utils.isNullOrEmpty(list)) {
			throw new Exception("没有可转发的会议");
		} else {
			for (MeetingQuery meetingQuery : list) {
				if (!Utils.isNullOrEmpty(meetingQuery) && !Utils.isNullOrEmpty(meetingQuery.getId())) {
					// 获取议题
					List<MeetingTopic> listMeetingTopic = meetingTopicDao.getByMeetingId(meetingQuery.getId());
					// 封装议题
					if (!Utils.isNullOrEmpty(listMeetingTopic)) {
						meetingQuery.setListMeetingTopic(listMeetingTopic);
					}
					List<MeetingTopicQuery> listMeetingTopicQuery = getMeetingTopicQuerys(listMeetingTopic);
					if (!Utils.isNullOrEmpty(listMeetingTopicQuery)) {
						meetingQuery.setListMeetingTopicQuery(listMeetingTopicQuery);
					}
					// 获取会议成员
					List<MeetingMember> listMeetingMember = meetingMemberDao.getByMeetingId(meetingQuery.getId());
					// 封装会议成员
					if (!Utils.isNullOrEmpty(listMeetingMember)) {
						meetingQuery.setListMeetingMember(listMeetingMember);
					}
				}
				listResult.add(meetingQuery);
			}
		}
		return listResult;
	}

	public List<MeetingTopicQuery> getMeetingTopicQuerys(List<MeetingTopic> listMeetingTopic) {
		if (!Utils.isNullOrEmpty(listMeetingTopic)) {
			List<MeetingTopicQuery> mtqs = new ArrayList<MeetingTopicQuery>();
			for (int i = 0; i < listMeetingTopic.size(); i++) {
				MeetingTopic mt = listMeetingTopic.get(i);
				MeetingTopicQuery mtq = new MeetingTopicQuery();
				mtq.setId(mt.getId());
				mtq.setMeetingId(mt.getMeetingId());
				mtq.setTopicContent(mt.getTopicContent());
				mtq.setTopicStartTime(mt.getTopicStartTime());
				mtq.setTopicEndTime(mt.getTopicEndTime());
				mtq.setTopicDesc(mt.getTopicDesc());
				mtq.setTaskId(mt.getTaskId());
				mtq.setMemberId(mt.getMemberId());
				mtq.setMemberName(mt.getMemberName());
				mtq.setMemberPic(mt.getMemberPic());
				mtq.setMemberDesc(mt.getMemberDesc());
				mtq.setCreateId(mt.getCreateId());
				mtq.setCreateName(mt.getCreateName());
				mtq.setCreateTime(mt.getCreateTime());
				mtq.setUpdateTime(mt.getUpdateTime());
				List<FileIndex> fileIndexs = fileIndexService.selectByTaskId(mt.getTaskId(), "1");
				List<MeetingFile> listMeetingFile = null;
				if (fileIndexs != null && fileIndexs.size() > 0) {
					listMeetingFile = new ArrayList<MeetingFile>();
					for (int j = 0; j < fileIndexs.size(); j++) {
						MeetingFile meetingFile = JsonToBean.fileIndexToMeetingFile(fileIndexs.get(j));
						listMeetingFile.add(meetingFile);
					}
				}
				mtq.setListMeetingFile(listMeetingFile);
				List<TopicChat> topicChats = topicChatService.getByMeetingId(mt.getMeetingId());
				List<Long> listUserId = new ArrayList<Long>();
				for (TopicChat tc : topicChats) {
					listUserId.add(tc.getMemberId());
					tc.setChatContent(EmojiUtil.emojiRecover(tc.getChatContent()));// 处理emoji表情
				}
				List<User> listUser = userDao.getByIds(listUserId);
				try {
					mtq.setListTopicChat(TopicChatQuery.copyList(topicChats, listUser));
				} catch (Exception e) {
					logger.error("", e);
				}
				mtqs.add(mtq);
			}
			return mtqs;
		}
		return null;
	}

	/**
	 * 设置主键为0的设置为null
	 * 
	 * @param obj
	 */
	public static void setPrimarykeyZoroToNull(Object obj) {
		Utils.setPrimarykeyZoroToNull(obj);
	}

	/**
	 * 去除参会人和主讲人重复的记录
	 * 
	 * @param entity
	 * @return
	 */
	public Map<String, MeetingMember> distinctListMeetingMember(MeetingQuery entity) {
		Map<String, MeetingMember> mapMeetingMember = new HashMap<String, MeetingMember>();
		for (MeetingMember meetingMember : entity.getListMeetingMember()) {
			if (!Utils.isNullOrEmpty(meetingMember)) {
				String key = meetingMember.getMemberType() + "-" + meetingMember.getMemberId().toString();
				if (Utils.isNullOrEmpty(mapMeetingMember.get(key))) {
					mapMeetingMember.put(key, meetingMember);
				} else {
					MeetingMember meetingMemberTemp = mapMeetingMember.get(key);
					// 如果已存在为参会人，则添加进嘉宾记录，移除参会人
					if (MemberType.PARTICIPANTS.code() == meetingMemberTemp.getMemberType() && MemberType.VIP.code() == meetingMember.getMemberType()) {
						mapMeetingMember.remove(key);
						mapMeetingMember.put(key, meetingMember);
					} else {
						mapMeetingMember.put(key, meetingMember);
					}
				}
			}
		}
		return mapMeetingMember;
	}

	/**
	 * 更新会议的各种属性
	 * 
	 * @param list
	 * @param cb
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public <T> void updateMeetingProperty(List<T> list, Callback<T> cb) throws Exception {
		if (!isNullOrEmpty(list)) {
			for (T t : list) {
				cb.callback(t);
			}
		}
	}
	/**
	 * @param listBefore
	 * @param listAfter
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public <T> boolean isModifyOfTwoList(List<T> listBefore, List<T> listAfter) {
		if (isNullOrEmpty(listBefore) && isNullOrEmpty(listAfter)) {
			return false;
		}
		if (isNullOrEmpty(listBefore) && !isNullOrEmpty(listAfter)) {
			return true;
		}
		if (!isNullOrEmpty(listBefore) && isNullOrEmpty(listAfter)) {
			return true;
		}
		TreeSet<T> set1 = new TreeSet<T>(listBefore);
		TreeSet<T> set2 = new TreeSet<T>(listAfter);
		return !Utils.treeSetEquals(set1, set2);
	}

	/**
	 * 更新主讲人
	 * 
	 * @param list
	 *            主讲人列表 return true:主讲人有修改；false:主讲人没有修改
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateListSpeakker(List<MeetingMember> list, Long meetingId) throws Exception {
		// 如果新的主讲人为空，删除所有主讲人
		List<MeetingMember> listMember = meetingMemberDao.getVisitantByMeetingId(meetingId);
		if (isNullOrEmpty(list)) {
			if (!isNullOrEmpty(listMember)) {
				meetingMemberDao.deleteByMeetingIdAndMeetingtype(meetingId, 0);
			}
		}
		// 获取主讲人列表
		if (!isNullOrEmpty(listMember)) {
			// 删除已经移除的
			for (MeetingMember meetingMember : listMember) {
				boolean flag = false;
				if (!isNullOrEmpty(meetingMember) && isNullOrEmpty(meetingMember.getId())) {
					for (MeetingMember meetingMemberAfter : list) {
						if (isNullOrEmpty(meetingMemberAfter) || isNullOrEmpty(meetingMemberAfter.getId())) {
							continue;
						}
						if (meetingMember.getId().equals(meetingMemberAfter.getId())) {
							// 原有主讲人没有被移除
							flag = true;
							break;
						}
					}
					if (!flag) {
						meetingMemberDao.delete(meetingMember.getId());
					}

				}
			}
			// 更新主讲人
			updateMeetingProperty(list, new Callback<MeetingMember>() {
				public void callback(MeetingMember t) throws Exception {
					meetingMemberDao.saveOrUpdate(t);
				}
			});
		}
	}

	/**
	 * 删除t_im_RecordMessage表中的会议参与者的记录
	 * 
	 * @param listAttendMeetingId
	 *            需要删除的用户id集合
	 * @param meetingId
	 *            会议id
	 */
	private void deleteMembers(List<Long> listAttendMeetingId, Long meetingId) {
		int size = listAttendMeetingId.size();
		if (null != listAttendMeetingId && size > 0) {
			Map<String, Object> entities = new HashMap<String, Object>();
			entities.put("meetingId", Integer.valueOf(String.valueOf(meetingId)));
			List<Integer> ids = new ArrayList<Integer>(size);
			for (Long id : listAttendMeetingId) {
				if (!Utils.isNullOrEmpty(id)) {
					ids.add(Integer.valueOf(String.valueOf(id)));
				}
			}
			entities.put("meetingMemberList", ids);
			imRecordmessageDao.batchDelete(entities);
		}
	}

	private void buildNewMembersForImRecordMessage(List<ImRecordmessage> recordMessageList, Meeting meeting, MeetingMember member) {
		ImRecordmessage recordMessageBean = new ImRecordmessage();
		recordMessageBean.setUserId(Integer.valueOf(String.valueOf(member.getMemberId())));
		recordMessageBean.setMucId(Integer.valueOf(String.valueOf(meeting.getId())));
		recordMessageBean.setMucCreateUserId(Integer.valueOf(String.valueOf(meeting.getCreateId())));
		recordMessageBean.setType(3);// 标示会议
		recordMessageBean.setNewCount(0);
		recordMessageBean.setMucStartDate(meeting.getStartTime());
		recordMessageBean.setMessageStartTime(new Date());
		recordMessageBean.setStatus(String.valueOf(meeting.getMeetingStatus()));
		recordMessageList.add(recordMessageBean);
	}

	/**
	 * 名称: updateMeetingInterfix 描述: 修改会议以及会议相关的数据
	 * 
	 * @since 2014-09-14
	 * @author qingc
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateMeetingInterfix(MeetingQuery entity, User user, String fileImgUrl) throws Exception {
		if (!Utils.isNullOrEmpty(entity)) {
			Meeting meeting = this.getById(entity.getId());
			final String groupId = meeting.getGroupId();
			Meeting beginUpdateMeeting = meeting;
			if (Utils.isNullOrEmpty(meeting)) {
				logger.info("会议不存在");
				throw new Exception("会议不存在");
			}
			if (Utils.isNullOrEmpty(entity.getStartTime())) {
				logger.info("会议时间不存在");
				throw new Exception("会议时间不存在");
			} else if (
			// meeting.getEndTime().getTime()<=new
			// Date().getTime()+(Constant.ALLOW_MODIFY_MEETING_TIME*3600*1000)&&meeting.getMeetingStatus()==MeetingStatusType.ENDED.code()
			meeting.getMeetingStatus() == MeetingStatusType.ENDED.code()) {
				logger.info("会议已结束，不能修改");
				throw new Exception("会议已结束，不能修改");
			} else {
				int oldMeetingStatus = meeting.getMeetingStatus();
				List<String> listNoticeField = new ArrayList<String>();
				meeting.setIsSecrecy(entity.getIsSecrecy());
				if (entity.getMeetingStatus() != MeetingStatusType.DRAFT.code() && Utils.isModify(meeting.getMeetingName(), entity.getMeetingName())) {
					// 加入通知
					listNoticeField.add(ModifyMeetingNoticeType.NAME.toString());
				}
				meeting.setMeetingName(entity.getMeetingName());
				if (entity.getMeetingStatus() != MeetingStatusType.DRAFT.code()) {
					if (Utils.isModify(meeting.getMeetingAddress(), entity.getMeetingAddress())
							|| Utils.isModify(meeting.getMeetingAddressPosX(), entity.getMeetingAddressPosX())
							|| Utils.isModify(meeting.getMeetingAddressPosY(), entity.getMeetingAddressPosY())) {
						// 加入通知
						listNoticeField.add(ModifyMeetingNoticeType.ADDRESS.toString());
					}
				}
				meeting.setMeetingAddress(entity.getMeetingAddress());
				meeting.setMeetingAddressPosX(entity.getMeetingAddressPosX());
				meeting.setMeetingAddressPosY(entity.getMeetingAddressPosY());
				if (entity.getMeetingStatus() != MeetingStatusType.DRAFT.code() && Utils.isModify(meeting.getMeetingDesc(), entity.getMeetingDesc())) {
					// 加入通知
					listNoticeField.add(ModifyMeetingNoticeType.INTRODUCE.toString());
				}
				meeting.setMeetingDesc(entity.getMeetingDesc());
				meeting.setMeetingStatus(entity.getMeetingStatus());
				meeting.setMeetingType(entity.getMeetingType());
				meeting.setMemberCount(entity.getMemberCount());
				meeting.setTaskId(entity.getTaskId());
				meeting.setStartTime(entity.getStartTime());
				meeting.setEndTime(entity.getEndTime());
				// 非草稿会议，根据会议开始时间重新设置会议状态
				if (!Utils.isNullOrEmpty(meeting.getStartTime()) && MeetingStatusType.DRAFT.code() != meeting.getMeetingStatus()) {
					if (meeting.getStartTime().after(new Date())) {
						meeting.setMeetingStatus(MeetingStatusType.NOT_BEGIN.code());
					} else if (meeting.getStartTime().before(new Date())) {
						meeting.setMeetingStatus(MeetingStatusType.IN_MEETING.code());
					}
				}
				// 设置行业
				String industry = meetingDict.getDictStr(entity.getListIndustry(), MeetingDict.INDUSTRY);
				meeting.setIndustry(industry);
				// 设置图文混排的会议描述
				meeting.setFormatedDesc(entity.getFormatedDesc());
				// 更新会议
				this.saveOrUpdate(meeting);
				// 参会人
				List<MeetingMember> listAttendMeeting = new ArrayList<MeetingMember>();
				List<MeetingMember> listSpeaker = new ArrayList<MeetingMember>();
				List<MeetingMember> listMeetingMember = new ArrayList<MeetingMember>();
				final List<Long> listAttendMeetingId = new ArrayList<Long>();
				final List<Long> listAttendMeetingMemberId = new ArrayList<Long>();
				final List<Long> listAttendMeetingMemberIdNew = new ArrayList<Long>();
				List<MeetingMember> listMeetingMemberOld = meetingMemberDao.getAttendMemberByMeetingId(meeting.getId());
				final Map<String, MeetingMember> mapAttendMeetingMemberOld = new HashMap<String, MeetingMember>();
				if (!Utils.isNullOrEmpty(listMeetingMemberOld)) {
					for (MeetingMember meetingMember : listMeetingMemberOld) {
						if (!Utils.isNullOrEmpty(meetingMember.getMemberId()) && meetingMember.getMemberId() != 0) {
							mapAttendMeetingMemberOld.put(meetingMember.getMemberId().toString(), meetingMember);
						}
					}
				}
				final List<ImRecordmessage> recordMessageList = new ArrayList<ImRecordmessage>();
				final Meeting meetingTemp = meeting;
				// 如果会议无议题
				if (MeetingType.NOT_HAVE_SPEAKER.code() == meeting.getMeetingType()) {
					listAttendMeeting = entity.getListMeetingMember();
					// 修改参会人
					if (!Utils.isNullOrEmpty(listAttendMeeting)) {
						updateMeetingProperty(listAttendMeeting, new Callback<MeetingMember>() {
							public void callback(MeetingMember t) throws Exception {
								setPrimarykeyZoroToNull(t);
								if (Utils.isNullOrEmpty(t.getId())) {
									t.setAttendMeetTime(new Date());
								}
								Long id = t.getId();
								if (t.getMemberType().intValue() == MemberType.CREATER.code() && (null == id || id == 0)) {
									buildNewMembersForImRecordMessage(recordMessageList, meetingTemp, t);
								}
								if (!Utils.isNullOrEmpty(t.getMemberId()) && mapAttendMeetingMemberOld.containsKey(t.getMemberId().toString())) {
									listAttendMeetingMemberIdNew.add(t.getMemberId());
								}
								meetingMemberDao.saveOrUpdate(t);
								listAttendMeetingId.add(t.getId());
								if (!Utils.isNullOrEmpty(t.getMemberId())) {
									listAttendMeetingMemberId.add(t.getMemberId());
								}
							}
						});
					}
					// 删除已经移除的参会人
					if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getId())) {
						// meetingDao.deleteBatchOther(listAttendMeetingId,entity.getId(),0);
						meetingMemberDao.deleteMemberByMeetingId(entity.getId(), listAttendMeetingId);
						deleteMembers(listAttendMeetingId, entity.getId());
					}
				} else {
					// 去除参会人和主讲人重复的记录
					Map<String, MeetingMember> mapMeetingMember = distinctListMeetingMember(entity);
					// 修改会包含的主讲人和参会人id集合
					final List<Long> listMeetingMemberId = new ArrayList<Long>();
					// 遍历主讲人、参会人
					if (!isNullOrEmpty(mapMeetingMember)) {
						for (MeetingMember meetingMember : mapMeetingMember.values()) {
							if (!isNullOrEmpty(meetingMember)) {
								// 参会人
								if (MemberType.PARTICIPANTS.code() == meetingMember.getMemberType()) {
									listAttendMeeting.add(meetingMember);
								}
								listMeetingMember.add(meetingMember);
								// 主讲人
								if (MemberType.VIP.code() == meetingMember.getMemberType()) {
									listSpeaker.add(meetingMember);
								}
							}
						}
						// 从数据库获取修改前 主讲人
						List<MeetingMember> listMember = meetingMemberDao.getVisitantByMeetingId(meeting.getId());
						if (entity.getMeetingStatus() != MeetingStatusType.DRAFT.code() && isModifyOfTwoList(listMember, listSpeaker)) {
							// 加入通知
							listNoticeField.add(ModifyMeetingNoticeType.SPEAKER.toString());
						}
						// 更新主讲人和参会人
						updateMeetingProperty(listMeetingMember, new Callback<MeetingMember>() {
							public void callback(MeetingMember t) throws Exception {
								setPrimarykeyZoroToNull(t);
								if (Utils.isNullOrEmpty(t.getId())) {
									t.setAttendMeetTime(new Date());
								}
								Long id = t.getId();
								if (!Utils.isNullOrEmpty(t.getMemberId()) && mapAttendMeetingMemberOld.containsKey(t.getMemberId().toString())) {
									listAttendMeetingMemberIdNew.add(t.getMemberId());
								}
								if (t.getMemberType().intValue() == MemberType.CREATER.code()
										|| t.getMemberType().intValue() == MemberType.VIP.code()) {
									if (null == id || id == 0) {
										buildNewMembersForImRecordMessage(recordMessageList, meetingTemp, t);
									}
									// 会议创建人和主讲人默认接收邀请，且不显示邀请函
									t.setIsShowInvitation(0L);
									t.setAttendMeetStatus(1);
								}
								meetingMemberDao.saveOrUpdate(t);
								listMeetingMemberId.add(t.getId());
								if (!Utils.isNullOrEmpty(t.getMemberId())) {
									listAttendMeetingMemberId.add(t.getMemberId());
								}
								if (t.getMemberType().intValue() == 0) {
									/**
									 * 集成环信：更新主讲人
									 */
									final Long userId = id;
									final Long creatorUserId = meetingTemp.getCreateId();
									ThreadPoolUtils.getExecutorService().execute(new Runnable() {
										@Override
										public void run() {
											// 2016-03-03 tanm 将操作环信的部分改为操作畅聊
											GinTongInterface.invite2MUC(creatorUserId, Arrays.asList(Long.valueOf(userId)), groupId);
											// HuanxinUtils.addUserToChatGroups(groupId,
											// userId);
										}
									});
								}
							}
						});
					}
					// 删除已经移除的参会人
					if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getId())) {
						// meetingDao.deleteBatchOther(listMeetingMemberId,entity.getId(),0);
						meetingMemberDao.deleteMemberByMeetingId(entity.getId(), listMeetingMemberId);
						deleteMembers(listAttendMeetingId, entity.getId());
						final List<Long> userIds = listMeetingMemberId;
						final long operatorUserId = meeting.getCreateId();
						ThreadPoolUtils.getExecutorService().execute(new Runnable() {
							@Override
							public void run() {
								// 2016-03-03 将环信删除用户改为使用畅聊删除用户
								for (long uid : userIds) {
									GinTongInterface.exitFromMUC(operatorUserId, uid, groupId);
								}
								// HuanxinUtils.deleteMembersFromGroup(groupId,
								// userIds);
							}
						});
					}
				}
				/**
				 * 修改会议的时候，当新增成员或删除成员的时候要维护t_im_recordMessage中的数据
				 */
				if (recordMessageList.size() > 0) {
					Map<String, List<ImRecordmessage>> imRecordMessageList = new HashMap<String, List<ImRecordmessage>>(1);
					imRecordMessageList.put("imRecordMessageList", recordMessageList);
					imRecordmessageDao.batchSave(imRecordMessageList);
				}
				/**
				 * 保存会议议题
				 */
				this.updateMeetingTopic(entity, beginUpdateMeeting);
				/**
				 * 保存会议时间
				 */
				List<MeetingTime> listMeetingTimeTemp = meetingTimeDao.getByMeetingId(meeting.getId());
				if (entity.getMeetingStatus() != MeetingStatusType.DRAFT.code()
						&& isModifyOfTwoList(listMeetingTimeTemp, entity.getListMeetingTime())) {
					// 加入通知
					listNoticeField.add(ModifyMeetingNoticeType.TIME.toString());
				}
				final List<Long> listMeetingTimeId = new ArrayList<Long>();
				if (!Utils.isNullOrEmpty(entity.getListMeetingTime())) {
					updateMeetingProperty(entity.getListMeetingTime(), new Callback<MeetingTime>() {
						public void callback(MeetingTime t) throws Exception {
							setPrimarykeyZoroToNull(t);
							meetingTimeDao.saveOrUpdate(t);
							listMeetingTimeId.add(t.getId());
						}
					});
				}
				// 删除已经移除的时间
				if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getId())) {
					meetingDao.deleteBatchOther(listMeetingTimeId, entity.getId(), 2);
				}
				/**
				 * 保存会议图片
				 */
				String homePage = this.updateMeetingPicAndFile(entity);
				/**
				 * 保存会议人脉
				 */
				List<MeetingPeople> listPeoples = meetingPeopleDao.getByMeetingId(meeting.getId());
				if (entity.getMeetingStatus() != MeetingStatusType.DRAFT.code() && isModifyOfTwoList(listPeoples, entity.getListMeetingPeople())) {
					// 加入通知
					listNoticeField.add(ModifyMeetingNoticeType.PEOPLE.toString());
				}
				final List<Long> listMeetingPeopleId = new ArrayList<Long>();
				if (!Utils.isNullOrEmpty(entity.getListMeetingPeople())) {
					updateMeetingProperty(entity.getListMeetingPeople(), new Callback<MeetingPeople>() {
						public void callback(MeetingPeople t) throws Exception {
							setPrimarykeyZoroToNull(t);
							meetingPeopleDao.saveOrUpdate(t);
							listMeetingPeopleId.add(t.getId());
						}
					});
				}
				// 删除已经移除的人脉
				if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getId())) {
					meetingDao.deleteBatchOther(listMeetingPeopleId, entity.getId(), 3);
				}
				/**
				 * 保存会议组织
				 */
				List<MeetingOrgan> listOrgans = meetingOrganDao.getByMeetingId(meeting.getId());
				if (entity.getMeetingStatus() != MeetingStatusType.DRAFT.code() && isModifyOfTwoList(listOrgans, entity.getListMeetingOrgan())) {
					// 加入通知
					listNoticeField.add(ModifyMeetingNoticeType.ORGAN.toString());
				}
				final List<Long> listMeetingOrganId = new ArrayList<Long>();
				if (!Utils.isNullOrEmpty(entity.getListMeetingOrgan())) {
					updateMeetingProperty(entity.getListMeetingOrgan(), new Callback<MeetingOrgan>() {
						public void callback(MeetingOrgan t) throws Exception {
							setPrimarykeyZoroToNull(t);
							meetingOrganDao.saveOrUpdate(t);
							listMeetingOrganId.add(t.getId());
						}
					});
				}
				// 删除已经移除的组织
				if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getId())) {
					meetingDao.deleteBatchOther(listMeetingOrganId, entity.getId(), 5);
				}
				/***
				 * 保存会议资料
				 */
				List<MeetingData> listdatas = meetingDataDao.getByMeetingId(meeting.getId());
				if (entity.getMeetingStatus() != MeetingStatusType.DRAFT.code() && isModifyOfTwoList(listdatas, entity.getListMeetingData())) {
					// 加入通知
					listNoticeField.add(ModifyMeetingNoticeType.DATA.toString());
				}
				final List<Long> listMeetingDataId = new ArrayList<Long>();
				if (!Utils.isNullOrEmpty(entity.getListMeetingData())) {
					updateMeetingProperty(entity.getListMeetingData(), new Callback<MeetingData>() {
						public void callback(MeetingData t) throws Exception {
							setPrimarykeyZoroToNull(t);
							meetingDataDao.saveOrUpdate(t);
							listMeetingDataId.add(t.getId());
						}
					});
				}
				// 删除已经移除的资料
				if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getId())) {
					meetingDao.deleteBatchOther(listMeetingDataId, entity.getId(), 4);
				}
				// 保存会议标签
				final List<Long> listMeetingSignLabelId = new ArrayList<Long>();
				if (!Utils.isNullOrEmpty(entity.getListMeetingSignLabel())) {
					updateMeetingProperty(entity.getListMeetingSignLabel(), new Callback<MeetingSignLabel>() {
						public void callback(MeetingSignLabel t) throws Exception {
							setPrimarykeyZoroToNull(t);
							meetingSignLabelDao.saveOrUpdate(t);
							listMeetingSignLabelId.add(t.getId());
						}
					});
				}
				// 删除已经移除的会议标签
				if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getId()) && !Utils.isNullOrEmpty(listMeetingSignLabelId)) {
					meetingDao.deleteBatchOther(listMeetingSignLabelId, entity.getId(), 6);
				}
				// 如果会议不是草稿状态发起会议,发送修改会议通知
				if (oldMeetingStatus != MeetingStatusType.DRAFT.code() && entity.getMeetingStatus() != MeetingStatusType.DRAFT.code()) {
					String content = meeting.getCreateName() + "修改了";
					List<MeetingMember> listMeetingMemberTemp = meetingMemberDao.getByMeetingId(meeting.getId());
					List<String> listMemberIdNew = new ArrayList<String>();
					for (MeetingMember meetingMember : listMeetingMemberTemp) {
						// 未修改的参会人，发送修改会议通知
						if (null == meetingMember || !mapAttendMeetingMemberOld.containsKey(meetingMember.getMemberId().toString())) {
							continue;
						}
						// 新增加的参会人
						listMemberIdNew.add(meetingMember.getMemberId().toString());
						// 通过邀请参会 不是“接受邀请”状态
						// 通过报名参会 不是“同意报名”状态：审核不通过
						if ((meetingMember.getAttendMeetType() == 0 && meetingMember.getAttendMeetStatus() != 1)
								|| (meetingMember.getAttendMeetType() == 1 && meetingMember.getExcuteMeetSign() != ExcuteMeetSignType.AGREE_SIGN_UP
										.code())) {
							continue;
						}
						MeetingNotice meetingNotice = new MeetingNotice();
						if (!Utils.isNullOrEmpty(meetingMember.getMemberId()) && meetingMember.getMemberId().equals(meeting.getCreateId())) {
							// 修改会议向创建者发通知
							String tempContent = content.replaceAll(meeting.getCreateName(), "您");
							meetingNotice.setNoticeContent(tempContent + "会议" + meeting.getMeetingName());
							meetingNotice.setReceiverType(NoticeReceiverType.CREATER.code());
						} else {
							// 修改会议向参会人发通知
							meetingNotice.setReceiverType(NoticeReceiverType.PARTICIPANTS.code());
							meetingNotice.setNoticeContent(content + "会议" + meeting.getMeetingName());
						}
						meetingNotice.setCreateId(user.getId());
						meetingNotice.setCreateName(user.getName());
						meetingNotice.setCreateTime(new Date());
						meetingNotice.setUpdateTime(new Date());
						meetingNotice.setMeetingId(meeting.getId());
						meetingNotice.setIsShow(1);
						meetingNotice.setReceiver(meetingMember.getMemberId());
						if (!Utils.isNullOrEmpty(meetingMember.getMemberId())) {
							meetingNotice.setReceiverName(meetingMember.getMemberName());
						}
						meetingNotice.setNoticeType(NoticeType.UPDATE_MEETING.code());
						meetingNoticeDao.saveOrUpdate(meetingNotice);
						for (String field : listNoticeField) {
							NoticeField noticeField = new NoticeField();
							noticeField.setNoticeId(meetingNotice.getId());
							noticeField.setField(field);
							noticeFieldDao.saveOrUpdate(noticeField);
						}
					}
					// 删除的邀请人，发送取消参会通知
					content = meeting.getCreateName() + "取消了";
					for (MeetingMember meetingMember : mapAttendMeetingMemberOld.values()) {
						if (!Utils.isNullOrEmpty(meetingMember.getMemberId()) && !meetingMember.getMemberId().equals(meeting.getCreateId())
								&& !listMemberIdNew.contains(meetingMember.getMemberId().toString())) {
							MeetingNotice meetingNotice = new MeetingNotice();
							// 修改会议向参会人发通知
							meetingNotice.setReceiverType(NoticeReceiverType.PARTICIPANTS.code());
							meetingNotice.setNoticeContent(content + "您参加的" + meeting.getMeetingName() + "会议");
							meetingNotice.setCreateId(user.getId());
							meetingNotice.setCreateName(user.getName());
							meetingNotice.setCreateTime(new Date());
							meetingNotice.setUpdateTime(new Date());
							meetingNotice.setMeetingId(meeting.getId());
							meetingNotice.setIsShow(1);
							meetingNotice.setReceiver(meetingMember.getMemberId());
							if (!Utils.isNullOrEmpty(meetingMember.getMemberId())) {
								meetingNotice.setReceiverName(meetingMember.getMemberName());
							}
							meetingNotice.setNoticeType(NoticeType.CANCEL_ATTEND_MEETING.code());
							meetingNoticeDao.saveOrUpdate(meetingNotice);
						}
					}
					String dateStr = DateUtil.convertDateToStringForChina(new Date());
					// 修改会议推送通知
					UserBean userBean = new UserBean();
					userBean.setId(user.getId());
					userBean.setName(user.getName());
					GinTongInterface.pushToAttendMeetingMember(userBean, String.valueOf(meeting.getId()), meeting.getCreateName() + "于" + dateStr
							+ "修改了会议", false, dateStr);
				}
				// 草稿状态发起会议，要增加动态
				if (oldMeetingStatus == MeetingStatusType.DRAFT.code() && entity.getMeetingStatus() != MeetingStatusType.DRAFT.code()) {
					this.insertNewsAndRelation(meeting, homePage, user, listAttendMeetingMemberId);
				}
				// 非草稿状态修改，新邀请的成员增加动态
				if (oldMeetingStatus != MeetingStatusType.DRAFT.code() && !Utils.isNullOrEmpty(listAttendMeetingMemberIdNew)) {
					this.insertNewsAndRelation(meeting, homePage, user, listAttendMeetingMemberIdNew);
				}
			}
		}
	}
	// 修改会议图片与文件
	private String updateMeetingPicAndFile(MeetingQuery entity) {
		String homePage = "";
		List<String> listFileIndexId = new ArrayList<String>();
		List<Long> listMeetingPicId = new ArrayList<Long>();
		if (!Utils.isNullOrEmpty(entity.getListMeetingPic())) {
			for (int i = 0; i < entity.getListMeetingPic().size(); i++) {
				MeetingPic meetingPic = entity.getListMeetingPic().get(i);
				if (!Utils.isNullOrEmpty(meetingPic)) {
					meetingPic.setMeetingId(entity.getId());
					meetingPic.setModuleId(entity.getId());
					meetingPic.setModuleType(MeetingPic.MODULE_TYPE_MEETING);
					meetingPic.setCreateDate(new Date());
					if (i == 0) {
						homePage = meetingPic.getPicPath();
						meetingPic.setIshomePage(1);
					} else {
						meetingPic.setIshomePage(0);
					}
					meetingPicDao.saveOrUpdate(meetingPic);
					listMeetingPicId.add(meetingPic.getId());
					listFileIndexId.add("" + meetingPic.getFileIndexId());
				}
			}
		}
		// 删除图片
		meetingPicDao.deleteByModuleId(entity.getId(), MeetingPic.MODULE_TYPE_MEETING, listMeetingPicId);
		if (!Utils.isNullOrEmpty(entity.getListMeetingFile())) {
			for (MeetingFile meetingFile : entity.getListMeetingFile()) {
				if (!Utils.isNullOrEmpty(meetingFile)) {
					listFileIndexId.add("" + meetingFile.getFileIndexId());
				}
			}
		}
		// 删除已移除的FileIndex
		if (!Utils.isNullOrEmpty(entity.getTaskId())) {
			List<FileIndex> listFileIndex = fileIndexService.selectByTaskId(entity.getTaskId(), "1");
			if (!Utils.isNullOrEmpty(listFileIndex)) {
				for (FileIndex fileIndex : listFileIndex) {
					if (!listFileIndexId.contains("" + fileIndex.getId())) {
						fileIndexService.delete(Long.parseLong(fileIndex.getId()));
					}
				}
			}
		}
		return homePage;
	}
	private void updateMeetingTopic(MeetingQuery entity, Meeting beginUpdateMeeting) throws Exception {
		final List<Long> listMeetingTopicId = new ArrayList<Long>();
		// 会议修 改前是有议题会议，修改后还是有议题会议
		if (MeetingType.HAVE_SPEAKER.code() == beginUpdateMeeting.getMeetingType() && entity.getMeetingType() == MeetingType.HAVE_SPEAKER.code()) {
			if (!Utils.isNullOrEmpty(entity.getListMeetingTopicQuery())) {
				// 议题
				updateMeetingProperty(entity.getListMeetingTopicQuery(), new Callback<MeetingTopicQuery>() {
					public void callback(MeetingTopicQuery t) throws Exception {
						MeetingTopic meetingTopic = t.createMeetingTopic();
						setPrimarykeyZoroToNull(meetingTopic);
						meetingTopicDao.saveOrUpdate(meetingTopic);
						t.setId(meetingTopic.getId());
						listMeetingTopicId.add(meetingTopic.getId());
					}
				});
			}
			// 删除已经移除的议题
			if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getId())) {
				meetingDao.deleteBatchOther(listMeetingTopicId, entity.getId(), 1);
			}
			// 更新议题相关的图片、文件等
			this.updateMeetingTopicPicAndFile(entity);
		}
		// 会议修改前是无议题会议，修改后是有议题会议
		if (MeetingType.NOT_HAVE_SPEAKER.code() == beginUpdateMeeting.getMeetingType() && entity.getMeetingType() == MeetingType.HAVE_SPEAKER.code()) {
			// 删除 无主将默认议题
			meetingTopicDao.deleteByMeetingId(entity.getId());
			// 新增用户添加议题
			if (!Utils.isNullOrEmpty(entity.getListMeetingTopicQuery())) {
				updateMeetingProperty(entity.getListMeetingTopicQuery(), new Callback<MeetingTopicQuery>() {
					public void callback(MeetingTopicQuery t) throws Exception {
						MeetingTopic meetingTopic = t.createMeetingTopic();
						setPrimarykeyZoroToNull(meetingTopic);
						meetingTopicDao.saveOrUpdate(meetingTopic);
						t.setId(meetingTopic.getId());
					}
				});
			}
			// 更新议题相关的图片、文件等
			this.updateMeetingTopicPicAndFile(entity);
		}
		// 会议修 改前是有议题会议，修改后是无议题会议
		if (MeetingType.HAVE_SPEAKER.code() == beginUpdateMeeting.getMeetingType() && entity.getMeetingType() == MeetingType.NOT_HAVE_SPEAKER.code()) {
			// 删除 所有议题
			meetingTopicDao.deleteByMeetingId(entity.getId());
			// 增加无主将默认议题
			MeetingTopic meetingTopic = new MeetingTopic();
			meetingTopic.setMeetingId(entity.getId());
			meetingTopic.setCreateId(entity.getCreateId());
			meetingTopic.setCreateName(entity.getCreateName());
			meetingTopic.setCreateTime(entity.getCreateTime());
			meetingTopic.setUpdateTime(new Date());
			meetingTopic.setTopicContent("无主讲会议默认议题");
			meetingTopicDao.saveOrUpdate(meetingTopic);
		}
	}
	private void updateMeetingTopicPicAndFile(MeetingQuery entity) {
		List<String> listFileIndexId = new ArrayList<String>();
		if (!Utils.isNullOrEmpty(entity) && !Utils.isNullOrEmpty(entity.getListMeetingTopicQuery())) {
			for (MeetingTopicQuery topicQuery : entity.getListMeetingTopicQuery()) {
				listFileIndexId.clear();
				List<Long> listMeetingPicId = new ArrayList<Long>();
				if (!Utils.isNullOrEmpty(topicQuery.getListMeetingPic())) {
					for (int i = 0; i < topicQuery.getListMeetingPic().size(); i++) {
						MeetingPic meetingPic = topicQuery.getListMeetingPic().get(i);
						if (!Utils.isNullOrEmpty(meetingPic)) {
							meetingPic.setMeetingId(entity.getId());
							meetingPic.setModuleId(topicQuery.getId());
							meetingPic.setModuleType(MeetingPic.MODULE_TYPE_TOPIC);
							meetingPic.setCreateDate(new Date());
							if (i == 0) {
								meetingPic.setIshomePage(1);
							} else {
								meetingPic.setIshomePage(0);
							}
							meetingPicDao.saveOrUpdate(meetingPic);
							listMeetingPicId.add(meetingPic.getId());
							listFileIndexId.add("" + meetingPic.getFileIndexId());
						}
					}
				}
				// 删除图片
				meetingPicDao.deleteByModuleId(topicQuery.getId(), MeetingPic.MODULE_TYPE_TOPIC, listMeetingPicId);
				if (!Utils.isNullOrEmpty(topicQuery.getListMeetingFile())) {
					for (MeetingFile meetingFile : topicQuery.getListMeetingFile()) {
						if (!Utils.isNullOrEmpty(meetingFile)) {
							listFileIndexId.add("" + meetingFile.getFileIndexId());
						}
					}
				}
				// 删除已移除的FileIndex
				if (!Utils.isNullOrEmpty(topicQuery.getTaskId())) {
					List<FileIndex> listFileIndex = fileIndexService.selectByTaskId(topicQuery.getTaskId(), "1");
					if (!Utils.isNullOrEmpty(listFileIndex)) {
						for (FileIndex fileIndex : listFileIndex) {
							if (!listFileIndexId.contains("" + fileIndex.getId())) {
								fileIndexService.delete(Long.parseLong(fileIndex.getId()));
							}
						}
					}
				}
			}
		}
	}
	// 会议
	public List<MeetingVo> getMeetingList(long user_id) {
		List<Map<String, Object>> list = meetingDao.selectmeetingindex(user_id);
		if (list == null || list.size() == 0)
			return null;
		int len = list.size();
		List<MeetingVo> result = new ArrayList<MeetingVo>(len);
		for (int i = 0; i < len; i++) {
			Map<String, Object> map = list.get(i);
			if (map == null)
				return null;
			MeetingVo mv = new MeetingVo();
			MeetingMini index = new MeetingMini();
			index.setMeeting_id(Long.parseLong(map.get("id").toString()));
			index.setTitle(map.get("title") + "");
			index.setStart_time(Utils.DateFormat((Date) map.get("start_time")));
			index.setCreate_time(Utils.DateFormat((Date) map.get("create_time")));
			index.setStatus(StringUtils.isNumber(map.get("status") + "") ? Integer.parseInt(map.get("status") + "") : 0);
			index.setImage(map.get("image") + "");
			mv.setType(1);
			if (index.getStatus() == 1)
				mv.setSort((Date) map.get("start_time"));
			else if (index.getStatus() == 2)
				mv.setSort((Date) map.get("start_time"));
			else if (index.getStatus() == 3)
				mv.setSort((Date) map.get("end_time"));
			mv.setMeeting(index);
			getTopicChat(index.getMeeting_id(), index);
			result.add(mv);
		}
		return result;
	}
	// 邀请函
	public List<MeetingVo> getInviteList(long user_id) {

		List<Map<String, Object>> list = meetingDao.selectinviteindex(user_id);

		if (list == null || list.size() == 0)
			return null;
		int len = list.size();
		List<MeetingVo> result = new ArrayList<MeetingVo>(len);

		for (int i = 0; i < len; i++) {
			Map<String, Object> map = list.get(i);
			if (map == null)
				return null;
			MeetingVo mv = new MeetingVo();

			MeetingMini index = new MeetingMini();
			index.setMeeting_id(Long.parseLong(map.get("id").toString()));
			index.setTitle(map.get("title") + "");
			index.setStart_time(Utils.DateFormat((Date) map.get("start_time")));
			index.setCreate_time(Utils.DateFormat((Date) map.get("create_time")));
			index.setStatus(StringUtils.isNumber(map.get("status") + "") ? Integer.parseInt(map.get("status") + "") : 0);
			index.setImage(map.get("image") + "");

			mv.setType(3);
			mv.setSort((Date) map.get("create_time"));
			mv.setInvite(index);

			getTopicChat(index.getMeeting_id(), index);
			result.add(mv);
		}
		return result;
	}

	// 获取新会议详细信息
	public MeetingMini getTopicChat(long meeting_id, MeetingMini mi) {
		TopicChat tc = topicChatService.selectByMeetingId(meeting_id);
		if (tc == null)
			return null;
		mi.setLast_chat_time(Utils.DateFormat(tc.getPublishTime()));
		// mi.setSenderID(tc.getMemberId());
		mi.setCommtent(tc.getChatContent());
		// mi.setMessageType(tc.getSenderType());

		/*
		 * com.ginkgocap.ywxt.model.meeting.JTFile jtFile = new
		 * com.ginkgocap.ywxt.model.meeting.JTFile();
		 * jtFile.setUrl(tc.getJtfileUrl());
		 * jtFile.setSuffixName(tc.getJtfileSuffixName());
		 * jtFile.setType(StringUtils.isNumber(tc.getJtfileType() + "") ?
		 * Integer.parseInt(tc.getJtfileType() + "") : 0);
		 * jtFile.setFileName(tc.getJtfileName());
		 * jtFile.setFileSize(tc.getJtfileSize());
		 * jtFile.setModuleType(tc.getJtFileModuleType());
		 * jtFile.setTaskId(tc.getJtfileTaskId());
		 * jtFile.setReserved1(tc.getJtFileReserved1());
		 * jtFile.setReserved2(tc.getJtFileReserved2());
		 * jtFile.setReserved3(tc.getJtFileReserved3()); mi.setJtFile(jtFile);
		 * mi.setIndex(tc.getId()); mi.setMessageID("server_" +
		 * tc.getMessageId());
		 * 
		 * List<Integer> user = new ArrayList<Integer>();
		 * user.add(tc.getMemberId().intValue()); List<ConnectionsMini>
		 * listConnectionsMini = GinTongInterface.getListConnectionsMini(user);
		 * if(listConnectionsMini != null && listConnectionsMini.size() != 0) {
		 * ConnectionsMini mini = listConnectionsMini.get(0); if(mini != null) {
		 * mi.setSenderName(mini.getName()); } }
		 */
		return mi;
	}

	/**
	 * 名称: getMeetingListAndInvitation 描述: 获取用户的会议和邀请函
	 * 
	 * @since 2014-01-22
	 * @author qingc
	 * @throws Exception
	 */
	public List<Social> getMeetingListAndInvitation(Long userId) {
		List<ImRecordmessage> imRecordmessages = imRecordmessageDao.getMeetingRecordMessage(userId);
		removeDuplicateImRecordMessage(imRecordmessages);
		List<Social> socials = meetingDao.getMeetingListAndInvitation(userId);
		// 去除重复会议
		removeDuplicatMeeting(socials, userId);
		Integer newCount = imRecordmessageDao.getNewCountOfMeetingInvitation(userId);
		return rebuildSocialList(socials, imRecordmessages, newCount);
	}
	@Override
	public List<Social> getMeetingListByUserId(Long userId) {
		List<ImRecordmessage> imRecordmessages = imRecordmessageDao.getMeetingRecordMessage(userId);
		removeDuplicateImRecordMessage(imRecordmessages);
		List<Social> socials = meetingDao.getMeetingListByUserId(userId);
		// 去除重复会议
		removeDuplicatMeeting(socials, userId);
		// 根据用户设置过滤已结束会议
		UserConfig userConfig = userConfigService.getByUserId(userId);
		if (!Utils.isNullOrEmpty(socials) && !Utils.isNullOrEmpty(userConfig) && !Utils.isNullOrEmpty(userConfig.getAutosave())
				&& userConfig.getAutosave().intValue() == 2) {
			for (int i = socials.size() - 1; i >= 0; i--) {
				Social social = socials.get(i);
				if (!Utils.isNullOrEmpty(social.getType()) && social.getType().intValue() == 5) {
					socials.remove(social);
				}
			}
		}
		return rebuildSocialList(socials, imRecordmessages);
	}
	private void removeDuplicatMeeting(List<Social> socials, Long userId) {
		for (int i = socials.size() - 1; i >= 0; i--) {
			Social social = socials.get(i);
			if (social.getType().intValue() == 3 || social.getType().intValue() == 4 || social.getType().intValue() == 5) {
				for (int j = i - 1; j >= 0; j--) {
					Social tempSocial = socials.get(j);
					if (tempSocial.getType().intValue() == 3 || tempSocial.getType().intValue() == 4 || tempSocial.getType().intValue() == 5) {
						if (!Utils.isNullOrEmpty(social) && !Utils.isNullOrEmpty(tempSocial)
								&& social.getId().longValue() == tempSocial.getId().longValue()) {
							if (social.getCompereId().longValue() == userId.longValue()) {
								socials.remove(j);
							} else if (tempSocial.getCompereId().longValue() == userId.longValue()) {
								socials.remove(i);
							} else {
								socials.remove(j);
							}
							break;
						}
					}
				}
			}
		}
	}
	@Override
	public Social getLatestInvitation(Long userId) {
		Social social = meetingDao.getLatestInvitation(userId);
		Integer newCount = imRecordmessageDao.getNewCountOfMeetingInvitation(userId);
		return rebuildSocialList(social, newCount);
	}

	private Social rebuildSocialList(Social social, Integer invitationOfnewCount) {
		if (null == social) {
			return null;
		}
		social.setNewCount(invitationOfnewCount == null ? 0 : invitationOfnewCount);
		return social;
	}

	private void removeDuplicateImRecordMessage(List<ImRecordmessage> imRecordmessages) {
		// 去重复
		for (int i = 0; i < imRecordmessages.size(); i++) // 外循环是循环的次数
		{
			for (int j = imRecordmessages.size() - 1; j > i; j--) // 内循环是
																	// 外循环一次比较的次数
			{
				if (defineUnique(imRecordmessages.get(i), imRecordmessages.get(j))) {
					imRecordmessages.remove(j);
				}
			}
		}
	}

	private boolean defineUnique(ImRecordmessage source, ImRecordmessage target) {
		if ((source.getUserId() == null ? 0 : source.getUserId().longValue()) == (target.getUserId() == null ? 0 : target.getUserId().longValue())
				&& (source.getUserId2() == null ? 0 : source.getUserId2().longValue()) == (target.getUserId2() == null ? 0 : target.getUserId2()
						.longValue())
				&& (source.getChatMessageId() == null ? 0 : source.getChatMessageId().intValue()) == (target.getChatMessageId() == null ? 0 : target
						.getChatMessageId().intValue())
				&& (source.getMucId() == null ? 0 : source.getMucId().intValue()) == (target.getMucId() == null ? 0 : target.getMucId().intValue())
				&& (source.getMucMessageId() == null ? 0 : source.getMucMessageId().intValue()) == (target.getMucMessageId() == null ? 0 : target
						.getMucMessageId().intValue())
				&& (source.getNewCount() == target.getNewCount())
				&& (source.getMucCreateUserId() == null ? 0 : source.getMucCreateUserId().intValue()) == (target.getMucCreateUserId() == null
						? 0
						: target.getMucCreateUserId().intValue())
				&& (source.getType() == null ? 0 : source.getType().intValue()) == (target.getType() == null ? 0 : target.getType().intValue())
				&& (source.getStatus() == null ? "" : source.getStatus().trim())
						.equals((target.getStatus() == null ? "" : target.getStatus().trim()))) {
			return true;
		} else {
			return false;
		}
	}

	private List<Social> rebuildSocialList(List<Social> socials, List<ImRecordmessage> imRecordmessages, Integer invitationOfnewCount) {
		if (null == socials || socials.size() == 0) {
			return null;
		}
		Map<Long, Integer> map = convertListToMap(imRecordmessages);
		for (Social social : socials) {
			if (social.getType() == 7) {// 邀请函
				social.setNewCount(invitationOfnewCount == null ? 0 : invitationOfnewCount);
			} else {// 会议和通知
				Integer newCount = map.get(social.getId());
				social.setNewCount(newCount == null ? 0 : newCount);
			}
		}
		return socials;
	}

	private List<Social> rebuildSocialList(List<Social> socials, List<ImRecordmessage> imRecordmessages) {
		if (null == socials || socials.size() == 0) {
			return null;
		}
		Map<Long, Integer> map = convertListToMap(imRecordmessages);
		for (Social social : socials) {
			Integer newCount = map.get(social.getId());
			social.setNewCount(newCount == null ? 0 : newCount);
		}
		return socials;
	}

	private Map<Long, Integer> convertListToMap(List<ImRecordmessage> list) {
		if (null == list || list.size() == 0) {
			return new HashMap<Long, Integer>(0);
		}
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		for (ImRecordmessage recordMessage : list) {
			map.put(Long.valueOf(String.valueOf(recordMessage.getMucId())), recordMessage.getNewCount());
		}
		return map;
	}

	/**
	 * 名称: getMyForwardingInMeeting 描述: 获取我的可转发的进行中会议
	 * 
	 * @since 2015-01-24
	 * @author qingc
	 * @throws Exception
	 */
	public List<Social> getMyForwardingInMeeting(Long userId) {
		return meetingDao.getMyForwardingInMeeting(userId);
	}
	/**
	 * 查询大数据推送会议的图片、参会人数、创建人头像
	 * 
	 * @param list
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BigDataQuery> getAttendCountAndPic(List<BigDataQuery> list) {
		if (!Utils.isNullOrEmpty(list)) {
			List<Long> meetingIdList = new ArrayList<Long>();
			List<Long> userIdList = new ArrayList<Long>();
			for (BigDataQuery bigDataQuery : list) {
				if (!Utils.isNullOrEmpty(bigDataQuery.getId())) {
					meetingIdList.add(bigDataQuery.getId());
				}
				if (!Utils.isNullOrEmpty(bigDataQuery.getCreateId())) {
					userIdList.add(bigDataQuery.getCreateId());
				}
			}
			// 查询会议首页图片
			Map<String, String> meetingPicMap = new HashMap<String, String>();
			List<MeetingPic> listMeetingPic = meetingPicDao.getMeetingFrontPage(meetingIdList);
			if (!Utils.isNullOrEmpty(listMeetingPic)) {
				for (MeetingPic meetingPic : listMeetingPic) {
					meetingPicMap.put("" + meetingPic.getModuleId(), meetingPic.getPicPath());
				}
			}
			// 查询创建者头像和姓名
			Map<String, User> userMap = userDao.getUserMapByIds(userIdList);
			for (BigDataQuery bigDataQuery : list) {
				if (!Utils.isNullOrEmpty(bigDataQuery.getId())) {
					String meetingId = bigDataQuery.getId().toString();
					if (meetingPicMap.get(meetingId) != null) {
						bigDataQuery.setMeetingPic(meetingPicMap.get(meetingId));
					}
					Integer count = meetingMemberDao.getAttendMeetingCount(bigDataQuery.getId());
					bigDataQuery.setCount(count);
				}
				if (!Utils.isNullOrEmpty(bigDataQuery.getCreateId())) {
					String userId = bigDataQuery.getCreateId().toString();
					if (userMap.get(userId) != null) {
						User user = userMap.get(userId);
						bigDataQuery.setCreateName(user.getName());
						bigDataQuery.setCreatePic(user.getPicPath());
					}
				}
			}
		}
		return list;
	}
	/**
	 * 批量查询会议
	 * 
	 * @param meetingIdList
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Meeting> getByIds(List<Long> meetingIdList) {
		return meetingDao.getByIds(meetingIdList);
	}
	/**
	 * 批量删除我的会议
	 * 
	 * @param meetingIdList
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteMyMeetingBatch(List<Long> meetingIdList, Long memberId) throws Exception {
		List<Meeting> meetingList = meetingDao.getByIds(meetingIdList);
		if (!Utils.isNullOrEmpty(meetingList)) {
			List<Long> userIdList = new ArrayList<Long>();
			userIdList.add(memberId);
			List<User> userList = userDao.getByIds(userIdList);
			User user = userList.get(0);
			List<String> finishedIdList = new ArrayList<String>();
			List<Long> attendIdList = new ArrayList<Long>();
			for (Meeting meeting : meetingList) {
				if (meeting.getMeetingStatus() != null && meeting.getMeetingStatus().intValue() == 3) {
					finishedIdList.add("" + meeting.getId().longValue());
				} else {
					if (memberId.longValue() == meeting.getCreateId().longValue()) {// 我创建的
						if (meeting.getMeetingStatus().intValue() == 2) {
							throw new Exception("不能删除进行中的会议");
						} else {
							cancelNotBenginMeeting(meeting.getId(), memberId);
						}
					} else {// 我参加的
						if (meeting.getMeetingStatus().intValue() == 1 || meeting.getMeetingStatus().intValue() == 2) {// 退出会议，发送通知
							meetingMemberService.changeAttendMeetStatus(meeting.getId(), memberId, AttendMeetStatusType.QUIT_MEETING.code(), user);
						} else {// 删除我的会议
							attendIdList.add(meeting.getId());
						}
					}
				}
			}
			if (!Utils.isNullOrEmpty(finishedIdList)) {
				List<MeetingMember> meetingMemberList = meetingMemberDao.getByMemberId(memberId);
				if (!Utils.isNullOrEmpty(meetingMemberList)) {
					for (MeetingMember member : meetingMemberList) {
						if (member.getMeetingId() != null && finishedIdList.contains("" + member.getMeetingId().longValue())
								&& member.getIsDelete() == 0) {
							meetingMemberDao.delete(member.getId());
						}
					}

				}
			}
			if (!Utils.isNullOrEmpty(attendIdList)) {
				meetingMemberService.deleteAttendMeetingBatch(attendIdList);
			}
		}
		meetingDao.deleteBatch(meetingIdList);
	}
	@Override
	public Social getMeetingWithLatestMessage(Long userId) {
		return meetingDao.getMeetingWithLatestMessage(userId);
	}
}
