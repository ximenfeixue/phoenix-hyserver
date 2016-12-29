package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.eclipse.jetty.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ginkgocap.ywxt.cache.Cache;
import com.ginkgocap.ywxt.common.base.BaseController;
import com.ginkgocap.ywxt.file.model.FileIndex;
import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.model.meeting.ChatMini;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.MeetingOrgan;
import com.ginkgocap.ywxt.model.meeting.MeetingPeople;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.model.meeting.MeetingSignLabel;
import com.ginkgocap.ywxt.model.meeting.MeetingTime;
import com.ginkgocap.ywxt.model.meeting.MeetingTopic;
import com.ginkgocap.ywxt.model.meeting.MeetingVo;
import com.ginkgocap.ywxt.model.meeting.SocialListReq;
import com.ginkgocap.ywxt.model.meeting.SocialStatus;
import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.service.meeting.ImRecordmessageService;
import com.ginkgocap.ywxt.service.meeting.MeetingCountService;
import com.ginkgocap.ywxt.service.meeting.MeetingMemberService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoteService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoticeService;
import com.ginkgocap.ywxt.service.meeting.MeetingPicService;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.service.meeting.MeetingTopicService;
import com.ginkgocap.ywxt.service.meeting.SocialStatusService;
import com.ginkgocap.ywxt.service.meeting.TopicChatService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.util.HttpClientHelper;
import com.ginkgocap.ywxt.utils.GinTongInterface;
import com.ginkgocap.ywxt.utils.MeetingDict;
import com.ginkgocap.ywxt.utils.ResultBean;
import com.ginkgocap.ywxt.utils.ThreadPoolUtils;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.utils.type.SocialType;
import com.ginkgocap.ywxt.vo.query.community.Community;
import com.ginkgocap.ywxt.vo.query.meeting.BigDataQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoteQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.ginkgocap.ywxt.vo.query.meeting.Notification;
import com.ginkgocap.ywxt.vo.query.meeting.ResponseObject;
import com.ginkgocap.ywxt.vo.query.meeting.UserBean;
import com.ginkgocap.ywxt.vo.query.social.CommunityNewCount;
import com.ginkgocap.ywxt.vo.query.social.Social;
import com.ginkgocap.ywxt.vo.query.social.SocialDetail;
import com.gintong.easemob.server.comm.GsonUtils;
import com.gintong.rocketmq.api.DefaultMessageService;
import com.gintong.rocketmq.api.enums.TopicType;
import com.gintong.rocketmq.api.model.RocketSendResult;
import com.gintong.rocketmq.api.utils.FlagTypeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import com.ginkgocap.ywxt.model.meeting.FileIndex;

/**
 * @Description: Controller
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */

@Controller
@RequestMapping("/meeting")
public class MeetingController extends BaseController {
	private static ResourceBundle resource = ResourceBundle.getBundle("gintongService");
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private MeetingMemberService meetingMemberService;
	@Autowired
	private TopicChatService topicChatService;
	@Autowired
	private MeetingTopicService meetingTopicService;
	@Autowired
	private MeetingPicService meetingPicService;
	@Autowired
	private FileIndexService fileIndexService;
	@Autowired
	private ImRecordmessageService imRecordmessageService;
	@Autowired
	private UserService userService;
	@Autowired
	private MeetingNoticeService meetingNoticeService;
	@Autowired
	private MeetingDict meetingDict;
	@Autowired
	private MeetingNoteService meetingNoteService;
	@Autowired
	private MeetingCountService meetingCountService;
	@Autowired
	private SocialStatusService socialStatusService;
	@Autowired
	private DefaultMessageService defaultMessageService;
	@Autowired
	private Cache cache;
	private static int  expiredTime = 60 * 60 * 24 * 7;

	private final Logger logger = LoggerFactory.getLogger(MeetingController.class);
	private static final String CLASS_NAME = MeetingController.class.getName();

	/*
	 * 在社交列表中移除单聊、群聊、会议
	 */
	@RequestMapping(value = "/removeSocial.json", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, Object> removeSocial(HttpServletRequest request, HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("parse request parameters error", e);
			return rebuildResponseResult(false, "0002", "请求参数错误");
		}
		SocialStatus message = com.ginkgocap.ywxt.utils.GsonUtils.StringToObject(SocialStatus.class, requestJson);
		if (null == message) {
			return rebuildResponseResult(false, "0002", "请求参数错误");
		} else {
			List<SocialStatus> list = socialStatusService.queryList(message);
			if (list == null || list.size() == 0) {
				try {
					socialStatusService.save(message);
				} catch (Exception e) {
					return rebuildResponseResult(false, "0002", e.getMessage());
				}
			}
		}
		return rebuildResponseResult(true, "0001", null);
	}

	/**
	 * @param isuccessed
	 * @param code
	 *            0001表示成功 0002表示失败
	 * @param errorMessage
	 */
	static Map<String, Object> rebuildResponseResult(boolean isuccessed, String code, String errorMessage) {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		responseDataMap.put("succeed", isuccessed);
		notificationMap.put("notifCode", code);
		notificationMap.put("notifInfo", errorMessage);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 名称: addMettingGet 描述: 新增会议
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/add.json", method = RequestMethod.GET)
	public Map<String, Object> addMettingGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = addMetting(request, response);
		return model;
	}

	/**
	 * 名称: addMetting 描述: 新增会议
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public Map<String, Object> addMetting(HttpServletRequest request, HttpServletResponse response) {
		// 获取json参数串
		String debugStr="";
		String requestJson = "";
		try {
			debugStr+="1";
			requestJson = getJsonParamStr(request);

			debugStr+="12";
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		User user = getUser(request);
		debugStr+="13";
		if (!isNullOrEmpty(user) && !isNullOrEmpty(user.getId())) {

			if (!isNullOrEmpty(requestJson)) {
				try {
					Map<String, Class> classMap = new HashMap<String, Class>();
					classMap.put("listMeetingMember", MeetingMember.class);
					classMap.put("listMeetingPic", MeetingPic.class);
					classMap.put("listMeetingTime", MeetingTime.class);
					classMap.put("listMeetingTopic", MeetingTopic.class);
					classMap.put("listMeetingData", MeetingData.class);
					classMap.put("listMeetingRequirement", MeetingData.class);
					classMap.put("listMeetingKnowledge", MeetingData.class);
					classMap.put("listMeetingPeople", MeetingPeople.class);
					classMap.put("listMeetingOrgan", MeetingOrgan.class);
					classMap.put("listMeetingSignLabel", MeetingSignLabel.class);
					// json 转为对象
					JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}));
					/*
					 * MeetingQuery meetingObj = (MeetingQuery)
					 * JSONObject.toBean(j, MeetingQuery.class, classMap);
					 */
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					MeetingQuery meetingObj = (MeetingQuery) gson.fromJson(requestJson, MeetingQuery.class);
					String nfsHome = (String) request.getSession().getServletContext().getAttribute("nfsHome");
					String path = request.getContextPath();
					String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
					String url = basePath + "/file/get/image/?url=" + nfsHome;

