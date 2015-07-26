package com.joy1joy.app.bean;

import org.apache.ibatis.type.Alias;

/**
 * 字典表
 * 
 * @author boyd
 *
 */
@Alias("TDict")
public class TDict implements java.io.Serializable {

	private Integer id;
	/**
	 * 字典类别(目前有的值:attype=活动类别字典,其它类型字典根据需要添加)
	 */
	private String dtype;
	/**
	 * 字典key
	 */
	private String dkey;

	/**
	 * 父字典key
	 */
	private String pdkey;
	/**
	 * 字典值
	 */
	private String dvalue;
	/**
	 * 排序
	 */
	private int dorder;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getDkey() {
		return dkey;
	}

	public void setDkey(String dkey) {
		this.dkey = dkey;
	}

	public String getPdkey() {
		return pdkey;
	}

	public void setPdkey(String pdkey) {
		this.pdkey = pdkey;
	}

	public String getDvalue() {
		return dvalue;
	}

	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}

	public int getDorder() {
		return dorder;
	}

	public void setDorder(int dorder) {
		this.dorder = dorder;
	}

}
