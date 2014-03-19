package ipower.studentbehaviors.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.model.DataGrid;
import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.domain.Class;
import ipower.studentbehaviors.domain.Student;
import ipower.studentbehaviors.domain.StudentAbnAttendance;
import ipower.studentbehaviors.modal.AbnAttendanceStatistics;
import ipower.studentbehaviors.modal.AbnAttendanceTotal;
import ipower.studentbehaviors.modal.AttendanceInfo;
import ipower.studentbehaviors.modal.ClassAttendanceReport;
import ipower.studentbehaviors.service.IAttendanceService;
/**
 * 学生考勤服务实现类。
 * @author yangyong.
 * @since 2014-03-09.
 * */
public class AttendanceServiceImpl implements IAttendanceService {
	private IStudentAbnAttendanceDao studentAbnAttendanceDao;
	private IStudentDao studentDao;
	private IClassDao classDao;
	
	@Override
	public void setStudentAbnAttendanceDao(IStudentAbnAttendanceDao studentAbnAttendanceDao) {
		this.studentAbnAttendanceDao = studentAbnAttendanceDao;
	}
	
	@Override
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public void setClassDao(IClassDao classDao) {
		this.classDao = classDao;
	}

	@Override
	public synchronized  DataGrid<AttendanceInfo> datagrid(final AttendanceInfo info) {
		DataGrid<AttendanceInfo> grid = new DataGrid<AttendanceInfo>();
		List<AttendanceInfo> rows = new ArrayList<AttendanceInfo>();
		String stu_hql = "from Student s where s.status=1 and s.clazz.id=:classId",
			     abn_hql = "from StudentAbnAttendance s where s.student.id=:studentId and s.date=:date and s.segment=:segment";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("classId", info.getClassId());
		
		if(info.getSort().equalsIgnoreCase("studentName")){
			stu_hql += " order by s.name " + info.getOrder();
		}else if(info.getSort().equalsIgnoreCase("gender")){
			stu_hql += " order by s.gender " + info.getOrder();
		}
		
		List<Student> students = this.studentDao.find(stu_hql, parameters, null, null);
		if(students != null && students.size() > 0){
			for(int i = 0; i < students.size(); i++){
				Student student = students.get(i);
				if(student == null) continue;
				AttendanceInfo data = new AttendanceInfo();
				data.setStudentId(student.getId());
				data.setStudentName(student.getName());
				data.setGender(student.getGender());
				data.setClassId(info.getClassId()); 
				
				parameters = new HashMap<String, Object>();
				parameters.put("studentId", student.getId());
				parameters.put("date", info.getDate());
				parameters.put("segment", info.getSegment());
				
			    List<StudentAbnAttendance> list = this.studentAbnAttendanceDao.find(abn_hql, parameters, null, null);
			    if(list != null && list.size() > 0){
			    	StudentAbnAttendance abn = list.get(0);
			    	data.setId(abn.getId());
			    	data.setCreateTime(abn.getCreateTime());
			    	data.setCreateUserId(abn.getCreateUserId());
			    	data.setCreateUserName(abn.getCreateUserName());
			    	data.setDate(abn.getDate());
			    	data.setRemarks(abn.getRemarks());
			    	data.setSegment(abn.getSegment());
			    	data.setStatus(abn.getStatus());
			    }
			    rows.add(data);
			}
			if(info.getSort() != null && !info.getSort().trim().isEmpty() 
					&& !info.getSort().equalsIgnoreCase("studentName")
					&& !info.getSort().equalsIgnoreCase("gender")){
				Collections.sort(rows, new Comparator<AttendanceInfo>() {
					@Override
					public int compare(AttendanceInfo o1, AttendanceInfo o2) {
						if(info.getSort().equalsIgnoreCase("status")){
							if(info.getOrder().equalsIgnoreCase("asc")){
								return o1.getStatus() - o2.getStatus();
							}
							return o2.getStatus() - o1.getStatus(); 
						}
						return 0;
					}
				});
			}
		}
		grid.setRows(rows);
		grid.setTotal((long)rows.size());
		return grid;
	}

