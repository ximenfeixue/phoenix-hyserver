package com.ginkgocap.ywxt.vo.query.meeting;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wang fei on 2017/9/30.
 */
public class MeetingSignUpFormQuery implements Serializable{

    private static final long serialVersionUID = 3148176768559230817L;

    // 会议 id
    private Long meetingId;
    // 报名时间
    private Long createTime;
    // 报名人 id
    private Long userId;
    // 报名人 名字
    @Transient
    private String userName;
    // 报名人 头像
    @Transient
    private String userLogo;
    // 报名表单信息
    private List<MeetingCustom> SignUpFormList;
    // 是否是好友状态 0：不是好友 1：等待状态 2：是好友
    @Transient
    private Byte friendStatus;
    // 报名人 行业
    private String industry;
    // 订单号
    private String orderNumber;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<MeetingCustom> getSignUpFormList() {
        return SignUpFormList;
    }

    public void setSignUpFormList(List<MeetingCustom> signUpFormList) {
        SignUpFormList = signUpFormList;
    }

    public Byte getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(Byte friendStatus) {
        this.friendStatus = friendStatus;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
