package ipower.studentbehaviors.service;

import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.modal.ClassInfo;

/**
 * 班级服务接口。
 * @author yangyong.
 * @since 2014-02-28.
 * */
public interface IClassService extends IDataService<ClassInfo> {
	/**
	 * 设置班级数据操作接口。
	 * @param classDao
	 * 	数据操作接口。
	 * */
	void setClassDao(IClassDao classDao);
}