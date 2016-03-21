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

import com.ginkgocap.ywxt.model.meeting.MeetingFile;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.model.meeting.MeetingTopic;

public class MeetingTopicQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
	/** 议题序号 */
	private Long id;
	/** 会议序号 */
	private Long meetingId;
	/** 议题内容 */
	private String topicContent;
	/** 议题开始时间*/
	private Date topicStartTime;
	/** 议题结束时间*/
	private Date topicEndTime;
	/** 议题介绍 */
	private String topicDesc;
	/** 附件id */
	private String taskId;
	/** 人员ID */
	private Long memberId;
	/** 人员名字 */
	private String memberName;
	/** 主讲人头像 */
	private String memberPic;
	/** 主讲人描述 */
	private String memberDesc;
	/** 创建人Id */
	private Long createId;
	/** 创建人名字 */
	private String createName;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 议题相关图片 **/
	private List<MeetingPic> listMeetingPic;
	/** 附件列表 **/
	private List<MeetingFile> listMeetingFile;
	/** 议题聊天记录 **/
	private List<TopicChatQuery> listTopicChat;
	/** 显示图片地址 **/
	private String path;
	//是否已结束 0：默认 1：结束
	private int isFinished;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getMeetingId() {
		return this.meetingId;
	}
	
	public void setMeetingId(Long value) {
		this.meetingId = value;
	}
	
	public String getTopicContent() {
		return this.topicContent;
	}
	
	public void setTopicContent(String value) {
		this.topicContent = value;
	}
	public Date getTopicStartTime() {
		return topicStartTime;
	}

	public void setTopicStartTime(Date topicStartTime) {
		this.topicStartTime = topicStartTime;
	}

	public Date getTopicEndTime() {
		return topicEndTime;
	}

	public void setTopicEndTime(Date topicEndTime) {
		this.topicEndTime = topicEndTime;
	}

	public String getTopicDesc() {
		return this.topicDesc;
	}
	
	public void setTopicDesc(String value) {
		this.topicDesc = value;
	}
	
	
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String value) {
		this.taskId = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public List<TopicChatQuery> getListTopicChat() {
		return listTopicChat;
	}

	public void setListTopicChat(List<TopicChatQuery> listTopicChat) {
		this.listTopicChat = listTopicChat;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPic() {
		return memberPic;
	}

	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}

	public String getMemberDesc() {
		return memberDesc;
	}

	public void setMemberDesc(String memberDesc) {
		this.memberDesc = memberDesc;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public List<MeetingPic> getListMeetingPic() {
		return listMeetingPic;
	}

	public void setListMeetingPic(List<MeetingPic> listMeetingPic) {
		this.listMeetingPic = listMeetingPic;
	}

	public List<MeetingFile> getListMeetingFile() {
		return listMeetingFile;
	}

	public void setListMeetingFile(List<MeetingFile> listMeetingFile) {
		this.listMeetingFile = listMeetingFile;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}

	public MeetingTopic createMeetingTopic() {
		MeetingTopic meetingTopic = new MeetingTopic();
		meetingTopic.setId(id);
		meetingTopic.setMeetingId(meetingId);
		meetingTopic.setTopicContent(topicContent);
		meetingTopic.setTopicStartTime(topicStartTime);
		meetingTopic.setTopicEndTime(topicEndTime);
		meetingTopic.setTopicDesc(topicDesc);
		meetingTopic.setTaskId(taskId);
		meetingTopic.setMemberId(memberId);
		meetingTopic.setMemberName(memberName);
		meetingTopic.setMemberPic(memberPic);
		meetingTopic.setMemberDesc(memberDesc);
		meetingTopic.setCreateId(createId);
		meetingTopic.setCreateName(createName);
		meetingTopic.setCreateTime(createTime);
		meetingTopic.setUpdateTime(updateTime);
		meetingTopic.setIsFinished(isFinished);
		return meetingTopic;
	}
}

