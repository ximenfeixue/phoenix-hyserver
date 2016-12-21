package com.ginkgocap.ywxt.utils.type;

import java.util.EnumSet;


/**
 * 参会方式
 * 
 * @author qingc
 */
public enum AttendMeetType {

    /**
     * 邀请
     */
	INVITATION {
        @Override
        public String toString() {
            return "邀请";
        }
        @Override
        public int code() {
            return 0;
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
            return 1;
        }
    }
    ;
    @Override
    public abstract String toString();
    public abstract int code();

}
