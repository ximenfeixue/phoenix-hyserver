package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.model.meeting.MeetingLiveCreateRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cinderella
 * @version 2017/12/7
 */
@Repository
public interface MeetingLiveCreateRecordDao {

    /**
     * save
     * @param meetingLiveCreateRecord
     * @return
     */
    MeetingLiveCreateRecord save(MeetingLiveCreateRecord meetingLiveCreateRecord);

    /**
     * select
     * @param meetingId
     * @return
     */
    List<MeetingLiveCreateRecord> getByMeetingId(final Long meetingId);
}
