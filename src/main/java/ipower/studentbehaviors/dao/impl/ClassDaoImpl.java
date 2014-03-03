package ipower.studentbehaviors.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.domain.Class;

/**
 * 班级信息数据访问实现。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public class ClassDaoImpl extends BaseDaoImpl<ipower.studentbehaviors.domain.Class> implements IClassDao {

	/**
	 * 加载班级。
	 * @param code
	 * 	班级代码。
	 * @return
	 * 	班级对象。
	 * */
	protected synchronized Class load(String code){
		final String hql = "from Class c where t.code=:code";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("code", code);
		List<Class> list = this.find(hql, parameters, null, null);
		if(list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
	
	@Override
	public boolean sync(Class data) {
		if(data == null)return false;
		Class clazz = this.load(data.getCode());
		boolean isAdded = false;
		if(isAdded = (clazz == null)){
			clazz = new Class();
			clazz.setId(UUID.randomUUID().toString());
		}
		BeanUtils.copyProperties(data, clazz);
		if(isAdded)this.save(clazz);
		return true;
	}
}