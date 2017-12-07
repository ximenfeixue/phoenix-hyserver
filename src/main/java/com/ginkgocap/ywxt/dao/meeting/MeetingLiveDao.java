package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.model.meeting.MeetingLive;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cinderella
 * @version 2017/12/7
 */
@Repository
public interface MeetingLiveDao {

    /**
     * save
     * @param meetingLive
     * @return
     */
    MeetingLive save(MeetingLive meetingLive);

    /**
     * select
     * @param meetingId
     * @return
     */
    List<MeetingLive> getByMeetingId(final Long meetingId);
}
