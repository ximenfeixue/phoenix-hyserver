package com.ginkgocap.ywxt.controller.meeting;

import com.ginkgocap.ywxt.common.base.BaseController;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.gintong.frame.util.dto.CommonResultCode;
import com.gintong.frame.util.dto.InterfaceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public InterfaceResult getByLiveRoomId(@PathVariable long liveRoomId) {
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

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
