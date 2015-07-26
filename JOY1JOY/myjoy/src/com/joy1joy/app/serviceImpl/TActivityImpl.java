package com.joy1joy.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joy1joy.app.bean.ActivityPage;
import com.joy1joy.app.bean.TActivity;
import com.joy1joy.app.dao.TActivityMapper;
import com.joy1joy.app.service.ITActivity;

/**
 *
 * @author xujun
 */
@Service
public class TActivityImpl implements ITActivity {

	@Autowired
	private TActivityMapper mapper;

	@Override
	public TActivity getActivityById(int id) {
		// TODO Auto-generated method stub
		return mapper.getActivityById(id);
	}

	@Override
	public int insertActivity(TActivity activity) {
		// TODO Auto-generated method stub
		return mapper.insertActivity(activity);
	}

	@Override
	public List<TActivity> getActivitiesWithPages(ActivityPage page) {
		// TODO Auto-generated method stub
		return mapper.getActivitiesWithPages(page);
	}

	@Override
	public int updateActivity(TActivity activity) {
		// TODO Auto-generated method stub
		return mapper.updateActivity(activity);
	}

	@Override
	public int getOrgActivitiesWithPagesCount(int cuid) {
		// TODO Auto-generated method stub
		return mapper.getOrgActivitiesWithPagesCount(cuid);
	}

	@Override
	public List<TActivity> getOrgActivitiesWithPages(ActivityPage page) {
		// TODO Auto-generated method stub
		return mapper.getOrgActivitiesWithPages(page);
	}

	@Override
	public int updateAtStatus(TActivity activity) {
		// TODO Auto-generated method stub
		return mapper.updateAtStatus(activity);
	}

	@Override
	public List<TActivity> getAllActivitiesWithPages(ActivityPage page) {
		// TODO Auto-generated method stub
		return mapper.getAllActivitiesWithPages(page);
	}

	@Override
	public int getAllActivitiesCount(ActivityPage page) {
		// TODO Auto-generated method stub
		return mapper.getAllActivitiesCount(page);
	}

	@Override
	public int updateActivityStatus(TActivity activity) {
		// TODO Auto-generated method stub
		return mapper.updateActivityStatus(activity);
	}

}
