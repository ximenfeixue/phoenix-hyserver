package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gintong.frame.util.dto.CommonResultCode;
import com.gintong.frame.util.dto.InterfaceResult;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ginkgocap.ywxt.common.base.BaseController;
import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.model.meeting.MeetingNote;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.model.meeting.MeetingOrgan;
import com.ginkgocap.ywxt.model.meeting.MeetingPeople;
import com.ginkgocap.ywxt.model.meeting.MeetingTopic;
import com.ginkgocap.ywxt.service.meeting.MeetingCountService;
import com.ginkgocap.ywxt.service.meeting.MeetingDataService;
import com.ginkgocap.ywxt.service.meeting.MeetingMemberService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoteDetailService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoteService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoticeService;
import com.ginkgocap.ywxt.service.meeting.MeetingOrganService;
import com.ginkgocap.ywxt.service.meeting.MeetingPeopleService;
import com.ginkgocap.ywxt.service.meeting.MeetingPicService;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.service.meeting.MeetingTopicService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.MeetingDict;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingMemberListQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoteQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;

/**
 * @Description: Controller
 * @Author: qinguochao
 * @CreateDate: 2014-9-17
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/my")
public class MyMeetingController extends BaseController {
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private MeetingMemberService meetingMemberService;
	@Autowired
	private MeetingPeopleService meetingPeopleService;
	@Autowired
	private MeetingDataService meetingDataService;
	@Autowired
	private MeetingNoteService meetingNoteService;
	@Autowired
	private MeetingNoteDetailService meetingNoteDetailService;
	@Autowired
	private MeetingNoticeService meetingNoticeService;
	@Autowired
	private MeetingDict meetingDict;
	@Autowired
	private MeetingPicService meetingPicService;
	@Autowired
	private MeetingOrganService meetingOrganService;
	@Autowired
	private MeetingTopicService meetingTopicService;
	@Autowired
	private MeetingCountService meetingCountService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	//我的会议列表查询参数 0：全查 1：我的会议 2：人脉 3：组织 4：知识 5：事件 6：笔记 
	private static final String MY_MEETING_SEARCH_TYPE_ALL = "0";
	//private static final String MY_MEETING_SEARCH_TYPE_MEETING = "1";
	private static final String MY_MEETING_SEARCH_TYPE_PEOPLE = "2";
	private static final String MY_MEETING_SEARCH_TYPE_ORGAN = "3";
	private static final String MY_MEETING_SEARCH_TYPE_KNOWLEDGE = "4";
	private static final String MY_MEETING_SEARCH_TYPE_REQUIREMENT = "5";
	private static final String MY_MEETING_SEARCH_TYPE_NOTE = "6";
	/**
	 * 名称: meetingListGet 描述: 获取我的会议列表和邀请函
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/meetingList.json", method = RequestMethod.GET)
	public Map<String, Object> meetingListGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = meetingList(request, response);
		return model;
	}

	/**
	 * 名称: meetingList 描述: 获取我的会议列表和邀请函
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/meetingList.json", method = RequestMethod.POST)
	//此方法不用了
	public Map<String, Object> meetingList(HttpServletRequest request,
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
		List<MeetingMemberListQuery> listResult = new ArrayList<MeetingMemberListQuery>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 获取用户Id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				if (!isNullOrEmpty(memberIdStr)) {
					Long memberId = Long.valueOf(memberIdStr);
					// 获取会议中
					List<MeetingMemberListQuery>listInMeeting=meetingService.getInMeetingByMemberId(memberId);
					MeetingNotice meetingNotice=meetingNoticeService.getNewNotice(memberId);
					if(!isNullOrEmpty(listInMeeting)){
						listResult.addAll(listInMeeting);
					}
					// 封装通知
					if(!isNullOrEmpty(meetingNotice)){
						MeetingMemberListQuery meetingMemberListQuery=new MeetingMemberListQuery();
						meetingMemberListQuery.setType("2");
						meetingMemberListQuery.setMeetingDesc(meetingNotice.getNoticeContent());
						meetingMemberListQuery.setCreateId(meetingNotice.getCreateId());
						meetingMemberListQuery.setCreateTime(meetingNotice.getUpdateTime());
						listResult.add(meetingMemberListQuery);
					}
					
					// 封装邀请函
					List<MeetingMemberListQuery> listInvitation = meetingService.getMyLastInvitation(memberId);
					if(!isNullOrEmpty(listInvitation)){
							listResult.addAll(listInvitation);
					}
					// 封装未开始和已结束的会议
					List<MeetingMemberListQuery> listOtherMeeting = meetingService.getListOtherMeeting(memberId);
					if(!isNullOrEmpty(listOtherMeeting)){
						listResult.addAll(listOtherMeeting);
					}
					// 获取邀请函数量
					int invitationCount = meetingService
							.getInvitationCount(memberId);
					responseDataMap.put("invitationCount", invitationCount);
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app!");
				} else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "memberId为空");
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
		responseDataMap.put("listMeetingMemberListQuery", listResult);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 名称: getMyInvitationGet 描述: 获取我的邀请函列表
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
//
//	@ResponseBody
//	@RequestMapping(value = "/getMyInvitation.json", method = RequestMethod.GET)
//	public Map<String, Object> getMyInvitationGet(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		Map<String, Object> model = getMyInvitation(request);
//		return model;
//	}

	/**
	 * 名称: getMyInvitation 描述: 获取我的邀请函列表
	 * 
	 * @param request 请求
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMyInvitation.json", method = RequestMethod.POST)
	public InterfaceResult getMyInvitation(HttpServletRequest request) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		List<MeetingMemberListQuery> listResult = new ArrayList<MeetingMemberListQuery>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 获取成员id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				if (!isNullOrEmpty(memberIdStr)) {
					Long memberId=Long.valueOf(memberIdStr);
					listResult = meetingService.getMyInvitation(memberId);
				} else {
					return InterfaceResult.getInterfaceResultInstance(CommonResultCode.PARAMS_NULL_EXCEPTION);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
			}
		} else {
			return InterfaceResult.getInterfaceResultInstance(CommonResultCode.PARAMS_EXCEPTION);
		}
		responseDataMap.put("listMeetingMemberListQuery", listResult);
		return InterfaceResult.getSuccessInterfaceResultInstance(responseDataMap);
	}



	/**
	 * 名称: changeMyMemberMeetStatusGet 
	 * 描述: 改变我的会议状态 type 0：归档，1：删除
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/changeMyMemberMeetStatus.json", method = RequestMethod.GET)
	public Map<String, Object> changeMyMemberMeetStatusGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = changeMyMemberMeetStatus(request, response);
		return model;
	}

	/**
	 * 名称: changeMyMemberMeetStatus 
	 * 描述: 改变我的会议状态 type 0：归档，1：删除
	 * 删除不是删除会议，知识在我的会议列表不显示，不能删除进行中的，只能归档已结束的会议
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/changeMyMemberMeetStatus.json", method = RequestMethod.POST)
	public Map<String, Object> changeMyMemberMeetStatus(
			HttpServletRequest request, HttpServletResponse response) {
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
		Map<String, Object> page = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 获取会议Id
				String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
				// 获取用户Id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				// 操作类型
				String type = getStringJsonValueByKey(j, "type");
				if (Utils.isAllNotNullOrEmpty(meetingIdStr, memberIdStr, type)) {
					if ("0".equals(type)) {//保存我的会议
						meetingMemberService.changeMyMemberMeetStatus(
								Long.valueOf(meetingIdStr),
								Long.valueOf(memberIdStr), 1);
						//增加会议收藏数
						meetingCountService.addCollectCount(Long.valueOf(meetingIdStr));
						responseDataMap.put("succeed", true);
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "归档成功");
					} else if ("1".equals(type)) {//删除我的会议
						meetingMemberService.changeMyMemberMeetStatus(
								Long.valueOf(meetingIdStr),
								Long.valueOf(memberIdStr), 2);
						responseDataMap.put("succeed", true);
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "删除成功");
					}
				} else {
					responseDataMap.put("succeed", false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "type 类型错误");
				}
				responseDataMap.put("page", page);
			} catch (Exception e) {
				logger.error("操作异常");
				responseDataMap.put("succeed", false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			responseDataMap.put("succeed", false);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: getMeetingSquare 描述: 获取会议广场
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingSquare.json", method = RequestMethod.GET)
	public Map<String, Object> getMeetingSquareGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getMeetingSquare(request, response);
		return model;
	}

	/**
	 * 名称: getMeetingSquare 描述: 获取会议广场 type :0 按时间查，1 按地点查，其他的 查寻所有 date:today
	 * 今天，tomorrow 明天，week 本周，weekend 本周末，month 本月 ，other 全部
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingSquare.json", method = RequestMethod.POST)
	public Map<String, Object> getMeetingSquare(HttpServletRequest request,
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
		Map<String, Object> page = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		Integer count=0;
		// 封装会议广场
		List<MeetingMemberListQuery> listMeetingMemberQuery = new ArrayList<MeetingMemberListQuery>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				String client = getStringJsonValueByKey(j, "client");
				if(!"ios".equals(client)) {
					return this.getMeetingSquare4Android(request, response);
				}
				// 查询类型
				String time = getStringJsonValueByKey(j, "time");
				String location = getStringJsonValueByKey(j, "location");
				String industry = getStringJsonValueByKey(j, "industry");
				String keyword = getStringJsonValueByKey(j, "keyword");
				// 查询页数
				String indexStr = getStringJsonValueByKey(j, "index");
				Integer index = isNullOrEmpty(indexStr) ? null : Integer.valueOf(indexStr);
				// 查询条数
				String sizeStr = getStringJsonValueByKey(j, "size");
				Integer size=isNullOrEmpty(sizeStr) ? null : Integer.valueOf(sizeStr);
				page.put("index",index);
				page.put("size",size);
				// 封装查本市还是其他城市
				Integer isCurrentCity=null;
				String position=null;
				// 用户所在地点经纬度坐标
				Double addressPosX=null;
				Double addressPosY=null;
				// 是否查找附近的会议
				// 获取用户Id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				if (!isNullOrEmpty(memberIdStr)) {
					//时间查询条件
					Date beginDate = null;
					//结束时间
					Date endDate = null;
					//开始时间
					Calendar calendar = Calendar.getInstance();
					if (!isNullOrEmpty(time)) {
						if ("today".equals(time)) {// 今天
							// 设置今天零点
							calendar.set(Calendar.HOUR_OF_DAY, 0);
							calendar.set(Calendar.MINUTE, 0);
							calendar.set(Calendar.SECOND, 0);
							beginDate = (Date) calendar.getTime().clone();
							// 设置明天零点
							int datPos = calendar.get(Calendar.DAY_OF_MONTH);
							calendar.set(Calendar.DAY_OF_MONTH, datPos + 1);
							endDate = (Date) calendar.getTime().clone();
						} else if ("tomorrow".equals(time)) {// 明天
							// 设置名天零点
							calendar.set(Calendar.HOUR_OF_DAY, 0);
							calendar.set(Calendar.MINUTE, 0);
							calendar.set(Calendar.SECOND, 0);
							// 设置后天零点
							int datPos = calendar.get(Calendar.DAY_OF_MONTH);
							calendar.set(Calendar.DAY_OF_MONTH, datPos + 1);
							beginDate = (Date) calendar.getTime().clone();
							datPos = calendar.get(Calendar.DAY_OF_MONTH);
							calendar.set(Calendar.DAY_OF_MONTH, datPos + 1);
							endDate = (Date) (Date) calendar.getTime().clone();
						} else if ("week".equals(time)) {// 本周
							// 获取本周周一
							calendar = Utils.getMondayOfThisWeek(calendar);
							calendar.set(Calendar.HOUR_OF_DAY, 0);
							calendar.set(Calendar.MINUTE, 0);
							calendar.set(Calendar.SECOND, 0);
							beginDate = (Date) calendar.getTime().clone();
							// 获取下周周一
							int datPos = calendar.get(Calendar.DAY_OF_MONTH);
							calendar.set(Calendar.DAY_OF_MONTH, datPos + 7);
							endDate = (Date) calendar.getTime().clone();
						} else if ("month".equals(time)) {// 本月
							// 获取本月1号
							calendar.set(Calendar.DAY_OF_MONTH, 1);
							calendar.set(Calendar.HOUR_OF_DAY, 0);
							calendar.set(Calendar.MINUTE, 0);
							calendar.set(Calendar.SECOND, 0);
							beginDate = (Date) calendar.getTime().clone();
							// 获取下月1号
							int datPos = calendar.get(Calendar.MONTH);
							calendar.set(Calendar.MONTH, datPos + 1);
							endDate = (Date) calendar.getTime().clone();
						}
					}
					//地点查询条件
					position = getStringJsonValueByKey(j, "position");
					if (Utils.isAllNotNullOrEmpty(location, position)) {
						if ("near".equals(location)) {
							if(!isNullOrEmpty(position)){
								String adressPos[]=position.split(",");
								if(!isNullOrEmpty(adressPos)&&adressPos.length>1){
									// 封装用户所在地点坐标
									addressPosX=isNullOrEmpty(adressPos[0])?null:Double.valueOf(adressPos[0]);
									addressPosY=isNullOrEmpty(adressPos[1])?null:Double.valueOf(adressPos[1]);
									if(!Utils.isAllNotNullOrEmpty(addressPosX,addressPosY)){
										addressPosX=null;
										addressPosY=null;
									}
								}
							}
						} else if ("city".equals(location)) {
							isCurrentCity=1;
						} else if ("other".equals(location)) {
							isCurrentCity=0;
						}
					}
					if(!Utils.isNullOrEmpty(industry)) {
						industry = meetingDict.getDictKey(industry, MeetingDict.INDUSTRY);
						if(!Utils.isNullOrEmpty(industry)) {
							industry = "%," + industry + ",%";
						}
					}
					if(industry!=null && industry.trim().length()==0) {
						industry = null;
					}
					if(!Utils.isNullOrEmpty(keyword)) {
						keyword = "%" + keyword + "%";
					} else {
						keyword = null;
					}
					/***
					 * 获取会议广场条数
					 */
					count=meetingService
							.getMyMeetingSquareCount(
									Long.valueOf(memberIdStr),
									beginDate, endDate, isCurrentCity,position,
									addressPosX,addressPosY,industry, keyword);
					/***
					 * 获取会议广场
					 */
					listMeetingMemberQuery = meetingService
							.getMyMeetingSquare(
									Long.valueOf(memberIdStr),
									beginDate, endDate, index,
									size,isCurrentCity,position,addressPosX,addressPosY,industry, keyword);
					if(!Utils.isNullOrEmpty(listMeetingMemberQuery)) {
						List<Long> meetingIdList = new ArrayList<Long>();
						for(MeetingMemberListQuery meetingMemberListQuery : listMeetingMemberQuery) {
							meetingIdList.add(meetingMemberListQuery.getId());
						}
						Map<String, List<MeetingTopic>> meetingTopicMap = new HashMap<String, List<MeetingTopic>>();
						List<MeetingTopic> meetingTopicList = meetingTopicService.getByMeetingIds(meetingIdList);
						if(!Utils.isNullOrEmpty(meetingTopicList)) {
							for(MeetingTopic meetingTopic : meetingTopicList) {
								List<MeetingTopic> tempMeetingTopicList = meetingTopicMap.get(""+meetingTopic.getMeetingId());
								if(Utils.isNullOrEmpty(tempMeetingTopicList)) {
									tempMeetingTopicList = new ArrayList<MeetingTopic>();
								}
								tempMeetingTopicList.add(meetingTopic);
								meetingTopicMap.put(""+meetingTopic.getMeetingId(), tempMeetingTopicList);
							}
						}
						for(MeetingMemberListQuery meetingMemberListQuery : listMeetingMemberQuery) {
							String key = ""+meetingMemberListQuery.getId();
							List<MeetingTopic> listMeetingTopic = meetingTopicMap.get(key);
							if(!Utils.isNullOrEmpty(listMeetingTopic)) {
								meetingMemberListQuery.setListMeetingTopic(listMeetingTopic);
							}
						}
					}
				}else{
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
				}
				page.put("listMeetingMemberListQuery", listMeetingMemberQuery);
			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				logger.error("操作异常");
				e.printStackTrace();
			}
			page.put("total",count);
			responseDataMap.put("page",page);
		}
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello mobile app!");
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	public Map<String, Object> getMeetingSquare4Android(HttpServletRequest request,
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
		Map<String, Object> page = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		Integer count=0;
		// 封装会议广场
		List<MeetingMemberListQuery> listMeetingMemberQuery = new ArrayList<MeetingMemberListQuery>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 查询类型
				String type = getStringJsonValueByKey(j, "type");
				// 查询页数
				String indexStr = getStringJsonValueByKey(j, "index");
				Integer index=isNullOrEmpty(indexStr)?null:Integer.valueOf(indexStr);
				// 查询条数
				String sizeStr = getStringJsonValueByKey(j, "size");
				Integer size=isNullOrEmpty(sizeStr)?null:Integer.valueOf(sizeStr);
				page.put("index",index);
				page.put("size",size);
				// 封装查本市还是其他城市
				Integer isCurrentCity=null;
				String position=null;
				// 用户所在地点经纬度坐标
				Double addressPosX=null;
				Double addressPosY=null;
				// 是否查找附近的会议
				// 获取用户Id
				String memberIdStr = getStringJsonValueByKey(j, "memberId");
				if (!isNullOrEmpty(memberIdStr)) {

					// 获取查询类型
					// 按时间查询
					String dataFlag = getStringJsonValueByKey(j, "data");
					Date beginDate = null;
					// 结束时间
					Date endDate = null;
					if (!isNullOrEmpty(type)) {
						if ("0".equals(type)) {
							// 开始时间
							Calendar calendar = Calendar.getInstance();
							// calendar.set(Calendar.MONTH, 0);
							// calendar.set(Calendar.DAY_OF_MONTH, 1);
							// calendar.set(Calendar.HOUR, 0);
							// calendar.set(Calendar.MINUTE, 0);
							// calendar.set(Calendar.SECOND, 0);
							if (!isNullOrEmpty(dataFlag)) {
								if ("today".equals(dataFlag)) {// 今天
									// 设置今天零点
									calendar.set(Calendar.HOUR_OF_DAY, 0);
									calendar.set(Calendar.MINUTE, 0);
									calendar.set(Calendar.SECOND, 0);
									beginDate = (Date) calendar.getTime()
											.clone();
									// 设置明天零点
									int datPos = calendar
											.get(Calendar.DAY_OF_MONTH);
									calendar.set(Calendar.DAY_OF_MONTH,
											datPos + 1);
									endDate = (Date) calendar.getTime().clone();
								} else if ("tomorrow".equals(dataFlag)) {// 明天
									// 设置名天零点
									calendar.set(Calendar.HOUR_OF_DAY, 0);
									calendar.set(Calendar.MINUTE, 0);
									calendar.set(Calendar.SECOND, 0);
									// 设置后天零点
									int datPos = calendar
											.get(Calendar.DAY_OF_MONTH);
									calendar.set(Calendar.DAY_OF_MONTH,
											datPos + 1);
									beginDate = (Date) calendar.getTime()
											.clone();
									datPos = calendar
											.get(Calendar.DAY_OF_MONTH);
									calendar.set(Calendar.DAY_OF_MONTH,
											datPos + 1);
									endDate = (Date) (Date) calendar.getTime()
											.clone();

								} else if ("week".equals(dataFlag)) {// 本周
									// 获取本周周一
									calendar = Utils
											.getMondayOfThisWeek(calendar);
									calendar.set(Calendar.HOUR_OF_DAY, 0);
									calendar.set(Calendar.MINUTE, 0);
									calendar.set(Calendar.SECOND, 0);
									beginDate = (Date) calendar.getTime()
											.clone();
									// 获取下周周一
									int datPos = calendar
											.get(Calendar.DAY_OF_MONTH);
									calendar.set(Calendar.DAY_OF_MONTH,
											datPos + 7);
									endDate = (Date) calendar.getTime().clone();
								} /*else if ("all".equals(dataFlag)) {// 全部
									beginDate = null;
									// 获取下周周一
									endDate = null;
								}*/ else if ("month".equals(dataFlag)) {// 本月
									// 获取本月1号
									calendar.set(Calendar.DAY_OF_MONTH, 1);
									calendar.set(Calendar.HOUR_OF_DAY, 0);
									calendar.set(Calendar.MINUTE, 0);
									calendar.set(Calendar.SECOND, 0);
									beginDate = (Date) calendar.getTime()
											.clone();
									// 获取下月1号
									int datPos = calendar.get(Calendar.MONTH);
									calendar.set(Calendar.MONTH, datPos + 1);
									endDate = (Date) calendar.getTime().clone();
								}
								// 按时间查询会议广场
							}
						} else if ("1".equals(type)) {// 按地点查询
							 position = getStringJsonValueByKey(j, "position");
							if (Utils.isAllNotNullOrEmpty(dataFlag, position)) {
								if ("near".equals(dataFlag)) {
									if(!isNullOrEmpty(position)){
										String adressPos[]=position.split(",");
										if(!isNullOrEmpty(adressPos)&&adressPos.length>1){
											// 封装用户所在地点坐标
											addressPosX=isNullOrEmpty(adressPos[0])?null:Double.valueOf(adressPos[0]);
											addressPosY=isNullOrEmpty(adressPos[1])?null:Double.valueOf(adressPos[1]);
											if(!Utils.isAllNotNullOrEmpty(addressPosX,addressPosY)){
												addressPosX=null;
												addressPosY=null;
											}
										}
									}
								} else if ("city".equals(dataFlag)) {
									isCurrentCity=1;
								}else if ("other".equals(dataFlag)) {
									isCurrentCity=0;
								}
							} 
						}
					}
					/***
					 * 获取会议广场条数
					 */
					count=meetingService
							.getMyMeetingSquareCount(
									Long.valueOf(memberIdStr),
									beginDate, endDate, isCurrentCity,position,
									addressPosX,addressPosY, null, null);
					/***
					 * 获取会议广场
					 */
					listMeetingMemberQuery = meetingService
							.getMyMeetingSquare(
									Long.valueOf(memberIdStr),
									beginDate, endDate, index,
									size,isCurrentCity,position,addressPosX,addressPosY, null, null);
					
				}else{
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
				}
				page.put("listMeetingMemberListQuery", listMeetingMemberQuery);
			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				logger.error("操作异常");
				e.printStackTrace();
			}
			page.put("total",count);
			responseDataMap.put("page",page);
		}
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello mobile app!");
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: getMyMeetingGet 描述: 获取的会议列表
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/MyMeeting.json", method = RequestMethod.GET)
	public Map<String, Object> getMyMeetingGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = getMyMeeting(request, response);
		return model;
	}
	/**
	 * 名称: getMyMeeting 
	 * 描述: 我的会议（“我”- “我的会议”）
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/MyMeeting.json", method = RequestMethod.POST)
	public Map<String, Object> getMyMeeting(HttpServletRequest request, HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		List<MeetingMemberListQuery> listMeetingMemberListQuery = null;
		Long total = 0L;
		Long indexNum = 0L;
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 获取用户Id
				String memberId = getStringJsonValueByKey(j, "memberId");
				String index = getStringJsonValueByKey(j, "index");
				String size = getStringJsonValueByKey(j, "size");
				//关键字查询
				String keyword = getStringJsonValueByKey(j, "keyword");
				//类型查询 0：全查 1：我的会议 2：人脉 3：组织 4：知识 5：事件 6：笔记 
				String type = getStringJsonValueByKey(j, "type");
				//参会人员类型 0:全部 1:我创建的 2:我参加的
				String memberType = getStringJsonValueByKey(j, "memberType");
				type = Utils.isNullOrEmpty(type) ? "0" : type;
				if (!isNullOrEmpty(memberId)) {
					if (!isNullOrEmpty(index) && !isNullOrEmpty(size)){
						indexNum = Long.parseLong(index);
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("memberId", memberId);
						if(!Utils.isNullOrEmpty(keyword)) {
							keyword = "%" + keyword + "%";
						} else {
							keyword = null;
						}
						param.put("keyword", keyword);
						param.put("type", type);
						param.put("memberType", memberType);
						total = meetingService.getMyAttendAndCreateMeetingCount(param);
						param.put("startRow", Integer.parseInt(index)*Integer.parseInt(size));
						param.put("size", Integer.parseInt(size));
						listMeetingMemberListQuery = meetingService.getMyAttendAndCreateMeeting(param);
						List<Long> meetingIdList = new ArrayList<Long>();
						if(!Utils.isNullOrEmpty(listMeetingMemberListQuery)) {
							for(MeetingMemberListQuery meetingMemberListQuery : listMeetingMemberListQuery) {
								if(!Utils.isNullOrEmpty(meetingMemberListQuery)
										&& !Utils.isNullOrEmpty(meetingMemberListQuery.getId())) {
									meetingIdList.add(meetingMemberListQuery.getId());
								}
							}
						}
						if(!Utils.isNullOrEmpty(meetingIdList)) {
							//封装会议相关的人脉
							Map<String, List<MeetingPeople>> meetingPeopleMap = new HashMap<String, List<MeetingPeople>>();
							if(MY_MEETING_SEARCH_TYPE_ALL.equals(type)
									|| MY_MEETING_SEARCH_TYPE_PEOPLE.equals(type)) {
								List<MeetingPeople> meetingPeopleList = meetingPeopleService.getByMeetingIdList(meetingIdList);
								if(!Utils.isNullOrEmpty(meetingPeopleList)) {
									for(MeetingPeople meetingPeople : meetingPeopleList) {
										List<MeetingPeople> tempMeetingPeopleList = meetingPeopleMap.get(""+meetingPeople.getMeetingId());
										if(Utils.isNullOrEmpty(tempMeetingPeopleList)) {
											tempMeetingPeopleList = new ArrayList<MeetingPeople>();
										}
										tempMeetingPeopleList.add(meetingPeople);
										meetingPeopleMap.put(""+meetingPeople.getMeetingId(), tempMeetingPeopleList);
									}
								}
							}
							//封装会议相关的组织
							Map<String, List<MeetingOrgan>> meetingOrganMap = new HashMap<String, List<MeetingOrgan>>();
							if(MY_MEETING_SEARCH_TYPE_ALL.equals(type)
									|| MY_MEETING_SEARCH_TYPE_ORGAN.equals(type)) {
								List<MeetingOrgan> meetingOrganList = meetingOrganService.getByMeetingIdList(meetingIdList);
								if(!Utils.isNullOrEmpty(meetingOrganList)) {
									for(MeetingOrgan meetingOrgan : meetingOrganList) {
										List<MeetingOrgan> tempMeetingOrganList = meetingOrganMap.get(""+meetingOrgan.getMeetingId());
										if(Utils.isNullOrEmpty(tempMeetingOrganList)) {
											tempMeetingOrganList = new ArrayList<MeetingOrgan>();
										}
										tempMeetingOrganList.add(meetingOrgan);
										meetingOrganMap.put(""+meetingOrgan.getMeetingId(), tempMeetingOrganList);
									}
								}
							}
							//封装会议相关的需求、知识
							Map<String, List<MeetingData>> meetingDataMap = new HashMap<String, List<MeetingData>>();
							if(MY_MEETING_SEARCH_TYPE_ALL.equals(type)
									|| MY_MEETING_SEARCH_TYPE_KNOWLEDGE.equals(type)
									|| MY_MEETING_SEARCH_TYPE_REQUIREMENT.equals(type)) {
								List<MeetingData> meetingDataList = meetingDataService.getByMeetingIdList(meetingIdList);
								if(!Utils.isNullOrEmpty(meetingDataList)) {
									for(MeetingData meetingData : meetingDataList) {
										List<MeetingData> tempMeetingDataList = meetingDataMap.get(""+meetingData.getMeetingId());
										if(Utils.isNullOrEmpty(tempMeetingDataList)) {
											tempMeetingDataList = new ArrayList<MeetingData>();
										}
										tempMeetingDataList.add(meetingData);
										meetingDataMap.put(""+meetingData.getMeetingId(), tempMeetingDataList);
									}
								}
							}
							//封装会议相关的笔记
							Map<String, List<MeetingNoteQuery>> meetingNoteMap = new HashMap<String, List<MeetingNoteQuery>>();
							if(MY_MEETING_SEARCH_TYPE_ALL.equals(type)
									|| MY_MEETING_SEARCH_TYPE_NOTE.equals(type)) {
								List<MeetingNote> meetingNoteList = meetingNoteService.getByMeetingIdList(meetingIdList, memberId);
								if(!Utils.isNullOrEmpty(meetingNoteList)) {
									for(MeetingNote meetingNote : meetingNoteList) {
										List<MeetingNoteQuery> tempMeetingNoteQueryList = meetingNoteMap.get(""+meetingNote.getMeetingId());
										if(Utils.isNullOrEmpty(tempMeetingNoteQueryList)) {
											tempMeetingNoteQueryList = new ArrayList<MeetingNoteQuery>();
										}
										MeetingNoteQuery meetingNoteQuery = meetingNoteService.getNoteAndDetailtById(meetingNote.getId());
										tempMeetingNoteQueryList.add(meetingNoteQuery);
										meetingNoteMap.put(""+meetingNote.getMeetingId(), tempMeetingNoteQueryList);
									}
								}
							}
							Map<String, List<MeetingTopic>> meetingTopicMap = new HashMap<String, List<MeetingTopic>>();
							List<MeetingTopic> meetingTopicList = meetingTopicService.getByMeetingIds(meetingIdList);
							if(!Utils.isNullOrEmpty(meetingTopicList)) {
								for(MeetingTopic meetingTopic : meetingTopicList) {
									List<MeetingTopic> tempMeetingTopicList = meetingTopicMap.get(""+meetingTopic.getMeetingId());
									if(Utils.isNullOrEmpty(tempMeetingTopicList)) {
										tempMeetingTopicList = new ArrayList<MeetingTopic>();
									}
									tempMeetingTopicList.add(meetingTopic);
									meetingTopicMap.put(""+meetingTopic.getMeetingId(), tempMeetingTopicList);
								}
							}
							if(!Utils.isNullOrEmpty(listMeetingMemberListQuery)) {
								for(MeetingMemberListQuery meetingMemberListQuery : listMeetingMemberListQuery) {
									if(!Utils.isNullOrEmpty(meetingMemberListQuery)
											&& !Utils.isNullOrEmpty(meetingMemberListQuery.getId())) {
										String key = ""+meetingMemberListQuery.getId();
										//人脉
										List<MeetingPeople> listMeetingPeople = meetingPeopleMap.get(key);
										if(!Utils.isNullOrEmpty(listMeetingPeople)) {
											meetingMemberListQuery.setListMeetingPeople(listMeetingPeople);
										}
										//组织
										List<MeetingOrgan> listMeetingOrgan = meetingOrganMap.get(key);
										if(!Utils.isNullOrEmpty(listMeetingOrgan)) {
											meetingMemberListQuery.setListMeetingOrgan(listMeetingOrgan);
										}
										//需求、知识
										List<MeetingData> listMeetingDate = meetingDataMap.get(key);
										if(!Utils.isNullOrEmpty(listMeetingDate)) {
											meetingMemberListQuery.setListMeetingData(listMeetingDate);
										}
										//笔记
										List<MeetingNoteQuery> listMeetingNoteQuery = meetingNoteMap.get(key);
										if(!Utils.isNullOrEmpty(listMeetingNoteQuery)) {
											meetingMemberListQuery.setListMeetingNoteQuery(listMeetingNoteQuery);
										}
										//议题
										List<MeetingTopic> listMeetingTopic = meetingTopicMap.get(key);
										if(!Utils.isNullOrEmpty(listMeetingTopic)) {
											meetingMemberListQuery.setListMeetingTopic(listMeetingTopic);
										}
									}
								}
							}
						}
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello mobile app!");
					}else{
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "分页信息不完整");
					}
				} else {
					logger.error("用户为空");
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "用户为空");
				}
			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				logger.error("操作异常");
				e.printStackTrace();
			}
		} else {
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "用户信息为空");
		}
		responseDataMap.put("total", total);
		responseDataMap.put("index", indexNum);
		responseDataMap.put("listMeetingMemberListQuery", listMeetingMemberListQuery);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: getMyForwardingMeetingGet 
	 * 描述: 获取我的可转发啊的会议列表
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMyForwardingMeeting.json", method = RequestMethod.GET)
	public Map<String, Object> getMyForwardingMeetingGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = getMyForwardingMeeting(request, response);
		return model;
	}
	/**
	 * 名称: getMyForwardingMeeting 
	 * 描述: 获取我的可转发的会议列表
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMyForwardingMeeting.json", method = RequestMethod.POST)
	public Map<String, Object> getMyForwardingMeeting(HttpServletRequest request, HttpServletResponse response){
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		List<MeetingQuery> listMeetingQuery=new ArrayList<MeetingQuery>();
		User user=getUser(request);
		try {
			listMeetingQuery = meetingService.getMyForwardingMeeting(user.getId());
			notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello App");
		} catch (Exception e) {
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
			logger.error("", e);
		}
		responseDataMap.put("listMeetingQuery", listMeetingQuery);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 删除我的邀请函
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMyInvitation.json", method = RequestMethod.GET)
	public Map<String, Object> deleteMyInvitationGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = deleteMyInvitation(request, response);
		return model;
	}
	/**
	 * 删除我的邀请函
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMyInvitation.json", method = RequestMethod.POST)
	public Map<String, Object> deleteMyInvitation(HttpServletRequest request, HttpServletResponse response){
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
		try {
			JSONObject j = JSONObject.fromObject(requestJson);
			String meetingId = getStringJsonValueByKey(j, "meetingId");
			String memberId = getStringJsonValueByKey(j, "memberId");
			if(!Utils.isNullOrEmpty(meetingId) && !Utils.isNullOrEmpty(memberId)) {
				List<MeetingMember> listMeetingMember =
						meetingMemberService.getByMeetingIdAndMemberId(Long.parseLong(meetingId), Long.parseLong(memberId));
				if(!Utils.isNullOrEmpty(listMeetingMember)) {
					for(MeetingMember meetingMember : listMeetingMember) {
						if(!Utils.isNullOrEmpty(meetingMember)) {
							meetingMember.setIsShowInvitation(0L);
							meetingMemberService.saveOrUpdate(meetingMember);
						}
					}
				}
				responseDataMap.put("succeed", "true");
				notificationMap.put("notifCode", "0001");
				notificationMap.put("notifInfo", "hello App");
			} else {
				responseDataMap.put("succeed", "false");
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数不正确");
			}
		} catch (Exception e) {
			responseDataMap.put("succeed", "false");
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", e.getMessage());
			e.printStackTrace();
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 删除我的邀请函
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMyInvitationBatch.json", method = RequestMethod.GET)
	public Map<String, Object> deleteMyInvitationBatchGet(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, Object> model = deleteMyInvitationBatch(request, response);
		return model;
	}
	/**
	 * 删除我的邀请函
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMyInvitationBatch.json", method = RequestMethod.POST)
	public Map<String, Object> deleteMyInvitationBatch(HttpServletRequest request, HttpServletResponse response){
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
		if(!Utils.isNullOrEmpty(user)
				&& !Utils.isNullOrEmpty(user.getId())) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				String ids = getStringJsonValueByKey(j, "id");
				if(!Utils.isNullOrEmpty(ids)) {
					String[] idArr = ids.split(",");
					List<String> idList = new ArrayList<String>();
					List<Long> memberIdList = new ArrayList<Long>();
					for(String id : idArr) {
						idList.add(id);
						memberIdList.add(Long.parseLong(id));
					}
					List<MeetingMember> listMeetingMember = meetingMemberService.getByIdList(memberIdList);
					if(!Utils.isNullOrEmpty(listMeetingMember)) {
						for(MeetingMember meetingMember : listMeetingMember) {
							if(!Utils.isNullOrEmpty(meetingMember)
									&& meetingMember.getIsShowInvitation().intValue() != 0) {
								meetingMember.setIsShowInvitation(0L);
								meetingMemberService.saveOrUpdate(meetingMember);
							}
						}
					}
					responseDataMap.put("succeed", "true");
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello App");
				} else {
					responseDataMap.put("succeed", "false");
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数不正确");
				}
			} catch (Exception e) {
				responseDataMap.put("succeed", "false");
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				logger.error("删除我的邀请函失败", e);
			}
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
