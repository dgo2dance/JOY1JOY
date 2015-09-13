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
<meta name="keywork" value="动一动，白领，户外活动，学习，周末无聊，周末干嘛,有趣">
<link rel='icon' href="<%=path%>images/favcion.ico" type=‘image/x-ico’ />
<meta name="description" content="网站描述">
<jsp:include page="../base/base_import_jquery1.11.2.jsp"></jsp:include>
<link href="<%=path%>css/publishP.css" rel="stylesheet">
<link href="<%=path%>css/footer.css" rel="stylesheet">


<script type="text/javascript" src="<%=path%>js/jquery.Jcrop.min.js"></script>
<link href="<%=path%>css/jquery.Jcrop.min.css">

<script type="text/javascript" src="<%=path%>My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=path%>js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=path%>js/activity/input.js"></script>
<script type="text/javascript" src="<%=path%>js/activity/input-config.js"></script>
<script type="text/javascript" src="<%=path%>js/activity/input-define.js"></script>

</head>

<body>
	<jsp:include page="../base/joy1joy_header.jsp"></jsp:include>

	<div class="main wrap">
		<jsp:include page="../base/joy1joy_left_side.jsp"></jsp:include>
		<!-- content -->
		<div class="content">

			<div class="party">
				<input type="hidden" id="at_optType" value="${editOpt}">

				<form id="activity_form" action="" method="post">
					<input type="hidden" name="activity.id" id="at_id"
						value="${activity.id}"> <input type="hidden"
						name="activity.status" id="at_status" value="${activity.status}">
					<div>
						<label>活动标题：</label> <input class="m-input-text" type="text"
							name="activity.name" value="${activity.name}" id="at_name"
							placeholder="活动标题"> <span class="tips tips-error"><i></i><em
							id="at_name_tip"></em></span>
					</div>
					<input type="hidden" name="activity.status" id="at_status"
						value="-1">
					<div>
						<label>活动地点：</label> <select id="at_address" class="m-select-text"
							name="faddress" id="faddress">
							<s:iterator value="address" var="a">
								<option value="${a.dkey}">${a.dvalue}</option>
							</s:iterator>

						</select> <select id="type" id="saddress" name="activity.address"
							value="${activity.address}" class="m-select-text" name="type">
							<option value="zhengzhou">郑州市</option>
						</select> <span class="tips tips-error"><i></i><em
							id="at_address_tip"></em></span>
					</div>

					<div>
						<label>活动时间：</label> <input class="m-input-time-text" type="text"
							name="activity.stime" id="at_stime" placeholder="开始时间"
							onClick="WdatePicker()"
							value="<s:date name='activity.stime' format='yyyy-MM-dd' />">
						<input class="m-input-time-text" type="text" id="at_etime"
							name="activity.etime" placeholder="结束时间"
							value='<s:date name="activity.etime" format="yyyy-MM-dd" />'
							onClick="WdatePicker()"> <span class="tips tips-error"><i></i><em
							id="at_time_tip"></em></span>
					</div>

					<div>
						<label>活动类别：</label> <input id="at_type" type="hidden"
							value="${activity.type}" name="activity.type">
						<s:iterator var="t" value="dtypes">
							<input type="checkbox" name="type" class="checkfee"
								value="${t.dkey}">${t.dvalue}</input> &nbsp &nbsp
						</s:iterator>
						<span class="tips tips-error"><i></i><em id="at_type_tip"></em></span>
					</div>

					<div>
						<label>活动人数：</label> <input class="m-input-text" type="text"
							name="activity.pnum" value="${activity.pnum}" id="at_pnum"
							placeholder="活动人数"> <span class="tips tips-error"><i></i><em
							id="pnum_tip"></em></span>
					</div>


					<div>
						<label>活动费用：</label>
						<s:if test="activity.joyFee>0">
							<input type="radio" name="at_fee" class="checkfee" value="0">免费&nbsp &nbsp <input
								type="radio" name="at_fee" class="checkfee" value="1"
								checked="checked">收费
							</s:if>
						<s:else>
							<input type="radio" name="at_fee" class="checkfee" value="0"
								checked="checked">免费&nbsp &nbsp <input type="radio"
								name="at_fee" class="checkfee" value="1">收费
							</s:else>
						&nbsp &nbsp <input class="m-input-time-text" type="text"
							name="activity.joyFee" value="${activity.joyFee}" id="at_joyFee"
							placeholder="活动费用" value="0">&nbsp;单位:元<span
							class="tips tips-error"><i></i><em id="at_joyFee_tip"></em></span>
					</div>

               
					
					<div class="uploadImg">
						<label style="float:left;">上传海报1：</label> 
						
					  <div class="viewImg" style="float:left;width:192px;height:121px;background:#eee;margin-right:15px;padding:10px;">
								<label><span>&nbsp;&nbsp;</span></label> <img id="poster-view-img"
							style="cursor: pointer;width:162px;height:112px;"
							
							src="<%=path.substring(0,path.length()-1)%>${activity.poster}"
							alt="" /> <span
							class="tips tips-error"><i></i><em></em></span>
					 </div>
						<div class="upload">
						<input type="file" id="poster" name="upload" style="border: none;" />
						<input type="hidden" id="at_poster" value="${activity.poster}" name="activity.poster"> 
						</p>
						<a id="poster-upload" href="javascript:void(0);" style="color: red;">点击上传</a>
						</p>
						<label>图片大小比例建议为：640*450</label>
							<span class="tips tips-error"><i></i><em id="at_poster_tip"></em></span>
						</div>
					</div>
				
					<div>
						<label>活动介绍：</label>
						<textarea class="ckeditor" name="description" id="at_description"
							name="activity.description"
							style="height: 300px; width: 95%; border: 1px solid #ddd">${activity.description}</textarea>
						<span class="tips tips-error"><i></i><em
							id="at_description_tip"></em></span>
					</div>

					<div>
						<label>注意事项：</label>
						<textarea class="ckeditor"
							style="height: 300px; width: 95%; border: 1px solid #ddd"
							name="activity.attention" id="at_attention">${activity.attention}</textarea>
						<span class="tips tips-error"><i></i><em
							id="at_attention_tip"></em></span>
					</div>

					<div>
						<label>报名须知：</label>
						<textarea class="ckeditor"
							style="height: 300px; width: 95%; border: 1px solid #ddd"
							name="activity.caution" id="at_caution">${activity.caution}</textarea>
						<span class="tips tips-error"><i></i><em
							id="at_caution_tip"></em></span>
					</div>
                    <!--  
					<div>
						<label>是否短信确认报名：</label>
						<s:if test="activity.autoSMS==1">
							<input type="radio" name="activity.autoSMS" class="checkfee"
								value="1" checked="checked">是</input>
						&nbsp &nbsp <input type="radio" name="activity.autoSMS"
								class="checkfee" value="0">否</input>
						</s:if>
						<s:else>
							<input type="radio" name="activity.autoSMS" class="checkfee"
								value="1">是</input>
						&nbsp &nbsp <input type="radio" name="activity.autoSMS"
								class="checkfee" value="0" checked="checked">否</input>
						</s:else>
						&nbsp &nbsp <span class="tips tips-error"><i></i><em
							id="smsconfirm_tip"></em></span>
					</div>

					<div>
						<label>短信确认内容：</label>
						<textarea style="height: 60px; width: 95%; border: 1px solid #ddd"
							name="activity.smsContent" id="at_smsContent">${activity.smsContent}</textarea>
						<span class="tips tips-error"><i></i><em
							id="at_smsContent_tip"></em></span>
					</div>
                    -->
					<div>
						<label>是否上传微信群组图片：</label>
						<s:if test="activity.gqr!=null && activity.gqr!=''">
							<input type="radio" name="uploadWeiXin" class="checkfee"
								value="1" checked="checked">是</input> &nbsp &nbsp <input
								type="radio" name="uploadWeiXin" class="checkfee" value="0">否</input>
						</s:if>
						<s:else>
							<input type="radio" name="uploadWeiXin" class="checkfee"
								value="1">是</input> &nbsp &nbsp <input type="radio"
								name="uploadWeiXin" class="checkfee" checked="checked" value="0">否</input>
						</s:else>
						&nbsp &nbsp <span class="tips tips-error"><i></i><em></em></span>
					</div>

					<div class="uploadImg">
						<label style="float:left;">上传微信群组图片：</label> 
						
					<div class="viewImg" style="float:left;width:150px;height:130px;background:#eee;margin-right:15px;padding:20px;">
						<label><span>&nbsp;&nbsp;</span></label> <img
							id="poster-view-img2" style="cursor: pointer; width:120px;height:120px;"
							src="<%=path.substring(0,path.length()-1)%>${activity.gqr}"
							alt="" /> <span
							class="tips tips-error"><i></i><em></em></span>
					</div>
					<div class="upload">
						<input type="file" id="gqr" name="upload"
							style="border: none;" /><input type="hidden" id="at_gqr"
							name="activity.gqr" value="${activity.gqr}"> 
							</p>
							<a
							id="poster-upload2" href="javascript:void(0);"
							style="color: red;">点击上传</a>
							</p>
							<label>图片大小比例建议为：430*430</label>
							<span class="tips tips-error"><i></i><em
							id="gqr_tip"></em></span>
							</div>
					</div>
			

					<div class="status">
						<label></label> <em></em>
					</div>

					<div>
						<label></label>
						<button id="at_submit1" class="m-btn m-btn-primary" type="submit">提交</button>
						<button id="at_submit2" class="m-btn m-btn-primary" type="submit">保存草稿</button>
					</div>
				</form>
			</div>

		</div>
	</div>

	<div class="footer-placeholder"></div>
	<jsp:include page="../base/joy1joy_footer.jsp"></jsp:include>
</body>
</html>