package com.joy1joy.app.actions.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSONObject;
import com.joy1joy.app.bean.TUsers;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results({
		@Result(name = "login", type = "redirect", location = "/user/jumpLogin.action"),
		@Result(name = "noAdminPri", type = "redirect", location = "/noAdminPri.action") })
public class BaseAction extends ActionSupport implements IAction,
		ServletRequestAware, ServletResponseAware, SessionAware {

	@SuppressWarnings("unchecked")
	protected Map session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	private Logger logger = Logger.getLogger(BaseAction.class);
	/**
	 * 分页的索引
	 */
	protected String pageIndex;

	/**
	 * 显示的条数
	 */
	protected int showCount = 5;

	/**
	 * 获得分页的总数
	 * 
	 * @param totalCount
	 * @return
	 */
	public int getPageNum(int totalCount) {
		int pageNum = totalCount % showCount == 0 ? totalCount / showCount
				: totalCount / showCount + 1;
		return pageNum;
	}

	/**
	 * 获得起始索引
	 * 
	 * @return
	 */
	public int getStartIndex() {
		int startIndex = (Integer.valueOf(pageIndex) - 1) * showCount;
		return startIndex;
	}

	public Map getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setSession(Map session) {
		this.session = session;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	/**
	 * 返回JSON格式的字符串
	 * 
	 * @param code
	 * @param msg
	 * @param o
	 * @return
	 * @author:xujun
	 */
	protected String jsonData(int code, String msg, Object o) {
		JSONObject json = new JSONObject();
		json.put(CODE, code);
		json.put(MSG, msg);
		json.put(DATA, o);

		return json.toString();

	}

	protected int getLoginUserId() {
		int id = -1;
		Object o = ActionContext.getContext().getSession().get("users");
		if (null != o) {
			TUsers user = (TUsers) o;
			id = user.getUid();
		}
		// for test
		// id = 1;
		logger.debug("登录用户id:" + id);
		return id;
	}
}