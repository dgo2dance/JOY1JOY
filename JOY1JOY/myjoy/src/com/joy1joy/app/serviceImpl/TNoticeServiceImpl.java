package com.joy1joy.app.serviceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy1joy.app.bean.TNotices;
import com.joy1joy.app.dao.TNoticesMapper;
import com.joy1joy.app.service.ITNoticeService;

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
 * 用户管理实现类
 */
@Service
//声明此类为业务逻辑层的类
public class TNoticeServiceImpl  implements ITNoticeService {

	private static Logger log=Logger.getLogger(TNoticeServiceImpl.class);
	
	@Autowired
	private TNoticesMapper noticeMapper;

	

	@Override
	public boolean addNotices(TNotices notices) {
		try {
			noticeMapper.insertTNotices(notices);
		} catch (Exception e) {
			log.error("********【新增公告失败】*****，原因：", e);
			return false;
		}
		return true;
	}

	@Override
	public List<TNotices> findNoticeList() {
		List<TNotices> list=null;
		try {
			list= noticeMapper.selectTNoticesList();
		} catch (Exception e) {
			log.error("********【查询公告列表失败】*****，原因：", e);
		}
		return list;
	}

	@Override
	public List<TNotices> findNoticeListByLimit(String type,int status,int offset,int limit) {
		List<TNotices> list=null;
		try {
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("type", type);
			m.put("status", status);
			m.put("offset", offset);
			m.put("limit", limit);
			list= noticeMapper.selectTNoticesByLimit(m);
		} catch (Exception e) {
			log.error("********【分页查询公告列表失败】*****，原因：", e);
		}
		return list;
	}

	@Override
	public int selectTNoticesCount(String type,int status) {
		int count=0;
		try {
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("type", type);
			m.put("status", status);
			count=noticeMapper.selectTNoticesCount(m);
		} catch (Exception e) {
			log.error("********【查询公告总数失败】*****，原因：", e);
			return 0;
		}
		return count;
	}

	@Override
	public TNotices selectTNoticesById(Integer id) {
		TNotices notices=null;
		try {
			notices=noticeMapper.selectTNoticesById(id);
		} catch (Exception e) {
			log.error("********【查询公告详细失败】*****，原因：", e);
			return null;
		}
		return notices;
	}

	@Override
	public List<TNotices> selectRecentNotices(Map map) {
		// TODO Auto-generated method stub
		return noticeMapper.selectRecentNotices(map);
	}


	
	
	
	
	
	

}
