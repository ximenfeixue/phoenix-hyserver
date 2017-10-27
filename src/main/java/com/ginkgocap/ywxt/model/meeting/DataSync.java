package com.ginkgocap.ywxt.model.meeting;

import java.io.Serializable;

/**
 * Created by Wang fei on 2017/5/31.
 */
public class DataSync<T> implements Serializable {

    private static final long serialVersionUID = 8746712210836358492L;

    /**
     * 唯一 主键
     */
    private Object id;
    /**
     * 同步数据 对象
     */
    private T data;

    public DataSync(){}

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
