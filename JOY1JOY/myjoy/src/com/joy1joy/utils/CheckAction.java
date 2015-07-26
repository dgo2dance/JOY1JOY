package com.joy1joy.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CheckAction extends BodyTagSupport {
	
	private static final Log log = LogFactory.getLog(CheckAction.class);
	
	private static final long serialVersionUID = 1L;
	private String akey;//权限关键字
	public String getAkey() {
		return akey;
	}

	public void setAkey(String akey) {
		this.akey = akey;
	}

	@Override
	public int doEndTag() throws JspException {
//		JspWriter out = pageContext.getOut();
//		try {
//			Roles role = (Roles)pageContext.getSession().getAttribute("role");
//			String  actions = role.getActions();
//			String colactions = role.getColactions();
//			String rolekey = role.getRolekey();
//			if(rolekey!=null){
//				if(rolekey.equals("8888")||rolekey.equals("7777")){
//					out.println(bodyContent.getString());
//				}
//				else{
//					if(rolekey.equals("6666")){
//						if(akey.trim().length()==33){
//							out.println(bodyContent.getString());
//						}
//					}
//				}
//					
//			}
//			else{
//				if(actions!=null&&!actions.equals("")){
//					String [] action= actions.split(",");
//					for(String act:action){
//						if(act.equals(akey.trim())){
//							out.println(bodyContent.getString());
//						}
//					}
//				}
//				if(colactions!=null&&!colactions.equals("")){
//					String [] colaction= colactions.split(",");
//					for(String act:colaction){
//						//System.out.println("++++++++++++++++++"+act+"==="+akey);
//						if(act.equals(akey.trim())){
//							out.println(bodyContent.getString());
//						}
//					}
//				}
//			}
//		} catch (IOException e) {
//			log.error("操作权限验证权限失败!!!", e);
//		}
		return EVAL_PAGE;
	}
	@Override
	public void setBodyContent(BodyContent arg0) {
		// TODO Auto-generated method stub
		super.setBodyContent(arg0);
	}

}
