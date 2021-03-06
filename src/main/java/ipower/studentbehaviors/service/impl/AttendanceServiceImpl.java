package ipower.studentbehaviors.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import ipower.studentbehaviors.dao.IClassAttendanceRegisterDao;
import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.domain.Class;
import ipower.studentbehaviors.domain.ClassAttendanceRegister;
import ipower.studentbehaviors.domain.Student;
import ipower.studentbehaviors.domain.StudentAbnAttendance;
import ipower.studentbehaviors.modal.AbnAttendanceInfo;
import ipower.studentbehaviors.modal.AbnAttendanceStatistics;
import ipower.studentbehaviors.modal.AbnAttendanceStatusReport;
import ipower.studentbehaviors.modal.AbnAttendanceTotal;
import ipower.studentbehaviors.modal.AttendanceInfo;
import ipower.studentbehaviors.modal.AttendanceRecord;
import ipower.studentbehaviors.modal.AttendanceRegisterInfo;
import ipower.studentbehaviors.modal.ClassAttendanceReport;
import ipower.studentbehaviors.modal.UserInfo;
import ipower.studentbehaviors.service.IAttendanceService;
import ipower.utils.DateUtil;
/**
 * 学生考勤服务实现类。
 * @author yangyong.
 * @since 2014-03-09.
 * */
public class AttendanceServiceImpl implements IAttendanceService {
	private IStudentAbnAttendanceDao studentAbnAttendanceDao;
	private IStudentDao studentDao;
	private IClassDao classDao;
	private IClassAttendanceRegisterDao classAttendanceRegisterDao;
	
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
	public void setClassAttendanceRegisterDao(IClassAttendanceRegisterDao classAttendanceRegisterDao) {
		this.classAttendanceRegisterDao = classAttendanceRegisterDao;
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
	private List<Class> loadClasses(String grade){
		final String class_hql = "from Class c where c.status = 1 and c.grade = :grade order by c.joinYear desc,c.name";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("grade", grade);
		return this.classDao.find(class_hql, parameters, null, null);
	}
	private Integer loadClassStudentCount(String classId){
		final String  student_hql = "select count(*) from Student s where s.status = 1 and s.clazz.id = :classId";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("classId", classId);
		return this.studentDao.count(student_hql, parameters).intValue();
	}
	
	@Override
	public synchronized List<ClassAttendanceReport> classDailyReport(String grade, String date) {
		List<ClassAttendanceReport> list = new ArrayList<ClassAttendanceReport>();
		if(grade == null || grade.trim().isEmpty()) return list;
		if(date == null || date.trim().isEmpty()) return list;
		final String abn_hql = "from StudentAbnAttendance s where s.student.clazz.id=:classId and s.date=:date";
		List<Class> classes = this.loadClasses(grade);
		if(classes == null || classes.size() == 0) return list;
		for(int i = 0; i < classes.size(); i++){
			Class clazz = classes.get(i);
			if(clazz == null) continue;
			ClassAttendanceReport report = new ClassAttendanceReport();
			report.setClassId(clazz.getId());
			report.setClassName(clazz.getName());
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("classId", report.getClassId());
			parameters.put("date",date);
		    report.setTotal(this.loadClassStudentCount(clazz.getId()));
			report.setStatistics(this.createAbnAttendanceStatistics(this.studentAbnAttendanceDao.find(abn_hql, parameters, null, null)));
			list.add(report);
		}
		return list;
	}

	@Override
	public List<ClassAttendanceReport> classWeekReport(String grade,String start, String end) {
		List<ClassAttendanceReport> list = new ArrayList<ClassAttendanceReport>();
		if(grade == null || grade.trim().isEmpty()) return list;
		if(start == null || start.trim().isEmpty()) return list;
		if(end == null || end.trim().isEmpty()) return list;
		
		final String abn_hql = "from StudentAbnAttendance s where s.student.clazz.id=:classId and (s.date between :start and :end)";
		List<Class> classes = this.loadClasses(grade);
		if(classes == null || classes.size() == 0) return list;
		for(int i = 0; i < classes.size(); i++){
			Class clazz = classes.get(i);
			if(clazz == null) continue;
			ClassAttendanceReport report = new ClassAttendanceReport();
			report.setClassId(clazz.getId());
			report.setClassName(clazz.getName());
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("classId", report.getClassId());
			parameters.put("start",start);
			parameters.put("end", end);
		    report.setTotal(this.loadClassStudentCount(clazz.getId()));
			report.setStatistics(this.createAbnAttendanceStatistics(this.studentAbnAttendanceDao.find(abn_hql, parameters, null, null)));
			list.add(report);
		}
		return list;
	}
	@Override
	public synchronized DataGrid<AbnAttendanceInfo> students(AbnAttendanceInfo info) {
		String hql = "from StudentAbnAttendance s where 1=1 ";
		//where
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(info.getGrade() != null && !info.getGrade().trim().isEmpty()){
			hql += " and (s.student.clazz.grade = :grade) ";
			parameters.put("grade", info.getGrade());
		}
		if(info.getClassId() != null && !info.getClassId().trim().isEmpty()){
			hql += " and (s.student.clazz.id = :classId) ";
			parameters.put("classId", info.getClassId());
		}
		if(info.getStudentName() != null && !info.getStudentName().trim().isEmpty()){
			hql += " and (s.student.code like :studentName or s.student.name like :studentName) ";
			parameters.put("studentName", "%"  + info.getStudentName() + "%");
		}
		if(info.getDate() != null && !info.getDate().trim().isEmpty()){
			hql += " and (s.date = :date) ";
			parameters.put("date", info.getDate());
		}
		//order
		if(info.getSort() != null && info.getSort().equalsIgnoreCase("grade")){
			info.setSort("student.clazz.grade");
		}
		if(info.getSort() != null && info.getSort().equalsIgnoreCase("studentName")){
			info.setSort("student.name");
		}
		if(info.getSort() != null && info.getSort().equalsIgnoreCase("className")){
			info.setSort("student.clazz.name");
		}
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += " order by s." + info.getSort() + " " + info.getOrder();
		}
		List<AbnAttendanceInfo> rows = new ArrayList<>();
		List<StudentAbnAttendance> list =  this.studentAbnAttendanceDao.find(hql, parameters, info.getPage(), info.getRows());
		if(list != null && list.size() > 0){
			 for(int i = 0; i < list.size(); i++){
				 StudentAbnAttendance data = list.get(i);
				 if(data == null) continue;
				 AbnAttendanceInfo row = new AbnAttendanceInfo();
				 row.setId(data.getId());
				 if(data.getStudent() != null){
					 row.setStudentName(data.getStudent().getName());
					 if(data.getStudent().getClazz() != null){
						 row.setClassName(data.getStudent().getClazz().getName());
						 row.setGrade(data.getStudent().getClazz().getGrade());
					 }
				 }
				 row.setCreateUserName(data.getCreateUserName());
				 row.setDate(data.getDate());
				 row.setRemarks(data.getRemarks());
				 row.setSegment(data.getSegment());
				 row.setStatus(data.getStatus());
				 rows.add(row);
			 }
		}
		DataGrid<AbnAttendanceInfo> grid = new DataGrid<>();
		grid.setTotal((long)rows.size());
		grid.setRows(rows);
		return grid;
	}

