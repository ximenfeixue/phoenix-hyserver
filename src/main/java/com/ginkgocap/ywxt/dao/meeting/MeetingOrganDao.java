package com.ginkgocap.ywxt.dao.meeting;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.model.meeting.MeetingOrgan;

@Repository
public interface MeetingOrganDao {
	/**
	 * 根据ID查找
	 * @param property ID
	 * @return 会议组织
	 */
	public MeetingOrgan getById(Long property);
	/**
	 * 根据会议ID查找会议关联的组织
	 * @param meetingId
	 * @return 会议关联的组织列表
	 */
	public List<MeetingOrgan> getByMeetingId(Long meetingId);
	/**
	 * 批量查询会议相关组织
	 * @param meetingIdList
	 * @return
	 */
	public List<MeetingOrgan> getByMeetingIdList(List<Long> meetingIdList);
	/**
	 * 根据ID删除
	 * @param property 会议组织ID
	 */
	public void delete(Long property);
	/**
	 * 根据ID批量删除
	 * @param ids 会议组织ID列表
	 */
	public void delete(List<Long> ids);
	/**
	 * 根据会议ID删除
	 * @param meetingId 会议ID
	 */
	public void deleteByMeetingId(Long meetingId);
	/**
	 * 新增会议关联的组织
	 * @param meetingOrgan
	 */
	public void saveOrUpdate(MeetingOrgan meetingOrgan);
	/**
	 * 批量新增会议关联的组织
	 * @param meetingOrgan
	 */
	public void save(MeetingOrgan meetingOrgan);
}
