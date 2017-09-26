package com.ginkgocap.ywxt.utils;

import java.util.ResourceBundle;

import com.ginkgocap.ywxt.file.model.FileIndex;
import com.ginkgocap.ywxt.model.meeting.MeetingFile;
import com.ginkgocap.ywxt.model.mobile.JTFile;

public class JsonToBean {

	public static JTFile fileIndexToJTfile(FileIndex fileIndex){
		JTFile jtFile=new JTFile();
		if(null!=fileIndex){
			jtFile.setFileName(fileIndex.getFileTitle());
			jtFile.setFileSize(fileIndex.getFileSize());
			jtFile.setModuleType(fileIndex.getModuleType()+"");
			String[] nameSplit=fileIndex.getFileTitle().split("\\.");
			jtFile.setSuffixName(nameSplit.length>0?nameSplit[nameSplit.length-1]:"");
			jtFile.setTaskId(fileIndex.getTaskId());
			ResourceBundle resourceBundle =  ResourceBundle.getBundle("application");
			String nginxRoot=resourceBundle.getString("nginx.root");
			jtFile.setUrl(nginxRoot+"/meeting/download?id="+fileIndex.getId());
		}
		return jtFile;
	}

	public static JTFile fileIndexToJTfile(com.ginkgocap.parasol.file.model.FileIndex fileIndex){
		JTFile jtFile=new JTFile();
		if(null!=fileIndex){
			jtFile.setFileName(fileIndex.getFileTitle());
			jtFile.setFileSize(fileIndex.getFileSize());
			jtFile.setModuleType(fileIndex.getModuleType()+"");
			String[] nameSplit=fileIndex.getFileTitle().split("\\.");
			jtFile.setSuffixName(nameSplit.length>0?nameSplit[nameSplit.length-1]:"");
			jtFile.setTaskId(fileIndex.getTaskId());
			ResourceBundle resourceBundle =  ResourceBundle.getBundle("application");
			String nginxRoot=resourceBundle.getString("nginx.root");
			jtFile.setUrl(nginxRoot+"/meeting/download?id="+fileIndex.getId());
		}
		return jtFile;
	}
	
	public static MeetingFile fileIndexToMeetingFile(FileIndex fileIndex){
		MeetingFile meetingFile = new MeetingFile();
		if(null != fileIndex){
			meetingFile.setFileIndexId(fileIndex.getId());
			meetingFile.setFileName(fileIndex.getFileTitle());
			meetingFile.setFileSize(fileIndex.getFileSize());
			meetingFile.setModuleType(Long.parseLong(fileIndex.getModuleType()+""));
			if(!Utils.isNullOrEmpty(fileIndex.getFileTitle())) {
				String[] nameSplit = fileIndex.getFileTitle().split("\\.");
				meetingFile.setSuffixName(nameSplit.length>0 ? nameSplit[nameSplit.length-1] : "");
			}
			meetingFile.setTaskId(fileIndex.getTaskId());
			ResourceBundle resourceBundle =  ResourceBundle.getBundle("application");
			String nginxRoot=resourceBundle.getString("nginx.root");
			meetingFile.setUrl(nginxRoot+"/meeting/download?id="+fileIndex.getId());
		}
		return meetingFile;
	}

	public static MeetingFile apiFileIndexToMeetingFile(com.ginkgocap.parasol.file.model.FileIndex fileIndex) {
		MeetingFile meetingFile = new MeetingFile();
		if(null != fileIndex){
			meetingFile.setFileIndexId(String.valueOf(fileIndex.getId()));
			meetingFile.setFileName(fileIndex.getFileTitle());
			meetingFile.setFileSize(fileIndex.getFileSize());
			meetingFile.setModuleType(Long.parseLong(fileIndex.getModuleType()+""));
			if(!Utils.isNullOrEmpty(fileIndex.getFileTitle())) {
				String[] nameSplit = fileIndex.getFileTitle().split("\\.");
				meetingFile.setSuffixName(nameSplit.length>0 ? nameSplit[nameSplit.length-1] : "");
			}
			meetingFile.setTaskId(fileIndex.getTaskId());
			ResourceBundle resourceBundle =  ResourceBundle.getBundle("application");
			String nginxRoot=resourceBundle.getString("dfs.path");
			meetingFile.setUrl(nginxRoot + fileIndex.getFilePath());
		}
		return meetingFile;
	}
}