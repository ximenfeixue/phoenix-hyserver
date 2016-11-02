/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.model.meeting;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import com.ginkgocap.ywxt.common.base.BaseEntity;


public class TopicChat extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "TopicChat";
	public static final String ALIAS_ID = "内容序号";
	public static final String ALIAS_MEETING_ID = "会议序号";
	public static final String ALIAS_SENDER_TYPE = "0-系统用户发送，此时userID无效；1-普通用户发送，具体用户此时参考meeting_id";
	public static final String ALIAS_TOPIC_ID = "议题序号";
	public static final String ALIAS_CHAT_CONTENT = "聊天内容";
	public static final String ALIAS_CHAT_TYPE = "聊天类型    0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement";
	public static final String ALIAS_MESSAGE_ID = "消息id串，客户端随机生成，每条记录唯一";
	public static final String ALIAS_MEMBER_ID = "人员ID";
	public static final String ALIAS_JTFILE_URL = "文件地址";
	public static final String ALIAS_JTFILE_SUFFIX_NAME = "后缀名    jpg,png,amr,pdf等";
	public static final String ALIAS_JTFILE_TYPE = "文件类型";
	public static final String ALIAS_JTFILE_NAME = "文件名";
	public static final String ALIAS_JTFILE_SIZE = "文件大小";
	public static final String ALIAS_JT_FILE_MODULE_TYPE = "0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构";
	public static final String ALIAS_JTFILE_TASK_ID = "附件id";
	public static final String ALIAS_PUBLISH_TIME = "发布时间";
	public static final String ALIAS_JT_FILE_RESERVED1 = "备用1";
	public static final String ALIAS_JT_FILE_RESERVED2 = "备用2";
	public static final String ALIAS_JT_FILE_RESERVED3 = "备用3";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 内容序号       db_column: id 
     */	
	
	private Long id;
    /**
     * 会议序号       db_column: meeting_id 
     */	
	
	private Long meetingId;
    /**
     * 0-系统用户发送，此时userID无效；1-普通用户发送，具体用户此时参考meeting_id       db_column: sender_Type 
     */	
	
	private Integer senderType;
    /**
     * 议题序号       db_column: topic_id 
     */	
	
	private Long topicId;
    /**
     * 聊天内容       db_column: chat_content 
     */	
	@Length(max=65535)
	private String chatContent;
    /**
     * 聊天类型    0-text；1-audio；2-image；3-video；4-file；5-JTContact(人脉）;6-knowledge(知识）;7-requirement       db_column: chat_type 
     */	
	
	private Integer chatType;
    /**
     * 消息id串，客户端随机生成，每条记录唯一       db_column: message_Id 
     */	
	@Length(max=255)
	private String messageId;
    /**
     * 人员ID       db_column: member_id 
     */	
	
	private Long memberId;
	 /**
     * 人员名字      
     */	
	private String memberName;
	//人员头像
	private String memberPic;
    /**
     * 文件地址       db_column: jtfile_url 
     */	
	@Length(max=65535)
	private String jtfileUrl;
    /**
     * 后缀名    jpg,png,amr,pdf等       db_column: jtfile_suffix_name 
     */	
	@Length(max=255)
	private String jtfileSuffixName;
    /**
     * 文件类型       db_column: jtfile_type 
     */	
	@Length(max=255)
	private String jtfileType;
    /**
     * 文件名       db_column: jtfile_name 
     */	
	@Length(max=255)
	private String jtfileName;
    /**
     * 文件大小       db_column: jtfile_size 
     */	
	
	private Integer jtfileSize;
    /**
     * 0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构       db_column: jtFile_Module_Type 
     */	
	
	private Integer jtFileModuleType;
    /**
     * 附件id       db_column: jtfile_task_id 
     */	
	@Length(max=255)
	private String jtfileTaskId;
    /**
     * 发布时间       db_column: publish_time 
     */	
	@Length(max=30)
	private java.util.Date publishTime;
    /**
     * 备用1       db_column: jtFile_Reserved1 
     */	
	@Length(max=255)
	private String jtFileReserved1;
    /**
     * 备用2       db_column: jtFile_Reserved2 
     */	
	@Length(max=255)
	private String jtFileReserved2;
    /**
     * 备用3       db_column: jtFile_Reserved3 
     */	
	@Length(max=255)
	private String jtFileReserved3;
	//columns END

	public TopicChat(){
	}

	public TopicChat(
		Long id
	){
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
	public void setSenderType(Integer value) {
		this.senderType = value;
	}
	
	public Integer getSenderType() {
		return this.senderType;
	}
	public void setTopicId(Long value) {
		this.topicId = value;
	}
	
	public Long getTopicId() {
		return this.topicId;
	}
	public void setChatContent(String value) {
		this.chatContent = value;
	}
	
	public String getChatContent() {
		return this.chatContent;
	}
	public void setChatType(Integer value) {
		this.chatType = value;
	}
	
	public Integer getChatType() {
		return this.chatType;
	}
	public void setMessageId(String value) {
		this.messageId = value;
	}
	
	public String getMessageId() {
		return this.messageId;
	}
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	public void setJtfileUrl(String value) {
		this.jtfileUrl = value;
	}
	
	public String getJtfileUrl() {
		return this.jtfileUrl;
	}
	public void setJtfileSuffixName(String value) {
		this.jtfileSuffixName = value;
	}
	
	public String getJtfileSuffixName() {
		return this.jtfileSuffixName;
	}
	public void setJtfileType(String value) {
		this.jtfileType = value;
	}
	
	public String getJtfileType() {
		return this.jtfileType;
	}
	public void setJtfileName(String value) {
		this.jtfileName = value;
	}
	
	public String getJtfileName() {
		return this.jtfileName;
	}
	public void setJtfileSize(Integer value) {
		this.jtfileSize = value;
	}
	
	public Integer getJtfileSize() {
		return this.jtfileSize;
	}
	public void setJtFileModuleType(Integer value) {
		this.jtFileModuleType = value;
	}
	
	public Integer getJtFileModuleType() {
		return this.jtFileModuleType;
	}
	public void setJtfileTaskId(String value) {
		this.jtfileTaskId = value;
	}
	
	public String getJtfileTaskId() {
		return this.jtfileTaskId;
	}
	public void setPublishTime(Date value) {
		this.publishTime = value;
	}
	
	public Date getPublishTime() {
		return this.publishTime;
	}
	public void setJtFileReserved1(String value) {
		this.jtFileReserved1 = value;
	}
	
	public String getJtFileReserved1() {
		return this.jtFileReserved1;
	}
	public void setJtFileReserved2(String value) {
		this.jtFileReserved2 = value;
	}
	
	public String getJtFileReserved2() {
		return this.jtFileReserved2;
	}
	public void setJtFileReserved3(String value) {
		this.jtFileReserved3 = value;
	}
	
	public String getJtFileReserved3() {
		return this.jtFileReserved3;
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
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TopicChat == false) return false;
		if(this == obj) return true;
		TopicChat other = (TopicChat)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

