/**

 * @author boyd
 * @version 1.0
 * @description 公告
 */

$(function(){
	$("#pubNotice").click(function(){
		window.location.href=joy.getContextPath() +"/notice/jumpSaveNotice.action";
	});
	//发布公告
	$("#pub_btn").click(function(){
		
		var noticeTitle=$("#noticeTitle").val();
		var noticeType=$("#noticeType").val();
		var noticeContent=CKEDITOR.instances.noticeContent.getData();
		if(noticeTitle=="")
		{
			joy.alert("公告标题不能为空");
			return;
		}
		else if(noticeType=="")
		{
			joy.alert("公告类别不能为空");
			return;
		}
		else if(noticeContent=="")
		{
			joy.alert("公告内容不能为空");
			return;
		}else
		{
			document.noticeForm.submit();
		}
		
	});
});
