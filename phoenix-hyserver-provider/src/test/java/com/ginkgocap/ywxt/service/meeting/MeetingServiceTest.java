/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import static junit.framework.Assert.assertNotNull;
import javacommon.base.BaseManagerTestCase;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.utils.test.TestMethodContext;


public class MeetingServiceTest extends BaseManagerTestCase{

	private MeetingService manager;
	
	@Autowired
	public void setMeetingService(MeetingService manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/Meeting.xml",
                            "classpath:testdata/Meeting_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		Meeting obj = newMeeting();
		manager.saveOrUpdate(obj);
		assertNotNull(obj.getId());
		
		manager.delete(obj.getId());
	
	}
	
	public static Meeting newMeeting() {
		Meeting obj = new Meeting();
		
	  	obj.setMeetingName(new java.lang.String("1"));
	  	obj.setMeetingAddress(new java.lang.String("1"));
//	  	obj.setMeetingAddressPos(new java.lang.String("1"));
	  	obj.setStartTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setEndTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setMeetingType(new java.lang.Integer("1"));
	  	obj.setMeetingStatus(new java.lang.Integer("1"));
	  	obj.setIsSecrecy(new java.lang.Boolean("1"));
	  	obj.setMemberCount(new java.lang.Integer("1"));
	  	obj.setMeetingDesc(new java.lang.String("1"));
//	  	obj.setCrateId(new java.lang.Long("1"));
	  	obj.setTaskId(new java.lang.String("1"));
//	  	obj.setCrateTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
