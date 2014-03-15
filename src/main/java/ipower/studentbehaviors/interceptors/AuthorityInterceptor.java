package ipower.studentbehaviors.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 鉴权拦截器。
 * @author yangyong.
 * @since 2014-03-15.
 * */
public class AuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		// TODO Auto-generated method stub
		return invocation.invoke();
	}

}