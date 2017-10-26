package com.ginkgocap.ywxt.service.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.MeetingMongoDao;
import com.ginkgocap.ywxt.service.meeting.MeetingMongoService;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignUpFormQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wang fei on 2017/9/30.
 */
@Service
public class MeetingMongoServiceImpl implements MeetingMongoService {

    @Autowired
    private MeetingMongoDao meetingMongoDao;

    @Override
    public void saveMeetingSignForm(MeetingSignUpFormQuery signUpFormQuery) throws Exception {

        meetingMongoDao.saveMeetingSignForm(signUpFormQuery);
    }

    @Override
    public List<MeetingSignUpFormQuery> getMeetingSignFormListByMeetingId(Long meetingId, int start, final int size) throws Exception {

        return meetingMongoDao.getMeetingSignFormListByMeetingId(meetingId, start, size);
    }

    @Override
    public void deleteMeetingByIdAndUserId(long meetingId, long userId) throws Exception {

        meetingMongoDao.deleteMeetingByIdAndUserId(meetingId, userId);
    }

    @Override
    public MeetingSignUpFormQuery getMeetingSignFormByMeetingIdAndUserId(long meetingId, long userId) throws Exception {

        return meetingMongoDao.getMeetingSignFormByMeetingIdAndUserId(meetingId, userId);
    }
}
