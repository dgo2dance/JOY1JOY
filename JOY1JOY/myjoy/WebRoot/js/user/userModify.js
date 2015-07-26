/**

 * @author boyd
 * @version 1.0
 * @description 用户
 */
$(function(){
	//头像
	//$("#changeHead").click(function(){
		//$("#imgOne").show();
		//$("#imgOne").click();
		//$("#imgOne").change();
	//})
	//var isIE = navigator.userAgent.match(/MSIE/)!= null;
	//alert(isIE);
	/*
	if(!isIE)
	{
		$("#imgOne").hide();
		$("#changeHead").click(function(){
			//$("#imgOne").show();
			$("#imgOne").click();
			$("#imgOne").change();
		});
	}
	*/
	//保存按钮
	$("#editUserInfo").click(function(){
		var emailValue=$("#email").val();
		if(emailValue!="")
		{
			 var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			    if (!reg.test(emailValue)) {
			        joy.alert('邮箱格式不正确，请重新填写!');
			       return;
			  }
		}
		document.userEditForm.submit();
	})
})

function previewImage(file)
        {
          var MAXWIDTH  = 154; 
          var MAXHEIGHT = 154;
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          {
        	  var fileValue=$("#imgOne").val();
        	  var indexSrc=fileValue.lastIndexOf(".")+1; 
              var imgValue=fileValue.substring(indexSrc);
              if(imgValue.toUpperCase()=="GIF"||imgValue.toUpperCase()=="JPG"||imgValue.toUpperCase()=="PNG"||imgValue.toUpperCase()=="JPEG")
              {
            	  div.innerHTML ='<a href="javascript:void(0);" style="cursor: default;"><img id=imghead></a>';
                  var img = document.getElementById('imghead');
                  img.onload = function(){
                    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                    img.width  =  rect.width;
                    img.height =  rect.height;
                  }
                  var reader = new FileReader();
                  reader.onload = function(evt){img.src = evt.target.result;}
                  reader.readAsDataURL(file.files[0]);
              }else
              {
             	 joy.alert("图片类型必须是.gif,jpeg,jpg,png中的一种!");
             	 return;
             }
              
             
          }
          else //IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            //obj.select(); 
            file.blur(); 
            var src = document.selection.createRange().text; 
            var indexSrc=src.lastIndexOf(".")+1; 
            var imgValue=src.substring(indexSrc);
            if(imgValue.toUpperCase()=="GIF"||imgValue.toUpperCase()=="JPG"||imgValue.toUpperCase()=="PNG"||imgValue.toUpperCase()=="JPEG")
            {
            	 //alert(imgValue.toUpperCase());
                document.selection.empty();  
               // var src = document.selection.createRange().text;
                //alert( document.selection.createRange().value);
                div.innerHTML = '<a href="javascript:void(0);" style="cursor: default;"><img id=imghead></a>';
                var img = document.getElementById('imghead');
                img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
                //var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                //status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
                div.innerHTML = "<div id=divhead style='width:120px;height:120px;"+sFilter+src+"\"'></div>";
            }else
            {
            	 joy.alert("图片类型必须是.gif,jpeg,jpg,png中的一种!");
            	 return;
            }
           
          }
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                 
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
             
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }