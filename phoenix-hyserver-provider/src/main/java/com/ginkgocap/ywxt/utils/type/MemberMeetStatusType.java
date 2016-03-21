package com.ginkgocap.ywxt.utils.type;

import java.util.EnumSet;


/**
 * 人员处理会议的状态
 * 
 * @author qingc
 */
public enum MemberMeetStatusType {

    /**
     * 默认
     */
	DEFAULT {
        @Override
        public String toString() {
            return "默认";
        }
        @Override
        public int code() {
            return 0;
        }
    },
    /**
     * 归档
     */
    PLACE_ON_FILE {
        @Override
        public String toString() {
            return "归档";
        }
        @Override
        public int code() {
            return 1;
        }
    },
    /**
     * 删除
     */
    DELETED {
        @Override
        public String toString() {
            return "删除";
        }
        @Override
        public int code() {
            return 2;
        }
    }
    ;
    @Override
    public abstract String toString();
    public abstract int code();

}
