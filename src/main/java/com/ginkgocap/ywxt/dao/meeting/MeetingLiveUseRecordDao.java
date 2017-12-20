package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.model.meeting.MeetingLiveUseRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cinderella
 * @version 2017/12/13
 */
@Repository
public interface MeetingLiveUseRecordDao {

    /**
     * save
     * @param meetingLiveUseRecord
     * @return
     */
    MeetingLiveUseRecord save(MeetingLiveUseRecord meetingLiveUseRecord);

    /**
     * getByMeetingId
     * @param meetingId
     * @return
     */
    List<MeetingLiveUseRecord> getByMeetingId(final long meetingId);

    /**
     * getByChannelId
     * @param channelId
     * @return
     */
    List<MeetingLiveUseRecord> getByChannelId(final String channelId);
}
