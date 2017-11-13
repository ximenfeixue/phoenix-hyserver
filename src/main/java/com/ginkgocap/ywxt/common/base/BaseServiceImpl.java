package com.ginkgocap.ywxt.common.base;

import java.io.Serializable;

import org.springframework.stereotype.Controller;

import com.ginkgocap.ywxt.utils.Utils;


/**
 * controller基类
 * @author qingc
 *
 */
public class BaseServiceImpl<T, PK extends Serializable> {
	
//	============
	
	 /**
     * 判断对象是否为null或空
     * @param obj
     * return IOException
     */
	public static boolean isNullOrEmpty(Object obj){
        return Utils.isNullOrEmpty(obj);
    }
}
