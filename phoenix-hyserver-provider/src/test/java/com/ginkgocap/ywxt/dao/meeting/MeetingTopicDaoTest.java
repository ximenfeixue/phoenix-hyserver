/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import javacommon.base.BaseDaoTestCase;

import org.springframework.beans.factory.annotation.Autowired;

import com.ginkgocap.ywxt.utils.test.TestMethodContext;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingTopicQuery;


public class MeetingTopicDaoTest extends BaseDaoTestCase{
	
	private MeetingTopicDao dao;
	
	@Autowired
	public void setMeetingTopicDao(MeetingTopicDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingTopic.xml",
		                    "classpath:testdata/MeetingTopic_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingTopicQuery newMeetingTopicQuery() {
		MeetingTopicQuery query = new MeetingTopicQuery();
	  	query.setMeetingId(new Long("1"));
//	  	query.setTopicCoutent(new String("1"));
//	  	query.setTopicTime(new String("1"));
	  	query.setTopicDesc(new String("1"));
//	  	query.setTopicFileName(new String("1"));
//	  	query.setTopicFilePath(new String("1"));
	  	query.setTaskId(new String("1"));
	  	query.setMemberId(new Long("1"));
		return query;
	}
	
}
