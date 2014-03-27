package ipower.studentbehaviors.modal;

import java.io.Serializable;

/**
 * 考勤异常状态报表。
 * @author yangyong.
 * @since 2014-03.
 * */
public class AbnAttendanceStatusReport  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer  total, status,count;
	private String share;
	/**
	 * 获取异常学生总数。
	 * @return 异常学生总数。
	 * */
	public Integer getTotal() {
		return total;
	}
	/**
	 * 设置异常学生总数。
	 * @param total
	 * 	异常学生总数。
	 * */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * 获取考勤异常状态。
	 * @return  考勤异常状态。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置考勤异常状态。
	 * @param status
	 * 考勤异常状态。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取考勤异常状态下人数。
	 * @return 考勤异常状态下人数。
	 * */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置考勤异常状态下人数。
	 * @param 
	 * */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取所占比例。
	 * @return 所占比例。
	 * */
	public String getShare() {
		return share;
	}
	/**
	 *  设置所占比例。
	 *  @param share
	 *  所占比例。
	 * */
	public void setShare(String share) {
		this.share = share;
	}
	/**
	 * 计算所占比例
	 * */
	public void computerShare(){
		if(this.total > 0){
			double result = ((this.count)/(double)this.total) * 100;
			this.share = String.format("%.2f%%", result);
		}
	}
}