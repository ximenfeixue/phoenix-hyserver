/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.*;




import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.common.base.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;


public class MeetingPicQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** "" */
	private java.lang.Long meetingId;
	/** nginx上图片路径，去掉nfs部分 */
	private java.lang.String picPath;
	/** 图片名字 nginx生成的 */
	private java.lang.String picName;
	/** 图片的真实名字 */
	private java.lang.String picRealName;
	/** 图片描述 */
	private java.lang.String picDesc;
	/** 是否把此图片作为封面 默认插入数据库时为0 */
	private java.lang.Integer ishomePage;
	/** 上传这个图片的用户id */
	private java.lang.Long createUserId;
	/** 上传此图片的用户登录名 */
	private java.lang.String createUserName;
	/** 创建时间 */
	private java.util.Date createDate;
	/** 图片状态：0-待生效 1-已生效 2-屏蔽 */
	private java.lang.String picStatus;
	 /**
     * 文件表关联id       db_column: file_index_id 
     */	
	private String fileIndexId;
	/**
	/** 图片待删除标记 1-待删除 */
	private java.lang.String picDel;
	/** updateDate */
	private java.util.Date updateDate;
	private java.util.Date updateDateBegin;
	private java.util.Date updateDateEnd;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getMeetingId() {
		return this.meetingId;
	}
	
	public void setMeetingId(java.lang.Long value) {
		this.meetingId = value;
	}
	
	public java.lang.String getPicPath() {
		return this.picPath;
	}
	
	public void setPicPath(java.lang.String value) {
		this.picPath = value;
	}
	
	public java.lang.String getPicName() {
		return this.picName;
	}
	
	public void setPicName(java.lang.String value) {
		this.picName = value;
	}
	
	public java.lang.String getPicRealName() {
		return this.picRealName;
	}
	
	public void setPicRealName(java.lang.String value) {
		this.picRealName = value;
	}
	
	public java.lang.String getPicDesc() {
		return this.picDesc;
	}
	
	public void setPicDesc(java.lang.String value) {
		this.picDesc = value;
	}
	
	public java.lang.Integer getIshomePage() {
		return this.ishomePage;
	}
	
	public void setIshomePage(java.lang.Integer value) {
		this.ishomePage = value;
	}
	
	public java.lang.Long getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.Long value) {
		this.createUserId = value;
	}
	
	public java.lang.String getCreateUserName() {
		return this.createUserName;
	}
	
	public void setCreateUserName(java.lang.String value) {
		this.createUserName = value;
	}
	
	
	public java.lang.String getPicStatus() {
		return this.picStatus;
	}
	
	public void setPicStatus(java.lang.String value) {
		this.picStatus = value;
	}
	
	public java.lang.String getPicDel() {
		return this.picDel;
	}
	
	public void setPicDel(java.lang.String value) {
		this.picDel = value;
	}
	
	public java.util.Date getUpdateDateBegin() {
		return this.updateDateBegin;
	}
	
	public void setUpdateDateBegin(java.util.Date value) {
		this.updateDateBegin = value;
	}	
	
	public java.util.Date getUpdateDateEnd() {
		return this.updateDateEnd;
	}
	
	public void setUpdateDateEnd(java.util.Date value) {
		this.updateDateEnd = value;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getFileIndexId() {
		return fileIndexId;
	}

	public void setFileIndexId(String fileIndexId) {
		this.fileIndexId = fileIndexId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

