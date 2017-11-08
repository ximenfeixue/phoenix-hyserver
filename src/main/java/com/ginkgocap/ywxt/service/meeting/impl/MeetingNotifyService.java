package com.ginkgocap.ywxt.service.meeting.impl;

import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingMember;
import com.ginkgocap.ywxt.task.DataSyncScheduler;
import com.ginkgocap.ywxt.user.model.User;
import com.gintong.ywxt.im.constant.MessageNotifyResType;
import com.gintong.ywxt.im.constant.MessageNotifyType;
import com.gintong.ywxt.im.model.MessageNotify;
import com.gintong.ywxt.im.service.MessageNotifyService;
import org.apache.commons.collections.CollectionUtils;
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

    public void addInvitationNotify(long toId, String picPath, Meeting meeting) {
        try {
            MessageNotify notify = newInvitationNotify(toId, picPath, meeting);
            if (notify != null) {
                messageNotifyService.sendMessageNotify(notify);
                logger.info("send notify success.");
            }
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public void addInvitationNotify(List<MessageNotify> notifyList) {
        if (CollectionUtils.isEmpty(notifyList)) {
            logger.error("notifyList size is 0, so skip to send.");
            return;
        }
        try {
            messageNotifyService.sendMessageNotify(notifyList);
            logger.info("send notify success, size: " + notifyList.size());
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public MessageNotify newInvitationNotify(long toId, String picPath, Meeting meeting) {
        long fromId = meeting.getCreateId();
        if (toId == fromId) {
            logger.error("can't invite self, userId: " + fromId);
            return null;
        }

        String fromName = meeting.getCreateName();
        String title = meeting.getMeetingName();
        long meetingId = meeting.getId();
        long time = meeting.getStartTime().getTime();

        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setPicPath(picPath);
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

    public void addApplyMeetingNotify(User applyUser, String title, Meeting meeting) {
        try {
            MessageNotify notify = newApplyMeetingNotify(applyUser, title, meeting);
            if (notify != null) {
                messageNotifyService.sendMessageNotify(notify);
            }
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public MessageNotify newApplyMeetingNotify(User applyUser, String title, Meeting meeting) {
        long toId = meeting.getCreateId();
        long fromId =  applyUser.getId();
        if (toId == fromId) {
            logger.error("can't apply self, userId: " + fromId);
            return null;
        }

        String meetingTitle = meeting.getMeetingName();
        long meetingId = meeting.getId();
        //long time = meeting.getStartTime().getTime();

        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setPicPath(applyUser.getPicPath());
        notify.setTitle(title);
        notify.setFromId(fromId);
        notify.setFromName(applyUser.getName());
        notify.setResId(meetingId);
        notify.setResTitle(meetingTitle);
        notify.setResType(MessageNotifyResType.EResActivity.value());
        notify.setType(MessageNotifyType.EActivityApply.value());
        String content = "{\"meetingId\":" + meetingId + ",\"groupId\":" + meeting.getGroupId() + ",\"memberId\":" + fromId + "}";
        notify.setContent(content);

        return notify;
    }

    public void addAgreeMeetingNotify(Meeting meeting, User applyUser, User ownerUser) {
        String title = "您 同意了 " + applyUser.getName() + " 的报名";
        addMeetingNotify(meeting.getCreateId(), applyUser, title, meeting);

        title = meeting.getCreateName() + " 同意了您的报名";
        addMeetingNotify(applyUser.getId(), ownerUser, title, meeting);
    }

    public void addRefuseMeetingNotify(Meeting meeting, User applyUser, User ownerUser) {
        String title = "您 拒绝了 " + applyUser.getName() + " 的报名";
        addMeetingNotify(meeting.getCreateId(), applyUser, title, meeting);

        title = meeting.getCreateName() + " 拒绝了您的报名";
        addMeetingNotify(applyUser.getId(), ownerUser, title, meeting);
    }

    public void addMeetingNotify(long toId, User fromUser, String title, Meeting meeting) {
        try {
            MessageNotify notify = newMeetingNotify(toId, fromUser, title, meeting);
            if (notify != null) {
                messageNotifyService.sendMessageNotify(notify);
            }
        } catch (Exception ex) {
            logger.error("send messageNotify failed. error: " + ex.getMessage());
        }
    }

    public MessageNotify newMeetingNotify(long toId, User fromUser, String title, Meeting meeting) {
        final long fromId = fromUser.getId();
        if (toId == fromId) {
            logger.error("can't send notify to self, userId: " + fromId);
            return null;
        }
        String meetingTitle = meeting.getMeetingName();
        long meetingId = meeting.getId();
        //long time = meeting.getStartTime().getTime();

        MessageNotify notify = new MessageNotify();
        notify.setToId(toId);
        notify.setPicPath(fromUser.getPicPath());
        notify.setTitle(title);
        notify.setFromId(fromUser.getId());
        notify.setFromName(fromUser.getName());
        notify.setResId(meetingId);
        notify.setResTitle(meetingTitle);
        notify.setResType(MessageNotifyResType.EResActivity.value());
        notify.setType(MessageNotifyType.EActivity.value());
        String content = "{\"meetingId\":" + meetingId + ",\"title\":\"" + meetingTitle + "\"}";
        notify.setContent(content);

        return notify;
    }

    public int deleteMeetingNotify(long fromId, long toId, long meetingId) {
        return deleteMeetingNotify(fromId, toId, meetingId, MessageNotifyType.EActivityApply.value());
    }

    public int deleteMeetingNotify(long fromId, long toId, long meetingId, int type) {
        if (toId <= 0 || meetingId <= 0) {
            logger.error("userId or meetingId is invalidated.");
            return -1;
        }

        try {
            return messageNotifyService.deleteByFromIdToIdResIdType(fromId, toId, meetingId, type);
        } catch (Exception ex) {
            logger.error("invoke deleteByToIdResIdType failed. toId: " + toId + " meetingId: " + meetingId + " type: " + type);
            return -1;
        }
    }

}
