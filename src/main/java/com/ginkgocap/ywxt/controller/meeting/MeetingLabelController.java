package com.ginkgocap.ywxt.controller.meeting;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.model.meeting.MeetingLabel;
import com.ginkgocap.ywxt.service.meeting.MeetingLabelService;
import com.ginkgocap.ywxt.user.model.User;


/**
 * @Description: Controller
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/label")
public class MeetingLabelController extends BaseController {
	@Autowired
	private MeetingLabelService meetingLabelService;
	@Autowired
	private FileIndexService fileIndexService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 名称: addMeetingLabelGet
     * 描述: 新增自定义标签
	 * @param request  请求
	 * @param response 响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/addMeetingLabel.json", method = RequestMethod.GET)
	public Map<String, Object> addMeetingLabelGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = addMeetingLabel(request, response);
		return model;
	}

	/**
	 * 名称: addMetting
     * 描述: 新增自定义标签
	 * @param request  请求
	 * @param response 响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/addMeetingLabel.json", method = RequestMethod.POST)
	public Map<String, Object> addMeetingLabel(HttpServletRequest request,
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
			MeetingLabel meetingLabel = (MeetingLabel) JSONObject.toBean(j, MeetingLabel.class);
			List<MeetingLabel> list = meetingLabelService.getByCreateIdAndLabelName(meetingLabel.getCreateId(), meetingLabel.getLabelName());
				if(isNullOrEmpty(list)){
					meetingLabelService.saveOrUpdate(meetingLabel);
					responseDataMap.put("succeed",true);
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello app");
				}else{
					responseDataMap.put("succeed",false);
					notificationMap.put("notifCode", "0002");
					notificationMap.put("notifInfo", "该标签已存在");
				}
			} catch (Exception e) {
				responseDataMap.put("succeed",false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
			} else {
				responseDataMap.put("succeed",false);
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数错误");
		}
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	} 
	/**
	 * 名称: getMeetingLabelByCreateIdGet
     * 描述: 根据用户Id获取所有自定义标签
	 * @param request  请求
	 * @param response 响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingLabelByCreateId.json", method = RequestMethod.GET)
	public Map<String, Object> getMeetingLabelByCreateIdGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = getMeetingLabelByCreateId(request, response);
		return model;
	}

	/**
	 * 名称: getMeetingLabelByCreateId
     * 描述: 根据用户Id获取所有自定义标签
	 * @param request  请求
	 * @param response 响应
	 * @return model
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMeetingLabelByCreateId.json", method = RequestMethod.POST)
	public Map<String, Object> getMeetingLabelByCreateId(HttpServletRequest request,
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
			String memberIdStr = j.getString("memberId");
			// json 转为对象
			if(!isNullOrEmpty(memberIdStr)){
				List<MeetingLabel> list=meetingLabelService.getByCreateId(Long.valueOf(memberIdStr));
					responseDataMap.put("listMeetingLabel",list);
					notificationMap.put("notifCode", "0001");
					notificationMap.put("notifInfo", "hello app");
			}
			} catch (Exception e) {
				responseDataMap.put("listMeetingLabel",new ArrayList<MeetingLabel>());
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
			} else {
				responseDataMap.put("listMeetingLabel",new ArrayList<MeetingLabel>());
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数错误");
		}
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}
	/**
	 * 删除自定义
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMeetingLabel.json", method = RequestMethod.GET)
	public Map<String, Object> deleteMeetingLabelGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> model = addMeetingLabel(request, response);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteMeetingLabel.json", method = RequestMethod.POST)
	public Map<String, Object> deleteMeetingLabel(HttpServletRequest request,
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
		User user=getUser(request);
		if(!isNullOrEmpty(user)&&!isNullOrEmpty(user.getId())){
		
		if (!isNullOrEmpty(requestJson)) {
			try {
				JSONObject j = JSONObject.fromObject(requestJson);
				String labelIdStr = j.getString("labelId");
				// json 转为对象
				if(!isNullOrEmpty(labelIdStr)){
					MeetingLabel meetingLabel = meetingLabelService.getById(Long.valueOf(labelIdStr));
					if(!isNullOrEmpty(meetingLabel)) {
						meetingLabelService.delete(Long.valueOf(labelIdStr));
						responseDataMap.put("succeed", "true");
						notificationMap.put("notifCode", "0001");
						notificationMap.put("notifInfo", "hello app");
					} else {
						responseDataMap.put("succeed", "false");
						notificationMap.put("notifCode", "0002");
						notificationMap.put("notifInfo", "会议标签不存在");
					}
				}
			} catch (Exception e) {
				responseDataMap.put("listMeetingLabel",new ArrayList<MeetingLabel>());
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", e.getMessage());
				e.printStackTrace();
			}
			} else {
				responseDataMap.put("listMeetingLabel",new ArrayList<MeetingLabel>());
				notificationMap.put("notifCode", "0002");
				notificationMap.put("notifInfo", "参数错误");
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
