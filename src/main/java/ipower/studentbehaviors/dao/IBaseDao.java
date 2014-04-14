package ipower.studentbehaviors.dao;

import java.util.List;
import java.util.Map;

import ipower.dao.IDao;
/**
 * 数据操作接口。
 * @author 杨勇.
 * @since 2014-02-19.
 * */
public interface IBaseDao<T> extends IDao<T> {

	/**
	 * 查找对象集合。
	 * @param hql
	 * 	HQL语句。
	 * @param parameters
	 * 	参数集合。
	 * @param page
	 * 	页码。
	 * @param rows
	 * 	页数据量
	 * <pre>
	 * 	当page与rows同时为null时，则查询全部数据。
	 * </pre>
	 * @return 结果数据集合。
	 * */
	List<T> find(String hql, Map<String, Object> parameters,Integer page, Integer rows);
	/**
	 * 统计数据总数。
	 * @param hql
	 *  HQL语句。
	 * @param parameters
	 * 	参数集合。
	 * @return 数据总数。
	 * */
	Long count(String hql, Map<String, Object> parameters);
}