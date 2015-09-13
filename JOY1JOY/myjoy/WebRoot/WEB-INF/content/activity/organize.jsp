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
<script type="text/javascript" src="<%=path%>js/activity/organize.js"></script>
</head>

<body>

	<jsp:include page="../base/joy1joy_header.jsp"></jsp:include>
	<div class="main wrap">

		<jsp:include page="../base/joy1joy_left_side.jsp"></jsp:include>

		<!-- content -->
		<div class="content user-join">
			<div class="thead-row">
				<div class="td td-1">
					全部活动<i></i>
				</div>
				<div class="td td-2"></div>
				<div class="td td-3">状态(已报/总)</div>
				<div class="td td-4">操作</div>
			</div>

			<div>

				<ul class="join-list">

				</ul>

				<div id="Pagination" class="pagination"></div>

				<div class="loading" style="visibility: hidden;">还没有数据……</div>




			</div>
		</div>



	</div>

	<input type="hidden" id="totalPages" value="${totalPages}">


	<div class="footer-placeholder"></div>


	<jsp:include page="../base/joy1joy_footer.jsp"></jsp:include>


</body>
</html>