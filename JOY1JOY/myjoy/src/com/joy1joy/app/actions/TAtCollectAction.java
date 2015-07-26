package com.joy1joy.app.actions;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.joy1joy.app.actions.base.BaseAction;
import com.joy1joy.app.bean.CollectAtResult;
import com.joy1joy.app.bean.TAtCollect;
import com.joy1joy.app.core.annotation.LoginAccess;
import com.joy1joy.app.service.ITAtCollectService;
import com.opensymphony.xwork2.ActionContext;

/*
 *
 *@author:xujun
 */
@Namespace("/collect")
public class TAtCollectAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TAtCollect collect;
	private Logger logger = Logger.getLogger(TAtCollectAction.class);
	@Autowired
	private ITAtCollectService atCollectService;

	@LoginAccess
	@Action(value = "add", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String add() {
		logger.debug("收藏活动");
		int code = -1;
		String msg = MSG_FAILURE;
		try {
			if (null != collect) {
				collect.setUid(getLoginUserId());
				collect.setCollectTime(new Date());
				try {
					boolean ok = false;

					TAtCollect find = atCollectService
							.selectTAtCollect(collect);
					if (null == find) {
						int i = atCollectService.insertTAtCollect(collect);
						if (i > 0) {
							ok = true;
							logger.debug("收藏活动成功");
						} else {
							logger.debug("收藏活动失败!");
							ok = false;
						}
					} else {
						ok = true;
					}
					if (ok) {
						code = R_SUCCESS;
						msg = MSG_SUCCESS;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
		}
		ActionContext.getContext().put(JSON_DATA,
				this.jsonData(code, msg, null));
		return C_SUCCESS;
	}

	@LoginAccess
	@Action(value = "list", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String joinList() {
		logger.debug("查询收藏记录");
		int code = -1;
		String msg = MSG_FAILURE;

		List<CollectAtResult> data = null;
		try {

			data = atCollectService.selectByUserid(this.getLoginUserId());
			code = 1;
			msg = MSG_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, data));
		return C_SUCCESS;
	}

	public TAtCollect getCollect() {
		return collect;
	}

	public void setCollect(TAtCollect collect) {
		this.collect = collect;
	}

}
