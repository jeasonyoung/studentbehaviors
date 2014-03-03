package ipower.studentbehaviors.service;

import ipower.studentbehaviors.domain.Class;

/**
 * 同步数据接口。
 * @author yangyong.
 * @since 2014-03-03.
 * */
public interface ISyncDataService {
	/**
	 * 同步教师。
	 * @param unitName
	 * 	单位名称。
	 * */
	void syncTeachers(String unitName);
	/**
	 * 同步班级。
	 * @param unitName
	 * 	单位名称。
	 * */
	void syncClasses(String unitName);
	/**
	 * 同步学生。
	 * @param unitName
	 * 	单位名称。
	 * @param clazz
	 *  班级。
	 * */
	void syncStudents(String unitName,Class clazz);
}