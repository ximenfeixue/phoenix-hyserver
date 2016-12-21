package com.ginkgocap.ywxt.utils.type;



/**
 * 会议成员类型
 * 
 * @author qingc
 */
public enum MemberType {

    /**
     * 嘉宾
     */
	VIP {
        @Override
        public String toString() {
            return "嘉宾";
        }
        @Override
        public int code() {
        	return 0;
        }
    },
    /**
     * 参会人
     */
    PARTICIPANTS {
        @Override
        public String toString() {
            return "参会人";
        }
        @Override
        public int code() {
        	return 1;
        }
    },
    /**
     * 参会人
     */
    CREATER {
        @Override
        public String toString() {
            return "创建人";
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
