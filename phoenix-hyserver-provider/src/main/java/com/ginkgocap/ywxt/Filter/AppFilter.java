package com.ginkgocap.ywxt.Filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	

	public void destroy() {

	}
	public User getUser(HttpServletRequest request){
		String sessionId=request.getHeader("sessionID");
		if(sessionId!=null && !"null".equals(sessionId) && !"".equals(sessionId)){
			WebApplicationContext wac=	WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			Cache cache=(Cache) wac.getBean("cache");
			User user=(User) cache.getByRedis("user"+sessionId);
			return user;
		}else{
			return null;
		}
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url=req.getRequestURI();
//		if(loginFlag){
//			User user=getUser(req);
//			if (Utils.isNullOrEmpty(user)) {
////				res.setHeader("errorCode", "-1");
////				res.setHeader("errorMessage", Encodes.encodeBase64("Filter test".getBytes()));
//				response.getWriter().write();
//				return;
//			}
// 		}
		if(url.contains("file/")){
			chain.doFilter(request, res);
			return;
		}
		UserBean userBean=new UserBean();
		String sessionId = req.getHeader("sessionID");
		userBean.setSessionId(sessionId);
		String jtUserIDs = req.getHeader("jtUserID");
		String name = req.getHeader("jtNickName");
		userBean.setName(name);

		if(!Utils.isNullOrEmpty(jtUserIDs)){
			Long jtUserId = Long.parseLong(jtUserIDs);
			userBean.setId(jtUserId);
			WebApplicationContext wac=	WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
			UserService userService=(UserService) wac.getBean("userService");
			User user=userService.selectByPrimaryKey(jtUserId);
			userBean.setName(user.getName());
			req.setAttribute("users",user);
		}else{
//			PrintWriter out=res.getWriter();
//			String errorStr="{\"notification\":{\"notifCode\": \"0003\",\"notifInfo\": \"用户长时间未操作或已过期,请重新登录\"}}";
//			out.println(errorStr);
//			out.flush();
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