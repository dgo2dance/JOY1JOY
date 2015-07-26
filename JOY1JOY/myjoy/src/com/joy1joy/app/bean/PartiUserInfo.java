package com.joy1joy.app.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/*
 *
 *@author xujun
 */
@Alias("partiUser")
public class PartiUserInfo {
	private int id;
	private int atid;
	private double joyFee;// 报名费用
	private int uid;
	private String userid;// 用户名称
	private String mobile;// 手机号
	private String icon;// 头像
	@JSONField(format = "yyyy-MM-hh HH:mm:ss")
	private Date partiTime;// 报名时间
	private int partiNum;// 报名人数
	private String name;
	private int atNum;
	private String partiTimeStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAtid() {
		return atid;
	}

	public void setAtid(int atid) {
		this.atid = atid;
	}

	public double getJoyFee() {
		return joyFee;
	}

	public void setJoyFee(double joyFee) {
		this.joyFee = joyFee;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getPartiTime() {
		return partiTime;
	}

	public void setPartiTime(Date partiTime) {
		this.partiTime = partiTime;
	}

	public int getPartiNum() {
		return partiNum;
	}

	public void setPartiNum(int partiNum) {
		this.partiNum = partiNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAtNum() {
		return atNum;
	}

	public void setAtNum(int atNum) {
		this.atNum = atNum;
	}

	public String getPartiTimeStr() {
		return partiTimeStr;
	}

	public void setPartiTimeStr(String partiTimeStr) {
		this.partiTimeStr = partiTimeStr;
	}

}
