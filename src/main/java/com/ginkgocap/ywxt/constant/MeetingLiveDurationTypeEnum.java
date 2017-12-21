package com.ginkgocap.ywxt.constant;

/**
 * @author cinderella
 * @version 2017/12/7
 */
public enum MeetingLiveDurationTypeEnum {

    TWENTY_MINUTES(1, 20 * 60, "20分钟"),

    HALF_HOUR(2, 30 * 60, "30分钟"),

    FORTY_MINUTES(3, 40 * 60, "40分钟"),

    ONE_HOUR(4, 60 * 60, "60分钟");

    private int key;
    private long value;
    private String name;

    MeetingLiveDurationTypeEnum(int key, long value, String name) {
        this.key = key;
        this.value = value;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
