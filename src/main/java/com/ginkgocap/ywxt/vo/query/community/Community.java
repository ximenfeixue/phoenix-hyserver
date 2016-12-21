package com.ginkgocap.ywxt.vo.query.community;

import com.ginkgocap.ywxt.vo.query.social.Social;

public class Community extends Social {

	private static final long serialVersionUID = 6280434067029846221L;
	
	/**群消息提醒:0开启 1无声 2 关闭*/
	private Integer newMessageRemind;
	
	/**社群里的昵称*/
	private String nickname;
	
	/**是否显示群成员昵称：0是 1否*/
	private Integer showNickname;

	public Integer getNewMessageRemind() {
		return newMessageRemind;
	}

	public void setNewMessageRemind(Integer newMessageRemind) {
		this.newMessageRemind = newMessageRemind;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getShowNickname() {
		return showNickname;
	}

	public void setShowNickname(Integer showNickname) {
		this.showNickname = showNickname;
	}
	
}