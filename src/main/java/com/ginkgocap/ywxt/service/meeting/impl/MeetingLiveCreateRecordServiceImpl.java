package com.ginkgocap.ywxt.service.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.MeetingLiveCreateRecordDao;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingLiveCreateRecord;
import com.ginkgocap.ywxt.service.meeting.MeetingLiveCreateRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wang fei on 2017/12/20.
 */
@Service
public class MeetingLiveCreateRecordServiceImpl implements MeetingLiveCreateRecordService{

    @Autowired
    private MeetingLiveCreateRecordDao meetingLiveCreateRecordDao;

    @Override
    public MeetingLiveCreateRecord saveRecord(MeetingLiveCreateRecord meetingLiveCreateRecord) throws Exception {

        return meetingLiveCreateRecordDao.save(meetingLiveCreateRecord);
    }
}
