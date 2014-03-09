package ipower.studentbehaviors.dao;

import java.util.List;
import java.util.Map;

import ipower.studentbehaviors.domain.StudentAbnAttendance;

/**
 * 学生考勤异常数据访问。
 * @author yangyong.
 * @since 2014-03-07.
 * */
public interface IStudentAbnAttendanceDao extends IBaseDao<StudentAbnAttendance> {
	
	/**
	 * 查询数据。
	 * @param hql
	 * 	HQL.
	 * @param parameters
	 * 	参数数据集合。
	 * @return
	 * 	结果数据集合。
	 * */
	List<?> find(String hql, Map<String, Object> parameters);
}