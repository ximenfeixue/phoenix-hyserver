package com.ginkgocap.ywxt.dao.meeting;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.model.meeting.SocialStatus;

@Repository
public interface SocialStatusDao {
	/**
	 * @param social
	 * @return
	 */
	public List<SocialStatus> queryList(SocialStatus social);
	
	public List<SocialStatus> queryListWithoutMeetingByUserId(Long userId);
	
	public List<SocialStatus> queryMeetingListByUserId(Long userId);

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

}
