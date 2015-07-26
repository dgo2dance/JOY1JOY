package com.joy1joy.app.dao;

import java.util.Map;

import com.joy1joy.app.bean.TUsers;

public interface TUsersMapper
{


	/**
	 * 添加用户
	 * @param tusers
	 * @return
	 */
	int insertTUsers(TUsers tusers);

	/**
	 * 根据用户名查找用户
	 * @param userid
	 * @return
	 */
	TUsers selectUserByName(String userid);
	
	/**
	 * 根据手机号查找用户
	 * @param userid
	 * @return
	 */
	TUsers selectUserByPhone(String mobile);
	/**
	 * 根据用户名或者手机号查找用户
	 * @param userid
	 * @return
	 */
	TUsers selectUserByNameOrPhone(Map map);
	
	/**
	 * 更新用户基本信息
	 * @param tusers
	 * @return
	 */
	int updateByPrimaryKey(TUsers tusers);
}