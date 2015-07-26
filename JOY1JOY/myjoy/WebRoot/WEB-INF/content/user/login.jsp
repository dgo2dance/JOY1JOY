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
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>首页 - 动一动</title>
<meta name="keywork" value="动一动,白领,户外活动,学习,周末无聊,周末干嘛,有趣">
<meta name="description" content="网站描述">
<jsp:include page="../base/base_import_jquery1.11.2.jsp" />
<link href="<%=path%>css/style.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>css/common.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>css/login.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=path%>js/user/user.js"></script>
</head>


<body>


<jsp:include page="../base/joy1joy_header.jsp" />

<div class="main wrap">

<!--
    <div class="topic-block"> </div>
    -->


<div class="log_c">
  <div class="log_pic"><img src="<%=path%>images/log_pic.jpg" /></div>  
    <div class="log_area">
    <h3>登录动一动</h3>
    <ul>
          <li>   <label>用户名：</label><input id="loginName" class="m-input-text logininput" type="text" name="user" placeholder="手机或者昵称"></li>
            <li> <label>密码：</label><input id="loginPass" class="m-input-text logininput" type="password" name="psw" placeholder="密码"></li>
        </ul>

        <div class="log_box"><a href="javascript:void(0);" class="forget">忘记密码?</a><span><input type="checkbox" id="rememberMe"/></span>记住我</div>
        <a href="javascript:void(0);" class="log_btn m-btn m-btn-primary" id="log_btn">登录</a>
        
        <div class="tip_reg">
          <p>还没有动一动账户</p>
            <p>马上注册，发现趣味的活动！<a href="javascript:void(0);"  class="flatReg">立即注册>></a></p>
        </div>
    
    
    </div>
    <div class="clear"></div>
</div>



</div>


<div class="footer-placeholder"></div>


 <jsp:include page="../base/joy1joy_footer.jsp" />
</body></html>