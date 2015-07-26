package com.joy1joy.app.actions.base;

/**
 *
 * @author xujun
 */
public interface IAction {
	final public static String C_SUCCESS = "success";
	final public static String C_INPUT = "input";
	final public static String C_DETAIL = "detail";
	final public static String C_LIST = "list";
	final public static String C_ERROR = "error";
	final public static String C_JSON = "json";

	final public static int R_SUCCESS = 0;

	final public static String CODE = "code";
	final public static String MSG = "msg";
	final public static String DATA = "data";
	final public static String FILE_PATH = "fpath";
	final public static String CONTEXT_PATH = "contextPath";
	final public static String JSON_DATA = "jsonData";

	final public static String DICT_AT_TYPE = "at_type";
	final public static String DICT_ADDRESS = "address";

	final public static String MSG_SUCCESS = "操作成功!";
	final public static String MSG_FAILURE = "操作失败!";

	// 活动图片存放相对路径
	final public static String PATH_ACTIVITY_POSTER_BASE = "/images/at/";

}
