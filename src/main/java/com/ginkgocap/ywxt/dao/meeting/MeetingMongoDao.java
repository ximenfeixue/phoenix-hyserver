package com.ginkgocap.ywxt.dao.meeting;

import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignUpFormQuery;

import java.util.List;

/**
 * Created by wang fei on 2017/9/30.
 */
public interface MeetingMongoDao {

    /**
     * 保存会议表单
     * @param signUpFormQuery
     * @throws Exception
     */
    void saveMeetingSignForm(MeetingSignUpFormQuery signUpFormQuery) throws Exception;

    /**
     * 通过 meetingId 获取填写的会议表单列表
     * @param meetingId
     * @return
     * @throws Exception
     */
    List<MeetingSignUpFormQuery> getMeetingSignFormListByMeetingId(Long meetingId, int start, final int size) throws Exception;

    /**
     * 通过 meetingId 获取填写表单的 人数
     * @param meetingId
     * @return
     * @throws Exception
     */
    long countMeetingSignFormListByMeetingId(Long meetingId) throws Exception;

    /**
     * 通过 meetingId userId 删除报名信息
     * @param meetingId
     * @param userId
     * @throws Exception
     */
    void deleteMeetingByIdAndUserId(long meetingId, long userId) throws Exception;

    /**
     * 获取报名信息
     * @param meetingId
     * @param userId
     * @return
     * @throws Exception
     */
    MeetingSignUpFormQuery getMeetingSignFormByMeetingIdAndUserId(long meetingId, long userId) throws Exception;
}
