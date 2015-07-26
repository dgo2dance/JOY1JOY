package com.joy1joy.app.actions;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.joy1joy.app.actions.base.BaseAction;
import com.joy1joy.app.bean.ActivityPage;
import com.joy1joy.app.bean.PartiUserInfo;
import com.joy1joy.app.bean.TActivity;
import com.joy1joy.app.bean.TDict;
import com.joy1joy.app.core.annotation.AdminAccess;
import com.joy1joy.app.core.annotation.LoginAccess;
import com.joy1joy.app.service.ITActivity;
import com.joy1joy.app.service.ITAtUsersService;
import com.joy1joy.app.service.ITDictService;
import com.joy1joy.utils.DateTool;
import com.opensymphony.xwork2.ActionContext;

/**
 * 活动action
 * 
 * @author xujun
 */
@Namespace("/at")
public class TActivityAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private List<TActivity> list;

	private int optType = -1;
	private int pno;
	private int psize;
	private int status = -100;
	private String type;// 活动类别
	private double minJoyFee;// 费用最小值
	private double maxJoyFee;// 费用最大值
	private int time;// 活动时间
	private String keyWord;// 搜索关键字

	private TActivity activity;

	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	@Autowired
	private ITActivity iTActivity;
	@Autowired
	private ITDictService iDict;
	@Autowired
	private ITAtUsersService atUserService;

	private Logger logger = Logger.getLogger(TActivityAction.class);

	/**
	 * 新建活动
	 */
	@LoginAccess
	@Action(value = "input", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/input.jsp") })
	public String input() {
		List<TDict> dtypes = iDict.selectAllSubOptions(DICT_AT_TYPE);
		ActionContext.getContext().put("dtypes", dtypes);
		List<TDict> address = iDict.findDictByType(DICT_ADDRESS);
		ActionContext.getContext().put("address", address);
		return C_INPUT;
	}

	/**
	 * 编辑活动
	 */
	@Action(value = "edit", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/input.jsp") })
	public String edit() {
		logger.debug("编辑活动");
		ActionContext.getContext().put("editOpt", 1);
		List<TDict> dtypes = iDict.selectAllSubOptions(DICT_AT_TYPE);
		ActionContext.getContext().put("dtypes", dtypes);

		TActivity at = iTActivity.getActivityById(activity.getId());
		if (null != at) {
			activity = at;
		} else {
			activity = null;
		}
		ActionContext.getContext().put("activity", at);

		return C_INPUT;
	}

	/**
	 * 活动管理
	 * 
	 * @return
	 * @author xujun
	 */
	@LoginAccess
	@AdminAccess
	@Action(value = "audit", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/mgr.jsp") })
	public String audit() {

		return C_INPUT;
	}

	/**
	 * 已报名活动
	 * 
	 * @return
	 * @author xujun
	 */
	@LoginAccess
	@Action(value = "joinAt", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/joinAt.jsp") })
	public String joinAt() {
		ActivityPage page = new ActivityPage(1);
		page.setUid(this.getLoginUserId());
		int total = atUserService.selectJoinAtCount(page);
		ActionContext.getContext().put("totalPages", total);
		return C_INPUT;
	}

	/**
	 * 已收藏
	 * 
	 * @return
	 * @author xujun
	 */
	@LoginAccess
	@Action(value = "myCollection", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/mycollect.jsp") })
	public String mycollect() {

		return C_INPUT;
	}

	/**
	 * 成员管理
	 * 
	 * @return
	 * @author xujun
	 */
	@LoginAccess
	@Action(value = "partiList", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/partiList.jsp") })
	public String partiList() {
		logger.debug("成员管理页面");
		int partiPersons = 0;
		int atNum = 0;
		String atName = "";
		List<PartiUserInfo> data = new ArrayList<PartiUserInfo>();

		logger.debug("获取参加人员列表");
		try {
			TActivity findAt = iTActivity.getActivityById(activity.getId());
			if (null != findAt) {
				atNum = findAt.getPnum();
				atName = findAt.getName();
			}
			data = atUserService.selectPartiUsersList(activity.getId());

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < data.size(); i++) {
				partiPersons += data.get(i).getPartiNum();
				data.get(i).setPartiTimeStr(
						format.format(data.get(i).getPartiTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ActionContext.getContext().put("atid", activity.getId());
		ActionContext.getContext().put("partiUsers", data);
		ActionContext.getContext().put("atNum", atNum);
		ActionContext.getContext().put("partiPersons", partiPersons);
		ActionContext.getContext().put("atName", atName);
		return C_INPUT;
	}

	/**
	 * 搜索
	 * 
	 * @return
	 * @author xujun
	 */
	@Action(value = "search", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/search.jsp") })
	public String search() {
		logger.debug("搜索活动页面");
		try {
			if (null != this.keyWord)
				this.keyWord = new String(this.keyWord.getBytes("iso8859-1"),
						"UTF-8");
			logger.debug("搜索关键字:" + keyWord);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ActionContext.getContext().put("keyword", this.keyWord);
		return C_INPUT;
	}

	/**
	 * 搜索活动数据
	 * 
	 * @return
	 * @author xujun
	 */
	@Action(value = "searchData", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String searchData() {
		logger.debug("搜索活动");
		int code = -1;
		String msg = MSG_FAILURE;
		List<TActivity> data = null;

		if (null == this.keyWord) {
			ActionContext.getContext()
					.put(JSON_DATA, jsonData(code, msg, data));
			return C_SUCCESS;
		}
		ActivityPage page = new ActivityPage(this.pno, this.psize);

		return C_SUCCESS;
	}

	/**
	 * 已组织活动
	 * 
	 * @return
	 * @author xujun
	 */
	@LoginAccess
	@Action(value = "organize", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/organize.jsp") })
	public String organize() {
		int cuid = getLoginUserId();
		int count = iTActivity.getOrgActivitiesWithPagesCount(cuid);
		ActionContext.getContext().put("totalPages", count);
		return C_INPUT;
	}

	@LoginAccess
	@Action(value = "orgAtList", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String orgAtList() {
		logger.debug("查询已组织活动!");
		int code = -1;
		String msg = MSG_FAILURE;
		List<TActivity> data = null;

		int cuid = getLoginUserId();
		try {
			ActivityPage p = new ActivityPage(pno, psize);
			p.setUid(cuid);
			data = iTActivity.getOrgActivitiesWithPages(p);
			code = R_SUCCESS;
			msg = MSG_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询已组织活动失败!");
		}

		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, data));
		return C_SUCCESS;
	}

	@LoginAccess
	@AdminAccess
	@Action(value = "allAtCount", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String allActivityCount() {
		logger.debug("查询所有活动总数");
		int code = -1;
		String msg = MSG_FAILURE;
		int data = 0;

		try {
			ActivityPage p = new ActivityPage(pno, psize);
			if (status != -100) {
				p.setStatus(status);
			}
			data = iTActivity.getAllActivitiesCount(p);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询所有活动总数失败!");
		}

		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, data));
		return C_SUCCESS;
	}

	@LoginAccess
	@AdminAccess
	@Action(value = "allAtList", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String allActivity() {
		logger.debug("查询所有活动!");
		int code = -1;
		String msg = MSG_FAILURE;
		List<TActivity> data = null;

		try {
			ActivityPage p = new ActivityPage(pno, psize);
			if (status != -100) {
				p.setStatus(status);
			}
			data = iTActivity.getAllActivitiesWithPages(p);
			code = R_SUCCESS;
			msg = MSG_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询所有活动失败!");
		}

		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, data));
		return C_SUCCESS;
	}

	@LoginAccess
	@AdminAccess
	@Action(value = "updateStatus", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String updateActivityStatus() {
		logger.debug("审核通过或不通过活动!");
		int code = -1;
		String msg = MSG_FAILURE;

		try {
			if (activity.getId() > 0
					&& (activity.getStatus() == -2 || activity.getStatus() == 1)) {
				int i = iTActivity.updateActivityStatus(activity);
				logger.debug("审核状态值:" + activity.getStatus());
				if (i > 0) {
					logger.debug("审核通过或审核不通过成功!");
					code = 1;
					msg = MSG_SUCCESS;
				} else {
					logger.error("审核通过或审核不通过失败!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("审核通过或审核不通过失败!");
		}

		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, null));
		return C_SUCCESS;
	}

	@Action(value = "atlist", results = { @Result(name = C_INPUT, location = "/WEB-INF/content/activity/list.jsp") })
	public String list_input() {
		List<TDict> dtypes = iDict.selectAllSubOptions(DICT_AT_TYPE);
		ActionContext.getContext().put("dtypes", dtypes);
		return C_INPUT;
	}

	@LoginAccess
	@Action(value = "upload", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String upload() {
		logger.debug("上传文件");
		int code = -1;
		Map map = getRequestMap(ServletActionContext.getRequest());
		if (null == this.upload) {
			logger.error("文件为空!!!");
			ActionContext.getContext().put(JSON_DATA,
					jsonData(code, "没有文件!", null));
			return C_SUCCESS;
		}

		String tmp_name = this.upload.getName();
		logger.debug("上传的文件临时名称:" + tmp_name);

		logger.debug("原始文件名称:" + this.uploadFileName);

		String absolutePath = ServletActionContext.getServletContext()
				.getRealPath("");
		String save_file_name = getCurDate("yyyyMMddHHmmssSSS") + "_"
				+ this.uploadFileName;

		String save_file_path = PATH_ACTIVITY_POSTER_BASE + save_file_name;
		String path = absolutePath + save_file_path;

		logger.debug("保存文件绝对路径:" + path);

		// save
		boolean save_result = saveFile(path, this.upload);

		if (save_result) {
			code = R_SUCCESS;
			logger.debug("上传文件成功!");
			JSONObject data = new JSONObject();
			data.put(FILE_PATH, save_file_path);
			data.put(CONTEXT_PATH, ServletActionContext.getRequest()
					.getContextPath());
			ActionContext.getContext().put(JSON_DATA,
					jsonData(code, "上传成功!", data));
			return C_SUCCESS;
		}

		logger.debug("上传文件失败!");
		ActionContext.getContext()
				.put(JSON_DATA, jsonData(code, "上传失败!", null));
		return C_SUCCESS;
	}

	@LoginAccess
	@Action(value = "add", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String add() {
		logger.debug("添加活动");
		int code = -1;
		String msg = MSG_FAILURE;

		activity.setCuid(getLoginUserId());
		activity.setCdatetime(DateTool.getCurrentDate());
		int i = this.iTActivity.insertActivity(activity);
		if (i > 0) {
			code = 0;
			msg = MSG_SUCCESS;

			logger.debug("新建活动成功!id=" + activity.getId() + "--->" + i);
		} else {
			logger.error("新建活动失败!");
		}
		ActionContext.getContext().put(JSON_DATA,
				this.jsonData(code, msg, null));
		return C_SUCCESS;
	}

	@LoginAccess
	@Action(value = "update", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String update() {
		logger.debug("更新活动");
		int code = -1;
		String msg = MSG_FAILURE;

		if (null != activity || activity.getId() != -1) {
			try {
				int i = iTActivity.updateActivity(activity);
				if (i > 0) {
					logger.debug("更新活动成功");
					code = R_SUCCESS;
					msg = MSG_SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("更新活动失败!id=" + activity.getId());
			}
		}
		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, null));
		return C_SUCCESS;
	}

	@Action(value = "detail", results = { @Result(name = C_DETAIL, location = "/WEB-INF/content/activity/detail.jsp") })
	public String detail() {
		logger.debug("查看活动详细!activity id=" + activity.getId());
		TActivity at = null;
		try {
			at = iTActivity.getActivityById(activity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			at = null;
		}
		if (null != at) {
			activity = at;
			logger.debug("查看详细成功!" + at.getName());

		} else {
			activity = null;
			logger.error("查看详细失败!");
		}
		ActionContext.getContext().put("activity", at);
		return C_DETAIL;
	}

	@Action(value = "list", results = { @Result(name = C_JSON, type = C_JSON) })
	public String list() {
		logger.debug("查询活动列表");
		if (null != keyWord) {
			try {
				keyWord = new String(this.keyWord.getBytes("iso8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ActivityPage page = new ActivityPage(this.pno, this.psize);
		this.pno = page.getNo();
		this.psize = page.getSize();
		page.setTime(time);
		page.setType(type);
		page.setMinJoyFee(minJoyFee);
		page.setMaxJoyFee(maxJoyFee);
		page.setKeyWord(keyWord);

		logger.debug("类型:" + type);
		try {
			this.list = iTActivity.getActivitiesWithPages(page);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return C_JSON;
	}

	private boolean saveFile(String filename, File file) {
		boolean result = false;
		try {
			FileUtils.copyFile(file, new File(filename));
			result = true;
			logger.debug("保存上传文件成功!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("保存上传文件失败!");
		}
		return result;
	}

	private Map getRequestMap(HttpServletRequest request) {
		Map map = new HashMap<String, Object>();
		Map<String, String[]> map2 = request.getParameterMap();
		for (Object k : map2.keySet()) {
			String[] v1 = map2.get(k);
			if (null != v1 && v1.length == 1) {
				map.put(k, v1[0]);
			}
		}
		return map;
	}

	private String getCurDate(String pattern) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(cal.getTime());
	}

	public List<TActivity> getList() {
		return list;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int getPsize() {
		return psize;
	}

	public void setPsize(int psize) {
		this.psize = psize;
	}

	@JSON(serialize = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JSON(serialize = false)
	public double getMinJoyFee() {
		return minJoyFee;
	}

	public void setMinJoyFee(double minJoyFee) {
		this.minJoyFee = minJoyFee;
	}

	@JSON(serialize = false)
	public double getMaxJoyFee() {
		return maxJoyFee;
	}

	public void setMaxJoyFee(double maxJoyFee) {
		this.maxJoyFee = maxJoyFee;
	}

	@JSON(serialize = false)
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@JSON(serialize = false)
	public TActivity getActivity() {
		return activity;
	}

	public void setActivity(TActivity activity) {
		this.activity = activity;
	}

	@JSON(serialize = false)
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	@JSON(serialize = false)
	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	@JSON(serialize = false)
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@JSON(serialize = false)
	public int getOptType() {
		return optType;
	}

	public void setOptType(int optType) {
		this.optType = optType;
	}

	@JSON(serialize = false)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@JSON(serialize = false)
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
