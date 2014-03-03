package ipower.studentbehaviors.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.dao.ITeacherDao;
import ipower.studentbehaviors.domain.Class;
import ipower.studentbehaviors.domain.Student;
import ipower.studentbehaviors.domain.Teacher;
import ipower.studentbehaviors.service.ISyncDataService;
import ipower.utils.HttpUtil;

/**
 * 同步数据实现类。
 * @author yangyong.
 * @since 2014-03-03.
 * */
public class SyncDataServiceImpl implements ISyncDataService {
	private static Logger logger = Logger.getLogger(SyncDataServiceImpl.class);
	private ITeacherDao teacherDao;
	private IClassDao classDao;
	private IStudentDao studentDao;
	private String url,userName,password;
	
	public void setUrl(String url) {
		this.url = url;
	}
	 
	public void setUserName(String userName) {
		this.userName = userName;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}

	public void setTeacherDao(ITeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
 
	public void setClassDao(IClassDao classDao) {
		this.classDao = classDao;
	}

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}
	/**
	 * 发送Soap格式报文到服务器。
	 * @param body
	 * 	调用函数体xml片段。
	 * @return
	 *  服务器反馈结果。
	 * @throws IOException 
	 * @throws DocumentException 
	 * */
	private synchronized Document sendSoapToServer(String body) throws IOException, DocumentException{
		StringBuilder soapBuilder = new StringBuilder();
		soapBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
				   .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">")
					   .append("<soap12:Header>")
						   .append("<AuthHeader xmlns=\"http://tempuri.org/\">")
						   		.append("<UserName>" + this.userName + "</UserName>")
						   		.append("<PassWord>" + this.password + "</PassWord>")
						   .append("</AuthHeader>")
					   .append("</soap12:Header>")
					   .append("<soap12:Body>")
					   		.append(body)
					   .append("</soap12:Body>")
				   .append("</soap12:Envelope>");
		
