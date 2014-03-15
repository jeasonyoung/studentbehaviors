package ipower.studentbehaviors.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import ipower.studentbehaviors.modal.UserInfo;
import ipower.studentbehaviors.service.IUserAuthenticationService;
import ipower.studentbehaviors.service.IUserService;
import ipower.utils.HttpUtil;

/**
 * 用户认证服务实现。
 * @author yangyong.
 * @since 2014-03-15.
 * */
public class UserAuthenticationServiceImpl implements IUserAuthenticationService {
	private static Logger logger = Logger.getLogger(UserAuthenticationServiceImpl.class);
	private String url,userName,userPassword;
	private IUserService userService;
	/**
	 * 设置用户认证Url。
	 * @param url
	 * 	用户认证Url。
	 * */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 设置url验证账号。
	 * @param userName
	 * 	url验证账号。
	 * */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 设置url验证账号密码。
	 * @param userPassword
	 * 	url验证账号密码。
	 * */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * 
	 * */ 
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	private synchronized boolean sendUserSoapCheck(String account,String password) throws IOException, DocumentException{
		StringBuilder soapBuilder = new StringBuilder();
		soapBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
				   .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">")
				   		.append("<soap12:Header>")
				   			.append("<AuthHeader xmlns=\"http://webservice.furongedu.com/LoginService/LoginService\">")
				   				.append("<UserName>" + this.userName + "</UserName>")
				   				.append("<PassWord>" + this.userPassword +"</PassWord>")
				   			.append("</AuthHeader>")
				   		.append("</soap12:Header>")
				   		.append("<soap12:Body>")
				   			.append("<VerifyUser xmlns=\"http://webservice.furongedu.com/LoginService/LoginService\">")
				   				.append("<UserName>" + account + "</UserName>")
				   				.append("<PassWord>" + password + "</PassWord>")
				   			.append("</VerifyUser>")
				   		.append("</soap12:Body>")
				   .append("</soap12:Envelope>");
		String post = soapBuilder.toString();
		logger.info("开始提交验证数据:");
		logger.info("url:" + this.url);
		logger.info("post:\r\n" + post);
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/soap+xml; charset=utf-8");
		headers.put("Content-Length",((Integer)post.getBytes("UTF-8").length).toString());
		
		String resultXml = HttpUtil.sendRequest(this.url, headers, "POST", post);
		logger.info("反馈数据：\r\n" + resultXml);
		if(resultXml != null && resultXml.length() > 0){
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new ByteArrayInputStream(resultXml.getBytes("UTF-8")));
			if(document == null) return false;
			Element root = document.getRootElement();
			if(root == null) return false;
			Node node = root.selectSingleNode("//VerifyUserResult");
			if(node == null) return false;
			if(node instanceof Element){
				String result = ((Element)node).getTextTrim();
				if(result == null || result.trim().isEmpty()) return false;
				return Boolean.parseBoolean(result);
			}
		}
		return false;
	}

	@Override
	public synchronized UserInfo authen(String userAccount, String password) throws Exception {
		boolean result = false;
		try {
			result = this.sendUserSoapCheck(userAccount, password);
		} catch (IOException | DocumentException e) {
			logger.error("校验账号密码时发生异常：" + e.getMessage(), e);
			e.printStackTrace();
		}
		if(!result) throw new Exception("验证用户账号或密码错误！");
		if(this.userService == null) return null;
		UserInfo info = this.userService.loadUser(userAccount);
		if(info == null) throw new Exception("未被授权访问本系统！");
		return info;
	}

}