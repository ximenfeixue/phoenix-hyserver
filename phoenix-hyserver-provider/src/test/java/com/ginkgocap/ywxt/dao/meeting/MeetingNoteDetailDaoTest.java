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


public class MeetingNoteDetailDaoTest extends BaseDaoTestCase{
	
	private MeetingNoteDetailDao dao;
	
	@Autowired
	public void setMeetingNoteDetailDao(MeetingNoteDetailDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingNoteDetail.xml",
		                    "classpath:testdata/MeetingNoteDetail_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingNoteDetailQuery newMeetingNoteDetailQuery() {
		MeetingNoteDetailQuery query = new MeetingNoteDetailQuery();
	  	query.setMeetingNoteId(new Long("1"));
	  	query.setMeetingNoteContent(new String("1"));
//		query.setMeetingNoteTimeBegin(new Date(System.currentTimeMillis()));
//		query.setMeetingNoteTimeEnd(new Date(System.currentTimeMillis()));
	  	query.setTaskId(new String("1"));
		return query;
	}
	
}
