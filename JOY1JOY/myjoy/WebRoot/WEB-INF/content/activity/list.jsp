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
<style type="text/css">
.itemSelected {
	background: #666;
	color: #fff;
	border-radius: 2px;
}
</style>
<script type="text/javascript" src="<%=path%>js/activity/list-config.js"></script>
<script type="text/javascript" src="<%=path%>js/activity/list-define.js"></script>
<script type="text/javascript" src="<%=path%>js/activity/list.js"></script>
</head>

<body>
	<jsp:include page="../base/joy1joy_header.jsp"></jsp:include>
	<div class="main wrap">
		<div class="sort_list">
			<div class="sort_inner">
				<dl class="s_list" id="at_dtype">
					<dt>分类：</dt>
					<dd>
						<a href="#" data-value="all" class="itemSelected">全部</a>
					</dd>
					<s:iterator var="t" value="dtypes">
						<dd>
							<a href="#" data-value="${t.dkey}">${t.dvalue}</a>
						</dd>
					</s:iterator>
				</dl>
				<dl class="s_list" id="at_time">
					<dt>时间：</dt>
					<dd>
						<a href="#" data-value="0" class="itemSelected">全部</a>
					</dd>
					<dd>
						<a href="#" data-value="7">周六</a>
					</dd>
					<dd>
						<a href="#" data-value="1">周日</a>
					</dd>
					<dd>
						<a href="#" data-value="-1">其他</a>
					</dd>
				</dl>
				<dl class="s_list" id="at_fee">
					<dt>费用：</dt>
					<dd>
						<a href="#" data-value="0" class="itemSelected">全部</a>
					</dd>
					<dd>
						<a href="#" data-value="1">免费</a>
					</dd>
					<dd>
						<a href="#" data-value="0-50">0-50</a>
					</dd>
					<dd>
						<a href="#" data-value="50-100">50-100</a>
					</dd>
					<dd>
						<a href="#" data-value="-1">其他</a>
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
		</div>

		<div class="course-block">

			<div class="content js-course-hot">
				<ul id="list_activities">

				</ul>

				<div id="at-loading" class="loading" style="visibility: visible;">
					<a href="javascript:at.get_more();">加载更多……</a>
				</div>
				<div style="display: none;" id="at-no-more-loading" class="loading"
					style="visibility: visible;">没有更多数据了……</div>
			</div>
		</div>
	</div>



	<div class="footer-placeholder"></div>
	<jsp:include page="../base/joy1joy_footer.jsp"></jsp:include>
</body>
</html>