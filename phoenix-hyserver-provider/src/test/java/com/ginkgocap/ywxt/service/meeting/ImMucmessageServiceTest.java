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

import com.ginkgocap.ywxt.model.meeting.ImMucmessage;
import com.ginkgocap.ywxt.utils.test.TestMethodContext;


public class ImMucmessageServiceTest extends BaseManagerTestCase{

	private ImMucmessageService manager;
	
	@Autowired
	public void setImMucmessageService(ImMucmessageService manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/ImMucmessage.xml",
                            "classpath:testdata/ImMucmessage_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		ImMucmessage obj = newImMucmessage();
		manager.saveOrUpdate(obj);
		assertNotNull(obj.getId());
		
		manager.delete(Long.parseLong(""+obj.getId()));
	
	}
	
	public static ImMucmessage newImMucmessage() {
		ImMucmessage obj = new ImMucmessage();
		
	  	obj.setMucid(new java.lang.Integer("1"));
	  	obj.setSenderId(new java.lang.Integer("1"));
	  	obj.setSenderType(new java.lang.Integer("1"));
	  	obj.setMsg(new java.lang.String("1"));
	  	obj.setMsgType(new java.lang.Integer("1"));
	  	obj.setTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setMessageId(new java.lang.String("1"));
	  	obj.setJtFileUrl(new java.lang.String("1"));
	  	obj.setJtFileSuffixName(new java.lang.String("1"));
	  	obj.setJtFileType(new java.lang.String("1"));
	  	obj.setJtFileName(new java.lang.String("1"));
	  	obj.setJtFileSize(new java.lang.Integer("1"));
	  	obj.setJtFileModuleType(new java.lang.Integer("1"));
	  	obj.setJtFileTaskId(new java.lang.String("1"));
	  	obj.setJtFileReserved1(new java.lang.String("1"));
	  	obj.setJtFileReserved2(new java.lang.String("1"));
	  	obj.setJtFileReserved3(new java.lang.String("1"));
	  	obj.setSequence(new java.lang.Integer("1"));
		return obj;
	}
}
