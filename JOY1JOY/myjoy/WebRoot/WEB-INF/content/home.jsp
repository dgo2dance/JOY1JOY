<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	if (!"".equals(path)) {
		path = path + "/";
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
<meta name="description" content="网站描述">
<link rel='icon' href="<%=path%>images/favcion.ico" type=‘image/x-ico’ />
<link href="<%=path%>css/home/style.css" type="text/css"
	rel="stylesheet" media="all" />

<link href="<%=path%>css/common.css" rel="stylesheet">
<link href="<%=path%>css/style.css" rel="stylesheet">


<jsp:include page="base/base_import_jquery1.11.2.jsp" />
<script type="text/javascript"
	src="<%=path%>js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="<%=path%>js/home/config.js"></script>
<script type="text/javascript" src="<%=path%>js/activity/list-config.js"></script>
<script type="text/javascript" src="<%=path%>js/home/define.js"></script>
<script type="text/javascript" src="<%=path%>js/home/home.js"></script>
</head>

<body>
	<jsp:include page="base/joy1joy_header.jsp"></jsp:include>

	<!-- JS模块 Banner -->
	<div class="wrap-m-banner">
		<!--
<div class="m-banner">
  <div class="m-banner-content">
    <ul style="width: 9580px; left: -5748px;">
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
      <li><a href="#"><img src="./images/banner02.jpg"></a></li>
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
      <li><a href="#"><img src="./images/banner01.jpg"></a></li>
    </ul>
  </div>
  
  <div class="m-banner-index">
    <span class=""></span>
    <span class=""></span>
    <span class="active"></span>
    <span class=""></span>
    <span class=""></span>
  </div>
  
  <div class="m-banner-btn-l"></div>
  <div class="m-banner-btn-r"></div>
</div>
-->



		<div class="banner-box" style="z-index: 98;">
			<div class="bd">
				<ul>
					<li style="background: #acc9d9;">
						<div class="m-width">
							<a href="javascript:void(0);"><img src="images/img1.jpg" /></a>
						</div>
					</li>
	            
					<li style="background: #e0a74e;">
						<div class="m-width">
							<a href="javascript:void(0);"><img src="images/img3.jpg" /></a>
						</div>
					</li>
					
								
            <li style="background:#3167c1">
                <div class="m-width">
                <a href="javascript:void(0);"><img src="images/img2.jpg" /></a>
                </div>
            </li>
	    
					<!--
            <li style="background:#FDFDF5">
                <div class="m-width">
                <a href="javascript:void(0);"><img src="images/img4.jpg" /></a>
                </div>
            </li>  
         -->
				</ul>
			</div>
			<div class="banner-btn">
				<a class="prev" href="javascript:void(0);"></a> <a class="next"
					href="javascript:void(0);"></a>
				<div class="hd">
					<ul></ul>
				</div>
			</div>
		</div>

	</div>

	<div class="main wrap">

		<div class="topic-block"></div>

		<div class="course-block">
			<h2>
				最新活动 <a href="<%=path%>at/atlist.action">查看全部</a>
			</h2>
			<div class="content js-course-hot">
				<ul id="home_activities">

				</ul>
			</div>
		</div>
	</div>

    <!-- 
	<div class="m-article">

		<div class="footer_top">

	 	<img src="<%=path%>images/footer_top.jpg">  
		</div>
		 
		<div class="footer_mid">
			<div class="footer_inner">

				 <ul class="f_list1">
              <li><p>前往论坛讨论区</p></li>
                <li><img src="images/t_code.jpg" /></li>
                <li><a href="#"> >>前往论坛讨论区 </a></li>
            </ul>
          
				<ul class="f_inner_list" id="home-knowledge">
					<li><h3>
							<a
								href="<%=path%>notice/findNoticeList.action?noticeType=knowledge">户外知识</a>
						</h3></li>

				</ul>
				<ul class="f_inner_list" id="home-activity">
					<li><h3>
							<a
								href="<%=path%>notice/findNoticeList.action?noticeType=activity">活动公告</a>
						</h3></li>
				</ul>

				<ul class="f_inner_list" style="margin-right: 0" id="home-join">
					<li><h3>报名动态</h3></li>

				</ul>
				<div class="clear"></div>
				
			</div>
		</div>
		
	</div>
 -->

	<div class="footer-placeholder"></div>

	<jsp:include page="base/joy1joy_footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>

	<script>
		$(document).ready(function() {

			$(".prev,.next").hover(function() {
				$(this).stop(true, false).fadeTo("show", 0.9);
			}, function() {
				$(this).stop(true, false).fadeTo("show", 0.4);
			});

			$(".banner-box").hover(function() {
				$(".prev,.next").fadeIn(200)
			}, function() {
				$(".prev,.next").fadeOut(200);
			});

			$(".banner-box").slide({
				titCell : ".hd ul",
				mainCell : ".bd ul",
				effect : "fold",
				interTime : 3500,
				delayTime : 500,
				autoPlay : true,
				autoPage : true,
				trigger : "click"
			});

		});
	</script>
</body>
</html>