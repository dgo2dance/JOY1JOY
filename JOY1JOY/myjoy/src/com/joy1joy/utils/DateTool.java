package com.joy1joy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Title: DateTool.java
 * </p>
 * 
 * <p>
 * Description:日期工具类
 * </p>
 * 
 * <p>
 * Date: 2015-4-20
 * </p>
 * 
 * <p>
 * Time: 12:09:24 PM
 * </p>
 * 
 * <p>
 * Copyright: 2015
 * </p>
 * 
 * <p>
 * Company: yt
 * </p>
 * 
 * @author 张亚博
 * @version 1.0
 * 
 *          <p>=
 *          ===========================================
 *          </p>
 *          <p>
 *          Modification History
 *          <p>
 *          Mender:
 *          </p>
 *          <p>
 *          Date:
 *          </p>
 *          <p>
 *          Reason:
 *          </p>
 *          <p>=
 *          ===========================================
 *          </p>
 */
public class DateTool {

	public static final String DATE_STYLE_BEGIN = "yyyy-MM-dd 00:00:00";
	public static final String DATE_STYLE_END = "yyyy-MM-dd 23:59:59";
	public static final String DATE_STYLE_BASIC = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_STYLE_OTHER = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_STYLE_SIMPLE = "yyyy-MM-dd";

	/**
	 * 将日期按指定格式格式化
	 * 
	 * @param date
	 *            日期对象
	 * @param style
	 *            样式
	 * @return
	 */
	public static Date formatDate(Date date, String style) {
		SimpleDateFormat baseSdf = new SimpleDateFormat(style);
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		try {
			return baseSdf.parse(sdf.format(date));
		} catch (ParseException e) {
			System.out.println("格式化日期异常");
		}
		return null;
	}

	/**
	 * 将日期按指定格式转换成字符串
	 * 
	 * @param date
	 * @param style
	 * @return
	 */
	public static String dateToString(Date date, String style) {
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		if (date != null) {
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * 将字符串按指定格式转换成Date
	 * 
	 * @param str
	 * @param style
	 * @return
	 */
	public static Date stringToDate(String str, String style) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			date = new Date();
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 * @author:xujun
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获得时间 分钟差
	 * @param dateNew
	 * @param dateOld
	 * @return
	 */
	public static long getTimeDiff(Date dateNew,Date dateOld)
	{
       //计算差值，分钟数
        long minutes=(dateNew.getTime()-dateOld.getTime())/(1000*60);
        return minutes;
	}
	public static void main(String[] args) {
		System.out.print(DateTool.dateToString(new Date(),
				DateTool.DATE_STYLE_BEGIN));
	}
	
	
}
