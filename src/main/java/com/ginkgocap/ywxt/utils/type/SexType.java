package com.ginkgocap.ywxt.utils.type;



/**
 * 性别类型
 * 
 * @author qingc
 */
public enum SexType {

    /**
     * 男
     */
	MALE {
        @Override
        public String toString() {
            return "男";
        }
    },
    /**
     * 女
     */
    FEMALE {
        @Override
        public String toString() {
            return "女";
        }
    },
    /**
     * 未知
     */
    UNKNOWN {
        @Override
        public String toString() {
            return "未知";
        }
    }
    ;
    @Override
    public abstract String toString();

}
