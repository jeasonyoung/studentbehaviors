package ipower.studentbehaviors.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.domain.Student;

/**
 * 学生信息数据访问实现。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public class StudentDaoImpl extends BaseDaoImpl<Student> implements IStudentDao {
	
	protected synchronized Student load(String code){
		final String hql = "from Student s where s.code=:code";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("code", code);
		List<Student> list = this.find(hql, parameters, null, null);
		if(list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
	
	@Override
	public boolean sync(Student data) {
		if(data == null) return false;
		Student student = this.load(data.getCode());
		boolean isAdded = false;
		if(isAdded = (student == null)){
			student = new Student();
			student.setId(UUID.randomUUID().toString());
			student.setClazz(data.getClazz());
		}else {
			data.setIdCard(student.getIdCard());
		}
		data.setId(student.getId());
		data.setStatus(student.getStatus());
		if(data.getStatus() == null){
			data.setStatus(1);
		}
		BeanUtils.copyProperties(data, student);
		if(isAdded)this.save(student);
		return true;
	}
}