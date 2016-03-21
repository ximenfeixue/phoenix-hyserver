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


public class MeetingMemberDaoTest extends BaseDaoTestCase{
	
	private MeetingMemberDao dao;
	
	@Autowired
	public void setMeetingMemberDao(MeetingMemberDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingMember.xml",
		                    "classpath:testdata/MeetingMember_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingMemberQuery newMeetingMemberQuery() {
		MeetingMemberQuery query = new MeetingMemberQuery();
	  	query.setMemberId(new Long("1"));
	  	query.setMeetingId(new Long("1"));
	  	query.setMemberType(new Integer("1"));
	  	query.setMemberName(new String("1"));
	  	query.setMemberPhoto(new String("1"));
	  	query.setMemberMeetStatus(new Integer("1"));
	  	query.setAttendMeetStatus(new Integer("1"));
	  	query.setAttendMeetType(new Integer("1"));
		return query;
	}
	
}
