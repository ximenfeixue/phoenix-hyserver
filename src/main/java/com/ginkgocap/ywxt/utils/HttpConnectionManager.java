package com.ginkgocap.ywxt.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @Description:http 链接管理
 * @Author: qinguochao
 * @CreateDate: 2014-10-2
 * @Version: [v1.0]
 */
public class HttpConnectionManager {
	
	private static HttpParams httpParams;  
    private static ClientConnectionManager connectionManager;  
  
    /** 
     * 最大连接数 
     */  
    public final static int MAX_TOTAL_CONNECTIONS = 800;  
    /** 
     * 获取连接的最大等待时间 
     */  
    public final static long WAIT_TIMEOUT = 600000;  
    /** 
     * 每个路由最大连接数 
     */  
    public final static int MAX_ROUTE_CONNECTIONS = 400;  
    /** 
     * 连接超时时间 
     */  
    public final static int CONNECT_TIMEOUT = 300000;  
    /** 
     * 读取超时时间 
     */  
    public final static int READ_TIMEOUT = 300000;  
  
    static {  
        httpParams = new BasicHttpParams();  
        // 设置最大连接数  
        ConnManagerParams.setMaxTotalConnections(httpParams, MAX_TOTAL_CONNECTIONS);  
        // 设置获取连接的最大等待时间  
        ConnManagerParams.setTimeout(httpParams, WAIT_TIMEOUT);  
        // 设置每个路由最大连接数  
        ConnPerRouteBean connPerRoute = new ConnPerRouteBean(MAX_ROUTE_CONNECTIONS);  
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams,connPerRoute);  
        // 设置连接超时时间  
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);  
        // 设置读取超时时间  
        HttpConnectionParams.setSoTimeout(httpParams, READ_TIMEOUT);  
  
        SchemeRegistry registry = new SchemeRegistry();  
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));  
        registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));  
  
        connectionManager = new ThreadSafeClientConnManager(httpParams, registry);  
    }  
  
    public static HttpClient getHttpClient() {  
        return new DefaultHttpClient(connectionManager, httpParams);  
    }  


	

}
