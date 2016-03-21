package com.ginkgocap.ywxt.utils.type;



/**
 * 参会状态
 * 
 * @author qingc
 */
public enum AttendMeetStatusType {

    /**
     *未答复
     */
	NO_REPLY {
        @Override
        public String toString() {
            return "未答复";
        }
        @Override
        public int code() {
            return 0;
        }
    },
    /**
     * 接受邀请
     */
    ACCEPT_INVITATION {
        @Override
        public String toString() {
            return "接受邀请";
        }
        @Override
        public int code() {
            return 1;
        }
    },
    /**
     * 拒绝邀请
     */
    REFUSE_INVITATION {
        @Override
        public String toString() {
            return "拒绝邀请";
        }
        @Override
        public int code() {
            return 2;
        }
    },
    /**
     * 报名
     */
    SIGN_UP {
        @Override
        public String toString() {
            return "报名";
        }
        @Override
        public int code() {
            return 4;
        }
    },
    /**
     * 取消报名
     */
    CANCEL_SIGN_UP {
        @Override
        public String toString() {
            return "取消报名";
        }
        @Override
        public int code() {
            return 5;
        }
    },
    /**
     * 退出会议
     */
    QUIT_MEETING {
        @Override
        public String toString() {
            return "退出会议";
        }
        @Override
        public int code() {
            return 6;
        }
    }
    ;
    @Override
    public abstract String toString();
    public abstract int code();

}
