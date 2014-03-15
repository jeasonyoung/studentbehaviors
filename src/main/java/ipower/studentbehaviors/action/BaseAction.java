package ipower.studentbehaviors.action;

import ipower.studentbehaviors.interceptors.IUserAware;
import ipower.studentbehaviors.modal.UserInfo;
import ipower.utils.XmlUtil;

import java.io.IOException;
import java.io.PrintWriter; 

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;

/**
 * ACTION基础抽象类.
 * @author 杨勇.
 * @since 2013-11-27.
 * */
public class BaseAction implements Action,IUserAware {
	private UserInfo userInfo;
	/**
	 * 当前用户的SESSION键值。
	 * */
	public final static String CURRENT_USER_SESSION_KEY = "current_user";
	/**
	 * 设置用户信息。
	 * @param userInfo
	 * 	用户信息。
	 * */
	@Override
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	/**
	 * 获取用户信息。
	 * @return 用户信息。
	 * */
	public UserInfo getUserInfo(){
		return this.userInfo;
	}
	/**
	 * 将对象转换成JSON字符串，并响应回前台。
	 * @param o
	 * 	须转换的对象。
	 * @throws IOException 
	 * */
	public void writeJson(Object o) throws IOException{
		if(o != null){
			String json = JSON.toJSONStringWithDateFormat(o, "yyyy-MM-dd HH:mm:ss");
						
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		}
	}
	
	/**
	 * 输出Xml。
	 * @param doc
	 * 	Xml对象。
	 * @throws IOException 
	 * @throws TransformerException 
	 * @throws TransformerFactoryConfigurationError 
	 * */
	public void writeXml(Document doc) throws IOException, TransformerFactoryConfigurationError, TransformerException{
		if(doc != null){
			String xml = XmlUtil.writeXmlToString(doc);
			
			HttpServletResponse response =ServletActionContext.getResponse();
			response.setContentType("text/xml;charset=utf-8");
			
			PrintWriter writer = response.getWriter();	
			writer.write(xml);
			writer.flush();
			writer.close();
		}
	}
	/**
	 * 默认执行调用list页面.
	 * @return 执行结果。
	 * */
	@Override
	public String execute() throws Exception {
		return this.list();
	}
	/**
	 * 调用list页面。
	 * */
	public String list(){
		return "list";
	}
	/**
	 * 调用edit页面。
	 * */
	public String edit(){
		return "edit";
	}
}