package com.ginkgocap.ywxt.utils.type;

import java.util.EnumSet;


/**
 * 需求类型
 * 
 * @author qingc
 */
public enum RequirementType {

    /**
     * 投资
     */
	INVEST {
        @Override
        public String toString() {
            return "投资";
        }
        @Override
        public int code() {
            return 0;
        }
    },
    /**
     * 融资
     */
    FINANCING {
        @Override
        public String toString() {
            return "融资";
        }
        @Override
        public int code() {
            return 1;
        }
    },
    /**
     * 无主讲
     */
    NOT_SPEAKER {
        @Override
        public String toString() {
            return "无主讲";
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
