package com.ginkgocap.ywxt.dao.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.MeetingMongoDao;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignUpFormQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wang fei on 2017/9/30.
 */
@Repository
public class MeetingMongoDaoImpl implements MeetingMongoDao {

    private static final Logger logger = LoggerFactory.getLogger(MeetingMongoDaoImpl.class);

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveMeetingSignForm(MeetingSignUpFormQuery signUpFormQuery) throws Exception {

        mongoTemplate.save(signUpFormQuery, "meetingSignForm");
    }

    @Override
    public List<MeetingSignUpFormQuery> getMeetingSignFormListByMeetingId(Long meetingId, int start, final int size) throws Exception {

        if (meetingId < 0 || start < 0 || size < 0)
            throw new IllegalArgumentException("param is error");
        long count = countMeetingSignFormListByMeetingId(meetingId);
        int index = start * size;
        if (index > count) {
            logger.error("because of index > count , so return null. index :" + index + " count :" + count);
            return null;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("meetingId").is(meetingId));
        query.sort().on("createTime", Order.DESCENDING);
        query.skip(index);
        query.limit(size);
        return mongoTemplate.find(query, MeetingSignUpFormQuery.class, "meetingSignForm");
    }

    @Override
    public long countMeetingSignFormListByMeetingId(Long meetingId) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("meetingId").is(meetingId));
        return mongoTemplate.count(query, "meetingSignForm");
    }

    @Override
    public void deleteMeetingByIdAndUserId(long meetingId, long userId) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("meetingId").is(meetingId));
        query.addCriteria(Criteria.where("userId").is(userId));
        mongoTemplate.remove(query, "meetingSignForm");
    }

    @Override
    public MeetingSignUpFormQuery getMeetingSignFormByMeetingIdAndUserId(long meetingId, long userId) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("meetingId").is(meetingId));
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, MeetingSignUpFormQuery.class, "meetingSignForm");
    }
}
