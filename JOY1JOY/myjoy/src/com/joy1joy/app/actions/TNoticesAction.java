package com.joy1joy.app.actions;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.joy1joy.app.actions.base.BaseAction;
import com.joy1joy.app.bean.TDict;
import com.joy1joy.app.bean.TNotices;
import com.joy1joy.app.bean.TUsers;
import com.joy1joy.app.core.annotation.AdminAccess;
import com.joy1joy.app.core.annotation.LoginAccess;
import com.joy1joy.app.service.ITDictService;
import com.joy1joy.app.service.ITNoticeService;
import com.joy1joy.utils.DateJsonValueProcessor;
import com.opensymphony.xwork2.ActionContext;

/**
 * <p>
 * Title: TNoticesAction.java
 * </p>
 * 
 * <p>
 * Description: 公告控制层
 * </p>
 * 
 * <p>
 * Date: 2015-4-20
 * </p>
 * 
 * <p>
 * Time: 上午11:42:57
 * </p>
 * 
 * <p>
 * Copyright: 2015
 * </p>
 * 
 * 
 * @author boyd
 * @version 1.0
 */
@Results({
		@Result(name = "success", location = "/WEB-INF/content/user/success.jsp"),
		@Result(name = "success", location = "/WEB-INF/content/user/fail.jsp") })
@Namespace(value = "/notice")
public class TNoticesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static String dateFormat = "yyyy.MM.dd";
	private Integer noticeId;

	private String noticeTitle;
	private String noticeContent;
	private String noticeType = "knowledge";
	private int noticeState = 0;
	private String type = "";
	private int start;
	private int end;

	public TNoticesAction() {
		setShowCount(3);

	}

	// 引入业务层
	@Autowired
	ITNoticeService noticeService;
	// 引入业务层
	@Autowired
	ITDictService dictService;

	@Action(value = "findNoticeList", results = {
			@Result(name = "success", location = "/WEB-INF/content/notice/noticeList.jsp"),
			@Result(name = "error", location = "/WEB-INF/content/notice/success.jsp") })
	public String findNoticeList() {
		// 根据登录人的角色信息，判断noticeState的取值。普通用户为2，管理员为所有
		if (session.get("users") != null) {
			TUsers users = (TUsers) session.get("users");
			if (users.getType() == 0) {
				noticeState = TNotices.NOTICE_STATUE_RELEASE;
			}
		}
		// 查询公告字典集合
		List<TDict> dicts = dictService.findDictByType("notice");
		for (TDict dict : dicts) {
			// 获取公告总数
			int totalCount = noticeService.selectTNoticesCount(dict.getDkey(),
					noticeState);

			int pageNum = getPageNum(totalCount);
			// System.out.println(dict.getDkey()+"PageNum");
			request.setAttribute(dict.getDkey() + "PageNum", pageNum);
		}

		return C_SUCCESS;
	}

	@Action(value = "noticeList")
	public void noticeList() {
		try {
			// 根据登录人的角色信息，判断noticeState的取值。普通用户为0，管理员为所有 1
			if (session.get("users") != null) {
				TUsers users = (TUsers) session.get("users");
				if (users.getType() == 0) {
					noticeState = TNotices.NOTICE_STATUE_DELETE;
				}
			}
			PrintWriter out = null;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			int startIndex = getStartIndex();
			List<TNotices> noticeLists = noticeService.findNoticeListByLimit(
					noticeType, noticeState, startIndex, showCount);
			JSONArray jsonArray = new JSONArray();
			for (TNotices notice : noticeLists) {
				jsonArray.add(DateJsonValueProcessor.beanToJson(notice,
						dateFormat));
			}
			String json = "{\"rows\":" + jsonArray.toString() + "}";
			out.print(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AdminAccess
	@LoginAccess
	@Action(value = "jumpSaveNotice", results = { @Result(name = "success", location = "/WEB-INF/content/notice/noticeAdd.jsp") })
	public String jumpSave() {
		// 查询公告字典集合
		List<TDict> dicts = dictService.findDictByType("notice");
		request.setAttribute("dcits", dicts);
		return C_SUCCESS;
	}

	@AdminAccess
	@LoginAccess
	@Action(value = "saveNotice", results = {
			@Result(name = "success", location = "/WEB-INF/content/notice/noticeList.jsp"),
			@Result(name = "error", location = "/WEB-INF/content/notice/success.jsp"),
			@Result(name = "input", location = "/WEB-INF/content/notice/noticeInput.jsp") })
	public String save() {
		TNotices notices = new TNotices();
		notices.setTitle(noticeTitle);
		notices.setType(noticeType);
		notices.setContent(noticeContent);
		notices.setCdatetime(new Timestamp(System.currentTimeMillis()));
		notices.setStatus(TNotices.NOTICE_STATUE_RELEASE);
		if (session.get("users") == null) {
			return C_INPUT;
		}
		TUsers users = (TUsers) session.get("users");
		notices.setCuid(users.getUid());
		boolean result = this.noticeService.addNotices(notices);
		if (result) {
			findNoticeList();
			request.setAttribute("noticeType", noticeType);
			return C_SUCCESS;
		} else {
			return C_ERROR;
		}
	}

	@Action(value = "detailNotice", results = {
			@Result(name = "success", location = "/WEB-INF/content/notice/noticeDetail.jsp"),
			@Result(name = "error", location = "/WEB-INF/content/notice/success.jsp") })
	public String findNoticeDetail() {
		TNotices tnotice = this.noticeService.selectTNoticesById(noticeId);
		if (null != tnotice) {
			// request.setAttribute("tnotice", tnotice);
			ActionContext.getContext().put("tnotice", tnotice);
			return C_SUCCESS;
		} else {
			return C_ERROR;
		}

	}

	@Action(value = "recent", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String update() {
		Log.debug("获取最新发布的通知或公告");
		int code = -1;
		String msg = MSG_FAILURE;
		List<TNotices> data = null;
		try {
			Map map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("start", start);
			map.put("end", end);
			data = noticeService.selectRecentNotices(map);
			code = 1;
			msg = MSG_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, data));
		return C_SUCCESS;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
