package com.joy1joy.app.actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fckeditor.requestcycle.impl.ContextPathBuilder;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.joy1joy.app.actions.base.BaseAction;
import com.joy1joy.app.bean.ActivityPage;
import com.joy1joy.app.bean.JoinAt;
import com.joy1joy.app.bean.PartiUserInfo;
import com.joy1joy.app.bean.TActivity;
import com.joy1joy.app.bean.TAtUsers;
import com.joy1joy.app.core.annotation.LoginAccess;
import com.joy1joy.app.service.ITActivity;
import com.joy1joy.app.service.ITAtUsersService;
import com.opensymphony.xwork2.ActionContext;

/*
 *
 *@author:xujun
 */
@Namespace("/join")
public class TAtUsersAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(TAtUsersAction.class);

	private int pno;
	private int psize;
	private String ids;
	private int atid;

	private TAtUsers atUser;
	@Autowired
	private ITActivity iTActivity;
	@Autowired
	private ITAtUsersService atUserService;

	@LoginAccess
	@Action(value = "add", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String add() {
		logger.debug("报名活动");
		int code = -1;
		String msg = MSG_FAILURE;

		try {
			if (null != atUser) {
				atUser.setUid(getLoginUserId());

				TAtUsers find = atUserService.selectTAtUser(atUser);
				if (null != find) {
					code = 1;// 已报名
					ActionContext.getContext().put(JSON_DATA,
							this.jsonData(code, msg, null));
					return C_SUCCESS;
				}
				// 未报名
				TActivity at = iTActivity.getActivityById(atUser.getAtid());
				if (null == at) {
					code = 2;// 活动不存在
					ActionContext.getContext().put(JSON_DATA,
							this.jsonData(code, msg, null));
					return C_SUCCESS;
				}

				if (at.getStatus() == 2 || at.getStatus() == 3
						|| at.getStatus() == 4) {// 名额已满、已结束、或者已关闭
					code = 3;
					ActionContext.getContext().put(JSON_DATA,
							this.jsonData(code, msg, null));
					return C_SUCCESS;
				}
				if (at.getStatus() != 1) {// 活动状态不对
					code = 4;
					ActionContext.getContext().put(JSON_DATA,
							this.jsonData(code, msg, null));
					return C_SUCCESS;
				}
				// 报名
				// 转码
				String s=java.net.URLDecoder.decode(this.atUser.getRemark(),"UTF-8");
				 this.atUser.setRemark(s);
				int i = atUserService.insertTAtUser(atUser);
				if (i > 0) {// 报名成功
					code = 0;
					msg = MSG_SUCCESS;
					logger.debug("报名成功");
					// 更新状态
					int joinCount = atUserService.getAtJoinCount(atUser
							.getAtid());
					if (joinCount >= at.getPnum()) {
						at.setStatus(2);

						int u = iTActivity.updateAtStatus(at);
						if (u < 1) {
							logger.error("更新活动状态为名额已满出错!");
						} else {
							logger.error("更新活动状态为名额已满成功!");
						}
					}
				} else {
					code = -1;
					logger.error("报名失败!");
				}

			}
		} catch (Exception e) {
			code = -1;
			e.printStackTrace();
		}
		ActionContext.getContext().put(JSON_DATA,
				this.jsonData(code, msg, null));
		return C_SUCCESS;
	}

	@Action(value = "recent", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String recent() {
		logger.debug("查询最近的报名记录");
		int code = -1;
		String msg = MSG_FAILURE;

		List<Map> data = null;
		try {
			data = atUserService.selectRecent();
			for (Map m : data) {
				Date d = (Date) m.get("addtime");
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyyMMdd HH:mm:ss");
				m.put("addtime", format.format(d));
			}
			code = 1;
			msg = MSG_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, data));
		return C_SUCCESS;
	}

	@LoginAccess
	@Action(value = "joinList", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String joinList() {
		logger.debug("查询报名记录");
		int code = -1;
		String msg = MSG_FAILURE;

		List<JoinAt> data = null;
		try {
			ActivityPage page = new ActivityPage(pno, psize);
			page.setUid(this.getLoginUserId());
			data = atUserService.selectJoinAt(page);
			code = 1;
			msg = MSG_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionContext.getContext().put(JSON_DATA, jsonData(code, msg, data));
		return C_SUCCESS;
	}

	/**
	 * 导出成员列表
	 * 
	 * @return
	 * @author xujun
	 */
	@Action(value = "export", results = { @Result(name = C_SUCCESS, location = "/WEB-INF/content/base/JSON.jsp") })
	public String exportData() {
		logger.debug("导出成员列表");
		int code = -1;
		String msg = MSG_FAILURE;
		String path = null;

		if (null == ids || ids.trim().equals("") || ids.split(",").length < 1) {
			ActionContext.getContext()
					.put(JSON_DATA, jsonData(code, msg, path));
			return C_SUCCESS;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", atid);
		map.put("ids", ids.split(","));
		try {

			List<PartiUserInfo> list = atUserService
					.selectExportPartiUsers(map);
			String baseDir = System.getProperty("joyHome");
			logger.debug("webRoot.dir=" + baseDir);
			path = toFile(list, baseDir);
			if (null != path) {
				code = 1;
				msg = MSG_SUCCESS;
			}

			ActionContext.getContext()
					.put(JSON_DATA, jsonData(code, msg, path));
			logger.debug("导出成员列表成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导出成员列表失败!");
		}
		return C_SUCCESS;
	}

	public String toFile(List<PartiUserInfo> list, String baseDir) {
		String path = null;

	//	String[] headers = { "用户Id", "报名时间", "报名人数", "手机号", "报名费用","备注" };
		String[] headers = { "用户Id", "报名时间", "报名人数", "联系方式", "报名费用","备注","身份证号"};
		
		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet sh = wb.createSheet("报名列表");
		// 标题
		Row r = sh.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = r.createCell(i);
			String v = headers[i];
			cell.setCellValue(v);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int k = 0; k < list.size(); k++) {
			PartiUserInfo u = list.get(k);
			Row row = sh.createRow(k + 1);

			String userid = u.getUserid();
			String partiTime = format.format(u.getPartiTime());
			String partiNum = u.getPartiNum() + "";
			String mobile = u.getMobile();
			String fee = u.getJoyFee() + "";
			String remark = u.getRemark();
			String iphone = u.getIphone();
			String idcard = u.getIdcard();
		//	String[] data = { userid, partiTime, partiNum, mobile, fee, remark, iphone, idcard};
			String[] data = { userid, partiTime, partiNum, iphone, fee, remark, idcard};
			
			for (int m = 0; m < data.length; m++) {
				Cell cell = row.createCell(m);
				String v="";
				if(data[m]!=null){
				v = data[m];
				}
				cell.setCellValue(v);
			}
		}

		FileOutputStream out = null;
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
			String absolutePath = "/download/" + f.format(new Date()) + ".xlsx";
			String filePath = baseDir + absolutePath;
			out = new FileOutputStream(filePath);
			wb.write(out);
			out.close();
			path = absolutePath;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != wb) {
				try {
					wb.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return path;
	}

	public TAtUsers getAtUser() {
		return atUser;
	}

	public void setAtUser(TAtUsers atUser) {
		this.atUser = atUser;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getAtid() {
		return atid;
	}

	public void setAtid(int atid) {
		this.atid = atid;
	}

}
