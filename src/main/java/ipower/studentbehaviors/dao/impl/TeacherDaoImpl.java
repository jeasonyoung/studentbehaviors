package ipower.studentbehaviors.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.ITeacherDao;
import ipower.studentbehaviors.domain.Teacher;

/**
 * 教师信息数据访问实现。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements ITeacherDao {
	/**
	 * 根据教师代码加载数据。
	 * @param account
	 * 	教师帐号。
	 * @return
	 * 教师数据。
	 * */
	protected synchronized Teacher load(String account){
		final String hql = "from Teacher t where t.account=:account";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("account", account);
		List<Teacher> list = this.find(hql, parameters, null, null);
		if(list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
	
	@Override
	public boolean sync(Teacher data) {
		if(data == null) return false;
		Teacher teacher = this.load(data.getAccount());
		boolean isAdded = false;
		if(isAdded = (teacher == null)){
			teacher = new Teacher();
			teacher.setId(UUID.randomUUID().toString());
			if(data.getId() == null || data.getId().trim().isEmpty()){
				data.setId(teacher.getId());
			}
		}
		BeanUtils.copyProperties(data, teacher);
		if(isAdded)this.save(teacher);
		return true;
	}

}