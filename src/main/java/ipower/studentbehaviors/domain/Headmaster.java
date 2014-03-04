package ipower.studentbehaviors.domain;

import java.io.Serializable;

/**
 * 班主任。
 * @author yangyong.
 * @since 2014-03-04.
 * */
public class Headmaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private Teacher teacher;
	private Class clazz;
	private String id;
	private Integer type;
	/**
	 * 获取班主任ID。
	 * @return ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置班主任ID。
	 * @param id
	 * 	班主任ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取教师。
	 * @return 教师。
	 * */
	public Teacher getTeacher() {
		return teacher;
	}
	/**
	 * 设置教师。
	 * @param teacher
	 * 	教师。
	 * */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	/**
	 * 获取班级。
	 * @return 班级。
	 * */
	public Class getClazz() {
		return clazz;
	}
	/**
	 * 设置班级。
	 * @param clazz
	 * 	班级。
	 * */
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	/**
	 * 获取班主任类型。
	 * @return 班主任类型(1-班主任，2-副班主任)。
	 * */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置班主任类型。
	 * @param type
	 * 	班主任类型。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
}