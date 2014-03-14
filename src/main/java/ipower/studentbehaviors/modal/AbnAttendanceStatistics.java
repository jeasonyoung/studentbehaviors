package ipower.studentbehaviors.modal;

import java.io.Serializable;
import java.util.List;
/**
 * 学生考勤异常统计。
 * @author yangyong.
 * @since 2014-03-13.
 * */
public class AbnAttendanceStatistics implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long total;
	private List<AbnAttendanceTotal> abns;
	/**
	 * 构造函数。
	 * */
	public AbnAttendanceStatistics(){
		this.total = (long)0;
	}
	/**
	 * 获取学生考勤异常总数。
	 * @return 学生考勤异常总数。
	 * */
	public Long getTotal() {
		return total;
	}
	/**
	 * 设置学生考勤异常总数。
	 * @param total
	 * 	考勤异常总数。
	 * */
	public void setTotal(Long total) {
		this.total = total;
	}
	/**
	 * 获取异常分类统计。
	 * @return 异常分类统计。
	 * */
	public List<AbnAttendanceTotal> getAbns() {
		return abns;
	}
	/**
	 * 设置异常分类统计。
	 * @param abns
	 * 	异常分类统计。
	 * */
	public void setAbns(List<AbnAttendanceTotal> abns) {
		this.abns = abns;
	}
}