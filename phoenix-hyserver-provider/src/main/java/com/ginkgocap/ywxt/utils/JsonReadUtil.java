package com.ginkgocap.ywxt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

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
}
