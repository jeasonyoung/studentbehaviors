package ipower.studentbehaviors.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.domain.Class;
import ipower.studentbehaviors.domain.Student;
import ipower.studentbehaviors.modal.StudentInfo;
import ipower.studentbehaviors.service.IStudentService;

/**
 * 学生服务实现类。
 * @author yangyong.
 * @since 2014-03-01.
 * */
public class StudentServiceImpl extends DataServiceImpl<Student, StudentInfo> implements IStudentService {
	private IStudentDao studentDao;
	private IClassDao classDao;
	
	@Override
	public void setClassDao(IClassDao classDao) {
		this.classDao = classDao;
	}
	@Override
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	protected List<Student> find(StudentInfo info) {
		String hql = "from Student s where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort().equalsIgnoreCase("className")){
			info.setSort("clazz.name");
		}
		if(info.getSort() != null){
			hql += " order by s." + info.getSort() + " " + info.getOrder();
		}
		return this.studentDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected StudentInfo changeModel(Student data) {
		if(data == null) return null;
		StudentInfo info = new StudentInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getClazz() != null){
			info.setClassId(data.getClazz().getId());
			info.setClassName(data.getClazz().getName());
		}
		return info;
	}

	@Override
	protected Long total(StudentInfo info) {
		String hql = "select count(*) from Student s where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		return this.studentDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(StudentInfo info, String hql, Map<String, Object> parameters) {
		if(info.getClassName() != null && !info.getClassName().trim().isEmpty()){
			hql += " and (s.clazz.name like :className) ";
			parameters.put("className", "%" + info.getClassName() + "%");
		}
		if(info.getClassId() != null && !info.getClassId().trim().isEmpty()){
			hql += " and (s.clazz.id = :classId) ";
			parameters.put("classId", info.getClassId());
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and (s.name like :name or s.code like :name)";
			parameters.put("name", "%"+ info.getName() +"%");
		}
		return hql;
	}

	@Override
	public StudentInfo update(StudentInfo info) {
		if(info != null){
			boolean isAdded = false;
			Student data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.studentDao.load(Student.class, info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Student();
			}
			BeanUtils.copyProperties(info, data);
			if(info.getClassId() != null && (data.getClazz() == null || !data.getClazz().getId().equalsIgnoreCase(info.getClassId()))){
				Class clazz = this.classDao.load(Class.class, info.getClassId());
				if(clazz != null){
					data.setClazz(clazz);
					info.setClassName(clazz.getName());
				}
			}
			if(isAdded)this.studentDao.save(data);
			if(data.getClazz() != null && (info.getClassName() == null || info.getClassName().trim().isEmpty())){
				info.setClassName(data.getClazz().getName());
			}
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(String id : ids){
			if(id == null || id.trim().isEmpty())
				continue;
			Student data = this.studentDao.load(Student.class, id);
			if(data != null)
				this.studentDao.delete(data);
		}
	}
	@Override
	public List<StudentInfo> loadStudents(String classId) {
		final String hql = "from Student s where s.status = 1 and s.clazz.id = :classId order by s.name";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("classId", classId);
		List<Student> list = this.studentDao.find(hql, parameters, null, null);
		return this.changeModel(list);
	}
}