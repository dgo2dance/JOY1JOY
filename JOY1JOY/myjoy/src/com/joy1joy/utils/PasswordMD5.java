package com.joy1joy.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/** 
 * <p>Title: PasswordMD5.java</p>
 * 
 * <p>Description:MD5加密 </p>
 * 
 * <p>Date: 2015-4-20</p>
 * 
 * <p>Time: 9:09:13 AM</p>
 * 
 * <p>Copyright: 2015</p>
 * 
 * <p>Company: yt</p>
 * 
 * @author 张亚博
 * @version 1.0
 * 
 * <p>============================================</p>
 * <p>Modification History
 * <p>Mender: </p>
 * <p>Date: </p>
 * <p>Reason: </p>
 * <p>============================================</p>
 */
public class PasswordMD5 {
	
	/**
	 * 密码加密
	 * @param psw 需要加密的密码
	 * @return MD5加密后的字符串
	 * @throws Exception MD5加密异常
	 */
	public static String createEncryptPSW(String psw) throws Exception {
		MessageDigest messagedigest = null;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
			messagedigest.update(psw.getBytes("UTF8"));
			byte abyte0[] = messagedigest.digest();
			return (new BASE64Encoder()).encode(abyte0);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("MD5加密异常!", e);
		} catch (UnsupportedEncodingException e) {
			throw new Exception("MD5加密异常!", e);
		}
	}
	
	public static void main(String[] arg0){
		
		try {
			System.out.println(createEncryptPSW("123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
