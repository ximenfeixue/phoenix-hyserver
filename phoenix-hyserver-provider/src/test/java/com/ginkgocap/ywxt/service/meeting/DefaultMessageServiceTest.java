package com.ginkgocap.ywxt.service.meeting;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.ginkgocap.ywxt.vo.query.meeting.MeetingQuery;
import com.gintong.rocketmq.api.DefaultMessageService;
import com.gintong.rocketmq.api.enums.TopicType;
import com.gintong.rocketmq.api.model.RocketSendResult;
import com.gintong.rocketmq.api.utils.FlagTypeUtils;
import javacommon.base.BaseManagerTestCase;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * <p>DESCRIPTION:  测试给mq发送数据
 * <p>CALLED BY:	钱明金
 * <p>CREATE DATE: 2015/12/14
 *
 * @version 1.0
 * @since java 1.7.0
 */
public class DefaultMessageServiceTest extends BaseManagerTestCase {
    private DefaultMessageService defaultMessageService;
    private MeetingService meetingService;

    @Autowired
    public void setDefaultMessageService(DefaultMessageService defaultMessageService) {
        this.defaultMessageService = defaultMessageService;
    }

    @Autowired

    public void setMeetingService(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Test
    public void testSendMessage() {
        List<MeetingQuery> meetingQueryList = null;
        try {
            meetingQueryList = meetingService.getMyForwardingMeeting(13363L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RocketSendResult result;
        for (int i = 0; meetingQueryList != null && i < meetingQueryList.size(); i++) {
            MeetingQuery meetingQuery = meetingQueryList.get(i);
            result = defaultMessageService.sendMessage(TopicType.MEETING_TOPIC, FlagTypeUtils.createMeetingFlag(), getMeetingQueryToIndexJsonString(meetingQuery));
            assertThat(result, Matchers.notNullValue());
            assertThat(result.getSendResult().getMessageQueue().getTopic(), Matchers.is(TopicType.MEETING_TOPIC.name()));

        }


    }

    private String getMeetingQueryToIndexJsonString(MeetingQuery meetingQuery) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", meetingQuery.getId());
        data.put("path", meetingQuery.getPath());
        data.put("createTime", meetingQuery.getCreateTime());
        data.put("meetingDesc", meetingQuery.getMeetingDesc());
        data.put("meetingName", meetingQuery.getMeetingName());
        data.put("meetingStatus", meetingQuery.getMeetingStatus());
        data.put("meetingAddress", meetingQuery.getMeetingAddress());
        data.put("listIndustry", StringUtils.join(meetingQuery.getListIndustry(), " "));
        return JSONObject.fromObject(data).toString();
    }

    @Test
  public   void testConsumer() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(DefaultMessageServiceTest.class.getSimpleName());
        consumer.setNamesrvAddr("192.168.101.159:9876;192.168.101.131:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(TopicType.MEETING_TOPIC.name(), "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext Context) {
                //System.out.println("list size=" + list.size());
                /*for(int index=0;index < list.size();index++){
				Message msg = list.get(index);
				try {
					String body = new String(msg.getBody(), "UTF-8");
					System.err.println("index="+index+"==="+body);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				}*/
                Message msg = list.get(0);
                try {
                    System.err.println(msg);
                    String body = new String(msg.getBody(), "UTF-8");
                    System.err.println(body);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
