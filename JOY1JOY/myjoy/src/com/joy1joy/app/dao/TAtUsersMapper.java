package com.joy1joy.app.dao;

import java.util.List;
import java.util.Map;

import com.joy1joy.app.bean.ActivityPage;
import com.joy1joy.app.bean.JoinAt;
import com.joy1joy.app.bean.PartiUserInfo;
import com.joy1joy.app.bean.TAtUsers;

/*
 *
 *@author:xujun
 */
public interface TAtUsersMapper {
	public int insertTAtUser(TAtUsers tAtUsers);

	public int deleteTAtUser(int id);

	public TAtUsers selectTAtUser(TAtUsers atUser);

	public int getAtJoinCount(int atid);

	public List<PartiUserInfo> selectPartiUsersList(int atid);

	public List<Map> selectRecent();

	public int selectJoinAtCount(ActivityPage page);

	public List<JoinAt> selectJoinAt(ActivityPage page);

	public List<PartiUserInfo> selectExportPartiUsers(Map map);
}
