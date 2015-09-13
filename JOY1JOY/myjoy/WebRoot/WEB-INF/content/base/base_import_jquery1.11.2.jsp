<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<input id="joy1_context_path" type="hidden"
	value="<%=path.substring(0, path.length() - 1)%>" />
<link href="<%=path%>css/common.css" type="text/css" rel="stylesheet"
	media="all" />


<script type="text/javascript">
<!--
	var contextPath = document.getElementById("joy1_context_path").value;
//-->
</script>
<script type="text/javascript" src="<%=path%>js/jquery-1.11.2.min.js"></script>

<link href="<%=path%>css/sobox/sobox_style.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=path%>js/jquery.sobox.min.js"></script>

<script type="text/javascript" src="<%=path%>js/is.js"></script>
<script type="text/javascript" src="<%=path%>js/base/config.js"></script>
<script type="text/javascript" src="<%=path%>js/base/define.js"></script>

