/**
 * @author:xujun
 * @desc:活动管理
 */

at.findData = false;
at.keyword = "";
at.get_activities = function() {
	// 查询
	var data = {
		pno : at.pno,
		psize : at.psize,
		minJoyFee : at.minJoyFee,
		maxJoyFee : at.maxJoyFee,
		time : at.time,
		keyWord : at.keyword
	};

	$.getJSON(JOY_URL_ACTIVITY_LIST, data, function(o) {

		if (o && o.list) {
			var finished = true;

			$.each(o.list, function(i) {
				finished = false;
				var me = this;
				me.mask_image = joy.getContextPath() + "" + me.cuserIcon;// activity.get_mask_image();
				me.poster = joy.getContextPath() + me.poster;
				me.url = joy.getContextPath()
						+ "/at/detail.action?activity.id=" + me.id;
				me.time = me.stime.substring(0, 10);// +"~"+me.etime.substring(0,10);
				// me.typeName = at.get_dtype_name(me.type);
				me.statusName = JOY_ACTIVITY_STATUS[me.status];
				var content = joy.template(JOY_TEMPLATE_ACTIVITY, me);
				$("#list_activities").append(content);
			});

			if (finished) {//
				$("#at-loading").hide();
				$("#at-no-more-loading").show();
				if (!at.findData) {
					$("#at_nothing").show();
					$("#at-loading").hide();
					$("#at-no-more-loading").hide();
				}
			} else {
				$("#at-no-more-loading").hide();
				$("#at-loading").show();
				if (!at.findData) {
					at.findData = true;
					$("#at_nothing").hide();
					$("#at-no-more-loading").hide();
					$("#at-loading").show();
				}
			}
		}

	});
};
// 加载更多
at.get_more = function() {
	at.pno = at.pno + 1;
	at.get_activities();
};
at.search_handler = function() {
	at.findData = false;

	var keyword = $.trim($("#at_search_input").val());
	if ("" == keyword) {
		joy.alert("请填写搜索关键字!!!");
		return false;
	}
	$("#list_activities").empty();
	at.pno = 1;
	at.keyword = keyword;
	at.get_activities();
}
$(function() {
	$("#at_search_form").submit(function() {
		at.search_handler();
		return false;
	});

	$("#search-submit").trigger("click");
});