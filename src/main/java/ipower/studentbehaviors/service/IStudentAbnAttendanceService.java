package ipower.studentbehaviors.service;

import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.modal.AbnAttendanceStatistics;
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
	 * 统计学生异常考勤。
	 * @param classId
	 * 	班级ID。
	 * @param date
	 * 	日期。
	 * @param segment
	 * 	考勤段。
	 * @return 
	 * 	统计信息。
	 * */
	AbnAttendanceStatistics total(String classId, String date, Integer segment);
}