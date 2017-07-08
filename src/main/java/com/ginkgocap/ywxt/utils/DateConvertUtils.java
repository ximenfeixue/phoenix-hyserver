package com.ginkgocap.ywxt.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qingc email:qingc(a)gintong.com
 * @version 1.0
 * @since 1.0
 */
public class DateConvertUtils {
	private final static Logger logger = LoggerFactory.getLogger(DateConvertUtils.class);

	public static java.util.Date parse(String dateString,String dateFormat) {
		return parse(dateString, dateFormat,java.util.Date.class);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends java.util.Date> T parse(String dateString,String dateFormat,Class<T> targetResultType) {
		if(StringUtils.isEmpty(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			long time = df.parse(dateString).getTime();
			java.util.Date t = targetResultType.getConstructor(long.class).newInstance(time);
			return (T)t;
		} catch (ParseException e) {
			String errorInfo = "cannot use dateformat:"+dateFormat+" parse datestring:"+dateString;
			throw new IllegalArgumentException(errorInfo,e);
		} catch (Exception e) {
			throw new IllegalArgumentException("error targetResultType:"+targetResultType.getName(),e);
		}
	}
	/** 
	 * 日期转化为日期字符串
	 * @param date
	 * @return
	 */
	public static String format(java.util.Date date,String dateFormat) {
		 if(date == null) {
			 return null;
		 }
		 return new SimpleDateFormat(dateFormat).format(date);
	}
	/**
	 * 日期转化为日期字符串
	 * @param date
	 * @return
	 */
	public static String format(java.util.Date date) {
		 return format(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 转化日期字符串为日期
	 * @param dateStr 日期字符串 
	 * @return 日期
	 * @throws ParseException
	 */
	public static Date parse(String dateStr)  {
		if(Utils.isNullOrEmpty(dateStr) || "null".equals(dateStr)){
			return null;
		}
		 try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			 logger.error("Parse date failed. dateStr: " + dateStr + " error: " + e.getMessage());
		}
		 return null;
	}
}
