/**
 * @author:xujun
 * @desc:主控制js-活动主页
 */

$(function() {
	at.get_activities();
	// 分类
	$("#at_dtype a").on("click", function(e) {
		e.preventDefault();
		$(this).addClass(JOY_CLASS_ITEM_SELECTED);
		var fobj = this;
		$("#at_dtype a").each(function() {
			if (fobj != this) {
				$(this).removeClass(JOY_CLASS_ITEM_SELECTED);
			}
		});
		at.search_handler(0, this);
	});
	// 时间
	$("#at_time a").on("click", function(e) {
		e.preventDefault();
		$(this).addClass(JOY_CLASS_ITEM_SELECTED);
		var fobj = this;
		$("#at_time a").each(function() {
			if (fobj != this) {
				$(this).removeClass(JOY_CLASS_ITEM_SELECTED);
			}
		});
		at.search_handler(1, this);
	});
	// 费用
	$("#at_fee a").on("click", function(e) {
		e.preventDefault();
		$(this).addClass(JOY_CLASS_ITEM_SELECTED);
		var fobj = this;
		$("#at_fee a").each(function() {
			if (fobj != this) {
				$(this).removeClass(JOY_CLASS_ITEM_SELECTED);
			}
		});
		at.search_handler(2, this);
	});

});
