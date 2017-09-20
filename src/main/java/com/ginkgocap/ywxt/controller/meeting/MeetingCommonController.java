package com.ginkgocap.ywxt.controller.meeting;

import com.ginkgocap.ywxt.common.base.BaseController;
import com.ginkgocap.ywxt.model.meeting.Meeting;
import com.ginkgocap.ywxt.service.meeting.MeetingService;
import com.ginkgocap.ywxt.user.model.User;
import com.gintong.frame.util.dto.CommonResultCode;
import com.gintong.frame.util.dto.InterfaceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wang fei on 2017/9/19.
 */
@Controller
@RequestMapping("/common")
public class MeetingCommonController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(MeetingCommonController.class);

    @Resource
    private MeetingService meetingService;

    @Override
    public Logger getLogger() {
        return logger;
    }

    /**
     * 置顶 会议
     * @param request
     * @param id     会议id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/top/{id}", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public InterfaceResult top(HttpServletRequest request, @PathVariable long id) {

        InterfaceResult result = null;
        User user = this.getYINUser(request);
        if (user == null)
            return InterfaceResult.getInterfaceResultInstance(CommonResultCode.PERMISSION_EXCEPTION);

        Meeting meeting = meetingService.getById(id);
        if (meeting == null) {
            result = InterfaceResult.getInterfaceResultInstance(CommonResultCode.PARAMS_EXCEPTION);
            result.getNotification().setNotifInfo("当前会议不存在或已删除");
            return result;
        }
        if (meeting.getDisable() == 1) {
            result = InterfaceResult.getInterfaceResultInstance(CommonResultCode.PARAMS_EXCEPTION);
            result.getNotification().setNotifInfo("当前会议已禁用，不允许置顶哦");
            return result;
        }
        try {
            result = meetingService.addTop(id);

        } catch (Exception e) {
            logger.error("invoke meetingService failed! method = {addTop}" );
            return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
        }
        return result;
    }
    /**
     * 取消置顶 会议
     * @param request
     * @param id     会议id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteTop/{id}", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    public InterfaceResult deleteTop(HttpServletRequest request, @PathVariable long id) {

        InterfaceResult result = null;
        User user = this.getYINUser(request);
        if (user == null)
            return InterfaceResult.getInterfaceResultInstance(CommonResultCode.PERMISSION_EXCEPTION);

        Meeting meeting = meetingService.getById(id);
        if (meeting == null) {
            result = InterfaceResult.getInterfaceResultInstance(CommonResultCode.PARAMS_EXCEPTION);
            result.getNotification().setNotifInfo("当前会议不存在或已删除");
            return result;
        }
        if (meeting.getDisable() == 1) {
            result = InterfaceResult.getInterfaceResultInstance(CommonResultCode.PARAMS_EXCEPTION);
            result.getNotification().setNotifInfo("当前会议已禁用，不允许置顶哦");
            return result;
        }
        try {
            result = meetingService.deleteTop(id);

        } catch (Exception e) {
            logger.error("invoke meetingService failed! method = {deleteTop}" );
            return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
        }
        return result;
    }

    /**
     * 禁用 会议
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public InterfaceResult disable(HttpServletRequest request, @PathVariable long id) {

        InterfaceResult result = null;
        User user = this.getYINUser(request);
        if (user == null)
            return InterfaceResult.getInterfaceResultInstance(CommonResultCode.PERMISSION_EXCEPTION);

        Meeting meeting = meetingService.getById(id);
        if (meeting == null) {
            result = InterfaceResult.getInterfaceResultInstance(CommonResultCode.PARAMS_EXCEPTION);
            result.getNotification().setNotifInfo("当前会议不存在或已删除");
            return result;

        }
        try {
            result = meetingService.disable(id);
        } catch (Exception e) {
            logger.error("invoke meetingService failed! method = {disable}");
            return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
        }
        return result;
    }
    /**
     * 启用 会议
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public InterfaceResult enable(HttpServletRequest request, @PathVariable long id) {

        InterfaceResult result = null;
        User user = this.getYINUser(request);
        if (user == null)
            return InterfaceResult.getInterfaceResultInstance(CommonResultCode.PERMISSION_EXCEPTION);

        Meeting meeting = meetingService.getById(id);
        if (meeting == null) {
            result = InterfaceResult.getInterfaceResultInstance(CommonResultCode.PARAMS_EXCEPTION);
            result.getNotification().setNotifInfo("当前会议不存在或已删除");
            return result;

        }
        try {
            result = meetingService.enable(id);
        } catch (Exception e) {
            logger.error("invoke meetingService failed! method = {enable}");
            return InterfaceResult.getInterfaceResultInstance(CommonResultCode.SYSTEM_EXCEPTION);
        }
        return result;
    }

}
