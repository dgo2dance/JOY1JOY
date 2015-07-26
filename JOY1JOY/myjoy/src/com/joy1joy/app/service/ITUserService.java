package com.joy1joy.app.service;

import com.joy1joy.app.bean.TUsers;

/** 
 * <p>Title: IUserService.java</p>
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
public interface ITUserService{
	public boolean addUsers(TUsers users);
	public TUsers findUserByName(String userName);
	public TUsers findUserByPhone(String userPhone);
	public TUsers findUserByNameOrPhone(String userName,String userPhone);
	public boolean updateUsers(TUsers users);
}
