package ipower.studentbehaviors.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.domain.Class;
import ipower.studentbehaviors.modal.ClassInfo;
import ipower.studentbehaviors.service.IClassService;

/**
 * 班级服务实现。
 * @author yangyong.
 * @since 2014-02-28.
 * */
public class ClassServiceImpl extends DataServiceImpl<ipower.studentbehaviors.domain.Class, ClassInfo> implements IClassService {
	private IClassDao classDao;
	
	@Override
	public void setClassDao(IClassDao classDao) {
		this.classDao = classDao;
	}

	@Override
	protected List<Class> find(ClassInfo info) {
		String hql = "from Class c where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null){
			hql += " order by c." + info.getSort() + " " + info.getOrder();
		}
		return this.classDao.find(hql, parameters, info.getPage(),info.getRows());
	}

	@Override
	protected ClassInfo changeModel(Class data) {
		if(data == null) return null;
		ClassInfo info = new ClassInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}

	@Override
	protected Long total(ClassInfo info) {
		String hql = "select count(*) from Class c where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		return this.classDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(ClassInfo info, String hql, Map<String, Object> parameters) {
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and (c.name like :name or c.code like :name)";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}

	@Override
	public ClassInfo update(ClassInfo info) {
		if(info != null){
			boolean isAdded = false;
			Class data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.classDao.load(Class.class, info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Class();
			}
			BeanUtils.copyProperties(info, data);
			if(isAdded) this.classDao.save(data);
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(String id: ids){
			if(id == null || id.trim().isEmpty())
				continue;
			Class data = this.classDao.load(Class.class, id);
			if(data != null)this.classDao.delete(data);
		}
	}
}