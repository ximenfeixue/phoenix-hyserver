/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.dao.meeting.MeetingOrganDao;
import com.ginkgocap.ywxt.model.meeting.MeetingData;
import com.ginkgocap.ywxt.model.meeting.MeetingOrgan;
import com.ginkgocap.ywxt.service.meeting.MeetingOrganService;

@Service
@Transactional
public class MeetingOrganServiceImpl implements MeetingOrganService {
	@Autowired
	private MeetingOrganDao meetingOrganDao;
	/**
	 * 批量查询会议相关组织
	 * @param meetingIdList
	 * @return
	 */
	public List<MeetingOrgan> getByMeetingIdList(List<Long> meetingIdList) {
		return meetingOrganDao.getByMeetingIdList(meetingIdList);
	}
}