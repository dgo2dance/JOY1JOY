package com.joy1joy.app.service;

import java.util.List;

import com.joy1joy.app.bean.Option;
import com.joy1joy.app.bean.TDict;

/**
 * <p>
 * Title: IDcitService.java
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
 * Time: 上午10:10:10
 * </p>
 * 
 * <p>
 * Copyright: 2015
 * </p>
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
public interface ITDictService {

	public List<TDict> findDictByType(String dtype);

	/**
	 * 查询子字典选项
	 * 
	 * @param dtype
	 * @param fdkey
	 * @return
	 * @author:xujun
	 */
	List<Option> selectSubOptions(TDict dict);

	/**
	 * 根据类型查找字典
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
