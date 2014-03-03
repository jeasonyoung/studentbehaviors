package ipower.studentbehaviors.dao;

import ipower.studentbehaviors.domain.Teacher;
/**
 * 教师信息数据访问接口。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public interface ITeacherDao extends IBaseDao<Teacher> {
	/**
	 * 同步数据。
	 * @param data
	 * 	数据。
	 * @return
	 * 	结果。
	 * */
	boolean sync(Teacher data);
}