
package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ginkgocap.ywxt.common.base.BaseController;
import com.ginkgocap.ywxt.model.meeting.ImRecordmessage;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.model.meeting.MeetingSignLabel;
import com.ginkgocap.ywxt.model.meeting.MeetingTopic;
import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.person.model.Basic;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonName;
import com.ginkgocap.ywxt.person.model.WorkExperience;
import com.ginkgocap.ywxt.person.service.PersonService;
import com.ginkgocap.ywxt.service.meeting.ImRecordmessageService;
import com.ginkgocap.ywxt.service.meeting.MeetingMemberService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoticeService;
import com.ginkgocap.ywxt.service.meeting.MeetingPicService;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.service.meeting.MeetingSignLabelDataService;
import com.ginkgocap.ywxt.service.meeting.MeetingSignLabelService;
import com.ginkgocap.ywxt.service.meeting.MeetingTopicService;
import com.ginkgocap.ywxt.service.meeting.TopicChatService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.DynamicNewsService;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.utils.DateUtil;
import com.ginkgocap.ywxt.utils.GinTongInterface;
import com.ginkgocap.ywxt.utils.HuanxinUtils;
import com.ginkgocap.ywxt.utils.ThreadPoolUtils;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.utils.type.AttendMeetStatusType;
import com.ginkgocap.ywxt.utils.type.AttendMeetType;
import com.ginkgocap.ywxt.utils.type.DefaultSignUpMeetingLabelType;
import com.ginkgocap.ywxt.utils.type.ExcuteMeetSignType;
import com.ginkgocap.ywxt.utils.type.MeetingStatusType;
import com.ginkgocap.ywxt.utils.type.NoticeReceiverType;
import com.ginkgocap.ywxt.utils.type.NoticeType;
import com.ginkgocap.ywxt.utils.type.SexType;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignLabelDataQuery;
import com.ginkgocap.ywxt.vo.query.meeting.UserBean;
//import com.gintong.easemob.server.httpclient.api.EasemobChatGroupsHandler;

