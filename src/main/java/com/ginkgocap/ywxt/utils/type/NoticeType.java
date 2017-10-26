package com.ginkgocap.ywxt.utils.type;



/**
 * 参会状态
 * 
 * @author qingc
 */
public enum NoticeType {

    /**
     *修改会议
     */
	UPDATE_MEETING {
        @Override
        public String toString() {
            return "修改会议";
        }
        @Override
        public int code() {
            return 0;
        }
    },
    /**
     * 报名申请
     */
    SIGN_UP_APPLY {
        @Override
        public String toString() {
            return "报名申请";
        }
        @Override
        public int code() {
            return 1;
        }
    },
    /**
     * 报名通过
     */
    SIGN_UP_SUCCESS {
        @Override
        public String toString() {
            return "报名通过";
        }
        @Override
        public int code() {
            return 2;
        }
    },
    /**
     * 报名未通过
     */
    SIGN_UP_FAILURE {
        @Override
        public String toString() {
            return "报名未通过";
        }
        @Override
        public int code() {
            return 3;
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
            return 4;
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
            return 5;
        }
    },
    /**
     * 批准报名
     */
    EXAMINE_SIGN_UP_SUCCESS {
        @Override
        public String toString() {
            return "批准报名";
        }
        @Override
        public int code() {
            return 6;
        }
    },
    /**
     * 拒绝报名
     */
    EXAMINE_SIGN_UP_FAILURE {
        @Override
        public String toString() {
            return "拒绝报名";
        }
        @Override
        public int code() {
            return 7;
        }
    },
    /**
     * 取消参会
     */
    CANCEL_ATTEND_MEETING {
        @Override
        public String toString() {
            return "取消参会";
        }
        @Override
        public int code() {
            return 8;
        }
    },
    /**
     * 添加议题
     */
    ADD_MEETING_TOPIC {
        @Override
        public String toString() {
            return "增加议题";
        }
        @Override
        public int code() {
            return 9;
        }
    },
    /**
     * 删除议题
     */
    DELETE_MEETING_TOPIC {
        @Override
        public String toString() {
            return "删除议题";
        }
        @Override
        public int code() {
            return 10;
        }
    },
    /**
     * 修改议题
     */
    UPDATE_MEETING_TOPIC {
        @Override
        public String toString() {
            return "修改议题";
        }
        @Override
        public int code() {
            return 11;
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
            return 12;
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
            return 13;
        }
    },
    /**
     * 删除会议
     */
    DELETE_MEETING {
        @Override
        public String toString() {
        	return "删除会议";
        }
        @Override
        public int code() {
            return 14;
        }
    },
    /**
     * 不需要审核的活动
     */
    NO_REVIEW_MEETING {
        @Override
        public String toString() {
        	return "不需要审核的活动";
        }
        @Override
        public int code() {
            return 15;
        }
    };
    @Override
    public abstract String toString();
    public abstract int code();

}
