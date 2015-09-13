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
<div class="m-footer">
	<div class="wrap">

		<div class="m-footer_inner">
			<ul class="m-footer_inner_list">
				<li><h3>关于我们</h3></li>
				<li><a href="<%=path%>about.action">了解我们</a></li>
				<li><a href="<%=path%>about.action">联系我们</a></li>

			</ul>

			<ul class="m-footer_inner_list">
				<li><h3>商务合作</h3></li>
				<li><a href="<%=path%>copperate.action">发布申请</a></li>
				<li><a href="<%=path%>copperate.action">审核流程</a></li>
			</ul>

			<ul class="m-footer-follow">
				<li><h3>关注我们</h3></li>
				<li><a class="m-footer-weixin" href="javascript:void(0);"
					title="扫我"> <img class="icon"
						src="<%=path%>images/icon-weixin.png" title="关注动一动公众号">腾讯微信
						<img class="tcode" src="<%=path%>images/follow-weixin.jpg"
						title="关注动一动公众号"></a></li>
				<li><a class="m-footer-weibo" href="#" target="_blank"
					title="动一动官方微博"> <img class="icon"
						src="<%=path%>images/icon-weibo.png" title="关注动一动公众号">新浪微博
				</a></li>
			</ul>


		</div>
		<div class="clear"></div>


		<div class="m-footer-company">
			<a class="m-footer-warmup" href="http://joy1joy.com/">JOY1JOY.COM</a>
			<br> Copyright 2015 @JOY1JOY, All right reserved. <a
				href="http://www.miitbeian.gov.cn/" target="_blank">豫ICP备案：15016670号</a>
		</div>
	</div>




	<div class="go-to-top" style="visibility: hidden;">
		<div class="wrap">
			<a href="#"></a>
		</div>
	</div>