package com.ginkgocap.ywxt.model.meeting;

import com.ginkgocap.ywxt.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cinderella
 * @version 2017/12/7
 */
public class MeetingLiveCreateRecord extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8321937305026328911L;

    private Long id;

    /**
     * 活动id
     */
    private Long meetingId;

    /**
     * 频道ID，32位字符串
     */
    private String channelId;

    /**
     * 聊天室id
     */
    private Long roomId;

    /**
     * 类型，1--开通，2--续费
     */
    private Integer createType;

    /**
     * 开通/续费直播时长类型，1--30分钟，2--60分钟
     */
    private Integer durationType;

    /**
     * 支付订单
     */
    private Long payOrderId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人
     */
    private Long userCreate;

    /**
     * 备注
     */
    private String remark;

    public MeetingLiveCreateRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public Integer getDurationType() {
        return durationType;
    }

    public void setDurationType(Integer durationType) {
        this.durationType = durationType;
    }

    public Long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Long payOrderId) {
        this.payOrderId = payOrderId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Long userCreate) {
        this.userCreate = userCreate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
