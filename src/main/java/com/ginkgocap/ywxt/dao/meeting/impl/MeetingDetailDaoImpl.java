package com.ginkgocap.ywxt.dao.meeting.impl;

import com.ginkgocap.ywxt.dao.meeting.MeetingDetailDao;
import com.ginkgocap.ywxt.model.meeting.MeetingDetail;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xutlong on 2017/9/21.
 */
@Repository
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

    @Override
    public List<MeetingDetail> getMeetingDetailByMeetingId(Long id) {
        return getSqlSession().selectList("MeetingDetail.getByMeetingId",id);
    }

    @Override
    public int deleteByMeetingIdAndNotDetailIdList(Long meetingId, List<Long> meetingDetailIdList) {
        Map<String,Object> map = new HashMap();
        map.put("meetingId",meetingId);
        map.put("list",meetingDetailIdList);
        return getSqlSession().delete("MeetingDetail.deleteByMeetingIdAndNotDetailIdList",map);
    }
}
