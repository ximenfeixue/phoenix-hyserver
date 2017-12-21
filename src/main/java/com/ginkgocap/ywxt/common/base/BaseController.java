package com.ginkgocap.ywxt.common.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.ginkgocap.ywxt.controller.meeting.MeetingController;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.util.Encodes;
import com.ginkgocap.ywxt.utils.JsonReadUtil;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.UserBean;


/**
 * controller基类
 * @author qingc
 *
 */
@Controller
public abstract class BaseController {

	public abstract Logger getLogger();
	 /**
     * 判断对象是否为null或空
     * @param obj
     * return IOException
     */
	public static boolean isNullOrEmpty(Object obj){
    	return Utils.isNullOrEmpty(obj);
    }
	 /**
     * 获取head中的json参数串
     * @param request
     * @return result 
     * @throws IOException
     * 
     */
    public String getJsonParamStr(HttpServletRequest request) throws IOException{
        String result=JsonReadUtil.getJsonIn(request);
        User user = getUser(request);
        long userId = user != null ? user.getId() : 0;
        getLogger().info("{request:{url:"+request.getRequestURI()+","+"param:"+result+"}}" + "   请求的userId为：" + userId);
		return result;
    }
    
	/**
	 * 如果为null，设置空值
	 * 
	 * @param str
	 *            待处理对象
	 * @return str
	 */
	public Object getJsonValueByKey(JSONObject j,String key) {
		// json中是否包含{Param:key}
		if(j.containsKey(key)){
			return j.get(key);
		}else{
			throw new RuntimeException("JSONObject hasn't key :"+key);
		}
	}
	/**
	 * 如果为null，设置空值
	 * 
	 * @param str
	 *            待处理对象
	 * @return str
	 */
	public Boolean getBooleanJsonValueByKey(JSONObject j,String key) {
		// json中是否包含{Param:key}
		Object obj=getJsonValueByKey(j,key); 
		if(obj instanceof Boolean){
			return j.getBoolean(key);
		} else {
			return Boolean.valueOf(String.valueOf(j.get(key)));
		}
	}
	/**
	 * 如果为null，设置空值
	 * 
	 * @param str
	 *            待处理对象
	 * @return str
	 */
	public String getStringJsonValueByKey(JSONObject j, String key) {
		// json中是否包含{Param:key}
		if (j.containsKey(key) == false) {
			return null;
		}
		Object obj=getJsonValueByKey(j,key); 
		if(obj instanceof String){
			return j.getString(key);
		} else {
			return String.valueOf(j.get(key));
		}
	}
	/**
	 * 如果为null，设置空值
	 * 
	 * @param str
	 *            待处理对象
	 * @return str
	 */
	public Long geLongJsonValueByKey(JSONObject j,String key) {
		// json中是否包含{Param:key}
		Object obj=getJsonValueByKey(j,key); 
		if(obj instanceof Long){
			return j.getLong(key);
		} else {
			return Long.valueOf(String.valueOf(j.get(key)));
		}
	}
	/**
	 * 如果为null，设置空值
	 * 
	 * @param str
	 *            待处理对象
	 * @return str
	 */
	public Integer geIntegerJsonValueByKey(JSONObject j,String key) {
		// json中是否包含{Param:key}
		Object obj=getJsonValueByKey(j,key); 
		if(obj instanceof Integer){
			return j.getInt(key);
		} else {
			throw new RuntimeException("Can't put a non-Integer  type cast  Integer type");
		}
	}
	/**
	 * 获取用户
	 * 
	 * @param str
	 *            待处理对象
	 * @return str
	 */
	public User getUser(HttpServletRequest request){
		User user = (User) request.getAttribute("users");
		return user;
	}

	/**
	 * 获取用户
	 *
	 * @param request
	 *            待处理对象
	 * @return str
	 */
	public User getYINUser(HttpServletRequest request){
		User user = getUser(request);
		if (user.getId() == 1) {
			return user;
		}
		return null;
	}

	 public void setSessionAndErr(HttpServletRequest request,HttpServletResponse response,String errCode,String errMessage){
//	    String sessionId=request.getHeader("sessionID");
	    response.setHeader("errorCode", errCode);
	    response.setHeader("errorMessage", Encodes.encodeBase64(errMessage.getBytes()));
	}

	public Map<String, Object> responseData(Map<String, Object> model, Map<String, Object> responseDataMap, Map<String, Object> notificationMap) {
		if (model == null) {
			model = new HashMap<String, Object>();
		}
		model.put("responseData", responseDataMap);
		model.put("notification", notificationMap);
		return model;
	}

	/**
	 * 获取用户
	 * 
	 * @param str
	 *            待处理对象
	 * @return str
	 */
	public UserBean getUserBean(HttpServletRequest request){
		UserBean userBean = (UserBean) request.getAttribute("userBean");
		return userBean;
	}
	
	
	public JSONObject convertJson(HttpServletRequest request) throws IOException {
	    String result = JsonReadUtil.getJsonIn(request);
	    if(StringUtils.isEmpty(result)) return null;
	    	JSONObject json = null;
	    	try {
	    		json = JSONObject.fromObject(result);
	    	} catch(net.sf.json.JSONException e) {
	    		getLogger().warn("convert json exception: " + e.getMessage());
			return null;
		}
		return json;
	}

	public JsonNode ObjectToJsonNode(final String content) throws IOException {
		if(StringUtils.isEmpty(content) || "null".equals(content)) {
			return null;
		}
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			return objectMapper.readTree(content);
		}
		catch(Exception e) {
			getLogger().warn("convert json exception: " + e.getMessage());
			return null;
		}
	}

	public String StringToString(final Object object) throws IOException {
		if(object != null) {
			return null;
		}
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			return objectMapper.writeValueAsString(object);
		}
		catch(Exception e) {
			getLogger().warn("convert json exception: " + e.getMessage());
			return null;
		}
	}


	public <T> T StringToObject(java.lang.Class<T> aClass, final String content) throws IOException {
		if(StringUtils.isEmpty(content) || "null".equals(content)) {
			return null;
		}
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			return objectMapper.readValue(content, aClass);
		}
		catch(Exception e) {
			getLogger().warn("convert json exception: " + e.getMessage());
			return null;
		}
	}

	public static boolean isWeb(HttpServletRequest request)
	{
		String s = request.getHeader("s");
		return "web".equals(s);
	}
}
