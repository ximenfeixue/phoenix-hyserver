package com.ginkgocap.ywxt.dao.meeting.impl;

import com.ginkgocap.ywxt.constant.MeetingLiveCreateTypeEnum;
import com.ginkgocap.ywxt.constant.MeetingLiveDurationTypeEnum;
import com.ginkgocap.ywxt.dao.meeting.MeetingDao;
import com.ginkgocap.ywxt.dao.meeting.MeetingLiveCreateRecordDao;
import com.ginkgocap.ywxt.model.meeting.Live;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingLiveCreateRecord;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cinderella
 * @version 2017/12/7
 */
@Repository
public class MeetingLiveCreateRecordDaoImpl extends SqlSessionDaoSupport implements MeetingLiveCreateRecordDao,ApplicationContextAware {

    @Resource
    private MeetingDao meetingDao;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws ApplicationContextException {
        this.applicationContext = applicationContext;
    }

    /**
     * save
     *
     * @param meetingLiveCreateRecord
     * @return
     */
    @Override
    public MeetingLiveCreateRecord save(MeetingLiveCreateRecord meetingLiveCreateRecord) throws Exception{
        Meeting meeting = meetingDao.getById(meetingLiveCreateRecord.getMeetingId());
        if (null == meeting) {
            return null;
        }
        Long duration = meeting.getLiveRemainDuration();
        if (MeetingLiveDurationTypeEnum.HALF_HOUR.getKey() == meetingLiveCreateRecord.getDurationType()) {
            duration += MeetingLiveDurationTypeEnum.HALF_HOUR.getValue();
        }
        if (MeetingLiveDurationTypeEnum.ONE_HOUR.getKey() == meetingLiveCreateRecord.getDurationType()) {
            duration += MeetingLiveDurationTypeEnum.ONE_HOUR.getValue();
        }
        meetingLiveCreateRecord.setRemainDuration(duration);
        int insert = getSqlSession().insert("MeetingLiveCreateRecord.insert", meetingLiveCreateRecord);
        if (insert > 0) {
            if (MeetingLiveCreateTypeEnum.CREATE_LIVE.getKey() == meetingLiveCreateRecord.getCreateType()) {
                Live live = new Live();
                live.setId(meeting.getId());
                live.setLiveStartTime(new Date());
                live.setLive(1);
                live.setLiveChannelId(meetingLiveCreateRecord.getChannelId());
                live.setLiveRoomId(meetingLiveCreateRecord.getRoomId());
                live.setLiveStatus(1);
                live.setLiveRemainDuration(duration);
                meetingDao.updateLive(live);
            } else if (MeetingLiveCreateTypeEnum.RENEW_LIVE.getKey() == meetingLiveCreateRecord.getCreateType()) {
                meetingDao.updateLiveRemainDurationById(meeting.getId(), duration);
            } else {
                //状态非法
                throw new RuntimeException();
            }
            return meetingLiveCreateRecord;
        }
        return null;
    }

    /**
     * select
     *
     * @param meetingId
     * @return
     */
    @Override
    public List<MeetingLiveCreateRecord> getByMeetingId(final Long meetingId) {
        return getSqlSession().selectList("MeetingLiveCreateRecord.getByMeetingId", meetingId);
    }

    /**
     * updateRemainDurationById
     *
     * @param remainDuration
     * @param id
     * @return
     */
    @Override
    public boolean updateRemainDurationById(final long remainDuration, final long id) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("remainDuration", remainDuration);
        map.put("id", id);
        int update = getSqlSession().update("MeetingLiveCreateRecord.updateRemainDurationById", map);
        if (update > 0) {
            return true;
        }
        return false;
    }

    /**
     * getById
     *
     * @param id
     * @return
     */
    @Override
    public MeetingLiveCreateRecord getById(final long id) {
        return getSqlSession().selectOne("MeetingLiveCreateRecord.getById", id);
    }
}
