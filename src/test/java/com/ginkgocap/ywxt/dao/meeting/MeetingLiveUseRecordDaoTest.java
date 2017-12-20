package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.base.BaseTest;
import com.ginkgocap.ywxt.model.meeting.MeetingLiveUseRecord;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author cinderella
 * @version 2017/12/13
 */
public class MeetingLiveUseRecordDaoTest extends BaseTest {

    @Resource
    private MeetingLiveUseRecordDao meetingLiveUseRecordDao;

    @Test
    public void save() throws Exception {
        MeetingLiveUseRecord meetingLiveUseRecord = new MeetingLiveUseRecord();
        meetingLiveUseRecord.setChannelId("1e619c9e532341a9b953ad252abe4e21");
        meetingLiveUseRecord.setMeetingId(3913L);
        meetingLiveUseRecord.setUserCreate(1L);
        meetingLiveUseRecord.setStartTime(new Date());
        meetingLiveUseRecord.setEndTime(new Date(System.currentTimeMillis() + 60 * 60 * 1000));
        meetingLiveUseRecord.setGmtCreate(new Date());
        MeetingLiveUseRecord save = meetingLiveUseRecordDao.save(meetingLiveUseRecord);
        System.out.println(save.toString());
    }

    @Test
    public void getByMeetingId() throws Exception {
        List<MeetingLiveUseRecord> liveUseRecordList = meetingLiveUseRecordDao.getByMeetingId(3913L);
        for (MeetingLiveUseRecord temp : liveUseRecordList) {
            System.out.println(null == temp ? "" : temp.toString());
        }
    }

    @Test
    public void getByChannelId() throws Exception {
        List<MeetingLiveUseRecord> liveUseRecordList = meetingLiveUseRecordDao.getByChannelId("1e619c9e532341a9b953ad252abe4e21");
        for (MeetingLiveUseRecord temp : liveUseRecordList) {
            System.out.println(null == temp ? "" : temp.toString());
        }
    }

}