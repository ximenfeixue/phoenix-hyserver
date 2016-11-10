package com.ginkgocap.ywxt.knowledge.web.test;

import com.fasterxml.jackson.databind.JsonNode;


public class FreechatWebTest extends BaseTestCase
{
    private final String testTitle = "世界就是数据，一切皆可相连，人与人、人与物、物与物互联互通";
    private final String freeChatUrl = "http://192.168.130.200:8081/freechat";
    
    public void testSendMessage()
    {
    	String reqContent = "{\"jtContactID\":\"7\",\"senderName\":\"陈培锋\",\"text\":\"gghjj\",\"fromTime\":\"2016-11-07 12:38:11\",\"fromIndex\":122802,\"type\":0,\"messageID\":\"7FF51CB393E0FCF2\",\"modal\":0,\"ats\":[]}";
    	String subUrl = "/mobile/im/sendMessage.action";
    	try {
			JsonNode result = HttpPost(freeChatUrl+subUrl, reqContent);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}