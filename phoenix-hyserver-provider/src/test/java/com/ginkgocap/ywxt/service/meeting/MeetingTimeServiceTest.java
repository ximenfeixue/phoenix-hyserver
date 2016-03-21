/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

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


public class MeetingTimeServiceTest extends BaseManagerTestCase{

	private MeetingTimeService manager;
	
	@Autowired
	public void setMeetingTimeService(MeetingTimeService manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingTime.xml",
                            "classpath:testdata/MeetingTime_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		MeetingTime obj = newMeetingTime();
		manager.saveOrUpdate(obj);
		assertNotNull(obj.getId());
		
		manager.delete(obj.getId());
	
	}
	
	public static MeetingTime newMeetingTime() {
		MeetingTime obj = new MeetingTime();
		
	  	obj.setMeetingId(new java.lang.Long("1"));
	  	obj.setStartTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setEndTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
