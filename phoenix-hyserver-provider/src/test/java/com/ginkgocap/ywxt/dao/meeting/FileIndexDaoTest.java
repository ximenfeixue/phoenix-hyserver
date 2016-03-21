/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.dao.meeting;

import java.util.Date;

import javacommon.base.BaseDaoTestCase;

import com.ginkgocap.ywxt.utils.test.TestMethodContext;
import com.ginkgocap.ywxt.vo.query.meeting.FileIndexQuery;


public class FileIndexDaoTest extends BaseDaoTestCase{
	
//	private FileIndexDao dao;
//	
//	@Autowired
//	public void setFileIndexDao(FileIndexDao dao) {
//		this.dao = dao;
//	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
		return new String[]{"classpath:testdata/common.xml","classpath:testdata/FileIndex.xml",
		                    "classpath:testdata/FileIndex_"+TestMethodContext.getMethodName()+".xml"};
	}
	
	
	static int pageNumber = 1;
	static int pageSize = 10;	
	public static FileIndexQuery newFileIndexQuery() {
		FileIndexQuery query = new FileIndexQuery();
	  	query.setFilePath(new String("1"));
	  	query.setFileTitle(new String("1"));
	  	query.setFileSize(new Long("1"));
	  	query.setStatus(new Boolean("1"));
	  	query.setAuthorId(new Long("1"));
	  	query.setMd5(new String("1"));
	  	query.setTaskId(new String("1"));
	  	query.setModuleType(new Integer("1"));
		query.setCtime(new Date(System.currentTimeMillis()));
	  	query.setAuthorName(new String("1"));
	  	query.setCrc32(new String("1"));
		return query;
	}
	
}
