package com.ginkgocap.ywxt.utils.type;


/**
 * 修改会议通知类型
 * 
 * @author qingc
 */
public enum ModifyMeetingNoticeType {

    /**
     * 名称
     */
    NAME {
        @Override
        public String toString() {
            return "名称";
        }
    },
    /**
     * 人脉
     */
    PEOPLE {
        @Override
        public String toString() {
            return "人脉";
        }
    },
    /**
     * 组织
     */
    ORGAN {
        @Override
        public String toString() {
            return "组织";
        }
    },
    /**
     * 资料
     */
    DATA {
        @Override
        public String toString() {
            return "资料";
        }
    },
    /**
     * 时间
     */
    TIME {
        @Override
        public String toString() {
            return "时间";
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
     * 介绍
     */
    INTRODUCE {
        @Override
        public String toString() {
            return "介绍";
        }
    },
    /**
     * 主讲人
     */
    SPEAKER {
        @Override
        public String toString() {
            return "主讲人";
        }
    }
    ;

    @Override
    public abstract String toString();

}