	@Override
	public  List<AbnAttendanceStatusReport> attendanceStatusReport(String grade, String classId, String studentName, String start, String end) {
		String hql = "from StudentAbnAttendance s where 1=1 ";
		//where
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(grade != null && !grade.trim().isEmpty()){
			hql += " and (s.student.clazz.grade = :grade) ";
			parameters.put("grade", grade);
		}
		if(classId != null && !classId.trim().isEmpty()){
			hql += " and (s.student.clazz.id = :classId) ";
			parameters.put("classId", classId);
		}
		if(studentName != null && !studentName.trim().isEmpty()){
			hql += " and (s.student.code like :studentName or s.student.name like :studentName) ";
			parameters.put("studentName", "%"  + studentName + "%");
		}
		if(start != null && !start.trim().isEmpty()){
			hql += " and (s.date >= :start) ";
			parameters.put("start", start);
		}
		if(end != null && !end.trim().isEmpty()){
			hql += " and (s.date <= :end)";
			parameters.put("end", end);
		}
		List<AbnAttendanceStatusReport> rows = new ArrayList<>();
		List<StudentAbnAttendance> list =  this.studentAbnAttendanceDao.find(hql, parameters, null, null);
		int total = list.size();
		Map<Integer,AbnAttendanceStatusReport> cache = Collections.synchronizedMap(new HashMap<Integer,AbnAttendanceStatusReport>());
		for(int i = 0; i < list.size(); i ++){
			StudentAbnAttendance data = list.get(i);
			if(data == null) continue;
			AbnAttendanceStatusReport report = cache.get(data.getStatus());
			if(report == null){
				report = new AbnAttendanceStatusReport();
				report.setTotal(total);
				report.setStatus(data.getStatus());
				report.setCount(0);
			}
			report.setCount(report.getCount() + 1);
			cache.put(data.getStatus(), report);
		}
		for(AbnAttendanceStatusReport info: cache.values()){
			if(info != null){
				info.computerShare();
				rows.add(info);
			}
		}
		return rows;
	}

