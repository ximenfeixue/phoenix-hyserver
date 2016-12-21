package com.ginkgocap.ywxt.utils.type;



/**
 * 会议状态类型
 * 
 * @author qingc
 */
public enum MeetingStatusType {

    /**
     *草稿
     */
	DRAFT {
        @Override
        public String toString() {
            return "草稿";
        }
        @Override
        public int code() {
            return 0;
        }
    },
    /**
     * 未开始
     */
    NOT_BEGIN {
        @Override
        public String toString() {
            return "未开始";
        }
        @Override
        public int code() {
            return 1;
        }
    },
    /**
     * 会议中
     */
    IN_MEETING {
        @Override
        public String toString() {
            return "会议中";
        }
        @Override
        public int code() {
            return 2;
        }
    },
    /**
     * 已结束
     */
    ENDED {
        @Override
        public String toString() {
            return "已结束";
        }
        @Override
        public int code() {
            return 3;
        }
    }
    ;
    @Override
    public abstract String toString();
    public abstract int code();

}
