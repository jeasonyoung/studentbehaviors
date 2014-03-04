package ipower.studentbehaviors.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.ITeacherDao;
import ipower.studentbehaviors.domain.Teacher;
import ipower.studentbehaviors.modal.TeacherInfo;
import ipower.studentbehaviors.service.ITeachersService;

/**
 * 教师服务实现类。
 * @author yangyong.
 * @since 2014-02-26.
 * */
public class TeachersServiceImpl extends DataServiceImpl<Teacher, TeacherInfo> implements ITeachersService {
	private ITeacherDao teacherDao;
	
	@Override
	public void setTeacherDao(ITeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	
	@Override
	protected List<Teacher> find(TeacherInfo info) {
		String hql = "from Teacher t where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += " order by t." + info.getSort() + " " + info.getOrder();
		}
		return this.teacherDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected TeacherInfo changeModel(Teacher data) {
		if(data == null) return null;
		TeacherInfo info = new TeacherInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}

	@Override
	protected Long total(TeacherInfo info) {
		String hql = "select count(*) from Teacher t where 1=1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.teacherDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(TeacherInfo info, String hql,Map<String, Object> parameters) {
		if(info.getAccount() != null && !info.getAccount().trim().isEmpty()){
			hql += " and (t.account like :account or t.name like :account) ";
			parameters.put("account", "%"+ info.getAccount() +"%");
		}
		return hql;
	}

	@Override
	public TeacherInfo update(TeacherInfo info) {
		if(info != null){
			boolean isAdded = false;
			Teacher data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.teacherDao.load(Teacher.class, info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Teacher();
			}
			BeanUtils.copyProperties(info, data);
			if(isAdded)this.teacherDao.save(data);
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0)return;
		for(String id: ids){
			if(id == null || id.trim().isEmpty()) 
				continue;
			Teacher data = this.teacherDao.load(Teacher.class, id);
			if(data != null) this.teacherDao.delete(data);
		}
	}
	
	public List<TeacherInfo> all(){
		List<Teacher> list = this.teacherDao.find("from Teacher t order by t.name", null, null, null);
		return this.changeModel(list);
	}
}