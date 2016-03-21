package com.ginkgocap.ywxt.utils;

import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gintong.easemob.server.comm.Constants;
import com.gintong.easemob.server.httpclient.api.EasemobChatGroupsHandler;

public final class HuanxinUtils {
	
	static ResourceBundle resource =  ResourceBundle.getBundle("gintongService");
	static String url = ""; 
	static String interfaceName = ""; 
	static{
		url = resource.getString("url");
		interfaceName = resource.getString("writeHuanErrorForAddUser");
	}
	
	public static void addUserToChatGroups(final String chatGroupId, final Long userId) {
		ArrayNode usernames = JsonNodeFactory.instance.arrayNode();
		usernames.add(String.valueOf(userId));
		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
		usernamesNode.put("usernames", usernames);
		final ObjectNode objectNode = EasemobChatGroupsHandler.addUsersToGroupBatch(chatGroupId, usernamesNode);
		ThreadPoolUtils.getExecutorService().execute(new Runnable() {
			@Override
			public void run() {
				writeHuanxinError(objectNode,chatGroupId,String.valueOf(userId));
			}
		});
	}
	
	static void writeHuanxinError(ObjectNode objectNode,String chatGroupId, String userId){
		if(null != objectNode && !objectNode.isNull()){
			JsonNode jsonNode = objectNode.get("error");
			if (null != jsonNode && !jsonNode.isNull()) {
				objectNode.put("applicationName", Constants.APP_NAME_MEETIGN);
				objectNode.put("methodName", "addUserToChatGroups");
				objectNode.put("chatId", chatGroupId);
				objectNode.put("reqJson", "{\"chatGroupId\":chatGroupId,\"userId\":userId}");
				try {
					HttpClientUtil.getGintongPost(url, interfaceName, objectNode.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void addUserToChatGroups(final String chatGroupId,final String userId) {
		ArrayNode usernames = JsonNodeFactory.instance.arrayNode();
		usernames.add(userId);
		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
		usernamesNode.put("usernames", usernames);
		final ObjectNode objectNode = EasemobChatGroupsHandler.addUsersToGroupBatch(chatGroupId, usernamesNode);
		ThreadPoolUtils.getExecutorService().execute(new Runnable() {
			@Override
			public void run() {
				writeHuanxinError(objectNode,chatGroupId,userId);
			}
		});
	}

	public static void deleteUserFromGroup(String chatGroupId, String userId) {
		EasemobChatGroupsHandler.deleteUserFromGroup(chatGroupId, userId);
	}

	public static void deleteUserFromGroup(String chatGroupId, Long userId) {
		EasemobChatGroupsHandler.deleteUserFromGroup(chatGroupId, String.valueOf(userId));
	}

	public static void deleteUsersFromGroup(String chatGroupId, List<String> userIds) {
		for (String userId : userIds) {
			EasemobChatGroupsHandler.deleteUserFromGroup(chatGroupId, userId);
		}
	}

	public static void deleteMembersFromGroup(String chatGroupId, List<Long> userIds) {
		for (Long userId : userIds) {
			EasemobChatGroupsHandler.deleteUserFromGroup(chatGroupId, String.valueOf(userId));
		}
	}

}
