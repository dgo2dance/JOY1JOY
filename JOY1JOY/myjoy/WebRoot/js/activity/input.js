/**
 * @author:xujun
 * @desc:新增活动主控制js
 */
$(function() {
	init();
	handleSubDict("henan");
	// 上传文件
	$("#poster-upload").on("click", function(e) {
		e.preventDefault();
		$("#at_poster").val("");
		upload($(this).val());
	});
	$("#poster-upload2").on("click", function(e) {
		e.preventDefault();
		$("#at_gqr").val("");
		upload2($(this).val());
	});

	$("#at_submit1").on("click", function(e) {
		e.preventDefault();
		submit_handler(0);
	});
	$("#at_submit2").on("click", function(e) {
		e.preventDefault();
		submit_handler(-1);
	});
	$("#at_address").on("change", function() {
		var v=$(this).val();
		handleSubDict(v);
	});
});
