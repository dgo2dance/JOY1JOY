package com.joy1joy.app.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy1joy.app.bean.ActivityPage;
import com.joy1joy.app.bean.JoinAt;
import com.joy1joy.app.bean.PartiUserInfo;
import com.joy1joy.app.bean.TAtUsers;
import com.joy1joy.app.dao.TAtUsersMapper;
import com.joy1joy.app.service.ITAtUsersService;

/*
 *
 *@author:xujun
 */
@Service
public class TAtUsersServiceImpl implements ITAtUsersService {

	@Autowired
	private TAtUsersMapper mapper;

	@Override
	public int insertTAtUser(TAtUsers tAtUsers) {
		// TODO Auto-generated method stub
		return mapper.insertTAtUser(tAtUsers);
	}

	@Override
	public int deleteTAtUser(int id) {
		// TODO Auto-generated method stub
		return mapper.deleteTAtUser(id);
	}

	@Override
	public TAtUsers selectTAtUser(TAtUsers atUser) {
		// TODO Auto-generated method stub
		return mapper.selectTAtUser(atUser);
	}

	@Override
	public int getAtJoinCount(int atid) {
		// TODO Auto-generated method stub
		return mapper.getAtJoinCount(atid);
	}

	@Override
	public List<PartiUserInfo> selectPartiUsersList(int atid) {
		// TODO Auto-generated method stub
		return mapper.selectPartiUsersList(atid);
	}

	@Override
	public List<Map> selectRecent() {
		// TODO Auto-generated method stub
		return mapper.selectRecent();
	}

	@Override
	public int selectJoinAtCount(ActivityPage page) {
		// TODO Auto-generated method stub
		return mapper.selectJoinAtCount(page);
	}

	@Override
	public List<JoinAt> selectJoinAt(ActivityPage page) {
		// TODO Auto-generated method stub
		return mapper.selectJoinAt(page);
	}

	@Override
	public List<PartiUserInfo> selectExportPartiUsers(Map map) {
		// TODO Auto-generated method stub
		return mapper.selectExportPartiUsers(map);
	}

}
