package ipower.studentbehaviors.dao;

import ipower.studentbehaviors.domain.ClassAttendanceRegister;

/**
 * 班级全勤登记数据接口。
 * @author yangyong.
 * @since 2014-03-27.
 * */
public interface IClassAttendanceRegisterDao extends IBaseDao<ClassAttendanceRegister> {
	/**
	 * 加载班级全勤登记数据。
	 * @param classId
	 * 	班级ID。
	 * @param date
	 * 	日期。
	 * @param segment
	 * 	考勤段。
	 * @return
	 * 	班级全勤登记数据。 
	 * */
	ClassAttendanceRegister loadAttendanceRegister(String classId, String date, Integer segment);
}