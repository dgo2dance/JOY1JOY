package com.joy1joy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/** 
 * 字符串处理工具类 
 * 
 */
public class StringTool {

	/**
	 * 判断字符串是否为null或空
	 * @param str 
	 * @return 为null和空时返回true，反之返回false
	 */
	public static boolean isNullOrEmpty(String str){
		if(str==null||"".equals(str))
		   return true;
		return false;
	}
	
	 /**
	 * 是否有非指定字符
	 * @param strCheck 需要检查的字符串
	 * @param strStand 字符范围
	 * @param bEnableEmpty 是否允许为空
	 * @return
	 */
	public static boolean IsInStr(String strCheck, String strStand, boolean bEnableEmpty) {
		boolean bFlag = false;
		if (bEnableEmpty) {
			if (isNullOrEmpty(strCheck))
				return true;
		} else {
			if (isNullOrEmpty(strCheck))
				return false;
		}
		try {
			bFlag = true;
			for (int i = 0; i < strCheck.length(); i++) {
				if (strStand.indexOf(strCheck.charAt(i)) < 0) {
					bFlag = false;
					break;
				}
			}
		} catch (Exception e) {
			bFlag = false;
		}
		return bFlag;
	}
	
	/**
	 * 去掉第一个数组中和第二个数组相同的元素
	 * @param oldStr 第一个数组
	 * @param newStr 第二个数组
	 * @return
	 */
	public static String[] removeSameStr(String[] oldStr,String[] newStr){
		StringBuffer sb=new StringBuffer("");
		if(ListTool.isNullOrEmpty(newStr))
			return oldStr;
		if(ListTool.isNullOrEmpty(oldStr))
			return null;
		for (String s1 : oldStr) {
			boolean flag=false;
			for (String s2 : newStr) {
				if(s1.equals(s2)){
					flag=true;
					break;
				}
			}
			if(!flag)
				sb.append(s1).append(",");
		}
		return sb.toString().split(",");
	}
	
	
	public static void main(String[] arg0){
//		String[] oldStr={"1","2","3","4","5","6","7","8"};
//		String[] newStr={"1"};
//		String[] t=removeSameStr(newStr,oldStr);
//	    for (String string : t) {
//			System.out.println(string);
//		}
		System.out.println(createRandom(true,4));
	}
	/**
	 * 判断当前请求是否为ajax
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
	/**
	 * 读取发送短信配置文件
	 * @return
	 */
	public static Map<String,String> getPropertityByKey()
	{
		Map<String,String> hash=new HashMap<String,String>();
		Properties prop = new Properties();   
	    InputStream in = StringTool.class.getResourceAsStream("/mobileConfig.properties"); 
	    try {
			prop.load(in);
			String restUrl=prop.getProperty("restUrl");
			String restPort=prop.getProperty("restPort");
			String acountSID=prop.getProperty("acountSID");
			String authToken=prop.getProperty("authToken");
			String appId=prop.getProperty("appId");
			String appTemplateId=prop.getProperty("appTemplateId");
			hash.put("restUrl", restUrl);
			hash.put("restPort", restPort);
			hash.put("acountSID", acountSID);
			hash.put("authToken", authToken);
			hash.put("appId", appId);
			hash.put("appTemplateId", appTemplateId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return hash;
	}
	
	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
}
