var JOY_AT_JOIN_URL = joy.getContextPath() + "/join/add.action";
var JOY_AT_COLLECT_URL = joy.getContextPath() + "/collect/add.action";
function opt_handler(t, id) {

	switch (t) {
	case 0:// 报名
		var pnum = $("#at_joiin_pnum").val();
		var premark = $("#at_joiin_remark").val();
		var piphone = $("#at_joiin_iphone").val();
		var pidcard = $("#at_joiin_idcard").val();
		if (!/[0-9]+/.test(pnum) || pnum < 1) {
			joy.alert('请正确输入报名人数!');
			break;
		}
		if (!/[0-9]+/.test(piphone) || piphone ==null) {
			joy.alert('请输入手机号码，以便联系!');
			break;
		}
		var param = {
			"atUser.pnum" : pnum,
			"atUser.atid" : id,
			"atUser.remark" : encodeURI(premark),
			"atUser.iphone" : piphone,
			"atUser.idcard" : pidcard
		};
		at_join(param);
		break;
	case 1:// 加入群组
		var qr = $("#at_gqr").val();
		if (qr && qr != "") {
			$("#at_qr_area").fadeToggle("slow");
		} else {
			joy.alert("对不起,该活动还没有创建群组!");
		}
		break;
	case 2:// 收藏
		var data = {
			"collect.atid" : id
		};
		at_collect(data);
		break;
	default:
		break;
	}
}
function at_join(data) {
	$.getJSON(JOY_AT_JOIN_URL, data, function(o) {
		if (o) {
			var code = o.code;
			if (code == 0) {
				joy.alert("恭喜您，报名成功!");
			} else if (code == 1) {
				joy.alert("您已报名此活动!");
			} else if (code == 2) {
				joy.alert("活动不存在，报名失败!");

			} else if (code == 3) {
				joy.alert("活动状态不正确，报名失败!");
			} else if (code == 4) {
				joy.alert("活动状态不正确，报名失败!");
			} else {
				if (o && o.checkSession) {
					joy.util.handle_not_login(window);
				} else
					joy.alert("对不起,报名失败!");
			}
		}
	});
}
function at_collect(data) {
	$.getJSON(JOY_AT_COLLECT_URL, data, function(o) {
		if (o && o.code == 0) {
			joy.alert("恭喜您，收藏成功!")
		} else {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			} else
				joy.alert("对不起，收藏失败!");
		}
	});
}

$(function() {
//	$("#at_join_group").on("blur", function() {
//		$("#at_qr_area").fadeOut("slow");
//	});
	$(".d_con").find("img").each(function(i){
	//	alert($(this).width());
	//	alert($(this).height());
	var height=595/$(this).width()*$(this).height();
		if($(this).width()>595){
			$(this).width(595);
			$(this).height(height);
		}
	
	});
});
