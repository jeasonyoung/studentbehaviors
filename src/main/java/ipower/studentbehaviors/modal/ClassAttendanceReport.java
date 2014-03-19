package ipower.studentbehaviors.modal;

import java.io.Serializable;
/**
 * 班级考勤报表。
 * @author yangyong.
 * @since 2014-03-18.
 * */
public class ClassAttendanceReport implements Serializable {
	private static final long serialVersionUID = 1L;
	private String classId,className,attendance;
	private Integer total;
	private AbnAttendanceStatistics statistics;
	/**
	 * 构造函数。
	 * */
	public ClassAttendanceReport(){
		this.setStatistics(new AbnAttendanceStatistics());
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
	/**
	 * 获取班级总人数。
	 * @return 班级总人数。
	 * */
	public Integer getTotal() {
		return total;
	}
	/**
	 * 设置班级总人数。
	 * @param total
	 * 	班级总人数。
	 * */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * 获取考勤异常汇总。
	 * @return 考勤异常汇总。
	 * */
	public AbnAttendanceStatistics getStatistics() {
		return statistics;
	}
	/**
	 * 设置考勤异常汇总。
	 * @param statistics
	 * 	考勤异常汇总。
	 * */
	public void setStatistics(AbnAttendanceStatistics statistics) {
		this.statistics = statistics;
		this.setAttendance(this.createAttendance(statistics));
	}
	/**
	 * 获取出勤率。
	 * @return 出勤率。
	 * */
	public String getAttendance() {
		return attendance;
	}
	/**
	 * 设置出勤率。
	 * @param attendance
	 * 	出勤率。
	 * */
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	/**
	 * 获取出勤率。
	 * @return 
	 * 出勤率。
	 * */
	private String createAttendance(AbnAttendanceStatistics statistics){
		if(this.total == null || this.total == 0) return "";
		if(statistics == null || statistics.getTotal() == null || statistics.getTotal() == 0){
			return "100%";
		}
		int abn = statistics.getTotal().intValue();
		double result = ((this.total - abn)/(double)this.total) * 100;
		return String.format("%.2f%%", result);
	}
}