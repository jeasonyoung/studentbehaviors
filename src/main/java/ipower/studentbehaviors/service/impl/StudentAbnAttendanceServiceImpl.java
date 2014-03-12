package ipower.studentbehaviors.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.domain.Student;
import ipower.studentbehaviors.domain.StudentAbnAttendance;
import ipower.studentbehaviors.modal.StudentAbnAttendanceInfo;
import ipower.studentbehaviors.service.IStudentAbnAttendanceService;

/**
 * 学生考勤异常服务实现。
 * @author yangyong.
 * @since 2014-03-07.
 * */
public class StudentAbnAttendanceServiceImpl extends DataServiceImpl<StudentAbnAttendance,StudentAbnAttendanceInfo> implements IStudentAbnAttendanceService{
	private IStudentAbnAttendanceDao studentAbnAttendanceDao;
	private IStudentDao studentDao;
	
	@Override
	public void setStudentAbnAttendanceDao(IStudentAbnAttendanceDao studentAbnAttendanceDao) {
		this.studentAbnAttendanceDao = studentAbnAttendanceDao;
	}
	
	@Override
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	protected List<StudentAbnAttendance> find(StudentAbnAttendanceInfo info) {
		String hql = "from StudentAbnAttendance s where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && info.getSort().equalsIgnoreCase("studentName")){
			info.setSort("student.name");
		}
		if(info.getSort() != null && info.getSort().equalsIgnoreCase("className")){
			info.setSort("student.clazz.name");
		}
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += " order by s." + info.getSort() + " " + info.getOrder();
		}
		return this.studentAbnAttendanceDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected StudentAbnAttendanceInfo changeModel(StudentAbnAttendance data) {
		if(data == null)return null;
		StudentAbnAttendanceInfo info = new StudentAbnAttendanceInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getStudent() != null){
			info.setStudentId(data.getStudent().getId());
			info.setStudentName(data.getStudent().getName());
			if(data.getStudent().getClazz() != null){
				info.setClassId(data.getStudent().getClazz().getId());
				info.setClassName(data.getStudent().getClazz().getName());
			}
		}
		return info;
	}

	@Override
	protected Long total(StudentAbnAttendanceInfo info) {
		String hql = "select count(*) from StudentAbnAttendance s where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		return this.studentAbnAttendanceDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(StudentAbnAttendanceInfo info, String hql, Map<String, Object> parameters) {
		if(info.getClassId() != null && !info.getClassId().trim().isEmpty()){
			hql += " and (s.student.clazz.id = :classId) ";
			parameters.put("classId", info.getClassId());
		}
		if(info.getStudentName() != null && !info.getStudentName().trim().isEmpty()){
			hql += " and (s.student.name like :studentName) ";
			parameters.put("studentName", "%" + info.getStudentName() + "%");
		}
		if(info.getDate() != null && !info.getDate().trim().isEmpty()){
			hql += " and (s.date = :date)";
			parameters.put("date", info.getDate());
		}
		if(info.getCreateUserId() != null && !info.getCreateUserId().trim().isEmpty()){
			hql += " and (s.createUserId = :createUserId)";
			parameters.put("createUserId", info.getCreateUserId());
		}
		if(info.getCreateUserName() != null && !info.getCreateUserName().trim().isEmpty()){
			hql += " and (s.createUserName like :createUserName) ";
			parameters.put("createUserName", "%" + info.getCreateUserName() + "%");
		}
		if(info.getSegment() != null && info.getSegment() > 0){
			hql += " and (s.segment = :segment) ";
			parameters.put("segment", info.getSegment());
		}
		if(info.getStatus() != null && info.getStatus() > 0){
			hql += " and (s.status = :status) ";
			parameters.put("status", info.getStatus());
		}
		return hql;
	}

	@Override
	public StudentAbnAttendanceInfo update(StudentAbnAttendanceInfo info) {
		if(info != null){
			boolean isAdded = false;
			StudentAbnAttendance data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.studentAbnAttendanceDao.load(StudentAbnAttendance.class, info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new StudentAbnAttendance();
			}
			if(info.getStudentId() != null && (data.getStudent() == null || !data.getStudent().getId().equalsIgnoreCase(info.getStudentId()))){
				Student student = this.studentDao.load(Student.class, info.getStudentId());
				if(student != null){
					data.setStudent(student);
					info.setStudentName(student.getName());
				}
			}
			BeanUtils.copyProperties(info, data);
			if(isAdded)this.studentAbnAttendanceDao.save(data);
			if((info.getStudentName() == null || info.getStudentName().trim().isEmpty()) && data.getStudent() != null){
				info.setStudentName(data.getStudent().getName());
			}
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			if(ids[i] == null || ids[i].trim().isEmpty()) continue;
			Student data = this.studentDao.load(Student.class, ids[i]);
			if(data != null){
				this.studentDao.delete(data);
			}
		}
	}
}