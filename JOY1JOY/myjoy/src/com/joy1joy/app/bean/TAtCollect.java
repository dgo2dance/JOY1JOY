package com.joy1joy.app.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/*
 *
 *@author:xujun
 */
@Alias("TAtCollect")
public class TAtCollect {
	private int id;
	private int atid;
	private int uid;
	private Date collectTime;

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

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
}
