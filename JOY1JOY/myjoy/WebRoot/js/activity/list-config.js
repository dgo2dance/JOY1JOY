/**
 * @author:xujun
 * @desc:configs-活动主页
 */

var at = {
	pno : 1,
	psize : 9,
	minJoyFee : 0,
	maxJoyFee : 0,
	time : 0,
	type : "all"
};
// 活动列表URL
JOY_URL_ACTIVITY_LIST = joy.getContextPath() + "/at/list.action";
JOY_JOY_FEE_MIN = 0;// 可选费用的最小值
JOY_JOY_FEE_MAX = 100;// 可选费用的最大值
JOY_CLASS_ITEM_SELECTED = "itemSelected";

// 活动展示魔板
JOY_TEMPLATE_ACTIVITY = '<li>'
		+ '<div class="m-course">'
		+ '<div class="m-course-top">'
		+ '<a href="{url}"> <img src="{poster}"><span class="m-course-title-bg"></span>'
		+ '<span class="m-course-title">{name}</span> <span class="m-course-user-bg"></span>'
		+ '<span class="m-course-user" > <img src="{mask_image}"><em>{cuserName}</em></span></a>'
		+ '</div>' + '<div class="m-course-bottom">'
		+ '<div class="m-course-time">' + '<i></i> <span>{time}</span>'
		+ '</div>' + '<div class="m-course-address">'
		+ '<i></i> <span>{statusName}</span>' + '</div>' + '</div>' + '</div>'
		+ '</li>';