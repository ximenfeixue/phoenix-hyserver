package com.ginkgocap.ywxt.model.meeting;

/**
 * Created by xutlong on 2017/9/21.
 */
public class MeetingDetail {

    // 详情ID
    private Long id;
    // 会议ID
    private Long meetingId;
    // 详情标题
    private String meetingDetailTitle;
    // 详情介绍
    private String meetingDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingDetailTitle() {
        return meetingDetailTitle;
    }

    public void setMeetingDetailTitle(String meetingDetailTitle) {
        this.meetingDetailTitle = meetingDetailTitle;
    }

    public String getMeetingDetail() {
        return meetingDetail;
    }

    public void setMeetingDetail(String meetingDetail) {
        this.meetingDetail = meetingDetail;
    }
}
