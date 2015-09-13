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
<link href="<%=path%>css/joiner.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>js/activity/partiList.js"></script>
</head>


<body>

	<jsp:include page="../base/joy1joy_header.jsp"></jsp:include>



	<div class="main wrap">

		<!--
    <div class="topic-block"> </div>
    -->


		<div class="theader">
			<h2><a href="<%=path%>at/detail.action?activity.id=${atid}">${atName}</a></h2>
			<div>
				<span class="price-now">总人数<em class="js-fee">${atNum}</em></span> <span
					class="price-now">已报<em class="js-fee">${partiPersons}</em></span>
				<button class="m-btn m-btn-primary" id="export-selected">导出报名列表</button>

			</div>
		</div>

		<form class="tbody" action="#" method="post" target="_blank">
			<input type="hidden" id="atid" name="atid" value="${atid}">
			<table id="table-list">
				<colgroup>
					<col class="col-1">
					<col class="col-2">
					<col class="col-3">
					<col class="col-4" align="center">
					<col class="col-5">
					<col class="col-5">
						<col class="col-5">
					<col class="col-7">
					<col class="col-8">
				</colgroup>
				<tbody>
					<tr class="th">
						<td><input type="checkbox" name="whole" value="0" checked=""
							id="all-selected-or-not"></td>
						<td>全选 / 已选 <em class="js-num" id="already-selected">0</em></td>
						<td>用户名</td>
						<td align="center">报名时间</td>
						<td align="center">报名人数</td>
						<td align="center">联系方式</td>
							<td align="center">身份证号</td>
						<td>金额</td>
						<td>备注</td>
					</tr>
					<s:iterator var="u" value="partiUsers">
						<tr>
							<td><input type="checkbox" name="partiAt"
								id="selected-${u.id}" checked="true" value="${u.id}"></td>
							<td><span><img src="<%=path.substring(0,path.length()-1)%>${u.icon}" alt=""></span></td>
							<td><span>${u.userid}</span></td>
							<td align="center">${u.partiTimeStr}</td>
							<td align="center">${u.partiNum}</td>
						<!--  	<td align="center">${u.mobile}</td> -->
						<td align="center">${u.iphone}</td>
						<td align="center">${u.idcard}</td>
							<td class="price">￥${u.joyFee}</td>
							<td class="center">${remark}</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</form>





	</div>


	<div class="footer-placeholder"></div>


	<jsp:include page="../base/joy1joy_footer.jsp"></jsp:include>
</body>
</html>