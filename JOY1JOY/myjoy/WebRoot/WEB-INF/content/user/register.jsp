<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	if (!"".equals(path)) {
		path = path + "/";
	}else {
		path = "/";
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>动一动</title>
<jsp:include page="../base/base_import_jquery1.11.2.jsp" />
<link rel='icon' href="<%=path%>images/favcion.ico" type=‘image/x-ico’ />
<script type="text/javascript" src="<%=path%>js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=path%>js/user/user.js"></script>
<link href="<%=path%>css/regedit.css" type="text/css" rel="stylesheet" />

</head>

<body>
<jsp:include page="../base/joy1joy_header.jsp" />

<div class="main wrap">

<!--
    <div class="topic-block"> </div>
    -->
<div class="register">
    <h2>注册动一动</h2>

    <form id="register">
    
       <div class="userId">
        <label> 用户名：</label> 
          <input class="m-input-text" id="userId" type="text" name="id" placeholder="用户名"><em class="redstar"> * </em>
      <span class="tips tips-error" id="userIdSpan"><i></i><em></em></span>
       </div>
    
      <div class="password">
      <label>设置密码：</label>
      <input id="onePass" class="m-input-text" type="password" name="password" placeholder="输入6~18位字符"><em class="redstar"> * </em>
      <span class="tips tips-error" id="oneSpan"><i></i><em></em></span>
      </div>
      <div class="repassword">
        <label>重复密码：</label>
        <input id="twoPass" class="m-input-text" type="password" name="repassword" placeholder="输入6~18位字符"><em class="redstar"> * </em>
        <span class="tips tips-error" id="twoSpan"><i></i><em></em></span>
      </div>
      
       <div class="userEmail">
        <label> 邮箱：</label> 
          <input class="m-input-text" id="userEmail" type="text" name="mail" placeholder="邮箱"><em class="redstar"> * </em>
      <span class="tips tips-error" id="userEailSpan"><i></i><em></em></span>
       </div>
      
          
      <div class="phone">
        <label>手机号：</label>
        <input class="m-input-text" id="userName" type="text" name="phone" placeholder="手机号码">
        <span class="tips tips-error" id="phoneSpan"><i></i><em></em></span>
      </div>
      <!--  
	<div class="verify-phone">
        <label>图形验证码：</label>
        <input class="m-input-text" type="text" id="imageCode" name="imageCode" placeholder="图形验证码"/>
       <img id="randCodeImg" onclick="refreshRandCode()" src=''  width="101px" height="36px" style="
    vertical-align: middle";/></img>
         <span class="tips tips-error" id="imageCodeSpan"><i></i><em></em></span>
         
         
           <input type="hidden" id="imageCodeValue" value=""/>
        
      </div>
      	<div class="verify-phone">
      	 <label></label>
       <button class="m-btn m-btn-primary m-btn" id="userCode">发送验证码</button>
        <span class="tips tips-error" id="codeSpan"><i></i><em></em></span>
         </div>
      <div class="verify-phone">
        <label>手机验证码：</label>
        <input class="m-input-text" type="text" id="phoneCode" name="code" placeholder="输入验证码">
      
        
        <input type="hidden" id="codBtn" value=""/>
      </div>
        -->
     

      <div class="agreement">
        <label></label>
        <input id="agree" type="checkbox" checked name="agreement" value="1">
        <span>我已阅读并同意</span>
        <a href="<%=path%>user/protocol.action" target="_blank">《动一动用户条例》</a>
      </div>

      <div class="status">
        <label></label>
        <em></em>
      </div>

      <div>
        <label></label>
        <button class="m-btn m-btn-primary" id="reg_btn" type="button">注册</button>
      </div>
    </form>
  </div>

      <div class="regedit_rig">
      <ul class="reg_login">
          <li>已有动一动账户</li>
            <li><a href="javascript:void(0);" class="flatLogin">立即登录>></a></li>
        </ul>
      <ul class="reg_otherAccount">
      <!--
          <li>使用第三方账户登录</li>
            <li><a href="javascript:void(0);"><img src="<%=path %>images/weibo_ico.jpg" /></a></li>
	    -->
        </ul>
    </div>



</div>
	<div class="footer-placeholder"></div>
	<jsp:include page="../base/joy1joy_footer.jsp" />
</body>
</html>
