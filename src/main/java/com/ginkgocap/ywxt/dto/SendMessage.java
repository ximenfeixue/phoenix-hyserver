package com.ginkgocap.ywxt.dto;

import com.gintong.ywxt.im.model.JTFile;
import com.google.gson.*;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发送消息请求
 * 
 * @author edwin
 *
 */
public class SendMessage {

    //1. web, 2, android, 3, ios
    private Short os;

    //客户端消息消息唯一序列号， 用户确认消息是否成功
    private Long sequence;

	/** 聊天的序号,id主键 */
	private Long jtContactID;
	/** 接受者id */
	private Long mucID;
	/** 接受者id */
	private Long topicId;

	//发送人Id
	private Long senderID;

	//发送人名字
	private String senderName;

	/** 消息类型 */
	/**
	 * 7:需求，11:知识，5:关系，2:图片，3:视频, 24: 创建抽奖 25: 参与抽奖 26: 添加文件
	 */
	private Integer type;

    /** 内容type */

	private Integer userType;

	private String text;
	/** 消息id串，客户端随机生成，每条记录唯一 */
	private String messageID;
	/** 当前聊天记录中最新的聊天记录发送时间，如果时间为null，返回最新的十条 */
	private Date fromTime;
	/** 消息顺序号：按群分组后的 */
	private Long fromIndex;
	/** 页面大小 */
	private Integer size;
	/** 文件对象 */
	private JTFile jtFile;
	/**
	 * @用户的id数组
	 */
	private Long[] ats = {};
	/**
	 * 畅聊、单聊、会议的名称
	 */
	private String chatName;
	/**
	 * 是否是阅后即焚消息标志, 0:不是 1:是
	 */
	private Integer modal = 0;

	public static final String TYPE_TEXT = "0";
	public static final String TYPE_AUDIO = "1";
	public static final String TYPE_IMAGE = "2";

    public Short getOs() {
        return os != null ? os.shortValue() : 1;
    }

    public void setOs(Short os) {
        this.os = os;
    }

    public Long getSequence() {
        return sequence != null ? sequence.longValue() : -1L;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

	public Long getJtContactID() {
		return jtContactID != null ? jtContactID.longValue() : 0L;
	}
	public void setJtContactID(Long jtContactID) {
		this.jtContactID = jtContactID;
	}
	public Long getMucID() {
		return mucID != null ? mucID.longValue() : 0L;
	}
	public void setMucID(Long mucID) {
		this.mucID = mucID;
	}

	public Long getSenderID() {
		return senderID != null ? senderID.longValue() : 0L;
	}

	public void setSenderID(Long senderID) {
		this.senderID = senderID;
	}


	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	public Integer getType() {
		return type != null ? type.intValue() : 0;
	}
	public void setType(Integer type) {
		this.type = type;
	}

    public Integer getUserType() {
        return userType != null ? userType.intValue() : 0;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Long getFromIndex() {
		return fromIndex != null ? fromIndex.longValue() : 0L;
	}
	public void setFromIndex(Long fromIndex) {
		this.fromIndex = fromIndex;
	}
	public Integer getSize() {
		return size != null ? size.intValue() : 0;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public JTFile getJtFile() {
		return jtFile;
	}
	public void setJtFile(JTFile jtFile) {
		this.jtFile = jtFile;
	}
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	public Long getTopicId() {
		return topicId != null ? topicId.longValue() : 0L;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Long[] getAts() {
		return ats;
	}
	public void setAts(Long[] ats) {
		this.ats = ats;
	}
	public String getChatName() {
		return chatName;
	}
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}
	
	public Integer getModal() {
		return modal != null ? modal.intValue() : 0;
	}
	public void setModal(Integer modal) {
		this.modal = modal;
	}

	public static void main(String[] args) {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			@Override
			public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
				try {
					return df.parse(json.getAsString());
				} catch (ParseException e) {
					return null;
				}
			}
		});

		gb.registerTypeAdapter(Long.class, new JsonDeserializer<Long>() {
			@Override
			public Long deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
				if (json.isJsonNull() || json.getAsString().length() == 0) {
					return null;
				}
				try {
					return json.getAsLong();
				} catch (NumberFormatException e) {
					return null;
				}
			}
		});

		gb.registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {
			@Override
			public Integer deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
					throws JsonParseException {
				if (json.isJsonNull() || json.getAsString().length() == 0) {
					return null;
				}

				try {
					return json.getAsInt();
				} catch (NumberFormatException e) {
					return null;
				}
			}
		});

		Gson gson = gb.create();

		String json = "{\"jtContactID\":\"441649\",\"mucID\":\"aa\",\"type\":\"0\",\"text\":\"[猪头]\",\"senderName\":\"张志刚\",\"messageID\":\"26506712660193443\", \"fromTime\":\"2016-03-28 18:29:49\",\"userType\":\"1\"}";
		SendMessage msg = gson.fromJson(json, SendMessage.class);
		System.out.println(ReflectionToStringBuilder.toString(msg));
	}
}
