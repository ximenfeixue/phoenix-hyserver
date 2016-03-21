/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-09-14
 * @version 1.0
 * @since 1.0
 */
package com.ginkgocap.ywxt.service.meeting;

import static junit.framework.Assert.assertNotNull;

import java.util.List;

import javacommon.base.BaseManagerTestCase;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ginkgocap.ywxt.file.model.FileIndex;
import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.util.DateFunc;
import com.ginkgocap.ywxt.utils.test.TestMethodContext;


public class FileIndexServiceTest extends BaseManagerTestCase{

	private FileIndexService manager;
	
	@Autowired
	public void setFileIndexService(FileIndexService manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/FileIndex.xml",
                            "classpath:testdata/FileIndex_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		FileIndex obj = newFileIndex();
	//	manager.saveOrUpdate(obj);
		assertNotNull(obj.getId());
		
	//	manager.delete(obj.getId());
	
	}
	
	public static FileIndex newFileIndex() {
		FileIndex obj = new FileIndex();
		
	  	obj.setFilePath(new java.lang.String("1"));
	  	obj.setFileTitle(new java.lang.String("1"));
	  	obj.setFileSize(new java.lang.Long("1"));
	  	obj.setStatus(new java.lang.Boolean("1"));
	  	obj.setAuthor(new java.lang.Long("1"));
	  	obj.setMd5(new java.lang.String("1"));
	  	obj.setTaskId(new java.lang.String("1"));
	  	obj.setModuleType(new java.lang.Integer("1"));
	  	obj.setCtime(DateFunc.getDate());
	  	obj.setAuthorName(new java.lang.String("1"));
	  	obj.setCrc32(new java.lang.String("1"));
		return obj;
	}
	
	@Test
	public void getByTaskId(){
		
		List<FileIndex> fis=manager.selectByTaskId("222222", "1");
		if(fis!=null&&fis.size()>0){
			for(FileIndex fi:fis){
				System.out.println(fi.getAuthorName()+fi.getCtime()+fi.getFileTitle());
			}
		}
	}
	
	
}
