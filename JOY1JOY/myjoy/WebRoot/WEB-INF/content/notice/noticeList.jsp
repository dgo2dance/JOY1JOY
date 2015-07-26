<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	//System.out.println(path + "************");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>动一动</title>
<jsp:include page="../base/base_import_jquery1.11.2.jsp" />
<link href="<%=path%>/css/style.css" rel="stylesheet"/>
<link href="<%=path%>/css/notice.css" rel="stylesheet"/>
<link rel="stylesheet" href="<%=path%>/css/pagination.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/notice/noticeList.js"></script>

</head>

<body>
	<input type="hidden" id="activityPageNum" value="${activityPageNum}"/>
	<input type="hidden" id="knowledgePageNum" value="${knowledgePageNum}"/>
	<input type="hidden" id="noticeType" value="${noticeType}"/>
	<jsp:include page="../base/joy1joy_header.jsp" />
	
	<div class="main wrap">

<!--
    <div class="topic-block"> </div>
    -->
<div class="notice_bar">
    <div class="notice_inner">
    <div id="usual2" class="usual"> 
      <ul class="tab1"> 
        <li><a href="#tabs1" id="knowledge">户外知识</a></li> 
        <li><a href="#tabs2"  id="activity">活动公告</a></li> 
      </ul>
      <div class="clear"></div>
      <div id="tabs1">
        <div class="n_list">
            <h3>户外知识</h3>
             <ul id="knowledgeRow">
          
      		</ul>
            <div id="knowledgePage" class="pagination"></div>
        </div>
      
      </div> 
      <div id="tabs2">
        <div class="n_list">
            <h3>活动公告</h3>
            <ul id="noticeRow">
                
            </ul>
            <div id="Pagination" class="pagination"></div>
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
<jsp:include page="../base/joy1joy_footer.jsp" />
</body>
</html>
