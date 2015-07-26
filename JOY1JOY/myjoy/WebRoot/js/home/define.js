/**
 * @author:xujun
 * @desc:首页defines
 */
var home = {};
// 获取最新的活动
home.get_newest_activities = function() {
	$.getJSON(JOY_URL_ACTIVITY_LIST, {
		pno : 1,
		psize : JOY_HOME_NEWEST_ACTIVIITY_NUM
	}, function(o) {
		if (o && o.list) {
			$.each(o.list, function(i) {
				var me = this;
				me.mask_image = joy.getContextPath() + "" + me.cuserIcon;// activity.get_mask_image();
				me.poster = joy.getContextPath() + me.poster;
				me.url = joy.getContextPath()
						+ "/at/detail.action?activity.id=" + me.id;
				me.time = me.stime.substring(0, 10);// +"~"+me.etime.substring(0,10);
				me.statusName = JOY_ACTIVITY_STATUS[me.status];
				var content = joy.template(JOY_TEMPLATE_ACTIVITY, me);
				$("#home_activities").append(content);
			});
		}
	});
};
home.get_newest_kn_nt_pt = function() {
	$.getJSON(JOY_HOME_URL_KN_NT, {
		start : 0,
		end : 6,
		type : "knowledge"
	}, function(o) {
		if (o && o.data) {
			$.each(o.data, function(i) {
				var me = this;
				var detail_url = joy.getContextPath()
						+ "/notice/detailNotice.action?noticeId=" + me.id;
				var html = '<li><a href="' + detail_url + '" target="_blank">'
						+ me.title + '</a></li>';
				$("#home-knowledge").append(html);
			});
		}
	});
};
home.get_newest_kn_nt_pt2 = function() {
	$.getJSON(JOY_HOME_URL_KN_NT, {
		start : 0,
		end : 6,
		type : "activity"
	}, function(o) {
		if (o && o.data) {
			$.each(o.data, function(i) {
				var me = this;
				var detail_url = joy.getContextPath()
						+ "/notice/detailNotice.action?noticeId=" + me.id;
				var html = '<li><a href="' + detail_url + '" target="_blank">'
						+ me.title + '</a></li>';
				$("#home-activity").append(html);
			});
		}
	});
};
home.get_newest_join = function() {
	$.getJSON(JOY_HOME_URL_JOIN, {}, function(o) {
		if (o && o.data) {
			$.each(o.data, function(i) {
				var me = this;
				var name = me.userid;
				if (me.nickname) {
					name = me.nickname;
				}

				var title = name + "参加了'" + me.name + "'";
				var aturl = joy.getContextPath()
						+ "/at/detail.action?activity.id=" + me.at_id;
				var html = '<li><a href="' + aturl + '" target="_blank">'
						+ title + '</a></li>';
				$("#home-join").append(html);
			});
		}
	});
};
