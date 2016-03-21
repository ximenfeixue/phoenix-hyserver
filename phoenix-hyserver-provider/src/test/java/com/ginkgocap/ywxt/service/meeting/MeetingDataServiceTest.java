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


public class MeetingDataServiceTest extends BaseManagerTestCase{

	private MeetingDataService manager;
	
	@Autowired
	public void setMeetingDataService(MeetingDataService manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingData.xml",
                            "classpath:testdata/MeetingData_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		MeetingData obj = newMeetingData();
		manager.saveOrUpdate(obj);
		assertNotNull(obj.getId());
		
		manager.delete(obj.getId());
	
	}
	
	public static MeetingData newMeetingData() {
		MeetingData obj = new MeetingData();
		
	  	obj.setMeetingId(new java.lang.Long("1"));
	  	obj.setDataName(new java.lang.String("1"));
	  	obj.setDataId(new java.lang.Long("1"));
	  	obj.setDataType(new java.lang.Integer("1"));
	  	obj.setDataReqType(new java.lang.Integer("1"));
	  	obj.setDataUrl(new java.lang.String("1"));
		return obj;
	}
}
