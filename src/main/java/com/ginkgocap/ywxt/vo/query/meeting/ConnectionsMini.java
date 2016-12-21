package com.ginkgocap.ywxt.vo.query.meeting;

import java.util.List;
/**
 * @Description: 关系精简对象vo
 * @Author: qinguochao
 * @CreateDate: 2014-4-18
 * @Version: [v1.0]
 */
public class ConnectionsMini{

	/**分别为JTContact、机构id*/
	private Integer id;
	/**"0-个人、1-机构*/
	private String type;
	/**个人头像，或者机构的logo*/
	private String image;
	/**个人姓名或者机构简称*/
	private String name;
	/**请求ID集合*/
	private List<Integer> listUserID;
    
	private String shortName;//昵称
    
    private String companyName;//单位名称
   
    private String companyJob;//职务
    
    private String loginTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getListUserID() {
		return listUserID;
	}
	public void setListUserID(List<Integer> listUserID) {
		this.listUserID = listUserID;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyJob() {
		return companyJob;
	}
	public void setCompanyJob(String companyJob) {
		this.companyJob = companyJob;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
}
