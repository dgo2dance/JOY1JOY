package com.joy1joy.app.dao;

import java.util.List;
import java.util.Map;

import com.joy1joy.app.bean.TNotices;

public interface TNoticesMapper {

	/**
	 * 添加公告
	 * 
	 * @param tNotices
	 * @return
	 */
	int insertTNotices(TNotices tNotices);

	/**
	 * 查找公告
	 * 
	 * @return
	 */
	List<TNotices> selectTNoticesList();

	/**
	 * 分页查询公告
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<TNotices> selectTNoticesByLimit(Map map);

	/**
	 * 查询公告的总数
	 * 
	 * @return
	 */
	int selectTNoticesCount(Map map);

	/**
	 * 根据id查询公告
	 * 
	 * @param id
	 * @return
	 */
	TNotices selectTNoticesById(Integer id);

	public List<TNotices> selectRecentNotices(Map map);
}