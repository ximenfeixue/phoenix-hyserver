package com.ginkgocap.ywxt.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

/** @Description:  工具类   
 * @Author:       qingc  
 * @CreateDate:  2014-4-18   
 * @Version:      [v1.0]
 */
 
public class Utils {
	 /**
     * 判断对象是否为null或空
     * @param obj
     * return IOException
     */
	public static boolean isNullOrEmpty(Object obj){
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullOrEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    }
	/**
     * 判断所有对象对象是否为不等于null和不为空  不能用于含有依赖关系
     * @param obj
     * return IOException
     */
	public static boolean isAllNotNullOrEmpty(Object... obj){
        
		for(Object ob:obj){
			if(isNullOrEmpty(ob)){
				return false;
			}
		}
		return true;
    }
	/**
	 * qingc
	 * <p>获取ip地址，由于经过nginx跳转，所以不能单纯的request.getRemoteAddr</p> 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) { 
		String ip = request.getHeader("x-forwarded-for"); 
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) 
		ip = request.getHeader("Proxy-Client-IP"); 
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) 
		ip = request.getHeader("WL-Proxy-Client-IP"); 
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) 
		ip = request.getRemoteAddr(); 
		return ip; 
	}
	/**
	  * 得到本周周一
	  * 
	  */
	 public static Calendar getMondayOfThisWeek(Calendar c) {
		 if(isNullOrEmpty(c))
			   c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 1);
	  return c;
	 }
	 
	 /**
	  * 得到本周周六
	  * 
	  */
	 public static Calendar getSaturdayOfThisWeek(Calendar c) {
		 if(isNullOrEmpty(c))
	   c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 6);
	  return c;
	 }
	 /**
	  * 得到本周周日
	  * 
	  */
	 public static Calendar getSundayOfThisWeek(Calendar c) {
		 if(isNullOrEmpty(c))
	   c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 7);
	  return c;
	 }
	 /**
	  * 格式化日期
	  * 
	  */
	 public static String DateFormat(Date date,String formatStr){
		 if(isNullOrEmpty(date)){
			 return "";
		 }
		 if(isNullOrEmpty(formatStr)){
			 formatStr="yyyy-MM-dd HH:mm:ss";
		 }
		 if(formatStr.contains("E")){
			 formatStr=getDateStrWithChineseWeekFormatStr(date,formatStr);
		 }
		 SimpleDateFormat sim=new SimpleDateFormat(formatStr);
		 return sim.format(date);
	 }
	
	 /**
	  * 获取含有中文星期的日期串
	  * 
	  */
	 public static String getDateStrWithChineseWeekFormatStr(Date date,String formatStr){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 int i=calendar.get(Calendar.DAY_OF_WEEK);
		String weekStr="";
		 switch(i)
		 {
		     case 1:
		           weekStr="星期天";
		           break;
		     case 2:
		           weekStr="星期一";
		           break;
		     case 3:
		           weekStr="星期二";
		           break;
		     case 4:
		           weekStr="星期三";
		           break;
		     case 5:
		           weekStr="星期四";
		           break;
		     case 6:
		           weekStr="星期五";
		           break;
		     case 7:  
		    	 weekStr="星期六";
		           break;
		 }
		 formatStr=formatStr.replaceAll("E", weekStr);
		 return formatStr;
	 }
	 /**
	  * 格式化日期
	  * 
	  */
	 public static String DateFormat(Date date){
		 return DateFormat(date,null);
	 }
	    /** 把s转化为首字母小写
	     * @param s
	     * @return
	     */
	    public static String toLowerCaseFirstOne(String s)
	    {
	        if(Character.isLowerCase(s.charAt(0)))
	            return s;
	        else
	            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	    }
	    /** 把s转化为首字母大写
	     * @param s
	     * @return
	     */
	    public static String toUpperCaseFirstOne(String s)
	    {
	        if(Character.isUpperCase(s.charAt(0)))
	            return s;
	        else
	            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	    }
	 /**
	  * 判断对象是否被修改
	 * @param objBeging 修改前的对象
	 * @param objEnd 修改后的对象
	 * @return
	 */
	public static boolean isModify(Object objBeging,Object objEnd){
		boolean flag=false;
		if(isNullOrEmpty(objBeging)){
			if(!isNullOrEmpty(objEnd)){
				flag=true;
			}
		}else{
			flag=objBeging.equals(objEnd)?false:true;
		}
			return flag;
	}	
	
	public static <T> boolean treeSetEquals(TreeSet<T> set1,TreeSet<T> set2){
		List<T> list1=new ArrayList<T>();  
		List<T> list2=new ArrayList<T>();  
		if(set1.equals(set2)){
			return true;
		}else{
			list1.addAll(set1);
			list2.addAll(set2);
			return list1.equals(list2);
		}
	}
	/**
	 * 设置主键为0的设置为null
	 * @param obj
	 */
	@SuppressWarnings("unchecked")	
	public static void setPrimarykeyZoroToNull(Object obj){
		if(isNullOrEmpty(obj)){
			return;
		}else{
			Class classes=obj.getClass();
			try {
				Method methodGet=classes.getMethod("getId");
				try {
					Long resutlt=(Long) methodGet.invoke(obj);
					if(!isNullOrEmpty(resutlt)&&0==resutlt){
						try {
							Field filed=classes.getDeclaredField("id");
							filed.setAccessible(true);
							filed.set(obj, null);
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
					}
					System.out.println(resutlt);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
	/**生成聊天记录的messageID
	 * @param userId
	 * @return
	 */
	public static String genMessageID(Long userId){
		String msgID = null;
		try{
			Date now = new Date();
			String userid = userId + "";
			Random ran = new Random(System.currentTimeMillis());
			msgID = userid + now + ran.nextLong();
			msgID = StringUtils.getMD5Str(msgID);
		}catch(Exception e){
			
		}
		return msgID;
	}
}
