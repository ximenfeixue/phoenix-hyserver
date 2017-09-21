package com.ginkgocap.ywxt.service.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.MeetingDetailDao;
import com.ginkgocap.ywxt.model.meeting.MeetingDetail;
import com.ginkgocap.ywxt.service.meeting.MeetingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xutlong on 2017/9/21.
 */
@Service
@Transactional
public class MeetingDetailServiceImpl implements MeetingDetailService {

    @Autowired
    private MeetingDetailDao meetingDetailDao;

    @Override
    public Long save(MeetingDetail entity) {
        return meetingDetailDao.save(entity);
    }

    @Override
    public boolean update(MeetingDetail entity) {
        return meetingDetailDao.update(entity);
    }

    @Override
    public boolean delete(Long id) {
        return meetingDetailDao.delete(id);
    }

    @Override
    public boolean deleteByMeetingId(Long meetingId) {
        return meetingDetailDao.deleteByMeetingId(meetingId);
    }
}
