package com.joy1joy.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy1joy.app.bean.CollectAtResult;
import com.joy1joy.app.bean.TAtCollect;
import com.joy1joy.app.dao.TAtCollectMapper;
import com.joy1joy.app.service.ITAtCollectService;

/*
 *
 *@author:xujun
 */
@Service
public class TAtCollectImpl implements ITAtCollectService {

	@Autowired
	private TAtCollectMapper mapper;

	@Override
	public int insertTAtCollect(TAtCollect collection) {
		// TODO Auto-generated method stub
		return mapper.insertTAtCollect(collection);
	}

	@Override
	public int deleteTAtCollect(int id) {
		// TODO Auto-generated method stub
		return mapper.deleteTAtCollect(id);
	}

	@Override
	public TAtCollect selectTAtCollect(TAtCollect collect) {
		// TODO Auto-generated method stub
		return mapper.selectTAtCollect(collect);
	}

	@Override
	public List<CollectAtResult> selectByUserid(int uid) {
		// TODO Auto-generated method stub
		return mapper.selectByUserid(uid);
	}

}
