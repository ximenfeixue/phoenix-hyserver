/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;

import com.ginkgocap.ywxt.utils.test.TestMethodContext;

import static junit.framework.Assert.*;

import java.util.*;

import javacommon.base.*;


import com.ginkgocap.ywxt.model.meeting.*;
import com.ginkgocap.ywxt.dao.meeting.*;
import com.ginkgocap.ywxt.service.meeting.*;
import com.ginkgocap.ywxt.vo.query.meeting.*;
import com.ginkgocap.ywxt.utils.*;


public class MeetingTimeDaoTest extends BaseDaoTestCase{
	
	private MeetingTimeDao dao;
	
	@Autowired
	public void setMeetingTimeDao(MeetingTimeDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingTime.xml",
		                    "classpath:testdata/MeetingTime_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingTimeQuery newMeetingTimeQuery() {
		MeetingTimeQuery query = new MeetingTimeQuery();
	  	query.setMeetingId(new Long("1"));
	  	query.setStartTime(new Date("1"));
	  	query.setEndTime(new Date("1"));
		return query;
	}
	
}
