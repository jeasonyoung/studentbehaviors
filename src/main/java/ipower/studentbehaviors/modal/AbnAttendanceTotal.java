package ipower.studentbehaviors.modal;

import java.io.Serializable;

/**
 * 考勤异常信息。
 * @author yangyong.
 * @since 2014-03-13.
 * */
public class AbnAttendanceTotal implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer status,count;
	/**
	 * 获取考勤异常类型。
	 * @return 考勤异常类型。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置考勤异常类型。
	 * @param status
	 * 	考勤异常类型。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取异常统计。
	 * @return 异常统计。
	 * */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置异常统计。
	 * @param count
	 * 	异常统计。
	 * */
	public void setCount(Integer count) {
		this.count = count;
	}	
}