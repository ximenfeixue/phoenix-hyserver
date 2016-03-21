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


public class ImMucmessageDaoTest extends BaseDaoTestCase{
	
	private ImMucmessageDao dao;
	
	@Autowired
	public void setImMucmessageDao(ImMucmessageDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/ImMucmessage.xml",
		                    "classpath:testdata/ImMucmessage_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static ImMucmessageQuery newImMucmessageQuery() {
		ImMucmessageQuery query = new ImMucmessageQuery();
	  	query.setMucid(new Integer("1"));
	  	query.setSenderId(new Integer("1"));
	  	query.setSenderType(new Integer("1"));
	  	query.setMsg(new String("1"));
	  	query.setMsgType(new Integer("1"));
		query.setTime(new Date(System.currentTimeMillis()));
	  	query.setMessageId(new String("1"));
	  	query.setJtFileUrl(new String("1"));
	  	query.setJtFileSuffixName(new String("1"));
	  	query.setJtFileType(new String("1"));
	  	query.setJtFileName(new String("1"));
	  	query.setJtFileSize(new Integer("1"));
	  	query.setJtFileModuleType(new Integer("1"));
	  	query.setJtFileTaskId(new String("1"));
	  	query.setJtFileReserved1(new String("1"));
	  	query.setJtFileReserved2(new String("1"));
	  	query.setJtFileReserved3(new String("1"));
	  	query.setSequence(new Integer("1"));
		return query;
	}
	
}
