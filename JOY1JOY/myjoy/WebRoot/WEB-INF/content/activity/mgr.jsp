<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>首页 - 动一动</title>
<meta name="keywork" value="动一动，户外活动，学习，周末无聊，周末干嘛，有趣">
<link rel='icon' href="<%=path%>images/favcion.ico" type=‘image/x-ico’ />
<meta name="description" content="网站描述">
<jsp:include page="../base/base_import_jquery1.11.2.jsp"></jsp:include>
<link href="<%=path%>css/user.css" rel="stylesheet">
<link href="<%=path%>css/footer.css" rel="stylesheet">
<link href="<%=path%>css/pagination.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>js/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=path%>js/activity/mgr.js"></script>
</head>

<body>


	<jsp:include page="../base/joy1joy_header.jsp"></jsp:include>

	<div class="main wrap">
		<jsp:include page="../base/joy1joy_left_side.jsp"></jsp:include>
		<!-- content -->
		<div class="content user-join">
			<div class="thead-row">
				<div class="td td-1">
					全部活动 &nbsp; &nbsp; &nbsp;<select id="at_status"
						class="m-select-status-text" name="status">
						<option value="-100">全部</option>
						<option value="-1">草稿</option>
						<option value="0">待审核</option>
						<option value="-2">审核未通过</option>
						<option value="1">报名中</option>
						<option value="2">名额已满</option>
						<option value="3">已结束</option>
						<option value="4">关闭</option>
					</select><i></i>
				</div>
				<div class="td td-2"></div>
				<div class="td td-3">状态(已报/总)</div>
				<div class="td td-4">操作</div>
			</div>

			<div>

				<ul class="join-list">

					<li class="join-row">
						<div class="td td-1">
							<a href="#" class="title">嵩山爬山活动说明</a>
							<p class="gray">2015.04.09</p>

						</div>
						<div class="td td-2"></div>
						<div class="td td-3">报名中（10/20</div>
						<div class="td td-4">
							<a href="#">审核通过</a> <a href="#">审核未通过</a> <a href="#">成员管理</a>

						</div>

					</li>
					<li class="join-row">
						<div class="td td-1">
							<a href="#" class="title">嵩山爬山活动说明</a>
							<p class="gray">2015.04.09</p>

						</div>
						<div class="td td-2"></div>
						<div class="td td-3">报名中（10/20</div>
						<div class="td td-4">
							<a href="#">编辑</a> <a href="#">成员管理</a>

						</div>

					</li>

					<li class="join-row">
						<div class="td td-1">
							<a href="#" class="title">嵩山爬山活动说明</a>
							<p class="gray">2015.04.09</p>

						</div>
						<div class="td td-2"></div>
						<div class="td td-3">报名中（10/20</div>
						<div class="td td-4">
							<a href="#">编辑</a> <a href="#">成员管理</a>


						</div>

					</li>

					<li class="join-row">
						<div class="td td-1">
							<a href="#" class="title">嵩山爬山活动说明</a>
							<p class="gray">2015.04.09</p>

						</div>
						<div class="td td-2"></div>
						<div class="td td-3">报名中（10/20</div>
						<div class="td td-4">
							<a href="#">编辑</a> <a href="#">成员管理</a>

						</div>

					</li>




				</ul>

		  <div id="Pagination" class="pagination"></div>

				<div class="loading" style="visibility: hidden;">还没有数据……</div>




			</div>
		</div>



	</div>

	<div class="footer-placeholder"></div>

	<jsp:include page="../base/joy1joy_footer.jsp"></jsp:include>

</body>
</html>