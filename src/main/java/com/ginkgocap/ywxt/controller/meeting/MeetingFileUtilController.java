package com.ginkgocap.ywxt.controller.meeting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.h2.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ginkgocap.ywxt.file.model.FileIndex;
import com.ginkgocap.ywxt.file.service.FileIndexService;
import com.ginkgocap.ywxt.model.meeting.MeetingFile;
import com.ginkgocap.ywxt.model.meeting.MeetingPic;
import com.ginkgocap.ywxt.service.meeting.MeetingPicService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.util.DateFunc;
import com.ginkgocap.ywxt.utils.JsonToBean;
import com.ginkgocap.ywxt.utils.MeetingFilePrimaryKey;
import com.ginkgocap.ywxt.utils.Utils;

/**
 * 文件相关controller
 * 
 * @author jiangchao
 * 
 */
@Controller
@RequestMapping("/file")
public class MeetingFileUtilController  {
	@Resource
	//private FileIndexService meetingFileIndexServiceImpl;
	private FileIndexService fileIndexService;
	@Resource
	UserService userService;
	@Resource
	private MeetingPicService meetingPicService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 引导页面
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView leadTo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskId", "taskId-123456");
		map.put("moduleType", "1");
		map.put("uid", 1);
		String nginxRoot =(String)request.getSession().getServletContext().getAttribute("nginxRoot");
//		String nfsHome =(String)request.getSession().getServletContext().getAttribute("nfsHome");
		map.put("nginxRoot", nginxRoot);
		return new ModelAndView("/file/upload", map);
	}

	/**
	 * 完成上传动作
	 * 
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/goMeetingUpload.json", method = RequestMethod.POST)
	public Map<String, Object> goUpload(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
	 	String fileRealName = request.getParameter("file.name");
        String prefixPath = request.getParameter("prefix.path");
        String fullFilePath = request.getParameter("file.path");//上传文件在nginx中的路径
        String filePath = "";//文件在nfs的相对路径 例如:全路径 /webserver/uploadFile/cloudweb/000000001,相对路径就是cloudweb/000000001
        if(StringUtils.isNotBlank(fullFilePath) && StringUtils.isNotBlank(prefixPath)){
            filePath = StringUtils.remove(fullFilePath, prefixPath);
        }
        String md5 = request.getParameter("file.md5");
        String fileSize = request.getParameter("file.size");
        int size = StringUtils.isNotBlank(fileSize) ? Integer.parseInt(fileSize) : 0;
        String crc32 = request.getParameter("file.crc32");
        String taskId = request.getParameter("taskId");
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String moduleType = request.getParameter("moduleType");
        String uid = request.getParameter("uid");
        String setFileName=request.getParameter("setFileName");
        Map<String, Object> result = new HashMap<String, Object>();
    	Map<String, Object> responseDataMap = new HashMap<String, Object>();
    	Map<String, Object> notificationMap = new HashMap<String, Object>();
    	if(StringUtils.isBlank(uid)||StringUtils.isBlank(taskId)||StringUtils.isBlank(moduleType)){
    		responseDataMap.put("succeed", false);
    		responseDataMap.put("jtFile","");
			notificationMap.put("notifCode", "0002");
	    	notificationMap.put("notifInfo", "参数不全");
	    	result.put("responseData", responseDataMap);
	    	result.put("notification", notificationMap);
	    	return result;
    	}
        String fileId = MeetingFilePrimaryKey.getPrimaryKey();
        // 文件上传后在数据库中记录相应记录
        FileIndex fileIndex = new FileIndex();
        fileIndex.setId(fileId);
        fileIndex.setFilePath(filePath);
        fileIndex.setFileTitle(StringUtils.isNotBlank(setFileName)?setFileName:fileRealName);	
        fileIndex.setTaskId(taskId);
        fileIndex.setFileSize(Long.valueOf(size));
        fileIndex.setModuleType(Integer.parseInt(moduleType));
    	if(!Utils.isNullOrEmpty(userId)){
    		fileIndex.setAuthor(Long.valueOf(userId));
    	}
    	if(!Utils.isNullOrEmpty(name)){
 	        fileIndex.setAuthorName(name);
    	}
        fileIndex.setMd5(md5);
        fileIndex.setCrc32(crc32);
        fileIndex.setStatus(true);
        fileIndexService.insert(fileIndex);        
        MeetingFile meetingFile = null;
		try {
			meetingFile = JsonToBean.fileIndexToMeetingFile(fileIndex);
//			String path = request.getContextPath();  
//	        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
			String nginxRoot = (String) request.getSession().getServletContext().getAttribute("nginxRoot");
			String url = nginxRoot + filePath;
			meetingFile.setUrl(url);
	        responseDataMap.put("succeed", true);
			responseDataMap.put("jtFile", meetingFile);
			responseDataMap.put("id",fileIndex.getId());
			notificationMap.put("notifCode", "0001");
	    	notificationMap.put("notifInfo", "上传成功");
		} catch (Exception e) {
	        responseDataMap.put("succeed", false);
			responseDataMap.put("jtFile", meetingFile);
			responseDataMap.put("id", fileIndex.getId());
			notificationMap.put("notifCode", "0002");
	    	notificationMap.put("notifInfo", "上传失败："+e.getMessage());
	    	logger.error("上传文件失败", e);
		}
    	result.put("responseData", responseDataMap);
    	result.put("notification", notificationMap);
    	return result;
	}
	
	private String toPathEncoding(String charset, String fileName){
		try {
			return new String(fileName.getBytes(charset), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	//IE与firefox下载区分
	private String encode(HttpServletRequest request, String realfileName) {
		String agent = request.getHeader("USER-AGENT");
		try {
			//IE
			if (null != agent && -1 != agent.indexOf("MSIE")) {
				realfileName = URLEncoder.encode(realfileName, "UTF8").replaceAll("\\+", "%20");
				realfileName = new String(realfileName.getBytes("GBK"), "iso8859-1");
			// Firefox
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				realfileName = javax.mail.internet.MimeUtility.encodeText(realfileName, "UTF8", "B");
			}
		} catch (UnsupportedEncodingException e) {
			try {
				realfileName = new String(realfileName.getBytes("UTF-8"), "iso8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return realfileName;
	}
	
	@RequestMapping(value = "/nginx/download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String isMettingPic = request.getParameter("isMettingPic");
		if(StringUtils.isNotBlank(id)){
			if(!Utils.isNullOrEmpty(isMettingPic)&&"T".equalsIgnoreCase(isMettingPic)){
				// 获取会议图片对象
				MeetingPic meetingPic=meetingPicService.getById(Long.valueOf(id));
				id=Utils.isNullOrEmpty(meetingPic.getFileIndexId())?"0":String.valueOf(meetingPic.getFileIndexId());
			}
			//FileIndex fileIndex = meetingFileIndexServiceImpl.getById(Long.parseLong(id));
			FileIndex fileIndex = fileIndexService.selectByPrimaryKey(Long.parseLong(id));
			if(fileIndex != null) {
				setDownload(request, response,fileIndex);
			}
		}
	}
	
	private void setDownload(HttpServletRequest request, HttpServletResponse response,FileIndex fileIndex) {
		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("X-Accel-Redirect","/course_mobile/"+toPathEncoding("UTF-8", fileIndex.getFilePath()));
		response.setHeader("X-Accel-Charset", "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="+ encode(request, fileIndex.getFileTitle()));
		response.setContentLength(new Long(fileIndex.getFileSize()).intValue());
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		String isMettingPic=request.getParameter("isMettingPic");
		Map<String, Object> result = new HashMap<String, Object>();
    	Map<String, Object> responseDataMap = new HashMap<String, Object>();
    	Map<String, Object> notificationMap = new HashMap<String, Object>();
    	boolean succeed=false;
    	String notifCode="0002";
    	String notifInfo="删除失败";
		if(StringUtils.isNotBlank(id)){
			if(!Utils.isNullOrEmpty(isMettingPic)&&"T".equalsIgnoreCase(isMettingPic)){
				// 获取会议图片对象
				MeetingPic meetingPic=meetingPicService.getById(Long.valueOf(id));
				FileIndex fileIndex = fileIndexService.selectByPrimaryKey(Long.valueOf(id));
				try {
					meetingPicService.removeMeetingPic(meetingPic, fileIndex);
				} catch (Exception e) {
					e.printStackTrace();
				}
				succeed = true;
				notifCode="0001";
				notifInfo="删除成功";
			}else{
				FileIndex fileIndex = fileIndexService.selectByPrimaryKey(Long.valueOf(id));
				if (fileIndex != null) {
					fileIndexService.delete(Long.parseLong(fileIndex.getId()));
					succeed = true;
					notifCode="0001";
					notifInfo="删除成功";
				}
			}
		}
		responseDataMap.put("succeed", succeed);
		notificationMap.put("notifCode", notifCode);
    	notificationMap.put("notifInfo", notifInfo);
    	result.put("responseData", responseDataMap);
    	result.put("notification", notificationMap);
    	return result;
	}
	/**
	 * 完成上传用户头像动作
	 * 
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadMeetingImg.json", method = RequestMethod.POST)
	public Map<String, Object> uploadUserImg(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		Map<String, Object> result = new HashMap<String, Object>();
    	Map<String, Object> responseDataMap = new HashMap<String, Object>();
    	Map<String, Object> notificationMap = new HashMap<String, Object>();
		try {
			String fileRealName = request.getParameter("file.name");
	        String prefixPath = request.getParameter("prefix.path");
	        String fullFilePath = request.getParameter("file.path");//上传文件在nginx中的路径
	        String taskId = request.getParameter("taskId");
	        String  picDesc=request.getParameter("picDesc");
	        String filePath= "";//文件在nfs的相对路径 例如:全路径 /webserver/uploadFile/cloudweb/000000001,相对路径就是cloudweb/000000001
	        if(StringUtils.isNotBlank(fullFilePath) && StringUtils.isNotBlank(prefixPath)){
	            filePath = StringUtils.remove(fullFilePath, prefixPath);
	        }
	        String md5 = request.getParameter("file.md5");
	        String fileSize = request.getParameter("file.size");
	        int size = StringUtils.isNotBlank(fileSize) ? Integer.parseInt(fileSize) : 0;
	        String crc32 = request.getParameter("file.crc32");
//		    0:需求、1：业务需求、2：公司客户、3：公司项目、4：会员、5：名片 、6 公司名片 、7资讯、8客户、9人脉分享 、10机构
//		    RModuleCodeService  类中有moduleType分类
	        String uid = request.getParameter("uid");
	        User user=new User();
	        if(!Utils.isNullOrEmpty(uid)){
	        	 user = userService.selectByPrimaryKey(Long.parseLong(uid));
	        }
	    	if(StringUtils.isBlank(uid)){
	    		responseDataMap.put("succeed", false);
	    		responseDataMap.put("url","");
				notificationMap.put("notifCode", "0002");
		    	notificationMap.put("notifInfo", "参数不全");
	    	} else {
		        String fileId = MeetingFilePrimaryKey.getPrimaryKey();
		        // 文件上传后在数据库中记录相应记录
		        FileIndex fileIndex = new FileIndex();
		        fileIndex.setId(fileId);
		        fileIndex.setFilePath(filePath);
		        fileIndex.setFileTitle(fileRealName);
		        fileIndex.setTaskId(taskId);
		        fileIndex.setFileSize(Long.valueOf(size));
		        fileIndex.setModuleType(-1);
		        fileIndex.setCtime(DateFunc.getDate());
		        if(!Utils.isNullOrEmpty(user)){
	        		fileIndex.setAuthor(user.getId());
		 	        fileIndex.setAuthorName(user.getName());
	        	}
		        fileIndex.setMd5(md5);
		        fileIndex.setCrc32(crc32);
		        fileIndex.setStatus(true);
		        fileIndexService.insert(fileIndex);
		    	// 图片目的地址
		        String nginxRoot = (String) request.getSession().getServletContext().getAttribute("nginxRoot");
				String url = nginxRoot + "/meeting/download?id=" + fileIndex.getId();
				int width=0, height=0;
				//设置图片宽度和高度
//				BufferedImage bufferedImage = MeetingPicUtil.getBufferedImage(url);
//				if(!Utils.isNullOrEmpty(bufferedImage)) {
//					width = bufferedImage.getWidth();
//					height = bufferedImage.getHeight();
//				}
				MeetingPic fileImg = new MeetingPic();
				fileImg.setPicPath(url);
				fileImg.setCreateDate(new Date());
				fileImg.setFileIndexId(fileId);
				fileImg.setPicStatus("1");
				fileImg.setPicRealName(fileRealName);
				fileImg.setPicDesc(picDesc);
				fileImg.setWidth(""+width);
				fileImg.setHeight(""+height);
				if(!Utils.isNullOrEmpty(user)){
					fileIndex.setAuthor(user.getId());
				    fileIndex.setAuthorName(user.getName());
				}
				fileImg.setTaskId(taskId);
				responseDataMap.put("fileImg", fileImg);
		        responseDataMap.put("succeed", true);
		        responseDataMap.put("url", url);
				notificationMap.put("notifCode", "0001");
		    	notificationMap.put("notifInfo", "上传成功");
			}
		} catch (Exception e) {
			responseDataMap.put("succeed", false);
			responseDataMap.put("url", "");
			notificationMap.put("notifCode", "0002");
	    	notificationMap.put("notifInfo", "上传失败:"+e.getMessage());
	    	logger.error("上传失败", e);
		}
    	result.put("responseData", responseDataMap);
    	result.put("notification", notificationMap);
    	return result;
	}

	@RequestMapping(value = "/get/image/", method = RequestMethod.GET)
    public void getImage(@RequestParam String url,
             HttpServletRequest request,
             HttpServletResponse response)
            throws Exception{
    	// 禁止缓存
		response.reset();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		System.out.println("url="+url);
    	File file = new File(url);
    	System.out.println("fileName="+file.getName());
        if (file != null && file.exists()) {
            FileInputStream inputStream = null;
            OutputStream outs = null;
            try {
                response.setContentType("image/jpeg");
                inputStream = new FileInputStream(file);
                outs = response.getOutputStream();
                IOUtils.copy(inputStream, outs);
                return;
            } catch (Exception e) {
                logger.error("处理头像失败", e);
                e.printStackTrace();
            } finally {
                try {
                    outs.flush();
                    outs.close();
                    inputStream.close();
                } catch (Exception e) {
                    
                }
            }
        }
    }
}
