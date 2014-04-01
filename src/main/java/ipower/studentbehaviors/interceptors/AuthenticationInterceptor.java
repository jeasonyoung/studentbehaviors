package ipower.studentbehaviors.interceptors;

import ipower.studentbehaviors.action.BaseAction;
import ipower.studentbehaviors.modal.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
 


import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 身份认证拦截器。
 * @author yangyong.
 * @since 2014-03-15.
 * */
public class AuthenticationInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AuthenticationInterceptor.class);
	private String ignoreActionNames;
	private String[] ignores;
	/**
	 * 设置需要忽略的Action认证名称正则表达式。
	 * @param ignoreActionNames
	 * 	忽略的Action认证名称正则表达式(多个用;分隔)。
	 * */
	public void setIgnoreActionNames(String ignoreActionNames) {
		this.ignoreActionNames = ignoreActionNames;
	}
	/**
	 * 初始化。
	 * */
	@Override
	public void init(){
		logger.info("开始初始化身份认证拦截器...");
		this.ignores = null;
		if(this.ignoreActionNames == null || this.ignoreActionNames.trim().isEmpty()){
			logger.info("未设置忽略的action.");
			return;
		} 
		logger.info("忽略的Action：" + this.ignoreActionNames);
		String[] strs = this.ignoreActionNames.split(";");
		List<String> list = new ArrayList<>();
		for(int i = 0; i < strs.length; i++){
			if(strs[i] == null || strs[i].trim().isEmpty())
				continue;
			list.add(strs[i]);
		}
		if(list.size() > 0){
			this.ignores = list.toArray(new String[0]);
		}
		logger.info("初始化完成!");
	}
	/**
	 * 是否忽略。
	 * @param actionName
	 * 	action名称。
	 * */
	protected boolean isIgnore(String actionName){
		if(this.ignores == null || this.ignores.length == 0) return false;
		for(int i = 0; i < this.ignores.length; i++){
			if(Pattern.matches(this.ignores[i], actionName)){
				logger.info("身份认证拦截 忽略匹配:[" + this.ignores[i] + "]->[" + actionName + "] ");
				return true;
			}
		}
		return false;
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String action = invocation.getProxy().getActionName();
		logger.info("拦截：" + action);
		if(this.isIgnore(action)){
			logger.info("忽略身份认证！");
			return invocation.invoke();
		}
		ActionContext ctx = invocation.getInvocationContext();
		Map<String, Object> session = ctx.getSession();
		UserInfo user = (UserInfo)session.get(BaseAction.CURRENT_USER_SESSION_KEY);
		if(user == null){
			logger.info("未获取身份信息，跳转至登录Action：" + BaseAction.LOGIN);
			return BaseAction.LOGIN;
		}
		logger.info("身份验证成功，准备注入用户信息[" + user.getTeacherName()+ "]role:"+ user.getRole());
		IUserAware userAware = (IUserAware)invocation.getAction();
		if(userAware != null){
			logger.info("注入用户信息成功！");
			userAware.setUserInfo(user);
		}
		logger.info("完成身份认证。");
		return invocation.invoke();
	}
}