package com.ginkgocap.ywxt.dao.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.MeetingLiveDao;
import com.ginkgocap.ywxt.model.meeting.MeetingLive;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cinderella
 * @version 2017/12/7
 */
@Repository
public class MeetingLiveDaoImpl extends SqlSessionDaoSupport implements MeetingLiveDao,ApplicationContextAware {

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
     * @param meetingLive
     * @return
     */
    @Override
    public MeetingLive save(MeetingLive meetingLive) {
        int insert = getSqlSession().insert("MeetingLive.insert", meetingLive);
        if (insert > 0) {
            return meetingLive;
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
    public List<MeetingLive> getByMeetingId(final Long meetingId) {
        return getSqlSession().selectList("MeetingLive.getByMeetingId", meetingId);
    }
}
