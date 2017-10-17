package com.ginkgocap.ywxt.Filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ginkgocap.ywxt.utils.RedisKeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ginkgocap.ywxt.cache.Cache;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.utils.Utils;
import com.ginkgocap.ywxt.vo.query.meeting.UserBean;

public class AppFilter implements Filter {
	String excludedUrl = "";
	String[] excludedUrlArray={};
	//允许游客状态的接口
	String[] webExcludedUrl = {"/meeting/getByIdAndMemberId.json"};
	
	public void destroy() {

	}

	private User getUser(String url, HttpServletRequest request) {
		// 判断客户端请求方式
		String s = request.getHeader("s");
		if ("web".equals(s)) {
			String sessionId = request.getHeader("sessionID");
			if (StringUtils.isNotBlank(sessionId)) {
				String key = RedisKeyUtils.getSessionIdKey(sessionId);
				return getUser(request, key);
			} else {
				// 对于游客允许的 url 设置默认的 userId
				for (String excludedUrl : webExcludedUrl) {
					if (url.contains(excludedUrl)) {
						return getJINUser();
					}
				}
			}
		} else {
			String sessionId = request.getHeader("sessionID");
			if (sessionId != null && !"null".equals(sessionId)
					&& !"".equals(sessionId)) {
				String key = "user" + sessionId;
				return getUser(request, key);
			}
		}
		return null;
	}

	/**
	 * 在访问游客允许的 url 时，并进行分享功能，提供默认的 userId
	 * @return
	 */
	private User getJINUser() {

		User user = new User();
		user.setId(0l);
		return user;
	}

	/**
	 * 获取用户信息
	 * @param request request
	 * @param key    sessionId key
	 * @return user
	 * @author haiyan
	 */
	private User getUser(HttpServletRequest request, String key) {
		WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession().getServletContext());
		Cache cache = (Cache) wac.getBean("cache");
		User user = (User) cache.getByRedis(key);
		if (user != null) {
			cache.setByRedis(key, user, 60 * 60 * 24 * 7);
		}
		return user;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI();
		if (url.contains("file/")) {
			chain.doFilter(request, res);
			return;
		}
		
		User user = getUser(url, req);
		if (user == null) {
			PrintWriter out = res.getWriter();
			String errorStr="{\"notification\":{\"notifCode\": \"0003\",\"notifInfo\": \"用户长时间未操作或已过期,请重新登录\"}}";
			out.println(errorStr);
			out.flush();
			return;
		}
		req.setAttribute("users",user);
		
		UserBean userBean = new UserBean();
		String sessionId = req.getHeader("sessionID");
		userBean.setSessionId(sessionId);
		String jtUserIDs = req.getHeader("jtUserID");

		if (Utils.isNullOrEmpty(jtUserIDs)){
			userBean.setId(user.getId());
			userBean.setName(user.getName());
		} else {
			Long jtUserId = Long.parseLong(jtUserIDs);
			userBean.setId(jtUserId);
			String name = req.getHeader("jtNickName");
			if(Utils.isNullOrEmpty(name)) {
				userBean.setName(user.getName());
			}
		}
		req.setAttribute("userBean",userBean);
		
		//敏感词过滤
		BufferedReader reader = request.getReader();
        String line = null;
        StringBuffer jsonIn = new StringBuffer();
        while((line=reader.readLine()) != null) {
        	jsonIn.append(line);
        }
        String result=jsonIn.toString();
        if(result.equals("")){
        	result=(String)request.getParameter("json");
        }
        String requestJson = result;
        reader.close();
        if(requestJson!=null && !"".equals(requestJson)){
        	request.setAttribute("requestJson", requestJson);
    		chain.doFilter(request, res);
			return;
        }else{
        	request.setAttribute("requestJson", requestJson);
    		chain.doFilter(request, res);
        	return;
        }
	}

	public void init(FilterConfig config) throws ServletException {
		excludedUrl = config.getInitParameter("excludedUrl");
		if(!Utils.isNullOrEmpty(excludedUrl)){
			 excludedUrlArray =excludedUrl.split(",");
		}
	}

}