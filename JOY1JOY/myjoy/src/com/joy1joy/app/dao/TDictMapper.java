package com.joy1joy.app.dao;

import java.util.List;

import com.joy1joy.app.bean.Option;
import com.joy1joy.app.bean.TDict;

public interface TDictMapper {

	/**
	 * 根据类型查找字典
	 * 
	 * @param dtype
	 * @return
	 */
	List<TDict> selectDictByType(String dtype);

	/**
	 * 查询字典子选项
	 * 
	 * @param dict
	 * @return
	 * @author:xujun
	 */
	List<Option> selectSubOptions(TDict dict);

	/**
	 * 查询字典选项
	 * 
	 * @param dtype
	 * @return
	 * @author:xujun
	 */
	List<Option> selectOptions(String dtype);

	/**
	 * 获取所有的子选项
	 * 
	 * @param dtype
	 * @return
	 * @author:xujun
	 */
	List<TDict> selectAllSubOptions(String dtype);
}