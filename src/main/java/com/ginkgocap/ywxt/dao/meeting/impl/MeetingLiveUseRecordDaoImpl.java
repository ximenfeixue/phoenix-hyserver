package com.ginkgocap.ywxt.dao.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.MeetingLiveUseRecordDao;
import com.ginkgocap.ywxt.model.meeting.MeetingLiveUseRecord;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cinderella
 * @version 2017/12/13
 */
@Repository
public class MeetingLiveUseRecordDaoImpl extends SqlSessionDaoSupport implements MeetingLiveUseRecordDao,ApplicationContextAware {

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
     * @param meetingLiveUseRecord
     * @return
     */
    @Override
    public MeetingLiveUseRecord save(MeetingLiveUseRecord meetingLiveUseRecord) {
        int insert = getSqlSession().insert("MeetingLiveUseRecord.insert", meetingLiveUseRecord);
        if (insert > 0) {
            return meetingLiveUseRecord;
        }
        return null;
    }

    /**
     * getByMeetingId
     *
     * @param meetingId
     * @return
     */
    @Override
    public List<MeetingLiveUseRecord> getByMeetingId(Long meetingId) {
        return getSqlSession().selectList("MeetingLiveUseRecord.getByMeetingId", meetingId);
    }

    /**
     * getByChannelId
     *
     * @param channelId
     * @return
     */
    @Override
    public List<MeetingLiveUseRecord> getByChannelId(String channelId) {
        return getSqlSession().selectList("MeetingLiveUseRecord.getByChannelId", channelId);
    }

}
