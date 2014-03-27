package ipower.studentbehaviors.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ipower.studentbehaviors.dao.IClassAttendanceRegisterDao;
import ipower.studentbehaviors.domain.ClassAttendanceRegister;
/**
 * 班级全勤登记收据接口实现。
 * @author yangyong.
 * @since 2014-03-27.
 * */
public class ClassAttendanceRegisterDaoImpl extends BaseDaoImpl<ClassAttendanceRegister> implements IClassAttendanceRegisterDao {

	@Override
	public ClassAttendanceRegister loadAttendanceRegister(String classId, String date, Integer segment) {
		final String hql = "from ClassAttendanceRegister c where c.clazz.id = :classId and c.date = :date and c.segment = :segment order by c.createTime desc";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("classId", classId);
		parameters.put("date", date);
		parameters.put("segment", segment);
		List<ClassAttendanceRegister> list = this.find(hql, parameters, null, null);
		if(list ==  null || list.size() == 0) return null;
		return list.get(0);
	}

}