package com.joy1joy.app.dao;

import java.util.List;

import com.joy1joy.app.bean.ActivityPage;
import com.joy1joy.app.bean.TActivity;

/**
 *
 * @author xujun
 */
public interface TActivityMapper {

	public TActivity getActivityById(int id);

	public int insertActivity(TActivity activity);

	public List<TActivity> getActivitiesWithPages(ActivityPage page);

	public int updateActivity(TActivity activity);

	public int getOrgActivitiesWithPagesCount(int cuid);

	public List<TActivity> getOrgActivitiesWithPages(ActivityPage page);

	public int updateAtStatus(TActivity activity);

	public List<TActivity> getAllActivitiesWithPages(ActivityPage page);

	public int getAllActivitiesCount(ActivityPage page);

	public int updateActivityStatus(TActivity activity);
}
