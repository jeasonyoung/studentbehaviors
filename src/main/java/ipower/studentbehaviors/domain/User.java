package ipower.studentbehaviors.domain;

import java.io.Serializable;

/**
 * 用户。
 * @author yangyong.
 * @since 2014-03-05.
 * */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String role;
	private Teacher teacher;
	/**
	 * 获取教师ID。
	 * @return 教师ID。
	 * */
	public Teacher getTeacher() {
		return teacher;
	}
	/**
	 * 设置教师ID。
	 * @param teacher
	 * 	教师ID。
	 * */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	/**
	 * 获取角色(0-班主任，1-学工处，2-管理员)。
	 * @return 角色。
	 * */
	public String getRole() {
		return role;
	}
	/**
	 * 设置角色。
	 * @param role
	 * 	角色。
	 * */
	public void setRole(String role) {
		this.role = role;
	}
}