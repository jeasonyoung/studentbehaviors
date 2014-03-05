package ipower.studentbehaviors.modal;

import ipower.model.Paging;

/**
 * 班主任信息。
 * @author yangyong.
 * @since 2014-03-05.
 * */
public class HeadmasterInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,teacherId,teacherName,classId,className;
	private Integer type;
	/**
	 * 获取班主任Id。
	 * @return 班主任Id。 
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置班主任Id。
	 * @param id
	 * 	班主任Id。
	 * */
	public void setId(String id) {
		this.id = id;
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
	 * 	班主任类型(1-班主任，2-副班主任)。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取班主任教师ID。
	 * @return 班主任教师ID。
	 * */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置班主任教师ID。
	 * @param teacherId
	 * 	班主任教师ID。
	 * */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * 获取班主任教师姓名。
	 * @return 班主任教师姓名。
	 * */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * 设置班主任教师姓名。
	 * @param teacherName
	 * 	班主任教师姓名。
	 * */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * 获取班级ID。
	 * @return 班级ID。
	 * */
	public String getClassId() {
		return classId;
	}
	/**
	 * 设置班级ID。
	 * @param classId
	 * 	班级ID。
	 * */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * 获取班级名称。
	 * @return 班级名称。
	 * */
	public String getClassName() {
		return className;
	}
	/**
	 * 设置班级名称。
	 * @param className
	 * 	班级名称。
	 * */
	public void setClassName(String className) {
		this.className = className;
	}
}