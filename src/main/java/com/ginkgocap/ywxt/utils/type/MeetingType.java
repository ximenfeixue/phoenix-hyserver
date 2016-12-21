package com.ginkgocap.ywxt.utils.type;

import java.util.EnumSet;


/**
 * 会议类型
 * 
 * @author qingc
 */
public enum MeetingType {

    
    /**
     * 无议题
     */
    NOT_HAVE_SPEAKER {
        @Override
        public String toString() {
            return "无议题";
        }
        @Override
        public int code() {
            return 0;
        }
    },
    /**
     * 有议题
     */
   HAVE_SPEAKER {
        @Override
        public String toString() {
            return "有议题";
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
