package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;

/**
 * Created by wang fei on 2017/9/30.
 */
public class MeetingCustom implements Serializable {

    private static final long serialVersionUID = 3148176768559230871L;

    private String name;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
