<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	if (!"".equals(path)) {
		path = path + "/";
	} else {
		path = "/";
	}
	//System.out.println(path + "************");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>首页 - 动一动</title>
<meta name="keywork" value="动一动,白领,户外活动,学习,周末无聊,周末干嘛,有趣">
<meta name="description" content="网站描述">
<link href="<%=path%>css/common.css" rel="stylesheet">
<link href="<%=path%>css/style.css" rel="stylesheet">
<link href="<%=path%>css/notice.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="./base/joy1joy_header.jsp" />



	<div class="main wrap">

		<!--
    <div class="topic-block"> </div>
    -->
		<div class="notice_bar">
			<div class="notice_inner">
				<div id="usual2" class="usual">
					<ul class="tab1">
						<li><a href="#tabs1" class="selected">发布活动</a></li>
						<li><a href="#tabs2">活动审核</a></li>
					</ul>
					<div class="clear"></div>
					<div id="tabs1">
						<div class="n_list">
							<h3>发布活动</h3>
							<ul>
								<li>
									<p class="blank">要想发布活动，需要有发布活动权限，获得发布活动权限后，在个人中心，即可显示发布活动功能菜单。点击菜单，即进入活动发布。</p>
									<h4>如何获取发布权限</h4>
									<p class="blank">1.对自己组织的活动类型（户外相关类）、自己的相关信息说明（电话、地点、姓名、账号）发送至邮箱hellojoy1joy@163.com。</p>
									<p class="blank">2.我们会3个工作日内依次与你联系进行核实</p>
									<p class="blank">3.信息确认属实后，我们会在后台给你开通发布活动的权限。</p>
									<p class="blank">4.你即可在动一动发布组织自己的活动。</p>


								</li>

							</ul>

						</div>

					</div>
					<div id="tabs2">
						<div class="n_list">
							<h3>活动审核</h3>
							<ul>
								<li>
									<h4>为什么要审核</h4>
									<p class="blank">为了确保活动的质量、以及信息的有效性，给用户带来可以得到保障的体验，我们会对发布的活动在后台逐一审核，审核通过后，方可前台进行显示。</p>
									<h4>如何审核</h4>
									<p class="blank">您发布活动信息后，我们会在一个工作日之内，对你活动的有效性进行审核确定。</p>
									<p class="blank">如符合要求，既会将活动置于审核通过状态，用户即可在网站上查看活动进行报名</p>

									<p class="blank">
										如不符合要求，我们会将活动置于审核未通过状态，并说明原因。您可以按照相关建议调整后重新提交接受审核。</p>
								</li>

							</ul>

						</div>
					</div>
				</div>
				<script type="text/javascript">
					$("#usual2 ul").idTabs("tabs2");
				</script>
			</div>

		</div>



	</div>


	<div class="footer-placeholder"></div>
	<script type="text/javascript" src="<%=path%>js/jquery-1.2.3.pack.js"></script>
	<script type="text/javascript" src="<%=path%>js/jquery.idTabs.min.js"></script>
	<script type="text/javascript">
		$("#usual2 ul").idTabs("tabs2");
	</script>
	<jsp:include page="./base/joy1joy_footer.jsp" />
</body>
</html>