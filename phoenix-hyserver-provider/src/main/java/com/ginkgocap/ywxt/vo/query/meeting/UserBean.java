package com.ginkgocap.ywxt.vo.query.meeting;

import java.util.Date;

import com.mongodb.DBObject;
/**
 * @Description: 修改会议推送vo
 * @Author: qinguochao
 * @CreateDate: 2014-10-2
 * @Version: [v1.0]
 */
public class UserBean{

	/**用户id*/
	private Long id;
	/**用户名字*/
	private String name;
	/**用户sessionId*/
	private String sessionId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}