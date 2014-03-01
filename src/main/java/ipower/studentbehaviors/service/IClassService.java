package ipower.studentbehaviors.service;

import java.util.List;

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
	/**
	 * 获取全部班级信息。
	 * @return
	 * 	全部班级信息。
	 * */
	List<ClassInfo> all();
}