package com.joy1joy.app.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy1joy.app.bean.Option;
import com.joy1joy.app.bean.TDict;
import com.joy1joy.app.dao.TDictMapper;
import com.joy1joy.app.service.ITDictService;

/** 
 * <p>Title: TDictServiceImpl.java</p>
 * 
 * <p>Description: </p>
 * 
 * <p>Date: 2015-4-20</p>
 * 
 * <p>Time: 上午10:10:10</p>
 * 
 * <p>Copyright: 2015</p>
 * 
 * @author boyd
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
 * 字典类
 */
@Service
// 声明此类为业务逻辑层的类
public class TDictServiceImpl implements ITDictService {

	private static Logger log = Logger.getLogger(TDictServiceImpl.class);

	@Autowired
	private TDictMapper dictMapper;

	@Override
	public List<TDict> findDictByType(String dtype) {
		List<TDict> dicts = null;
		try {
			dicts = dictMapper.selectDictByType(dtype);
		} catch (Exception e) {
			log.error("********【查询" + dtype + "的字典失败】*****，原因：", e);
		}
		return dicts;
	}

	/**
	 * 查询子字典选项
	 * 
	 * @param dtype
	 * @param fdkey
	 * @return
	 * @author:xujun
	 */
	@Override
	public List<Option> selectSubOptions(TDict dict) {
		// TODO Auto-generated method stub
		return dictMapper.selectSubOptions(dict);
	}

	/**
	 * 根据类型查找字典
	 * 
	 * @param dtype
	 * @return
	 * @author:xujun
	 */
	@Override
	public List<Option> selectOptions(String dtype) {
		// TODO Auto-generated method stub
		return dictMapper.selectOptions(dtype);
	}

	/**
	 * 获取所有的子选项
	 * 
	 * @param dtype
	 * @return
	 * @author:xujun
	 */
	@Override
	public List<TDict> selectAllSubOptions(String dtype) {
		// TODO Auto-generated method stub
		return dictMapper.selectAllSubOptions(dtype);
	}

}
