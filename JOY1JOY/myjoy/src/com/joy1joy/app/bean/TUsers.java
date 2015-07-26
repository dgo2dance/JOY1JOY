package com.joy1joy.app.bean;

import java.util.Date;

/**
 * 用户表
 * @author Administrator
 *
 */
public class TUsers implements java.io.Serializable{

	
	/**
	 * 用户状态 ：正常
	 */
	public static final char USER_STATUE_NORMAL = 'N';
	/**
	 * 用户状态：删除
	 */
	public static final char USER_STATUE_DELETE ='D';
	/**
	 * 用户状态  ：锁定
	 */
	public static final char USER_STATUE_LOCK= 'L';
	/**
	 * 普通用户
	 */
	public static final int USER_COMMON=0;
	/**
	 * 管理员
	 */
	public static final int USER_MANAGER=1;
	private Integer uid;
	/**
	 * 登录名
	 */
	private String userid;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户状态(N=正常,D=删除,L=锁定)
	 */
	private char status;
	/**
	 * 账号类别(0=普通用户,1=管理员)
	 */
	private Integer type;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 性别 0男 1女
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Date birthdate;
	/**
	 * 心情短语
	 */
	private String state;
	/**
	 * qq
	 */
	private String qq;
	
	/**
	 * 头像
	 */
	private String icon;
	/**
	 * 备注说明
	 */
	private String remark;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
