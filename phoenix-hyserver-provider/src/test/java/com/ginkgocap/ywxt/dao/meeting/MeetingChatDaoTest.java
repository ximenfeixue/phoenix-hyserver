/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import javacommon.base.BaseDaoTestCase;

import com.ginkgocap.ywxt.utils.test.TestMethodContext;


public class MeetingChatDaoTest extends BaseDaoTestCase{
	
//	private MeetingChatDao dao;
//	
//	@Autowired
//	public void setMeetingChatDao(MeetingChatDao dao) {
//		this.dao = dao;
//	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/MeetingChat.xml",
		                    "classpath:testdata/MeetingChat_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
//	public static MeetingChatQuery newMeetingChatQuery() {
//		MeetingChatQuery query = new MeetingChatQuery();
//	  	query.setMeetingId(new Long("1"));
//	  	query.setSenderType(new Integer("1"));
//	  	query.setTopicId(new Long("1"));
//	  	query.setChatContent(new String("1"));
//	  	query.setChatType(new Integer("1"));
//	  	query.setMessageId(new String("1"));
//	  	query.setMemberId(new Long("1"));
//	  	query.setJtfileUrl(new String("1"));
//	  	query.setJtfileSuffixName(new String("1"));
//	  	query.setJtfileType(new String("1"));
//	  	query.setJtfileName(new String("1"));
//	  	query.setJtfileSize(new Integer("1"));
//	  	query.setJtFileModuleType(new Integer("1"));
//	  	query.setJtfileTaskId(new String("1"));
//	  	query.setPublishTime(new String("1"));
//	  	query.setJtFileReserved1(new String("1"));
//	  	query.setJtFileReserved2(new String("1"));
//	  	query.setJtFileReserved3(new String("1"));
//		return query;
//	}
	
}
