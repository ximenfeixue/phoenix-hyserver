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
public class Live extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 615938004296207918L;
    //会议序号
    private Long id;
    /**
     * 是否需要直播
     */
    private Integer live = 0;
    /**
     * 直播状态
     */
    private Integer liveStatus = 0;
    /**
     * 直播房间id
     */
    private String liveChannelId;
    /**
     * 直播聊天室id
     */
    private Long liveRoomId;
    /**
     * 直播开始时间
     */
    private Date liveStartTime;
    /**
     * 直播剩余时长,单位秒
     */
    private Long liveRemainDuration = 0L;

    public Live() {
    }

    public Live(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLive() {
        return live;
    }

    public void setLive(Integer live) {
        this.live = live;
    }

    public Integer getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getLiveChannelId() {
        return liveChannelId;
    }

    public void setLiveChannelId(String liveChannelId) {
        this.liveChannelId = liveChannelId;
    }

    public Long getLiveRoomId() {
        return liveRoomId;
    }

    public void setLiveRoomId(Long liveRoomId) {
        this.liveRoomId = liveRoomId;
    }

    public Long getLiveRemainDuration() {
        return liveRemainDuration;
    }

    public void setLiveRemainDuration(Long liveRemainDuration) {
        this.liveRemainDuration = liveRemainDuration;
    }

    public Date getLiveStartTime() {
        return liveStartTime;
    }

    public void setLiveStartTime(Date liveStartTime) {
        this.liveStartTime = liveStartTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
