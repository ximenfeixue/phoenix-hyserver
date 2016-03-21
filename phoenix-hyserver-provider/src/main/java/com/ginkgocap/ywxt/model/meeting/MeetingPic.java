/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import java.util.Date;

import javacommon.base.BaseEntity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.utils.DateConvertUtils;


public class MeetingPic extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final long MODULE_TYPE_MEETING = 1;
	public static final long MODULE_TYPE_TOPIC = 2;
	public static final long MODULE_TYPE_NOTE = 3;
	//alias
	public static final String TABLE_ALIAS = "MeetingPic";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MEETING_ID = "会议";
	public static final String ALIAS_PIC_PATH = "nginx上图片路径，去掉nfs部分";
	public static final String ALIAS_PIC_NAME = "图片名字 nginx生成的";
	public static final String ALIAS_PIC_REAL_NAME = "图片的真实名字";
	public static final String ALIAS_PIC_DESC = "图片描述";
	public static final String ALIAS_ISHOME_PAGE = "是否把此图片作为封面 默认插入数据库时为0";
	public static final String ALIAS_CREATE_USER_ID = "上传这个图片的用户id";
	public static final String ALIAS_CREATE_USER_NAME = "上传此图片的用户登录名";
	public static final String ALIAS_CREATE_DATE = "创建时间";
	public static final String ALIAS_PIC_STATUS = "图片状态：0-待生效 1-已生效 2-屏蔽";
	public static final String ALIAS_PIC_DEL = "图片待删除标记 1-待删除";
	public static final String ALIAS_UPDATE_DATE = "updateDate";
	
	//date formats
	public static final String FORMAT_CREATE_DATE = DATE_FORMAT;
	public static final String FORMAT_UPDATE_DATE = DATE_FORMAT;
	
	//ID
	private Long id;
	//会议ID
	private Long meetingId;
	//nginx上图片路径，去掉nfs部分
	private String picPath;
	//图片名字 nginx生成的
	private String picName;
	//图片的真实名字
	private String picRealName;
	//图片描述
	private String picDesc;
    //是否把此图片作为封面 默认插入数据库时为0
	private Integer ishomePage;
	//文件表关联id	
	private String fileIndexId;
	//上传这个图片的用户id
	private Long createUserId;
    //上传此图片的用户登录名
	private String createUserName;
	//创建时间
	private Date createDate;
	//图片状态：0-待生效 1-已生效 2-屏蔽
	private String picStatus;
	//图片待删除标记 1-待删除
	private String picDel;
    //更新时间
	private Date updateDate;
	//任务ID
	private String taskId;
	//会议/议题ID 
	private Long moduleId;
	//图片所属模块类型 1：会议 2：议题 3：笔记
	private Long moduleType;
	//图片宽度
	private String width;
	//图片高度
	private String height;

	public MeetingPic(){
	}

	public MeetingPic(Long id){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setMeetingId(Long value) {
		this.meetingId = value;
	}
	
	public Long getMeetingId() {
		return this.meetingId;
	}
	public void setPicPath(String value) {
		this.picPath = value;
	}
	
	public String getPicPath() {
		return this.picPath;
	}
	public void setPicName(String value) {
		this.picName = value;
	}
	
	public String getPicName() {
		return this.picName;
	}
	public void setPicRealName(String value) {
		this.picRealName = value;
	}
	
	public String getPicRealName() {
		return this.picRealName;
	}
	public void setPicDesc(String value) {
		this.picDesc = value;
	}
	
	public String getPicDesc() {
		return this.picDesc;
	}
	public void setIshomePage(Integer value) {
		this.ishomePage = value;
	}
	
	public Integer getIshomePage() {
		return this.ishomePage;
	}
	public void setCreateUserId(Long value) {
		this.createUserId = value;
	}
	
	public Long getCreateUserId() {
		return this.createUserId;
	}
	public void setCreateUserName(String value) {
		this.createUserName = value;
	}
	
	public String getCreateUserName() {
		return this.createUserName;
	}
	
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setPicStatus(String value) {
		this.picStatus = value;
	}
	
	public String getPicStatus() {
		return this.picStatus;
	}
	public void setPicDel(String value) {
		this.picDel = value;
	}
	
	public String getPicDel() {
		return this.picDel;
	}
	public String getUpdateDateString() {
		return DateConvertUtils.format(getUpdateDate(), FORMAT_UPDATE_DATE);
	}
	public void setUpdateDateString(String value) {
		setUpdateDate(DateConvertUtils.parse(value, FORMAT_UPDATE_DATE,Date.class));
	}
	
	public void setUpdateDate(Date value) {
		this.updateDate = value;
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	public String getFileIndexId() {
		return fileIndexId;
	}

	public void setFileIndexId(String fileIndexId) {
		this.fileIndexId = fileIndexId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Long getModuleType() {
		return moduleType;
	}

	public void setModuleType(Long moduleType) {
		this.moduleType = moduleType;
	}

	/**
	 * 重写toString方法
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MeetingId",getMeetingId())
			.append("PicPath",getPicPath())
			.append("PicName",getPicName())
			.append("PicRealName",getPicRealName())
			.append("PicDesc",getPicDesc())
			.append("IshomePage",getIshomePage())
			.append("CreateUserId",getCreateUserId())
			.append("CreateUserName",getCreateUserName())
			.append("CreateDate",getCreateDate())
			.append("PicStatus",getPicStatus())
			.append("PicDel",getPicDel())
			.append("UpdateDate",getUpdateDate())
			.append("TaskId",getTaskId())
			.append("ModuleId",getModuleId())
			.append("ModuleType",getModuleType())
			.append("Width",getWidth())
			.append("Height",getHeight())
			.toString();
	}
	/**
	 * 生成json串
	 * @return
	 */
	public String toJson() {
		 return new StringBuilder()
		.append("{ 'meeting':{")
			.append("'id':'"+getId()+"',")
			.append("'meetingId':'"+getMeetingId()+"',")
			.append("'picPath':'"+getPicPath()+"',")
			.append("'picName':'"+getPicName()+"',")
			.append("'picRealName':'"+getPicRealName()+"',")
			.append("'picDesc':'"+getPicDesc()+"',")
			.append("'ishomePage':'"+getIshomePage()+"',")
			.append("'createUserId':'"+getCreateUserId()+"',")
			.append("'createUserName':'"+getCreateUserName()+"',")
			.append("'createDate':'"+getCreateDate()+"',")
			.append("'picStatus':'"+getPicStatus()+"',")
			.append("'picDel':'"+getPicDel()+"',")
			.append("'updateDate':'"+getUpdateDate()+"',")
			.append("'taskId':'"+getTaskId()+"',")
			.append("'moduleId':'"+getModuleId()+"',")
			.append("'moduleType':'"+getModuleType()+"',")
			.append("'width':'"+getWidth()+"',")
			.append("'height':'"+getHeight()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MeetingPic == false) return false;
		if(this == obj) return true;
		MeetingPic other = (MeetingPic)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

