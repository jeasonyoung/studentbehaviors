package ipower.studentbehaviors.interceptors;

import ipower.studentbehaviors.modal.UserInfo;

/**
 * 用户信息接口。
 * @author yangyong.
 * @since 2014-03-15.
 * */
public interface IUserAware {
	/**
	 * 设置用户信息。
	 * @param userInfo
	 * 	用户信息。
	 * */
	void setUserInfo(UserInfo userInfo);
}