package com.ginkgocap.ywxt.model.meeting;

import java.io.Serializable;
import java.util.Date;

public class SocialStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long mucId;
	private Long topicId;
	private Integer type;//1-私聊 ,2-群聊, 3-进行中的会议，4-未开始，5-已结束的会议, 6-通知,7-邀请函(通知和邀请函暂不处理)
	private Long userId;
	private Date createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMucId() {
		return mucId;
	}

	public void setMucId(Long mucId) {
		this.mucId = mucId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
