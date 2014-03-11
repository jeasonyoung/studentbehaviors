package ipower.studentbehaviors.service;

import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.modal.AttendanceInfo;
/**
 * 学生考勤服务接口。
 * @author yangyong.
 * @since 2014-03-09.
 * */
public interface IAttendanceService extends IDataService<AttendanceInfo> {
	/**
	 * 设置考勤异常数据访问接口。
	 * @param studentAbnAttendanceDao
	 * 	考勤异常数据访问接口。
	 * */
	void setStudentAbnAttendanceDao(IStudentAbnAttendanceDao studentAbnAttendanceDao);
	/**
	 * 设置学生数据访问接口。
	 * @param studentDao
	 * 	学生数据访问接口。
	 * */
	void setStudentDao(IStudentDao studentDao);
}