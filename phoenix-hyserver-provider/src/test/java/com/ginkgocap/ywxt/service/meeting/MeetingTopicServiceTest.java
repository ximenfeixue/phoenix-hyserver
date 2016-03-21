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

import com.ginkgocap.ywxt.model.meeting.MeetingTopic;
import com.ginkgocap.ywxt.utils.test.TestMethodContext;


public class MeetingTopicServiceTest extends BaseManagerTestCase{

	private MeetingTopicService manager;
	
	@Autowired
	public void setMeetingTopicService(MeetingTopicService manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingTopic.xml",
                            "classpath:testdata/MeetingTopic_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		MeetingTopic obj = newMeetingTopic();
		manager.saveOrUpdate(obj);
		assertNotNull(obj.getId());
		
		manager.delete(obj.getId());
	
	}
	
	public static MeetingTopic newMeetingTopic() {
		MeetingTopic obj = new MeetingTopic();
		
	  	obj.setMeetingId(new java.lang.Long("1"));
//	  	obj.setTopicCoutent(new java.lang.String("1"));
//	  	obj.setTopicTime(new java.lang.String("1"));
	  	obj.setTopicDesc(new java.lang.String("1"));
//	  	obj.setTopicFileName(new java.lang.String("1"));
//	  	obj.setTopicFilePath(new java.lang.String("1"));
	  	obj.setTaskId(new java.lang.String("1"));
	  	obj.setMemberId(new java.lang.Long("1"));
		return obj;
	}
}
