package com.ginkgocap.ywxt.vo.query.social;

import java.io.Serializable;

public class CommunityNewCount implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 社群主键
	 */
	private Integer mucId;
	/**
	 * 未读消息数
	 */
	private Integer newCount;

	/**
	 * 群消息提醒:0开启 1无声 2 关闭,默认是0
	 */
	private Byte newMessageRemind;

	public CommunityNewCount() {
		this.newCount = 0;
		this.newMessageRemind = 0;
	}
	
	public Integer getMucId() {
		return mucId;
	}

	public void setMucId(Integer mucId) {
		this.mucId = mucId;
	}

	public Integer getNewCount() {
		return newCount;
	}

	public void setNewCount(Integer newCount) {
		this.newCount = newCount;
	}

	public Byte getNewMessageRemind() {
		return newMessageRemind;
	}

	public void setNewMessageRemind(Byte newMessageRemind) {
		this.newMessageRemind = newMessageRemind;
	}

	@Override
	public String toString() {
		return "CommunityNewCount [mucId=" + mucId + ", newCount=" + newCount + ", newMessageRemind="
				+ newMessageRemind + "]";
	}
}
