package com.ginkgocap.ywxt.dao.meeting.support;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class DynamicSqlSessionDaoSupport extends SqlSessionDaoSupport implements ApplicationContextAware {
	@Autowired
	private ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	private SqlSessionFactory dynamicSqlSessionFactory;
	private SqlSession sqlSession;
	
	public final SqlSession getDynamicSqlSession(String factory) {
		if(dynamicSqlSessionFactory == null) {
			dynamicSqlSessionFactory = (SqlSessionFactory)applicationContext.getBean(factory);
		}
		return getDynamicSqlSession();
    }
	
	public final SqlSession getDynamicSqlSession(SqlSessionFactory dynamicSqlSessionFactory) {
		setDynamicSqlSessionFactory(dynamicSqlSessionFactory);
        return getDynamicSqlSession();
    }
	
	public final SqlSession getDynamicSqlSession() {
		if(this.sqlSession == null) {
			this.sqlSession = new SqlSessionTemplate(dynamicSqlSessionFactory);
		}
        return this.sqlSession;
	}
	
	public void setDynamicSqlSessionFactory(SqlSessionFactory dynamicSqlSessionFactory) {
		this.dynamicSqlSessionFactory = dynamicSqlSessionFactory;
	}
}
