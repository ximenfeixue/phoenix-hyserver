/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.SocialStatusDao;
import com.ginkgocap.ywxt.model.meeting.SocialStatus;

@Repository
public class SocialStatusDaoImpl extends SqlSessionDaoSupport implements SocialStatusDao, ApplicationContextAware {

	private static final String SQLMAP_NAMESPACE = "SocialStatus";

	@Autowired
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	@Override
	public List<SocialStatus> queryList(SocialStatus social) {
		return getSqlSession().selectList(SQLMAP_NAMESPACE + ".queryList", social);
	}

	@Override
	public void save(SocialStatus social) {
		getSqlSession().insert(SQLMAP_NAMESPACE + ".save", social);
	}

	@Override
	public void delete(SocialStatus social) {
		getSqlSession().delete(SQLMAP_NAMESPACE + ".delete", social);
	}

	@Override
	public List<SocialStatus> queryListWithoutMeetingByUserId(Long userId) {
		return getSqlSession().selectList(SQLMAP_NAMESPACE + ".queryListWithoutMeetingByUserId", userId);
	}

	@Override
	public List<SocialStatus> queryMeetingListByUserId(Long userId) {
		return getSqlSession().selectList(SQLMAP_NAMESPACE + ".queryMeetingListByUserId", userId);
	}
}
