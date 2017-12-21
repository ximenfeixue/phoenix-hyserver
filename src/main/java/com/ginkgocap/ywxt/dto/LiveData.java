package com.ginkgocap.ywxt.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cinderella
 * @version 2017/12/20
 */
public class LiveData implements Serializable{
    private static final long serialVersionUID = 1786938495879804327L;

    private Date liveStartTime;

    private Long meetingId;

    private Long liveRemainDuration;

    private Date lastAccessTime;

    private Date thisAccessTime;

    public LiveData() {
    }

    public Date getLiveStartTime() {
        return liveStartTime;
    }

    public void setLiveStartTime(Date liveStartTime) {
        this.liveStartTime = liveStartTime;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public Long getLiveRemainDuration() {
        return liveRemainDuration;
    }

    public void setLiveRemainDuration(Long liveRemainDuration) {
        this.liveRemainDuration = liveRemainDuration;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Date getThisAccessTime() {
        return thisAccessTime;
    }

    public void setThisAccessTime(Date thisAccessTime) {
        this.thisAccessTime = thisAccessTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