	@Override
	public synchronized boolean attendanceRegister(String classId, String date, Integer segment, UserInfo user) {
		if(classId == null || classId.trim().isEmpty()) return false;
		if(date == null || date.trim().isEmpty()) return false;
		ClassAttendanceRegister data = this.classAttendanceRegisterDao.loadAttendanceRegister(classId, date, segment);
		boolean isAdded = false;
		if(data == null){
			data = new ClassAttendanceRegister();
			data.setId(UUID.randomUUID().toString());
			isAdded = true;
		}
		if(classId != null && (data.getClazz() == null || !data.getClazz().getId().equalsIgnoreCase(classId))){
			Class clazz = this.classDao.load(Class.class, classId);
			if(clazz == null) return false;
			data.setClazz(clazz);
		}
		data.setDate(date);
		data.setSegment(segment);
		if(user != null){
			data.setCreateUserId(user.getTeacherId());
			data.setCreateUserName(user.getTeacherName());
		}
		data.setCreateTime(new Date());
		if(isAdded) this.classAttendanceRegisterDao.save(data);
		return true;
	}

	@Override
	public synchronized AttendanceRegisterInfo loadAttendanceRegister(String classId, String date, Integer segment) {
		ClassAttendanceRegister data = this.classAttendanceRegisterDao.loadAttendanceRegister(classId, date, segment);
		if(data == null) return null;
		AttendanceRegisterInfo info = new AttendanceRegisterInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	
	private List<StudentAbnAttendance> loadAbnAttendances(String classId,String date,Integer segment){
		String abn_hql = "from StudentAbnAttendance s where s.date = :date and s.segment = :segment and  s.student.clazz.id = :classId ";
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("date", date);
		parameters.put("segment", segment); 
		parameters.put("classId", classId);
			
		return this.studentAbnAttendanceDao.find(abn_hql, parameters, null, null);
	}

	@Override
	public List<AttendanceRecord> loadAttendanceRecords(String date, Integer segment) {
		List<AttendanceRecord> records = new ArrayList<>();
		String weekName = null;
		if(date != null && !date.trim().isEmpty()){
			try {
				weekName = DateUtil.DayOfWeekText(new SimpleDateFormat("yyyy-MM-dd").parse(date));
			} catch (ParseException e) {
				weekName = DateUtil.DayOfWeekText(new Date());
				e.printStackTrace();
			}
		}
		List<Class> classes =  this.classDao.find("from Class c where c.status = 1  order by c.joinYear desc,c.name", null, null, null);
		for(int i = 0; i < classes.size(); i++){
			Class c = classes.get(i);
			if(c == null) continue;
			AttendanceRecord record = new AttendanceRecord();
			record.setClassName(c.getName());
			record.setGrade(c.getGrade());
	        record.setWeek(weekName);
			record.setTotal(this.loadClassStudentCount(c.getId()));
			record.setSegment(segment);
			List<StudentAbnAttendance> absList = this.loadAbnAttendances(c.getId(), date, segment);
			if(absList == null || absList.size() == 0){
				record.setStatus( this.loadAttendanceRegister(c.getId(), date, segment) ==  null ? "未上报" :"全勤");
			}else {
				 record.setStatus("缺勤");
				 for(int j = 0; j < absList.size(); j++){
					 StudentAbnAttendance abn = absList.get(j);
					 if(abn == null) continue;
					 if(abn.getStatus() == 1){
						 record.setLate(record.getLate() + 1);
						 continue;
					 }
					 if(abn.getStatus() == 2){
						 record.setDisease(record.getDisease() + 1);
						 continue;
					 }
					 if(abn.getStatus() == 3){
						 record.setLeave(record.getLeave() + 1);
						 continue;
					 }
					 if(abn.getStatus() == 4){
						 record.setOther(record.getOther() + 1);
						 continue;
					 }
				 }
			}
			records.add(record);
		}
		return records;
	}
}