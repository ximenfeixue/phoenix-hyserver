package com.ginkgocap.ywxt.utils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ginkgocap.ywxt.model.meeting.SocialListReq;
import com.ginkgocap.ywxt.vo.query.meeting.ConnectionsMini;
//import com.ginkgocap.ywxt.vo.query.meeting.JTContact;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingMemberPush;
import com.ginkgocap.ywxt.vo.query.meeting.ResponseObject;
import com.ginkgocap.ywxt.vo.query.meeting.UserBean;
import com.ginkgocap.ywxt.vo.query.social.Social;
import com.ginkgocap.ywxt.vo.query.social.SocialDetail;
import com.google.gson.Gson;
/**
 * @Description: rest调用客户端
 * @Author: qinguochao
 * @CreateDate: 2014-10-2
 * @Version: [v1.0]
 */
public class GinTongInterface {
	private static Logger logger = LoggerFactory.getLogger(GinTongInterface.class);

	private static ResourceBundle resource = ResourceBundle.getBundle("gintongService");

	/**
	 * 获取人脉详情
	 * 
	 * @param userId
	 *            用户Id
	 * @param isOnline
	 *            是否线上人脉
	 * @return
	 */
	/*
	 * public static String addJTContact(JTContact jTContact,UserBean userBean){
	 * 
	 * try{ Map<String,Object> map = new HashMap<String,Object>();
	 * map.put("jtContact", jTContact); String json = new Gson().toJson(map);
	 * String url = resource.getString("url"); String interfaceName =
	 * resource.getString("addJTContact"); HttpClientUtil httpClientUtil = new
	 * HttpClientUtil();
	 * //System.out.println("接口getListConnections requestJson-->>"+json); String
	 * responseJson = httpClientUtil.getGintongPost(url, interfaceName,
	 * json,userBean);
	 * //System.out.println("接口getListConnections responseJson-->>"
	 * +responseJson); if(responseJson != null && responseJson.length()>0){
	 * ResponseObject responseObject = new Gson().fromJson(responseJson,
	 * ResponseObject.class); return
	 * responseObject.getResponseData().getSucceed(); }
	 * //通过jtMember.getId()获取关系对象; 关系对象通过接口获取 2.55 }catch (Exception e) {
	 * e.printStackTrace(); //errorMessageList.add(
	 * "访问/connections/getListConnectionsMini.json 接口 获取关系错误;"); } return null;
	 * }
	 */
	/**
	 * 获取人脉详情
	 * 
	 * @return
	 */
	/*
	 * public static JTContact getJTContactDetail(boolean isOnline,UserBean
	 * userBean){
	 * 
	 * try{ Map<String,Object> map = new HashMap<String,Object>();
	 * map.put("isOnline", isOnline); map.put("jtContactID", userBean.getId());
	 * String json = new Gson().toJson(map); String url =
	 * resource.getString("url"); String interfaceName =
	 * resource.getString("getJTContactDetail"); HttpClientUtil httpClientUtil =
	 * new HttpClientUtil();
	 * //System.out.println("接口getListConnections requestJson-->>"+json); String
	 * responseJson = httpClientUtil.getGintongPost(url, interfaceName,
	 * json,userBean);
	 * //System.out.println("接口getListConnections responseJson-->>"
	 * +responseJson); if(responseJson != null && responseJson.length()>0){
	 * ResponseObject responseObject = new Gson().fromJson(responseJson,
	 * ResponseObject.class); return
	 * responseObject.getResponseData().getOnlineJTContact(); }
	 * //通过jtMember.getId()获取关系对象; 关系对象通过接口获取 2.55 }catch (Exception e) {
	 * e.printStackTrace(); //errorMessageList.add(
	 * "访问/connections/getListConnectionsMini.json 接口 获取关系错误;"); } return null;
	 * }
	 */
	public static List<ConnectionsMini> getListConnectionsMini(List<Integer> userIds) {
		String url = null;
		String json = null;
		String interfaceName = null;
		String responseJson = null;
		try {
			ConnectionsMini connectionsMini = new ConnectionsMini();
			connectionsMini.setListUserID(userIds);
			for (int i = userIds.size() - 1; i >= 0; i--) {
				if (userIds.get(i) == 0)
					userIds.remove(i);
			}
			json = new Gson().toJson(connectionsMini);
			url = resource.getString("url");
			interfaceName = resource.getString("getListConnectionsMini");
			// System.out.println("接口getListConnections requestJson-->>"+json);
			responseJson = HttpClientUtil.getGintongPost(url, interfaceName, json);
			// System.out.println("接口getListConnections responseJson-->>"+responseJson);
			if (responseJson != null && responseJson.length() > 0) {
				JSONObject j = JSONObject.fromObject(responseJson);
				Map<String, Class> classMap = new HashMap<String, Class>();
				// json 转为对象
				classMap.put("listConnectionsMini", ConnectionsMini.class);
				ResponseObject responseObject = (ResponseObject) JSONObject.toBean(j, ResponseObject.class, classMap);
				return responseObject.getResponseData().getListConnectionsMini();
			}
			// 通过jtMember.getId()获取关系对象; 关系对象通过接口获取 2.55
		} catch (Throwable e) {
			e.printStackTrace();
			// errorMessageList.add("访问/connections/getListConnectionsMini.json 接口 获取关系错误;");
			System.out.println("url:" + url);
			System.out.println("interfaceName:" + interfaceName);
			System.out.println("json:" + json);
			System.out.println("responseJson:" + responseJson);
		}
		return null;
	}

