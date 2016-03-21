/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.Date;

import javacommon.base.BaseDaoTestCase;

import org.springframework.beans.factory.annotation.Autowired;

import com.ginkgocap.ywxt.utils.test.TestMethodContext;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingMemberListQuery;


public class MeetingDaoTest extends BaseDaoTestCase{
	
	private MeetingDao dao;
	
	@Autowired
	public void setMeetingDao(MeetingDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/Meeting.xml",
		                    "classpath:testdata/Meeting_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingMemberListQuery newMeetingQuery() {
		MeetingMemberListQuery query = new MeetingMemberListQuery();
	  	query.setMeetingName(new String("1"));
	  	query.setMeetingAddress(new String("1"));
	  	query.setMeetingAddressPosX(new String("1"));
	  	query.setMeetingAddressPosY(new String("1"));
	  	query.setStartTime(new Date("1"));
	  	query.setEndTime(new Date("1"));
	  	query.setMeetingType(new Integer("1"));
	  	query.setMeetingStatus(new Integer("1"));
	  	query.setIsSecrecy(new Boolean("1"));
	  	query.setMemberCount(new Integer("1"));
	  	query.setMeetingDesc(new String("1"));
	  	query.setCreateId(new Long("1"));
	  	query.setTaskId(new String("1"));
		query.setCreateTime(new Date(System.currentTimeMillis()));
		return query;
	}
	
}
