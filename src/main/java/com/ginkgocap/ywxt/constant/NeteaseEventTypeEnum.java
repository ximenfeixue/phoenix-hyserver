package com.ginkgocap.ywxt.constant;

/**
 * @author cinderella
 * @version 2017/12/14
 */
public enum NeteaseEventTypeEnum {

    LOGIN("2", "用户登录事件的消息"),
    LOGOUT("3", "用户登出事件的消息"),
    CHAT_ROOM("4", "聊天室中聊天的消息"),
    VEDIO_DataTunnel("5", "汇报实时音视频通话时长、白板事件时长的消息"),
    CHAT_ROOM_INOUT("9","汇报主播或管理员进出聊天室事件消息");

    private String key;
    private String value;

    NeteaseEventTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
