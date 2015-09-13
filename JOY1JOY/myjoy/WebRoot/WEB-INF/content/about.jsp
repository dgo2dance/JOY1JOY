<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	if (!"".equals(path)) {
		path = path + "/";
	}else {
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
<link rel='icon' href="<%=path%>images/favcion.ico" type=‘image/x-ico’ />
<meta name="description" content="网站描述">
<jsp:include page="./base/base_import_jquery1.11.2.jsp" />
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
						<li><a href="#tabs1" class="selected">了解我们</a></li>
						<li><a href="#tabs2">联系我们</a></li>
					</ul>
					<div class="clear"></div>
					<div id="tabs1">
						<div class="n_list">
							<h3>了解我们</h3>
							<ul>
								<li>
									<!-- <a href="#">我们是谁</a> -->
									<p class="blank">你去或不去，山都在那里。</p>
									<p class="blank">我们热爱登山，我们热爱户外，我们热爱有趣的活动。</p>
									<p class="blank">我们正在试图，以登山为支点，让人们的生活更加随意有趣。</p>
									<p class="blank">挑战、探索、环保、AA、自由、交友、合作、交流、分享。</p>
									<p class="blank">让美好发现美好，让美好遇见美好。</p>
									<p class="blank">
										Just <span style="font-size: 20px">动一动</span>,Then <span
											style="font-size: 18px">Joy1Joy</span>。
									</p> </br> </br> <!--
                    <a href="#">我们能给您带来什么</a>
                    <p class="blank">2015嵩山爬山活动路线风景说明</p>
              
                    
                    </br>
                    </br>
             
                    <a href="#">需要我们一起做什么</a>
                    <p class="blank">2015嵩山爬山活动路线风景说明</p>
                    -->
								</li>

							</ul>

						</div>

					</div>
					<div id="tabs2">
						<div class="n_list">
							<h3>联系我们</h3>
							<ul>
								<li>
									<!-- <a href="#">你可以通过下面</a>-->

									<p class="blank">动一动，郑州</p>
									<p class="blank">官方邮箱:hellojoy1joy@163.com</p>
									<p class="blank">官方电话:</p>
									<p class="blank">官方微信:</p>
									<p class="blank">官方微博:</p>
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