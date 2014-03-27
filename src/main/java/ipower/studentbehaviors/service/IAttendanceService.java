package ipower.studentbehaviors.service;

import java.util.List;

import ipower.model.DataGrid;
import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.modal.AbnAttendanceInfo;
import ipower.studentbehaviors.modal.AbnAttendanceStatusReport;
import ipower.studentbehaviors.modal.AttendanceInfo;
import ipower.studentbehaviors.modal.ClassAttendanceReport;
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
	/**
	 * 设置班级数据访问接口。
	 * @param classDao
	 * 	班级数据访问接口。
	 * */
	void setClassDao(IClassDao classDao);
	/**
	 * 班级考勤日报。
	 * @param grade
	 * 	年级。
	 * @param date
	 * 	日期。
	 * @return
	 * 	考勤日报
	 * */
	List<ClassAttendanceReport> classDailyReport(String grade,String date);
	/**
	 * 班级考勤周报。
	 * @param grade
	 * 	年级。
	 * @param start
	 * 	开始时间。
	 * @param end
	 *  结束时间。
	 * @return
	 * 	考勤周报。
	 * */
	List<ClassAttendanceReport> classWeekReport(String grade,String start,String end);
	/**
	 * 学生异常考勤明细。
	 * @param info
	 *  查询条件。
	 * @return
	 *  查询结果。
	 * */
	DataGrid<AbnAttendanceInfo> students(AbnAttendanceInfo info);
	/**
	 * 考勤状态报表。
	 * @param grade
	 * 	年级。
	 * @param classId
	 * 班级ID。
	 * @param studentName
	 * 学生。
	 * @param start
	 * 开始时间。
	 * @param end
	 * 结束时间。
	 * @return 
	 * 	考勤状态报表。
	 * */
	List<AbnAttendanceStatusReport> attendanceStatusReport(String grade,String classId,String studentName, String start,String end);
}