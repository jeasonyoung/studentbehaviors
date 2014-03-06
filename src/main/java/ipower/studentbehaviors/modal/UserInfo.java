package ipower.studentbehaviors.modal;

import ipower.model.Paging;
/**
 * 用户信息。
 * @author yangyong.
 * @since 2014-03-05.
 * */
public class UserInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String teacherId,teacherName;
	private String role;
	/**
	 * 获取教师Id。
	 * @return 教师Id。 
	 * */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置教师Id。
	 * @param teacherId
	 * 	教师Id。
	 * */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * 获取教师姓名。
	 * @return 教师姓名。
	 * */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * 设置教师姓名。
	 * @param teacherName
	 * 	教师姓名。
	 * */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * 获取角色。
	 * @return 角色。
	 * */
	public String getRole() {
		return role;
	}
	/**
	 * 设置角色。
	 * @param role
	 * */
	public void setRole(String role) {
		this.role = role;
	}
}