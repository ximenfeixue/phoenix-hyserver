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
import com.ginkgocap.ywxt.vo.query.meeting.MeetingPicQuery;


public class MeetingPicDaoTest extends BaseDaoTestCase{
	
	private MeetingPicDao dao;
	
	@Autowired
	public void setMeetingPicDao(MeetingPicDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingPic.xml",
		                    "classpath:testdata/MeetingPic_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static MeetingPicQuery newMeetingPicQuery() {
		MeetingPicQuery query = new MeetingPicQuery();
	  	query.setMeetingId(new Long("1"));
	  	query.setPicPath(new String("1"));
	  	query.setPicName(new String("1"));
	  	query.setPicRealName(new String("1"));
	  	query.setPicDesc(new String("1"));
//	  	query.setIshomePage(new String("1"));
	  	query.setCreateUserId(new Long("1"));
	  	query.setCreateUserName(new String("1"));
//		query.setCreateDateBegin(new Date(System.currentTimeMillis()));
//		query.setCreateDateEnd(new Date(System.currentTimeMillis()));
	  	query.setPicStatus(new String("1"));
	  	query.setPicDel(new String("1"));
		query.setUpdateDateBegin(new Date(System.currentTimeMillis()));
		query.setUpdateDateEnd(new Date(System.currentTimeMillis()));
		return query;
	}
	
}
