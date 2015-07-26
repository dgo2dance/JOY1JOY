package com.joy1joy.utils;

import java.util.List;
import java.util.Set;

/** 
 *List集合操作工具类 
 */
public class ListTool {

	
	/**
	 * 判断list集合是否为null或空
	 * @param list
	 * @return true（null或空），反之 false
	 */
	@SuppressWarnings("unchecked")
	public static boolean isNullOrEmpty(List list){
		if(list==null||list.size()<1)
			return true;
		return false;
	}
	
	/**
	 * 判断set集合是否为null或空
	 * @param set
	 * @return true（null或空），反之 false
	 */
	@SuppressWarnings("unchecked")
	public static boolean isNullOrEmpty(Set set){
		if(set==null||set.size()<1)
			return true;
		return false;
	}
	
	/**
	 * 判断数组是否为null或空
	 * @param obj
	 * @return true（null或空），反之 false
	 */
	public static boolean isNullOrEmpty(Object[] obj){
		if(obj==null||obj.length<1)
			return true;
		return false;
	}
	/**
	 * 截取标题字符串
	 * @param str
	 * @return
	 */
	public static String stringSub(String str){
		String strsub = str;
		if(str.length()>10){
		strsub = ""+str.substring(0, 10)+"...";
		}
		return strsub;
	}
	
	
}
