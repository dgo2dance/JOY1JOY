package com.joy1joy.app.actions;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.joy1joy.app.actions.base.BaseAction;
import com.joy1joy.app.bean.Option;
import com.joy1joy.app.bean.TDict;
import com.joy1joy.app.service.ITDictService;
import com.opensymphony.xwork2.ActionContext;

/**
 *
 * @author xujun
 */
@Namespace("/dict")
public class TDictAction extends BaseAction {

	@Autowired
	private ITDictService dictService;

	private String dtype;
	private String fdkey;

	private Logger logger = Logger.getLogger(TDictAction.class);

	@Action(value = "findOptions", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String getOptions() {
		logger.debug("查询字典选项!");
		int code = -1;
		String msg = MSG_FAILURE;
		Object o = null;

		try {
			List<Option> list = dictService.selectOptions(dtype);
			logger.error("查询字典选项成功!");
			code = R_SUCCESS;
			msg = MSG_SUCCESS;
			o = list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询字典选项失败!");
		}

		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, o));
		return C_SUCCESS;
	}

	@Action(value = "findSubOptions", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String getSubOptions() {
		logger.debug("查询字典子选项!");
		int code = -1;
		String msg = MSG_FAILURE;
		Object o = null;

		try {
			TDict dict = new TDict();
			dict.setDtype(dtype);
			dict.setPdkey(fdkey);
			List<Option> list = dictService.selectSubOptions(dict);
			logger.error("查询字典子选项成功!");
			code = R_SUCCESS;
			msg = MSG_SUCCESS;
			o = list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询字典子选项失败!");
		}

		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, o));
		return C_SUCCESS;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getFdkey() {
		return fdkey;
	}

	public void setFdkey(String fdkey) {
		this.fdkey = fdkey;
	}
}
