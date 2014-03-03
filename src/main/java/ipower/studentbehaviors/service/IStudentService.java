package ipower.studentbehaviors.service;

import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.modal.StudentInfo;

/**
 * 学生服务接口。
 * @author yangyong.
 * @since 2014-02-28.
 * */
public interface IStudentService extends IDataService<StudentInfo> {
	/**
	 * 设置学生数据操作接口。
	 * @param studentDao
	 * 	学生数据操作接口。
	 * */
	void setStudentDao(IStudentDao studentDao);
	/**
	 * 设置班级数据操作接口。
	 * @param classDao
	 * 	班级数据操作接口。
	 * */
	void setClassDao(IClassDao classDao);
}