/**
 * @Description: Controller
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/member")
public class MeetingMemberController extends BaseController {
	@Autowired
	private MeetingMemberService meetingMemberService;
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private UserService userService;
	@Autowired
	private MeetingNoticeService meetingNoticeService;
	@Autowired
	private MeetingSignLabelService meetingSignLabelService;
	@Autowired
	private MeetingSignLabelDataService meetingSignLabelDataService;
	@Autowired
	private PersonService personService;
	@Autowired
	private TopicChatService topicChatService;
	@Autowired
	private MeetingTopicService meetingTopicService;
	@Autowired
	private MeetingPicService meetingPicService;
	@Autowired
	private DynamicNewsService dynamicNewsService;
	@Autowired
	private ImRecordmessageService imRecordmessageService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 名称: addMettingMemberGet 描述: 当面邀请
	 * 
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/add.json", method = RequestMethod.GET)
	public Map<String, Object> addMettingMemberGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = addMettingMember(request, response);
		return model;
	}

	/**
	 * 名称: addMettingMember 描述: 当面邀请
	 * 
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public Map<String, Object> addMettingMember(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		User user = getUser(request);
		if (!isNullOrEmpty(user)
				&& !isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// json 转为对象
				MeetingMember meetingMember = (MeetingMember) JSONObject
						.toBean(j, MeetingMember.class);
				if (isNullOrEmpty(meetingMember)
						|| isNullOrEmpty(meetingMember.getMeetingId())) {
					responseDataMap.put("succeed", false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常!");
				} else {
					Meeting meeting = meetingService.getById(meetingMember.getMeetingId());
					if (isNullOrEmpty(meeting)) {
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议不存在");
					} else {
						String homePage = "";
						List<MeetingPic> listMeetingPic = meetingPicService.getByModuleId(meetingMember.getMeetingId(), MeetingPic.MODULE_TYPE_MEETING);
						if(!Utils.isNullOrEmpty(listMeetingPic)) {
							for(MeetingPic meetingPic : listMeetingPic) {
								if(!Utils.isNullOrEmpty(meetingPic)
										&& !Utils.isNullOrEmpty(meetingPic.getIshomePage())
										&& (1==meetingPic.getIshomePage().intValue())) {
									homePage = meetingPic.getPicPath();
									break;
								}
							}
						}
						if (!isNullOrEmpty(meetingMember.getId())) {
							responseDataMap.put("succeed", false);
							notificationMap.put("notifCode", "0002");
							notificationMap.put("notifInfo", "参数异常，id不应有值");
						} else {
							List<MeetingMember> list = meetingMemberService
									.getByMeetingIdAndMemberId(
											meetingMember.getMeetingId(),
											meetingMember.getMemberId());
							if (!isNullOrEmpty(list) || list.size() > 0) {
								MeetingMember meetingMemberTemp=list.get(0);
								if(!isNullOrEmpty(meetingMemberTemp)){
									meetingMember.setId(meetingMemberTemp.getId());
									// 用户已经报名参会
									if(AttendMeetType.SIGN_UP.code()==meetingMemberTemp.getAttendMeetType()){
										// 用户已经报名参会，且被审核通过
										if(meetingMemberTemp.getExcuteMeetSign()==ExcuteMeetSignType.AGREE_SIGN_UP.code()){
											responseDataMap.put("succeed", true);
											notificationMap.put("notifCode", "0002");
											notificationMap.put("notifInfo", "该用户已经参加了该会议");
										}else{
											// 用户已经报名参会，且被审核拒绝或者未审核
											meetingMember.setId(meetingMemberTemp.getId());
											meetingMember.setAttendMeetType(0);
											meetingMember.setMemberMeetStatus(1);
											meetingMember.setMemberType(1);
											meetingMember.setAttendMeetTime(new Date());
											meetingMemberService.saveOrUpdate(meetingMember);
											responseDataMap.put("succeed", true);
											logger.info("操作成功");
											notificationMap.put("notifCode", "0001");
											notificationMap.put("notifInfo", "hello mobile app!");
											//增加动态
											List<Long> listUserId = new ArrayList<Long>();
											listUserId.add(meetingMember.getMemberId());
			 								this.insertNewsAndRelation(meeting, homePage, user, listUserId);
										}
										// 用户被邀请参会
									}else if(AttendMeetType.INVITATION.code()==meetingMemberTemp.getAttendMeetType()){
										// 用户被邀请参会 并且已经接受邀请
										if(AttendMeetStatusType.ACCEPT_INVITATION.code()==meetingMemberTemp.getAttendMeetStatus()){
											responseDataMap.put("succeed", true);
											notificationMap.put("notifCode", "0002");
											notificationMap.put("notifInfo", "该用户已经参加了该会议");
										}else{
											// 用户被邀请参会，没有接受邀请
											meetingMember.setId(meetingMemberTemp.getId());
											meetingMember.setAttendMeetType(0);
											meetingMember.setMemberMeetStatus(1);
											meetingMember.setMemberType(1);
											meetingMember.setAttendMeetTime(new Date());
											meetingMemberService.saveOrUpdate(meetingMember);
											responseDataMap.put("succeed", true);
											logger.info("操作成功");
											notificationMap.put("notifCode", "0001");
											notificationMap.put("notifInfo", "hello mobile app!");
										}
									}
								} else {
									logger.error("MeetingMember垃圾数据:meeting_id="+meetingMember.getMeetingId()+",member_id="+meetingMember.getMemberId());
									responseDataMap.put("succeed", false);
									notificationMap.put("notifCode", "0002");
									notificationMap.put("notifInfo", "该用户已经参加了该会议");
								}
							} else {
								meetingMember.setAttendMeetStatus(0);
								meetingMember.setAttendMeetType(0);
								meetingMember.setMemberMeetStatus(0);
								meetingMember.setMemberType(1);
								meetingMemberService.saveOrUpdate(meetingMember);
								meetingMember.setAttendMeetTime(new Date());
								responseDataMap.put("succeed", true);
								notificationMap.put("notifCode", "0001");
								notificationMap.put("notifInfo", "hello mobile app!");
								logger.info("操作成功");
								
								/**
								 * 集成环信：往会议中添加新成员,只有接受邀请的才能注册到环信
								 */
								//TODO (keifer) 需要测试
								/*final String chatGroupId = meeting.getGroupId();
								final Long addMemberId = meetingMember.getMemberId();
								executorService.execute(new Runnable() {
									@Override
									public void run() {
										addUsersToChatGroups(chatGroupId,addMemberId);
									}
								});*/
								
								//增加动态
								List<Long> listUserId = new ArrayList<Long>();
								listUserId.add(meetingMember.getMemberId());
								this.insertNewsAndRelation(meeting, homePage, user, listUserId);
							}
						}
					}
				}
			} catch (Exception e) {
				responseDataMap.put("succeed", false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数异常");
				logger.error("当面邀请失败", e);
			}
		} else {
			responseDataMap.put("succeed", false);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
			logger.error("传入参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/*
	private void addUsersToChatGroups(String chatGroupId,Long memeberId){
		ArrayNode usernames = JsonNodeFactory.instance.arrayNode();
		if(null != memeberId && memeberId > 0){
			usernames.add(String.valueOf(memeberId));
		}
		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
		usernamesNode.put("usernames", usernames);
		EasemobChatGroupsHandler.addUsersToGroupBatch(chatGroupId,usernamesNode);
	}*/
	
	//增加动态
	private void insertNewsAndRelation(Meeting meeting, String homePage, User user, List<Long> listUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", "40");
		param.put("lowType", ""); 
		param.put("createrId", String.valueOf(user.getId()));
		param.put("createrName", user.getName());
		param.put("picPath", user.getPicPath());
		param.put("title", meeting.getMeetingName()+""); 
		param.put("content", meeting.getMeetingDesc()+""); 
		param.put("targetId", meeting.getId()+""); 
		// 附件表查询第一张图片 
		param.put("imgPath", homePage); 
		param.put("forwardingContent", ""); 
		Map<String,List<Long>> mapUserRight = new HashMap<String, List<Long>>();
		List<Long> dales = new ArrayList<Long>();
		dales.addAll(listUserId);
		List<Long> zhongles = new ArrayList<Long>();
		mapUserRight.put("dale", dales);
		mapUserRight.put("zhongle", zhongles);
		param.put("receiverIds", mapUserRight);
		dynamicNewsService.insertNewsAndRelationByParam(param);
	}
	/**
	 * 名称: signInMeetingGet 描述: 签到
	 * 
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/signInMeeting.json", method = RequestMethod.GET)
	public Map<String, Object> signInMeetingGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = signInMeeting(request, response);
		return model;
	}

	/**
	 * 名称: signInMeeting 描述: 签到
	 * 
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/signInMeeting.json", method = RequestMethod.POST)
	public Map<String, Object> signInMeeting(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("传入参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				// 会议成员id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				// 签到距离
				String signDistance = getStringJsonValueByKey(j, "signDistance");

				if (Utils.isAllNotNullOrEmpty(meetingIdStr, memberIdStr)) {
					Long meetingId = Long.valueOf(meetingIdStr);
					Long memberId = Long.valueOf(memberIdStr);
					Meeting meeting = meetingService.getById(meetingId);
					if (isNullOrEmpty(meeting)) {
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议不存在!");
					} else {
						List<MeetingMember> list = meetingMemberService
								.getByMeetingIdAndMemberId(meetingId, memberId);
						if (isNullOrEmpty(list)) {
							responseDataMap.put("succeed", false);
							notificationMap.put("notifCode", "0002");
							notificationMap.put("notifInfo", "您没有参加该会议");
						} else {
							MeetingMember meetingMember = list.get(0);
							if (isNullOrEmpty(meetingMember)
									|| isNullOrEmpty(meetingMember.getId())) {
								responseDataMap.put("succeed", false);
								notificationMap.put("notifCode", "0002");
								notificationMap.put("notifInfo", "您没有参加该会议");
							} else {
								if (!isNullOrEmpty(meetingMember.getIsSign())&&meetingMember.getIsSign() == 1) {
									responseDataMap.put("succeed", true);
									notificationMap.put("notifCode", "0001");
									notificationMap.put("notifInfo", "签到成功");
								} else {
									meetingMember.setIsSign(1);
									meetingMember.setSignDistance(signDistance);
									meetingMemberService
											.saveOrUpdate(meetingMember);
									User user=getUser(request);
									// 签到发送系统消息
									List<MeetingTopic> listMeetingTopic=meetingTopicService.getByMeetingId(meetingMember.getMeetingId());
									for(MeetingTopic meetingTopic:listMeetingTopic){
										sendingSystemMessage(user,meetingTopic);
									}
									responseDataMap.put("succeed", true);
									notificationMap.put("notifCode", "0001");
									notificationMap.put("notifInfo", "签到成功");
								}
							}
						}
					}

				} else {
					logger.error("会议id或者会议成员id为空");
					responseDataMap.put("succeed", false);
					responseDataMap.put("notifInfo", "会议id或者会议成员id为空");
				}

			} catch (Exception e) {
				logger.error("操作异常");
				responseDataMap.put("succeed", false);
				responseDataMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("传入参数异常");
			responseDataMap.put("succeed", false);
			responseDataMap.put("notifInfo", "传入参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 签到发送系统消息
	 */
	public void sendingSystemMessage(User user,MeetingTopic meetingTopic){
		if(isNullOrEmpty(meetingTopic)||isNullOrEmpty(meetingTopic.getMeetingId())||isNullOrEmpty(meetingTopic.getId())
				||isNullOrEmpty(user)){
		} else {
			TopicChat topicChat=new TopicChat();
			topicChat.setMeetingId(meetingTopic.getMeetingId());
			topicChat.setTopicId(meetingTopic.getId());
			topicChat.setChatContent(user.getName()+"已签到");
			topicChat.setMessageId(Utils.genMessageID(user.getId()));
			topicChatService.sendingSystemMessage(topicChat);
		}
	}
	/**
	 * 名称: signUpReviewGet 描述: 报名审核
	 * 
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/signUpReview.json", method = RequestMethod.GET)
	public Map<String, Object> signUpReviewGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = signUpReview(request, response);
		return model;
	}

	/**
	 * 名称: signUpReview 描述: 报名审核
	 * 
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/signUpReview.json", method = RequestMethod.POST)
	public Map<String, Object> signUpReview(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("传入参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				//群组id
				final String groupId = getStringJsonValueByKey(j, "groupId");
				// 会议成员id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				User user = getUser(request);
				// 审核状态
				String reviewStatus = getStringJsonValueByKey(j, "reviewStatus");
				if (Utils.isAllNotNullOrEmpty(meetingIdStr, memberIdStr, reviewStatus)) {
					Long meetingId = Long.valueOf(meetingIdStr);
					Long memberId = Long.valueOf(memberIdStr);
					// 获取会议
					Meeting meeting = meetingService.getById(meetingId);
					if (isNullOrEmpty(meeting)) {
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议不存在");
						setSessionAndErr(request, response, "-1", "会议不存在");
					} else if (meeting.getIsSecrecy()) {
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "该会议是保密会议");
						setSessionAndErr(request, response, "-1", "该会议是保密会议");
					} else {
						// 获取该用户的参会信息
						List<MeetingMember> list = meetingMemberService.getByMeetingIdAndMemberId(meetingId, memberId);
						if (null == list || list.isEmpty()) {
							responseDataMap.put("succeed", false);
							notificationMap.put("notifCode", "0002");
							notificationMap.put("notifInfo", "该用户没有参加该会议");
							setSessionAndErr(request, response, "-1", "该用户没有参加该会议");
						} else {
							MeetingMember meetingMember = list.get(0);
							if (isNullOrEmpty(meetingMember)
									|| isNullOrEmpty(meetingMember.getId())) {
								responseDataMap.put("succeed", false);
								notificationMap.put("notifCode", "0002");
								notificationMap.put("notifInfo", "该用户没有参加该会议");
								setSessionAndErr(request, response, "-1", "该用户没有参加该会议");
							} else {
								// 看是否已经审核过了
								if (meetingMember.getExcuteMeetSign() != 0) {
									responseDataMap.put("succeed", false);
									notificationMap.put("notifCode", "0002");
									notificationMap.put("notifInfo", "已经审核过了");
									setSessionAndErr(request, response, "-1", "已经审核过了,不需重复审核");
								} else if (meetingMember.getAttendMeetType() != 1) {
									responseDataMap.put("succeed", false);
									notificationMap.put("notifCode", "0002");
									notificationMap.put("notifInfo", "该用户不是通过报名方式参会，不需审核");
									setSessionAndErr(request, response, "-1", "该用户不是通过报名方式参会，不需审核");
								} else if (meetingMember.getAttendMeetStatus() != 4) {
									responseDataMap.put("succeed", false);
									notificationMap.put("notifCode", "0002");
									notificationMap.put("notifInfo",
											"该用户报名状态已变更，不需审核");
									setSessionAndErr(request, response, "-1", "该用户报名状态已变更，不需审核");
								} else {
									if(!isNullOrEmpty(meetingMember)&&!isNullOrEmpty(meetingMember.getMemberId())){
										Long meetingMemberId=meetingMember.getMemberId();
										User meetingMemberUser=userService.selectByPrimaryKey(meetingMemberId);
										if(!isNullOrEmpty(meetingMemberUser)&&!isNullOrEmpty(meetingMemberUser.getName())){
											meetingMember.setMemberName(meetingMemberUser.getName());
										}
									}
									if ("1".equals(reviewStatus)) {
										Integer count = meetingMemberService.getSignUpMemberCount(meetingId);
										// 同意报名
										if (!isNullOrEmpty(meeting.getMemberCount())
												&& meeting.getMemberCount() > 0
												&& meeting.getMemberCount() <= count) {
											responseDataMap.put("succeed", false);
											notificationMap.put("notifCode", "0002");
											notificationMap.put("notifInfo", "报名人数已满额，不能审核");
											setSessionAndErr(request, response, "-1", "报名人数已满额，不能审核");
										} else {
											meetingMember.setExcuteMeetSign(ExcuteMeetSignType.AGREE_SIGN_UP.code());
											meetingMemberService.signUpReview(0, meeting, meetingMember, user);
											responseDataMap.put("succeed", true);
											notificationMap.put("notifCode", "0001");
											notificationMap.put("notifInfo", "报名审核通过");
											setSessionAndErr(request, response, "0", "报名审核通过");
											/**
										 	* 集成环信：会议添加成员
										 	*/
											final Long userId = meetingMember.getMemberId();
											final Long creatorUserId = meeting.getCreateId();
											ThreadPoolUtils.getExecutorService().execute(new Runnable() {
												@Override
												public void run() {
													//2016-03-03 tanm 将操作环信的部分改为操作畅聊
													GinTongInterface.invite2MUC(creatorUserId, Arrays.asList(Long.valueOf(userId)), groupId);
//													HuanxinUtils.addUserToChatGroups(groupId,userId);
												}
											});
											//增加ImRecordmessage记录
											ImRecordmessage recordMessageBean = new ImRecordmessage();
											recordMessageBean.setUserId(Integer.valueOf(memberIdStr));
											recordMessageBean.setMucId(Integer.valueOf(String.valueOf(meeting.getId())));
											recordMessageBean.setMucCreateUserId(Integer.valueOf(String.valueOf(meeting.getCreateId())));
											recordMessageBean.setType(3);//标示会议
											recordMessageBean.setNewCount(0);
											recordMessageBean.setMucStartDate(meeting.getStartTime());
											recordMessageBean.setMessageStartTime(new Date());
											recordMessageBean.setStatus(String.valueOf(meeting.getMeetingStatus()));
											imRecordmessageService.saveOrUpdate(recordMessageBean);
										}
									} else if ("2".equals(reviewStatus)) {
										meetingMember.setExcuteMeetSign(ExcuteMeetSignType.REFUSE_SIGN_UP.code());
										meetingMemberService.signUpReview(1,
												meeting, meetingMember, user);
										responseDataMap.put("succeed", true);
										notificationMap
												.put("notifCode", "0001");
										notificationMap.put("notifInfo",
												"该会议没有接受你的报名");
										setSessionAndErr(request, response, "0", "该会议没有接受你的报名");
									}
								}

							}
						}
					}
				} else {
					logger.error("会议id或者会议成员id为空");
					responseDataMap.put("succeed", false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "会议id或者会议成员id为空");
				}

			} catch (Exception e) {
				logger.error("操作异常");
				responseDataMap.put("succeed", false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("传入参数异常");
			responseDataMap.put("succeed", false);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "传入参数异常");
		}

		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	
	
	
	/**
	 * 名称: signUpMeetingGet 描述: 报名会议
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/signUpMeeting.json", method = RequestMethod.GET)
	public Map<String, Object> signUpMeetingGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = signUpMeeting(request, response);
		return model;
	}

	/**
	 * 名称: signUpMeeting 描述: 报名会议
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/signUpMeeting.json", method = RequestMethod.POST)
	public Map<String, Object> signUpMeeting(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("传入参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				// 会议成员id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				// 报名会议成员姓名
				String memberName = getStringJsonValueByKey(j, "memberName");
				// 报名会议成员头像
				String memberPhoto = getStringJsonValueByKey(j, "memberPhoto");
				if(!isNullOrEmpty(memberIdStr)){
					Long meetingMemberId=Long.valueOf(memberIdStr);
					User meetingMemberUser = userService.selectByPrimaryKey(meetingMemberId);
					if(!isNullOrEmpty(meetingMemberUser) && !isNullOrEmpty(meetingMemberUser.getName())){
						memberName = meetingMemberUser.getName();
					}
				}
				if (Utils.isAllNotNullOrEmpty(meetingIdStr, memberIdStr)) {
					Long meetingId = Long.valueOf(meetingIdStr);
					Long memberId = Long.valueOf(memberIdStr);
					Meeting meeting = meetingService.getById(meetingId);
					if (isNullOrEmpty(meeting)) {
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议不存在");
					} else if (!isNullOrEmpty(meeting.getIsSecrecy())
							&& meeting.getIsSecrecy() == true) {
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议是保密会议，不允许报名");
					} else {
						List<MeetingMember> list = meetingMemberService
								.getByMeetingIdAndMemberId(meetingId, memberId);
						if (!isNullOrEmpty(list)) {
							MeetingMember meetingMember = list.get(0);
							if (!isNullOrEmpty(meetingMember)) {
								if (meeting.getCreateId().toString().equals(memberIdStr)) {
									responseDataMap.put("succeed", false);
									notificationMap.put("notifCode", "0002");
									notificationMap.put("notifInfo",
											"你是会议发起人不能报名");
								}else if (meetingMember.getAttendMeetType() == 0) {
									if(AttendMeetStatusType.REFUSE_INVITATION.code()==meetingMember.getAttendMeetStatus()){
										User user = getUser(request);
										// 报名
										signUp(meetingMember.getId(),memberName, memberPhoto, memberId, user,
												meeting);
										responseDataMap.put("succeed", true);
										notificationMap.put("notifCode", "0001");
										notificationMap.put("notifInfo",
												"hello mobile app!");
									}else{
										responseDataMap.put("succeed", false);
										notificationMap.put("notifCode", "0002");
										notificationMap.put("notifInfo",
												"会议举办方已经邀请您参会，无需报名");
									}
								} else if(meetingMember.getAttendMeetType() == 1&&meetingMember.getExcuteMeetSign()==2){
									User user = getUser(request);
									// 报名
									signUp(meetingMember.getId(),memberName, memberPhoto, memberId, user,
											meeting);
									responseDataMap.put("succeed", true);
									notificationMap.put("notifCode", "0001");
									notificationMap.put("notifInfo",
											"hello mobile app!");
								}else{
									responseDataMap.put("succeed", false);
									notificationMap.put("notifCode", "0002");
									notificationMap.put("notifInfo","等待审核或者您已经参会");
								}
							}else{
								User user = getUser(request);
								// 报名
								signUp(meetingMember.getId(),memberName, memberPhoto, memberId, user,
										meeting);
								responseDataMap.put("succeed", true);
								notificationMap.put("notifCode", "0001");
								notificationMap.put("notifInfo",
										"hello mobile app!");
							}
						} else {
							User user = getUser(request);
							// 报名
							signUp(null,memberName, memberPhoto, memberId, user,
									meeting);
							responseDataMap.put("succeed", true);
							notificationMap.put("notifCode", "0001");
							notificationMap.put("notifInfo",
									"hello mobile app!");

						}
					}
				} else {
					logger.error("会议id或者会议成员id为空");
					responseDataMap.put("succeed", false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "会议id或者会议成员id为空");
				}
			} catch (Exception e) {
				logger.error("操作异常");
				responseDataMap.put("succeed", false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("传入参数异常");
			responseDataMap.put("succeed", false);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "传入参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	public void signUp(Long id,String memberName, String memberPhoto, Long memberId,
			User user, Meeting meeting) throws Exception {
		MeetingMember meetingMember = new MeetingMember();
		meetingMember.setMeetingId(meeting.getId());
		meetingMember.setMemberId(memberId);
		meetingMember.setExcuteMeetSign(0);
		// 默认设置为群众
		meetingMember.setMemberType(1);
		if(!isNullOrEmpty(id)){
			meetingMember.setId(id);	
		}
		if (!isNullOrEmpty(memberName)) {
			meetingMember.setMemberName(memberName);
		}
		if (!isNullOrEmpty(memberPhoto)) {
			meetingMember.setMemberPhoto(memberPhoto);
		}
		/** 0：默认，1：归档，2：删除 **/
		meetingMember.setMemberMeetStatus(0);
		/**
		 * 参会状态 0.未答复 1接受邀请2拒绝邀请， 4 报名 5取消报名
		 */
		meetingMember.setAttendMeetStatus(4);
		/** 0 邀请，1 报名 **/
		meetingMember.setAttendMeetType(1);
		meetingMember.setAttendMeetTime(new Date());
		// 报名
		meetingMemberService.signUp(meeting, meetingMember, user);
	}

	/**
	 * 名称: changeAttendMeetStatusGet 描述: 改变成员参会状态: 0未答复1接受邀请（取消报名）2拒绝邀请，5取消报名
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/changeAttendMeetStatus.json", method = RequestMethod.GET)
	public Map<String, Object> changeAttendMeetStatusGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = changeAttendMeetStatus(request, response);
		return model;
	}

	/**
	 * 名称: changeAttendMeetStatusGet 描述: 改变成员参会状态 :0未答复1接受邀请2拒绝邀请，5取消报名
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/changeAttendMeetStatus.json", method = RequestMethod.POST)
	public Map<String, Object> changeAttendMeetStatus(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("传入参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				User user = getUser(request);
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				String groupId = getStringJsonValueByKey(j, "groupId");
				// 会议成员id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				// type 操作类型：0.未答复 1接受邀请2拒绝邀请， 4 报名 5取消报名
				String type = getStringJsonValueByKey(j, "type");

				if (Utils.isAllNotNullOrEmpty(meetingIdStr, memberIdStr, type)) {
					Meeting meeting = meetingService.getById(Long.valueOf(meetingIdStr));
					if (isNullOrEmpty(meeting)) {
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议不存在!");
					} else {
						if(AttendMeetStatusType.ACCEPT_INVITATION.code()==Integer.valueOf(type)||AttendMeetStatusType.REFUSE_INVITATION.code()==Integer.valueOf(type)){
							// 只有未开始和进行中的会议可以接受邀请或者拒绝邀请
							if(MeetingStatusType.NOT_BEGIN.code()==meeting.getMeetingStatus()||MeetingStatusType.IN_MEETING.code()==meeting.getMeetingStatus()){
								meetingMemberService.changeAttendMeetStatus(
										Long.valueOf(meetingIdStr),
										Long.valueOf(memberIdStr),
										Integer.valueOf(type), user);
								responseDataMap.put("succeed", true);
								notificationMap.put("notifCode", "0001");
								notificationMap.put("notifInfo", "hello mobile app!");
								final String group_Id = StringUtils.isBlank(groupId) ? meeting.getGroupId() : groupId;//解决客户端发送groupId丢失的问题
								/**
							 	* 集成环信：会议添加成员
							 	*/
								final String userId = memberIdStr;
								final long creatorUserId = meeting.getCreateId();
								ThreadPoolUtils.getExecutorService().execute(new Runnable() {
									@Override
									public void run() {
										//2016-03-03 tanm 将操作环信的部分改为操作畅聊
										GinTongInterface.invite2MUC(creatorUserId, Arrays.asList(Long.valueOf(userId)), group_Id);
//										HuanxinUtils.addUserToChatGroups(group_Id,userId);
									}
								});
								//增加ImRecordmessage记录
								ImRecordmessage recordMessageBean = new ImRecordmessage();
								recordMessageBean.setUserId(Integer.valueOf(memberIdStr));
								recordMessageBean.setMucId(Integer.valueOf(String.valueOf(meeting.getId())));
								recordMessageBean.setMucCreateUserId(Integer.valueOf(String.valueOf(meeting.getCreateId())));
								recordMessageBean.setType(3);//标示会议
								recordMessageBean.setNewCount(0);
								recordMessageBean.setMucStartDate(meeting.getStartTime());
								recordMessageBean.setMessageStartTime(new Date());
								recordMessageBean.setStatus(String.valueOf(meeting.getMeetingStatus()));
								imRecordmessageService.saveOrUpdate(recordMessageBean);
							}else{
								responseDataMap.put("succeed", false);
								notificationMap.put("notifCode", "0002");
								notificationMap.put("notifInfo", "只有未开始和进行中的会议可以接受邀请或者拒绝邀请!");
							}
						}else if(AttendMeetStatusType.CANCEL_SIGN_UP.code()==Integer.valueOf(type)
								|| AttendMeetStatusType.QUIT_MEETING.code()==Integer.valueOf(type)){//取消报名、退出会议
							if(MeetingStatusType.NOT_BEGIN.code()==meeting.getMeetingStatus()
									|| MeetingStatusType.IN_MEETING.code()==meeting.getMeetingStatus()){
								meetingMemberService.changeAttendMeetStatus(
										Long.valueOf(meetingIdStr),
										Long.valueOf(memberIdStr),
										Integer.valueOf(type), user);
								responseDataMap.put("succeed", true);
								notificationMap.put("notifCode", "0001");
								notificationMap.put("notifInfo", "hello mobile app!");
							}else{
								responseDataMap.put("succeed", false);
								notificationMap.put("notifCode", "0002");
								notificationMap.put("notifInfo", "只有未开始和进行中的会议可以退出!");
							}
						}
					}
				} else {
					logger.error("会议id或者会议成员id为空");
					responseDataMap.put("succeed", false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "会议id或者会议成员id为空!");
				}

			} catch (Exception e) {
				logger.error("操作异常");
				responseDataMap.put("succeed", false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("传入参数异常");
			responseDataMap.put("succeed", false);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 名称: getmeetingMemberListGet 描述: 获取参会人列表
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/getmeetingMemberList.json", method = RequestMethod.GET)
	public Map<String, Object> getmeetingMemberListGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = getmeetingMemberList(request, response);
		return model;
	}

	/**
	 * 名称: getmeetingMemberList 描述: 获取参会人列表
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/getmeetingMemberList.json", method = RequestMethod.POST)
	public Map<String, Object> getmeetingMemberList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");

				if (Utils.isAllNotNullOrEmpty(meetingIdStr)) {
					Long meetingId = Long.valueOf(meetingIdStr);
					List<MeetingMember> listMember = meetingMemberService
							.getByMeetingId(meetingId);
					responseDataMap.put("listMeetingMember", listMember);
				} else {
					logger.error("会议id为空");
					responseDataMap.put("listMeetingMember",
							new ArrayList<MeetingMember>());
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
				responseDataMap.put("listMeetingMember",
						new ArrayList<MeetingMember>());
				e.printStackTrace();
			}
		} else {
			logger.error("传入参数异常");
			responseDataMap.put("listMeetingMember",
					new ArrayList<MeetingMember>());
		}
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello mobile app!");
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 名称: getmeetingVisitantListGet 描述: 获取参会嘉宾
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/getmeetingVisitantList.json", method = RequestMethod.GET)
	public Map<String, Object> getmeetingVisitantListGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = getmeetingVisitantList(request, response);
		return model;
	}

	/**
	 * 名称: getmeetingVisitantList 描述: 获取参会嘉宾
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/getmeetingVisitantList.json", method = RequestMethod.POST)
	public Map<String, Object> getmeetingVisitantList(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("传入参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");

				if (Utils.isAllNotNullOrEmpty(meetingIdStr)) {
					Long meetingId = Long.valueOf(meetingIdStr);
					List<MeetingMember> listMember = meetingMemberService
							.getVisitantByMeetingId(meetingId);
					responseDataMap.put("listMeetingMember", listMember);
				} else {
					logger.error("会议id为空");
					responseDataMap.put("listMeetingMember",
							new ArrayList<MeetingMember>());
				}

			} catch (Exception e) {
				logger.error("操作异常");
				responseDataMap.put("listMeetingMember",
						new ArrayList<MeetingMember>());
				e.printStackTrace();
			}
		} else {
			logger.error("传入参数异常");
			responseDataMap.put("listMeetingMember",
					new ArrayList<MeetingMember>());
		}
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello mobile app!");
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 名称: getRequiredSignupInfoGet 描述: 获取用户报名会议必填信息
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/getRequiredSignupInfo.json", method = RequestMethod.GET)
	public Map<String, Object> getRequiredSignupInfoGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = getRequiredSignupInfo(request, response);
		return model;
	}

	/**
	 * 名称: getRequiredSignupInfo 描述: 获取用户报名会议必填信息
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/getRequiredSignupInfo.json", method = RequestMethod.POST)
	public Map<String, Object> getRequiredSignupInfo(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("传入参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		List<MeetingSignLabelDataQuery> listResult = new ArrayList<MeetingSignLabelDataQuery>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				if (isNullOrEmpty(meetingIdStr)) {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "会议iD不能为空!");
				} else {
//					UserBean userBean = getUserBean(request);
					User user = getUser(request);
					// 获取用户详细资料呢
//					PeopleTemp people = peopleMongoService.selectByPrimary(user.getPeopleId());
					Person person = null;
					if (StringUtils.isNotBlank(user.getPeopleId())) {
						person = personService.get(Long.valueOf(user.getPeopleId()));
					}

					List<MeetingSignLabel> list = meetingSignLabelService
							.getByMeetingId(Long.valueOf(meetingIdStr));
					if (!isNullOrEmpty(list)) {
						// 遍历标签
						for (MeetingSignLabel meetingSignLabel : list) {
							if (!isNullOrEmpty(meetingSignLabel)) {
								MeetingSignLabelDataQuery meetingSignLabelDataQuery = new MeetingSignLabelDataQuery();
								// 封装报名用户ID
								meetingSignLabelDataQuery.setMemberId(user
										.getId());
								// 封装报名字段编号
								meetingSignLabelDataQuery
										.setMslabelId(meetingSignLabel.getId());
								// 封装报名字段名字
								meetingSignLabelDataQuery
										.setMslabelName(meetingSignLabel
												.getLabelName());
								// 封装是否自定义字段
								meetingSignLabelDataQuery
										.setIsCustomer(meetingSignLabel
												.getIsCustom());
								// 是否为后台人脉原有数据
								meetingSignLabelDataQuery.setIsPeopleData(0);
								// 非自定义字段
								if (0 == meetingSignLabel.getIsCustom()) {
									if (!isNullOrEmpty(person)) {
										// 获取名字
										if (DefaultSignUpMeetingLabelType.NAME.toString().equals(meetingSignLabel
												.getLabelName())) {
											// 获取名字集合
											List<PersonName> peopleNameList = person
													.getPeopleNameList();
											if (!isNullOrEmpty(peopleNameList)) {
												// 取第一个名字
												PersonName peopleName = peopleNameList
														.get(0);
												if (!isNullOrEmpty(peopleName)) {
													// 是否为后台人脉原有数据
													meetingSignLabelDataQuery
															.setIsPeopleData(1);
													meetingSignLabelDataQuery
															.setLabelContent(StringUtils.trimToEmpty(peopleName.getLastname()) + StringUtils.trimToEmpty(peopleName.getFirstname()));
												}
											}
										}
										if (DefaultSignUpMeetingLabelType.SEX.toString().equals(meetingSignLabel
												.getLabelName())) {
											// 获取性别
											Byte gender = person.getGender();
											if (!isNullOrEmpty(gender)) {
												String sex = "";
												if (gender == 1)
													sex = SexType.MALE.toString();
												else if (gender == 1)
													sex = SexType.FEMALE.toString();
												else
													sex = SexType.UNKNOWN.toString();
												meetingSignLabelDataQuery
														.setIsPeopleData(1);
												meetingSignLabelDataQuery
														.setLabelContent(sex);
											}
										}
										// 获取传真
										if (DefaultSignUpMeetingLabelType.FAX.toString().equals(meetingSignLabel
												.getLabelName())) {
											packagePeopleContactCommToSignLabel(
													meetingSignLabel,
													meetingSignLabelDataQuery,
													person.getContactInformationList(), Basic.PARENT_TYPE_FAX);
										}
										// 获取手机
										if (DefaultSignUpMeetingLabelType.MOBILEPHONE.toString().equals(meetingSignLabel
												.getLabelName())) {
											packagePeopleContactCommToSignLabel(
													meetingSignLabel,
													meetingSignLabelDataQuery,
													person.getContactInformationList(), Basic.PARENT_TYPE_MOBILE);
										}
										// 获取固话
										if (DefaultSignUpMeetingLabelType.FIXEDPHONE.toString().equals(meetingSignLabel
												.getLabelName())) {
											packagePeopleContactCommToSignLabel(
													meetingSignLabel,
													meetingSignLabelDataQuery,
													person.getContactInformationList(), Basic.PARENT_TYPE_FIXED_PHONE);
										}
										// 获取邮箱
										if (DefaultSignUpMeetingLabelType.EMAIL.toString().equals(meetingSignLabel
												.getLabelName())) {
											packagePeopleContactCommToSignLabel(
													meetingSignLabel,
													meetingSignLabelDataQuery,
													person.getContactInformationList(), Basic.PARENT_TYPE_EMAIL);
										}
										// 获取地址
										if (DefaultSignUpMeetingLabelType.ADDRESS.toString().equals(meetingSignLabel
												.getLabelName())) {
											// 获取地址集合
											String addr = StringUtils.trimToEmpty(person.getLocationCountry())
													+ StringUtils.trimToEmpty(person.getLocationCounty())
													+ StringUtils.trimToEmpty(person.getLocationCity())
													+ StringUtils.trimToEmpty(person.getAddress());
											if (StringUtils.isNotBlank(addr)) {
												// 是否为后台人脉原有数据
												meetingSignLabelDataQuery.setIsPeopleData(1);
												meetingSignLabelDataQuery.setLabelContent(addr);
											}
										}
										// 获取工作经历
										List<WorkExperience> workExperienceList = person.getWorkExperienceList();
										if (null != workExperienceList && workExperienceList.size() > 0) {
											WorkExperience peopleWorkExperience = workExperienceList.get(0);
											// 获取职务
											if (DefaultSignUpMeetingLabelType.POSITION.toString().equals(meetingSignLabel
													.getLabelName())) {
												String position = peopleWorkExperience.getPosition();
												// 封装职务
												if (StringUtils.isNotBlank(position)) {
													// 是否为后台人脉原有数据
													meetingSignLabelDataQuery.setIsPeopleData(1);
													meetingSignLabelDataQuery.setLabelContent(position);
												}
											}
											// 获取单位名称
											if (DefaultSignUpMeetingLabelType.COMPANY.toString()
													.equals(meetingSignLabel
															.getLabelName())) {
												String company = peopleWorkExperience.getCompany();
												// 封装单位名称
												if (StringUtils.isNotBlank(company)) {
													// 是否为后台人脉原有数据
													meetingSignLabelDataQuery.setIsPeopleData(1);
													meetingSignLabelDataQuery.setLabelContent(company);
												}
											}
											// 获取所属部门
											if (DefaultSignUpMeetingLabelType.DEPARTMENT.toString()
													.equals(meetingSignLabel
															.getLabelName())) {
												String department = peopleWorkExperience
														.getDepartment();
												// 封装所属部门
												if (StringUtils.isNotBlank(department)) {
													// 是否为后台人脉原有数据
													meetingSignLabelDataQuery.setIsPeopleData(1);
													meetingSignLabelDataQuery.setLabelContent(department);
												}
											}
										}
									}
								}
								listResult.add(meetingSignLabelDataQuery);
							}
						}
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello APP!");
					}
				}

			} catch (Exception e) {
				logger.error("操作异常");
				e.printStackTrace();
			}
		} else {
			logger.error("传入参数异常");
		}
		responseDataMap.put("listMeetingSignLabelDataQuery", listResult);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	

	/**
	 * 名称: improveSignInformation 描述: 完善报名信息
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/improveSignInformation.json", method = RequestMethod.GET)
	public Map<String, Object> improveSignInformationGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = improveSignInformation(request, response);
		return model;
	}

	/**
	 * 名称: improveSignInformation 描述: 完善报名信息
	 *
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 */
	@ResponseBody
	@RequestMapping(value = "/improveSignInformation.json", method = RequestMethod.POST)
	public Map<String, Object> improveSignInformation(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("传入参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		User user = getUser(request);
		if (StringUtils.isBlank(requestJson)) {
			logger.error("传入参数异常");
			responseDataMap.put("succeed", false);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "传入参数异常");
			return model;
		}
		try {
			// 获取用户必填信息
			JSONObject j = JSONObject.fromObject(requestJson);
			JSONArray jsonArray = j.getJSONArray("listMeetingSignLabelDataQuery");
			List<MeetingSignLabelDataQuery> list = JSONArray.toList(jsonArray, MeetingSignLabelDataQuery.class);
			if(null != list && list.size() > 0){
				MeetingSignLabelDataQuery meetingSignLabelDataQuery = list.get(0);
				if(!Utils.isNullOrEmpty(meetingSignLabelDataQuery)) {
					//删除已填写的报名信息
					MeetingSignLabel meetingSignLabel = meetingSignLabelService.getById(meetingSignLabelDataQuery.getMslabelId());
					if(!Utils.isNullOrEmpty(meetingSignLabel)
							&& !Utils.isNullOrEmpty(user)) {
						meetingSignLabelDataService.deleteByMeetingIdAndMemberId(meetingSignLabel.getMeetingId(), user.getId());
					}
				}
				//保存新的报名信息
				meetingSignLabelDataService.addBatchMeetingSignLabelData(list);
			}
			responseDataMap.put("succeed", true);
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello mobile app!");
		} catch (Exception e) {
			responseDataMap.put("succeed", false);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
			logger.error("", e);
		}
		return model;
	}
	
	/**
	 * 封装报名用户到人脉数据
	 * 
	 * @param meetingSignLabel
	 *            会议报名必填字段
	 * @param meetingSignLabelDataQuery
	 *            会议报名必填字段及内容
	 * @param ContactCommList
	 *            基本联系人集合
	 */
	/*
	public PeopleTemp packageSignLabelToPeopleContactComm(PeopleTemp peopleTemp,MeetingSignLabelDataQuery meetingSignLabelDataQuery,
			List<PeopleContactComm> ContactCommList) {
		List<PeopleContactComm> listPeopleContactComm=new ArrayList<PeopleContactComm>();
		// 人脉原有手机不为空 
		if(!isNullOrEmpty(peopleTemp.getContactMobileList())){
			 listPeopleContactComm=peopleTemp.getContactMobileList();
		}
		    // 封装手机
			PeopleContactComm peopleContactComm=new PeopleContactComm();
			// 标签对象
			PeopleSelectTag peopleSelectTag=new PeopleSelectTag();
			peopleSelectTag.setType(1);
			peopleSelectTag.setName(meetingSignLabelDataQuery.getMslabelName());
			peopleContactComm.setTypeTag(peopleSelectTag);
			peopleContactComm.setContent(meetingSignLabelDataQuery.getLabelContent());
			listPeopleContactComm.add(peopleContactComm);
			peopleTemp.setContactMobileList(listPeopleContactComm);
			return peopleTemp;
	}
	*/
	/**
	 * 封装人脉数据到报名用户
	 * 
	 * @param meetingSignLabel
	 *            会议报名必填字段
	 * @param meetingSignLabelDataQuery
	 *            会议报名必填字段及内容
	 * @param contactList
	 *            基本联系人集合
	 */
	public void packagePeopleContactCommToSignLabel(MeetingSignLabel meetingSignLabel,
			MeetingSignLabelDataQuery meetingSignLabelDataQuery,
			List<Basic> contactList, Byte basicType) {
		// 获取联系方式集合
		if (null == contactList || contactList.isEmpty()) {
			return;
		}
		for (Basic co : contactList) {
			if (co == null || co.getType() == null || co.getType().equals(basicType.toString()) == false)
				continue;
			// 是否为后台人脉原有数据
			meetingSignLabelDataQuery.setIsPeopleData(1);
			// 封装数据
			meetingSignLabelDataQuery.setLabelContent(co.getContent());
		}
	}
	/**
	 * 删除参会人
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMeetingMember.json", method = RequestMethod.GET)
	public Map<String, Object> deleteMeetingMemberGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = deleteMeetingMember(request, response);
		return model;
	}
	/**
	 * 删除参会人
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMeetingMember.json", method = RequestMethod.POST)
	public Map<String, Object> deleteMeetingMember(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("传入参数异常");
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		boolean flag = false;
		String message = "删除参会人成功";
		User user = getUser(request);
		if(!Utils.isNullOrEmpty(user)) {
			if (StringUtils.isBlank(requestJson)) {
				message = "传入参数异常";
				logger.error("传入参数异常");
			} else {
				try {
					JSONObject j = JSONObject.fromObject(requestJson);
					String meetingId = getStringJsonValueByKey(j, "meetingId");
					String memberId = getStringJsonValueByKey(j, "memberId");
					if(!Utils.isNullOrEmpty(meetingId) && !Utils.isNullOrEmpty(memberId)) {
						Meeting meeting = meetingService.getById(Long.parseLong(meetingId));
						if(Utils.isNullOrEmpty(meeting)) {
							message = "会议已删除";
						} else if(3 == meeting.getMeetingStatus().intValue()) {
							message = "会议已结束";
						} else {
							String[] memberIdArr = memberId.split(",");
							for(String memberIdStr : memberIdArr) {
								List<MeetingMember> listMeetingMember = meetingMemberService.getByMeetingIdAndMemberId(Long.parseLong(meetingId), Long.parseLong(memberIdStr));
								if(!Utils.isNullOrEmpty(listMeetingMember)) {
									UserBean userBean = new UserBean();
									userBean.setId(user.getId());
									userBean.setName(user.getName());
									MeetingMember meetingMember = listMeetingMember.get(0);
									String content = meeting.getCreateName() + "取消了";
									if(2 == meetingMember.getMemberType().intValue()) {//创建者
										message = "不能删除会议创建者";
									} else if(1 == meetingMember.getMemberType().intValue()) {//普通参会人
										meetingMemberService.delete(meetingMember.getId());
										MeetingNotice meetingNotice = new MeetingNotice();
										meetingNotice.setNoticeContent(content+"您参加的"+meeting.getMeetingName()+"会议");
										meetingNotice.setReceiverType(NoticeReceiverType.PARTICIPANTS.code());
										meetingNotice.setCreateId(user.getId());
										meetingNotice.setCreateName(user.getName());
										meetingNotice.setCreateTime(new Date());
										meetingNotice.setUpdateTime(new Date());
										meetingNotice.setMeetingId(meeting.getId());
										meetingNotice.setIsShow(1);
										meetingNotice.setReceiver(meetingMember.getMemberId());
										if(!Utils.isNullOrEmpty(meetingMember.getMemberId())){
											meetingNotice.setReceiverName(meetingMember.getMemberName());
										}
										meetingNotice.setNoticeType(NoticeType.CANCEL_ATTEND_MEETING.code());
										meetingNoticeService.saveOrUpdate(meetingNotice);
										//推送通知
										String dateStr = DateUtil.convertDateToStringForChina(new Date());
										GinTongInterface.pushToAttendMeetingMember(userBean, String.valueOf(meeting.getId()), meeting.getCreateName()+"于"+dateStr+"取消了您参加的"+meeting.getMeetingName()+"会议",false,dateStr);
									} else if(0 == meetingMember.getMemberType().intValue()) {//主讲人
										//删除议题
										List<MeetingTopic> listMeetingTopic = meetingTopicService.getByMeetingId(Long.parseLong(meetingId));
										if(!Utils.isNullOrEmpty(listMeetingTopic)) {
											for(MeetingTopic meetingTopic : listMeetingTopic) {
												if(!Utils.isNullOrEmpty(meetingTopic)) {
													//删除聊天
													List<TopicChat> listTopicChat = topicChatService.getByTopicId(meetingTopic.getId());
													if(!Utils.isNullOrEmpty(listTopicChat)) {
														for(TopicChat topicChat : listTopicChat) {
															topicChatService.delete(topicChat.getId());
														}
													}
													if(memberIdStr.equals(meetingTopic.getMemberId().toString())) {
														meetingTopicService.delete(meetingTopic.getId());
													}
												}
											}
										}
										//删除主讲人
										meetingMemberService.delete(meetingMember.getId());
										//增加取消参会通知
										MeetingNotice meetingNotice = new MeetingNotice();
										meetingNotice.setNoticeContent(content+"您主讲的"+meeting.getMeetingName()+"会议");
										meetingNotice.setReceiverType(NoticeReceiverType.PARTICIPANTS.code());
										meetingNotice.setCreateId(user.getId());
										meetingNotice.setCreateName(user.getName());
										meetingNotice.setCreateTime(new Date());
										meetingNotice.setUpdateTime(new Date());
										meetingNotice.setMeetingId(meeting.getId());
										meetingNotice.setIsShow(1);
										meetingNotice.setReceiver(meetingMember.getMemberId());
										if(!Utils.isNullOrEmpty(meetingMember.getMemberId())){
											meetingNotice.setReceiverName(meetingMember.getMemberName());
										}
										meetingNotice.setNoticeType(NoticeType.CANCEL_ATTEND_MEETING.code());
										meetingNoticeService.saveOrUpdate(meetingNotice);
										//推送通知
										String dateStr = DateUtil.convertDateToStringForChina(new Date());
										GinTongInterface.pushToAttendMeetingMember(userBean, String.valueOf(meeting.getId()), meeting.getCreateName()+"于"+dateStr+"取消了您主讲的"+meeting.getMeetingName()+"会议",false,dateStr);
									}
									//删除全部主讲人后，会议变更为无主讲，增加默认议题
									List<MeetingTopic> listMeetingTopic = meetingTopicService.getByMeetingId(Long.parseLong(meetingId));
									if(Utils.isNullOrEmpty(listMeetingTopic)) {
										//会议变更为无主讲
										meeting.setMeetingType(0);
										meetingService.saveOrUpdate(meeting);
										//增加默认议题
										MeetingTopic meetingTopic=new MeetingTopic();
										meetingTopic.setMeetingId(meeting.getId());
										meetingTopic.setCreateId(meeting.getCreateId());
										meetingTopic.setCreateName(meeting.getCreateName());
										meetingTopic.setCreateTime(new Date());
										meetingTopic.setUpdateTime(new Date());
										meetingTopic.setTopicContent("无主讲会议默认议题");
										meetingTopicService.saveOrUpdate(meetingTopic);
									}
								}
							}
							flag = true;
						}
					} else {
						message = "非法参数";
					}
				} catch (Exception e) {
					message = e.getMessage();
					logger.error("", e);
				}
			}
		} else {
			message = "用户信息为空";
		}
		responseDataMap.put("succeed", flag);
		if(flag) {
			notificationMap.put("notifCode", "0001");
		} else {
			notificationMap.put("notifCode", "0002");
		}
		notificationMap.put("notifInfo", message);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	@Override
	public Logger getLogger() {
		return logger;
	}
}
