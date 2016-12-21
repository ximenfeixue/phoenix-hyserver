package com.ginkgocap.ywxt.utils.type;

import java.util.EnumSet;


/**
 * 资料类型
 * 
 * @author qingc
 */
public enum DataType {

    /**
     * 需求
     */
    REQUIREMENT {
        @Override
        public String toString() {
            return "需求";
        }
        @Override
        public int code() {
            return 0;
        }
    },
    /**
     * 无主讲
     */
    /**
     * 知识
     */
    KNOWLEDGE {
        @Override
        public String toString() {
            return "知识";
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
