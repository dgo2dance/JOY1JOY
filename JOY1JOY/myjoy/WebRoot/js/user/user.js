/**

 * @author boyd
 * @version 1.0
 * @description 用户
 */
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {
		window.clearInterval(InterValObj);// 停止计时器
		$("#userCode").removeClass("m-btn m-btn-primary not-allowed");
        $("#userCode").addClass("m-btn m-btn-primary m-btn");
		$("#codeSpan").html("");
	} else {
		curCount--;
		$("#codeSpan").html("" + curCount + "秒后重新发送验证码");
	}
} 
//刷新图形验证码
function refreshRandCode(){
	var url=joy.getContextPath()+'/user/genericImage.action?rnd=' + Math.random();
	$("#randCodeImg").attr("src",url);
	$.ajax({
		 url:joy.getContextPath()+'/user/refreshCode.action',
		 type:'post',
		// data:{userPhone:phone},
		 cache:false,
		 dataType:'json',
		 success:function(re)
		 {
			 //alert(re.result);
			 $("#imageCodeValue").attr("value",re.result);
		 } ,error:function(e)
		 {
			 joy.alert(e);
			 return;
		 }
	});
	
}
$(function(){
	
	refreshRandCode();
	//发送验证码
	$("#userCode").click(function(e){
		e.preventDefault();
		 $("#codBtn").attr("value","");
		 var codeValue=$("#imageCode").val();
		// alert(codeValue);
		 if(codeValue=="")
		{
			 joy.alert("图形验证码输入错误");
			 return;
		}else
		{
			var sessionCode=$("#imageCodeValue").val();
			//alert(codeValue.toLowerCase());
			//alert(sessionCode);
			//alert(codeValue.toLowerCase()!=sessionCode);
			if(codeValue.toLowerCase()!=sessionCode)
			{
				joy.alert("图形验证码输入错误");
				return;
			}
			else
			{
				var phone=$("#userName").val();
				if(phone!="")
				{
					var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/;
					if(isMobile.test(phone))
					{
						
						$("#userCode").removeClass("m-btn m-btn-primary m-btn");
				        $("#userCode").addClass("m-btn m-btn-primary not-allowed");
				        curCount = count; 
				        $("#codeSpan").html("" + curCount + "秒后重新发送验证码"); 
				        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次 
				        $.ajax({
							 url:joy.getContextPath()+'/user/sendCode.action',
							 type:'post',
							 data:{userPhone:phone},
							 cache:false,
							 dataType:'json',
							 success:function(re)
							 {
								// alert(re);
								 if(re.result!="success")
								 {
									 joy.alert("短信验证码发送失败，请重新发送");
									 return;
								 }
							 }
							 ,error:function(e)
							 {
								 joy.alert("短信验证码发送失败，请重新发送");
								 return;
							 }
						});
					}else
					{
						$("#phoneSpan").html("手机号格式不正确");
						return;
					}
					
				}else
				{
					$("#phoneSpan").html("请输入手机号");
					return;
				}
			}
			
		}
		
		//GetNumber();
		
		//console.log(phone);
		//console.log(joy.getContextPath()+'/user/sendCode.action');
		
	});
	$("#phoneCode").blur(function(){
		var userName=$("#userName").val();
		var phoneCode=$("#phoneCode").val();
		$("#codBtn").attr("value","");
		if(phoneCode!="")
		{
			$.ajax({
				 url:joy.getContextPath() +'/user/compareCode.action',
				 type:'post',
				 data:{userPhone:userName,phoneCode:phoneCode},
				 cache:false,
				 dataType:'json',
				 success:function(re)
				 {
					 if(re.result!="success")
					 {
						 //$("#codBtn").val("error");
						 $("#codBtn").attr("value","error");
						 if(re.result=="timeout")
						 {
							 joy.alert("短信验证码不正确或已过期!");
							 
							 return;
						 }else
						 {
							 joy.alert("短信验证码输入有误");
							return;
						 }
						 
					 }
				 }
			});
		}
	});
	//注册页面js验证
	if($("#userName")!=null)
	{
		
		$("#userName").focus(function(){
			$("#phoneSpan").html("");
			
		});
		$("#phoneCode").focus(function(){
			$("#codeSpan").html("");
			
		});
		$("#imageCode").focus(function(){
			$("#imageCodeSpan").html("");
			
		});
		$("#onePass").focus(function(){
			$("#oneSpan").html("");
			
		});
		$("#twoPass").focus(function(){
			$("#twoSpan").html("");
			
		});
		$("#reg_btn").click(function(){
			var goRes=true;
			var userName=$("#userName").val();
			var phoneCode=$("#phoneCode").val();
			var imageCode=$("#imageCode").val();
			var onePass=$("#onePass").val();
			var twoPass=$("#twoPass").val();
			var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/;
			
			if(userName=="")
			{
				$("#phoneSpan").html("注册手机号不能为空");
				goRes=false;
			}
			//alert("0:"+goRes);
			if(userName!="")
			{
				if(!isMobile.test(userName))
				{ 
					$("#phoneSpan").html("手机号格式不正确");
					goRes=false;
				}
				
			}
			//alert("1:"+goRes);
			if(phoneCode=="")
			{
				$("#codeSpan").html("手机验证码不能为空");
				goRes=false;
			}
			//alert("2:"+goRes);
			if(phoneCode!="")
			{
				if($("#codBtn").val()!="")
				{
					goRes=false;
				}
				//alert($("#codBtn").val()+"3:"+goRes);
			}
			//alert("3:"+goRes);
			if(imageCode=="")
			{
				$("#imageCodeSpan").html("图形验证码不能为空");
				goRes=false;
			}
			//alert(goRes);
			if(onePass=="")
			{
				$("#oneSpan").html("密码不能为空");
				goRes=false;
			}
			if(onePass!="")
			{
				if(onePass.length<6||onePass.length>18)
				{
					$("#oneSpan").html("密码6~18位字符");
					goRes=false;
				}
			}
			//alert(goRes);
			if(twoPass=="")
			{
				$("#twoSpan").html("重复密码不能为空");
				goRes=false;
			}
			if(twoPass!="")
			{
				if(twoPass.length<6||twoPass.length>18)
				{
					$("#twoSpan").html("重复密码6~18位字符");
					goRes=false;
					return;
				}
				if(onePass!=twoPass)
				{
					$("#twoSpan").html("两次密码不一致");
					goRes=false;
				}
			}
			//alert(goRes);
			if(goRes)
			{
				if($("#agree").prop('checked'))
				{
					$.ajax({
						 url:joy.getContextPath() +'/user/findUser.action',
						 type:'post',
						 data:{userPhone:userName},
						 cache:false,
						 dataType:'json',
						 success:function(re)
						 {
							 if(re.result=="noExit")
							 {
								// alert(imageCode);
								 $.ajax({
									 url:joy.getContextPath() +'/user/saveUser.action',
									 type:'post',
									 data:{userPhone:userName,password:twoPass,imageNum:imageCode},
									 cache:false,
									 dataType:'json',
									 success:function(re)
									 {
										 if(re.result=="success")
										 {
											 joy.alert("注册成功，请登录!", function() {
													window.location = joy.getContextPath() + "/user/jumpLogin.action?d="
															+ new Date();
												});
										 }
										 else if(re.result=="codeFail")
										 {
											 joy.alert("图形验证码输入错误，请重新填写");
											 return;
										 }
										 else
										 {
											 joy.alert("注册失败，请重新注册");
											 return;
										 }
									 },error:function(e)
									 {
										 joy.alert("服务器繁忙，请稍候注册");
										 return;
									 }
								});
							 }
							 if(re.result=="exit")
							 {
								 joy.alert("手机号已存在注册");
								 return;
							 }
							 if(re.result=="fail")
							 {
								 joy.alert("服务器繁忙，请稍候注册");
								 return;
							 }
						 },error:function(e)
						 {
							 joy.alert("注册失败，请重新注册");
						 }
					});
					
				}else
				{
					joy.alert("请先同意《动一动用户条例》");
					return;
				}
			}else
			{
				joy.alert("输入有误，请核对输入项");
				return;
			}
			
			
		});
	}
	
	//登录界面js验证
	if($("#loginName")!=null&&$("#loginPass")!=null)
	{
		if ($.cookie("rememberMe") == "true") {
			$("#rmbUser").attr("checked", true);
			$("#loginName").val($.cookie("loginName"));
			$("#loginPass").val($.cookie("loginPass"));
		} 
		
//		$("#loginName").focus(function(){
//			if($(this).attr("placeholder")=="手机或者昵称")
//			{
//				$(this).val("");
//			}
//			
//		});
//		$("#loginName").blur(function(){
//			if($(this).attr("placeholder")=="")
//			{
//				$(this).val("手机或者昵称");
//			}
//		});
//		$("#loginPass").focus(function(){
//			if($(this).attr("placeholder")=="密码")
//			{
//				$(this).val("");
//			}
//			
//		});
//		$("#loginPass").blur(function(){
//			if($(this).attr("placeholder")=="")
//			{
//				$(this).val("密码");
//			}
//		});
		$("#log_btn").click(function(){
			var loginName=$("#loginName").val();
			var loginPass=$("#loginPass").val();
			if(loginName=="")
			{
				joy.alert("用户名不能为空");
				return;
			}
			if(loginPass=="")
			{
				joy.alert("密码不能为空");
				return;
			}
			if($("#rememberMe").prop('checked'))
			{
				$.cookie("rememberMe", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
				$.cookie("loginName", loginName, { expires: 7 }); // 存储一个带7天期限的 cookie
				$.cookie("loginPass", loginPass, { expires: 7 }); // 存储一个带7天期限的 cookie 
			}else
			{
				$.cookie("rememberMe", "false", { expires: -1 });
				$.cookie("loginName", '', { expires: -1 });
				$.cookie("loginPass", '', { expires: -1 }); 
			}
			$.ajax({
				 url:joy.getContextPath() +'/user/login.action',
				 type:'post',
				 data:{userId:loginName,userPhone:loginName,password:loginPass},
				 cache:false,
				 dataType:'json',
				 success:function(re)
				 {
					 if(re.result=="success")
					 {
						 window.location = joy.getContextPath() + "/home.action?d="
							+ new Date();
					 }
					 if(re.result=="passError")
					 {
						 joy.alert("密码输入错误");
						 return;
					 }
					 if(re.result=="noExit")
					 {
						 joy.alert("用户名不存在");
						 return;
					 }
					 
				 },error:function(e)
				 {
					 joy.alert("登录失败，请重新登录");
					 return;
				 }
			});
			
		});
	}
	
	//立即注册
	$(".flatReg").click(function(){
		
		window.location.href=joy.getContextPath()+"/user/register.action";
	});
	//立即登录
	$(".flatLogin").click(function(){
		
		window.location.href=joy.getContextPath()+"/user/jumpLogin.action";
	});
});
