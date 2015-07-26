/*
 * @author:xujun @desc 成员管理
 */
var selected = 0;
var JOY_URL_AT_EXPORT = joy.getContextPath() + "/join/export.action";
function handler(id, isChecked) {

	var obj = document.getElementById(id);
	if (isChecked && !obj.checked) {
		selected += 1;
	}
	if (!isChecked && obj.checked) {
		selected -= 1;
	}

	obj.checked = isChecked;

}
function export_handler(data) {
	if (!data.length) {
		joy.alert("请选择要导出的数据");
		return false;
	}
	//console.log(data.join(","));
	$.getJSON(JOY_URL_AT_EXPORT, {
		ids : data.join(","),
		atid : $("#atid").val()
	}, function(o) {
		if (o && o.code && o.data) {
			window.location = joy.getContextPath() + "" + o.data + "";
		} else {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			} else {
				joy.alert("对不起，导出列表失败!");
			}
		}
	});
}
$(function() {
	selected = $("input[type='checkbox']").length - 1;
	$("#already-selected").html(selected);

	$("#all-selected-or-not").on("click", function() {
		var checked = $(this).is(":checked");

		$($("input[name='partiAt']")).each(function() {
			var id = $(this).attr("id");
			handler(id, checked);
			$("#already-selected").html(selected);
		});
	});

	$("input[name='partiAt']").on("click", function() {
		var checked = $(this).is(":checked");
		selected += (checked ? 1 : -1);
		$("#already-selected").html(selected);
	});
	// 导出
	$("#export-selected").on("click", function(e) {

		var data = [];
		$($("input[name='partiAt']")).each(function() {
			if ($(this).is(":checked")) {
				data.push($(this).val());
			}
		});
		export_handler(data);
	});
});
