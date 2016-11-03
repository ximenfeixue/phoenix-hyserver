package com.ginkgocap.ywxt.utils;

import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.util.Constants;
import com.ginkgocap.ywxt.util.sso.session.SessionManager;

/**
 * @author qingc email:qingc(a)gintong.com
 * @date 2014-10-1
 * @version 1.0
 * @since 1.0
 */
public class Constant {
	
	/**
	 * 允许修改会议的时间 ，单位 小时
	 */
	public static final int ALLOW_MODIFY_MEETING_TIME = 0;
	/**
	 * 会议时间格式
	 */
	public static final String MEETING_DATE_FORMAT="yyyy年MM月dd E HH:mm";
	/**
	 * 坐标范围
	 */
	public static final int ADDRESS_POS_RANGE =100;
	/**
	 * 通知分割字符串字符
	 */
	public static final String NOTICE_CONTENT_SPLIT_CHAR ="<";
	/**
	 * 坐标范围
	 */
	public static final Long ONE_SECOND_TIMES =1000l;
}

