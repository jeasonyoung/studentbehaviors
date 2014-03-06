package ipower.studentbehaviors.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IHeadmasterDao;
import ipower.studentbehaviors.dao.ITeacherDao;
import ipower.studentbehaviors.domain.Class;
import ipower.studentbehaviors.domain.Headmaster;
import ipower.studentbehaviors.domain.Teacher;
import ipower.studentbehaviors.modal.ClassInfo;
import ipower.studentbehaviors.modal.HeadmasterInfo;
import ipower.studentbehaviors.service.IHeadmasterService;

/**
 * 班主任服务实现类。
 * @author yangyong.
 * @since 2014-03-05.
 * */
public class HeadmasterServiceImpl extends DataServiceImpl<Headmaster,HeadmasterInfo> implements IHeadmasterService {
	private IHeadmasterDao headmasterDao;
	private IClassDao classDao;
	private ITeacherDao teacherDao;
	
	@Override
	public void setHeadmasterDao(IHeadmasterDao headmasterDao) {
		 this.headmasterDao = headmasterDao;
	}
	@Override
	public void setClassDao(IClassDao classDao) {
		this.classDao = classDao;
	}
	@Override
	public void setTeacherDao(ITeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	
	@Override
	protected List<Headmaster> find(HeadmasterInfo info) {
		String hql = "from Headmaster h where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort().equalsIgnoreCase("className")){
			info.setSort("clazz.name");
		}
		if(info.getSort().equalsIgnoreCase("teacherName")){
			info.setSort("teacher.name");
		}
		if(info.getSort() != null){
			hql += " order by h." + info.getSort() + " " + info.getOrder();
		}
		return this.headmasterDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected HeadmasterInfo changeModel(Headmaster data) {
		if(data == null) return null;
		HeadmasterInfo info = new HeadmasterInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getClazz() != null){
			info.setClassId(data.getClazz().getId());
			info.setClassName(data.getClazz().getName());
		}
		if(data.getTeacher() != null){
			info.setTeacherId(data.getTeacher().getId());
			info.setTeacherName(data.getTeacher().getName());
		}
		return info;
	}

	@Override
	protected Long total(HeadmasterInfo info) {
		String hql = "select count(*) from Headmaster h where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		return this.headmasterDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(HeadmasterInfo info, String hql, Map<String, Object> parameters) {
		if(info.getType() != null && info.getType() > 0){
			hql += " and (h.type = :type) ";
			parameters.put("type", info.getType());
		}
		if(info.getClassName() != null && !info.getClassName().trim().isEmpty()){
			hql += " and (h.clazz.name like :className) ";
			parameters.put("className", "%" + info.getClassName() + "%");
		}
		if(info.getTeacherName() != null && !info.getTeacherName().trim().isEmpty()){
			hql += " and (h.teacher.name like :teacherName) ";
			parameters.put("teacherName", "%" + info.getTeacherName() + "%");
		}
		return hql;
	}

	@Override
	public HeadmasterInfo update(HeadmasterInfo info) {
		if(info != null){
			boolean isAdded = false;
			Headmaster data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.headmasterDao.load(Headmaster.class, info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Headmaster();
			}
			BeanUtils.copyProperties(info, data);
			if(info.getClassId() != null && (data.getClazz() == null ||!info.getClassId().equalsIgnoreCase(data.getClazz().getId()))){
				Class clazz = this.classDao.load(Class.class, info.getClassId());
				if(clazz != null){
					data.setClazz(clazz);
					info.setClassName(clazz.getName());
				}
			}
			if(info.getTeacherId() != null && (data.getTeacher() == null || !info.getTeacherId().equalsIgnoreCase(data.getTeacher().getId()))){
				Teacher teacher = this.teacherDao.load(Teacher.class, info.getTeacherId());
				if(teacher != null){
					data.setTeacher(teacher);
					info.setTeacherName(teacher.getName());
				}
			}
			if(isAdded)this.headmasterDao.save(data);
			if(data.getClazz() != null && (info.getClassName() == null || info.getClassName().trim().isEmpty())){
				info.setClassName(data.getClazz().getName());
			}
			if(data.getTeacher() != null && (info.getTeacherName() == null || info.getTeacherName().trim().isEmpty())){
				info.setTeacherName(data.getTeacher().getName());
			}
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(ids[i] == null || ids[i].trim().isEmpty()) 
				continue;
			Headmaster data = this.headmasterDao.load(Headmaster.class, ids[i]);
			if(data != null)
				this.headmasterDao.delete(data);
		}
	}
	
	@Override
	public List<HeadmasterInfo> headmasters(String classId, Integer type) {
		String hql = "from Headmaster h where h.clazz.id = :classId ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("classId", classId);
		if(type != null && type > 0){
			hql += " and h.type = :type ";
			parameters.put("type", type);
		}
		hql += " order by h.teacher.name";
		List<Headmaster> list = this.headmasterDao.find(hql, parameters, null, null);
		return this.changeModel(list);
	}
	
	@Override
	public List<ClassInfo> headmasterClasses(String teacherId) {
		final String hql = "from Headmaster h where h.teacher.id = :teacherId";
		List<ClassInfo> classes = new ArrayList<ClassInfo>();
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("teacherId", teacherId);
		List<Headmaster> list = this.headmasterDao.find(hql, parameters, null, null);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				if(list.get(i) != null && list.get(i).getClazz() != null){
					ClassInfo info = new ClassInfo();
					BeanUtils.copyProperties(list.get(i).getClazz(), info);
					if(!classes.contains(info))
						classes.add(info);
				}
			}
		}
		return classes;
	}
}