	@Override
	public synchronized AttendanceInfo update(AttendanceInfo info) {
		if(info == null) return null;
		StudentAbnAttendance data = null;
		boolean isAdded = false;
		if(info.getId() != null && !info.getId().trim().isEmpty()){
			data = this.studentAbnAttendanceDao.load(StudentAbnAttendance.class, info.getId());
		}
		if(info.getStatus() > 0 && data == null){
			isAdded = true;
			info.setId(UUID.randomUUID().toString());
			info.setCreateTime(new Date());
			data = new StudentAbnAttendance();
			BeanUtils.copyProperties(info, data);
			if(info.getStudentId() != null && !info.getStudentId().trim().isEmpty()){
				Student student = this.studentDao.load(Student.class, info.getStudentId());
				if(student != null){
					data.setStudent(student);
					info.setStudentName(student.getName());
				}
			}
		}
		if(info.getStatus() > 0){
			data.setStatus(info.getStatus());
			data.setRemarks(info.getRemarks());
			if(info.getCreateUserId() != null && !info.getCreateUserId().trim().isEmpty()){
				data.setCreateUserId(info.getCreateUserId());
				data.setCreateUserName(info.getCreateUserName());
			}
			data.setCreateTime(new Date());
			if(isAdded)this.studentAbnAttendanceDao.save(data);
			return info;
		}
		if(info.getStatus() == 0){
			if(isAdded) return info;
			if(data != null){
				this.studentAbnAttendanceDao.delete(data);
				info.setId(null);
				return info;
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
			StudentAbnAttendance data = this.studentAbnAttendanceDao.load(StudentAbnAttendance.class, ids[i]);
			if(data != null)
				this.studentAbnAttendanceDao.delete(data);
		}
	}
	
	private synchronized AbnAttendanceStatistics createAbnAttendanceStatistics(List<StudentAbnAttendance> list){
		AbnAttendanceStatistics statistics = new AbnAttendanceStatistics();
		if(list == null || list.size() == 0) return statistics;
		statistics.setTotal((long)list.size());
		Map<Integer,AbnAttendanceTotal> cache = new HashMap<>();
		for(int i = 0; i < list.size(); i++){
			StudentAbnAttendance data = list.get(i);
			if(data == null || data.getStatus() == null)continue;
			AbnAttendanceTotal total = cache.get(data.getStatus());
			if(total == null){
				total = new AbnAttendanceTotal();
				total.setStatus(data.getStatus());
				total.setCount(0);
			}
			total.setCount(total.getCount() + 1);
			cache.put(data.getStatus(), total);
		}
		List<AbnAttendanceTotal> totals = new ArrayList<AbnAttendanceTotal>();
		if(cache.size() > 0){
			for(AbnAttendanceTotal t : cache.values()){
				if(t == null)continue;
				totals.add(t);
			}
		}
		statistics.setAbns(totals);
		return statistics;
	}

	@Override
	public synchronized List<ClassAttendanceReport> classDailyReport(String grade, String date) {
		List<ClassAttendanceReport> list = new ArrayList<ClassAttendanceReport>();
		if(grade == null || grade.trim().isEmpty()) return list;
		if(date == null || date.trim().isEmpty()) return list;
		final String class_hql = "from Class c where c.status = 1 and c.grade = :grade order by c.joinYear desc,c.name",
				     student_hql = "select count(*) from Student s where s.status = 1 and s.clazz.id = :classId",
				     abn_hql = "from StudentAbnAttendance s where s.student.clazz.id=:classId and s.date=:date";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("grade", grade);
		List<Class> classes = this.classDao.find(class_hql, parameters, null, null);
		if(classes == null || classes.size() == 0) return list;
		for(int i = 0; i < classes.size(); i++){
			Class clazz = classes.get(i);
			if(clazz == null) continue;
			ClassAttendanceReport report = new ClassAttendanceReport();
			report.setClassId(clazz.getId());
			report.setClassName(clazz.getName());
			parameters = new HashMap<String, Object>();
			parameters.put("classId", report.getClassId());
		    report.setTotal(this.studentDao.count(student_hql, parameters).intValue());
			parameters.put("date",date);
			report.setStatistics(this.createAbnAttendanceStatistics(this.studentAbnAttendanceDao.find(abn_hql, parameters, null, null)));
			list.add(report);
		}
		return list;
	}
}