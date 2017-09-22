package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.model.meeting.MeetingDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xutlong on 2017/9/21.
 */
@Repository
public interface MeetingDetailDao {

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

    /**
     * 根据会议Id获取各个小模块的数据
     * @param id
     * @return
     */
    public List<MeetingDetail> getMeetingDetailByMeetingId(Long id);
}
