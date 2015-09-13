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
<meta name="description" content="网站描述">
<link rel='icon' href="<%=path%>images/favcion.ico" type=‘image/x-ico’ />
<jsp:include page="../base/base_import_jquery1.11.2.jsp"></jsp:include>
<link href="<%=path%>css/style.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>js/activity/detail.js"></script>
</head>

<body>

	<jsp:include page="../base/joy1joy_header.jsp"></jsp:include>

	<div class="main wrap">
		<div class="con_inner">
		<!-- 	<li><h3>
							<s:property value="activity.name" />
						</h3></li>
						 -->
			<div class="con_left">
				<div class="box01">
					<img style="width:592px;" 
						src="<%=path.substring(0, path.length() - 1)%><s:property value='activity.poster' />" />
				</div>
				<div class="box02">
					<h3 class="title">活动介绍</h3>
					<div class="d_con" style="word-wrap: break-word;">
						${activity.description}</div>
					<h3 class="title">注意事项</h3>
					<div class="d_con" style="word-wrap: break-word;">
						${activity.attention}</div>
					<h3 class="title">报名须知</h3>
					<div class="d_con" style="word-wrap: break-word;">
						${activity.caution}</div>
					<a href="#" class="go_forum">>>前往论坛讨论区</a>
				</div>
			</div>
			<div class="con_rig">
				<ul class="c_list">
				
					<li><h3>
							<s:property value="activity.name" />
						</h3></li>
						
						
					<li>时间：<s:date name="activity.stime" format="yyyy-MM-dd" />~<s:date
							name="activity.stime" format="yyyy-MM-dd" /></li>
					<li>地点：<s:property value="activity.addressName" /></li>
					<li>活动人数：<s:property value="activity.pnum" /></li>
					<li>费用：￥<s:property value="activity.joyFee" /></li>
					<li>组织者：<s:property value="activity.cuserName" /></li>
					<li><label>报名人数：</label><input id="at_joiin_pnum" type="text" class="count"
						value="1" /></li>
							<li><label>手机号：</label><input id="at_joiin_iphone" type="text" class="countremark"
						value="" placeholder="组织者通过手机号联系您" style="font-size:10px;"/></li>
							<li><label>身份证号：</label><input id="at_joiin_idcard" type="text" class="countremark"
						value="" placeholder="便于购买保险" style="font-size:10px;"/></li>
					<li><label>备注：</label><input id="at_joiin_remark" type="text" class="countremark" 
						placeholder="可填写上车地点等备注" style="font-size:10px;"/></li>
				</ul>
				<s:if test="activity.status==1">
					<a href="javascript:opt_handler(0,${activity.id});"
						class="enroll  m-btn m-btn-primary">报名参加</a>
				</s:if>
				<s:if test="activity.status>0">
					<a href="javascript:opt_handler(1,${activity.id});"
						id="at_join_group" class="join m-btn m-btn-primary">加入群组</a>
					<div class="share" id="at_qr_area" style="display: none;">
						<br /> <br /> <input type="hidden" id="at_gqr"
							value="${activity.gqr}">
						<!-- <div style="color: red;">扫描以下二维码加入群组:</div> -->
						<img alt=""
							src="<%=path.substring(0,path.length()-1)%>${activity.gqr}"
							style="width: 225px;">
					</div>
					<a href="javascript:opt_handler(2,${activity.id});" id="at_collect"
						class="enroll  m-btn m-btn-primary">收藏</a>
					<div class="share">

						<!-- JiaThis Button BEGIN -->
						<div class="jiathis_style">
							<span class="font1">分享到：</span> <a class="jiathis_button_qzone"></a>
							<a class="jiathis_button_tsina"></a> <a
								class="jiathis_button_tqq"></a> <a class="jiathis_button_weixin"></a>
							<a class="jiathis_button_renren"></a> <a
								href="http://www.jiathis.com/share"
								class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
							<a class="jiathis_counter_style"></a>
						</div>
						<script type="text/javascript">
							var title = '<s:property value="activity.name" escape="false"/>';
							var jiathis_config = {
								title : "动一动 |"+title
							};
						</script>
						<script type="text/javascript"
							src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
						<!-- JiaThis Button END -->
					</div>

				</s:if>
			</div>
			<div class="clear"></div>
		</div>

	</div>


	<div class="footer-placeholder"></div>
	<jsp:include page="../base/joy1joy_footer.jsp"></jsp:include>
</body>
</html>