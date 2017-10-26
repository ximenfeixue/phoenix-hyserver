package com.ginkgocap.ywxt.vo.query.meeting;


/**
 * Created by wang fei on 2017/10/26.
 */
public class MeetingPayQuery{

    // 活动 id
    private Long meetingId;
    // 支付类型  1微信 2支付宝
    private Integer type;
    // 是否 web 端登录
    private Integer web;

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWeb() {
        return web;
    }

    public void setWeb(Integer web) {
        this.web = web;
    }
}
