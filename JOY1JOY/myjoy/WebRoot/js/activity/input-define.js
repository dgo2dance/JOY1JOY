/**
 * @author:xujun
 * @desc:defines-新增活动
 */
function init() {
	var type_value = $("#at_type").val();
	if ("" != type_value) {
		$.each(type_value.split(","), function(i, v) {
			var f = $("input[type='checkbox'][value='" + v + "']").attr(
					"checked", "checked");
		});
	}
	//

}
var jrop1 = null;
function poster_crop() {
	// $('#poster-view-img').Jcrop({
	// onSelect : crop1Select
	// }, function() {
	// jrop1 = this;
	// });
}
function crop1Select(c) {
	// console.log("selecte");
};

// 上传文件
function upload(up_v) {
	if (!$("#poster").val()) {
		joy.alert(TIP_NULL_UPLOAD_FILE);
		return false;
	}
	$.ajaxFileUpload({
		url : joy.getContextPath() + '/at/upload.action',
		secureuri : false,
		fileElementId : 'poster',
		dataType : 'json',
		type : "post",
		data : {
			"FILENAME" : up_v
		},
		success : function(data, status) {
			var code = data.code;
			if (0 == code) {
				var img_url = joy.getContextPath() + data.data.fpath + "?"
						+ new Date();

				var pre_img = $("#poster-view-img");
				var pre_li = $("#poster-view-li");
				pre_img.attr("src", img_url);
				$("#at_poster").val(data.data.fpath);
				poster_crop();
			} else {
				alert("上传失败!");
			}
		},
		error : function(data, status, e) {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			} else
				alert("保存失败!");
		}
	});
}
function upload2(up_v) {
	if (!$("#gqr").val()) {
		joy.alert(TIP_NULL_UPLOAD_FILE);
		return false;
	}
	$.ajaxFileUpload({
		url : joy.getContextPath() + '/at/upload.action',
		secureuri : false,
		fileElementId : 'gqr',
		dataType : 'json',
		type : "post",
		data : {
			"FILENAME" : up_v
		},
		success : function(data, status) {
			var code = data.code;
			if (0 == code) {

				var img_url = joy.getContextPath() + data.data.fpath + "?"
						+ new Date();

				var pre_img = $("#poster-view-img2");
				var pre_li = $("#poster-view-li2");
				pre_img.attr("src", img_url);
				$("#at_gqr").val(data.data.fpath);
			} else {
				alert("上传失败!");
			}
		},
		error : function(data, status, e) {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			} else
				alert("保存失败!");
		}
	});
}
function tip(msg) {
	joy.alert(msg);
}
function form_init() {
	$("#activity_form")[0].reset();
}
function submit_handler(status) {
	var is_approved = true;

	var name_val = $.trim($("#at_name").val());
	if (name_val == "") {
		tip("标题不能为空");
		return false;
	}

	var stime_val = $.trim($("#at_stime").val());
	if (stime_val == "") {
		tip("开始时间不能为空!");
		return false;
	}
	var etime_val = $.trim($("#at_etime").val());
	if (etime_val == "") {
		tip("结束时间不能为空!");
		return false;
	}

	var type = "", type_array = [];
	$.each($("input[name='type']:checked"), function() {
		type_array.push($(this).val());
	});
	type = type_array.join(",");
	if ("" == type) {
		tip("类别不能为空");
		return false;
	}

	var pnum_val = $.trim($("#at_pnum").val());
	if (pnum_val == "") {
		tip("活动人数不能为空");
		return false;
	}

	if (!is.integer(parseInt(pnum_val)) || pnum_val < 1) {
		tip("活动人数必须为正整数!");
		return false;
	}

	var at_fee_val = $("input[name='at_fee']:checked").val();
	var at_joyFee_val = $("#at_joyFee").val();
	if (at_fee_val == "1") {
		if ($.trim(at_joyFee_val) == "" || at_joyFee_val <= 0) {
			tip("费用不能为空");
			return false;
		}
		var afee = parseFloat(at_joyFee_val);
		if (!is.number(afee)) {
			tip("费用必须为数字!");
			return false;
		}
	} else {
		$("#at_joyFee").val(0);
		at_joyFee_val = 0;
	}

	var poster_val = $.trim($("#at_poster").val());
	if (poster_val == "") {
		tip("海报不能为空");
		return false;
	}

	var at_description_val = CKEDITOR.instances.at_description.getData();

	if (at_description_val == "") {
		tip("介绍不能为空");
		return false;
	}

	var at_attention_val = CKEDITOR.instances.at_attention.getData();
	if (at_attention_val == "") {
		tip("注意事项不能为空");
		return false;
	}

	var at_caution_val = CKEDITOR.instances.at_caution.getData();
	if (at_caution_val == "") {
		tip("报名须知不能为空");
		return false;
	}

	var autoSMS_val = $("input[name='activity.autoSMS']:checked").val();
	var at_smsContent_val = $.trim($("#at_smsContent").val());

	if (autoSMS_val == 1 && at_smsContent_val == "") {
		tip("短信内容不能为空");
		return false;
	} else {
		if (autoSMS_val == 0) {
			$("#at_smsContent").val("");
			at_smsContent_val = "";
		}

	}

	var uploadWeiXin_val = $("input[name='uploadWeiXin']:checked").val();
	var at_gqr_val = $.trim($("#at_gqr").val());

	if (uploadWeiXin_val == 1) {
		if (at_gqr_val == "") {
			tip("请上传群组二维码");
			return false;
		}

	} else {
		$("#poster-view-img").attr("src") == "";
		$("#at_gqr").val("");
		at_gqr_val = "";
	}

	var data = {
		'activity.id' : $("#at_id").val(),
		"activity.name" : name_val,
		"activity.status" : status,
		"activity.address" : $("select[name='activity.address']").val(),
		"activity.stime" : stime_val,
		"activity.etime" : etime_val,
		"activity.type" : type,
		"activity.pnum" : pnum_val,
		"activity.joyFee" : at_joyFee_val,
		"activity.poster" : poster_val,
		"activity.description" : at_description_val,
		"activity.attention" : at_attention_val,
		"activity.caution" : at_caution_val,
		"activity.gqr" : at_gqr_val,
		"activity.autoSMS" : autoSMS_val,
		"activity.smsContent" : at_smsContent_val,

	};

	var url = JOY_URL_ACTIVITY_ADD;
	var opt_type = $("#at_optType").val();
	var isAdd = true;
	if (1 == opt_type) {
		isAdd = false;
		url = JOY_URL_ACTIVITY_UPDATE;
		if (data["activity.id"] == "") {
			tip("非法操作!");
			return false;
		}
	}

	$.post(url, data, function(o) {
		if (o && o.code == 0) {
			if (isAdd)
				form_init();
			joy.alert("恭喜您,保存成功!", function() {
				window.location.href = joy.getContextPath()
						+ "/at/organize.action";
			});

		} else {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			} else
				joy.alert("对不起,保存失败,请稍后重试!");
		}
	}, "json");
	return is_approved;
}
function handleSubDict(v) {
	$.getJSON(JOY_URL_SUB_DICT, {
		"dtype" : "address",
		"fdkey" : v
	}, function(o) {
		console.log(o);
		if (o && o.data) {
			var optObj = $("select[name='activity.address']");
			optObj.empty();
			$.each(o.data, function(i) {
				optObj.append('<option value="' + this.id + '">' + this.text
						+ '</option>');
			});

		} else {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			}
		}

	});
}