		String post = soapBuilder.toString();
		logger.info("开始同步数据:");
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
			if(document != null) return document;
		}
		return null;
	}

	@Override
	public void syncTeachers(String unitName) {
		try {
			StringBuffer bodyBuffer = new StringBuffer();
			bodyBuffer.append("<GetTeacher xmlns=\"http://tempuri.org/\">")
					  	.append("<dwmc>" + unitName + "</dwmc>")
					  .append("</GetTeacher>");
		 
			logger.info("同步教师数据[" + unitName + "]：");
			Document doc = this.sendSoapToServer(bodyBuffer.toString());
			if(doc == null){
				logger.info("反馈结果为 null");
				return;
			}
			Element root = doc.getRootElement();
			List<?> list = root.selectNodes("//Datas");
			if(list == null || list.size() == 0){
				logger.info("没有获取到教师数据。");
				return;
			}
			logger.info("共获取：[" + list.size() + "]位教师.");
			for(int i = 0; i < list.size(); i++){
			   Object object = list.get(i);
			   if(object instanceof Node){
				   try{
					   Node node = (Node)object;
					   Element account = (Element)node.selectSingleNode("account"),
							  userName = (Element)node.selectSingleNode("xm");
					   if(account != null && userName != null){
						   Teacher data = new Teacher();
						   data.setAccount(account.getTextTrim());
						   data.setName(userName.getTextTrim());
						   if(data.getAccount() == null || data.getAccount().trim().isEmpty()){
							   continue;
						   }
						   boolean result = this.teacherDao.sync(data);
						   logger.info("同步[" + (i+1) + "]教师数据["+ data.getAccount() +"," + data.getName() + "]"+ result); 
					   }
				   }catch(Exception e){
					   logger.error("同步数据[" + (i+1) + "]时发生异常", e);
				   }
			   }
			}
		} catch (IOException | DocumentException e) {
			logger.error("同步教师数据发生异常：", e);
			e.printStackTrace();
		}
	}

	@Override
	public void syncClasses(String unitName) {
		try {
			StringBuffer bodyBuffer = new StringBuffer();
			bodyBuffer.append("<GetClass xmlns=\"http://tempuri.org/\">")
					  .append("<dwmc>" + unitName + "</dwmc>")
					  .append("</GetClass>");
			logger.info("同步班级数据[" + unitName + "]：");
			Document doc = this.sendSoapToServer(bodyBuffer.toString());
			if(doc == null){
				logger.info("反馈结果为 null");
				return;
			}
			List<?> list = doc.selectNodes("//Datas");
			if(list == null || list.size() == 0){
				logger.info("没有获取到班级数据。");
				return;
			}
			logger.info("共获取：[" + list.size() + "]个班级.");
			for(int i = 0; i < list.size(); i++){
				try{
					Object object = list.get(i);
					if(object instanceof Node){
					   Node node = (Node)object;
					   Element code = (Element)node.selectSingleNode("bjdm"),
							   name = (Element)node.selectSingleNode("bjmc"),
							   joinYear = (Element)node.selectSingleNode("rxnf"),
							   grade = (Element)node.selectSingleNode("dqnj"),
							   level = (Element)node.selectSingleNode("bjlx");
					   if(code != null && name != null && joinYear != null && grade != null){
						   ipower.studentbehaviors.domain.Class data = new Class();
						   data.setCode(code.getTextTrim());
						   data.setName(name.getTextTrim());
						   data.setJoinYear(Integer.parseInt(joinYear.getTextTrim()));
						   data.setGrade(grade.getTextTrim());
						   data.setLevel(level.getTextTrim());
						   if(data.getCode() == null || data.getCode().trim().isEmpty()){
							   continue;
						   }
						   boolean result = this.classDao.sync(data);
						   logger.info("同步[" + (i+1) + "]班级数据["+ data.getCode() +"," + data.getName() + "]"+ result); 
					   }
					}
				}catch(Exception e){
					logger.error("同步数据[" + (i+1) + "]时发生异常", e);
				}
			}
		} catch (IOException | DocumentException e) {
			logger.error("同步班级数据发生异常：", e);
			e.printStackTrace();
		}
	}

	@Override
	public void syncStudents(String unitName, Class clazz) {
		try {
			StringBuffer bodyBuffer = new StringBuffer();
			bodyBuffer.append("<GetStudent xmlns=\"http://tempuri.org/\">")
					  .append("<dwmc>" + unitName + "</dwmc>")
					  .append("<rxnf>" + clazz.getJoinYear() +"</rxnf>")
					  .append("<bjmc>" + clazz.getName() +"</bjmc>")
					  .append("<xh></xh>")
					  .append("</GetStudent>");
			logger.info("同步班级学生数据[" + clazz.getName() + "]：");
			Document doc = this.sendSoapToServer(bodyBuffer.toString());
			if(doc == null){
				logger.info("反馈结果为 null");
				return;
			}
			List<?> list = doc.selectNodes("//Datas");
			if(list == null || list.size() == 0){
				logger.info("没有获取到学生数据。");
				return;
			}
			logger.info("共获取：[" + list.size() + "]个学生.");
			for(int i = 0; i < list.size(); i++){
				try{
					Object object = list.get(i);
					if(object instanceof Node){
					   Node node = (Node)object;
					   Element code = (Element)node.selectSingleNode("xh"),
							   name = (Element)node.selectSingleNode("xm"),
							   gender = (Element)node.selectSingleNode("xb"),
							   join = (Element)node.selectSingleNode("rxnf");
					   if(code != null && name != null && gender != null && join != null){
						   Student data = new Student();
						   data.setCode(code.getTextTrim());
						   data.setName(name.getTextTrim());
						   data.setJoinYear(Integer.parseInt(join.getTextTrim()));
						   data.setGender(gender.getTextTrim());
						   data.setClazz(clazz);
						   if(data.getCode() == null || data.getCode().trim().isEmpty()){
							   continue;
						   }
						   boolean result = this.studentDao.sync(data);
						   logger.info("同步[" + (i+1) + "]班级数据["+ data.getCode() +"," + data.getName() + "]"+ result); 
					   }
					}
				}catch(Exception e){
					logger.error("同步数据[" + (i+1) + "]时发生异常", e);
				}
			}
		} catch (IOException | DocumentException e) {
			logger.error("同步班级数据发生异常：", e);
			e.printStackTrace();
		}
	}
}