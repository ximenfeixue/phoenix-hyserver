/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import javacommon.base.BaseManagerTestCase;

import org.junit.Test;

import com.ginkgocap.ywxt.utils.test.TestMethodContext;


public class MeetingChatServiceTest extends BaseManagerTestCase{

//	private MeetingChatService manager;
//	
//	@Autowired
//	public void setMeetingChatService(MeetingChatService manager) {
//		this.manager = manager;
//	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingChat.xml",
                            "classpath:testdata/MeetingChat_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

//		MeetingChat obj = newMeetingChat();
//		manager.saveOrUpdate(obj);
//		assertNotNull(obj.getId());
//		
//		manager.delete(obj.getId());
	
	}
	
//	public static MeetingChat newMeetingChat() {
//		MeetingChat obj = new MeetingChat();
//		
//	  	obj.setMeetingId(new java.lang.Long("1"));
//	  	obj.setSenderType(new java.lang.Integer("1"));
//	  	obj.setTopicId(new java.lang.Long("1"));
//	  	obj.setChatContent(new java.lang.String("1"));
//	  	obj.setChatType(new java.lang.Integer("1"));
//	  	obj.setMessageId(new java.lang.String("1"));
//	  	obj.setMemberId(new java.lang.Long("1"));
//	  	obj.setJtfileUrl(new java.lang.String("1"));
//	  	obj.setJtfileSuffixName(new java.lang.String("1"));
//	  	obj.setJtfileType(new java.lang.String("1"));
//	  	obj.setJtfileName(new java.lang.String("1"));
//	  	obj.setJtfileSize(new java.lang.Integer("1"));
//	  	obj.setJtFileModuleType(new java.lang.Integer("1"));
//	  	obj.setJtfileTaskId(new java.lang.String("1"));
//	  	obj.setPublishTime(new java.lang.String("1"));
//	  	obj.setJtFileReserved1(new java.lang.String("1"));
//	  	obj.setJtFileReserved2(new java.lang.String("1"));
//	  	obj.setJtFileReserved3(new java.lang.String("1"));
//		return obj;
//	}
}
