package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.Date;

public class BigDataQuery implements Serializable {
	private static final long serialVersionUID = -8217328295431912178L;
	//会议ID
	private Long id;
	//会议名称
	private String meetingName;
	//会议描述
	private String meetingDesc;
	//会议图片
	private String meetingPic;
	//参会人数
	private int count = 1;
	//创建人ID
	private Long createId;
	//创建人姓名
	private String createName;
	//创建人头像
	private String createPic;
	//会议开始时间
	private Date startTime;
	//会议结束时间
	private Date endTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getMeetingDesc() {
		return meetingDesc;
	}
	public void setMeetingDesc(String meetingDesc) {
		this.meetingDesc = meetingDesc;
	}
	public String getMeetingPic() {
		return meetingPic;
	}
	public void setMeetingPic(String meetingPic) {
		this.meetingPic = meetingPic;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreatePic() {
		return createPic;
	}
	public void setCreatePic(String createPic) {
		this.createPic = createPic;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
