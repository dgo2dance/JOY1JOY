<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<link href="<%=path%>/css/publishN.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path%>/js/notice/noticeAdd.js"></script>
<script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
</head>

<body>
	<jsp:include page="../base/joy1joy_header.jsp" />
	
	<div class="main wrap">
 <jsp:include page="../base/joy1joy_left_side.jsp"></jsp:include>

      <!-- content -->
<div class="content">
  
  <form action="<%=path%>/notice/saveNotice.action" name="noticeForm" method="post">
      <div class="row">
        <label for="name">公告标题：</label>
        <input type="text" name="noticeTitle" id="noticeTitle">
      </div>

      <div class="row">
        <label for="signature">公告类别：</label>
       
        <select name="noticeType" id="noticeType">
             	<s:iterator value="#request.dcits" id="entity">
             		<option value="${entity.dkey}">${entity.dvalue}</option>
             	</s:iterator>
        </select>
      </div>
      <div class="row">
        <label for="signature">公告内容：</label>
       <textarea style="height:300px;width:95%" name="noticeContent" id="noticeContent"></textarea>
        <script type="text/javascript">
        CKEDITOR.replace('noticeContent',
	     {

	     });
	   </script>
      </div>


  <div class="row-btn">
    <!--    <span class="status">数据提交中，请稍等……</span>  -->
        <br><br>
        <button class="m-btn m-btn-primary" type="button" id="pub_btn">保 存</button>
      </div>
    </form>

</div>
  </div>

	<div class="footer-placeholder"></div>
	<jsp:include page="../base/joy1joy_footer.jsp" />
</body>
</html>
