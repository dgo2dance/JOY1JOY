package com.joy1joy.app.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 活动表
 * 
 * @author xujun
 */
@Alias("TActiviity")
public class TActivity {
	private int id = -1;
	private String name;
	private int status;
	private String address;
	@JSONField(format = "yyyy-MM-dd")
	private Date stime;
	@JSONField(format = "yyyy-MM-dd")
	private Date etime;
	private String type;
	private String typeName;
	private String addressName;
	private String poster;
	private int pnum;
	private String mark;
	private String description;
	private String attention;
	private String caution;
	private double fee;
	private double joyFee;
	private String gqr;
	private int autoSMS;
	private String smsContent;
	private int cuid;
	private String cuserName;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date cdatetime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date audiDatetime;

	private int partNum;
	private String cuserIcon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getCaution() {
		return caution;
	}

	public void setCaution(String caution) {
		this.caution = caution;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getJoyFee() {
		return joyFee;
	}

	public void setJoyFee(double joyFee) {
		this.joyFee = joyFee;
	}

	public String getGqr() {
		return gqr;
	}

	public void setGqr(String gqr) {
		this.gqr = gqr;
	}

	public int getAutoSMS() {
		return autoSMS;
	}

	public void setAutoSMS(int autoSMS) {
		this.autoSMS = autoSMS;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public int getCuid() {
		return cuid;
	}

	public void setCuid(int cuid) {
		this.cuid = cuid;
	}

	public Date getCdatetime() {
		return cdatetime;
	}

	public void setCdatetime(Date cdatetime) {
		this.cdatetime = cdatetime;
	}

	public Date getAudiDatetime() {
		return audiDatetime;
	}

	public void setAudiDatetime(Date audiDatetime) {
		this.audiDatetime = audiDatetime;
	}

	public String getCuserName() {
		return cuserName;
	}

	public void setCuserName(String cuserName) {
		this.cuserName = cuserName;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getPartNum() {
		return partNum;
	}

	public void setPartNum(int partNum) {
		this.partNum = partNum;
	}

	public String getCuserIcon() {
		return cuserIcon;
	}

	public void setCuserIcon(String cuserIcon) {
		this.cuserIcon = cuserIcon;
	}

}
