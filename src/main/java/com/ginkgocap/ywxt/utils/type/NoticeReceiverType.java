package com.ginkgocap.ywxt.utils.type;



/**
 * 通知接收人类型
 * 
 * @author qingc
 */
public enum NoticeReceiverType {

    /**
     * 发起者
     */
	CREATER {
        @Override
        public String toString() {
            return "发起者";
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
    }
    ;
    @Override
    public abstract String toString();
    public abstract int code();

}
