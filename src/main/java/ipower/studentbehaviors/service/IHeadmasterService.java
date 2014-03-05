package ipower.studentbehaviors.service;

import java.util.List;

import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IHeadmasterDao;
import ipower.studentbehaviors.dao.ITeacherDao;
import ipower.studentbehaviors.modal.ClassInfo;
import ipower.studentbehaviors.modal.HeadmasterInfo;

/**
 * 班主任服务接口。
 * @author yangyong.
 * @since 2014-03-05.
 * */
public interface IHeadmasterService extends IDataService<HeadmasterInfo> {
	/**
	 * 设置班主任数据访问接口。
	 * @param headmasterDao
	 * 	班主任数据访问接口。
	 * */
	void setHeadmasterDao(IHeadmasterDao headmasterDao);
	/**
	 * 设置班级数据操作接口。
	 * @param classDao
	 * 	班级数据操作接口。
	 * */
	void setClassDao(IClassDao classDao);
	/**
	 * 设置教师数据操作接口。
	 * @param teacherDao
	 * 	教师数据操作接口。
	 * */
	void setTeacherDao(ITeacherDao teacherDao);
	/**
	 * 加载班级下班主任。
	 * @param classId
	 * 	班级ID。
	 * @param type
	 * 	班主任类型。
	 * @return
	 * 	班主任信息集合。
	 * */
	List<HeadmasterInfo> headmasters(String classId,Integer type);
	/**
	 * 加载班主任下班级数据。
	 * @param teacherId
	 * 	教师ID。
	 * @return
	 * 	班级信息集合。
	 * */
	List<ClassInfo> headmasterClasses(String teacherId);
}