package com.ginkgocap.ywxt.service.meeting;

import com.ginkgocap.ywxt.model.meeting.MeetingLiveCreateRecord;

/**
 * Created by wang fei on 2017/12/20.
 */
public interface MeetingLiveCreateRecordService {


    MeetingLiveCreateRecord saveRecord(MeetingLiveCreateRecord meetingLiveCreateRecord) throws Exception;
}
