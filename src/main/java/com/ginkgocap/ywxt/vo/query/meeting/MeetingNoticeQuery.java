/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.model.meeting.MeetingNotice;
import com.ginkgocap.ywxt.utils.Utils;
public class MeetingNoticeQuery implements Serializable, Comparable<Object> {
    private static final long serialVersionUID = 3148176768559230877L;
	/** 通知ID */
	private Long id;
	/** 接收人类型 0:发起者1：参会人 */
	private Integer receiverType;
	/** 接收人 */
	private Long receiver;
	 /**  接收人名字  */
	private String receiverName;
	 /**  参会人Id  */
	private Long attendMeetingId;
	 /**  参会人名字  */
	private String attendMeetingName;
	/** 通知类型：0：修改议题，1：报名申请，2：报名通过，3：报名未通过，4：接受邀请，5：拒绝邀请，6同意报名，7拒绝报名 */
	private Integer noticeType;
	/**是否显示：0：不显示，1：显示  */	
	private Integer isShow;
	/** 通知内容 */
	private String noticeContent;
	/** 会议ID */
	private Long meetingId;
	/** 创建者ID */
	private Long createId;
	/** 创建者名字 */
	private String createName;
	//创建者图片
	private String createPic;
	//创建者性别
	private String createSex;
	/** 创建者名字 */
	private String meetingCreateName;
	/** 成员报名信息 */
	private List<MeetingSignLabelDataQuery> listMeetingSignLabelDataQuery;
	/** 通知 修改会议的字段  */
	private List<String> listMeetingField;
	/** 创建时间 */
	private Date createTime;
	/**修改时间     */	
	private Date updateTime;
	//是否已读取 0：未读，1：已读
	private Long isRead;
	//读取时间
	private Date readTime;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Integer getReceiverType() {
		return this.receiverType;
	}
	
	public void setReceiverType(Integer value) {
		this.receiverType = value;
	}
	
	public Long getReceiver() {
		return this.receiver;
	}
	
	public void setReceiver(Long value) {
		this.receiver = value;
	}
	
	public Integer getNoticeType() {
		return this.noticeType;
	}
	
	public void setNoticeType(Integer value) {
		this.noticeType = value;
	}
	
	public String getNoticeContent() {
		return this.noticeContent;
	}
	
	public void setNoticeContent(String value) {
		this.noticeContent = value;
	}
	
	public Long getMeetingId() {
		return this.meetingId;
	}
	
	public void setMeetingId(Long value) {
		this.meetingId = value;
	}
	
	public Long getCreateId() {
		return this.createId;
	}
	
	public void setCreateId(Long value) {
		this.createId = value;
	}
	
	public String getCreateName() {
		return this.createName;
	}
	
	public void setCreateName(String value) {
		this.createName = value;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<String> getListMeetingField() {
		return listMeetingField;
	}

	public void setListMeetingField(List<String> listMeetingField) {
		this.listMeetingField = listMeetingField;
	}

	public List<MeetingSignLabelDataQuery> getListMeetingSignLabelDataQuery() {
		return listMeetingSignLabelDataQuery;
	}

	public void setListMeetingSignLabelDataQuery(
			List<MeetingSignLabelDataQuery> listMeetingSignLabelDataQuery) {
		this.listMeetingSignLabelDataQuery = listMeetingSignLabelDataQuery;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Long getAttendMeetingId() {
		return attendMeetingId;
	}

	public void setAttendMeetingId(Long attendMeetingId) {
		this.attendMeetingId = attendMeetingId;
	}

	public String getAttendMeetingName() {
		return attendMeetingName;
	}

	public void setAttendMeetingName(String attendMeetingName) {
		this.attendMeetingName = attendMeetingName;
	}

	public String getMeetingCreateName() {
		return meetingCreateName;
	}

	public void setMeetingCreateName(String meetingCreateName) {
		this.meetingCreateName = meetingCreateName;
	}
	
	public String getCreatePic() {
		return createPic;
	}

	public void setCreatePic(String createPic) {
		this.createPic = createPic;
	}
	
	public Long getIsRead() {
		return isRead;
	}

	public void setIsRead(Long isRead) {
		this.isRead = isRead;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	/**
	 * 名称: toString
	 * 描述: 重写toString方法
	 * @since  2014-09-14
	 * @author qingc
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ReceiverType",getReceiverType())
			.append("Receiver",getReceiver())
			.append("NoticeType",getNoticeType())
			.append("NoticeContent",getNoticeContent())
			.append("MeetingId",getMeetingId())
			.append("CreateId",getCreateId())
			.append("CreateName",getCreateName())
			.append("CreateTime",getCreateTime())
			.append("IsRead",getIsRead())
			.append("ReadTime",getReadTime())
			.toString();
	}
		
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getCreateSex() {
		return createSex;
	}

	public void setCreateSex(String createSex) {
		this.createSex = createSex;
	}

	/**
	 * 名称: toJson
	 * 描述: 生成json串
	 * @since  2014-09-14
	 * @author qingc
	 */
	public String toJson() {
		 return new StringBuilder()
		.append("{ 'meeting':{")
			.append("'id':'"+getId()+"',")
			.append("'receiverType':'"+getReceiverType()+"',")
			.append("'receiver':'"+getReceiver()+"',")
			.append("'noticeType':'"+getNoticeType()+"',")
			.append("'noticeContent':'"+getNoticeContent()+"',")
			.append("'meetingId':'"+getMeetingId()+"',")
			.append("'createId':'"+getCreateId()+"',")
			.append("'createName':'"+getCreateName()+"',")
			.append("'createTime':'"+getCreateTime()+"',")
			.append("'isRead':'"+getIsRead()+"',")
			.append("'readTime':'"+getReadTime()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}

	public int compareTo(Object o) {
		MeetingNoticeQuery meetingNoticeQuery=(MeetingNoticeQuery)o;  
		if(Utils.isNullOrEmpty(this.updateTime)||Utils.isNullOrEmpty(meetingNoticeQuery.updateTime)){
			return 1;
		}else{
			if(meetingNoticeQuery.updateTime.before(this.updateTime)){
				return -1;
			}else if(meetingNoticeQuery.updateTime.after(this.updateTime)){
				return 1;
			}else{
				return 1;
			}
		}
	}
	public void copyFromMeetingNotice(MeetingNotice meetingNotice) {
		if(meetingNotice != null) {
			this.setId(meetingNotice.getId());
			this.setReceiverType(meetingNotice.getReceiverType());
			this.setReceiver(meetingNotice.getReceiver());
			this.setReceiverName(meetingNotice.getReceiverName());
			this.setNoticeType(meetingNotice.getNoticeType());
			this.setIsShow(meetingNotice.getIsShow());
			this.setNoticeContent(meetingNotice.getNoticeContent());
			this.setMeetingId(meetingNotice.getMeetingId());
			this.setCreateId(meetingNotice.getCreateId());
			this.setCreateName(meetingNotice.getCreateName());
			this.setCreateTime(meetingNotice.getCreateTime());
			this.setUpdateTime(meetingNotice.getUpdateTime());
			this.setIsRead(meetingNotice.getIsRead());
			this.setReadTime(meetingNotice.getReadTime());
		}
	}
}

