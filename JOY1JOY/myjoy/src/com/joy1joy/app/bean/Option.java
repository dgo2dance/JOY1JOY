package com.joy1joy.app.bean;

import org.apache.ibatis.type.Alias;

/**
 *
 * @author xujun
 */
@Alias("option")
public class Option {

	private String id;
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
