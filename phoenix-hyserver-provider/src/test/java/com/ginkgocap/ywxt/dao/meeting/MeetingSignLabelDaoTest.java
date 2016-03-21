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
import com.ginkgocap.ywxt.vo.query.meeting.MeetingSignLabelQuery;


public class MeetingSignLabelDaoTest extends BaseDaoTestCase{
	
	private MeetingSignLabelDao dao;
	
	@Autowired
	public void setMeetingSignLabelDao(MeetingSignLabelDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingSignLabel.xml",
		                    "classpath:testdata/MeetingSignLabel_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingSignLabelQuery newMeetingSignLabelQuery() {
		MeetingSignLabelQuery query = new MeetingSignLabelQuery();
	  	query.setMeetingId(new Long("1"));
	  	query.setLabelName(new String("1"));
	  	query.setIsCustom(new Integer("1"));
	  	query.setCreateId(new Long("1"));
	  	query.setCreateName(new String("1"));
//		query.setCreateTimeBegin(new Date(System.currentTimeMillis()));
//		query.setCreateTimeEnd(new Date(System.currentTimeMillis()));
		return query;
	}
	
}
