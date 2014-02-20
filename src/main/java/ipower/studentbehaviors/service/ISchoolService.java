package ipower.studentbehaviors.service;

import ipower.studentbehaviors.dao.ISchoolDao;
import ipower.studentbehaviors.domain.School;

/**
 * 学校服务接口。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public interface ISchoolService {
	/**
	 * 设置学校数据访问。
	 * @param schoolDao
	 * 	学校数据访问。
	 * */
	void setSchoolDao(ISchoolDao schoolDao);
	/**
	 * 加载学校数据。
	 * @return 学校数据。
	 * */
	School load();
	/**
	 * 更新数据。
	 * @param data
	 * 	学校信息。
	 * @return 更新成功返回true,否则false。
	 * */
	void update(School data);
}