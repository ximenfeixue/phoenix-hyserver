package com.ginkgocap.ywxt.dao.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.MeetingDetailDao;
import com.ginkgocap.ywxt.model.meeting.MeetingDetail;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by xutlong on 2017/9/21.
 */
public class MeetingDetailDaoImpl extends SqlSessionDaoSupport implements MeetingDetailDao,ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public Long save(MeetingDetail entity) {
        getSqlSession().insert("MeetingDetail.insert",entity);
        return entity.getId();
    }

    @Override
    public boolean update(MeetingDetail entity) {
        int count = getSqlSession().update("MeetingDetail.update",entity);
        return count > 0 ? true : false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean deleteByMeetingId(Long meetingId) {
        return false;
    }


}
