package com.ginkgocap.ywxt.service.meeting.impl;

import com.ginkgocap.ywxt.task.DataSyncScheduler;
import com.gintong.ywxt.im.model.MessageNotify;
import com.gintong.ywxt.im.model.MessageNotifyType;
import com.gintong.ywxt.im.service.MessageNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gintong on 10/30/17.
 */
@Service("meetingNotifyService")
public class MeetingNotifyService {
    private final Logger logger = LoggerFactory.getLogger(MeetingNotifyService.class);

    @Resource
    private MessageNotifyService messageNotifyService;

    public void addInvitationNotify(long toId, long fromId, String fromName, long meetingId, String title, long time) {
        try {
            MessageNotify notify = newInvitationNotify(toId, fromId, fromName, meetingId, title, time);
            messageNotifyService.sendMessageNotify(notify);
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public void addInvitationNotify(List<MessageNotify> notifyList) {
        try {
            messageNotifyService.sendMessageNotify(notifyList);
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public MessageNotify newInvitationNotify(long toId, long fromId, String fromName, long meetingId, String title, long time) {
        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setFromId(fromId);
        notify.setFromName(fromName);
        notify.setResId(meetingId);
        notify.setResTitle(title);
        notify.setType(MessageNotifyType.EActivityInvite.value());
        String content = "{meetingId:" + meetingId +",title:\"" + title + ",time:" + time + "}";
        notify.setContent(content);

        return notify;
    }

    public void addApplyMeetingNotify(long toId, long fromId, String fromName, long meetingId, String title, long time) {
        try {
            MessageNotify notify = newApplyMeetingNotify(toId, fromId, fromName, meetingId, title, time);
            messageNotifyService.sendMessageNotify(notify);
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public MessageNotify newApplyMeetingNotify(long toId, long fromId, String fromName, long meetingId, String title, long time) {
        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setFromId(fromId);
        notify.setFromName(fromName);
        notify.setResId(meetingId);
        notify.setResTitle(title);
        notify.setType(MessageNotifyType.EActivityApply.value());
        String content = "{meetingId:" + meetingId +",title:\"" + title + ",time:" + time + "}";
        notify.setContent(content);

        return notify;
    }

    public void addMeetingNotify(long toId, long fromId, String fromName, long meetingId, String title, long time) {
        try {
            MessageNotify notify = newMeetingNotify(toId, fromId, fromName, meetingId, title, time);
            messageNotifyService.sendMessageNotify(notify);
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public MessageNotify newMeetingNotify(long toId, long fromId, String fromName, long meetingId, String title, long time) {
        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setFromId(fromId);
        notify.setFromName(fromName);
        notify.setResId(meetingId);
        notify.setResTitle(title);
        notify.setType(MessageNotifyType.EActivity.value());
        String content = "{meetingId:" + meetingId +",title:\"" + title + ",time:" + time + "}";
        notify.setContent(content);

        return notify;
    }

}
