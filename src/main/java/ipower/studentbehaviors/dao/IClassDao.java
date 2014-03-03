package ipower.studentbehaviors.dao;

import ipower.studentbehaviors.domain.Class;

/**
 * 班级信息数据访问接口。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public interface IClassDao extends IBaseDao<ipower.studentbehaviors.domain.Class> {
	/**
	 * 同步数据。
	 * @param data
	 * 	数据。
	 * @return
	 * 	结果。
	 * */
	boolean sync(Class data);
}