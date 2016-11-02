package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ginkgocap.ywxt.model.meeting.MeetingNote;
import com.ginkgocap.ywxt.model.meeting.MeetingNoteDetail;
import com.ginkgocap.ywxt.service.meeting.MeetingNoteDetailService;
import com.ginkgocap.ywxt.service.meeting.MeetingNoteService;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.service.meeting.TopicChatService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingNoteQuery;


/**
 * @Description: Controller
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/note")
public class MeetingNoteController extends BaseController {
	@Autowired
	private MeetingNoteService meetingNoteService;
	@Autowired
	private MeetingNoteDetailService meetingNoteDetailService;
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private TopicChatService topicChatService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 名称: addNoteGet
     * 描述:增加会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/addNote.json", method = RequestMethod.GET)
	public Map<String, Object> addNoteGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = addNote(request, response);
		return model;
	}

	/**
	 * 名称: addNote
     * 描述:增加会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/addNote.json", method = RequestMethod.POST)
	public Map<String, Object> addNote(HttpServletRequest request,
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
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				Map<String, Class> classMap = new HashMap<String, Class>();
				classMap.put("listMeetingNoteDetail", MeetingNoteDetail.class);
				// json 转为对象
				MeetingNoteQuery meetingNoteQuery = (MeetingNoteQuery) JSONObject.toBean((JSONObject) j, MeetingNoteQuery.class,classMap);
				if(isNullOrEmpty(meetingNoteQuery)||isNullOrEmpty(meetingNoteQuery.getMeetingId())){
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
					responseDataMap.put("succeed",false);
				}else{
					Meeting meeting=meetingService.getById(meetingNoteQuery.getMeetingId());
					if(isNullOrEmpty(meeting)){
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议不存在");
						responseDataMap.put("succeed",false);
					}else{
						MeetingNote meetingNote = meetingNoteService.getById(meetingNoteQuery.getId());
						if(isNullOrEmpty(meetingNote)){
							meetingNoteQuery.setCreater(user.getId());
							meetingNoteQuery.setCreateTime(new Date());
						}
						meetingNoteService.saveNote(meetingNoteQuery);
						//重新查询会议笔记
						List<MeetingNoteQuery> listMeetingNoteQuery = meetingNoteService.getByMeetingIdAndMemberId(meetingNoteQuery.getMeetingId(), meetingNoteQuery.getCreater());
						responseDataMap.put("succeed", true);
						responseDataMap.put("meetingNoteQuery", listMeetingNoteQuery.get(0));
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello mobile app!");
					}
				}
			} catch (Exception e) {
				responseDataMap.put("succeed", false);
				responseDataMap.put("meetingNoteQuery", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("参数异常");
			responseDataMap.put("succeed",false);
			responseDataMap.put("meetingNoteQuery", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: addNoteDetailGet
     * 描述:单独增加会议笔记明细
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/addNoteDetail.json", method = RequestMethod.GET)
	public Map<String, Object> addNoteDetailGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = addNoteDetail(request, response);
		return model;
	}

	/**
	 * 名称: addNoteDetail
     * 描述:单独增加会议笔记明细
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/addNoteDetail.json", method = RequestMethod.POST)
	public Map<String, Object> addNoteDetail(HttpServletRequest request,
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
		if (!isNullOrEmpty(requestJson)) {
			try {
			JSONObject j = JSONObject.fromObject(requestJson);
			// json 转为对象
			MeetingNoteDetail noteDetail = (MeetingNoteDetail) JSONObject.toBean(j, MeetingNoteDetail.class,
					new HashMap<String, Class>());
			if(isNullOrEmpty(noteDetail)||isNullOrEmpty(noteDetail.getMeetingNoteId())){
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数异常");
				responseDataMap.put("succeed",false);
			}else{
				MeetingNote meetingNote=meetingNoteService.getById(noteDetail.getMeetingNoteId());
				if(isNullOrEmpty(meetingNote)){
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "会议笔记不存在");
					responseDataMap.put("succeed",false);
				}else{
					noteDetail.setMeetingNoteTime(new Date());
					meetingNoteDetailService.saveOrUpdate(noteDetail);
					// 再查询会议笔记及明细并返回
					MeetingNoteQuery noteQuery = meetingNoteService.getNoteAndDetailtById(noteDetail.getMeetingNoteId());
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app!");
					responseDataMap.put("noteQuery", noteQuery);
					responseDataMap.put("succeed",true);
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
	 * 名称: deleteNoteGet
     * 描述: 删除会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNote.json", method = RequestMethod.GET)
	public Map<String, Object> deleteNoteGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = deleteNote(request, response);
		return model;
	}

	/**
	 * 名称: deleteNote
     * 描述: 删除会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNote.json", method = RequestMethod.POST)
	public Map<String, Object> deleteNote(HttpServletRequest request,
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
			// 查找会议笔记id
			String noteIdStr = j.getString("noteId");
			User user=getUser(request);
			if (!isNullOrEmpty(noteIdStr)) {
			Long noteId=Long.valueOf(noteIdStr);
			MeetingNote meetingNote=meetingNoteService.getById(noteId);
			if(Utils.isNullOrEmpty(meetingNote)){
				logger.error("会议笔记不存在");
				responseDataMap.put("succeed",false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "会议笔记不存在");
			}else {
				if(!isNullOrEmpty(meetingNote.getCreater())&&meetingNote.getCreater()==user.getId()){
					meetingNoteService.delete(noteId);
					responseDataMap.put("succeed",true);
					 notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app!");
				}else{
					logger.error("会议笔记不是当前用户所有，不能删除");
					responseDataMap.put("succeed",false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "会议笔记不是当前用户所有，不能删除在");
				}
			}	
				
		}else{
			logger.error("会议笔记id为空");
			responseDataMap.put("succeed",false);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
		}
			} catch (Exception e) {
				responseDataMap.put("succeed",false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
	}else{
		responseDataMap.put("succeed",false);
	}
		
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: getNoteByIdGet
     * 描述: 查询会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoteById.json", method = RequestMethod.GET)
	public Map<String, Object> getNoteByIdGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getNoteById(request, response);
		return model;
	}

	/**
	 * 名称: getNoteById
     * 描述: 查询会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoteById.json", method = RequestMethod.POST)
	public Map<String, Object> getNoteById(HttpServletRequest request,
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
		MeetingNoteQuery meetingNote=new MeetingNoteQuery();
		if (!isNullOrEmpty(requestJson)) {
			try {
			JSONObject j = JSONObject.fromObject(requestJson);
			// 查找会议笔记id
			String noteIdStr = j.getString("noteId");
			
			if (!isNullOrEmpty(noteIdStr)) {
			Long noteId=Long.valueOf(noteIdStr);
			 meetingNote=meetingNoteService.getNoteAndDetailtById(noteId);
				responseDataMap.put("meetingNoteQuery", meetingNote);
			 notificationMap.put("notifCode", "0001");
			notificationMap.put("notifInfo", "hello mobile app!");
		}else{
			responseDataMap.put("meetingNoteQuery", null);

			logger.error("会议笔记id为空");
			notificationMap.put("notifCode", "0002");
			responseDataMap.put("meetingNoteQuery", null);

			notificationMap.put("notifInfo", "会议笔记id为空");
		}
			} catch (Exception e) {
				logger.error(e.getMessage());
				responseDataMap.put("meetingNoteQuery", null);

				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
	}else{
		responseDataMap.put("meetingNoteQuery", null);

		notificationMap.put("notifCode", "0002");
		notificationMap.put("notifInfo", "参数异常");
		logger.error("参数异常");
	}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: getNoteByMeetingIdGet
     * 描述: 根据会议ID查询会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoteByMeetingId.json", method = RequestMethod.GET)
	public Map<String, Object> getNoteByMeetingIdGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getNoteByMeetingId(request, response);
		return model;
	}
	/**
	 * 名称: getNoteById
     * 描述: 查询会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoteByMeetingId.json", method = RequestMethod.POST)
	public Map<String, Object> getNoteByMeetingId(HttpServletRequest request,
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
				// 查找会议笔记id
				String meetingIdStr = j.getString("meetingId");
				// 获取用户
				User user=getUser(request);
				if (!isNullOrEmpty(meetingIdStr)) {
					Long meetingId=Long.valueOf(meetingIdStr);
					if(isNullOrEmpty(user)||isNullOrEmpty(user.getId())){
						responseDataMap.put("meetingNoteQuery", null);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "请先登录");
					}else{
						List<MeetingNoteQuery> listMeetingNote = meetingNoteService.getNoteAndDetailtByMeetingIdAndCreater(meetingId,user.getId());
						MeetingNoteQuery meetingNoteQuery = new MeetingNoteQuery();
						if(!Utils.isNullOrEmpty(listMeetingNote)) {
							meetingNoteQuery = listMeetingNote.get(0);
						}
						responseDataMap.put("meetingNoteQuery", meetingNoteQuery);
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello mobile app!");
					}
				}else{
					responseDataMap.put("meetingNoteQuery", null);
					logger.error("会议笔记id为空");
					notificationMap.put("notifCode", "0002");
					responseDataMap.put("meetingNoteQuery", null);
					notificationMap.put("notifInfo", "会议笔记id为空");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				responseDataMap.put("meetingNoteQuery", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		}else{
			responseDataMap.put("meetingNoteQuery", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
			logger.error("参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 名称: getNoteByMeetingIdGet
     * 描述: 根据会议ID查询会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoteListByMeetingId.json", method = RequestMethod.GET)
	public Map<String, Object> getNoteListByMeetingIdGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getNoteListByMeetingId(request, response);
		return model;
	}
	/**
	 * 名称: getNoteById
     * 描述: 查询会议笔记
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getNoteListByMeetingId.json", method = RequestMethod.POST)
	public Map<String, Object> getNoteListByMeetingId(HttpServletRequest request,
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
				// 查找会议笔记id
				String meetingIdStr = j.getString("meetingId");
				// 获取用户
				User user=getUser(request);
				if (!isNullOrEmpty(meetingIdStr)) {
					Long meetingId=Long.valueOf(meetingIdStr);
					if(isNullOrEmpty(user)||isNullOrEmpty(user.getId())){
						responseDataMap.put("listMeetingNoteQuery", null);
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "请先登录");
					}else{
						List<MeetingNoteQuery> listMeetingNoteQuery = meetingNoteService.getNoteAndDetailtByMeetingIdAndCreater(meetingId,user.getId());
						responseDataMap.put("listMeetingNoteQuery", listMeetingNoteQuery);
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello mobile app!");
					}
				}else{
					responseDataMap.put("listMeetingNoteQuery", null);
					logger.error("会议笔记id为空");
					responseDataMap.put("listMeetingNoteQuery", null);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "会议笔记id为空");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				responseDataMap.put("listMeetingNoteQuery", null);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
		}else{
			responseDataMap.put("listMeetingNoteQuery", null);
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
			logger.error("参数异常");
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 从聊天页面跳过来的添加会议笔记明细
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author wangfeiliang
	 */
	@ResponseBody
	@RequestMapping(value = "/addNoteDetailByChat.json", method = RequestMethod.GET)
	public Map<String, Object> addNoteDetailByChatGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = addNoteDetailByChatPost(request, response);
		return model;
	}
	/**
	 * 从聊天页面跳过来的添加会议笔记明细
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author wangfeiliang
	 */
	@ResponseBody
	@RequestMapping(value = "/addNoteDetailByChat.json", method = RequestMethod.POST)
	public Map<String, Object> addNoteDetailByChatPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
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
				boolean flag=false;
				if (!isNullOrEmpty(requestJson)) {
					try{
						JSONObject jo=JSONObject.fromObject(requestJson);
						String meetingIdStr=(String) jo.optString("meetingId");
						String messageIdStr=(String) jo.optString("messageId");
						String taskIdStr=(String) jo.get("taskId");
						// 获取用户
						User user=getUser(request);
						if(!isNullOrEmpty(meetingIdStr)&&!isNullOrEmpty(messageIdStr)){
							if(isNullOrEmpty(user)||isNullOrEmpty(user.getId())){
								notificationMap.put("notifCode", "0002");
								notificationMap.put("notifInfo", "请先登录");
							}else{
								Meeting meeting=meetingService.getById(Long.parseLong(meetingIdStr));
								if(isNullOrEmpty(meeting)){
									notificationMap.put("notifCode", "0002");
									notificationMap.put("notifInfo", "会议不存在");
									logger.error("会议不存在");
								}else{
									meetingNoteDetailService.saveMeetingNoteDetail(meetingIdStr,messageIdStr,taskIdStr,user);
									flag=true;
									notificationMap.put("notifCode", "0001");
									notificationMap.put("notifInfo", "hello mobile app!");
								}
							} 
						}else{
							notificationMap.put("notifCode", "0002");
							notificationMap.put("notifInfo", "会议Id或者messageId不能为空");
							logger.error("会议Id或者messageId不能为空");
						}
					}catch(Exception e){
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", e.getMessage().toString());
						e.printStackTrace();
						logger.error(e.getMessage().toString());
					}
				}else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
					logger.error("参数异常");
				}
				responseDataMap.put("succeed",flag);
			model.put("responseData", responseDataMap);
			model.put("notification", notificationMap);
		 return model;
	}
	@Override
	public Logger getLogger() {
		return logger;
	}
}
