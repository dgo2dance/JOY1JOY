/**
 * @author:xujun
 * @desc:configs-新增活动
 */
var TIP_NULL_UPLOAD_FILE = "请选择要上传的文件!";
var JOY_URL_ACTIVITY_ADD = joy.getContextPath() + "/at/add.action";
var JOY_URL_ACTIVITY_UPDATE = joy.getContextPath() + "/at/update.action";
var JOY_URL_SUB_DICT = joy.getContextPath() + "/dict/findSubOptions.action";
var AT_ADDRESS_PROVINCE = [ {
	id : "HN",
	text : "河南省"
}, {
	id : "HB",
	text : "湖北省"
} ];
var AT_ADDRESS_CITY = {

};
var AT_TYPES = [];

var validate_config = {
	defaultType : 'text',
	objs : [ {
		name : "name",
		tip : ""
	} ]
}
