package com.ginkgocap.ywxt.constant;

/**
 * Created by wang fei on 2017/12/19.
 */
public enum MeetingPayType {

    MEETING_PAY_TYPE(1, "活动基类支付"),
    MEETING_LIVE_PAY_TYPE(2, "活动直播支付");

    MeetingPayType(int value, String name) {
        this.name = name;
        this.value = value;
    }

    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
