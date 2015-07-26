﻿package com.joy1joy.app.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.joy1joy.app.actions.base.BaseAction;
import com.joy1joy.app.bean.TUsers;
import com.joy1joy.app.core.annotation.LoginAccess;
import com.joy1joy.app.service.ITUserService;
import com.joy1joy.utils.DateTool;
import com.joy1joy.utils.PasswordMD5;
import com.joy1joy.utils.SendMobileCode;
import com.joy1joy.utils.StringTool;
import com.joy1joy.utils.VerifyCodeUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * <p>
 * Title: TUsersAction.java
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Date: 2015-4-20
 * </p>
 * 
 * <p>
 * Time: 上午11:42:57
 * </p>
 * 
 * <p>
 * Copyright: 2015
 * </p>
 * 
 * 
 * @author boyd
 * @version 1.0
 * 
 *          <p>=
 *          ===========================================
 *          </p>
 *          <p>
 *          Modification History
 *          <p>
 *          Mender:
 *          </p>
 *          <p>
 *          Date:
 *          </p>
 *          <p>
 *          Reason:
 *          </p>
 *          <p>=
 *          ===========================================
 *          </p>
 */
@Results({
		@Result(name = "success", location = "/WEB-INF/content/user/success.jsp"),
		@Result(name = "success", location = "/WEB-INF/content/user/fail.jsp") })
