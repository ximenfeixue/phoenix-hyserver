package com.ginkgocap.ywxt.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author cinderella
 * @version 2017/12/14
 */
public class ChatRoomMessage implements Serializable{

    private static final long serialVersionUID = 4775116686631720919L;

    /**
     * 值为4，表示是聊天室消息
     */
    private String eventType;

    /**
     * 消息内容，若msgType为CUSTOM自定义消息，该字段为JSON格式；否则该字段为普通字符串类型
     */
    private String attach;

    /**
     * 第三方扩展字段, 格式不限，长度限制4096，字符串类型
     */
    private String ext;

    /**
     * 消息发送者的账号，字符串类型
     */
    private String fromAccount;

    /**
     * 发送者的头像，字符串类型
     */
    private String fromAvator;

    /**
     * 客户端类型： AOS、IOS、PC、WINPHONE、WEB、REST，字符串类型
     */
    private String fromClientType;

    /**
     * 发送者身份的扩展字段,开发者可以自定义，字符串类型
     */
    private String fromExt;

    /**
     * 发送方昵称，字符串类型
     */
    private String fromNick;

    /**
     * 消息发送的时间戳
     */
    private String msgTimestamp;

    /**
     * 消息类型：
         TEXT、
         PICTURE、
         AUDIO、
         VIDEO、
         LOCATION 、
         NOTIFICATION、
         FILE、 //文件消息
         NETCALL_AUDIO、 //网络电话音频聊天
         NETCALL_VEDIO、 //网络电话视频聊天
         DATATUNNEL_NEW、 //新的数据通道请求通知
         TIPS、 //提示类型消息
         CUSTOM //自定义消息
     */
    private String msgType;

    /**
     * 客户端生成的消息id
     */
    private String msgidClient;

    /**
     * 重发标记：0不是重发, 1是重发
     */
    private String resendFlag;

    /**
     * 消息发送者用户名片的最后更新时间，可转为Long型数据
     */
    private String roleInfoTimetag;

    /**
     * 消息所属的聊天室id，可转为Long型数据
     */
    private String roomId;

    /**
     * 标识是否被反垃圾，仅在被反垃圾时才有此字段，可转为Boolean类型数据
     */
    private String antispam;

    public ChatRoomMessage() {
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromAvator() {
        return fromAvator;
    }

    public void setFromAvator(String fromAvator) {
        this.fromAvator = fromAvator;
    }

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getFromExt() {
        return fromExt;
    }

    public void setFromExt(String fromExt) {
        this.fromExt = fromExt;
    }

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }

    public String getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(String msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgidClient() {
        return msgidClient;
    }

    public void setMsgidClient(String msgidClient) {
        this.msgidClient = msgidClient;
    }

    public String getResendFlag() {
        return resendFlag;
    }

    public void setResendFlag(String resendFlag) {
        this.resendFlag = resendFlag;
    }

    public String getRoleInfoTimetag() {
        return roleInfoTimetag;
    }

    public void setRoleInfoTimetag(String roleInfoTimetag) {
        this.roleInfoTimetag = roleInfoTimetag;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAntispam() {
        return antispam;
    }

    public void setAntispam(String antispam) {
        this.antispam = antispam;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
