package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.base.BaseTest;
import com.ginkgocap.ywxt.constant.MeetingLiveDurationTypeEnum;
import com.ginkgocap.ywxt.model.meeting.MeetingLive;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cinderella
 * @version 2017/12/7
 */
public class MeetingLiveDaoTest extends BaseTest {

    @Resource
    private MeetingLiveDao meetingLiveDao;

    @Test
    public void save() throws Exception {
        MeetingLive meetingLive = new MeetingLive();
        meetingLive.setMeetingId(3913L);
        meetingLive.setDurationType(MeetingLiveDurationTypeEnum.HALF_HOUR.getKey());
        meetingLive.setGmtCreate(new Date());
        meetingLive.setUserCreate(1L);
        meetingLive.setPayOrderId(123L);
        meetingLive.setRoomNum("110");
        MeetingLive save = meetingLiveDao.save(meetingLive);
        System.out.println(save.toString());
    }

    @Test
    public void getByMeetingId() throws Exception {
        List<MeetingLive> MeetingLiveList = meetingLiveDao.getByMeetingId(3913L);
        for (MeetingLive temp : MeetingLiveList) {
            System.out.println(null == temp ? "" : temp.toString());
        }
    }

}