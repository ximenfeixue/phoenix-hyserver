package com.ginkgocap.ywxt.vo.query.meeting;

import com.ginkgocap.ywxt.model.meeting.MeetingMember;

import java.io.Serializable;

/**
 * Created by wang fei on 2017/10/25.
 */
public class MeetingMemberOrderQuery extends MeetingMemberQuery implements Serializable{

    private static final long serialVersionUID = 3148176768559230872L;
    // 订单号
    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
