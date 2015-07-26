package com.joy1joy.app.dao;

import java.util.List;

import com.joy1joy.app.bean.CollectAtResult;
import com.joy1joy.app.bean.TAtCollect;

/*
 *
 *@author:xujun
 */
public interface TAtCollectMapper {
	public int insertTAtCollect(TAtCollect collection);

	public int deleteTAtCollect(int id);

	public TAtCollect selectTAtCollect(TAtCollect collect);

	public List<CollectAtResult> selectByUserid(int uid);
}
