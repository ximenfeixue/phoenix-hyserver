package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;

/**
 * Created by wang fei on 2017/9/27.
 *
 * 广告位 返回封装数据
 */
public class MeetingAdQuery implements Serializable{

    private static final long serialVersionUID = 3148276768559230877L;

    private Long id;

    private String meetingName;

    private String path;

    private Byte top;

    private Byte disable;

    private Byte meetingStatus;

    private String meetingDesc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Byte getTop() {
        return top;
    }

    public void setTop(Byte top) {
        this.top = top;
    }

    public Byte getDisable() {
        return disable;
    }

    public void setDisable(Byte disable) {
        this.disable = disable;
    }

    public Byte getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(Byte meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    public String getMeetingDesc() {
        return meetingDesc;
    }

    public void setMeetingDesc(String meetingDesc) {
        this.meetingDesc = meetingDesc;
    }
}
