package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ginkgocap.parasol.file.exception.FileIndexServiceException;
import com.ginkgocap.parasol.file.model.FileIndex;
import com.ginkgocap.parasol.file.service.FileIndexService;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ginkgocap.ywxt.common.base.BaseController;
// import com.ginkgocap.ywxt.file.model.FileIndex;
// import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingFile;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.model.meeting.MeetingTopic;
import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.service.meeting.MeetingMemberService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoticeService;
import com.ginkgocap.ywxt.service.meeting.MeetingPicService;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.service.meeting.MeetingTopicService;
import com.ginkgocap.ywxt.service.meeting.TopicChatService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.DateUtil;
import com.ginkgocap.ywxt.utils.GinTongInterface;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.utils.type.NoticeReceiverType;
import com.ginkgocap.ywxt.utils.type.NoticeType;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingTopicQuery;
import com.ginkgocap.ywxt.vo.query.meeting.UserBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @Description: Controller
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/topic")
public class MeetingTopicController extends BaseController {
	@Autowired
	private MeetingTopicService meetingTopicService;
	@Autowired
	private TopicChatService topicChatService;
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private MeetingPicService meetingPicService;
	@Resource
	private FileIndexService fileIndexService;
	@Autowired
	private MeetingMemberService meetingMemberService;
	@Autowired
	private MeetingNoticeService meetingNoticeService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 名称: getMeetingTopicListGet
     * 描述: 获取会议议题聊天数据
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingTopicList.json", method = RequestMethod.GET)
	public Map<String, Object> getMeetingTopicListGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getMeetingTopicList(request, response);
		return model;
	}

	/**
	 * 名称: getMeetingTopicList
     * 描述: 获取会议议题聊天数据
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingTopicList.json", method = RequestMethod.POST)
	public Map<String, Object> getMeetingTopicList(HttpServletRequest request,
			HttpServletResponse response) {
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
			String meetingIdStr=getStringJsonValueByKey(j, "meetingId");
			MeetingQuery meetingQuery=new MeetingQuery();
			if(!isNullOrEmpty(meetingIdStr)){
				Long meetingId=Long.valueOf(meetingIdStr);
				 meetingQuery=meetingTopicService.getMeetingTopicList(meetingId);
			}
			// json 转为对象
				responseDataMap.put("meetingQuery",meetingQuery);
				notificationMap.put("notifCode", "0001");
				notificationMap.put("notifInfo", "hello mobile app!");
			} catch (Exception e) {
				responseDataMap.put("meetingQuery",null );
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
			} else {
				logger.error("参数异常");
				responseDataMap.put("meetingQuery",null );
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: getTopicChatListGet
     * 描述: 获取议题聊天数据
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getTopicChatList.json", method = RequestMethod.GET)
	public Map<String, Object> getTopicChatListGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getTopicChatList(request, response);
		return model;
	}

	/**
	 * 名称: getTopicChatList
     * 描述: 获取议题聊天数据
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getTopicChatList.json", method = RequestMethod.POST)
	public Map<String, Object> getTopicChatList(HttpServletRequest request,
			HttpServletResponse response) {
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
			// 获取议题id
			String topicIdStr=getStringJsonValueByKey(j, "topicId");
			MeetingTopicQuery meetingTopicQuery=new MeetingTopicQuery();
			if(!isNullOrEmpty(topicIdStr)){
				Long topicId=Long.valueOf(topicIdStr);
				 meetingTopicQuery=meetingTopicService.getTopicChatList(topicId);
				 notificationMap.put("notifCode", "0001");
				 notificationMap.put("notifInfo", "hello mobile app!");
			}
				responseDataMap.put("meetingTopicQuery",meetingTopicQuery);
			} catch (Exception e) {
				responseDataMap.put("meetingTopicQuery",null );
				notificationMap.put("notifCode", "0001");
				notificationMap.put("notifInfo",e.getMessage());
				e.printStackTrace();
			}
			} else {
				logger.error("参数异常");
				responseDataMap.put("meetingTopicQuery",null );
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo","参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: updateTopic
     * 描述: 修改议题
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTopic.json", method = RequestMethod.GET)
	public Map<String, Object> updateTopicGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = updateTopic(request, response);
		return model;
	}

	/**
	 * 名称: updateTopic
     * 描述: 修改议题
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTopic.json", method = RequestMethod.POST)
	public Map<String, Object> updateTopic(HttpServletRequest request,
			HttpServletResponse response) {
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
		if (!Utils.isNullOrEmpty(user)
				&& !Utils.isNullOrEmpty(user.getId())
				&& !isNullOrEmpty(requestJson)) {
			try {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				MeetingTopicQuery meetingTopicQuery = (MeetingTopicQuery) gson.fromJson(requestJson, MeetingTopicQuery.class);
				if(isNullOrEmpty(meetingTopicQuery)||isNullOrEmpty(meetingTopicQuery.getMeetingId())){
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
				}else{
					MeetingTopic meetingTopic = meetingTopicQuery.createMeetingTopic();
					Meeting meeting = meetingService.getById(meetingTopic.getMeetingId());
					MeetingTopic elderMeetingTopic = null;
					if(!Utils.isNullOrEmpty(meetingTopic.getId())) {
						elderMeetingTopic = meetingTopicService.getById(meetingTopic.getId());
					}
					if(isNullOrEmpty(meeting)){
						responseDataMap.put("succeed", false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议不存在");
					} else {
						List<MeetingNotice> listMeetingNotice = new ArrayList<MeetingNotice>();
						List<MeetingMember> listMeetingMember = meetingMemberService.getByMeetingId(meetingTopic.getMeetingId());
						if(!Utils.isNullOrEmpty(listMeetingMember)) {
							for(MeetingMember meetingMember : listMeetingMember) {
								if(!Utils.isNullOrEmpty(meetingMember)
										&& ((!Utils.isNullOrEmpty(meetingMember.getMemberType()) && 3==meetingMember.getMemberType().intValue())
										|| (!Utils.isNullOrEmpty(meetingMember.getExcuteMeetSign()) && 1==meetingMember.getExcuteMeetSign().intValue())
										|| (!Utils.isNullOrEmpty(meetingMember.getAttendMeetStatus()) && 1==meetingMember.getAttendMeetStatus().intValue()))) {
									MeetingNotice meetingNotice = new MeetingNotice();
									// 封装创建人
									meetingNotice.setCreateId(user.getId());
									meetingNotice.setCreateName(user.getName());
									meetingNotice.setCreateTime(new Date());
									// 封装会议ID
									meetingNotice.setMeetingId(meeting.getId());
									meetingNotice.setIsShow(1);
									// 封装通知接收人
									meetingNotice.setReceiver(meetingMember.getMemberId());
									meetingNotice.setReceiverName(meetingMember.getMemberName());
									// 封装接收人类型
									if(2 == meetingMember.getMemberType().intValue()) {
										meetingNotice.setReceiverType(NoticeReceiverType.CREATER.code());
									} else {
										meetingNotice.setReceiverType(NoticeReceiverType.PARTICIPANTS.code());
									}
									// 封装更新时间
									meetingNotice.setUpdateTime(new Date());
									//封装议题类型
									if(!Utils.isNullOrEmpty(meetingTopic.getId())) {
										//修改议题
										meetingNotice.setNoticeType(NoticeType.UPDATE_MEETING_TOPIC.code());
										if(!Utils.isNullOrEmpty(elderMeetingTopic)) {
											meetingNotice.setNoticeContent(elderMeetingTopic.getTopicContent());
										} else {
											meetingNotice.setNoticeContent(meetingTopic.getTopicContent());
										}
									} else {
										meetingNotice.setNoticeType(NoticeType.ADD_MEETING_TOPIC.code());
										meetingNotice.setNoticeContent(meetingTopic.getTopicContent());
									}
									listMeetingNotice.add(meetingNotice);
								}
							}
						}
						meetingTopicService.saveOrUpdate(meetingTopic);
						//处理图片、文件
						meetingTopicQuery.setId(meetingTopic.getId());
						this.updateMeetingTopicPicAndFile(meetingTopicQuery);
						//发送通知消息
						if(!Utils.isNullOrEmpty(listMeetingNotice)) {
							for(MeetingNotice meetingNotice : listMeetingNotice) {
								meetingNoticeService.saveOrUpdate(meetingNotice);
							}
							String content = meetingTopic.getCreateName();
							if(!Utils.isNullOrEmpty(meetingTopic.getId())) {
								if(!Utils.isNullOrEmpty(elderMeetingTopic)) {
									content += "修改了" + elderMeetingTopic.getTopicContent();
								} else {
									content += "修改了" + meetingTopic.getTopicContent();
								}
							} else {
								content += "新增了" + meetingTopic.getTopicContent();
							}
							content += "议题";
							UserBean userBean = new UserBean();
							String dateStr=DateUtil.convertDateToStringForChina(new Date());
							GinTongInterface.pushToAttendMeetingMember(userBean, String.valueOf(meeting.getId()), content, false, dateStr);
							GinTongInterface.pushToMeetingCreater(userBean, String.valueOf(meetingTopic.getMeetingId()), content, dateStr);
						}
						responseDataMap.put("succeed", true);
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello mobile app!");
					}
				}
			} catch (Exception e) {
				responseDataMap.put("succeed", false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("参数异常");
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
			responseDataMap.put("succeed",false);
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 更新议题相关的图片、文件
	 * @param meetingTopicQuery
	 */
	private void updateMeetingTopicPicAndFile(MeetingTopicQuery meetingTopicQuery) throws FileIndexServiceException {
		List<String> listFileIndexId = new ArrayList<String>();
		if(!Utils.isNullOrEmpty(meetingTopicQuery)) {
			List<Long> listMeetingPicId = new ArrayList<Long>();
			if(!Utils.isNullOrEmpty(meetingTopicQuery.getListMeetingPic())) {
				for(int i=0; i<meetingTopicQuery.getListMeetingPic().size(); i++) {
					MeetingPic meetingPic = meetingTopicQuery.getListMeetingPic().get(i);
					if(!Utils.isNullOrEmpty(meetingPic)) {
						meetingPic.setMeetingId(meetingTopicQuery.getMeetingId());
						meetingPic.setModuleId(meetingTopicQuery.getId());
						meetingPic.setModuleType(MeetingPic.MODULE_TYPE_TOPIC);
						meetingPic.setCreateDate(new Date());
						if(i == 0) {
							meetingPic.setIshomePage(1);
						} else {
							meetingPic.setIshomePage(0);
						}
						meetingPicService.saveOrUpdate(meetingPic);
						listMeetingPicId.add(meetingPic.getId());
						listFileIndexId.add(""+meetingPic.getFileIndexId());
					}
				}
			}
			//删除图片
			meetingPicService.deleteByModuleId(meetingTopicQuery.getId(), MeetingPic.MODULE_TYPE_TOPIC, listMeetingPicId);
			if(!Utils.isNullOrEmpty(meetingTopicQuery.getListMeetingFile())){
				for(MeetingFile meetingFile : meetingTopicQuery.getListMeetingFile()) {
					if(!Utils.isNullOrEmpty(meetingFile)) {
						listFileIndexId.add(""+meetingFile.getFileIndexId());
					}
				}
			}
			//删除已移除的FileIndex
			if(!Utils.isNullOrEmpty(meetingTopicQuery.getTaskId())) {
				List<FileIndex> listFileIndex = fileIndexService.getFileIndexesByTaskId(meetingTopicQuery.getTaskId());
				if(!Utils.isNullOrEmpty(listFileIndex)) {
					for(FileIndex fileIndex : listFileIndex) {
						if(!listFileIndexId.contains(""+fileIndex.getId())) {
							fileIndexService.deleteFileIndexById(meetingTopicQuery.getCreateId(),fileIndex.getId());
						}
					}
				}
			}
		}
	}
	/**
	 * 名称: enteredMeetingNoticeGet
     * 描述: 进入会议消息
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/enteredMeetingNotice.json", method = RequestMethod.GET)
	public Map<String, Object> enteredMeetingNoticeGet(HttpServletRequest request,
			HttpServletResponse response) {
		return this.enteredMeetingNotice(request, response);
	}
	/**
	 * 名称: enteredMeetingNotice
     * 描述: 进入会议消息
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/enteredMeetingNotice.json", method = RequestMethod.POST)
	public Map<String, Object> enteredMeetingNotice(HttpServletRequest request,
			HttpServletResponse response) {
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
				// 获取参数
				String meetingIdStr=getStringJsonValueByKey(j, "meetingId");
				String topicIdStr=getStringJsonValueByKey(j, "topicId");
				if(!Utils.isAllNotNullOrEmpty(meetingIdStr,topicIdStr)){
					responseDataMap.put("succeed",false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
				}else{
					Meeting meeting=meetingService.getById(Long.valueOf(meetingIdStr));
					MeetingTopic meetingTopicTemp=meetingTopicService.getById(Long.valueOf(topicIdStr));
					if(isNullOrEmpty(meeting)){
						responseDataMap.put("succeed",false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议不存在");
					}else if(isNullOrEmpty(meetingTopicTemp)){
						responseDataMap.put("succeed",false);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "议题不存在");
					}else{
						// 进入
						User user=getUser(request);
						TopicChat topicChat=new TopicChat();
						topicChat.setMemberId(user.getId());
						topicChat.setMemberName(user.getName());
						topicChat.setChatContent(user.getName()+"进入会议");
						topicChat.setMeetingId(Long.valueOf(meetingIdStr));
						topicChat.setTopicId(Long.valueOf(topicIdStr));
						topicChat.setMessageId(Utils.genMessageID(user.getId()));
						try {
							topicChatService.sendingSystemMessage(topicChat );
							responseDataMap.put("succeed",true);
							notificationMap.put("notifCode", "0001");
							notificationMap.put("notifInfo", "hello mobile app!");
						} catch (Exception e) {
							responseDataMap.put("succeed",false);
							notificationMap.put("notifCode", "0002");
							notificationMap.put("notifInfo", e.getMessage());
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				responseDataMap.put("succeed",false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("参数异常");
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
			responseDataMap.put("succeed",false);
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 删除议题
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteTopic.json", method = RequestMethod.GET)
	public Map<String, Object> deleteTopicGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = deleteTopic(request, response);
		return model;
	}
	/**
	 * 删除议题
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteTopic.json", method = RequestMethod.POST)
	public Map<String, Object> deleteTopic(HttpServletRequest request,
			HttpServletResponse response) {
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
		if (!Utils.isNullOrEmpty(user)
				&& !Utils.isNullOrEmpty(user.getId())
				&& !isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				String topicIdStr = getStringJsonValueByKey(j, "topicId");
				MeetingTopic meetingTopic = meetingTopicService.getById(Long.parseLong(topicIdStr));
				if(isNullOrEmpty(meetingTopic)) {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "议题不存在");
				} else {
					meetingTopicService.delete(Long.parseLong(topicIdStr));
					//删除议题相关的附件
					if(!Utils.isNullOrEmpty(meetingTopic.getTaskId())) {
						fileIndexService.deleteFileIndexesByTaskId(meetingTopic.getTaskId());
					}
					//删除议题相关的图片
					meetingPicService.deleteByModuleId(Long.parseLong(topicIdStr), MeetingPic.MODULE_TYPE_TOPIC, null);
					//发送通知
					List<MeetingNotice> listMeetingNotice = new ArrayList<MeetingNotice>();
					List<MeetingMember> listMeetingMember = meetingMemberService.getByMeetingId(meetingTopic.getMeetingId());
					if(!Utils.isNullOrEmpty(listMeetingMember)) {
						for(MeetingMember meetingMember : listMeetingMember) {
							if(!Utils.isNullOrEmpty(meetingMember)
									&& (3==meetingMember.getMemberType().intValue()
									|| 1==meetingMember.getExcuteMeetSign().intValue()
									|| 1==meetingMember.getAttendMeetStatus())) {
								MeetingNotice meetingNotice = new MeetingNotice();
								meetingNotice.setCreateId(user.getId());
								meetingNotice.setCreateName(user.getName());
								meetingNotice.setCreateTime(new Date());
								meetingNotice.setUpdateTime(new Date());
								meetingNotice.setMeetingId(meetingTopic.getMeetingId());
								meetingNotice.setIsShow(1);
								meetingNotice.setReceiver(meetingMember.getMemberId());
								meetingNotice.setReceiverName(meetingMember.getMemberName());
								if(2 == meetingMember.getMemberType().intValue()) {
									meetingNotice.setReceiverType(NoticeReceiverType.CREATER.code());
								} else {
									meetingNotice.setReceiverType(NoticeReceiverType.PARTICIPANTS.code());
								}
								meetingNotice.setNoticeType(NoticeType.DELETE_MEETING_TOPIC.code());
								meetingNotice.setNoticeContent(meetingTopic.getTopicContent());
								listMeetingNotice.add(meetingNotice);
							}
						}
						if(!Utils.isNullOrEmpty(listMeetingNotice)) {
							for(MeetingNotice meetingNotice : listMeetingNotice) {
								meetingNoticeService.saveOrUpdate(meetingNotice);
							}
							String content = meetingTopic.getCreateName() + "删除了" + meetingTopic.getTopicContent() + "议题";
							UserBean userBean = new UserBean();
							String dateStr=DateUtil.convertDateToStringForChina(new Date());
							GinTongInterface.pushToAttendMeetingMember(userBean, String.valueOf(meetingTopic.getMeetingId()), content, false, dateStr);
							GinTongInterface.pushToMeetingCreater(userBean, String.valueOf(meetingTopic.getMeetingId()), content, dateStr);
						}
					}
					responseDataMap.put("succeed", true);
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app!");
				}
			} catch (Exception e) {
				responseDataMap.put("succeed",false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("参数异常");
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
			responseDataMap.put("succeed",false);
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 删除议题
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/finishTopic.json", method = RequestMethod.GET)
	public Map<String, Object> finishTopicGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = finishTopic(request, response);
		return model;
	}
	/**
	 * 删除议题
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/finishTopic.json", method = RequestMethod.POST)
	public Map<String, Object> finishTopic(HttpServletRequest request,
			HttpServletResponse response) {
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
		if (!Utils.isNullOrEmpty(user)
				&& !Utils.isNullOrEmpty(user.getId())
				&& !isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				String topicIdStr = getStringJsonValueByKey(j, "topicId");
				String[] topicIdArr = topicIdStr.split(",");
				for(String topicId : topicIdArr) {
					MeetingTopic meetingTopic = meetingTopicService.getById(Long.parseLong(topicId));
					if(!isNullOrEmpty(meetingTopic)) {
						meetingTopic.setIsFinished(1);
						meetingTopicService.saveOrUpdate(meetingTopic);
						responseDataMap.put("succeed", true);
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello mobile app!");
					}
				}
			} catch (Exception e) {
				responseDataMap.put("succeed",false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("参数异常");
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
			responseDataMap.put("succeed",false);
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	@Override
	public Logger getLogger() {
		return logger;
	}
}
