package com.ginkgocap.ywxt.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ginkgocap.ywxt.controller.meeting.MeetingController;
import org.apache.commons.lang3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static com.ginkgocap.ywxt.utils.StringUtils.*;

public class JsonReadUtil {
	private final static Logger logger = LoggerFactory.getLogger(JsonReadUtil.class);
	/**
	 * 获取json字符串
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getJsonIn(HttpServletRequest request) throws IOException{
        String requestJson=(String)request.getAttribute("requestJson");
        if(requestJson==null){
        	return "";
        }
		return requestJson;
	}

	public static JsonNode getJsonNode(final String content) {
		if (content == null || content.trim().length() <= 0 || "null".equals(content)) {
			return null;
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readTree(content);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("json content : " + content);
		}
		return null;
	}

	public static <T> List<T> readListValue(Class<T> valueType, final String content) {
		if (content == null || content.trim().length() <= 0 || "null".equals(content)) {
			return null;
		}
		try {
			//TypeReference javaType = new TypeReference<List<T>>(){};
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, valueType);
			objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			return objectMapper.readValue(content, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
