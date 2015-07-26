/**
 * @author:xujun
 * @desc:活动管理
 */
var page = {
	pno : 1,
	psize : 5,
	status : -100
};
var JOY_URL_ALL_AT_COUNT_LIST_URL = joy.getContextPath()
		+ "/at/allAtCount.action";
var JOY_URL_ALL_AT_LIST_URL = joy.getContextPath() + "/at/allAtList.action";
var JOY_URL_AUDIT_AT_URL = joy.getContextPath() + "/at/updateStatus.action";
var ITEM_TEMPLATE = '<li class="join-row"><div class="td td-1">'
		+ '<a href="{link_url}" class="title">{name}</a>'
		+ '<p class="gray">{stime}</p></div><div class="td td-2"></div>'
		+ '<div class="td td-3">{statusName}（{partNum}/{pnum}）</div><div class="td td-4">'
		+ '{opt}</li>';
var ITEM_OPT_TEMPLATE = '<a href="{optUrl}">{optName}</a>';

function at_handler() {
	var i = 0;
	$.getJSON(JOY_URL_ALL_AT_COUNT_LIST_URL, page, function(o) {
		if (o && o.data) {
			i = o.data;
		} else {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			}
			if (o && o.adminAccess) {
				joy.util.handle_no_admin_privilege(window);
			}
		}
		handler(i);
	});
}
function handler(i) {
	$(".pagination").pagination(i, {
		num_edge_entries : 3, // 边缘页数
		num_display_entries : 4, // 主体页数
		callback : pageselectCallback,
		items_per_page : page.psize, // 每页显示1项
		prev_text : "上一页",
		next_text : "下一页"
	});
}

function pageselectCallback(page_index, jq) {
	page.pno = page_index + 1;
	$.getJSON(JOY_URL_ALL_AT_LIST_URL, page, function(o) {
		if (o && o.data) {
			$(".join-list").empty();
			$.each(o.data, function(i) {
				var me = this;
				me.statusName = "";
				if (JOY_ACTIVITY_STATUS[me.status]) {
					me.statusName = JOY_ACTIVITY_STATUS[me.status];
				}
				// 处理链接
				var link_url = joy.getContextPath()
						+ "/at/detail.action?activity.id=" + me.id;
				me.link_url = link_url;
				// 处理操作
				var opt = "";
				var opt_param = {
					id : me.id,
					optUrl : "#",
					optName : ""
				};

				if (me.status == 0) {
					opt_param.optName = "审核通过";
					opt_param.optUrl = "javascript:audit_handler(1," + me.id
							+ ");";
					opt += joy.template(ITEM_OPT_TEMPLATE, opt_param);
					opt_param.optName = "审核不通过";
					opt_param.optUrl = "javascript:audit_handler(-2," + me.id
							+ ");";
					opt += joy.template(ITEM_OPT_TEMPLATE, opt_param);
				}

				opt_param.optName = "成员管理";
				opt_param.optType = 2;
				opt_param.optUrl = joy.getContextPath()
						+ "/at/partiList.action?activity.id=" + me.id;
				opt += joy.template(ITEM_OPT_TEMPLATE, opt_param);

				me.opt = opt;

				var content = joy.template(ITEM_TEMPLATE, me);
				$(".join-list").append(content);
			});

		} else {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			}
			if (o && o.adminAccess) {
				joy.util.handle_no_admin_privilege(window);
			}
		}

	});
	return false;
}
function audit_handler(status, id) {
	$.getJSON(JOY_URL_AUDIT_AT_URL, {
		"activity.id" : id,
		"activity.status" : status
	}, function(o) {
		if (o && o.code && o.code == 1) {
			if (1 == status) {
				joy.alert("恭喜您,执行审核通过成功!");
			} else if (-2 == status) {
				joy.alert("恭喜您,执行审核不通过成功!");
			}
			pageselectCallback(page.pno - 1, null);
		} else {
			if (o && o.checkSession) {
				joy.util.handle_not_login(window);
			}
			if (o && o.adminAccess) {
				joy.util.handle_no_admin_privilege(window);
			} else {
				joy.alert("对不起，操作失败!");
			}

		}
	});
}
$(function() {
	page.status = $("select[name='status']").val();
	at_handler();

	$("select[name='status']").on("change", function() {
		page.pno = 1;
		page.status = $(this).val();
		at_handler();
	});
});