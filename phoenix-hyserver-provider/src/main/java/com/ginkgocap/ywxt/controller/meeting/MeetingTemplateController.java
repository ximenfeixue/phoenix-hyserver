package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
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
import com.ginkgocap.ywxt.model.meeting.MeetingTemplate;
import com.ginkgocap.ywxt.service.meeting.MeetingTemplateService;
import com.ginkgocap.ywxt.utils.Utils;

@Controller
@RequestMapping("/template")
public class MeetingTemplateController extends BaseController {
	@Autowired
	private MeetingTemplateService meetingTemplateService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 保存会议模板
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/save.json", method = RequestMethod.GET)
	public Map<String, Object> saveGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = save(request, response);
		return model;
	}
	@ResponseBody
	@RequestMapping(value = "/save.json", method = RequestMethod.POST)
	public Map<String, Object> save(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("", e);
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		boolean executeResult = false;
		Long id = null;
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				MeetingTemplate meetingTemplate = (MeetingTemplate)JSONObject.toBean(j, MeetingTemplate.class);
				if (!isNullOrEmpty(meetingTemplate)) {
					//保存会议模板
					meetingTemplateService.saveOrUpdate(meetingTemplate);
					executeResult = true;
					id = meetingTemplate.getId();
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app!");
				} else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "数据不完整");
				}

			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				logger.error("", e);
			}
		} else {
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
		}
		responseDataMap.put("succeed", executeResult);
		responseDataMap.put("id", id);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 根据用户ID查找模板
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getByUserId.json", method = RequestMethod.GET)
	public Map<String, Object> getByUserIdGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getByUserId(request, response);
		return model;
	}
	@ResponseBody
	@RequestMapping(value = "/getByUserId.json", method = RequestMethod.POST)
	public Map<String, Object> getByUserId(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("", e);
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		List<MeetingTemplate> listMeetingTemplate = null;
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				// 获取用户Id
				String userIdStr = getStringJsonValueByKey(j, "userId");
				if (!isNullOrEmpty(userIdStr)) {
					Long userId = Long.valueOf(userIdStr);
					// 获取会议中
					listMeetingTemplate = meetingTemplateService.getByUserId(userId);
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app!");
				} else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "userId为空");
				}

			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				logger.error("", e);
			}
		} else {
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
		}
		responseDataMap.put("listMeetingTemplate", listMeetingTemplate);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 删除模板
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.GET)
	public Map<String, Object> deleteGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = delete(request, response);
		return model;
	}
	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public Map<String, Object> delete(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取json参数串
		String requestJson = "";
		try {
			requestJson = getJsonParamStr(request);
		} catch (IOException e) {
			logger.error("", e);
		}
		// 封装 response
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> responseDataMap = new HashMap<String, Object>();
		Map<String, Object> notificationMap = new HashMap<String, Object>();
		boolean executeResult = false;
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				String idStr = getStringJsonValueByKey(j, "id");
				String userIdStr = getStringJsonValueByKey(j, "userId");
				if (!Utils.isNullOrEmpty(idStr)) {
					Long id = Long.valueOf(idStr);
					// 获取会议中
					meetingTemplateService.delete(id);
					executeResult = true;
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app!");
				} else if(!Utils.isNullOrEmpty(userIdStr)) {
					Long userId = Long.valueOf(userIdStr);
					// 获取会议中
					meetingTemplateService.deleteByUserId(userId);
					executeResult = true;
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello mobile app!");
				} else {
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "参数异常");
				}

			} catch (Exception e) {
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				logger.error("", e);
			}
		} else {
			notificationMap.put("notifCode", "0002");
			notificationMap.put("notifInfo", "参数异常");
		}
		responseDataMap.put("succeed", executeResult);
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	
	@Override
	public Logger getLogger() {
		return logger;
	}
}