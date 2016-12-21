package com.ginkgocap.ywxt.utils.type;

import java.util.EnumSet;


/**
 * 会议报名必填默认字段
 * 
 * @author qingc
 */
public enum DefaultSignUpMeetingLabelType {

    /**
     * 姓名
     */
    NAME {
        @Override
        public String toString() {
            return "姓名";
        }
    },
    /**
     * 性别
     */
    SEX {
        @Override
        public String toString() {
            return "性别";
        }
    },
    /**
     * 职务
     */
    POSITION {
        @Override
        public String toString() {
            return "职务";
        }
    },
    /**
     * 手机
     */
    MOBILEPHONE {
        @Override
        public String toString() {
            return "手机";
        }
    },
    /**
     * 固话
     */
    FIXEDPHONE {
        @Override
        public String toString() {
            return "固话";
        }
    },
    /**
     *邮箱
     */
    EMAIL {
        @Override
        public String toString() {
            return "邮箱";
        }
    },
    /**
     * 地址
     */
    ADDRESS {
        @Override
        public String toString() {
            return "地址";
        }
    },
    /**
     * 传真
     */
    FAX {
        @Override
        public String toString() {
            return "传真";
        }
    },
    /**
     * 单位名称
     */
    COMPANY {
        @Override
        public String toString() {
            return "单位名称";
        }
    },
    /**
     * 部门
     */
    DEPARTMENT{
        @Override
        public String toString() {
            return "部门";
        }
    }
    ;
    public static final EnumSet<DefaultSignUpMeetingLabelType> SET_WORK_EXPERIENCE = EnumSet.of(DefaultSignUpMeetingLabelType.COMPANY,
    		DefaultSignUpMeetingLabelType.POSITION, DefaultSignUpMeetingLabelType.DEPARTMENT);
    @Override
    public abstract String toString();

}
