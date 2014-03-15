package ipower.studentbehaviors.service;

import ipower.studentbehaviors.dao.ITeacherDao;
import ipower.studentbehaviors.dao.IUserDao;
import ipower.studentbehaviors.modal.UserInfo;

/**
 * 用户服务接口。
 * @author yangyong.
 * @since 2014-03-05.
 * */
public interface IUserService extends IDataService<UserInfo> {
	/**
	 * 设置用户数据访问接口。
	 * @param userDao
	 * 	用户数据访问接口。
	 * */
	void setUserDao(IUserDao userDao);
	/**
	 * 设置教师数据访问接口。
	 * @param teacherDao
	 * 	教师数据访问接口。
	 * */
	void setTeacherDao(ITeacherDao teacherDao);
	/**
	 * 加载用户信息。
	 * @param account
	 * 	用户账号。
	 * @return
	 * 	用户信息。
	 * */
	UserInfo loadUser(String account);
	/**
	 * 获取用户角色。
	 * @param teacherId
	 * 	角色Id。
	 * @return
	 * 	角色数组。
	 * */
	String[] roles(String teacherId);
}