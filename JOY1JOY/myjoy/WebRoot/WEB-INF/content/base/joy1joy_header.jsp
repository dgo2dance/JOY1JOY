<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.joy1joy.app.bean.TUsers"%>
<%
	String path = request.getContextPath();
	if (!"".equals(path)) {
		if (!path.endsWith("/")) {
			path = path + "/";
		}
	} else {
		path = "/";
	}
%>
	<%
		Object syncs = request.getSession().getAttribute("synclogin");
		String s=(String)syncs;
		if (null != s) {
	%>
	<%=s%>
	<% } %>
<link rel='icon' href="<%=path%>images/favcion.ico" type=‘image/x-ico’ />
	
<input type="hidden" id="joy-context-path" value="<%=path%>" />
<div class="m-header">
	<div class="wrap">
		<h1 class="m-header-logo">
			<a href="<%=path%>home.action"> <img src="<%=path%>images/logo.png" title="动一动logo"
				alt="动一动"> <em>动一动</em>
			</a>
		</h1>

		<ul class="m-header-nav">
			<li><a data-api="hot" href="<%=path%>home.action" class="">首页</a></li>
			<li><a data-api="expert" href="<%=path%>at/atlist.action"
				class="">找活动</a></li>
			<li><a data-api="newest" href="http://127.0.0.1/joy1joy/forum.php" class="" target="_blank">动社区</a></li>
		</ul>

		<form id="header-search-form" class="m-header-search" action="#"
			method="get">
			<!-- 模块 m-search -->
			<label class="m-search"> <input class="m-input-text"
				id="at_search_input_header" type="text" placeholder="发现身边的户外活动"
				name="keyword" value=""> <input class="m-search-submit"
				type="submit" value="">
			</label>
		</form>
	</div>
	<%
		Object o = request.getSession().getAttribute("users");
		if (null == o) {
	%>
	<div class="m-header-account">
		<div class="m-account">
			<ul class="m-account-login">
				<li><a class="js-login" href="<%=path%>user/jumpLogin.action">登录</a></li>
				<li><a href="<%=path%>user/register.action" target="_blank">注册</a></li>
			</ul>
		</div>
	</div>
	<%
		} else {

			TUsers user = (TUsers) o;
			String icon = user.getIcon();
	%>
	<div class="m-header-account" style="z-index: 100;">
		<div class="m-account">

			<div class="m-account-logined">
				<div>
					<a href="#"><img src="<%=path.substring(0, path.length() - 1)%><%=icon%>" alt="头像" title=""></a>
				</div>
				<ul style="">
					<li><a href="<%=path%>at/joinAt.action">我的主页</a></li>
					<li><a href="<%=path%>user/jumpUserDetail.action">修改资料</a></li>
					<li><a class="js-logout" href="<%=path%>user/exit.action">退出</a></li>
				</ul>
			</div>
		</div>
	</div>
	<%
		}
	%>
</div>
<script type="text/javascript">
	$(function() {
		$("#header-search-form").submit(
				function() {
					var iv = $.trim($("#at_search_input_header").val());
					if (iv == "") {
						joy.alert("请填写搜索关键字!");
					} else {
						window.location = joy.getContextPath()
								+ "/at/search.action?keyWord=" + encodeURIComponent(iv);
					}
					return false;
				});
	});
</script>