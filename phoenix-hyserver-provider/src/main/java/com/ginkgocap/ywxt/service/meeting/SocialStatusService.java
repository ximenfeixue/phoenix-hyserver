package com.ginkgocap.ywxt.service.meeting;

import java.util.List;

import com.ginkgocap.ywxt.model.meeting.SocialStatus;

public interface SocialStatusService {
	/**
	 * @param social
	 * @return
	 */
	public List<SocialStatus> queryList(SocialStatus social);

	/**
	 * 
	 * @param social
	 * @return
	 */
	public void save(SocialStatus social);

	/**
	 * 
	 * @param social
	 * @return
	 */
	public void delete(SocialStatus social);
	
	
	public List<SocialStatus> queryListWithoutMeetingByUserId(Long userId);
	
	public List<SocialStatus> queryMeetingListByUserId(Long userId);
	
}
