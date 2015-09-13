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
<meta name="keywork" value="动一动,白领,户外活动,学习,周末无聊,周末干嘛,有趣">
<link rel='icon' href="<%=path%>images/favcion.ico" type=‘image/x-ico’ />
<meta name="description" content="网站描述">
<jsp:include page="../base/base_import_jquery1.11.2.jsp"></jsp:include>
<link href="<%=path%>css/style.css" rel="stylesheet">
<link href="<%=path%>css/detail.css" rel="stylesheet">
<link href="<%=path%>css/search.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>js/activity/list-config.js"></script>
<script type="text/javascript" src="<%=path%>js/activity/search.js"></script>
</head>

<body>
	<jsp:include page="../base/joy1joy_header.jsp"></jsp:include>
	<div class="main wrap">

		<!-- JS模块 Banner -->



		<!--
    <div class="topic-block"> </div>
    -->

		<form id="at_search_form" class="wrap-search" action="#" method="post">
			<!-- 模块 m-search -->
			<label class="m-search"> <input class="m-input-text"
				id="at_search_input" type="text" placeholder="搜索" name="keyword"
				value="${keyword}"> <input class="m-search-submit"
				type="submit" value="" id="search-submit">
			</label>
		</form>


		<div class="course-block">

			<div class="content js-course-hot">
				<ul id="list_activities">
				</ul>

				<div id="at-loading" style="display: none;" class="loading"
					style="visibility: visible;">
					<a href="javascript:at.get_more();">加载更多……</a>
				</div>
				<div style="display: none;" id="at-no-more-loading" class="loading"
					style="visibility: visible;">
					<a href="#">没有更多数据了……</a>
				</div>

				<div id="at_nothing" class="status ended">对不起，没有找到您想要的内容……</div>
			</div>
		</div>
	</div>



	<div class="footer-placeholder"></div>


	<jsp:include page="../base/joy1joy_footer.jsp"></jsp:include>
</body>
</html>