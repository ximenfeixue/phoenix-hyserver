package com.ginkgocap.ywxt.service.meeting.impl;

import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.task.DataSyncScheduler;
import com.gintong.ywxt.im.constant.MessageNotifyResType;
import com.gintong.ywxt.im.constant.MessageNotifyType;
import com.gintong.ywxt.im.model.MessageNotify;
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

    public void addInvitationNotify(long toId, Meeting meeting) {
        try {
            MessageNotify notify = newInvitationNotify(toId, meeting);
            if (notify != null) {
                messageNotifyService.sendMessageNotify(notify);
            }
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

    public MessageNotify newInvitationNotify(long toId, Meeting meeting) {
        final long fromId = meeting.getCreateId();
        if (toId == fromId) {
            logger.error("can't invite self, userId: " + fromId);
            return null;
        }

        final String fromName = meeting.getCreateName();
        final String title = meeting.getMeetingName();
        final long meetingId = meeting.getId();
        final long time = meeting.getStartTime().getTime();

        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setTitle(title);
        notify.setFromId(fromId);
        notify.setFromName(fromName);
        notify.setResId(meetingId);
        notify.setResTitle(title);
        notify.setResType(MessageNotifyResType.EResActivity.value());
        notify.setType(MessageNotifyType.EActivityInvite.value());
        String content = "{\"meetingId\":" + meetingId + ",\"title\":\"" + title + "\",\"time\":" + time + "}";
        notify.setContent(content);

        return notify;
    }

    public void addApplyMeetingNotify(long fromId, String fromName, String title, Meeting meeting) {
        try {
            MessageNotify notify = newApplyMeetingNotify(fromId, fromName, title, meeting);
            if (notify != null) {
                messageNotifyService.sendMessageNotify(notify);
            }
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public MessageNotify newApplyMeetingNotify(long fromId, String fromName, String title, Meeting meeting) {
        final long toId = meeting.getCreateId();
        if (toId == fromId) {
            logger.error("can't apply self, userId: " + fromId);
            return null;
        }

        final String meetingTitle = meeting.getMeetingName();
        final long meetingId = meeting.getId();
        //final long time = meeting.getStartTime().getTime();

        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setTitle(title);
        notify.setFromId(fromId);
        notify.setFromName(fromName);
        notify.setResId(meetingId);
        notify.setResTitle(meetingTitle);
        notify.setResType(MessageNotifyResType.EResActivity.value());
        notify.setType(MessageNotifyType.EActivityApply.value());
        String content = "{\"meetingId\":" + meetingId + ",\"groupId\":" + meeting.getGroupId() + ",\"memberId\":" + fromId + "}";
        notify.setContent(content);

        return notify;
    }

    public void addMeetingNotify(long fromId, String fromName, String title, Meeting meeting) {
        try {
            MessageNotify notify = newMeetingNotify(fromId, fromName, title, meeting);
            if (notify != null) {
                messageNotifyService.sendMessageNotify(notify);
            }
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public MessageNotify newMeetingNotify(long fromId, String fromName, String title, Meeting meeting) {
        final long toId = meeting.getCreateId();
        if (toId == fromId) {
            logger.error("can't send notify to self, userId: " + fromId);
            return null;
        }

        final String meetingTitle = meeting.getMeetingName();
        final long meetingId = meeting.getId();
        //final long time = meeting.getStartTime().getTime();

        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setTitle(title);
        notify.setFromId(fromId);
        notify.setFromName(fromName);
        notify.setResId(meetingId);
        notify.setResTitle(meetingTitle);
        notify.setResType(MessageNotifyResType.EResActivity.value());
        notify.setType(MessageNotifyType.EActivity.value());
        String content = "{\"meetingId\":" + meetingId + ",\"title\":\"" + meetingTitle + "\"}";
        notify.setContent(content);

        return notify;
    }

}
