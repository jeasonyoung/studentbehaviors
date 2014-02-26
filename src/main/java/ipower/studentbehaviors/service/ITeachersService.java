package ipower.studentbehaviors.service;

import ipower.studentbehaviors.dao.ITeacherDao;
import ipower.studentbehaviors.modal.TeacherInfo;

/**
 * 教师服务接口。
 * @author yangyong.
 * @since 2014-02-25.
 * */
public interface ITeachersService extends IDataService<TeacherInfo> {
	/**
	 * 设置教师数据操作接口。
	 * @param teacherDao
	 * 	教师数据操作接口。
	 * */
	void setTeacherDao(ITeacherDao teacherDao);
}