package com.ginkgocap.ywxt.model.meeting;

public class MeetingFile  {
	
	/** FileIndex的ID **/
	private String fileIndexId;
	/** 文件地址 **/
	private String url;
	/** 后缀名 **/
	private String suffixName;
	/** 内容类型 **/
	private Long type;
	/** 文件名 **/
	private String fileName;
	/** 文件大小 **/
	private Long fileSize;
	/** 0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构 */
	private Long moduleType;
	/**附件索引*/
	private String taskId;
	
	public String getFileIndexId() {
		return fileIndexId;
	}
	public void setFileIndexId(String fileIndexId) {
		this.fileIndexId = fileIndexId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSuffixName() {
		return suffixName;
	}
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Long getModuleType() {
		return moduleType;
	}
	public void setModuleType(Long moduleType) {
		this.moduleType = moduleType;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}