	public static String pushToAttendMeetingMember(UserBean user, String meetingId, String content, boolean isHaveCreate, String date) {
		String url = null;
		String json = null;
		String interfaceName = null;
		String responseJson = null;
		try {
			MeetingMemberPush updateMeetingPush = new MeetingMemberPush();
			updateMeetingPush.setContent(content);
			updateMeetingPush.setMeetingId(Integer.valueOf(meetingId));
			if (!Utils.isNullOrEmpty(user))
				updateMeetingPush.setUser(user);
			updateMeetingPush.setHaveCreate(true);
			updateMeetingPush.setDate(date);
			json = new Gson().toJson(updateMeetingPush);
			url = resource.getString("imUrl");
			interfaceName = resource.getString("meetingMemberPush");
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			// System.out.println("接口getListConnections requestJson-->>"+json);
			responseJson = httpClientUtil.getGintongPost(url, interfaceName, json);
			// System.out.println("接口getListConnections responseJson-->>"+responseJson);
			if (responseJson != null && responseJson.length() > 0) {
				JSONObject j = JSONObject.fromObject(responseJson);
				// json 转为对象
				ResponseObject responseObject = (ResponseObject) JSONObject.toBean(j, ResponseObject.class);
				return responseObject.getResponseData().getSucceed();
			}
			// 通过jtMember.getId()获取关系对象; 关系对象通过接口获取 2.55
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("url:" + url);
			System.out.println("interfaceName:" + interfaceName);
			System.out.println("json:" + json);
			System.out.println("responseJson:" + responseJson);
		}
		return null;
	}
	public static String pushToMeetingCreater(UserBean user, String meetingId, String content, String date) {
		String url = null;
		String json = null;
		String interfaceName = null;
		String responseJson = null;
		try {
			content = URLEncoder.encode(content, "UTF-8");
			MeetingMemberPush updateMeetingPush = new MeetingMemberPush();
			updateMeetingPush.setContent(content);
			updateMeetingPush.setMeetingId(Integer.valueOf(meetingId));
			updateMeetingPush.setUser(user);
			updateMeetingPush.setDate(date);
			updateMeetingPush.setHaveCreate(true);
			// String json = new Gson().toJson(updateMeetingPush);
			json = JSON.toJSONString(updateMeetingPush);
			url = resource.getString("imUrl");
			interfaceName = resource.getString("meetingCreatePush");
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			// System.out.println("接口getListConnections requestJson-->>"+json);
			responseJson = httpClientUtil.getGintongPost(url, interfaceName, json);
			// System.out.println("接口getListConnections responseJson-->>"+responseJson);
			if (responseJson != null && responseJson.length() > 0) {
				JSONObject j = JSONObject.fromObject(responseJson);
				// json 转为对象
				ResponseObject responseObject = (ResponseObject) JSONObject.toBean(j, ResponseObject.class);
				return responseObject.getResponseData().getSucceed();
			}
			// 通过jtMember.getId()获取关系对象; 关系对象通过接口获取 2.55
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("url:" + url);
			System.out.println("interfaceName:" + interfaceName);
			System.out.println("json:" + json);
			System.out.println("responseJson:" + responseJson);
		}
		return null;
	}
	/**
	 * 获取人脉详情
	 */
	public static ResponseObject addMeetingIndex(Long meetingId, String oper) {
		String url = null;
		String json = null;
		String interfaceName = null;
		String responseJson = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			json = new Gson().toJson(map);
			url = resource.getString("bigDataUrl");
			interfaceName = resource.getString("addMeetingIndex");
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			// System.out.println("接口getListConnections requestJson-->>"+json);
			System.err.print("url=:::::::::::" + url + interfaceName + "?id=" + meetingId + "&oper=" + oper);
			responseJson = httpClientUtil.doGet(url, interfaceName + "?id=" + meetingId + "&oper=" + oper);
			// System.out.println("接口getListConnections responseJson-->>"+responseJson);
			if (responseJson != null && responseJson.length() > 0) {
				ResponseObject responseObject = new Gson().fromJson(responseJson, ResponseObject.class);
				return responseObject;
			}
			// 通过jtMember.getId()获取关系对象; 关系对象通过接口获取 2.55
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("url:" + url);
			System.out.println("interfaceName:" + interfaceName);
			System.out.println("json:" + json);
			System.out.println("responseJson:" + responseJson);
		}
		// errorMessageList.add("访问/connections/getListConnectionsMini.json 接口 获取关系错误;");
		return null;
	}
	/*
	 * 会议搜索
	 * 
	 * @return add wangfeiliang
	 */
	public static ResponseObject meetingSeach(Map<String, Object> value, UserBean userBean) {
		String url = null;
		String json = null;
		String interfaceName = null;
		String responseJson = null;
		try {
			url = resource.getString("bigDataUrl");
			interfaceName = resource.getString("meetingSeach");
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			responseJson = httpClientUtil.doPost(url, interfaceName, value);
			if (responseJson != null && responseJson.length() > 0) {
				ResponseObject responseObject = new Gson().fromJson(responseJson, ResponseObject.class);
				return responseObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("url:" + url);
			System.out.println("interfaceName:" + interfaceName);
			System.out.println("json:" + json);
			System.out.println("responseJson:" + responseJson);
		}
		return null;
	}

	public static int getNewConnectionsCount(String userId) {
		String url = null;
		String json = null;
		String interfaceName = null;
		String responseJson = null;
		int count = 0;
		try {
			json = "{userId:" + userId + "}";
			url = resource.getString("url");
			interfaceName = "/connections/getNewConnectionsCount.json";
			responseJson = HttpClientUtil.getGintongPost(url, interfaceName, json);
			if (responseJson != null && responseJson.length() > 0) {
				JSONObject j = JSONObject.fromObject(responseJson);
				if (!Utils.isNullOrEmpty(j) && j.containsKey("responseData")) {
					JSONObject obj = j.getJSONObject("responseData");
					if (!Utils.isNullOrEmpty(obj) && obj.containsKey("count")) {
						count = obj.getInt("count");
					}
				}
			}
		} catch (Throwable e) {

		}
		return count;
	}

	public static String createMUC(long meetingId, String groupName, String meetingDesc, int roomsize, long ownerId, List<Long> memberIds) {
		ObjectMapper objectMap = new ObjectMapper();
		ObjectNode dataObjectNode = objectMap.createObjectNode();
		dataObjectNode.put("title", groupName);
		dataObjectNode.put("maxusers", String.valueOf(roomsize));
		dataObjectNode.put("mucID", String.valueOf(meetingId));
		dataObjectNode.put("subject", "");
		dataObjectNode.put("content", meetingDesc);
		ArrayNode arrayNode = objectMap.createArrayNode();
		for (long memberId : memberIds) {
			arrayNode.add(String.valueOf(memberId));
		}
		dataObjectNode.put("listJTContactID", arrayNode);

		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/createMUC.action";
		String groupId = null;
		try {
			String json = objectMap.writeValueAsString(dataObjectNode);
			logger.info("req freechat.createMUC =>" + objectMap.writeValueAsString(json));

			UserBean userBean = new UserBean();
			userBean.setId(ownerId);
			String responseJson = HttpClientUtil.getGintongPost(url, interfaceName, json, userBean);
			JsonNode jsonNode = objectMap.readTree(responseJson);

			logger.info("resp freechat.createMUC =>" + objectMap.writeValueAsString(jsonNode));
			if ("0000".equals(jsonNode.get("notification").get("notifCode").asText())) {
				groupId = jsonNode.get("responseData").get("mucDetail").get("imGroupId").asText();
			}
		} catch (Exception e) {
			logger.error("req frrechat.createMUC failed!", e);
		}

		return groupId;
	}

	/**
	 * 获取用户个人单聊与畅聊的会话列表
	 * 
	 * @param groupName
	 * @param roomsize
	 * @param ownerId
	 * @param memberIds
	 * @return
	 */
	public static List<Social> getListIMRecord(SocialListReq property) {
		List<Social> listSocial = new ArrayList<Social>();

		ObjectMapper objectMap = new ObjectMapper();
		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/getListIMRecord.action";
		try {
			UserBean userBean = new UserBean();
			userBean.setId(property.getUserId());
			String responseJson = HttpClientUtil.getGintongPost(url, interfaceName, "{}", userBean);
			JsonNode jsonNode = objectMap.readTree(responseJson);

			if ("0000".equals(jsonNode.get("notification").get("notifCode").asText())) {
				JsonNode listIMRecordNode = jsonNode.get("responseData").get("page").get("listIMRecord");
				for (JsonNode node : listIMRecordNode) {
					logger.debug("imrecord ==> " + node.toString());
					Social socail = new Social();
					socail.setId(node.get("id").asLong());
					socail.setNewCount(node.get("newCount").asInt());
					socail.setTime(DateConvertUtils.parse(getString(node, "startTime", "")));
					socail.setOrderTime(DateConvertUtils.parse(getString(node, "startTime", "")));
					socail.setTitle(getString(node, "title", ""));
					socail.setType(node.get("type").asInt());
					socail.setAtMsgId(getString(node, "atMsgId", ""));
					socail.setAtName(getString(node, "atName", ""));
					socail.setCompereName(getString(node, "compereName", ""));

					SocialDetail socialDetail = new SocialDetail();
					List<String> listImageUrl = new ArrayList<String>();
					for (JsonNode n : node.get("listImageUrl")) {
						listImageUrl.add(n.asText());
					}
					socialDetail.setListImageUrl(listImageUrl);

					socialDetail.setSenderID(node.get("senderId").asLong());
					socialDetail.setSenderName(getString(node, "senderName", ""));
					socialDetail.setContent(getString(node, "content", ""));
					socail.setSocialDetail(socialDetail);
					logger.debug("socail ==> " + node.toString());
					listSocial.add(socail);
				}
			}
		} catch (Exception e) {
			logger.error("req frrechat.createMUC failed!", e);
		}
		logger.info("socail list size ==> " + listSocial.size());
		return listSocial;
	}

	private static String getString(JsonNode node, String key, String defaultValue) {
		return node.get(key) != null ? node.get(key).asText() : defaultValue;
	}

	/**
	 * 获取用户个人单聊与畅聊的会话列表
	 * 
	 * @param groupName
	 * @param roomsize
	 * @param ownerId
	 * @param memberIds
	 * @return
	 */
	public static void invite2MUC(long inviteUserId, List<Long> listInvitedUserId, String groupId) {
		ObjectMapper objectMap = new ObjectMapper();
		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/invite2MUC.action";
		try {
			UserBean userBean = new UserBean();
			userBean.setId(inviteUserId);

			ObjectNode objectNode = objectMap.createObjectNode();
			objectNode.put("mucID", groupId);
			ArrayNode arrayNode = objectMap.createArrayNode();
			for (long uid : listInvitedUserId) {
				arrayNode.add(uid);
			}
			objectNode.put("listID", arrayNode);
			String jsonbody = objectMap.writeValueAsString(objectNode);

			String responseJson = HttpClientUtil.getGintongPost(url, interfaceName, jsonbody, userBean);
			JsonNode jsonNode = objectMap.readTree(responseJson);

			logger.info("resp freechat.invite2MUC =>" + objectMap.writeValueAsString(jsonNode));
			if ("0000".equals(jsonNode.get("notification").get("notifCode").asText())) {
				logger.info(inviteUserId + "邀请用户" + listInvitedUserId + "加入群聊成功!");
			} else {
				logger.info(inviteUserId + "邀请用户" + listInvitedUserId + "加入群聊失败!");
			}
		} catch (Exception e) {
			logger.error("req frrechat.invite2MUC failed!", e);
		}
	}
	public static void exitFromMUC(long operatorUserId, long targetUserId, String groupId) {
		ObjectMapper objectMap = new ObjectMapper();
		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/exitFromMUC.action";
		try {
			UserBean userBean = new UserBean();
			userBean.setId(operatorUserId);

			ObjectNode objectNode = objectMap.createObjectNode();
			objectNode.put("userID", targetUserId);
			objectNode.put("mucID", groupId);
			String jsonbody = objectMap.writeValueAsString(objectNode);

			String responseJson = HttpClientUtil.getGintongPost(url, interfaceName, jsonbody, userBean);
			JsonNode jsonNode = objectMap.readTree(responseJson);

			logger.info("resp freechat.exitFromMUC =>" + objectMap.writeValueAsString(jsonNode));
			if ("0000".equals(jsonNode.get("notification").get("notifCode").asText())) {
				logger.info(targetUserId + "离开畅聊" + groupId + "成功!");
			} else {
				logger.info(targetUserId + "离开畅聊" + groupId + "失败!");
			}
		} catch (Exception e) {
			logger.error("req frrechat.exitFromMUC failed!", e);
		}
	}

	public static void main(String[] args) {
		String groupId = "10024";
		long userId = 4L;
		List<Long> userIds = Arrays.asList(7L);
		long exitUserId = 7L;

		GinTongInterface.invite2MUC(userId, userIds, groupId);
		GinTongInterface.exitFromMUC(userId, exitUserId, groupId);
	}

	public static void updateMuc(String groupId, Map<String, Object> params) {
		// TODO Auto-generated method stub
		
	}
}
