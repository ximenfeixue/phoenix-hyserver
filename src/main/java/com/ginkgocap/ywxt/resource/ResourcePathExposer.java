/**
 * Copyright (c) 2011 银杏资本.
 * All Rights Reserved. 保留所有权利.
 */
package com.ginkgocap.ywxt.resource;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 通过Spring框架在ServletContext层面注入静态资源根路径信息
 * @author qingc
 *
 */
public class ResourcePathExposer implements ServletContextAware {
    private ServletContext application;
    private String nginxRoot = "";
    private String nfsHome;

    /*
     * 初始化方法
     */
    public void init() {
        //add by douyou ,in jsp,use ${contextPath } to replace request.getContextPath()
        getServletContext().setAttribute("nginxRoot", nginxRoot);
        getServletContext().setAttribute("nfsHome", nfsHome);
        getServletContext().setAttribute("contextPath", getServletContext().getContextPath());
        
    }


    public String getNginxRoot() {
		return nginxRoot;
	}

	public void setNginxRoot(String nginxRoot) {
		this.nginxRoot = nginxRoot;
	}

    public void setServletContext(ServletContext servletContext) {
        application = servletContext;

    }

    public ServletContext getServletContext() {
        return this.application;
    }

	public String getNfsHome() {
		return nfsHome;
	}

	public void setNfsHome(String nfsHome) {
		this.nfsHome = nfsHome;
	}

}
