package com.joy1joy.app.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/*
 *
 *@author:xujun
 */
@Alias("TAtUsers")
public class TAtUsers {
	private int id;
	private int atid;
	private int uid;
	private int pnum;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date addtime = new Date();
	private String remark;
	private String iphone;
	private String idcard;
	
	public String getIphone() {
		return iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}


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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
