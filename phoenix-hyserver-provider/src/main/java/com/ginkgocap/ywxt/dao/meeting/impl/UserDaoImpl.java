package com.ginkgocap.ywxt.dao.meeting.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.dao.meeting.UserDao;
import com.ginkgocap.ywxt.dao.meeting.support.DynamicSqlSessionDaoSupport;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.utils.Utils;
@Repository
public class UserDaoImpl extends DynamicSqlSessionDaoSupport implements UserDao {
	private static ResourceBundle resource =  ResourceBundle.getBundle("gintongService");
	@Autowired
	private SqlSessionFactory userSqlSessionFactory;
	/**
	 * 根据用户的ID查询相关信息（名称、图片）
	 * @param peopleIdList 用户ID列表
	 * @return 用户列表
	 */
	public List<User> getByIds(List<Long> userIdList) {
		List<User> listUser = new ArrayList<User>();
		if(!Utils.isNullOrEmpty(userIdList)) {
			List<String> listUserId = getDistinctIds(userIdList);
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			map.put("listUserId", listUserId);
			listUser = getDynamicSqlSession(userSqlSessionFactory).selectList("User.getByUserIds", map);
			if(!Utils.isNullOrEmpty(listUser)) {
				for(User user : listUser) {
					if(!Utils.isNullOrEmpty(user)) {
						user.setPicPath(getUserImg(user));
					}
				}
			}
		}
		return listUser;
	}
	public Map<String, User> getUserMapByIds(List<Long> userIdList) {
		Map<String, User> userMap = new HashMap<String, User>();
		List<User> userList = getByIds(userIdList);
		if(!Utils.isNullOrEmpty(userList)) {
			for(User user : userList) {
				userMap.put(""+user.getId(), user);
			}
		}
		return userMap;
	}
	/**
	 * 过滤唯一ID列表，拼成字符串 如:1,2,19
	 * @param idList
	 * @return
	 */
	private List<String> getDistinctIds(List<Long> idList) {
		List<String> idStrList = new ArrayList<String>();
		if(idList != null) {
			for(Long id : idList) {
				String idStr = ""+id;
				if(!idStrList.contains(idStr)) {
					idStrList.add(idStr);
				}
			}
		}
		return idStrList;
	}
	/**
	 * 拼接用户头像地址
	 * @param user
	 * @return
	 */
	private String getUserImg(User user){
		boolean isHaveHttp = false;
		String imgPath = user.getPicPath();
		if(imgPath!=null){
			isHaveHttp = imgPath.contains("http");
		} else {
			imgPath="/web/pic/user/default.jpeg";
			isHaveHttp = false;
		}
		if(!isHaveHttp){
			String url = resource.getString("url");
			StringBuffer link = new StringBuffer();
			link.append(url);
			link.append("/img/user/image/?module=user");
			link.append("&uId="+user.getId()+"&userId="+user.getId());
			if(StringUtils.isNotBlank(imgPath)) {
				int start = imgPath.lastIndexOf("/");
				int end = imgPath.lastIndexOf(".");
				if(end==-1 || start>end){
					imgPath = imgPath.substring(start+1);
				} else {
					imgPath = imgPath.substring(start+1,end);
				}
				link.append("&imgPath="+imgPath);
			}
			imgPath = link.toString();
		}
		return imgPath;
	}

}
