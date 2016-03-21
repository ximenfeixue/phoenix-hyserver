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

import com.ginkgocap.ywxt.model.meeting.MeetingLabel;
import com.ginkgocap.ywxt.utils.test.TestMethodContext;


public class MeetingLabelServiceTest extends BaseManagerTestCase{

	private MeetingLabelService manager;
	
	@Autowired
	public void setMeetingLabelService(MeetingLabelService manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingLabel.xml",
                            "classpath:testdata/MeetingLabel_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		MeetingLabel obj = newMeetingLabel();
		manager.saveOrUpdate(obj);
		assertNotNull(obj.getId());
		
		manager.delete(obj.getId());
	
	}
	
	public static MeetingLabel newMeetingLabel() {
		MeetingLabel obj = new MeetingLabel();
		
	  	obj.setLabelName(new java.lang.String("1"));
	  	obj.setCreateId(new java.lang.Long("1"));
//	  	obj.setCreateName(new java.lang.Long("1"));
	  	obj.setCreateTime(new java.util.Date(System.currentTimeMillis()));
		return obj;
	}
}
