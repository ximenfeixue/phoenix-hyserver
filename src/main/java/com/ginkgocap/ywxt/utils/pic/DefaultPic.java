package com.ginkgocap.ywxt.utils.pic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

/**
 * Created by wang fei on 2017/9/25.
 * 根据行业返回不公的默认图片
 */
public enum DefaultPic {

    TMT((byte)1, "科技、媒体和通信", "/meetingDefaultPic/1.jpg"),
    CURRENT_POLITICS((byte)2, "时政", "/meetingDefaultPic/2.jpg"),
    SOCIETY((byte)3, "社会", "/meetingDefaultPic/3.jpg"),
    FINANCE((byte)4, "金融", "/meetingDefaultPic/4.jpg"),
    EDUCATION((byte)5, "教育", "/meetingDefaultPic/5.jpg"),
    MEDICAL_CARE((byte)6, "医疗", "/meetingDefaultPic/6.jpg"),
    BIG_DATA((byte)7, "大数据", "/meetingDefaultPic/7.jpg"),
    FINANCE_AFFAIR((byte)8, "财务", "/meetingDefaultPic/8.jpg"),
    LAW((byte)9, "法律", "/meetingDefaultPic/9.jpg"),
    HUMAN_HR((byte)10, "人力HR", "/meetingDefaultPic/10.jpg"),
    PRODUCT_DEVELOPMENT((byte)11, "产品开发", "/meetingDefaultPic/11.jpg"),
    OPERATION((byte)12, "运营", "/meetingDefaultPic/12.jpg");

    private byte sequence;

    private String hyName;

    private String picPath;

    DefaultPic (byte sequence, String hyName, String picPath){
        this.sequence = sequence;
        this.hyName = hyName;
        this.picPath = picPath;
    }

    public byte getSequence() {
        return sequence;
    }

    public void setSequence(byte sequence) {
        this.sequence = sequence;
    }

    public String getHyName() {
        return hyName;
    }

    public void setHyName(String hyName) {
        this.hyName = hyName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public static DefaultPic defaultPic(byte type) {
        switch (type) {
            case 1 :
                return DefaultPic.TMT;
            case 2 :
                return DefaultPic.CURRENT_POLITICS;
            case 3 :
                return DefaultPic.SOCIETY;
            case 4 :
                return DefaultPic.FINANCE;
            case 5 :
                return DefaultPic.EDUCATION;
            case 6 :
                return DefaultPic.MEDICAL_CARE;
            case 7 :
                return DefaultPic.BIG_DATA;
            case 8 :
                return DefaultPic.FINANCE_AFFAIR;
            case 9 :
                return DefaultPic.LAW;
            case 10 :
                return DefaultPic.HUMAN_HR;
            case 11 :
                return DefaultPic.PRODUCT_DEVELOPMENT;
            case 12 :
                return DefaultPic.OPERATION;
            default: {
                logger.error("Can't find the type: " + type + ", so return default value: " + DefaultPic.FINANCE.getHyName());
                return DefaultPic.FINANCE;
            }
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(DefaultPic.class);
}
