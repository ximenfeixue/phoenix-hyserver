/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ginkgocap.ywxt.model.meeting.TopicChat;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.Utils;


public class TopicChatQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
	/** 内容序号 */
	private Long id;
	/** 会议序号 */
	private Long meetingId;
	/** 0-系统用户发送，此时userID无效；1-普通用户发送，具体用户此时参考meeting_id */
	private Integer senderType;
	/** 议题序号 */
	private Long topicId;
	/** 聊天内容 */
	private String chatContent;
	/** 聊天类型    0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement */
	private Integer chatType;
	/** 消息id串，客户端随机生成，每条记录唯一 */
	private String messageId;
	/** 人员ID */
	private Long memberId;
	//人员名称
	private String memberName;
	/** 文件地址 */
	private String jtfileUrl;
	/** 后缀名    jpg,png,amr,pdf等 */
	private String jtfileSuffixName;
	/** 文件类型 */
	private String jtfileType;
	/** 文件名 */
	private String jtfileName;
	/** 文件大小 */
	private Integer jtfileSize;
	/** 0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构 */
	private Integer jtFileModuleType;
	/** 附件id */
	private String jtfileTaskId;
	/** 发布时间 */
	private Date publishTime;
	/** 备用1 */
	private String jtFileReserved1;
	/** 备用2 */
	private String jtFileReserved2;
	/** 备用3 */
	private String jtFileReserved3;
	//用户头像地址
	private String memberPic;

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
	
	public Integer getSenderType() {
		return this.senderType;
	}
	
	public void setSenderType(Integer value) {
		this.senderType = value;
	}
	
	public Long getTopicId() {
		return this.topicId;
	}
	
	public void setTopicId(Long value) {
		this.topicId = value;
	}
	
	public String getChatContent() {
		return this.chatContent;
	}
	
	public void setChatContent(String value) {
		this.chatContent = value;
	}
	
	public Integer getChatType() {
		return this.chatType;
	}
	
	public void setChatType(Integer value) {
		this.chatType = value;
	}
	
	public String getMessageId() {
		return this.messageId;
	}
	
	public void setMessageId(String value) {
		this.messageId = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getJtfileUrl() {
		return this.jtfileUrl;
	}
	
	public void setJtfileUrl(String value) {
		this.jtfileUrl = value;
	}
	
	public String getJtfileSuffixName() {
		return this.jtfileSuffixName;
	}
	
	public void setJtfileSuffixName(String value) {
		this.jtfileSuffixName = value;
	}
	
	public String getJtfileType() {
		return this.jtfileType;
	}
	
	public void setJtfileType(String value) {
		this.jtfileType = value;
	}
	
	public String getJtfileName() {
		return this.jtfileName;
	}
	
	public void setJtfileName(String value) {
		this.jtfileName = value;
	}
	
	public Integer getJtfileSize() {
		return this.jtfileSize;
	}
	
	public void setJtfileSize(Integer value) {
		this.jtfileSize = value;
	}
	
	public Integer getJtFileModuleType() {
		return this.jtFileModuleType;
	}
	
	public void setJtFileModuleType(Integer value) {
		this.jtFileModuleType = value;
	}
	
	public String getJtfileTaskId() {
		return this.jtfileTaskId;
	}
	
	public void setJtfileTaskId(String value) {
		this.jtfileTaskId = value;
	}
	
	public Date getPublishTime() {
		return this.publishTime;
	}
	
	public void setPublishTime(Date value) {
		this.publishTime = value;
	}
	
	public String getJtFileReserved1() {
		return this.jtFileReserved1;
	}
	
	public void setJtFileReserved1(String value) {
		this.jtFileReserved1 = value;
	}
	
	public String getJtFileReserved2() {
		return this.jtFileReserved2;
	}
	
	public void setJtFileReserved2(String value) {
		this.jtFileReserved2 = value;
	}
	
	public String getJtFileReserved3() {
		return this.jtFileReserved3;
	}
	
	public void setJtFileReserved3(String value) {
		this.jtFileReserved3 = value;
	}
	
	public String getMemberPic() {
		return memberPic;
	}

	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
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
			.append("MeetingId",getMeetingId())
			.append("SenderType",getSenderType())
			.append("TopicId",getTopicId())
			.append("ChatContent",getChatContent())
			.append("ChatType",getChatType())
			.append("MessageId",getMessageId())
			.append("MemberId",getMemberId())
			.append("JtfileUrl",getJtfileUrl())
			.append("JtfileSuffixName",getJtfileSuffixName())
			.append("JtfileType",getJtfileType())
			.append("JtfileName",getJtfileName())
			.append("JtfileSize",getJtfileSize())
			.append("JtFileModuleType",getJtFileModuleType())
			.append("JtfileTaskId",getJtfileTaskId())
			.append("PublishTime",getPublishTime())
			.append("JtFileReserved1",getJtFileReserved1())
			.append("JtFileReserved2",getJtFileReserved2())
			.append("JtFileReserved3",getJtFileReserved3())
			.toString();
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
			.append("'meetingId':'"+getMeetingId()+"',")
			.append("'senderType':'"+getSenderType()+"',")
			.append("'topicId':'"+getTopicId()+"',")
			.append("'chatContent':'"+getChatContent()+"',")
			.append("'chatType':'"+getChatType()+"',")
			.append("'messageId':'"+getMessageId()+"',")
			.append("'memberId':'"+getMemberId()+"',")
			.append("'jtfileUrl':'"+getJtfileUrl()+"',")
			.append("'jtfileSuffixName':'"+getJtfileSuffixName()+"',")
			.append("'jtfileType':'"+getJtfileType()+"',")
			.append("'jtfileName':'"+getJtfileName()+"',")
			.append("'jtfileSize':'"+getJtfileSize()+"',")
			.append("'jtFileModuleType':'"+getJtFileModuleType()+"',")
			.append("'jtfileTaskId':'"+getJtfileTaskId()+"',")
			.append("'publishTime':'"+getPublishTime()+"',")
			.append("'jtFileReserved1':'"+getJtFileReserved1()+"',")
			.append("'jtFileReserved2':'"+getJtFileReserved2()+"',")
			.append("'jtFileReserved3':'"+getJtFileReserved3()+"',")
			.append("}}")
			.toString().replaceAll(",}", "}");
	}

	public static List<TopicChatQuery> copyList(List<TopicChat> listTopicChat, List<User> userList) throws Exception {
		List<TopicChatQuery> listTopicChatQuery = null;
		//用户Map
		Map<String, User> userMap = new HashMap<String, User>();
		for(User user : userList) {
			userMap.put(""+user.getId(), user);
		}
		if(!Utils.isNullOrEmpty(listTopicChat)) {
			listTopicChatQuery = new ArrayList<TopicChatQuery>();
			for(TopicChat topicChat : listTopicChat) {
				TopicChatQuery topicChatQuery = new TopicChatQuery();
				BeanUtils.copyProperties(topicChatQuery, topicChat);
				//设置用户头像
				if(!Utils.isNullOrEmpty(topicChat.getMemberId())) {
					User user = userMap.get(""+topicChat.getMemberId());
					if(!Utils.isNullOrEmpty(user)) {
						topicChatQuery.setMemberName(user.getName());
						topicChatQuery.setMemberPic(user.getPicPath());
					}
				}
				listTopicChatQuery.add(topicChatQuery);
			}
		}
		return listTopicChatQuery;
	}
	
}

