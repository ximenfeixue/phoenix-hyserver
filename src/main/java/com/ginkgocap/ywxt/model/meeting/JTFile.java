package com.ginkgocap.ywxt.model.meeting;

import java.util.Date;

import com.mongodb.DBObject;

/**
 * 通知
 * @author edwin
 *
 */
public class JTFile  {

	/**文件地址*/
	private String url;
	/**后缀名 jpg,png,amr,pdf等*/
	private String suffixName;
	/**内容type*/
	private Integer type;
	/**文件名*/
	private String fileName;
	/**文件大小*/
	private Integer fileSize;
	/**0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构*/
	private Integer moduleType;
	/**附件索引*/
	private String taskId;
	/**备用1*/
	private String reserved1;
	/**备用2*/
	private String reserved2;
	/**备用3*/
	private String reserved3;
	
	
	public static final int TYPE_VIDEO = 0;
	public static final int TYPE_AUDIO = 1;
	public static final int TYPE_FILE = 2;
	public static final int TYPE_IMAGE = 3;
	public static final int TYPE_OTHER = 4;
	
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public Integer getModuleType() {
		return moduleType;
	}
	public void setModuleType(Integer moduleType) {
		this.moduleType = moduleType;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

}
