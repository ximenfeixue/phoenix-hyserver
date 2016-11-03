package com.ginkgocap.ywxt.vo.query.meeting;

import java.util.List;
import java.util.Map;
/**
 * 返回消息主对象
 * @author edwin
 *
 */
public class ResponseObject{

	/**通知信息代号*/
	private ResponseData responseData;
	/**通知信息描述*/
	private Notification notification;
	/**错误信息*/
	private String errormessage;
	/**操作状态值*/
	private Integer status;
	private String error;
	/**记录总数*/
	private Integer total;
	/**每页显示条数*/
	private Integer psize;
	/**页数*/
	private Integer pno;
	/**手机和*/
	private List<Map<String,String>>list;
	
	
	public ResponseData getResponseData() {
		return responseData;
	}
	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPsize() {
		return psize;
	}
	public void setPsize(Integer psize) {
		this.psize = psize;
	}
	public Integer getPno() {
		return pno;
	}
	public void setPno(Integer pno) {
		this.pno = pno;
	}
	public List<Map<String, String>> getList() {
		return list;
	}
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
	 
}
