package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;

/**
 * Created by wang fei on 2017/10/24.
 */
public class MeetingSignQuery implements Serializable{

    private static final long serialVersionUID = 3148176768559230811L;

    private Long meetingId;

    private String orderNumber;

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
