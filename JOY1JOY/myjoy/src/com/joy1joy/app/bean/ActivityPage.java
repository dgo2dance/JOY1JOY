package com.joy1joy.app.bean;

import org.apache.ibatis.type.Alias;

/**
 *
 * @author xujun
 */
@Alias("page")
public class ActivityPage {
	private int no = 1;// 默认第一页
	private int size = 10;// 默认10条
	private int start;

	private String type;// 活动类别
	private double minJoyFee;// 费用最小值
	private double maxJoyFee;// 费用最大值
	private int time;// 活动时间
	private int uid;
	private int totalPages;
	private int status = -100;// 活动状态
	private String keyWord;

	public ActivityPage(int no) {
		if (no > 1)
			this.no = no;
		init();
	}

	public ActivityPage(int no, int size) {
		// TODO Auto-generated constructor stub
		if (no > 1)
			this.no = no;
		if (size > 0)
			this.size = size;
		init();
	}

	private void init() {
		start = (this.no - 1) * this.size;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStart() {

		return start;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMinJoyFee() {
		return minJoyFee;
	}

	public void setMinJoyFee(double minJoyFee) {
		this.minJoyFee = minJoyFee;
	}

	public double getMaxJoyFee() {
		return maxJoyFee;
	}

	public void setMaxJoyFee(double maxJoyFee) {
		this.maxJoyFee = maxJoyFee;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
