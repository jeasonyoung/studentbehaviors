package ipower.studentbehaviors.action;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import ipower.model.Json;
import ipower.studentbehaviors.modal.UserInfo;
import ipower.studentbehaviors.service.IUserAuthenticationService;
/**
 * 登录Action。
 * @author yangyong.
 * @since 2014-03-15.
 * */
public class LoginAction extends BaseAction implements SessionAware {
	private Map<String, Object> session;
	private IUserAuthenticationService userAuthentication;
	private String account,password;
	/**
	 * 设置用户认证服务接口。
	 * @param userAuthentication
	 * 	用户认证服务接口。
	 * */
	public void setUserAuthentication(IUserAuthenticationService userAuthentication) {
		this.userAuthentication = userAuthentication;
	}
	/**
	 * 设置Session。
	 * @param session
	 * 	Session.
	 * */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	/**
	 * 设置用户账号。
	 * @param account
	 * 	用户账号。
	 * */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 设置用户密码。
	 * @param password
	 * 	用户密码。
	 * */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 登录。
	 * @throws IOException 
	 * */
	public void login() throws IOException{
		Json result = new Json();
		try{
			UserInfo user = this.userAuthentication.authen(this.account, this.password);
			if(user != null && this.session != null){
				result.setSuccess(true);
				this.session.put(BaseAction.CURRENT_USER_SESSION_KEY, user);
			}
		}catch(Exception e){
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}finally{
			this.writeJson(result);
		}
		
	}
}