@Namespace(value = "/user")
public class TUsersAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 16 * 1024;
	private Logger logger = Logger.getLogger(TUsersAction.class);
	private String uid;
	private String userId;// 用户名
	private String password;

	private String userPhone;

	private String gender;
	private String state;
	private String qq;
	private String birthday;
	private String email;
	private String icon;
	private File myFile;
	private String contentType;
	private String fileName;
	private String imageFileName;
	private String imageNum;
	private String phoneCode;
	// 引入业务层
	@Autowired
	ITUserService userService;

	@Action(value = "jumpLogin", results = { @Result(name = "success", location = "/WEB-INF/content/user/login.jsp") })
	public String toLogin() {
		return C_SUCCESS;
	}

	@Action(value = "genericImage")
	public void genericImage() throws Exception  {
		getResponse().setHeader("Pragma", "No-cache");  
		getResponse().setHeader("Cache-Control", "no-cache");  
		getResponse().setDateHeader("Expires", 0);  
		getResponse().setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session  
        //System.out.println("-----verifyCode:"+verifyCode.toLowerCase());
        session.put("imageCode","");
        session.put("imageCode", verifyCode.toLowerCase());  
        //System.out.println("-------------"+session.get("imageCode"));
        //生成图片  
        int w = 200, h = 80;  
        VerifyCodeUtils.outputImage(w, h, getResponse().getOutputStream(), verifyCode); 
        //ActionContext.getContext().getSession().clear();
       
	}
	
	@Action(value = "refreshCode")
	public void refreshCode() throws Exception {
		PrintWriter out = null;
		JSONObject jsonObject = new JSONObject();
		try {
			out = getResponse().getWriter();
			String code=(String)session.get("imageCode");
			jsonObject.put("result",code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.error("新增用户失败!");
			jsonObject.put("result", "");
		}

		out.print(jsonObject.toString());
	}

	@Action(value = "register", results = { @Result(name = "success", location = "/WEB-INF/content/user/register.jsp") })
	public String register(){
		return C_SUCCESS;
	}

	@LoginAccess
	@Action(value = "jumpUserDetail", results = { @Result(name = "success", location = "/WEB-INF/content/user/userModify.jsp") })
	public String toUserDetail() {
		TUsers t_user = (TUsers) session.get("users");
		t_user = this.userService.findUserByPhone(t_user.getMobile());
		ActionContext.getContext().put("tUsers", t_user);
		return C_SUCCESS;
	}

	@Action(value = "saveUser")
	public void save() throws Exception {
		PrintWriter out = null;
		JSONObject jsonObject = new JSONObject();
		try {
			out = getResponse().getWriter();
			String image_code=(String)session.get("imageCode");
			if(!imageNum.toLowerCase().equals(image_code))
			{
				jsonObject.put("result", "codeFail");
			}else
			{
				TUsers users = new TUsers();
				users.setUserid(userPhone);
				users.setMobile(userPhone);
				users.setPassword(PasswordMD5.createEncryptPSW(password));
				users.setStatus(TUsers.USER_STATUE_NORMAL);
				users.setType(TUsers.USER_COMMON);
				users.setIcon("/images/userHeadImg/default.png");
				boolean isResult = this.userService.addUsers(users);
				if (isResult) {
					jsonObject.put("result", "success");
				} else {
					jsonObject.put("result", "fail");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("新增用户失败!");
			jsonObject.put("result", "fail");
		}

		out.print(jsonObject.toString());
	}

	@Action(value = "login")
	public void login() {

		PrintWriter out = null;
		JSONObject jsonObject = new JSONObject();
		try {
			out = getResponse().getWriter();
			TUsers users = this.userService.findUserByNameOrPhone(userId,
					userPhone);
			if (null != users) {
				if (users.getPassword().equals(PasswordMD5.createEncryptPSW(password))) {

					session.put("users", users);
					jsonObject.put("result", "success");
				} else {
					jsonObject.put("result", "passError");
				}

			} else {
				jsonObject.put("result", "noExit");
			}
		} catch (Exception e) {
			jsonObject.put("result", "fail");
			logger.error("登录失败!");
			e.printStackTrace();
		}
		out.print(jsonObject.toString());
	}

	@Action(value = "findUser")
	public void find() {

		PrintWriter out = null;
		JSONObject jsonObject = new JSONObject();
		try {
			out = this.getResponse().getWriter();
			TUsers users = this.userService.findUserByPhone(userPhone);
			if (null != users) {

				jsonObject.put("result", "exit");

			} else {
				jsonObject.put("result", "noExit");
			}
		} catch (Exception e) {
			jsonObject.put("result", "fail");
			logger.error("查询用户失败!");
			e.printStackTrace();
		}
		out.print(jsonObject.toString());
	}

	@LoginAccess
	@Action(value = "updateUser", results = {
			@Result(name = "success", type = "redirectAction", location = "jumpUserDetail.action"),
			@Result(name = "error", location = "/WEB-INF/content/user/register.jsp") })
	public String update() {
		try {
			TUsers user = (TUsers) session.get("users");
			user.setUid(user.getUid());
			user.setUserid(userId);
			user.setBirthdate(DateTool.stringToDate(birthday,
					DateTool.DATE_STYLE_SIMPLE));
			user.setEmail(email);
			user.setQq(qq);
			user.setState(state);
			//user.setUserid(userId);
			user.setGender(Integer.valueOf(gender));
			if (fileName != null && !fileName.equals("")) {
				String imageFileName = new Date().getTime()
						+ getExtention(fileName);
				String uploadPath = ServletActionContext.getServletContext().getRealPath("images/userHeadImg");   //设置保存目录  
		        File folder=new File(uploadPath);
				if(!folder.exists()&& !folder .isDirectory())
				{
					folder.mkdir();
				}
				File imageFile = new File(uploadPath
						+ "/" + imageFileName);
				String filecopy = copy(myFile, imageFile);
				if (filecopy.equals("1")) {
					user.setIcon("/images/userHeadImg/" + imageFileName);
				}
			}

			boolean res=this.userService.updateUsers(user);
			if(res)
			{
				session.remove("users");
				session.put("users", user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新用户失败!");
			return C_ERROR;
		}
		return C_SUCCESS;
	}

	private String copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
			return "1";
		} catch (Exception e) {
			return "0";
		}
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	
	@Action(value = "protocol", results = {
			@Result(name = "success",  location = "/WEB-INF/content/agreement.jsp")})
	public String protocol() {
		return C_SUCCESS;
	}
	
	@Action(value = "exit", results = {
			@Result(name = "success", type = "redirectAction", location = "../home.action")})
	public String exit()
	{
		session.remove("users");
		return C_SUCCESS;
	}
	
	@Action(value = "compareCode")
	public void compareCode()
	{
		PrintWriter out = null;
		JSONObject jsonObject = new JSONObject();
		try {
			out = getResponse().getWriter();

			Map userMap = (Map) session.get(userPhone);// 用户手机号
			if(null==userMap)
			{
				jsonObject.put("result", "timeout");
			}else
			{
				String userPhoneCode = (String) userMap.get("phoneCode");
				String userCodeTime = (String) userMap.get("codeTime");
				long timeDiff = DateTool.getTimeDiff(DateTool.formatDate(
						new Date(), DateTool.DATE_STYLE_BASIC), DateTool
						.stringToDate(userCodeTime, DateTool.DATE_STYLE_BASIC));
				if (timeDiff > 5) {
					jsonObject.put("result", "timeout");
				} else {
					if (userPhoneCode.equals(phoneCode)) {
						jsonObject.put("result", "success");
						session.remove(userPhone);
					} else {
						jsonObject.put("result", "codeError");
					}
				}
			}
			

		} catch (Exception e) {
			jsonObject.put("result", "fail");
			logger.error("验证码比较失败!");
			e.printStackTrace();
		}
		out.print(jsonObject.toString());
	}
	@Action(value = "sendCode")
	public void sendCode()
	{
		PrintWriter out = null;
		JSONObject jsonObject = new JSONObject();
		try {
			out = getResponse().getWriter();
			String code=StringTool.createRandom(true, 6);
			String resultValue=SendMobileCode.sendCode(userPhone, code, "5");
			if("000000".equals(resultValue))
			{
				Map userMap = new HashMap();
				userMap.put("phoneCode",code);
				userMap.put("codeTime", DateTool.dateToString(new Date(),
						DateTool.DATE_STYLE_BASIC));
				session.put(userPhone, userMap);
				jsonObject.put("result", "success");
			}else
			{
				jsonObject.put("result", "fail");
			}
			

		} catch (Exception e) {
			jsonObject.put("result", "fail");
			logger.error("发送验证码失败!");
			e.printStackTrace();
		}
		out.print(jsonObject.toString());
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setMyFileContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setMyFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getImageNum() {
		return imageNum;
	}

	public void setImageNum(String imageNum) {
		this.imageNum = imageNum;
	}

	

}