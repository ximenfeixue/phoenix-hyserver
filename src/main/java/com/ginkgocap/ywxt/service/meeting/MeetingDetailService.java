package com.ginkgocap.ywxt.service.meeting;

import com.ginkgocap.ywxt.model.meeting.MeetingDetail;

/**
 * Created by xutlong on 2017/9/21.
 */
public interface MeetingDetailService {

    /**
     * 新增会议详情小模块
     * @param entity
     * @return
     */
    public Long save(MeetingDetail entity);

    /**
     * 修改会议详情小模块
     * @param entity
     * @return
     */
    public boolean update(MeetingDetail entity);

    /**
     * 根据id删除详情小模块
     * @param id
     * @return
     */
    public boolean delete(Long id);

    /**
     * 根据会议ID删除此会议下所有的详情小模块
     * @param meetingId
     * @return
     */
    public boolean deleteByMeetingId(Long meetingId);
}