					Long meetingid = meetingService.saveMeetingInterfix(meetingObj, user, url);
					// 设置会议ID(钱明金：2016年1月18日)
					meetingObj.setId(meetingid);
					if (!isNullOrEmpty(meetingid)) {
						pushIndexByMQ(FlagTypeUtils.createMeetingFlag(), getMeetingQueryToIndexJsonString(meetingid));
					}
					responseDataMap.put("succeed", true);
					responseDataMap.put("meetingid", meetingid);
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello app");
				} catch (Exception e) {
					responseDataMap.put("succeed", false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", e.getMessage());
					e.printStackTrace();
				}
			} else {
				responseDataMap.put("succeed", false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数错误");
			}
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		model.put("debugStr:", debugStr);
		
		return model;
	}

	/**
	 * 删除会议（我创建的、我保存的）
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.GET)
	public Map<String, Object> deleteGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return this.delete(request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		boolean succeed = false;
		String notifCode = "0002";
		String notifInfo = "删除失败";
		Map<String, Object> result = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			JSONObject j = JSONObject.fromObject(requestJson);
			// 获取会议id
			String memberId = getStringJsonValueByKey(j, "memberId");
			String meetingId = getStringJsonValueByKey(j, "meetingId");
			if (!Utils.isNullOrEmpty(memberId) && !Utils.isNullOrEmpty(meetingId)) {
				String[] meetingIdArr = meetingId.split(",");
				List<Long> meetingIdList = new ArrayList<Long>();
				for (String id : meetingIdArr) {
					meetingIdList.add(Long.parseLong(id));
				}
				try {
					meetingService.deleteMyMeetingBatch(meetingIdList, Long.parseLong(memberId));
					succeed = true;
					notifCode = "0001";
					notifInfo = "删除成功";
				} catch (Exception e) {
					notifInfo = e.getMessage();
					logger.error("删除失败", e);
				}
			}
		} else {
			notifInfo = "参数错误";
		}
		responseDataMap.put("succeed", succeed);
		notificationMap.put("notifCode", notifCode);
		notificationMap.put("notifInfo", notifInfo);
		result.put("responseData", responseDataMap);
		result.put("notification", notificationMap);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/upate.json", method = RequestMethod.GET)
	public Map<String, Object> updateMeetingGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = updateMeeting(request, response);
		return model;
	}

	/**
	 * 名称: upateMetting 描述: 修改会议
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/upate.json", method = RequestMethod.POST)
	public Map<String, Object> updateMeeting(HttpServletRequest request, HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		User user = getUser(request);
		if (!isNullOrEmpty(user) && !isNullOrEmpty(user.getId())) {
			if (!isNullOrEmpty(requestJson)) {
				try {
					Map<String, Class> classMap = new HashMap<String, Class>();
					classMap.put("listMeetingMember", MeetingMember.class);
					classMap.put("listMeetingPic", MeetingPic.class);
					classMap.put("listMeetingTime", MeetingTime.class);
					classMap.put("listMeetingTopic", MeetingTopic.class);
					classMap.put("listMeetingData", MeetingData.class);
					classMap.put("listMeetingRequirement", MeetingData.class);
					classMap.put("listMeetingKnowledge", MeetingData.class);
					classMap.put("listMeetingPeople", MeetingPeople.class);
					classMap.put("listMeetingOrgan", MeetingOrgan.class);
					classMap.put("listMeetingSignLabel", MeetingSignLabel.class);
					// json 转为对象
					/*
					 * MeetingQuery meetingObj = (MeetingQuery)
					 * JSONObject.toBean(j, MeetingQuery.class, classMap);
					 */
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					MeetingQuery meetingObj = (MeetingQuery) gson.fromJson(requestJson, MeetingQuery.class);
					if (isNullOrEmpty(meetingObj) || isNullOrEmpty(meetingObj.getId())) {
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "参数错误");
					} else {
						String nfsHome = (String) request.getSession().getServletContext().getAttribute("nfsHome");
						String path = request.getContextPath();
						String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
						String url = basePath + "/file/get/image/?url=" + nfsHome;
						meetingService.updateMeetingInterfix(meetingObj, user, url);

						/**
						 * 集成环信：更新会议
						 */
						final MeetingQuery meetingParams = meetingObj;
						final User creatorParams = user;
						ThreadPoolUtils.getExecutorService().execute(new Runnable() {
							@Override
							public void run() {
								modifyMeeting(meetingParams, creatorParams);
							}
						});

						if (!isNullOrEmpty(meetingObj) && !isNullOrEmpty(meetingObj.getId())) {

							pushIndexByMQ(FlagTypeUtils.updateMeetingFlag(), getMeetingQueryToIndexJsonString(meetingObj.getId()));

						}
						responseDataMap.put("succeed", true);
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hollo app");
					}
				} catch (Exception e) {
					responseDataMap.put("succeed", false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", e.getMessage());
					e.printStackTrace();
				}
			} else {
				responseDataMap.put("succeed", false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数错误");
			}
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	private void modifyMeeting(MeetingQuery meetingObj, User creator) {
		Map<String, Object> params = new HashMap<String, Object>();
		String name = meetingObj.getMeetingName();
		if (StringUtils.isEmpty(name)) {
			name = "";
		}
		String desc = meetingObj.getMeetingDesc();
		if (StringUtils.isEmpty(desc)) {
			desc = "";
		}
		Integer maxusersSize = meetingObj.getAttendMeetingCount();
		if (maxusersSize == null || maxusersSize <= 0) {
			maxusersSize = 0;
		}
		GinTongInterface.updateMuc(creator.getId(), meetingObj.getId(), name, desc, maxusersSize);
	}

	/**
	 * 名称: deleteMettingGet 描述: 删除会议
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteByMeetingIdAndCreateId.json", method = RequestMethod.GET)
	public Map<String, Object> deleteMettingGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = deleteMetting(request, response);
		return model;
	}

	/**
	 * 名称: deleteMetting 描述: 删除会议
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteByMeetingIdAndCreateId.json", method = RequestMethod.POST)
	public Map<String, Object> deleteMetting(HttpServletRequest request, HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 获取会议id
				String meetingIdStr = j.getString("meetingId");
				// 获取创建者Id
				String memberIdStr = j.getString("memberId");

				if (!isNullOrEmpty(meetingIdStr) && !isNullOrEmpty(memberIdStr)) {

					meetingService.delete(Long.valueOf(meetingIdStr), Long.valueOf(memberIdStr));
					if (!isNullOrEmpty(meetingIdStr)) {

						Map<String, String> datas = new HashMap<String, String>();
						datas.put("id", meetingIdStr);

						pushIndexByMQ(FlagTypeUtils.deleteMeetingFlag(), JSONObject.fromObject(datas).toString());

					}
					responseDataMap.put("succeed", true);

					/**
					 * 集成环信：删除会议
					 */
					// (keifer)
					final String chatgroupid = j.getString("groupId");
					final long creatorId = Long.valueOf(memberIdStr);
					if (StringUtils.isNotEmpty(chatgroupid)) {
						ThreadPoolUtils.getExecutorService().execute(new Runnable() {
							@Override
							public void run() {
								// 2016-03-14 tanmin 同步删除会议对应的畅聊,创建者将自己退出会议即为解散
								GinTongInterface.exitFromMUC(creatorId, creatorId, chatgroupid);
								// EasemobChatGroupsHandler.deleteChatGroups(chatgroupid);
							}
						});
					} else {
						logger.error("执行 " + CLASS_NAME + " 类中的 deleteMetting 方法时出错：环信的群组 Id ( groupId ) 为空，删除环信服务器上的该群组失败");
					}

				} else {
					logger.error("会议id或者成员id为空");
					responseDataMap.put("succeed", false);
				}
			} catch (Exception e) {
				responseDataMap.put("succeed", false);
				e.printStackTrace();
			}
		} else {
			responseDataMap.put("succeed", false);
		}
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello mobile app!");
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 名称: getMettingByIdAndMemberId 描述: 根据会议编号和成员编号获取邀请函详情
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getByIdAndMemberId.json", method = RequestMethod.GET)
	public Map<String, Object> getMettingByIdAndMemberIdGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = getMettingByIdAndMemberId(request, response);
		return model;
	}

	/**
	 * 名称: getMettingByIdAndMemberId 描述: 根据会议编号和成员编号获取邀请函详情
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */

	@ResponseBody
	@RequestMapping(value = "/getByIdAndMemberId.json", method = RequestMethod.POST)
	public Map<String, Object> getMettingByIdAndMemberId(HttpServletRequest request, HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		MeetingQuery meetingObj = null;
		if (!isNullOrEmpty(requestJson)) {
			// User user = getUser(request);
			// if(!Utils.isNullOrEmpty(user) &&
			// !Utils.isNullOrEmpty(user.getId())) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议Id
				String idStr = getStringJsonValueByKey(j, "id");
				// 会议成员id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				if (Utils.isNullOrEmpty(memberIdStr)) {
					memberIdStr = "0";// 外部访问，默认为金桐脑
				}
				if (!Utils.isNullOrEmpty(idStr)) {
					Long id = Long.valueOf(idStr);
					Long memberId = Long.valueOf(memberIdStr);
					meetingObj = meetingService.getMeetingByIdAndMemberId(id, memberId);
					if (!Utils.isNullOrEmpty(meetingObj)) {
						// 封装会议笔记
						List<MeetingNoteQuery> listMeetingNoteQuery = meetingNoteService.getNoteAndDetailtByMeetingIdAndCreater(id, memberId);
						meetingObj.setListMeetingNoteQuery(listMeetingNoteQuery);
						MeetingQuery meetingTemp = meetingTopicService.getMeetingTopicList(id);
						if (!isNullOrEmpty(meetingTemp)) {
							// 封装议题及聊天数据
							if (!isNullOrEmpty(meetingTemp.getListMeetingTopicQuery()))
								meetingObj.setListMeetingTopicQuery(meetingTemp.getListMeetingTopicQuery());
							// 封装参会人数
							if (!isNullOrEmpty(meetingTemp.getAttendMeetingCount())) {
								meetingObj.setAttendMeetingCount(meetingTemp.getAttendMeetingCount());
							}
							// 封装签到人数
							if (!isNullOrEmpty(meetingTemp.getSignInCount())) {
								meetingObj.setSignInCount(meetingTemp.getSignInCount());
							}
						}
						// 增加已读会议数量
						meetingCountService.addReadCount(id);
					}
				}
			} catch (Exception e) {
				logger.error("查询会议详情失败", e);
			}
		}
		responseDataMap.put("meeting", meetingObj);
		if (Utils.isNullOrEmpty(meetingObj)) {
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "查询会议详情失败");
		} else {
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello mobile app!");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 名称: endMeeting 描述: 结束会议
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/changeMeetingStatus.json", method = RequestMethod.GET)
	public Map<String, Object> changeMeetingStatusGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = changeMeetingStatus(request, response);
		return model;
	}

	/**
	 * 名称: endMeeting 描述: 结束会议
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */

	@ResponseBody
	@RequestMapping(value = "/changeMeetingStatus.json", method = RequestMethod.POST)
	public Map<String, Object> changeMeetingStatus(HttpServletRequest request, HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		User user = getUser(request);
		boolean flag = false;
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议Id
				String meetingStatusStr = getStringJsonValueByKey(j, "meetingStatus");
				// 会议成员id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				// 会议成员id
				String memberId = getStringJsonValueByKey(j, "meetingId");
				if (Utils.isAllNotNullOrEmpty(meetingStatusStr, meetingIdStr, memberId)) {
					flag = meetingService.changeMeetingStatus(Long.valueOf(meetingIdStr), Integer.valueOf(meetingStatusStr), user.getId());
					if (!isNullOrEmpty(meetingIdStr)) {

						pushIndexByMQ(FlagTypeUtils.updateMeetingFlag(), getMeetingQueryToIndexJsonString(Long.getLong(meetingIdStr)));

						GinTongInterface.addMeetingIndex(Long.valueOf(meetingIdStr), "upd");
					}
				} else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
				}
			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
		}
		responseDataMap.put("succeed", flag);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 获取上传文件
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getFileListByTaskId.json", method = RequestMethod.GET)
	public Map<String, Object> getFileListByTaskIdGet(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		return this.getFileListByTaskId(request, response, model);
	}

	/**
	 * 获取上传文件
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getFileListByTaskId.json", method = RequestMethod.POST)
	public Map<String, Object> getFileListByTaskId(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		List<FileIndex> list = new ArrayList<FileIndex>();
		// 获取json参数串
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String taskId = getStringJsonValueByKey(j, "taskId");
				if (!isNullOrEmpty(taskId)) {
					// list = fileIndexService.getByTaskId(taskId);
					fileIndexService.selectByTaskId(taskId, "1");
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app");
				} else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数不全");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		responseDataMap.put("listFileIndex", list);
		result.put("responseData", responseDataMap);
		result.put("notification", notificationMap);
		return result;
	}

	/**
	 * deleteNotBeginMeetingGet 删除未开始的会议
	 *
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNotBeginMeeting.json", method = RequestMethod.GET)
	public Map<String, Object> deleteNotBeginMeetingGet(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		return this.deleteNotBeginMeeting(request, response, model);
	}

	/**
	 * deleteNotBeginMeeting 删除未开始的会议
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNotBeginMeeting.json", method = RequestMethod.POST)
	public Map<String, Object> deleteNotBeginMeeting(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		boolean flag = false;
		// 获取json参数串
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				String memberIdStr = getStringJsonValueByKey(j, "memberId");

				if (Utils.isAllNotNullOrEmpty(meetingIdStr, memberIdStr)) {
					meetingService.cancelNotBenginMeeting(Long.valueOf(meetingIdStr), Long.valueOf(memberIdStr));
					if (!isNullOrEmpty(meetingIdStr)) {

						Map<String, String> datas = new HashMap<String, String>();
						datas.put("id", meetingIdStr);
						pushIndexByMQ(FlagTypeUtils.deleteMeetingFlag(), JSONObject.fromObject(datas).toString());

					}
					flag = true;
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "");

					/**
					 * 集成环信：删除会议
					 */
					final String chatgroupid = getStringJsonValueByKey(j, "groupId");
					final long creatorId = Long.valueOf(memberIdStr);
					if (StringUtils.isNotEmpty(chatgroupid)) {
						ThreadPoolUtils.getExecutorService().execute(new Runnable() {
							@Override
							public void run() {
								// 2016-03-14 tanmin 同步删除会议对应的畅聊,创建者将自己退出会议即为解散
								GinTongInterface.exitFromMUC(creatorId, creatorId, chatgroupid);
								// EasemobChatGroupsHandler.deleteChatGroups(chatgroupid);
							}
						});
					} else {
						logger.error("执行 " + CLASS_NAME + " 类中的 deleteNotBeginMeeting 方法时出错：环信的群组 Id ( groupId ) 为空，删除环信服务器上的该群组失败");
					}

				} else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
				}
			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		}
		responseDataMap.put("succeed", flag);
		result.put("responseData", responseDataMap);
		result.put("notification", notificationMap);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/test.json", method = RequestMethod.POST)
	public Map<String, Object> test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		// User user=getUser(request);
		// UserBean userBean=getUserBean(request);
		/*
		 * UserBean userBean=new UserBean(); List<Integer> userIds=new
		 * ArrayList<Integer>(); userIds.add(14386); List<ConnectionsMini>
		 * list=GinTongInterface.getListConnectionsMini(userIds);
		 * userBean.setId(user.getId()); userBean.setName(user.getName()); //
		 * String succed=GinTongInterface.updateMettingPush(userBean, "18",
		 * "张三修改了会议"); // System.out.println("succed="+succed);
		 * System.out.println("list="+list);
		 */
		/*
		 * UserBean userBean=new UserBean(); List<Integer> userIds=new
		 * ArrayList<Integer>(); userIds.add(14386); List<ConnectionsMini>
		 * list=GinTongInterface.getListConnectionsMini(userIds);
		 * userBean.setId(user.getId()); userBean.setName(user.getName()); //
		 * String succed=GinTongInterface.updateMettingPush(userBean, "18",
		 * "张三修改了会议"); // System.out.println("succed="+succed);
		 * System.out.println("list="+list);
		 */
		/*
		 * List<Integer> userIds=new ArrayList<Integer>(); userIds.add(14386);
		 * List<ConnectionsMini>
		 * list=GinTongInterface.getListConnectionsMini(userIds); // String
		 * succed=GinTongInterface.updateMettingPush(userBean, "18", "张三修改了会议");
		 * // System.out.println("succed="+succed);
		 * System.out.println("list="+list);
		 */

		/**** 人脉详情 *****/
		/*
		 * JTContact jTContact=GinTongInterface.getJTContactDetail(
		 * true,userBean);
		 */
		// jTContact.get
		ResponseObject responseObject = new ResponseObject();
		try {
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello mobile app!");
		} catch (Exception e) {
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
			e.printStackTrace();
		}

		responseDataMap.put("status", responseObject.getStatus());
		responseDataMap.put("errormessage", responseObject.getErrormessage());
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello mobile app!");
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/test1.json", method = RequestMethod.POST)
	public Map<String, Object> test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!isNullOrEmpty(requestJson)) {
			JSONObject j = JSONObject.fromObject(requestJson);
			// json 转为对象
			MeetingTime meetingTime;
			try {
				meetingTime = (MeetingTime) JSONObject.toBean(j, MeetingTime.class);
				responseDataMap.put("meetingTime", meetingTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello mobile app!");
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 引导页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/params/", method = RequestMethod.GET)
	public ModelAndView leadTo() {

		Map<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("/params", map);
	}

	/**
	 * 结果显示页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/result/", method = RequestMethod.GET)
	public ModelAndView resultTo() {

		Map<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("/result", map);
	}

	@ResponseBody
	@RequestMapping(value = "/seach.json", method = RequestMethod.GET)
	public Map<String, Object> meetingSeachGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = this.meetingSeachPost(request, response);
		return model;
	}

	/**
	 * 会议关键字搜索
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author wangfeiliang
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/seach.json", method = RequestMethod.POST)
	public Map<String, Object> meetingSeachPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		Map<String, Object> page = new HashMap<String, Object>();

		try {
			UserBean userBean = getUserBean(request);
			Map<String, Object> param = new HashMap<String, Object>();
			Map<String, Object> value = null;
			if (!isNullOrEmpty(requestJson)) {
				value = JSONObject.fromObject(requestJson);
				for (String key : value.keySet()) {
					if ("index".equals(key)) {
						param.put("pno", value.get("index"));
					} else if ("size".equals(key)) {
						param.put("psize", value.get("size"));
					} else {
						param.put(key, value.get(key));
					}
				}
			}
			ResponseObject responseObject = GinTongInterface.meetingSeach(param, userBean);
			int total = 0;
			List<Map<String, String>> listMeeting = null;
			if (!isNullOrEmpty(responseObject)) {
				if (!isNullOrEmpty(responseObject.getTotal())) {
					total = responseObject.getTotal();
				}
				// if (!isNullOrEmpty(responseObject.getPno())) {
				// page.put("index", responseObject.getPno());
				// }
				// if (!isNullOrEmpty(responseObject.getPsize())) {
				// page.put("size", responseObject.getPsize());
				// }
				if (!isNullOrEmpty(responseObject.getList())) {
					listMeeting = responseObject.getList();
				}
			}
			if (listMeeting == null) {
				listMeeting = new ArrayList<Map<String, String>>();
			}
			if (value.get("index") != null) {
				page.put("index", value.get("index"));
			}
			if (value.get("size") != null) {
				page.put("size", value.get("size"));
			}
			page.put("total", total);
			page.put("listMeeting", listMeeting);
			responseDataMap.put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			responseDataMap.put("page", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		System.out.println(JSONObject.fromObject(model).toString());
		return model;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	/**
	 * 新框架会议首页 1.会议 2.通知 3.邀请函 4.私聊 5.群聊
	 */

	@RequestMapping(value = "/index.json", method = RequestMethod.POST)
	public ModelAndView meetingIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		JSONObject json = convertJson(request);

		long user_id = json.optLong("user_id", 0L);
		if (user_id == 0L) {
			out.println(ResultBean.EMPTY_INFO);
			return null;
		}

		List<MeetingVo> meeting = meetingService.getMeetingList(user_id); // 所有会议
		MeetingVo notice = meetingNoticeService.selectnoticeindex(user_id); // 通知
		List<MeetingVo> invite = meetingService.getInviteList(user_id); // 邀请函
		List<MeetingVo> chat = imRecordmessageService.getChatMobileIndex(user_id); // 消息

		List<MeetingVo> doing_meeting = new ArrayList<MeetingVo>(); // 正在进行的
		System.out.println("meeting_all_1:" + JSONArray.fromObject(meeting));

		if (meeting != null && meeting.size() > 0) {
			Iterator<MeetingVo> it = meeting.iterator();

			while (it.hasNext()) {
				MeetingVo mi = it.next();
				if (mi == null) {
					it.remove();
					continue;
				}

				if (mi.getMeeting().getStatus() == 2) { // 正在进行的
					doing_meeting.add(mi);
					it.remove();
				}
			} // 此时meeting中 包含 未开始的 和 已经结束

			// 正在进行的会议按照 排序时间 asc
			Collections.sort(doing_meeting, new Comparator<MeetingVo>() {
				public int compare(MeetingVo o1, MeetingVo o2) {
					if (o1 == null || o2 == null)
						return 0;
					if (o1.getSort() == null || o2.getSort() == null)
						return 0;
					if (o1.getSort().before(o2.getSort()))
						return -1;
					if (o1.getSort().after(o2.getSort()))
						return 1;
					return 0;
				}
			});

		}

		int len = meeting != null ? meeting.size() + (notice != null ? 1 : 0) + (invite != null ? invite.size() : 0)
				+ (chat != null ? chat.size() : 0) : 0;
		List<MeetingVo> shuffle = new ArrayList<MeetingVo>(len);
		if (meeting != null && meeting.size() > 0)
			shuffle.addAll(meeting);
		if (notice != null)
			shuffle.add(notice);
		if (invite != null && invite.size() > 0)
			shuffle.addAll(invite);
		if (chat != null && chat.size() > 0)
			shuffle.addAll(chat);

		// desc
		Collections.sort(shuffle, new Comparator<MeetingVo>() {
			public int compare(MeetingVo o1, MeetingVo o2) {
				if (o1 == null || o2 == null)
					return 0;
				if (o1.getSort() == null || o2.getSort() == null)
					return 0;
				if (o1.getSort().before(o2.getSort()))
					return 1;
				if (o1.getSort().after(o2.getSort()))
					return -1;
				return 0;
			}
		});

		if (shuffle != null && shuffle.size() > 0) {
			System.out.println("len:" + len + ":shuffer:" + shuffle.size() + "doing:" + doing_meeting.size());
			doing_meeting.addAll(shuffle);
		}

		Gson gson = new Gson();
		String sh = gson.toJson(doing_meeting);
		sh = sh.replaceAll("null", "");
		JSONObject result = new JSONObject();
		result.put("meetingVos", sh);
		String ss = ResultBean.return_info(result);

		System.out.println(ss);

		out.println(ss);
		return null;
	}

	@RequestMapping(value = "/groupchat.json", method = RequestMethod.POST)
	public ModelAndView groupIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		JSONObject json = convertJson(request);
		long user_id = json.optLong("user_id", 0L);
		if (user_id == 0L)
			return null;
		List<ChatMini> list = imRecordmessageService.getChatMessage(user_id);
		if (list == null || list.size() == 0) {
			out.println(ResultBean.EMPTY_INFO);
			return null;
		}

		Gson gson = new Gson();
		String result = gson.toJson(list).replaceAll("null", "");

		out.println(ResultBean.return_info(result));
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/meetingList.json", method = RequestMethod.POST)
	public Map<String, Object> meetingList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		// Map<String, Object> page = new HashMap<String, Object>();
		Date inDate = new Date();
		System.out.println("进入社交列表接口时间：" + Utils.DateFormat(inDate));
		try {
			User user = getUser(request);
			// 获取当前登录用户
			if (null == user || user.getId() < 1) {
				responseDataMap.put("listSocial", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "请先登录");
				return model;
			}
			List<Social> listResult = getMeetingList(user.getId());
			responseDataMap.put("listSocial", listResult);
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello App");
		} catch (Exception e) {
			e.printStackTrace();
			responseDataMap.put("listSocial", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
		}
		return model;
	}

	private List<Social> getMeetingList(Long userId) {
		List<Social> listResult = new ArrayList<Social>();
		// 获取用户所有会议（报名成功或接受邀请非草稿状态且成员未删除
		List<Social> listMeeting = meetingService.getMeetingListByUserId(userId);
		if (null != listMeeting && listMeeting.size() > 0) {
			// 先获取人名、最后一次聊天时间
			Map<Long, String> names = new HashMap<Long, String>();
			// Map<Long, Date> dates = new HashMap<Long, Date>();
			List<Long> list = new ArrayList<Long>();
			List<Long> list2 = new ArrayList<Long>();
			List<Long> listMeetingId = new ArrayList<Long>();
			for (Social social : listMeeting) {
				if (null == social) {
					continue;
				}
				Long compereId = social.getCompereId();
				if (null != compereId && list.contains(compereId) == false) {
					list.add(compereId);
				}
				if (null != social.getId() && SocialType.INVITATION.code() != social.getType() && list2.contains(social.getId()) == false) {
					list2.add(social.getId());
				}
				if (3 == social.getType().intValue() || 4 == social.getType().intValue() || 5 == social.getType().intValue()) {
					listMeetingId.add(social.getId());
				}
			}
			// 组装会议议题
			if (!Utils.isNullOrEmpty(listMeetingId)) {
				List<MeetingTopic> listMeetingTopic = meetingTopicService.getByMeetingIds(listMeetingId);
				Map<String, List<MeetingTopic>> mapMeetingTopic = new HashMap<String, List<MeetingTopic>>();
				if (!Utils.isNullOrEmpty(listMeetingTopic)) {
					for (MeetingTopic meetingTopic : listMeetingTopic) {
						if (meetingTopic.getIsFinished() != 1) {// 社交列表不能转发到已结束的议题
							List<MeetingTopic> tempListMeetingTopic = mapMeetingTopic.get("" + meetingTopic.getMeetingId());
							if (Utils.isNullOrEmpty(tempListMeetingTopic)) {
								tempListMeetingTopic = new ArrayList<MeetingTopic>();
							}
							tempListMeetingTopic.add(meetingTopic);
							mapMeetingTopic.put("" + meetingTopic.getMeetingId(), tempListMeetingTopic);
						}
					}
				}
				for (Social social : listMeeting) {
					if (3 == social.getType().intValue() || 4 == social.getType().intValue() || 5 == social.getType().intValue()) {
						social.setListMeetingTopic(mapMeetingTopic.get("" + social.getId()));
					}
				}
			}
			if (list.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ids", list);
				List<User> users = userService.selectSimpleUsers(map);
				if (null == users)
					users = new ArrayList<User>();
				for (User u : users) {
					names.put(u.getId(), u.getName());
				}
			}

			Map<String, Social> sessionMap = new HashMap<String, Social>();
			if (list2.size() > 0) {
				// todo : 在这里取session, 然后重新对social的未读消息数跟最后消息时间赋值
				List<Social> socialSession = GinTongInterface.getListMeetingRecord(userId);
				for (Social s : socialSession) {
					if (s.getId() != null) {
						sessionMap.put(String.valueOf(s.getId().longValue()), s);
					}
				}
			}

			// if (list2.size() > 0) {
			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("ids", list2);
			// List<Map<String, Object>> chats =
			// topicChatService.getLastNoticeTimes(map);
			// if (null == chats)
			// chats = new ArrayList<Map<String, Object>>();
			// for (Map<String, Object> m : chats) {
			// dates.put(Long.valueOf(m.get("id").toString()), (Date)
			// m.get("time"));
			// }
			// }

			// 组装会议对象
			for (Social social : listMeeting) {
				if (null == social) {
					continue;
				}

				// 会议封装最后聊天时间
				if (SocialType.INVITATION.code() != social.getType()) {
					// 获取最后消息时间
					// Date time = dates.get(social.getId());
					// social.setTime(time);
					// 将从畅聊获得session信息注入social
					Social s = sessionMap.get(social.getGroupId());
					logger.debug("meeting with groupid[" + social.getGroupId() + "] ==> " + ReflectionToStringBuilder.toString(s));
					if (s != null) {
						social.setTime(s.getTime());
						social.setNewCount(s.getNewCount());
					}
				}
				SocialDetail socialDetail = social.getSocialDetail();
				if (isNullOrEmpty(socialDetail)) {
					socialDetail = new SocialDetail();
				}

				social.setCompereName(names.get(social.getCompereId()));
				if (7 == social.getType()) {
					String content = social.getCompereName() + "邀请您参加";
					socialDetail.setContent(content);
				}
				String path = social.getPath();// 指的是会议的封面图片
				if (!isNullOrEmpty(path)) {// 数据库里面的路径就是带ip和端口的
					List<String> listImage = new ArrayList<String>();
					listImage.add(path);
					socialDetail.setListImageUrl(listImage);
				}
				social.setSocialDetail(socialDetail);
				listResult.add(social);
			}

			removeDuplicateSocial(listResult);

			logger.info(userId + "meetting-listResult:" + listResult.size());

			/*
			 * Collections.sort(listResult, new Comparator<Social>() { public
			 * int compare(Social o1, Social o2) { if (o1.getType().intValue()
			 * != o2.getType().intValue()) { if (o1.getType().intValue() >
			 * o2.getType().intValue()) { return 1; } else { return -1; } } if
			 * (null == o1.getOrderTime()) { return 1; } if (null ==
			 * o2.getOrderTime()) { return -1; } return
			 * o2.getOrderTime().compareTo(o1.getOrderTime()); } });
			 */

			/**
			 * 过滤掉客户端删除的畅聊
			 */
			meetingListFilter(listResult, userId);

			Collections.sort(listResult, new Comparator<Social>() {
				public int compare(Social o1, Social o2) {
					if (o1 == o2)
						return 0;
					if (o1.getType().intValue() != o2.getType().intValue()) {
						if (o1.getType().intValue() == 5) {
							return 1;
						}
						if (o2.getType().intValue() == 5) {
							return -1;
						}
					}
					if (o1.getTime() == o2.getTime()) {
						return 0;
					}
					if (null == o1.getTime()) {
						return 1;
					}
					if (null == o2.getTime()) {
						return -1;
					}
					return o2.getTime().compareTo(o1.getTime());
				}
			});
		}
		return listResult;
	}

	// 去重复
	static void removeDuplicateSocial(List<Social> listResult) {
		for (int i = 0; i < listResult.size(); i++) // 外循环是循环的次数
		{
			for (int j = listResult.size() - 1; j > i; j--) // 内循环是 外循环一次比较的次数
			{
				if (defineUnique(listResult.get(i), listResult.get(j))) {
					listResult.remove(j);
				}
			}
		}
	}

	static boolean defineUnique(Social source, Social target) {
		if ((source.getId() == null ? 0 : source.getId().longValue()) == (target.getId() == null ? 0 : target.getId().longValue())
				&& (source.getTitle() == null ? "" : source.getTitle()).equals((target.getTitle() == null ? "" : target.getTitle()))
				&& (source.getType() == null ? 0 : source.getType().intValue()) == (target.getType() == null ? 0 : target.getType().intValue())
				&& (source.getCompereId() == null ? 0 : source.getCompereId().longValue()) == (target.getCompereId() == null ? 0 : target
						.getCompereId().longValue())
				&& (source.getCompereName() == null ? "" : source.getCompereName()) == (target.getCompereName() == null ? "" : target
						.getCompereName())
				&& (source.getNewCount() == null ? 0 : source.getNewCount().intValue()) == (target.getNewCount() == null ? 0 : target.getNewCount()
						.intValue())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 社交列表过滤
	 *
	 * @param listResult
	 * @param userId
	 */
	List<Social> socialListFilter(List<Social> listResult, Long userId) {
		List<SocialStatus> execludeResult = socialStatusService.queryListWithoutMeetingByUserId(userId);
		for (SocialStatus ss : execludeResult) {
			logger.debug("exclued ====> " + ReflectionToStringBuilder.toString(ss));
		}
		return listFilter(listResult, execludeResult);
	}

	/**
	 * 会议列表过滤方法
	 *
	 * @param listResult
	 *            会议列表
	 * @param userId
	 *            登录的用户id
	 * @return
	 */
	List<Social> meetingListFilter(final List<Social> listResult, Long userId) {
		List<SocialStatus> execludeResult = socialStatusService.queryMeetingListByUserId(userId);
		return listFilter(listResult, execludeResult);
	}

	/**
	 * 社交列表通用过滤方法,当社交集合数据达到90个以上时，将使用多线程处理
	 *
	 * @param listResult
	 *            查询的所有社交
	 * @param execludeResult
	 *            已被客户端删除的社交
	 * @return
	 */
	List<Social> listFilter(List<Social> listResult, final List<SocialStatus> execludeResult) {
		if (null == execludeResult || execludeResult.size() == 0) {
			return listResult;
		}
		int size = execludeResult.size();
		int end = 0;
		if (size > 90) {
			// end = size / 3;
			// final List<Social> list1 = listResult.subList(0, end);
			// final List<Social> list2 = listResult.subList(end, end * 2);
			// final List<Social> list3 = listResult.subList(end * 2, size);
			// CompletionService<List<Social>> completionService = new
			// ExecutorCompletionService<List<Social>>(
			// ThreadPoolUtils.getExecutor());
			// List<Future<List<Social>>> futureList = new
			// ArrayList<Future<List<Social>>>(3);
			// Future<List<Social>> future1 = completionService.submit(new
			// Callable<List<Social>>() {
			// @Override
			// public List<Social> call() throws Exception {
			// return removeClientDeletedSocial(execludeResult, list1);
			// }
			// });
			// futureList.add(future1);
			// Future<List<Social>> future2 = completionService.submit(new
			// Callable<List<Social>>() {
			// @Override
			// public List<Social> call() throws Exception {
			// return removeClientDeletedSocial(execludeResult, list2);
			// }
			// });
			// futureList.add(future2);
			// Future<List<Social>> future3 = completionService.submit(new
			// Callable<List<Social>>() {
			// @Override
			// public List<Social> call() throws Exception {
			// return removeClientDeletedSocial(execludeResult, list3);
			// }
			// });
			// futureList.add(future3);
			// listResult.clear();
			// for (Future<List<Social>> flist : futureList) {
			// try {
			// List<Social> tempList = flist.get();
			// listResult.addAll(tempList);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }
			// return listResult;
			return removeClientDeletedSocial(execludeResult, listResult);
		} else {
			return removeClientDeletedSocial(execludeResult, listResult);
		}
	}

	/**
	 * 从原始的社交中移除已被客户端标示删除的社交
	 *
	 * @param listResult
	 *            原始的社交集合
	 * @param execludeResult
	 *            已被客户端删除的社交
	 * @return
	 */
	List<Social> removeClientDeletedSocial(List<SocialStatus> execludeResult, List<Social> listResult) {
		for (SocialStatus socialState : execludeResult) {
			for (int index = 0; index < listResult.size(); index++) {
				Social social = listResult.get(index);
				long mucId = socialState.getMucId(), id = social.getId();
				int stype = social.getType(), type = socialState.getType();
				if (mucId == id && type == stype) {
					if(social.getNewCount()==0){
						listResult.remove(index);
					}
				}
			}
		}
		return listResult;
	}

	/**
	 * fetchNewCount 获取用户的未读消息数
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/fetchNewCount.json", method = RequestMethod.POST)
	public int fetchNewCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int totalNewCount = 0;
		// 获取json参数串
		String requestJson = "";
		SocialListReq socialListReq = null;
		try {
			requestJson = getJsonParamStr(request);
			socialListReq = GsonUtils.StringToObject(SocialListReq.class, requestJson);
		} catch (IOException e) {
			logger.error("请求参数错误requestJson=" + requestJson, e);
			return 0;
		}

		// 获取私聊和群聊未读消息数
		totalNewCount = imRecordmessageService.getPrivateChatAndGroupChatNewCount(socialListReq);

		User user = new User();
		user.setId(socialListReq.getUserId());
		// 获取最新的通知未读消息数
		MeetingNotice meetingNotice = meetingNoticeService.getNewNotice(user.getId());
		if (!isNullOrEmpty(meetingNotice)) {
			Integer noticeCount = meetingNoticeService.getUnReadNoticeCount(user.getId());
			if (Utils.isNullOrEmpty(noticeCount)) {
				noticeCount = 0;
			}
			totalNewCount += noticeCount;
		}

		// 获取邀请函未读消息数
		Social invitation = meetingService.getLatestInvitation(user.getId());
		if (null != invitation) {
			Integer invitationNewCount = invitation.getNewCount();
			totalNewCount += invitationNewCount == null ? 0 : invitationNewCount;
		}

		// 会议列表的未读消息数
		List<Social> listMeeting = getMeetingList(user.getId());
		if (!Utils.isNullOrEmpty(listMeeting)) {
			int meetingCount = 0;
			if (!Utils.isNullOrEmpty(listMeeting)) {
				for (Social social : listMeeting) {
					if (social.getNewCount() != null && social.getNewCount().intValue() > 0) {
						meetingCount += social.getNewCount().intValue();
					}
				}
			}
			totalNewCount += meetingCount;
		}

		// 新的关系的未读消息数
		String withNewRelation = socialListReq.getWithNewRelation();
		if (!Utils.isNullOrEmpty(withNewRelation) && withNewRelation.equals("true")) {
			int relationCount = GinTongInterface.getNewConnectionsCount("" + user.getId());
			totalNewCount += relationCount;
		}
		return totalNewCount;
	}

	/**
	 * socialList 最新的社交列表
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/socialList2.json", method = RequestMethod.POST)
	public Map<String, Object> socialList2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		// 获取json参数串
		String requestJson = "";
		SocialListReq socialListReq = null;
		try {
			requestJson = getJsonParamStr(request);
			socialListReq = GsonUtils.StringToObject(SocialListReq.class, requestJson);
		} catch (IOException e) {// 兼容老版本
			socialListReq = new SocialListReq();
		}
		try {
			User user = getUser(request);
			// 获取当前登录用户
			if (null == user || user.getId() < 1) {
				responseDataMap.put("listSocial", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "请先登录");
				return model;
			}
			final long userId = user.getId();
			socialListReq = socialListReq == null ? new SocialListReq() : socialListReq;
			socialListReq.setUserId(user.getId());
			List<Social> listResult = new ArrayList<Social>();
			// 获取私聊和群聊列表
			List<Social> chat = imRecordmessageService.getPrivateChatAndGroupChat(socialListReq); // 消息
			 if (CollectionUtils.isNotEmpty(chat)) {
				 this.setChatListToCache(chat, userId);
			 } else {
				 chat = this.getChatListFromCache(userId);
			 }

			logger.info("chat-size:" + chat.size() + " userId: " + userId);
			listResult.addAll(chat);

			for (Social s : chat) {
				logger.info("SocialList ===>" + ReflectionToStringBuilder.toString(s));
			}

			// 过滤客户端删除的畅聊
			// 2016-03-10 tanmin getPrivateChatAndGroupChat直接获取畅聊提供的数据,无需再过滤
			// logger.debug("singeAndGroupChat ====> " + listResult.size());
			socialListFilter(listResult, user.getId());
			// logger.debug("singeAndGroupChat after filter====> " +
			// listResult.size());

			Collections.sort(listResult, new Comparator<Social>() {
				public int compare(Social o1, Social o2) {
					if (null == o1.getOrderTime()) {
						return 1;
					}
					if (null == o2.getOrderTime()) {
						return -1;
					}
					return o2.getOrderTime().compareTo(o1.getOrderTime());
				}
			});
			// 获取最新的通知
			MeetingNotice meetingNotice = meetingNoticeService.getNewNotice(user.getId());
			if (!isNullOrEmpty(meetingNotice)) {
				Integer noticeCount = meetingNoticeService.getUnReadNoticeCount(user.getId());
				if (Utils.isNullOrEmpty(noticeCount)) {
					noticeCount = 0;
				}
				// 封装通知
				Social socialNotice = new Social();
				socialNotice.setId(meetingNotice.getId());
				socialNotice.setTitle("通知");
				socialNotice.setType(SocialType.NOTICE.code());
				SocialDetail socialDetailNotice = new SocialDetail();
				socialDetailNotice.setContent(meetingNotice.getNoticeContent());
				socialNotice.setTime(meetingNotice.getUpdateTime());
				socialNotice.setOrderTime(meetingNotice.getUpdateTime());
				socialNotice.setSocialDetail(socialDetailNotice);
				socialNotice.setNewCount(noticeCount);
				listResult.add(0, socialNotice);
			}
			// 获取邀请函
			Social invitation = meetingService.getLatestInvitation(user.getId());
			if (null != invitation) {
				SocialDetail socialDetail = invitation.getSocialDetail();
				if (isNullOrEmpty(socialDetail)) {
					socialDetail = new SocialDetail();
				}
				String content = invitation.getCompereName() + "邀请您参加";
				socialDetail.setContent(content);
				String path = invitation.getPath();// 指的是会议的封面图片
				if (!isNullOrEmpty(path)) {// 数据库里面的路径就是带ip和端口的
					List<String> listImage = new ArrayList<String>();
					listImage.add(path);
					socialDetail.setListImageUrl(listImage);
				}
				invitation.setSocialDetail(socialDetail);
				listResult.add(0, invitation);
			}

			Social meetingSocial = new Social();
			// meetingSocial.setNewCount(imRecordmessageService.getAllMeetingNewCount(user.getId()));
			meetingSocial.setId(-1L);
			List<Social> listMeeting = getMeetingList(user.getId());
			String title = "会议暂无消息";
			meetingSocial.setTitle(title);
			if (!Utils.isNullOrEmpty(listMeeting)) {
				Social meeting = listMeeting.get(0);
				meetingSocial.setTitle(meeting.getTitle());// 标题
				meetingSocial.setCompereName(getCompereName(userService, meeting.getCompereId()));// 发起人
				meetingSocial.setTime(meeting.getOrderTime());// 右侧时间
				meetingSocial.setType(meeting.getType());// 会议状态:3-进行中的会议，4-未开始的会议
				// 5-已结束的会议
				int meetingCount = 0;
				if (!Utils.isNullOrEmpty(listMeeting)) {
					for (Social social : listMeeting) {
						if (social.getNewCount() != null && social.getNewCount().intValue() > 0) {
							meetingCount += social.getNewCount().intValue();
						}
					}
				}
				meetingSocial.setNewCount(meetingCount);
			}
			listResult.add(0, meetingSocial);
			// String requestJson = getJsonParamStr(request);
			// if(!Utils.isNullOrEmpty(requestJson)) {
			// JSONObject j = JSONObject.fromObject(requestJson);
			String withNewRelation = socialListReq.getWithNewRelation();
			if (!Utils.isNullOrEmpty(withNewRelation) && withNewRelation.equals("true")) {
				int relationCount = GinTongInterface.getNewConnectionsCount("" + user.getId());
				Social socialRelation = new Social();
				socialRelation.setId(-2L);
				socialRelation.setTitle("新的关系");
				socialRelation.setType(SocialType.RELATION.code());
				SocialDetail socialDetailNotice = new SocialDetail();
				socialRelation.setSocialDetail(socialDetailNotice);
				socialRelation.setNewCount(relationCount);
				listResult.add(0, socialRelation);
			}
			// }
			int count = 0;
			for (Social social : listResult) {
				// logger.info("retToClient ===>" +
				// ReflectionToStringBuilder.toString(social));
				if (!Utils.isNullOrEmpty(social.getNewCount()) && social.getNewCount().intValue() != 0) {
					count += social.getNewCount().intValue();
				}
			}

			responseDataMap.put("count", count);
			responseDataMap.put("listSocial", listResult);
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello App");
		} catch (Exception e) {
			logger.error("", e);
			responseDataMap.put("listSocial", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
		}
		return model;
	}

	/**
	 * 获取社群的社交列表-一般用在“消息”-“社群消息”中
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getCommunityList.json", method = RequestMethod.POST)
	public Map<String, Object> getCommunityList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		// 获取json参数串
		String requestJson = "";
		SocialListReq socialListReq = null;
		try {
			requestJson = getJsonParamStr(request);
			socialListReq = GsonUtils.StringToObject(SocialListReq.class, requestJson);
		} catch (IOException e) {// 兼容老版本
			socialListReq = new SocialListReq();
		}
		try {
			User user = getUser(request);
			socialListReq = socialListReq == null ? new SocialListReq() : socialListReq;
			socialListReq.setUserId(user.getId());
			List<Social> listResult = new ArrayList<Social>();
			// 获取当前登录用户
			if (null == user || user.getId() < 1) {
				responseDataMap.put("listSocial", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "请先登录");
				return model;
			}
			// 获取私聊和群聊列表
			List<Community> chat = imRecordmessageService.getPrivateCommunity(socialListReq); // 消息
			if (!isNullOrEmpty(chat)) {
				// 封装私聊和群聊
				listResult.addAll(chat);
			}
			// 过滤客户端删除的畅聊
			socialListFilter(listResult, user.getId());

			Collections.sort(listResult, new Comparator<Social>() {
				public int compare(Social o1, Social o2) {
					if (null == o1.getOrderTime()) {
						return 1;
					}
					if (null == o2.getOrderTime()) {
						return -1;
					}
					return o2.getOrderTime().compareTo(o1.getOrderTime());
				}
			});
			int count = 0;
			for (Social social : listResult) {
				if (!Utils.isNullOrEmpty(social.getNewCount()) && social.getNewCount().intValue() != 0) {
					count += social.getNewCount().intValue();
				}
			}
			responseDataMap.put("count", count);
			responseDataMap.put("listCommunity", listResult);
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello App");
		} catch (Exception e) {
			e.printStackTrace();
			responseDataMap.put("listSocial", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
		}
		return model;
	}

	/**
	 * 社交列表分页接口
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/socialListNext.json", method = RequestMethod.POST)
	public Map<String, Object> socialListNext(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		// 获取json参数串
		String requestJson = "";
		SocialListReq socialListReq = null;
		try {
			requestJson = getJsonParamStr(request);
			socialListReq = GsonUtils.StringToObject(SocialListReq.class, requestJson);
		} catch (IOException e) {// 兼容老版本
			socialListReq = new SocialListReq();
		}
		try {
			User user = getUser(request);
			socialListReq = socialListReq == null ? new SocialListReq() : socialListReq;
			socialListReq.setUserId(user.getId());
			List<Social> listResult = new ArrayList<Social>();
			// 获取当前登录用户
			if (null == user || user.getId() < 1) {
				responseDataMap.put("listSocial", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "请先登录");
				return model;
			}
			// 获取私聊和群聊列表
			List<Social> chat = imRecordmessageService.getPrivateChatAndGroupChat(socialListReq); // 消息
			if (!isNullOrEmpty(chat)) {
				// 封装私聊和群聊
				listResult.addAll(chat);
			}
			// 过滤客户端删除的畅聊
			socialListFilter(listResult, user.getId());

			Collections.sort(listResult, new Comparator<Social>() {
				public int compare(Social o1, Social o2) {
					if (null == o1.getOrderTime()) {
						return 1;
					}
					if (null == o2.getOrderTime()) {
						return -1;
					}
					return o2.getOrderTime().compareTo(o1.getOrderTime());
				}
			});
			responseDataMap.put("listSocial", listResult);
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello App");
		} catch (Exception e) {
			e.printStackTrace();
			responseDataMap.put("listSocial", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
		}
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/socialListNext.json", method = RequestMethod.GET)
	public Map<String, Object> socialListNextGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return this.socialListNext(request, response);
	}

	/**
	 * 获取会议创建人的名称
	 *
	 * @param userService
	 * @param compereId
	 *            会议创建人的id
	 * @return
	 */
	static String getCompereName(UserService userService, Long compereId) {
		List<Long> list = new ArrayList<Long>();
		list.add(compereId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", list);
		List<User> users = userService.selectSimpleUsers(map);
		if (null == users)
			return "";
		User user = users.get(0);
		if (user != null) {
			return user.getName();
		}
		return "";
	}

	/**
	 * socialListGet2 新的社交列表2015-08-25
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/socialList2.json", method = RequestMethod.GET)
	public Map<String, Object> socialListGet2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return this.socialList2(request, response);
	}

	/**
	 * socialList 社交列表
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/socialList.json", method = RequestMethod.POST)
	public Map<String, Object> socialList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		try {
			User user = getUser(request);
			List<Social> listResult = new ArrayList<Social>();
			// 获取当前登录用户
			if (null == user || user.getId() < 1) {
				responseDataMap.put("listSocial", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "请先登录");
				return model;
			}

			// 获取用户所有会议（报名成功或接受邀请非草稿状态且成员未删除）和邀请函（已被邀请、未接受或拒绝的非草稿状态的且成员未删除的会议）
			List<Social> listMeetingAndInvitation = meetingService.getMeetingListAndInvitation(user.getId());
			if (null != listMeetingAndInvitation && listMeetingAndInvitation.size() > 0) {
				// 先获取人名、最后一次聊天时间
				Map<Long, String> names = new HashMap<Long, String>();
				Map<Long, Date> dates = new HashMap<Long, Date>();
				List<Long> list = new ArrayList<Long>();
				List<Long> list2 = new ArrayList<Long>();
				List<Long> listMeetingId = new ArrayList<Long>();
				for (Social social : listMeetingAndInvitation) {
					if (null == social) {
						continue;
					}
					Long compereId = social.getCompereId();
					if (null != compereId && list.contains(compereId) == false) {
						list.add(compereId);
					}
					if (null != social.getId() && SocialType.INVITATION.code() != social.getType() && list2.contains(social.getId()) == false) {
						list2.add(social.getId());
					}
					if (3 == social.getType().intValue() || 4 == social.getType().intValue() || 5 == social.getType().intValue()) {
						listMeetingId.add(social.getId());
					}
				}
				// 组装会议议题
				if (!Utils.isNullOrEmpty(listMeetingId)) {
					List<MeetingTopic> listMeetingTopic = meetingTopicService.getByMeetingIds(listMeetingId);
					Map<String, List<MeetingTopic>> mapMeetingTopic = new HashMap<String, List<MeetingTopic>>();
					if (!Utils.isNullOrEmpty(listMeetingTopic)) {
						for (MeetingTopic meetingTopic : listMeetingTopic) {
							if (meetingTopic.getIsFinished() != 1) {// 社交列表不能转发到已结束的议题
								List<MeetingTopic> tempListMeetingTopic = mapMeetingTopic.get("" + meetingTopic.getMeetingId());
								if (Utils.isNullOrEmpty(tempListMeetingTopic)) {
									tempListMeetingTopic = new ArrayList<MeetingTopic>();
								}
								tempListMeetingTopic.add(meetingTopic);
								mapMeetingTopic.put("" + meetingTopic.getMeetingId(), tempListMeetingTopic);
							}
						}
					}
					for (Social social : listMeetingAndInvitation) {
						if (3 == social.getType().intValue() || 4 == social.getType().intValue() || 5 == social.getType().intValue()) {
							social.setListMeetingTopic(mapMeetingTopic.get("" + social.getId()));
						}
					}
				}
				if (list.size() > 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ids", list);
					List<User> users = userService.selectSimpleUsers(map);
					if (null == users)
						users = new ArrayList<User>();
					for (User u : users) {
						names.put(u.getId(), u.getName());
					}
				}
				if (list2.size() > 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ids", list2);
					List<Map<String, Object>> chats = topicChatService.getLastNoticeTimes(map);
					if (null == chats)
						chats = new ArrayList<Map<String, Object>>();
					for (Map<String, Object> m : chats) {
						dates.put(Long.valueOf(m.get("id").toString()), (Date) m.get("time"));
					}
				}

				// 组装会议对象
				for (Social social : listMeetingAndInvitation) {
					if (null == social) {
						continue;
					}

					// 会议封装最后聊天时间
					if (SocialType.INVITATION.code() != social.getType()) {
						// 获取最后消息时间
						Date time = dates.get(social.getId());
						social.setTime(time);
					}
					SocialDetail socialDetail = social.getSocialDetail();
					if (isNullOrEmpty(socialDetail)) {
						socialDetail = new SocialDetail();
					}

					social.setCompereName(names.get(social.getCompereId()));
					if (7 == social.getType()) {
						String content = social.getCompereName() + "邀请您参加";
						socialDetail.setContent(content);
					}
					String path = social.getPath();// 指的是会议的封面图片
					if (!isNullOrEmpty(path)) {// 数据库里面的路径就是带ip和端口的
						List<String> listImage = new ArrayList<String>();
						listImage.add(path);
						socialDetail.setListImageUrl(listImage);
					}
					social.setSocialDetail(socialDetail);
					listResult.add(social);
				}
				Collections.sort(listResult, new Comparator<Social>() {
					public int compare(Social o1, Social o2) {
						if (null != o1.getType() && o1.getType() == 7) {
							return 1;// 邀请函排在最后
						}
						if (null != o2.getType() && o2.getType() == 7) {
							return -1;// 邀请函排在最后
						}
						if (o1.getType().intValue() != o2.getType().intValue()) {
							if (o1.getType().intValue() > o2.getType().intValue()) {
								return 1;
							} else {
								return -1;
							}
						}
						if (null == o1.getOrderTime()) {
							return 1;
						}
						if (null == o2.getOrderTime()) {
							return -1;
						}
						return o2.getOrderTime().compareTo(o1.getOrderTime());
					}
				});
			}

			// 获取最新的通知
			MeetingNotice meetingNotice = meetingNoticeService.getNewNotice(user.getId());
			Integer count = meetingNoticeService.getUnReadNoticeCount(user.getId());
			if (Utils.isNullOrEmpty(count)) {
				count = 0;
			}
			if (!isNullOrEmpty(meetingNotice)) {
				// 封装通知
				Social socialNotice = new Social();
				socialNotice.setId(meetingNotice.getId());
				socialNotice.setTitle("通知");
				socialNotice.setType(SocialType.NOTICE.code());
				SocialDetail socialDetailNotice = new SocialDetail();
				socialDetailNotice.setContent(meetingNotice.getNoticeContent());
				socialNotice.setTime(meetingNotice.getUpdateTime());
				socialNotice.setOrderTime(meetingNotice.getUpdateTime());
				socialNotice.setSocialDetail(socialDetailNotice);
				socialNotice.setNewCount(count);
				listResult.add(socialNotice);
			}
			// 获取私聊和群聊列表
			SocialListReq socialListReq = new SocialListReq();
			socialListReq.setUserId(user.getId());
			List<Social> chat = imRecordmessageService.getPrivateChatAndGroupChat(socialListReq); // 消息
			if (!isNullOrEmpty(chat)) {
				// 封装私聊和群聊
				listResult.addAll(chat);
			}

			Collections.sort(listResult, new Comparator<Social>() {
				public int compare(Social o1, Social o2) {
					if (null == o1.getTime()) {
						return 1;
					}
					if (null == o2.getTime()) {
						return -1;
					}
					return o2.getTime().compareTo(o1.getTime());
				}
			});
			responseDataMap.put("listSocial", listResult);
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello App");
		} catch (Exception e) {
			e.printStackTrace();
			responseDataMap.put("listSocial", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
		}
		return model;
	}

	/**
	 * socialList3 社交列表,为web端的私信功能
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/socialList3.json", method = RequestMethod.POST)
	public Map<String, Object> privateMessageList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);

		String requestJson = "";
		SocialListReq socialListReq = null;
		try {
			requestJson = getJsonParamStr(request);
			socialListReq = GsonUtils.StringToObject(SocialListReq.class, requestJson);
			// 获取当前登录用户
			if (null == socialListReq || socialListReq.getUserId() < 1) {
				responseDataMap.put("listSocial", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "请求参数错误requestJson=" + requestJson);
				return model;
			}
			List<Social> listResult = new ArrayList<Social>();
			// 获取用户所有会议（报名成功或接受邀请非草稿状态且成员未删除
			List<Social> meetingList = getMeetingList(socialListReq.getUserId());
			if (!isNullOrEmpty(meetingList)) {
				listResult.addAll(meetingList);
			}

			int pageSize = socialListReq.getPageSize();
			int startRow = socialListReq.getStartRow();
			socialListReq.setPageSize(0);
			socialListReq.setStartRow(-1);
			// 获取私聊和群聊列表
			List<Social> chat = imRecordmessageService.getPrivateChatAndGroupChat(socialListReq); // 消息
			if (!isNullOrEmpty(chat)) {
				// 封装私聊和群聊
				listResult.addAll(chat);
			}

			int totalPageCount = listResult.size();// 分页的总记录数

			Collections.sort(listResult, new Comparator<Social>() {
				public int compare(Social o1, Social o2) {
					if (null == o1.getTime()) {
						return 1;
					}
					if (null == o2.getTime()) {
						return -1;
					}
					return o2.getTime().compareTo(o1.getTime());
				}
			});

			int count = count(listResult);
			List<Social> result = pagenate(listResult, pageSize, startRow);
			responseDataMap.put("count", count);
			responseDataMap.put("totalPageCount", totalPageCount);
			responseDataMap.put("listSocial", result);
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello App");
		} catch (Exception e) {
			e.printStackTrace();
			responseDataMap.put("listSocial", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
		}

		return model;
	}

	static List<Social> pagenate(List<Social> listResult, int pageSize, int startRow) {
		int size = listResult.size();
		int tempPageCount = size / pageSize;
		if (size > pageSize) {
			List<Social> result = new ArrayList<Social>();
			int pageCount = size % pageSize != 0 ? 1 + tempPageCount : tempPageCount;
			if (startRow < pageCount) {
				result = listResult.subList((startRow - 1) * pageSize, startRow * pageSize - 1);
				System.out.println("size=" + result.size() + "\t" + result);
			} else if (startRow == pageCount) {
				result = listResult.subList((startRow - 1) * pageSize, size - 1);
			}
			return result;
		} else {
			return listResult;
		}
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		list.add(17);

		list = list.subList(0, 3);
		System.out.println(list);
	}

	static int count(List<Social> listResult) {
		int count = 0;
		for (Social social : listResult) {
			if (!Utils.isNullOrEmpty(social.getNewCount()) && social.getNewCount().intValue() != 0) {
				count += social.getNewCount().intValue();
			}
		}
		return count;
	}

	/**
	 * socialListGet 社交列表
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/socialList.json", method = RequestMethod.GET)
	public Map<String, Object> socialListGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return this.socialList(request, response);
	}

	/**
	 * getMyForwardingSocial 获取转发的社交列表
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMyForwardingSocial.json", method = RequestMethod.GET)
	public Map<String, Object> getMyForwardingSocialGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return getMyForwardingSocial(request, response);
	}

	/**
	 * getMyForwardingSocial 获取转发的社交列表
	 *
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMyForwardingSocial.json", method = RequestMethod.POST)
	public Map<String, Object> getMyForwardingSocial(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		Date inDate = new Date();
		logger.info("进入接口时间：" + Utils.DateFormat(inDate));
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("解析json参数失败", e);
		}
		try {
			User user = getUser(request);
			List<Social> listResult = new ArrayList<Social>();
			// 获取当前登录用户
			if (!isNullOrEmpty(user) && !isNullOrEmpty(user.getId())) {
				// 获取用户所有会议
				List<Social> listMeeting = getMeetingList(user.getId());
				if (!isNullOrEmpty(listMeeting)) {
					for (Social social : listMeeting) {
						if (!isNullOrEmpty(social)) {
							// 会议封装最后聊天时间
							if (SocialType.INVITATION.code() != social.getType()) {
								// 获取最后消息时间
								Date time = topicChatService.getLastNoticeTime(social.getId());
								if (!isNullOrEmpty(time)) {
									social.setTime(time);
								} else {
									social.setTime(null);
								}
								// 如果会议为有议题类型 获取议题列表
								List<MeetingTopic> listMeetingTopic = meetingTopicService.getForwardingTopicByMeetingId(social.getId());
								if (!isNullOrEmpty(listMeetingTopic)) {
									social.setListMeetingTopic(listMeetingTopic);
								}
							}
							SocialDetail socialDetail = social.getSocialDetail();
							if (isNullOrEmpty(socialDetail)) {
								socialDetail = new SocialDetail();
							}
							Long compereId = social.getCompereId();
							if (!isNullOrEmpty(compereId)) {
								User userCompere = userService.selectByPrimaryKey(compereId);
								if (!isNullOrEmpty(userCompere)) {
									social.setCompereName(userCompere.getName());
								}
							}
							String path = social.getPath();
							if (!isNullOrEmpty(path)) {
								List<String> listImage = new ArrayList<String>();
								listImage.add(path);
								socialDetail.setListImageUrl(listImage);
							}
							social.setSocialDetail(socialDetail);
							listResult.add(social);
						}
					}
				}
			}
			// 获取私聊和群聊列表
			SocialListReq socialListReq = new SocialListReq();
			socialListReq.setUserId(user.getId());
			List<Social> chat = imRecordmessageService.getPrivateChatAndGroupChat(socialListReq);// 消息
			if (!isNullOrEmpty(chat)) {
				// 过滤客户端删除的畅聊
				socialListFilter(chat, user.getId());
				// 畅聊排序
				Collections.sort(chat, new Comparator<Social>() {
					public int compare(Social o1, Social o2) {
						if (null == o1.getOrderTime()) {
							return 1;
						}
						if (null == o2.getOrderTime()) {
							return -1;
						}
						return o2.getOrderTime().compareTo(o1.getOrderTime());
					}
				});
				// 封装私聊和群聊
				listResult.addAll(chat);
			}
			Date searchDate = new Date();
			logger.info("查询完成时间：" + Utils.DateFormat(searchDate));
			logger.info("查询完耗时：" + (searchDate.getTime() - inDate.getTime()) / (1000 * 60) + "分"
					+ ((searchDate.getTime() - inDate.getTime()) % (1000 * 60)) / 1000 + "秒");
			Date sortDate = new Date();
			// 关键字搜索
			if (!Utils.isNullOrEmpty(requestJson)) {
				JSONObject j = JSONObject.fromObject(requestJson);
				String keyword = getStringJsonValueByKey(j, "keyword");
				if (!Utils.isNullOrEmpty(keyword) && keyword.length() > 0) {
					for (int i = listResult.size() - 1; i >= 0; i--) {
						Social social = listResult.get(i);
						if (!Utils.isNullOrEmpty(keyword) && !Utils.isNullOrEmpty(social.getTitle()) && !social.getTitle().contains(keyword)) {
							listResult.remove(i);
						}
					}
				}
			}
			System.out.println("排序完成时间：：：：：：" + Utils.DateFormat(sortDate));
			System.out.println("排序完耗时：：：：：：" + (sortDate.getTime() - searchDate.getTime()) / (1000 * 60) + "分"
					+ ((sortDate.getTime() - searchDate.getTime()) % (1000 * 60)) / 1000 + "秒");
			responseDataMap.put("listSocial", listResult);
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello App");
		} catch (Exception e) {
			e.printStackTrace();
			responseDataMap.put("listSocial", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 名称: getForwardingMeetingDataGet 描述: 根据会议id和议题id获取转发会议的详细信息
	 *
	 * @throws Exception
	 * @author qingc
	 * @since 2015-1-24
	 */
	@ResponseBody
	@RequestMapping(value = "/getForwardingMeetingData.json", method = RequestMethod.GET)
	public Map<String, Object> getForwardingMeetingDataGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return this.getForwardingMeetingData(request, response);
	}

	/**
	 * 名称: getForwardingMeetingData 描述: 根据会议id和议题id获取转发会议的详细信息
	 *
	 * @throws Exception
	 * @author qingc
	 * @since 2015-1-24
	 */
	@ResponseBody
	@RequestMapping(value = "/getForwardingMeetingData.json", method = RequestMethod.POST)
	public Map<String, Object> getForwardingMeetingData(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		MeetingQuery meetingQuery = null;
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 会议id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				String memberIdStr = getStringJsonValueByKey(j, "topicId");

				if (Utils.isAllNotNullOrEmpty(meetingIdStr, memberIdStr)) {
					meetingQuery = meetingService.getForwardingMeetingData(Long.valueOf(meetingIdStr), Long.valueOf(memberIdStr));
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello App");
				} else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
				}
			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		}
		responseDataMap.put("meetingQuery", meetingQuery);
		result.put("responseData", responseDataMap);
		result.put("notification", notificationMap);
		return result;
	}

	/**
	 * 包含null值得对象排序
	 *
	 * @return
	 */
	public Integer containsNullSort(Object obj1, Object obj2) {
		if (isNullOrEmpty(obj1)) {
			if (!isNullOrEmpty(obj2)) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return -1;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getMeetingIndustryData.json", method = RequestMethod.GET)
	public Map<String, Object> getMeetingIndustryDataGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return this.getForwardingMeetingData(request, response);
	}

	/**
	 * 名称: getForwardingMeetingData 描述: 获取会议的行业列表
	 *
	 * @throws Exception
	 * @author wangmeizhou
	 * @since 2015-1-24
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingIndustryData.json", method = RequestMethod.POST)
	public Map<String, Object> getMeetingIndustryData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		Map<String, String> industryMap = meetingDict.getDict(MeetingDict.INDUSTRY);
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello App");
		responseDataMap.put("industry", industryMap);
		result.put("responseData", responseDataMap);
		result.put("notification", notificationMap);
		return result;
	}

	/**
	 * 生态对接接口
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBigData.json", method = RequestMethod.GET)
	public Map<String, Object> getBigDataGet(HttpServletRequest request, HttpServletResponse response) {
		return getBigData(request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/getBigData.json", method = RequestMethod.POST)
	public Map<String, Object> getBigData(HttpServletRequest request, HttpServletResponse response) {
		logger.info("into /getBigData.json");
		Map<String, Object> responseData = new HashMap<String, Object>();
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONObject j = JSONObject.fromObject(requestJson);
		String userId = getStringJsonValueByKey(j, "userId");
		String targetId = getStringJsonValueByKey(j, "targetId");
		String page = getStringJsonValueByKey(j, "page");
		String rows = getStringJsonValueByKey(j, "rows");
		String scope = getStringJsonValueByKey(j, "scope");
		String url = resource.getString("hotUrl") + "/API/relation.do?userId=" + userId + "&targetId=" + targetId + "&targetType=5" + "&page=" + page
				+ "&rows=" + rows + "&scope=" + scope;
		logger.info(url);
		String json = "";
		try {
			json = HttpClientHelper.get(url);
			List<BigDataQuery> listBigDataQuery = new ArrayList<BigDataQuery>();
			int total = 0;
			if (json.length() != 0 && json.startsWith("{")) {
				JSONObject jo = JSONObject.fromObject(json);
				if (!Utils.isNullOrEmpty(jo)) {
					JSONObject meetingObj = jo.getJSONObject("meets");
					if (!Utils.isNullOrEmpty(meetingObj)) {
						total = meetingObj.optInt("total");
						JSONArray meetingJsons = meetingObj.optJSONArray("datas");
						listBigDataQuery = getMeetingDataForBigData(meetingJsons);
						listBigDataQuery = meetingService.getAttendCountAndPic(listBigDataQuery);
					}
				}
			}
			responseData.put("succeed", true);
			responseData.put("meetings", listBigDataQuery);
			responseData.put("total", total);
		} catch (Exception e) {
			logger.error("调用大数据推送接口失败", e);
			responseData.put("succeed", false);
			responseData.put("info", "调用大数据推送接口失败:" + e.getMessage());
		}
		return responseData;
	}

	/**
	 * 热门推荐会议
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/hotList.json", method = RequestMethod.GET)
	public Map<String, Object> hotListGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return hotList(request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/hotList.json", method = RequestMethod.POST)
	public Map<String, Object> hotList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("into /hotList.json");
		Map<String, Object> responseData = new HashMap<String, Object>();
		String requestJson = "";
		requestJson = getJsonParamStr(request);
		JSONObject j = JSONObject.fromObject(requestJson);
		int hot = 1;
		if (j.has("hot")) {
			hot = Integer.parseInt(j.optString("hot"));
		}
		StringBuffer sb = new StringBuffer(resource.getString("hotUrl"));
		sb.append("/API/hotMeet.do?page=");
		sb.append(j.optString("page"));
		sb.append("&rows=");
		sb.append(j.optString("rows"));
		sb.append("&type=").append(hot);
		logger.info(sb.toString());
		String json = "";
		try {
			json = HttpClientHelper.get(sb.toString());
			List<BigDataQuery> listBigDataQuery = new ArrayList<BigDataQuery>();
			int total = 0;
			if (json.length() != 0 && json.startsWith("{")) {
				JSONObject jo = JSONObject.fromObject(json);
				if (!Utils.isNullOrEmpty(jo)) {
					total = jo.optInt("total");
					JSONArray meetingJsons = jo.optJSONArray("meets");
					listBigDataQuery = getMeetingDataForBigData(meetingJsons);
				}
			}
			responseData.put("succeed", true);
			responseData.put("meetings", listBigDataQuery);
			responseData.put("total", total);
		} catch (Exception e) {
			logger.error("调用大数据热门会议接口失败", e);
			responseData.put("succeed", false);
			responseData.put("info", "调用大数据热门会议接口失败:" + e.getMessage());
		}
		return responseData;
	}

	/**
	 * 相似会议
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getSimilarMeeting.json", method = RequestMethod.GET)
	public Map<String, Object> getSimilarMeetingGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getSimilarMeeting(request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/getSimilarMeeting.json", method = RequestMethod.POST)
	public Map<String, Object> getSimilarMeeting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("into /getSimilarMeeting.json");
		Map<String, Object> responseData = new HashMap<String, Object>();
		String requestJson = "";
		requestJson = getJsonParamStr(request);
		JSONObject j = JSONObject.fromObject(requestJson);
		String targetId = getStringJsonValueByKey(j, "targetId");
		String page = getStringJsonValueByKey(j, "page");
		String rows = getStringJsonValueByKey(j, "rows");
		String url = resource.getString("hotUrl") + "/API/simMeet.do?targetId=" + targetId + "&page=" + page + "&rows=" + rows;
		logger.info(url);
		String json = "";
		try {
			json = HttpClientHelper.get(url);
			List<BigDataQuery> listBigDataQuery = new ArrayList<BigDataQuery>();
			int total = 0;
			if (json.length() != 0 && json.startsWith("{")) {
				JSONObject jo = JSONObject.fromObject(json);
				JSONObject meetingObj = jo.getJSONObject("meets");
				if (!Utils.isNullOrEmpty(meetingObj)) {
					total = meetingObj.optInt("total");
					JSONArray meetingJsons = meetingObj.optJSONArray("datas");
					listBigDataQuery = getMeetingDataForBigData(meetingJsons);
					listBigDataQuery = meetingService.getAttendCountAndPic(listBigDataQuery);
				}
			}
			responseData.put("succeed", true);
			responseData.put("meetings", listBigDataQuery);
			responseData.put("total", total);
		} catch (Exception e) {
			logger.error("调用大数据热门会议接口失败", e);
			responseData.put("succeed", false);
			responseData.put("info", "调用大数据热门会议接口失败:" + e.getMessage());
		}
		return responseData;
	}

	private List<BigDataQuery> getMeetingDataForBigData(JSONArray meetingJsons) throws Exception {
		List<BigDataQuery> listBigDataQuery = new ArrayList<BigDataQuery>();
		if (meetingJsons != null) {
			for (int i = 0; i < meetingJsons.size(); i++) {
				JSONObject meeting = meetingJsons.getJSONObject(i);
				BigDataQuery bigDataQuery = new BigDataQuery();
				bigDataQuery.setId(meeting.optLong("id"));
				bigDataQuery.setMeetingName(meeting.optString("meeting_name"));
				bigDataQuery.setMeetingDesc(meeting.optString("meeting_desc"));
				String startTime = meeting.optString("start_time");
				if (!Utils.isNullOrEmpty(startTime)) {
					if (startTime.indexOf(".") != -1) {
						startTime = startTime.substring(0, startTime.indexOf("."));
					}
					bigDataQuery.setStartTime(sdf.parse(startTime));
				}
				String endTime = meeting.optString("end_time");
				if (!Utils.isNullOrEmpty(endTime)) {
					if (endTime.indexOf(".") != -1) {
						endTime = endTime.substring(0, endTime.indexOf("."));
					}
					bigDataQuery.setEndTime(sdf.parse(endTime));
				}
				bigDataQuery.setCreateId(meeting.optLong("create_id"));
				bigDataQuery.setCreateName(meeting.optString("create_name"));
				listBigDataQuery.add(bigDataQuery);
			}
		}
		return listBigDataQuery;
	}

	/**
	 * 查询会议聊天记录
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingMessage.json", method = RequestMethod.GET)
	public Map<String, Object> getMeetingMessageGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getMeetingMessage(request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/getMeetingMessage.json", method = RequestMethod.POST)
	public Map<String, Object> getMeetingMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("into /getMeetingMessage.json");
		Map<String, Object> responseData = new HashMap<String, Object>();
		String requestJson = "";
		requestJson = getJsonParamStr(request);
		try {
			JSONObject j = JSONObject.fromObject(requestJson);
			String time = getStringJsonValueByKey(j, "time");
			String meetingId = getStringJsonValueByKey(j, "meetingId");
			String topicId = getStringJsonValueByKey(j, "topicId");
			String pageStr = getStringJsonValueByKey(j, "page");
			String sizeStr = getStringJsonValueByKey(j, "size");
			int page = Integer.parseInt(pageStr);
			int size = Integer.parseInt(sizeStr);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("meetingId", Long.parseLong(meetingId));
			if (topicId != null && topicId.length() > 0) {
				param.put("topicId", Long.parseLong(topicId));
			}
			if (time != null && time.length() > 0) {
				param.put("startTime", time + " 00:00:00");
				param.put("endTime", time + " 23:59:59");
			}
			param.put("startRow", (page - 1) * size);
			param.put("size", size);
			List<TopicChat> list = topicChatService.getMeetingMessageByPage(param);
			int total = topicChatService.getMeetingMessageCount(param);
			responseData.put("succeed", true);
			responseData.put("list", list);
			responseData.put("total", total);
			responseData.put("info", "查询会议聊天记录成功");
		} catch (Exception e) {
			logger.error("查询会议聊天记录失败", e);
			responseData.put("succeed", false);
			responseData.put("list", null);
			responseData.put("total", "0");
			responseData.put("info", "查询会议聊天记录失败:" + e.getMessage());
		}
		return responseData;
	}

	/**
	 * 发送到mq建立索引的
	 *
	 * @param meetingId
	 * @return
	 */
	private String getMeetingQueryToIndexJsonString(Long meetingId) {
		Meeting meeting = meetingService.getById(meetingId);
		Map<String, Object> data = new HashMap<String, Object>(15);
		data.put("id", meeting.getId());
		// 会议封面图片搜索没有意义
		data.put("path", "");
		data.put("createId", meeting.getCreateId());
		data.put("createTime", meeting.getCreateTime().getTime());
		data.put("meetingDesc", meeting.getMeetingDesc());
		data.put("meetingName", meeting.getMeetingName());
		data.put("meetingStatus", meeting.getMeetingStatus());
		data.put("meetingAddress", meeting.getMeetingAddress());
		data.put("province", meeting.getProvince());
		data.put("city", meeting.getCity());
		data.put("town", meeting.getTown());
		data.put("listIndustry", meeting.getIndustry());
		return JSONObject.fromObject(data).toString();
	}

	// 推送MQ
	private void pushIndexByMQ(String flagTypes, String json) {
		logger.info("会议发送到mq服务建立索引的数据为:{}", json);
		RocketSendResult result = defaultMessageService.sendMessage(TopicType.MEETING_TOPIC, flagTypes, json);
		int bscode = result.getBscode();
		if (bscode != 0) {
			StringBuilder sb = new StringBuilder("MQ error : bscode=");
			sb.append(bscode);
			sb.append(",errorMessage=");
			sb.append(result.getErrorMessage());
			sb.append(" | ");
			sb.append("flagTypes=");
			sb.append(flagTypes);
			sb.append(",json=");
			sb.append(json);
			logger.error("向mq中发生数据出现错误:{}", sb.toString());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getCommunityNewCountByUserId/{userId}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Map<String, Object> getCommunityNewCountByUserId(@PathVariable("userId") Integer userId, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<CommunityNewCount> result = imRecordmessageService.getCommunityNewCountByUserId(userId);
		Notification notification = new Notification();
		notification.setNotifCode("0001");
		notification.setNotifInfo("操作成功");
		resultMap.put("responseData", result);
		resultMap.put("notification", notification);
		return resultMap;
	}
	
	private void setChatListToCache(final List<Social> chat,final long userId) {
		logger.info("set chat list for userId: " + userId);
		cache.setByRedis(chatListKey(userId), chat, expiredTime);
	}
	
	private List<Social> getChatListFromCache(final long userId) {
		logger.info("get chat list for userId: " + userId);
		return (List<Social>)cache.getByRedis(chatListKey(userId));
	}
	
	private String chatListKey(long userId)	{
		return "chat_list_" + userId + "_";
	}
}
