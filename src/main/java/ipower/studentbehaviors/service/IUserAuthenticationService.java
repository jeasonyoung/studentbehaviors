package ipower.studentbehaviors.service;

import ipower.studentbehaviors.modal.UserInfo;

/**
 * 用户认证服务接口。
 * @author yangyong.
 * @since 2014-03-15.
 * */
public interface IUserAuthenticationService {
	/**
	 * 认证用户。
	 * @param userAccount
	 * 	账号。
	 * @param password
	 * 	密码。
	 * @return
	 * 	用户信息。
	 * @throws Exception 
	 * */
	UserInfo authen(String userAccount,String password) throws Exception;
}