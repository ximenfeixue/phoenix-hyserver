package com.ginkgocap.ywxt.utils.type;

import java.util.EnumSet;


/**
 * 资料类型
 * 
 * @author qingc
 */
public enum ExcuteMeetSignType {

    /**
     * 未处理
     */
	UNTREATED {
        @Override
        public String toString() {
            return "未处理";
        }
        @Override
        public int code() {
            return 0;
        }
    },
    /**
     * 同意报名
     */
    AGREE_SIGN_UP {
        @Override
        public String toString() {
            return "同意报名";
        }
        @Override
        public int code() {
            return 1;
        }
    },
    /**
     * 拒绝报名
     */
    REFUSE_SIGN_UP {
        @Override
        public String toString() {
            return "拒绝报名";
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
