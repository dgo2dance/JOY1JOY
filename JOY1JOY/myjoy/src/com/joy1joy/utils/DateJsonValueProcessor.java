package com.joy1joy.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/*** 将Bean中的Timestamp转换为json中的日期字符串 */
public class DateJsonValueProcessor implements JsonValueProcessor {
	public static final String Default_DATE_PATTERN = "yyyy-MM-dd";
	private DateFormat dateFormat;

	public DateJsonValueProcessor(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception e) {
			dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
		}
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		return dateFormat.format((Date) value);
	}
	public static String beanToJson(Object s,String format){  
	    JsonConfig config=new JsonConfig();  
	    config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(format));  
	    JSONObject json=JSONObject.fromObject(s,config);  
	    return json.toString();
	} 
}
