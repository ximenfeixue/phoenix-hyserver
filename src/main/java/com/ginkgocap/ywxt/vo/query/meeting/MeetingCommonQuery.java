package com.ginkgocap.ywxt.vo.query.meeting;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wang fei on 2017/9/22.
 * 运营后台 前端在 body 中传数据使用
 * 筛选过滤条件功能
 */
public class MeetingCommonQuery implements Serializable{

    private static final long serialVersionUID = 3148176768559230817L;
    // 关键字
    private String keyword;
    // 关键字类型 0：活动名称 1：主办人
    private byte type;
    // 省
    private String province;
    // 市
    private String city;
    // 区
    private String town;
    // 开始时间
    private Date startTime;
    // 结束时间
    private Date endTime;
    // 禁用状态 0：未禁用 1：禁用
    private byte disable;
    // 审核状态 0：未审核 1：审核通过 2：审核拒绝
    private byte checkStatus;
    // 保密状态 0：开放 1：保密
    private byte isSecret;
    // 第几页 （从 0 开始）
    private int index;
    // 每页显示条数
    private int size;
    // 每页的索引开始
    private int startRow;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public byte getDisable() {
        return disable;
    }

    public void setDisable(byte disable) {
        this.disable = disable;
    }

    public byte getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(byte isSecret) {
        this.isSecret = isSecret;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
}
