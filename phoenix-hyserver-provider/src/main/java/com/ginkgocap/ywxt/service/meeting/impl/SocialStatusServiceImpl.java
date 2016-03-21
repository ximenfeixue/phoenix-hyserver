package com.ginkgocap.ywxt.service.meeting.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.dao.meeting.SocialStatusDao;
import com.ginkgocap.ywxt.model.meeting.SocialStatus;
import com.ginkgocap.ywxt.service.meeting.SocialStatusService;

@Service
public class SocialStatusServiceImpl implements SocialStatusService {

	static final Logger LOGGER = LoggerFactory.getLogger(SocialStatusServiceImpl.class);

	@Autowired
	SocialStatusDao socialStatusMapper;

	@Override
	public List<SocialStatus> queryList(SocialStatus social) {
		return socialStatusMapper.queryList(social);
	}

	@Override
	public void save(SocialStatus social) {
		socialStatusMapper.save(social);
	}

	@Override
	public void delete(SocialStatus social) {
		socialStatusMapper.delete(social);
	}

	@Override
	public List<SocialStatus> queryListWithoutMeetingByUserId(Long userId) {
		return socialStatusMapper.queryListWithoutMeetingByUserId(userId);
	}

	@Override
	public List<SocialStatus> queryMeetingListByUserId(Long userId) {
		return socialStatusMapper.queryMeetingListByUserId(userId);
	}

}
