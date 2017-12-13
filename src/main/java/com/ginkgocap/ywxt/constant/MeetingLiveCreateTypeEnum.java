package com.ginkgocap.ywxt.constant;

/**
 * @author cinderella
 * @version 2017/12/13
 */
public enum MeetingLiveCreateTypeEnum {

    CREATE_LIVE(1, "创建直播"),
    RENEW_LIVE(2, "续费直播");

    private int key;
    private String value;

    MeetingLiveCreateTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
