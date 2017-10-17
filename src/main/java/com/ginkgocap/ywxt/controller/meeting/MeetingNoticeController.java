package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ginkgocap.ywxt.common.base.BaseController;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.service.meeting.MeetingNoticeService;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.service.meeting.MeetingSignLabelDataService;
import com.ginkgocap.ywxt.service.meeting.NoticeFieldService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.Constant;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoticeQuery;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoticeRelation;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignLabelDataQuery;


/**
 * @Description: Controller
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/notice")
public class MeetingNoticeController extends BaseController {
	@Autowired
	private MeetingNoticeService meetingNoticeService;
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private NoticeFieldService noticeFieldService;
	@Autowired
	private MeetingSignLabelDataService meetingSignLabelDataService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 名称: getNoticeList
     * 描述: 获取通知列表
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoticeList.json", method = RequestMethod.GET)
	public Map<String, Object> getNoticeListGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getNoticeList(request, response);
		return model;
	}

	/**
	 * 名称: getNoticeList
     * 描述: 获取通知列表
	 * @param request 请求
	 * @param response 响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoticeList.json", method = RequestMethod.POST)
	public Map<String, Object> getNoticeList(HttpServletRequest request,
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
		List<Long> noticeIds = new ArrayList<Long>();
		User user=getUser(request);
		if(!isNullOrEmpty(user)&&!isNullOrEmpty(user.getId())){
			if (!isNullOrEmpty(requestJson)) {
				try {
					JSONObject j = JSONObject.fromObject(requestJson);
					// json 转为对象
					String memberIdStr = getStringJsonValueByKey(j, "memberId");
					String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
					Long memberId = Long.valueOf(memberIdStr);
					if(!isNullOrEmpty(memberIdStr)){
						List<MeetingNoticeRelation> list = meetingNoticeService.getMeetingNoticeRelation(memberId);
						if(!Utils.isNullOrEmpty(meetingIdStr) && !Utils.isNullOrEmpty(list)) {
							List<MeetingNoticeRelation> newList = new ArrayList<MeetingNoticeRelation>();
							for(MeetingNoticeRelation meetingNoticeRelation : list) {
								if(meetingIdStr.equals(""+meetingNoticeRelation.getMeetingId())) {
									newList.add(meetingNoticeRelation);
									break;
								}
							}
							list = newList;
						}
						List<MeetingNoticeRelation> listResult=new ArrayList<MeetingNoticeRelation>();
						if(!isNullOrEmpty(list)){
							for(MeetingNoticeRelation meetingNoticeRelation:list){
								if(!isNullOrEmpty(meetingNoticeRelation)&&!isNullOrEmpty(meetingNoticeRelation.getMeetingId())){
									TreeSet<MeetingNoticeQuery> setNoticeQuery=new TreeSet<MeetingNoticeQuery>();
									Meeting meeting=meetingService.getById(meetingNoticeRelation.getMeetingId());
									
									meetingNoticeRelation.setGroupId(meeting.getGroupId());
									// receiverType: 0会议创建者，1普通参会人
									Integer receiverType=memberId.equals(meeting.getCreateId())?0:1;
									List<MeetingNotice> listNotice=meetingNoticeService.getMyNoticeByMeetingIdAndReceiverType(memberId, meetingNoticeRelation.getMeetingId(), receiverType);
									List<Long> listUserId = new ArrayList<Long>();
									for(MeetingNotice meetingNotice : listNotice) {
										if(!Utils.isNullOrEmpty(meetingNotice)
												&& !Utils.isNullOrEmpty(meetingNotice.getCreateId())) {
											listUserId.add(meetingNotice.getCreateId());
										}
									}
									Map<String, User> userMap = meetingNoticeService.getUserMap(listUserId);
									// 封装通知信息
									for(MeetingNotice meetingNotice:listNotice){
										MeetingNoticeQuery meetingNoticeQuery=new MeetingNoticeQuery();
										meetingNoticeQuery.copyFromMeetingNotice(meetingNotice);
										//设置创建人性别
										User creater = userMap.get(""+meetingNotice.getCreateId());
										if(!Utils.isNullOrEmpty(creater)) {
											meetingNoticeQuery.setCreateName(creater.getName());
											meetingNoticeQuery.setCreatePic(creater.getPicPath());
											meetingNoticeQuery.setCreateSex(""+creater.getSex());
										}
										List<String> listField=noticeFieldService.getByNoticeId(meetingNotice.getId());
										// 创建者通知列表 receiverType==0
										switch (meetingNotice.getNoticeType()) {
										case 0:// 修改会议通知
											// 获取通知内容
											meetingNoticeQuery.setListMeetingField(listField);
											break;
										case 1:
											if(receiverType!=0)break;
											if(!isNullOrEmpty(listField)){
												// 报名申请通知
												for(String field:listField){
													if(!isNullOrEmpty(field)){
														String [] fieldArray=field.split(Constant.NOTICE_CONTENT_SPLIT_CHAR);
														if(fieldArray.length>=2){
															// 获取报名人ID
															Long signUperId=isNullOrEmpty(fieldArray[0])?0l:Long.valueOf(fieldArray[0].trim());
															// 获取报名人名字
															String signUperName=isNullOrEmpty(fieldArray[1])?"":fieldArray[1].trim();
															meetingNoticeQuery.setAttendMeetingId(signUperId);
															meetingNoticeQuery.setAttendMeetingName(signUperName);
															// 获取报名必填信息
															List<MeetingSignLabelDataQuery> listLabelContent=meetingSignLabelDataService.getByMeetingIdAndMemberId(meetingNotice.getMeetingId(), signUperId);
															meetingNoticeQuery.setListMeetingSignLabelDataQuery(listLabelContent);
														}
													}
												}
											}
											break;
										case 2:
											// 发起人同意你参会
											if(receiverType!=1) {
												break;
											}
											meetingNoticeQuery.setMeetingCreateName(meetingNoticeRelation.getMeetingCreateName());
											break;
										case 3:
											// 发起人拒绝你参会
											if(receiverType!=1) {
												break;
											}
											meetingNoticeQuery.setMeetingCreateName(meetingNoticeRelation.getMeetingCreateName());
											break;
										case 4:
											if(receiverType!=0) {
												break;
											}
											// 接受邀请通知
											if(!isNullOrEmpty(listField)){
												for(String field:listField){
													if(!isNullOrEmpty(field)){
														meetingNoticeQuery.setAttendMeetingName(field);
														break;
													}
												}
											}
											break;	
										case 5:
											if(receiverType!=0) {
												break;
											}
											// 拒绝邀请通知
											if(!isNullOrEmpty(listField)){
												for(String field:listField){
													if(!isNullOrEmpty(field)){
														meetingNoticeQuery.setAttendMeetingName(field);
														break;
													}
												}
											}
											break;	
										case 6:
											if(receiverType!=0) {
												break;
											}
											// 你同意参会人通知
											if(!isNullOrEmpty(listField)){
												for(String field:listField){
													if(!isNullOrEmpty(field)){
														String [] fieldArray=field.split(Constant.NOTICE_CONTENT_SPLIT_CHAR);
														if(fieldArray.length>=2){
															// 获取报名人ID
															Long signUperId=isNullOrEmpty(fieldArray[0])?0l:Long.valueOf(fieldArray[0].trim());
															// 获取报名人名字
															String signUperName=isNullOrEmpty(fieldArray[1])?"":fieldArray[1].trim();
															meetingNoticeQuery.setAttendMeetingId(signUperId);
															meetingNoticeQuery.setAttendMeetingName(signUperName);
															// 获取报名必填信息
															List<MeetingSignLabelDataQuery> listLabelContent=meetingSignLabelDataService.getByMeetingIdAndMemberId(meetingNotice.getMeetingId(), signUperId);
															meetingNoticeQuery.setListMeetingSignLabelDataQuery(listLabelContent);
															break;
														}
													}
												}
											}
											break;
										case 7:
											if(receiverType!=0) {
												break;
											}
											// 你拒绝参会人通知
											if(!isNullOrEmpty(listField)){
												for(String field:listField){
													if(!isNullOrEmpty(field)){
														String [] fieldArray=field.split(Constant.NOTICE_CONTENT_SPLIT_CHAR);
														if(fieldArray.length>=2){
															// 获取报名人ID
															Long signUperId=isNullOrEmpty(fieldArray[0])?0l:Long.valueOf(fieldArray[0].trim());
															// 获取报名人名字
															String signUperName=isNullOrEmpty(fieldArray[1])?"":fieldArray[1].trim();
															meetingNoticeQuery.setAttendMeetingId(signUperId);
															meetingNoticeQuery.setAttendMeetingName(signUperName);
															// 获取报名必填信息
															List<MeetingSignLabelDataQuery> listLabelContent=meetingSignLabelDataService.getByMeetingIdAndMemberId(meetingNotice.getMeetingId(), signUperId);
															meetingNoticeQuery.setListMeetingSignLabelDataQuery(listLabelContent);
															break;
														}
													}
												}
											}
											break;
										}
										// 多条显示的通知此处不添加
										meetingNoticeQuery.setMeetingCreateName(meetingNoticeRelation.getMeetingCreateName());
										noticeIds.add(meetingNoticeQuery.getId());
										setNoticeQuery.add(meetingNoticeQuery);
									}
									meetingNoticeRelation.setSetMeetingNoticeQuery(setNoticeQuery);
								}
								listResult.add(meetingNoticeRelation);
								if (noticeIds.size() > 1) {
									meetingNoticeService.updateMeetingIdRead(noticeIds);
								}
							}
						}
						responseDataMap.put("listMeetingNoticeRelation",listResult);
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello App!");	
					}
				} catch (Exception e) {
					responseDataMap.put("listMeetingNoticeRelation",null);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", e.getMessage());
					e.printStackTrace();
				}
			} else {
				responseDataMap.put("listMeetingNoticeRelation",null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数错误");
			}
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 删除通知
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNotice.json", method = RequestMethod.GET)
	public Map<String, Object> deleteNoticeGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = deleteNotice(request, response);
		return model;
	}
	/**
	 * 删除通知
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNotice.json", method = RequestMethod.POST)
	public Map<String, Object> deleteNotice(HttpServletRequest request,
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
		User user=getUser(request);
		if(!isNullOrEmpty(user)&&!isNullOrEmpty(user.getId())){
			if (!isNullOrEmpty(requestJson)) {
				try {
					JSONObject j = JSONObject.fromObject(requestJson);
					// json 转为对象
					String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
					String noticeIdStr = getStringJsonValueByKey(j, "noticeId");
					if(!Utils.isNullOrEmpty(meetingIdStr)) {
						String[] meetingIdArr = meetingIdStr.split(",");
						List<Long> meetingIdList = new ArrayList<Long>();
						for(String meetingId : meetingIdArr) {
							if(!Utils.isNullOrEmpty(meetingId)) {
								meetingIdList.add(Long.parseLong(meetingId));
							}
						}
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("userId", user.getId());
						param.put("isShow", "1");
						param.put("meetingIdList", meetingIdList);
						List<MeetingNotice> listMeetingNotice = meetingNoticeService.getByParam(param);
						if(!Utils.isNullOrEmpty(listMeetingNotice)) {
							for(MeetingNotice meetingNotice : listMeetingNotice) {
								if(!Utils.isNullOrEmpty(meetingNotice)) {
									meetingNotice.setIsShow(0);
									meetingNoticeService.saveOrUpdate(meetingNotice);
								}
							}
						}
						responseDataMap.put("succeed", "true");
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello App!");
					} else if(!Utils.isNullOrEmpty(noticeIdStr)) {
						MeetingNotice meetingNotice = meetingNoticeService.getById(Long.valueOf(noticeIdStr));
						if(!Utils.isNullOrEmpty(meetingNotice)) {
							if(meetingNotice.getIsShow().intValue() == 1) {
								meetingNotice.setIsShow(0);
								meetingNoticeService.saveOrUpdate(meetingNotice);
								responseDataMap.put("succeed", "true");
								notificationMap.put("notifCode", "0001");
								notificationMap.put("notifInfo", "hello App!");
							} else {
								responseDataMap.put("succeed", "false");
								notificationMap.put("notifCode", "0002");
								notificationMap.put("notifInfo", "通知已删除，请不要重复提交");
							}
						} else {
							responseDataMap.put("succeed", "false");
							notificationMap.put("notifCode", "0002");
							notificationMap.put("notifInfo", "该会议没有通知");
						}
					} else {
						responseDataMap.put("succeed", "false");
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "参数错误");
					}
				} catch(Exception e) {
					logger.error("删除会议通知失败", e);
				}
			}
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 更新通知
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/updateNoticeReadState.json", method = RequestMethod.GET)
	public Map<String, Object> updateNoticeReadStateGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = updateNoticeReadState(request, response);
		return model;
	}
	/**
	 * 更新通知
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateNoticeReadState.json", method = RequestMethod.POST)
	public Map<String, Object> updateNoticeReadState(HttpServletRequest request,
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
		User user=getUser(request);
		if(!isNullOrEmpty(user)&&!isNullOrEmpty(user.getId())){
			if (!isNullOrEmpty(requestJson)) {
				try {
					JSONObject j = JSONObject.fromObject(requestJson);
					// json 转为对象
					String meetingIdStr = getStringJsonValueByKey(j, "meetingId");
					String noticeIdStr = getStringJsonValueByKey(j, "noticeId");
					if(!Utils.isNullOrEmpty(meetingIdStr)) {
						List<MeetingNotice> listMeetingNotice = meetingNoticeService.getByMeetingId(Long.valueOf(meetingIdStr));
						if(!Utils.isNullOrEmpty(listMeetingNotice)) {
							for(MeetingNotice meetingNotice : listMeetingNotice) {
								if(!Utils.isNullOrEmpty(meetingNotice)
										&& meetingNotice.getIsShow().intValue()==1 
										&& user.getId()==meetingNotice.getReceiver().longValue()
										&& meetingNotice.getIsRead().intValue() == 0) {
									meetingNotice.setIsRead(1L);
									meetingNotice.setReadTime(new Date());
									meetingNoticeService.saveOrUpdate(meetingNotice);
								}
							}
							responseDataMap.put("succeed", "true");
							notificationMap.put("notifCode", "0001");
							notificationMap.put("notifInfo", "hello App!");
						}
					} else if(!Utils.isNullOrEmpty(noticeIdStr)) {
						MeetingNotice meetingNotice = meetingNoticeService.getById(Long.valueOf(noticeIdStr));
						if(!Utils.isNullOrEmpty(meetingNotice)) {
							if(meetingNotice.getIsShow().intValue() == 1
									&& meetingNotice.getIsRead().intValue() == 0) {
								meetingNotice.setIsRead(1L);
								meetingNotice.setReadTime(new Date());
								meetingNoticeService.saveOrUpdate(meetingNotice);
								responseDataMap.put("succeed", "true");
								notificationMap.put("notifCode", "0001");
								notificationMap.put("notifInfo", "hello App!");
							} else {
								responseDataMap.put("succeed", "false");
								notificationMap.put("notifCode", "0002");
								notificationMap.put("notifInfo", "通知已删除，请不要重复提交");
							}
						} else {
							responseDataMap.put("succeed", "false");
							notificationMap.put("notifCode", "0002");
							notificationMap.put("notifInfo", "没有这个会议通知");
						}
					} else {
						responseDataMap.put("succeed", "false");
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "参数错误");
					}
				} catch(Exception e) {
					logger.error("删除会议通知失败", e);
				}
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
