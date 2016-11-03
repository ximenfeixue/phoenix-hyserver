package com.ginkgocap.ywxt.dao.meeting;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.user.model.User;

@Repository
public interface UserDao {

	/**
	 * 根据用户的ID查询相关信息（名称、图片）
	 * @param userIdList 用户ID列表
	 * @return 用户列表
	 */
	public List<User> getByIds(List<Long> userIdList);
	public Map<String, User> getUserMapByIds(List<Long> userIdList);
}
