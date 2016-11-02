package com.ginkgocap.ywxt.common.base;

import java.lang.reflect.Field;

import com.ginkgocap.ywxt.utils.Utils;
import com.sun.star.bridge.oleautomation.Date;


/**
 * 名称: BaseEntity
 * 描述: model基类
  * @since  2014-09-14
 * @author qingc
 */
public class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7200095849148417467L;

	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	
	protected static final String TIME_FORMAT = "HH:mm:ss";
	
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
}
