package com.joy1joy.app.serviceImpl;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy1joy.app.bean.TUsers;
import com.joy1joy.app.dao.TUsersMapper;
import com.joy1joy.app.service.ITUserService;

/** 
 * <p>Title: TUserServiceImpl.java</p>
 * 
 * <p>Description: </p>
 * 
 * <p>Date: 2015-4-20</p>
 * 
 * <p>Time: 上午10:10:10</p>
 * 
 * <p>Copyright: 2015</p>
 * 
 * <p>Company: yt</p>
 * 
 * @author 张亚博
 * @version 1.0
 * 
 * <p>============================================</p>
 * <p>Modification History
 * <p>Mender: </p>
 * <p>Date: </p>
 * <p>Reason: </p>
 * <p>============================================</p>
 */
/**
 * 用户管理实现类
 */
@Service
//声明此类为业务逻辑层的类
public class TUserServiceImpl  implements ITUserService {

	private static Logger log=Logger.getLogger(TUserServiceImpl.class);
	
	@Autowired
	private TUsersMapper userMapper;

	@Override
	public boolean addUsers(TUsers users) {
		try {
			userMapper.insertTUsers(users);
		} catch (Exception e) {
			log.error("********【新增用户失败】*****，原因：", e);
			return false;
		}
		return true;
	}

	@Override
	public TUsers findUserByName(String userName) {
		TUsers users=null;
		try {
			users=userMapper.selectUserByName(userName);
		} catch (Exception e) {
			log.error("********【查询用户"+userName+"失败】*****，原因：", e);
		}
		return users;
	}

	@Override
	public TUsers findUserByNameOrPhone(String userName, String userPhone) {
		TUsers users=null;
		try {
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("userid", userName);
			m.put("mobile", userPhone);
			users=userMapper.selectUserByNameOrPhone(m);
		} catch (Exception e) {
			log.error("********【查询用户名或者手机"+userName+","+userPhone+"失败】*****，原因：", e);
		}
		return users;
	}

	@Override
	public TUsers findUserByPhone(String userPhone) {
		TUsers users=null;
		try {
			users=userMapper.selectUserByPhone(userPhone);
		} catch (Exception e) {
			log.error("********【查询用户手机"+userPhone+"失败】*****，原因：", e);
		}
		return users;
	}

	@Override
	public boolean updateUsers(TUsers users) {
		try {
			userMapper.updateByPrimaryKey(users);
		} catch (Exception e) {
			log.error("********【更新用户失败】*****，原因：", e);
			return false;
		}
		return true;
	}


	
	
	
	
	
	

}
