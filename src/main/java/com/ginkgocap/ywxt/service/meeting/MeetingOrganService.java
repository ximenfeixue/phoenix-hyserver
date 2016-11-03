/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.MeetingOrgan;

public interface MeetingOrganService {
	/**
	 * 根据会议ID列表获取相关组织
	 * @param meetingIdList
	 * @return
	 */
	public List<MeetingOrgan> getByMeetingIdList(List<Long> meetingIdList);
	
}