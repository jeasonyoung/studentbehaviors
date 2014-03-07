package ipower.studentbehaviors.service;

import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.modal.StudentAbnAttendanceInfo;
/**
 * 学生考勤异常服务接口。
 * @author yangyong.
 * @since 2014-03-07.
 * */
public interface IStudentAbnAttendanceService extends IDataService<StudentAbnAttendanceInfo> {
	/**
	 * 设置学生考勤异常数据访问接口。
	 * @param studentAbnAttendanceDao
	 * 	学生考勤异常数据访问接口。
	 * */
	void setStudentAbnAttendanceDao(IStudentAbnAttendanceDao studentAbnAttendanceDao);
	/**
	 * 设置学生考勤异常数据访问接口。
	 * @param studentDao
	 * 	学生考勤异常数据访问接口。
	 * */
	void setStudentDao(IStudentDao studentDao);
}