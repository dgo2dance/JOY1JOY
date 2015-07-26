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
	String s=path.substring(0, path.length() - 1);
	System.out.println("路径:"+s);
%>
<%
	Object o = request.getSession().getAttribute("users");
	if (null != o) {
		TUsers user = (TUsers) o;
		String icon = user.getIcon();
		if (user.getType() == 1) {
%>
<!-- 下面是左侧菜单  -->
<div class="side" id="left_side">
	<div class="user-info">
		<div class="avatar">
			<img src="<%=path.substring(0, path.length() - 1)%><%=icon%>"
				alt="头像"> <span></span>
		</div>

		<div class="name">
			<h3></h3>
			<p></p>
		</div>

		<div class="social">
			<span class="followers"> <strong>0</strong> <br> 粉丝
			</span> <span class="following"> <strong>0</strong> <br> 关注
			</span>
		</div>

	</div>
	<ul class="side-menu">
		<li id="joinAt.action" class="join active"><a
			href="<%=path%>at/joinAt.action"><i></i><span>已报名活动</span></a></li>
		<li id="organize.action" class="join"><a
			href="<%=path%>at/organize.action"><i></i><span>已组织活动</span></a></li>
		<li id="input.action" class="join"><a
			href="<%=path%>at/input.action"><i></i><span>发布活动</span></a></li>
		<li id="jumpSaveNotice.action" class="join"><a
			href="<%=path%>notice/jumpSaveNotice.action"><i></i><span>发布公告</span></a></li>
		<li id="audit.action" class="join"><a
			href="<%=path%>at/audit.action"><i></i><span>活动管理</span></a></li>
		<li id="myCollection.action" class="collect"><a
			href="<%=path%>at/myCollection.action"><i></i><span>我的收藏</span></a></li>
	</ul>
</div>
<%
	} else {
%><div class="side" id="left_side">
	<div class="user-info">
		<div class="avatar">
			<img src="<%=path.substring(0, path.length() - 1)%><%=icon%>"
				alt="头像"> <span></span>
		</div>

		<div class="name">
			<h3></h3>
			<p></p>
		</div>

		<div class="social">
			<span class="followers"> <strong>0</strong> <br> 粉丝
			</span> <span class="following"> <strong>0</strong> <br> 关注
			</span>
		</div>

	</div>
	<ul class="side-menu">
		<li id="joinAt.action" class="join active"><a
			href="<%=path%>at/joinAt.action"><i></i><span>已报名活动</span></a></li>
		<li id="organize.action" class="join"><a
			href="<%=path%>at/organize.action"><i></i><span>已组织活动</span></a></li>
		<li id="input.action" class="join"><a
			href="<%=path%>at/input.action"><i></i><span>发布活动</span></a></li>
		<li id="myCollection.action" class="collect"><a
			href="<%=path%>at/myCollection.action"><i></i><span>我的收藏</span></a></li>
	</ul>
</div>
<%
	}
	}
%>


<script type="text/javascript">
	$(function() {
		var url = window.location.href;
		var i = url.lastIndexOf("?");
		if (i > -1) {
			url = url.substring(0, url.lastIndexOf("?"));
		}

		$.each($("#left_side ul li"), function(i, v) {
			var id = $(this).attr("id");
			var re = new RegExp(id + "$");

			if (re.test(url)) {
				$(this).addClass('active');
			} else {
				$(this).removeClass('active');
			}
		});
	});
</script>
