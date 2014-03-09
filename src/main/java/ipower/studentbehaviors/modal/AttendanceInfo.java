package ipower.studentbehaviors.modal;

import java.util.Date;

import ipower.model.Paging;
/**
 * 学生考勤信息。
 * @author yangyong.
 * @since 2014-03-09.
 * */
public class AttendanceInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,studentId,studentName,date,gender,remarks,createUserId,createUserName;;
	private Integer segment,status;
	private Date createTime;
	/**
	 * 获取考勤ID。
	 * @return 考勤ID
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置考勤ID。
	 * @param id
	 * 	考勤ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取学生ID。
	 * @return 学生ID。
	 * */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * 设置学生ID。
	 * @param studentId
	 * 	学生ID。
	 * */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * 获取学生姓名。
	 * @return 学生姓名。
	 * */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * 设置学生姓名。
	 * @param studentName
	 * 	学生姓名。
	 * */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	 * 考勤日期。
	 * */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取性别。
	 * @return 性别。
	 * */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置性别。
	 * @param gender
	 * 	性别。
	 * */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * 获取考勤状态。
	 * @return 考勤状态。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置考勤状态。
	 * @param status
	 * 	考勤状态。
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
	 * 获取记录创建用户ID。
	 * @return 记录创建用户ID。
	 * */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置记录创建用户ID。
	 * @param createUserId
	 * 	记录创建用户ID。
	 * */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取记录创建用户姓名。
	 * @return 记录创建用户姓名。
	 * */
	public String getCreateUserName() {
		return createUserName;
	}
	/**
	 * 设置记录创建用户姓名。
	 * @param createUserName
	 * 	创建用户姓名。
	 * */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	/**
	 * 获取记录创建时间。
	 * @return 记录创建时间。
	 * */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置记录创建时间。
	 * @param createTime
	 * 	记录创建时间。
	 * */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}