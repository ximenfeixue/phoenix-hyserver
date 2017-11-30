package com.ginkgocap.ywxt.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import com.ginkgocap.ywxt.util.JsonUtil;
import com.gintong.ywxt.im.model.ChatMessage;
import com.gintong.ywxt.im.model.ImRecord;
import com.gintong.ywxt.im.model.MUCMessage;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONObject;

import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
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
import org.springframework.util.CollectionUtils;

/**
 * @Description: rest调用客户端
 * @Author: qinguochao
 * @CreateDate: 2014-10-2
 * @Version: [v1.0]
 */
public class GinTongInterface {
	private static Logger logger = LoggerFactory.getLogger(GinTongInterface.class);

	private static ResourceBundle resource = ResourceBundle.getBundle("gintongService");

	private static final int hyType = 3;
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
					if (node == null) {
						logger.error("node is null, so skip.");
						continue;
					}
					Social socail = new Social();
					socail.setId(getLong(node,"id",0L));
					socail.setNewCount(getInt(node,"newCount",0));
					socail.setTime(DateConvertUtils.parse(getString(node, "startTime", "")));
					socail.setOrderTime(DateConvertUtils.parse(getString(node, "startTime", "")));
					socail.setTitle(getString(node, "title", ""));
					socail.setType(getInt(node,"type",0));
					socail.setAtMsgId(getString(node, "atMsgId", ""));
					socail.setAtName(getString(node, "atName", ""));
					socail.setCompereName(getString(node, "compereName", ""));
					socail.setUserType(getInt(node, "userType", 0));
					SocialDetail socialDetail = new SocialDetail();
					List<String> listImageUrl = new ArrayList<String>();
					JsonNode imgNodeList = node.get("listImageUrl");
					if (imgNodeList != null) {
						for (JsonNode imgNode : imgNodeList) {
							if (imgNode != null) {
								final String imgUrl = imgNode.asText();
								if (imgUrl != null && imgUrl.trim().length() > 0) {
									listImageUrl.add(imgUrl);
								} else {
									logger.error("image url is null, so skip add to list.");
								}
							}
						}
					} else {
                        logger.error("image url lis is null.");
                    }
					socialDetail.setListImageUrl(listImageUrl);

