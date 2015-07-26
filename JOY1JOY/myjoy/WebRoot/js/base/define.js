/**
 * @author:xujun
 * @desc:全局defines
 */

var joy = {
	alert : function(content, callback) {
		$.sobox.alert("提示", content, callback);
	},
	confirm : function(title, content, successBack, cancelBack) {
		$.sobox.confirm(title, content, successBack, cancelBack);
	},
	getContextPath : function() {
		// var pathName = window.document.location.pathname;
		// var projectName = pathName.substring(0,
		// pathName.substr(1).indexOf('/') + 1);
		return contextPath;
	},
	template : function(template, data) {// 按摩板生成代码
		return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
			var keys = key.split("."), v = data[keys.shift()];
			for (var i = 0, l = keys.length; i < l; i++)
				v = v[keys[i]];
			return (typeof v !== "undefined" && v !== null) ? v : "";
		});
	},
	util : {
		handle_not_login : function(w) {
			joy.alert("对不起，您还没有登录!", function() {
				w.location = joy.getContextPath() + "/user/jumpLogin.action?d="
						+ new Date();
			});

		},
		handle_no_admin_privilege : function(w) {
			joy.alert("对不起，您没有权限!", function() {
				w.location = joy.getContextPath() + "/at/home.action?d="
						+ new Date();
			});
		}

	}
};
var activity = {
	get_mask_image : function() {
		return joy.getContextPath()
				+ "/images/a3a6301bc55adf501e277983be4460ad.jpg";
	}
};
