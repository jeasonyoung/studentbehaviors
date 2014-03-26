package ipower.studentbehaviors.modal;

import ipower.model.Paging;

/**
 * 考勤异常信息。
 * @author yangyong.
 * @since 2013-03-25.
 * */
public class AbnAttendanceInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,date,grade,classId,className,studentName,remarks,createUserName;
	private Integer segment,status;
	/**
	 * 获取考勤异常ID。
	 * @return 考勤异常ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置考勤异常ID。
	 * @param id
	 * 考勤异常ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取考勤日期。
	 * @return 考勤日期。
	 * */
	public String getDate() {
		return date;
	}
	/**
	 * 设置考勤日期。
	 * @param date
	 *  考勤日期。
	 * */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取年级。
	 * @return 年级。
	 * */
	public String getGrade() {
		return grade;
	}
	/**
	 * 设置年级。
	 * @param grade
	 * 	年级。
	 * */
	public void setGrade(String grade) {
		this.grade = grade;
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
	 *  班级ID。
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
	/**
	 * 获取学生名称。
	 * @return 学生名称。
	 * */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * 设置学生名称。
	 * @param studentName
	 * 学生名称。
	 * */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	 * 考勤段。
	 * */
	public void setSegment(Integer segment) {
		this.segment = segment;
	}
	/**
	 * 获取考勤状态。
	 * @return 考勤状态。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置考勤状态。
	 * @param status
	 * 考勤状态。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取考勤备注。
	 * @return 考勤备注。
	 * */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置考勤备注。
	 * @param remarks
	 * 	考勤备注。
	 * */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取登记者。
	 * @return 登记者。
	 * */
	public String getCreateUserName() {
		return createUserName;
	}
	/**
	 * 设置登记者。
	 * @param createUserName
	 * 	登记者。
	 * */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}