					socialDetail.setSenderID(getLong(node,"senderId",0L));
					socialDetail.setSenderName(getString(node, "senderName", ""));
					socialDetail.setContent(getString(node, "content", ""));
					socialDetail.setModal(getInt(node, "modal", 0));
					socail.setSocialDetail(socialDetail);
					logger.debug("socail info: id: " + socail.getId() + " title: " + socail.getTitle() +
								" sendId: " + socialDetail.getSenderID() + " sendName: " + socialDetail.getSenderName());
					listSocial.add(socail);
				}
			}
		} catch (Exception e) {
			logger.error("req frrechat.getListIMRecord failed!", e);
		}
		logger.info("socail list size ==> " + (listSocial != null ? listSocial.size() : 0));
		return listSocial;
	}

	public static Map<Integer,List<Social>> getListIMRecordMap(SocialListReq property) {

		ObjectMapper objectMap = new ObjectMapper();
		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/getListIMRecordMap";
		try {
			UserBean userBean = new UserBean();
			userBean.setId(property.getUserId());
			String responseJson = HttpClientUtil.getGintongPost(url, interfaceName, "{}", userBean);
			JsonNode jsonNode = null;
			try {
				jsonNode = objectMap.readTree(responseJson);
			} catch (Exception ex) {
				logger.error("parser json failed. error: " + ex.getMessage());
				logger.error("responseJson content: " + responseJson);
			}

			if (jsonNode != null && "0".equals(jsonNode.get("notification").get("notifCode").asText())) {
				JsonNode mapNode = jsonNode.get("responseData");
				if (mapNode != null) {
					Map<Integer, List<Social>> map = new HashMap<Integer, List<Social>>(2);
					for (int index = 1; index <= 2; index++) {
						JsonNode listIMRecordNode = mapNode.get(String.valueOf(index));
						if (listIMRecordNode != null) {
                            /*
							List<Social> listSocial = new ArrayList<Social>(listIMRecordNode.size());
							for (JsonNode node : listIMRecordNode) {
								Social socail = convertNodeToSocial(node, (short)(index ==1 ? 1 : 0));
								listSocial.add(socail);
							}*/
                            List<Social> listSocial = convertNodeToSocialList(listIMRecordNode, (short)(index ==1 ? 1 : 0));
							map.put(index, listSocial);
						}
					}
					return map;
				}
			}
		} catch (Exception e) {
			logger.error("req frrechat.getListIMRecord failed!", e);
		}
		return null;
	}

	private static Social convertNodeToSocial(JsonNode node, final short top) {
		if (node == null) {
			logger.error("node is null, so skip.");
			return null;
		}
		Social socail = new Social();
		socail.setTop(top);
		socail.setId(getLong(node,"mucId",0L));
		socail.setNewCount(getInt(node,"newCount",0));
		final Date dateTime = DateConvertUtils.parse(getString(node, "startTime", ""));
		socail.setTime(dateTime);
		socail.setOrderTime(dateTime);
		socail.setTitle(getString(node, "title", ""));
		socail.setType(getInt(node,"type",0));
		socail.setAtMsgId(getString(node, "atMsgId", ""));
		socail.setAtName(getString(node, "atName", ""));
		socail.setCompereName(getString(node, "compereName", ""));
		socail.setUserType(getInt(node, "userType", 0));
		SocialDetail socialDetail = new SocialDetail();
		List<String> listImageUrl = new ArrayList<String>();
		JsonNode imgNodeList = node.get("listImageUrl");
		if (imgNodeList != null) {
			for (JsonNode imgNode : imgNodeList) {
				if (imgNode != null) {
					final String imgUrl = imgNode.asText();
					if (imgUrl != null && imgUrl.trim().length() > 0 && !"null".equals(imgUrl)) {
						listImageUrl.add(imgUrl);
					} else {
						logger.error("image url is null, so skip add to list.");
					}
				}
			}
		} else {
			logger.error("image url lis is null.");
		}
		socialDetail.setListImageUrl(listImageUrl);

		socialDetail.setSenderID(getLong(node,"senderId",0L));
		socialDetail.setSenderName(getString(node, "senderName", ""));
		socialDetail.setContent(getString(node, "content", ""));
		socialDetail.setModal(getInt(node, "modal", 0));
		socialDetail.setMessageId(getString(node, "msgId", ""));
		socail.setSocialDetail(socialDetail);
		//logger.debug("socail info: id: " + socail.getId() + " title: " + socail.getTitle() +
				//" sendId: " + socialDetail.getSenderID() + " sendName: " + socialDetail.getSenderName());

		return socail;
	}

    private static List<Social> convertNodeToSocialList(JsonNode listIMRecordNode, final short top) {
        final String listImRecordContent = listIMRecordNode.toString();
        List<ImRecord> recordList = JsonReadUtil.readListValue(ImRecord.class, listImRecordContent);

        if (CollectionUtils.isEmpty(recordList)) {
            logger.error("node is null, so skip.");
            return null;
        }

        List<Social> socialList = new ArrayList<Social>(recordList.size());
        for (ImRecord record : recordList) {
            final int type = record.getType();
            Social socail = new Social();
            socail.setTop(top);
            socail.setId(record.getMucId()); //getLong(node, "mucId", 0L)
            socail.setNewCount(record.getNewCount()); //getInt(node, "newCount", 0)
            final Date dateTime =  DateConvertUtils.parse(record.getStartTime());// DateConvertUtils.parse(getString(node, "startTime", ""));
            socail.setTime(dateTime);
            socail.setOrderTime(dateTime);
            socail.setTitle(record.getTitle()); //getString(node, "title", ""));
            socail.setType(type); //node, "type", 0));
            socail.setAtMsgId(record.getAtMsgId()); //getString(node, "atMsgId", ""));
            socail.setAtName(record.getAtName()); //getString(node, "atName", ""));
            socail.setCompereName(record.getCompereName()); //getString(node, "compereName", ""));
            socail.setUserType(record.getUserType()); //getInt(node, "userType", 0));

            SocialDetail socialDetail = new SocialDetail();
            socialDetail.setListImageUrl(record.getListImageUrl()); //listImageUrl);
            socialDetail.setSenderID(record.getSenderId());//getLong(node, "senderId", 0L));
            socialDetail.setSenderName(record.getSenderName()); //getString(node, "senderName", ""));
            socialDetail.setContent(record.getContent()); //getString(node, "content", ""));
            socialDetail.setModal(record.getModal()); //getInt(node, "modal", 0));

            if (type == 1) {
                ChatMessage lastMessage = record.getChatMessage();
                if (lastMessage != null) {
                    logger.info("last message id: " + lastMessage.getMessageID());
                    socialDetail.setType(lastMessage.getType());
                    socialDetail.setMessageId(lastMessage.getMessageID()); //getString(node, "msgId", ""));
                } else {
                    logger.error("last message is null, so no messageId. mucId: " + socail.getId());
                }
            } else if (type == 2) {
                MUCMessage lastMessage = record.getMucMessage();
                if (lastMessage != null) {
                    logger.info("last message id: " + lastMessage.getMessageID());
                    socialDetail.setType(lastMessage.getType());
                    socialDetail.setMessageId(lastMessage.getMessageID()); //getString(node, "msgId", ""));
                } else {
                    logger.error("last message is null, so no messageId. mucId: " + socail.getId());
                }
            }
            socail.setSocialDetail(socialDetail);
            socialList.add(socail);
        }
        return socialList;
    }

	/**
	 * 获取用户会议会话列表
	 *
	 * @return
	 */
	public static List<Social> getListMeetingRecord(Long userId) {
		List<Social> listSocial = new ArrayList<Social>();

		ObjectMapper objectMap = new ObjectMapper();
		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/getListMeetingRecord.action";
		try {
			UserBean userBean = new UserBean();
			userBean.setId(userId);
			String responseJson = HttpClientUtil.getGintongPost(url, interfaceName, "{}", userBean);
			JsonNode jsonNode = objectMap.readTree(responseJson);

			if ("0000".equals(jsonNode.get("notification").get("notifCode").asText())) {
				JsonNode listIMRecordNode = jsonNode.get("responseData").get("page").get("listIMRecord");
				for (JsonNode node : listIMRecordNode) {
					logger.debug("imrecord ==> " + node.toString());
					Social socail = new Social();
					socail.setId(getLong(node, "id", 0L));
					socail.setNewCount(getInt(node, "newCount", 0));
					socail.setTime(DateConvertUtils.parse(getString(node, "startTime", "")));
					socail.setOrderTime(DateConvertUtils.parse(getString(node, "startTime", "")));
					socail.setTitle(getString(node, "title", ""));
					socail.setType(getInt(node, "type", 0));
					socail.setAtMsgId(getString(node, "atMsgId", ""));
					socail.setAtName(getString(node, "atName", ""));
					socail.setCompereName(getString(node, "compereName", ""));

					SocialDetail socialDetail = new SocialDetail();
					List<String> listImageUrl = new ArrayList<String>();
					for (JsonNode n : node.get("listImageUrl")) {
						listImageUrl.add(n.asText());
					}
					socialDetail.setListImageUrl(listImageUrl);

					socialDetail.setSenderID(getLong(node, "senderId", 0L));
					socialDetail.setSenderName(getString(node, "senderName", ""));
					socialDetail.setContent(getString(node, "content", ""));
					socail.setSocialDetail(socialDetail);
					logger.debug("socail ==> " + ReflectionToStringBuilder.toString(socail));
					listSocial.add(socail);
				}
			}
		} catch (Exception e) {
			logger.error("req frrechat.getListMeetingRecord failed!", e);
		}
		logger.info("socail list size ==> " + listSocial.size());
		return listSocial;
	}

	private static String getString(JsonNode node, String key, String defaultValue) {
		Object val = node.get(key);
		if (val == null) {
			logger.warn(key + " is null, use default : " + defaultValue);
			return defaultValue;
		}

		return node.get(key).asText();
	}

	private static long getLong(JsonNode node, String key, long defaultValue) {
		Object val = node.get(key);
		if (val == null) {
			logger.warn(key + " is null, use default : " + defaultValue);
			return defaultValue;
		}

		return node.get(key).asLong();
	}

	private static int getInt(JsonNode node, String key, int defaultValue) {
		Object val = node.get(key);
		if (val == null) {
			logger.warn(key + " is null, use default : " + defaultValue);
			return defaultValue;
		}

		return node.get(key).asInt();
	}

	/**
	 * 获取用户个人单聊与畅聊的会话列表
	 * @param inviteUserId
	 * @param listInvitedUserId
	 * @param groupId
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

	public static void dissolveMUC(long createUserId, String groupId) {
		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/deleteGroup/";
		StringBuffer json = new StringBuffer();
		interfaceName = json.append(interfaceName).append(groupId).append("/").append(createUserId).toString();
		String responseJson = HttpClientUtil.getGintongDelete(url, interfaceName);
		logger.info("responseJson: " + responseJson);
	}

	public static void addFreechatToBlack(String groupId) {

		final String url = resource.getString("imUrl");
		final String interfaceName = "/msgcenter/im/removeChatGroup/";

		StringBuffer json = new StringBuffer();
		json.append(interfaceName).append(groupId).append("/").append(hyType);
		String responseJson = HttpClientUtil.getGintongDelete(url, interfaceName);
		logger.info("responseJson: " + responseJson);
	}


	public static void main(String[] args) {
		String groupId = "10024";
		long userId = 4L;
		List<Long> userIds = Arrays.asList(7L);
		long exitUserId = 7L;

		GinTongInterface.invite2MUC(userId, userIds, groupId);
		GinTongInterface.exitFromMUC(userId, exitUserId, groupId);
	}


	public static void updateMuc(Long operatorUserId, Long meetingId, String name, String desc, Integer maxusersSize) {
		ObjectMapper objectMap = new ObjectMapper();
		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/modifyConference.action";
		try {
			UserBean userBean = new UserBean();
			userBean.setId(operatorUserId);

			ObjectNode objectNode = objectMap.createObjectNode();
			objectNode.put("id", meetingId);
			objectNode.put("title", name);
			objectNode.put("maxusersSize", maxusersSize);
			String jsonbody = objectMap.writeValueAsString(objectNode);

			String responseJson = HttpClientUtil.getGintongPost(url, interfaceName, jsonbody, userBean);
			JsonNode jsonNode = objectMap.readTree(responseJson);

			logger.info("resp freechat.updateMuc =>" + objectMap.writeValueAsString(jsonNode));
			if ("0000".equals(jsonNode.get("notification").get("notifCode").asText())) {
				logger.info("更新会议畅聊" + meetingId + "成功!");
			} else {
				logger.info("更新会议畅聊" + meetingId + "失败!");
			}
		} catch (Exception e) {
			logger.error("req frrechat.exitFromMUC failed!", e);
		}
	}

	public static void removeMuc(Long operatorUserId,String groupId,Long memberId) {
		String url = resource.getString("imUrl");
		String interfaceName = "/mobile/im/kickFromGroup/" + groupId + "/" + memberId;
		try {
			HttpClient httpClient = HttpConnectionManager.getHttpClient();
			UserBean userbean = new UserBean();
			userbean.setId(operatorUserId);

			HttpDelete deleteRequest = new HttpDelete(url + interfaceName);
			deleteRequest.setHeader("Accept", "application/json");
			deleteRequest.setHeader("sessionID", userbean.getSessionId());
			deleteRequest.setHeader("jtNickName", userbean.getName());
			deleteRequest.setHeader("jtUserId", userbean.getId().toString());

			HttpResponse response = httpClient.execute(deleteRequest);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				logger.info("REQEST => " + url);
				String result = EntityUtils.toString(entity).toString();
				final String ret = (result != null && result.length() > 55) ? result.substring(0, 55) : result;
				logger.info("RETURN => " + ret);
			}

		} catch (IOException e) {
			logger.info("RETURN => ");
			logger.error("请求phoenix-mobile异常", e);
		}
	}
}
