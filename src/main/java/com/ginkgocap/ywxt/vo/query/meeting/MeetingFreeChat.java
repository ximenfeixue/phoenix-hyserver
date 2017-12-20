package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang fei on 2017/11/22.
 */
public class MeetingFreeChat implements Serializable{

    private static final long serialVersionUID = 3148176768559230872L;

    private Long meetingId;

    private MeetingQuery meetingQuery;

    private List<Long> userIds;

    private Long ownerId;

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public MeetingQuery getMeetingQuery() {
        return meetingQuery;
    }

    public void setMeetingQuery(MeetingQuery meetingQuery) {
        this.meetingQuery = meetingQuery;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
