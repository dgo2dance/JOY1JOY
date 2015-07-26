/**
 * @author:xujun
 * @desc:defines-活动主页
 */
// 获取活动
at.get_activities = function() {
	// 查询
	var data = {
		pno : at.pno,
		psize : at.psize,
		minJoyFee : at.minJoyFee,
		maxJoyFee : at.maxJoyFee,
		time : at.time,
		type : at.type
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
			} else {
				$("#at-no-more-loading").hide();
				$("#at-loading").show();
			}
		}

	});
};
// 加载更多
at.get_more = function() {
	at.pno = at.pno + 1;
	at.get_activities();
};
// 搜索
at.search_handler = function(t, o) {
	var j_obj = $(o);
	var v = j_obj.attr("data-value");

	at.search_pre_handler(t, v);

	at.pno = 1;
	$("#list_activities").empty();
	at.get_activities();
};
at.search_pre_handler = function(t, v) {
	switch (t) {
	case 0:
		at.type = v;
		break;
	case 1:
		at.time = v;
		break;
	case 2:
		if (v == 0) {// 全部
			at.minJoyFee = at.maxJoyFee = 0;
		} else if (v == 1) {// 免费
			at.minJoyFee = at.maxJoyFee = -1;
		} else if (v == -1) {// 其它费用区间
			at.minJoyFee = -2;
			at.maxJoyFee = JOY_JOY_FEE_MAX;
		} else {
			at.minJoyFee = v.split("-")[0];
			at.maxJoyFee = v.split("-")[1];
		}
		break;
	default:
		break;
	}

	// $.getJSON(JOY_URL_ACTIVITY_LIST, {
	// pno : pno,
	// psize : psize
	// }, function(o) {
	// if (o && o.list) {
	// $.each(o.list, function(i) {
	// var me = this;
	// me.mask_image = activity.get_mask_image();
	// me.poster = joy.getContextPath() + me.poster;
	// me.url = "http://www.baidu.com";
	// var content = joy.template(JOY_TEMPLATE_ACTIVITY, me);
	// $("#list_activities").append(content);
	// });
	// }
	// });
};
at.get_dtype_name = function(k) {
	var name = "";
	$.each($("#at_dtype a"), function(v) {
		if ($(this).attr("data-value") == k) {
			name = $(this).html();
			return false;
		}
	});
	return name;
}