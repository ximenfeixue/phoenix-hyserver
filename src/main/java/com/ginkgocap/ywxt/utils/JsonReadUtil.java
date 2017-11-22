package com.ginkgocap.ywxt.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static com.ginkgocap.ywxt.utils.StringUtils.*;

public class JsonReadUtil {
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
