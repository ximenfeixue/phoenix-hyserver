package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseController;

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

import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.service.meeting.MeetingDataService;


/**
 * @Description: Controller
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/data")
public class MeetingDataController extends BaseController {
	@Autowired
	private MeetingDataService meetingDataService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 名称: getMeetingDataListGet
     * 描述: 获取会议资料
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingDataList.json", method = RequestMethod.GET)
	public Map<String, Object> getMeetingDataListGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getMeetingDataList(request, response);
		return model;
	}

	/**
	 * 名称: getMeetingDataList
     * 描述: 获取会议资料
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingDataList.json", method = RequestMethod.POST)
	public Map<String, Object> getMeetingDataList(HttpServletRequest request,
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
		Map<String, Object> DataMap = new HashMap<String, Object>();

		if (!isNullOrEmpty(requestJson)) {
			try {
			JSONObject j = JSONObject.fromObject(requestJson);
			// 获取会议id
			String meetingIdStr=getStringJsonValueByKey(j, "meetingId");
			if(!isNullOrEmpty(meetingIdStr)){
				Long meetingId=Long.valueOf(meetingIdStr);
				/** 获取会议对应的需求列表 **/
				List<MeetingData> listData=meetingDataService.getRequirementByMeetingId(meetingId);
				/** 获取会议对应的需求列表 **/
				List<MeetingData> listData2=meetingDataService.getKnowledgeByMeetingId(meetingId);
				DataMap.put("listRequirement", listData);
				DataMap.put("listKnowledge", listData2);
			}else{
				logger.error("会议不存在");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			} else {
				logger.error("参数异常");
		}
		responseDataMap.put("listData", DataMap);
		notificationMap.put("notifCode", "0001");
		notificationMap.put("notifInfo", "hello mobile app!");
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	
	
	@Override
	public Logger getLogger() {
		return logger;
	}
}
