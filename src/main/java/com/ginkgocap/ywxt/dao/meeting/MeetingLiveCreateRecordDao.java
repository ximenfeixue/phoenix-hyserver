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
    MeetingLiveCreateRecord save(MeetingLiveCreateRecord meetingLiveCreateRecord) throws Exception;

    /**
     * select
     * @param meetingId
     * @return
     */
    List<MeetingLiveCreateRecord> getByMeetingId(final Long meetingId);

    /**
     * updateRemainDurationById
     * @param remainDuration
     * @param id
     * @return
     */
    boolean updateRemainDurationById(final long remainDuration, final long id);

    /**
     * getById
     * @param id
     * @return
     */
    MeetingLiveCreateRecord getById(final long id);
}
