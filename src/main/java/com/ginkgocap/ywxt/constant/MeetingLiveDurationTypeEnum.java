package com.ginkgocap.ywxt.constant;

/**
 * @author cinderella
 * @version 2017/12/7
 */
public enum MeetingLiveDurationTypeEnum {

    /**
     * 30分钟
     */
    HALF_HOUR(1, 30 * 60 * 1000),

    /**
     * 60分钟
     */
    ONE_HOUR(2, 60 * 60 * 1000);

    private int key;
    private long value;

    MeetingLiveDurationTypeEnum(int key, long value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
