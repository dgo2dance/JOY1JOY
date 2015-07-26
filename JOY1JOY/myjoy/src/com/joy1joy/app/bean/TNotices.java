package com.joy1joy.app.bean;

import java.sql.Timestamp;

/**
 * 公告表
 * @author Administrator
 *
 */
public class TNotices implements java.io.Serializable{

	/**
	 * 公告状态 ：启用
	 */
	public static final int NOTICE_STATUE_ENABLE = 0;
	/**
	 * 公告状态：删除
	 */
	public static final int NOTICE_STATUE_DELETE =1;
	/**
	 * 公告状态  ：发布
	 */
	public static final int NOTICE_STATUE_RELEASE= 2;
	
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 类别
	 */
	private String type;
	
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 状态(0=启用,1=删除,2=发布)
	 */
	private Integer status;
	/**
	 * 创建人id
	 */
	private Integer cuid;
	/**
	 * 创建时间
	 */
	private Timestamp cdatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCuid() {
		return cuid;
	}
	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}
	public Timestamp getCdatetime() {
		return cdatetime;
	}
	public void setCdatetime(Timestamp cdatetime) {
		this.cdatetime = cdatetime;
	}
	
	
}
