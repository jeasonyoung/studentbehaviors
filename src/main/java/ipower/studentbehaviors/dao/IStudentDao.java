package ipower.studentbehaviors.dao;

import ipower.studentbehaviors.domain.Student;

/**
 * 学生信息数据访问接口。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public interface IStudentDao extends IBaseDao<Student> {
	/**
	 * 同步数据。
	 * @param data
	 * 	数据。
	 * @return
	 * 	结果。
	 * */
	boolean sync(Student data);
}