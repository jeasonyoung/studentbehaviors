package ipower.studentbehaviors.modal;

import java.io.Serializable;

/**
 * 考勤记录数据。
 * @author yangyong.
 * @since 2014-03-28.
 * */
public class AttendanceRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private String week, date,grade,className,status;
	private Integer segment,total, late,disease,leave,other;
	/**
	 * 构造函数。
	 * */
	public AttendanceRecord(){
		this.total = this.late = this.disease = this.leave = this.other = 0;
	}
	/**
	 * 获取星期。
	 * @return 星期。
	 * */
	public String getWeek() {
		return week;
	}
	/**
	 * 设置星期。
	 * @param week
	 * 	星期。
	 * */
	public void setWeek(String week) {
		this.week = week;
	}
	/**
	 * 获取日期。
	 * @return 日期。 
	 * */
	public String getDate() {
		return date;
	}
	/**
	 * 设置日期。
	 * @param date
	 * 	日期。
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
	 * 获取年级。
	 * @return 年级。
	 * */
	public String getGrade() {
		return grade;
	}
	/**
	 * 设置年级。
	 * @param grade
	 * 	 年级。
	 * */
	public void setGrade(String grade) {
		this.grade = grade;
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
	 *  班级名称。
	 * */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * 获取考勤状态。
	 * @return 考勤状态。
	 * */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置考勤状态。
	 * @param status
	 * 考勤状态。
	 * */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取总人数。
	 * @return 总人数。
	 * */
	public Integer getTotal() {
		return total;
	}
	/**
	 * 设置总人数。
	 * @param  total
	 * 总人数。
	 * */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * 获取迟到人数。
	 * @return 迟到人数。
	 * */
	public Integer getLate() {
		return late;
	}
	/**
	 * 设置迟到人数。
	 * @param late
	 * 迟到人数。
	 * */
	public void setLate(Integer late) {
		this.late = late;
	}
	/**
	 * 获取病假人数。
	 * @return 病假人数。
	 * */
	public Integer getDisease() {
		return disease;
	}
	/**
	 * 设置病假人数。
	 * @param disease
	 * 	病假人数。
	 * */
	public void setDisease(Integer disease) {
		this.disease = disease;
	}
	/**
	 * 获取请假人数。
	 * @return 请假人数。
	 * */
	public Integer getLeave() {
		return leave;
	}
	/**
	 * 设置请假人数。
	 * @param leave
	 * 请假人数。
	 * */
	public void setLeave(Integer leave) {
		this.leave = leave;
	}
	/**
	 * 获取其他人数。
	 * @return 其他人数。
	 * */
	public Integer getOther() {
		return other;
	}
	/**
	 * 设置其他人数。
	 * @param other
	 *  其他人数。
	 * */
	public void setOther(Integer other) {
		this.other = other;
	}
}