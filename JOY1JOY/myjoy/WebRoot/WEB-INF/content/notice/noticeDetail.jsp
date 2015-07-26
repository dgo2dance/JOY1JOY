<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	//System.out.println(path + "************");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>动一动</title>
<jsp:include page="../base/base_import_jquery1.11.2.jsp" />
<link href="<%=path%>/css/style.css" rel="stylesheet"/>
<link href="<%=path%>/css/notice.css" rel="stylesheet"/>
<style type="text/css">
.m-input-text {
	display:block;
	padding:7px 0;
	width:100%;
	height:35px;
	border:1px solid #e7e5e4;
	font-size:14px;
	line-height:20px;
	text-indent:10px;
	background-color:#fff;
	border-radius:4px;
	color:#737069
}
</style>
<script type="text/javascript">
$(function(){
	var paths="<%=path%>";
	var types="${tnotice.type}";
	if(types=="activity")
	{
		$("#hType").html("活动公告");
		$("#activity").addClass("selected");
	}
	if(types=="knowledge")
	{
		$("#hType").html("户外知识");
		$("#knowledge").addClass("selected");
	}
	$("#knowledge").click(function(){
		window.location.href=paths+"/notice/findNoticeList.action?noticeType=knowledge";
	});
	$("#activity").click(function(){
		window.location.href=paths+"/notice/findNoticeList.action?noticeType=activity";
	});
})
</script>

</head>

<body>
	<jsp:include page="../base/joy1joy_header.jsp" />
	
	<div class="main wrap">

<!--
    <div class="topic-block"> </div>
    -->
<div class="notice_bar">
    <div class="notice_inner">
    <div id="usual2" class="usual"> 
      <ul class="tab1"> 
        <li><a href="javascript:void(0);" id="knowledge">户外知识</a></li> 
        <li><a href="javascript:void(0);"  id="activity" >活动公告</a></li> 
      </ul>
      <div class="clear"></div>
      <div id="tabs1">
        <div class="n_list">
            <h3 id="hType"></h3>
            <ul>
                <li>
                    <a href="javascript:void(0);">${tnotice.title}</a>
                    <p class="gray"><s:date name="#tnotice.cdatetime" format="yyyy.MM.dd"/></p>
                    <p class="blank">${tnotice.content}</p>
                </li>
                
            </ul>
        </div>
      
      </div> 
    
      </div> 
    </div> 
 
  </div>

  </div>
<div class="footer-placeholder"></div>
	<jsp:include page="../base/joy1joy_footer.jsp" />
</body>
</html>
