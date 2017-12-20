package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.base.BaseTest;
import com.ginkgocap.ywxt.model.meeting.Live;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author cinderella
 * @version 2017/12/7
 */
public class MeetingDaoTest extends BaseTest{

    final static long ONE_HOUR = 1 * 60 * 60 * 1000;

    @Resource
    private MeetingDao meetingDao;

    @Test
    public void save() throws Exception {
        Meeting meeting = new Meeting();
        //live.setId(3913L);
        meeting.setLive(1);
        meeting.setLiveRoomNum("110");
        meeting.setLiveStartTime(new Date());
        meeting.setLiveEndTime(new Date(System.currentTimeMillis() +  ONE_HOUR));
        meetingDao.save(meeting);
    }

    @Test
    public void updateLive() throws Exception {
        Live live = new Live();
        live.setId(3913L);
        live.setLive(1);
        live.setLiveRoomNum("110");
        live.setLiveStartTime(new Date());
        live.setLiveEndTime(new Date(System.currentTimeMillis() +  ONE_HOUR));
        meetingDao.updateLive(live);
    }

    @Test
    public void updateLiveStatus() throws Exception {
        meetingDao.updateLiveStatus(3911L, 1);
    }

    @Test
    public void updateLiveEndTime() throws Exception {
        meetingDao.updateLiveEndTime(3912L, new Date(System.currentTimeMillis() + ONE_HOUR));
    }

}