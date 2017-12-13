package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.base.BaseTest;
import com.ginkgocap.ywxt.constant.MeetingLiveCreateTypeEnum;
import com.ginkgocap.ywxt.constant.MeetingLiveDurationTypeEnum;
import com.ginkgocap.ywxt.model.meeting.MeetingLiveCreateRecord;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * @author cinderella
 * @version 2017/12/7
 */
public class MeetingLiveCreateRecordDaoTest extends BaseTest {

    @Resource
    private MeetingLiveCreateRecordDao meetingLiveCreateRecordDao;

    @Test
    public void save() throws Exception {
        MeetingLiveCreateRecord meetingLiveCreateRecord = new MeetingLiveCreateRecord();
        meetingLiveCreateRecord.setMeetingId(3913L);
        meetingLiveCreateRecord.setChannelId("1e619c9e532341a9b953ad252abe4e21");
        meetingLiveCreateRecord.setRoomId(19816596L);
        meetingLiveCreateRecord.setCreateType(MeetingLiveCreateTypeEnum.CREATE_LIVE.getKey());
        meetingLiveCreateRecord.setDurationType(MeetingLiveDurationTypeEnum.HALF_HOUR.getKey());
        meetingLiveCreateRecord.setGmtCreate(new Date());
        meetingLiveCreateRecord.setUserCreate(1L);
        meetingLiveCreateRecord.setPayOrderId(123L);
        MeetingLiveCreateRecord save = meetingLiveCreateRecordDao.save(meetingLiveCreateRecord);
        System.out.println(save.toString());
    }

    @Test
    public void getByMeetingId() throws Exception {
        List<MeetingLiveCreateRecord> meetingLiveCreateRecordList = meetingLiveCreateRecordDao.getByMeetingId(3913L);
        for (MeetingLiveCreateRecord temp : meetingLiveCreateRecordList) {
            System.out.println(null == temp ? "" : temp.toString());
        }
    }

}