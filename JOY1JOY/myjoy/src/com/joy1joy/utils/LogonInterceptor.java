package com.joy1joy.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.joy1joy.app.bean.TUsers;
import com.joy1joy.app.core.annotation.AdminAccess;
import com.joy1joy.app.core.annotation.LoginAccess;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登陆验证拦截器,如果用户没有登陆系统则让用户到登陆验证页面进行登陆 当用户请求网站资源的时候,例如某个Action,在Action被执行之前,检
 * 查用户是否登陆了系统. 不足之处是该拦截器只能拦截Action,不能拦截jsp和servlet,所以不能
 * 
 * @author 张亚博
 */
@SuppressWarnings("serial")
public class LogonInterceptor extends AbstractInterceptor {
	private static Logger log = Logger.getLogger(LogonInterceptor.class);

	public LogonInterceptor() {
		// actionList.add("findNoticeList");
		// actionList.add("noticeList");
		// actionList.add("jumpSaveNotice");
		// actionList.add("saveNotice");
		// actionList.add("detailNotice");
	}

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		log.info("权限拦截器开始");

		ActionContext ac = invocation.getInvocationContext();

		Object action = invocation.getAction();
		String actionName = invocation.getProxy().getMethod();
		log.debug("方法名:" + actionName);
		Method[] methods = action.getClass().getMethods();
		Method targetMethod = null;
		for (Method m : methods) {
			if (m.getName().equals(actionName)) {
				targetMethod = m;
				break;
			}
		}

		AdminAccess adminAccess = targetMethod.getAnnotation(AdminAccess.class);
		LoginAccess loginAccess = targetMethod.getAnnotation(LoginAccess.class);

		boolean login_approved = false;

		if (null != loginAccess) {
			log.debug("需要登录权限");

			HttpServletRequest request = (HttpServletRequest) ac
					.get(ServletActionContext.HTTP_REQUEST);
			// 异步请求返回{checkSession:noSession}
			boolean isResult = StringTool.isAjaxRequest(request);

			login_approved = login_privilege(invocation);
			if (!login_approved) {//

				if (isResult) {
					getReturnJSON(ac, "checkSession", "noSession");
				}
				return "login";
			} else {// 登录过了
				if (null != adminAccess) {
					log.debug("需要管理员权限");
					TUsers user = (TUsers) ((Map) ac
							.get(ServletActionContext.SESSION)).get("users");
					if (user.getType() != 1) {
						if (isResult) {
							getReturnJSON(ac, "adminAccess", "noPrivilege");
						}
						return "noAdminPri";
					}

				}
			}
		}

		log.debug("继续执行对应的action");
		return invocation.invoke();
	}

	private void getReturnJSON(ActionContext ac, String key, String value)
			throws IOException {
		HttpServletResponse response = (HttpServletResponse) ac
				.get(ServletActionContext.HTTP_RESPONSE);
		JSONObject json = new JSONObject();
		json.put(key, value);

		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();

	}

	private boolean login_privilege(ActionInvocation invocation)
			throws IOException {
		boolean sessionCheck = false;
		// 取得Session。
		ActionContext ac = invocation.getInvocationContext();
		Map session = (Map) ac.get(ServletActionContext.SESSION);

		// log.info("验证sessin");
		if (session != null && session.get("users") != null) {
			sessionCheck = true;
		}
		return sessionCheck;
	}

}
