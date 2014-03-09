package ipower.studentbehaviors.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.domain.StudentAbnAttendance;

/**
 * 学生考勤异常数据访问实现。
 * @author yangyong.
 * @since 2014-03-07.
 * */
public class StudentAbnAttendanceDaoImpl extends BaseDaoImpl<StudentAbnAttendance> implements IStudentAbnAttendanceDao {
	private static Logger logger = Logger.getLogger(StudentAbnAttendanceDaoImpl.class);
	/**
	 * 查询数据。
	 * @param hql
	 * 	HQL
	 * @param parameters
	 * 	参数集合。
	 * @return
	 * 	查询结果数据集。
	 * */
	@Override
	public List<?> find(String hql, Map<String, Object> parameters){
		if(hql == null || hql.isEmpty()) return null;
		logger.info("查询数据HQL：" + hql);
		Query query = this.getCurrentSession().createQuery(hql);
		if(query != null){
			if(parameters != null && parameters.size() > 0){
				for(String key : parameters.keySet()){
					query.setParameter(key, parameters.get(key));
				}
			}
			return query.list();
		}
		return null;
	}
}