package com.ginkgocap.ywxt.controller.netease;

import com.alibaba.fastjson.JSONObject;
import com.ginkgocap.ywxt.constant.NeteaseEventTypeEnum;
import com.ginkgocap.ywxt.dto.ChatRoomMessage;
import com.ginkgocap.ywxt.dto.SendMessage;
import com.ginkgocap.ywxt.dto.UserAndMessage;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.utils.CheckSumBuilder;
import com.ginkgocap.ywxt.utils.HttpClientUtil;
import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author cinderella
 * @version 2017/12/14
 */
@Controller
@RequestMapping("/route")
public class RouteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteController.class);

    private static final String  APP_SECRET = "6d7923464477";

    private static ResourceBundle resource = ResourceBundle.getBundle("gintongService");

    private static String imUrl = resource.getString("imUrl");

    @Autowired
    private UserService userService;

    @Autowired
    private MeetingService meetingService;

    @RequestMapping(value = {"/mockClient.action"}, method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject mockClient(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            // 获取请求体
            byte[] body = readBody(request);
            if (body == null) {
                LOGGER.warn("request wrong, empty body!");
                result.put("code", 414);
                return result;
            }
            // 获取部分request header，并打印
            String ContentType = request.getContentType();
            String AppKey = request.getHeader("AppKey");
            String CurTime = request.getHeader("CurTime");
            String MD5 = request.getHeader("MD5");
            String CheckSum = request.getHeader("CheckSum");
            LOGGER.info("request headers: ContentType = {}, AppKey = {}, CurTime = {}, " +
                    "MD5 = {}, CheckSum = {}", ContentType, AppKey, CurTime, MD5, CheckSum);
            // 将请求体转成String格式，并打印
            String requestBody = new String(body, "utf-8");
            LOGGER.info("request body = {}", requestBody);
            // 获取计算过的md5及checkSum
            String verifyMD5 = CheckSumBuilder.getMD5(requestBody);
            String verifyChecksum = CheckSumBuilder.getCheckSum(APP_SECRET, verifyMD5, CurTime);
            LOGGER.info("verifyMD5 = {}, verifyChecksum = {}", verifyMD5, verifyChecksum);
            //比较md5、checkSum是否一致，以及后续业务处理
            if (MD5.equals(verifyMD5) && verifyChecksum.equals(CheckSum)) {
                handleJSONObject(requestBody);
            }
            result.put("code", 200);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.put("code", 414);
            return result;
        }
    }

    private byte[] readBody(HttpServletRequest request) throws IOException {
        if (request.getContentLength() > 0) {
            byte[] body = new byte[request.getContentLength()];
            IOUtils.readFully(request.getInputStream(), body);
            return body;
        } else {
            return null;
        }
    }

    private void handleJSONObject(String requestBody) {
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        if (NeteaseEventTypeEnum.CHAT_ROOM.getKey().equals((String)jsonObject.get("eventType"))) {
            chatRoomMessageSendToFreeChat(requestBody);
        }
        if (NeteaseEventTypeEnum.VEDIO_DataTunnel.getKey().equals((String)jsonObject.get("eventType"))) {
            // TODO: 2017/12/14
        }
    }

    private void chatRoomMessageSendToFreeChat(String requestBody) {
        ChatRoomMessage chatRoomMessage = JSONObject.parseObject(requestBody, ChatRoomMessage.class);
        User user = userService.getUserById(Long.parseLong(chatRoomMessage.getFromAccount()));
        SendMessage sedMessage = getSedMessage(chatRoomMessage);
        UserAndMessage userAndMessage = new UserAndMessage();
        userAndMessage.setSendMessage(sedMessage);
        userAndMessage.setUser(user);
        final String url = imUrl;
        final String interfaceName = "/mobile/im/synSendChatMessage";
        try {
            String ginTongPost = HttpClientUtil.getGintongPost(url, interfaceName, JSONObject.toJSONString(userAndMessage));
            LOGGER.info("chatRoomMessageSendToFreeChat result : {}", ginTongPost);
        } catch (Exception ex) {
            LOGGER.error("chatRoomMessageSendToFreeChat Exception : {}", ex.getMessage());
        }
    }

    private SendMessage getSedMessage(ChatRoomMessage chatRoomMessage) {
        Meeting meeting = meetingService.getByLiveRoomId(Long.parseLong(chatRoomMessage.getRoomId()));
        final long groupId = Long.parseLong(meeting.getGroupId());
        //{"jtContactID":"","mucID":"156472","type":"0","senderID":"3621233","senderName":"段爱华","text":"u6","messageID":"3860044459339018","fromTime":"2017-12-14 19:12:36","userType":1,"jtFile":{}}
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMucID(groupId);
        sendMessage.setText(chatRoomMessage.getAttach());
        sendMessage.setSenderID(Long.parseLong(chatRoomMessage.getFromAccount()));
        sendMessage.setSenderName(true == Strings.isNullOrEmpty(chatRoomMessage.getFromNick()) ? "" : chatRoomMessage.getFromNick());
        sendMessage.setType(0);
        sendMessage.setMessageID(chatRoomMessage.getMsgidClient());
        sendMessage.setFromTime(new Date(Long.parseLong(chatRoomMessage.getMsgTimestamp())));
        sendMessage.setUserType(1);
        return sendMessage;
    }


}
