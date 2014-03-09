package ipower.studentbehaviors.service;

import ipower.model.DataGrid;
import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.modal.AttendanceInfo;

/**
 * 学生考勤服务接口。
 * @author yangyong.
 * @since 2014-03-09.
 * */
public interface IAttendanceService {
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
	 * 考勤列表数据。
	 * @param classId
	 * 	班级ID。
	 * @param date
	 * 	日期。
	 * @param segment
	 * 	考勤段。
	 * @return 列表数据。
	 * */
	DataGrid<AttendanceInfo> datagrid(String classId,String date,Integer segment);
	/**
	 * 更新数据。
	 * @param info
	 * 	学生考勤信息。
	 * */
	AttendanceInfo update(AttendanceInfo info);
}