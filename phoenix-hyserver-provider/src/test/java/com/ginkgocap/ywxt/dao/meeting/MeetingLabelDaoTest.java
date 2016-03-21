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
import com.ginkgocap.ywxt.vo.query.meeting.MeetingLabelQuery;


public class MeetingLabelDaoTest extends BaseDaoTestCase{
	
	private MeetingLabelDao dao;
	
	@Autowired
	public void setMeetingLabelDao(MeetingLabelDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingLabel.xml",
		                    "classpath:testdata/MeetingLabel_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingLabelQuery newMeetingLabelQuery() {
		MeetingLabelQuery query = new MeetingLabelQuery();
	  	query.setLabelName(new String("1"));
	  	query.setCreateId(new Long("1"));
	  	query.setCreateName("1");
		query.setCreateTime(new Date(System.currentTimeMillis()));
		return query;
	}
	
}
