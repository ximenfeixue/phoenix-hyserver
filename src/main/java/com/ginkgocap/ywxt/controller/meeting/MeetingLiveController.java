package com.ginkgocap.ywxt.controller.meeting;

import com.ginkgocap.ywxt.common.base.BaseController;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.model.meeting.MeetingLiveUseRecord;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.gintong.frame.util.dto.CommonResultCode;
import com.gintong.frame.util.dto.InterfaceResult;
import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author cinderella
 * @version 2017/12/15
 */
@Controller
@RequestMapping("/meetingLive")
public class MeetingLiveController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(MeetingLiveController.class);

    @Resource
    private MeetingService meetingService;

    @ResponseBody
    @RequestMapping(value = "/getByLiveRoomId/{liveRoomId}", method = RequestMethod.GET)
    public InterfaceResult getByLiveRoomId(@PathVariable Long liveRoomId) {
        try {
            Meeting meeting = meetingService.getByLiveRoomId(liveRoomId);
            getLogger().info("getByLiveRoomId meeting : {}", null == meeting ? "" : meeting.toString());
            return InterfaceResult.getSuccessInterfaceResultInstance(meeting);
        } catch (Exception ex) {
            getLogger().error("getByLiveRoomId Exception : {}", ex.getMessage());
        }
        return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
    }

    @ResponseBody
    @RequestMapping(value = "/getByLiveChannelId/{liveChannelId}", method = RequestMethod.GET)
    public InterfaceResult getByLiveChannelId(@PathVariable String liveChannelId) {
        try {
            Meeting meeting = meetingService.getByLiveChannelId(liveChannelId);
            getLogger().info("getByLiveChannelId meeting : {}", null == meeting ? "" : meeting.toString());
            return InterfaceResult.getSuccessInterfaceResultInstance(meeting);
        } catch (Exception ex) {
            getLogger().error("getByLiveChannelId Exception : {}", ex.getMessage());
        }
        return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
    }

    @ResponseBody
    @RequestMapping(value = "/getLiveRecord/{meetingId}", method = RequestMethod.GET)
    public InterfaceResult getMeetingLiveUseRecordByMeetingId(@PathVariable Long meetingId) {
        try {
            List<MeetingLiveUseRecord> liveRecordList = meetingService.getMeetingLiveUseRecordByMeetingId(meetingId);
            getLogger().info("getMeetingLiveUseRecordByMeetingId liveRecordList size : {}", liveRecordList.size());
            return InterfaceResult.getSuccessInterfaceResultInstance(liveRecordList);
        } catch (Exception ex) {
            getLogger().error("getMeetingLiveUseRecordByMeetingId Exception : {}", ex.getMessage());
        }
        return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
    }


    @ResponseBody
    @RequestMapping(value = "/keepConnect", method = RequestMethod.GET)
    public InterfaceResult keepConnect(@PathVariable Long meetingId) {


        return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
    }



    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
