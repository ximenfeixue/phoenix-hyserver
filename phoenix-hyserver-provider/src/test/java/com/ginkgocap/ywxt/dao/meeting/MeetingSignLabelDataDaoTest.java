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
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignLabelDataQuery;


public class MeetingSignLabelDataDaoTest extends BaseDaoTestCase{
	
	private MeetingSignLabelDataDao dao;
	
	@Autowired
	public void setMeetingSignLabelDataDao(MeetingSignLabelDataDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingSignLabelData.xml",
		                    "classpath:testdata/MeetingSignLabelData_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingSignLabelDataQuery newMeetingSignLabelDataQuery() {
		MeetingSignLabelDataQuery query = new MeetingSignLabelDataQuery();
	  	query.setMslabelId(new Long("1"));
	  	query.setLabelContent(new String("1"));
	  	query.setMemberId(new Long("1"));
	  	query.setMemberName(new String("1"));
	  	query.setCreateId(new Long("1"));
	  	query.setCreateName(new String("1"));
//		query.setCreateTimeBegin(new Date(System.currentTimeMillis()));
//		query.setCreateTimeEnd(new Date(System.currentTimeMillis()));
		return query;
	}
	
}
