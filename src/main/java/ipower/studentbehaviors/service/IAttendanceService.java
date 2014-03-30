package ipower.studentbehaviors.service;

import java.util.List;

import ipower.model.DataGrid;
import ipower.studentbehaviors.dao.IClassAttendanceRegisterDao;
import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.modal.AbnAttendanceInfo;
import ipower.studentbehaviors.modal.AbnAttendanceStatusReport;
import ipower.studentbehaviors.modal.AttendanceInfo;
import ipower.studentbehaviors.modal.AttendanceRecord;
import ipower.studentbehaviors.modal.AttendanceRegisterInfo;
import ipower.studentbehaviors.modal.ClassAttendanceReport;
import ipower.studentbehaviors.modal.UserInfo;
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
	 * 设置班级全勤登记数据访问接口。
	 * @param classAttendanceRegisterDao
	 * 班级全勤登记数据访问接口。
	 * */
	void setClassAttendanceRegisterDao(IClassAttendanceRegisterDao classAttendanceRegisterDao);
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
	/**
	 * 班级全勤登记。
	 * @param classId
	 * 	班级ID。
	 * @param date
	 * 	日期。
	 * @param segment
	 * 	考勤段。
	 * @param user
	 *  用户信息。
	 * */
	boolean attendanceRegister(String classId, String date, Integer segment,UserInfo user);
	/**
	 * 加载班级全勤登记信息。
	 * @param classId
	 * 	班级ID。
	 * @param date
	 * 	日期。
	 * @param segment
	 * 	考勤段。
	 * @return
	 * 	班级全勤登记信息。
	 * */
	AttendanceRegisterInfo loadAttendanceRegister(String classId, String date, Integer segment);
	/**
	 * 加载考勤记录。
	 * @param date
	 * 日期。
	 * @param segment
	 * 考勤段。
	 * @return 
	 * 考勤记录数据。
	 * */
	List<AttendanceRecord> loadAttendanceRecords(String date,Integer segment);
}