/**
 * 
 * @author boyd
 * @version 1.0
 * @description 公告
 */
// 加载公告列表
var initPagination = function() {
	var num_entries = $("#activityPageNum").val();
	// 创建分页
	$("#Pagination").pagination(num_entries, {
		num_edge_entries : 1, // 边缘页数
		num_display_entries : 3, // 主体页数
		callback : pageselectCallback,
		items_per_page : 1, // 每页显示1项
		prev_text : "上一页",
		next_text : "下一页"
	});
};
function pageselectCallback(page_index, jq) {
	page_index += 1;
	$.ajax({
		url : joy.getContextPath() +'/notice/noticeList.action',
		type : 'post',
		data : {
			noticeType : "activity",
			pageIndex : page_index
		},
		cache : false,
		dataType : 'json',
		success : function(re) {
			/*
			if (re.checkSession != null && re.checkSession != "") {
				if (re.checkSession == "noSession") {
					joy.handle_not_login(window);
				}
			} else {
			*/
				// num_entries=re.total;
				$("#noticeRow").empty();
				var resMap = eval(re.rows);
				// alert(resMap.id);
				$.each(resMap, function(i) {
					var obj = resMap[i];
					var html = '<li>';
					html += '<a href="'+joy.getContextPath()+'/notice/detailNotice.action?noticeId='
							+ obj.id + '">' + obj.title + '</a>';
					html += '<p class="gray">' + obj.cdatetime + '</p>';
					html += '<p class="blank">' + obj.content + '</p>';
					html += '</li>';
					$("#noticeRow").append(html);
				});
				$("#knowledgePage").hide();
				$("#Pagination").show();
			}

		//}
	});
	return false;
}

// 加载户外知识列表
var initKnowledge = function() {
	var num_entries = $("#knowledgePageNum").val();
	// 创建分页
	$("#knowledgePage").pagination(num_entries, {
		num_edge_entries : 1, // 边缘页数
		num_display_entries : 3, // 主体页数
		callback : pageselectCallback1,
		items_per_page : 1, // 每页显示1项
		prev_text : "上一页",
		next_text : "下一页"
	});
};
function pageselectCallback1(page_index, jq) {
	page_index += 1;
	$.ajax({
		url : joy.getContextPath() +'/notice/noticeList.action',
		type : 'post',
		data : {
			noticeType : "knowledge",
			pageIndex : page_index
		},
		cache : false,
		dataType : 'json',
		success : function(re) {
			/*
			if (re.checkSession != null && re.checkSession != "") {
				if (re.checkSession == "noSession") {
					window.location.href = "../user/jumpLogin.action";
				}
			} else {
			*/
				// num_entries=re.total;
				$("#knowledgeRow").empty();
				var resMap = eval(re.rows);
				// alert(resMap.id);
				$.each(resMap, function(i) {
					var obj = resMap[i];
					var html = '<li>';
					html += '<a href="'+joy.getContextPath()+'/notice/detailNotice.action?noticeId='
							+ obj.id + '">' + obj.title + '</a>';
					html += '<p class="gray">' + obj.cdatetime + '</p>';
					html += '<p class="blank">' + obj.content + '</p>';
					html += '</li>';
					$("#knowledgeRow").append(html);
				});
				$("#Pagination").hide();
				$("#knowledgePage").show();
			}

		//}
	});
	return false;
}
$(function() {
	$("#knowledgePage").hide();
	$("#Pagination").hide();
	initPagination();
	initKnowledge();
	$("#knowledge").click(function() {
		initKnowledge();
	});
	$("#activity").click(function() {
		initPagination();
	});
	var types = $("#noticeType").val();
	if (types == "activity") {
		$("#knowledge").removeClass("selected");
		$("#activity").addClass("selected");
		$("#usual2 ul").idTabs("tabs2");
	}
	if (types == "knowledge") {
		$("#activity").removeClass("selected");
		$("#knowledge").addClass("selected");
		$("#usual2 ul").idTabs("tabs1");
	}
});
