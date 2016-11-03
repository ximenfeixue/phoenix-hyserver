package com.ginkgocap.ywxt.knowledge.web.test;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Admin on 2016/5/3.
 */
public class MeetingWebTest extends BaseTestCase 
{
    
    public static void main(String[] args) throws Exception {
    	//bigData();
        
    	/*
    	login(loginUrl);
        KnowledgeWebDemo demo = new KnowledgeWebDemo();
        demo.createKnowledge();
        demo.updateKnowledge();
        demo.deleteKnowledge();
        demo.knowledgeDetail();
        demo.allKnowledgeList();
        demo.allCreatedKnowledgeList();
        demo.allCollectedKnowledgeList();
        demo.allByColumnIdKnowledgeList();
        demo.allByKeyWordKnowledgeList();
        demo.allByColumnIdAndKeyWordKnowledgeList();
        demo.allByUserIdKnowledgeList();
        demo.knowledgeListByUserIdAndColumnId();
        demo.knowledgeCollect();
        demo.cancelCollectedKnowledge();
        demo.reportKnowledge();

        //Comment
        demo.knowledgeCommentCreate();
        demo.knowledgeCommentGetList();
        demo.knowledgeCommentGetCount();
        demo.knowledgeCommentDelete();*/
    }
    
    public void testGetMessageList()
    {
    	String reqContent = "{\"withNewRelation\":\"true\"}";
    	String subUrl = "/meeting/socialList2.json";
    	try {
			JsonNode result = HttpPost(hostUrl+subUrl, reqContent);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static void bigData()
	{
		Map<String,String> pairs = new HashMap<String,String>(3);
		pairs.put("page", String.valueOf(0));
		pairs.put("rows", String.valueOf(20));
		pairs.put("type", String.valueOf(1));// 1,推荐 2,发现
		String jsonContent = Util.writeObjectToJson(pairs);
		try {
			JsonNode responseJson = HttpPost("http://123.59.50.85:8090" + "/API/hotKno.do", jsonContent);
			System.err.println("responseJson："+responseJson);
			//model.put("list", PackingDataUtil.getRecommendResult(responseJson));
		} catch (Exception e) {
			System.err.println("connect big data service failed！");
			e.printStackTrace();
		}
	}
	
    ///////////////////////=======Log Request/Response Message=======//////////////////////
    private static String LogMethod(String content, int stackLevel)
    {
        String methodName = Thread.currentThread().getStackTrace()[stackLevel].getMethodName();
        System.out.println("\r\n======= "+content + " " + methodName+" ========");
        return methodName;
    }

    public static JsonNode HttpRequestFull(String httpMethod,String urlString,String jsonContent) throws Exception
    {
        return BaseTestCase.HttpRequestFull(httpMethod, urlString, jsonContent);
    }


}
