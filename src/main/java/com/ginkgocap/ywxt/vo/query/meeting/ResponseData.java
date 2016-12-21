package com.ginkgocap.ywxt.vo.query.meeting;

import java.util.List;


/**
 * @Description: 响应vo
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
public class ResponseData {

	
	/**关系精简对象*/
	private List<ConnectionsMini> listConnectionsMini;
	/**是否成功*/
	public String succeed;
	/**线上人脉*/
	private JTContact onlineJTContact;
	/**线下人脉*/
	private JTContact offlineJTContact;
	
	public List<ConnectionsMini> getListConnectionsMini() {
		return listConnectionsMini;
	}

	public void setListConnectionsMini(List<ConnectionsMini> listConnectionsMini) {
		this.listConnectionsMini = listConnectionsMini;
	}


	public JTContact getOnlineJTContact() {
		return onlineJTContact;
	}

	public void setOnlineJTContact(JTContact onlineJTContact) {
		this.onlineJTContact = onlineJTContact;
	}

	public JTContact getOfflineJTContact() {
		return offlineJTContact;
	}

	public void setOfflineJTContact(JTContact offlineJTContact) {
		this.offlineJTContact = offlineJTContact;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}
	
}
