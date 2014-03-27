package ipower.studentbehaviors.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级全勤登记表。
 * @author yangyong.
 * @since 2014-03-27.
 * */
public class ClassAttendanceRegister implements Serializable {
	private static final long serialVersionUID = 1L;
	private Class clazz;
	private String id,date,createUserId,createUserName;
	private Integer segment;
	private Date createTime;
	/**
	 * 构造函数。
	 * */
	public ClassAttendanceRegister(){
		this.setCreateTime(new Date());
	}
	/**
	 * 获取登记表ID。
	 * @return 登记表ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置登记表ID。
	 * @param id
	 * 	登记表ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取登记日期。
	 * @return 登记日期。
	 * */
	public String getDate() {
		return date;
	}
	/**
	 * 设置登记日期。
	 * @param date
	 * 登记日期。
	 * */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取考勤段。
	 * @return 考勤段。
	 * */
	public Integer getSegment() {
		return segment;
	}
	/**
	 * 设置考勤段。
	 * @param segment
	 * 	考勤段。
	 * */
	public void setSegment(Integer segment) {
		this.segment = segment;
	}
	/**
	 * 获取全勤班级。
	 * @return 全勤班级。
	 * */
	public Class getClazz() {
		return clazz;
	}
	/**
	 * 设置全勤班级。
	 * @param clazz
	 * 	全勤班级。
	 * */
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	/**
	 * 获取创建用户ID。
	 * @return 创建用户ID。
	 * */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置创建用户ID。
	 * @param createUserId
	 * 	创建用户ID。
	 * */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取创建用户。
	 * @return 创建用户。
	 * */
	public String getCreateUserName() {
		return createUserName;
	}
	/**
	 * 设置创建用户。
	 * @param createUserName
	 * 	创建用户。
	 * */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	/**
	 * 获取创建时间。
	 * @return 创建时间。
	 * */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间。
	 * @param createTime
	 * 创建时间。
	 * */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	} 
}