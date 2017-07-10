package com.ginkgocap.ywxt.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ginkgocap.ywxt.vo.query.meeting.UserBean;

/**
 * @Description:http 工具
 * @Author: qinguochao
 * @CreateDate: 2014-10-2
 * @Version: [v1.0]
 */
public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static final String dateTimeFormat = "MM/dd/yyyy HH:mm:ss";

	public static String getGintongPost(String url, String interfaceName, String json) throws Exception {

		// HttpClient httpClient = new DefaultHttpClient();
		HttpClient httpClient = HttpConnectionManager.getHttpClient();

		HttpPost postRequest = new HttpPost(url + interfaceName);
		StringEntity input = null;
		try {
			input = new StringEntity(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		input.setContentType("application/json;charset=UTF-8");
		postRequest.setEntity(input);
		input.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
		postRequest.setHeader("Accept", "application/json");
		postRequest.setEntity(input);

		try {
			HttpResponse response = httpClient.execute(postRequest);
			HttpEntity entity = response.getEntity();
			if (entity != null) {

				// System.out.println(EntityUtils.toString(entity));
				String a = EntityUtils.toString(entity).toString();
				// System.out.println(a);
				return a;
			}

		} catch (IOException e) {
			System.out.println("请求phoenix-mobile异常");
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return "";

	}
	public String doGet(String url, String interfaceName) {
		String result = "";
		HttpGet httpRequst = new HttpGet(url + interfaceName);

		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);// 其中HttpGet是HttpUriRequst的子类
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);// 取出应答字符串
				// 一般来说都要删除多余的字符
				result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			} else
				httpRequst.abort();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}
	/**
	 * post 方法调用，value里面存入传入的参数
	 * 
	 * @param url
	 * @param interfaceName
	 * @param value
	 * @return
	 * @author wangfeiliang
	 */
	public String doPost(String url, String interfaceName, Map<String, Object> value) {
		String result = "";
		HttpPost httpRequst = new HttpPost(url + interfaceName);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (value != null) {
			for (String key : value.keySet()) {
				params.add(new BasicNameValuePair(key, value.get(key).toString()));
			}
		}
		try {
			httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);// 取出应答字符串
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}

	public static String getGintongPost(String url, String interfaceName, String json, UserBean userbean) throws Exception {
		// HttpClient httpClient = new DefaultHttpClient();
		HttpClient httpClient = HttpConnectionManager.getHttpClient();

		HttpPost postRequest = new HttpPost(url + interfaceName);
		StringEntity input = null;
		try {
			input = new StringEntity(json, ContentType.create("application/json", "UTF-8"));
			input.setContentEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		postRequest.setEntity(input);
		postRequest.setHeader("Accept", "application/json");
		postRequest.setHeader("sessionID", userbean.getSessionId());
		postRequest.setHeader("jtNickName", userbean.getName());
		postRequest.setHeader("jtUserId", userbean.getId().toString());
		postRequest.setEntity(input);

		try {
			HttpResponse response = httpClient.execute(postRequest);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// System.out.println(EntityUtils.toString(entity));
				logger.info("REQEST => " + url + interfaceName);
				logger.info("PARAMS => " + json);
				String result = EntityUtils.toString(entity).toString();
				final String ret = (result != null && result.length() > 55) ? result.substring(0, 55) : result;
				logger.info("RETURN => " + ret);
				return result;
			}

		} catch (IOException e) {
			logger.info("RETURN => ");
			logger.error("请求phoenix-mobile异常", e);
		}
		return "";
	}
}
