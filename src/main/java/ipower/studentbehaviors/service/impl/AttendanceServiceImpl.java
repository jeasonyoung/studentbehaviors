package ipower.studentbehaviors.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ipower.model.DataGrid;
import ipower.studentbehaviors.dao.IStudentAbnAttendanceDao;
import ipower.studentbehaviors.dao.IStudentDao;
import ipower.studentbehaviors.domain.Student;
import ipower.studentbehaviors.domain.StudentAbnAttendance;
import ipower.studentbehaviors.modal.AttendanceInfo;
import ipower.studentbehaviors.service.IAttendanceService;

/**
 * 学生考勤服务实现类。
 * @author yangyong.
 * @since 2014-03-09.
 * */
public class AttendanceServiceImpl implements IAttendanceService {
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
	public DataGrid<AttendanceInfo> datagrid(String classId, String date, Integer segment) {
		DataGrid<AttendanceInfo> grid = new DataGrid<AttendanceInfo>();
		final String hql = "select s.id as studentId,s.name as studentName,s.gender "
							+ " b.id,b.date,b.remarks,b.status,b.createUserId,b.createUserName "
							+ " from Student s "
							+ " left outer join StudentAbnAttendance b "
							+ "	on (b.student.id = s.id) and (b.date = :date) and (b.segment = :segment)"
							+ " where (s.status = 1) and (s.clazz.id = :classId)";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("classId", classId);
		parameters.put("date", date);
		parameters.put("segment", segment);
		
		List<AttendanceInfo> rows = new ArrayList<AttendanceInfo>();
		List<?> list = this.studentAbnAttendanceDao.find(hql, parameters);
		if(list != null){
			for(int i = 0; i < list.size(); i++){
				if(list.get(i) instanceof AttendanceInfo){
					rows.add((AttendanceInfo)list.get(i));
				}
			}
		}
		grid.setRows(rows);
		grid.setTotal((long)list.size());
		return grid;
	}

	@Override
	public synchronized AttendanceInfo update(AttendanceInfo info) {
		if(info == null) return null;
		StudentAbnAttendance data = null;
		boolean isAdded = false;
		if(info.getId() != null && !info.getId().trim().isEmpty()){
			data = this.studentAbnAttendanceDao.load(StudentAbnAttendance.class, info.getId());
		}else {
			isAdded = true;
			data = new StudentAbnAttendance();
			data.setId(UUID.randomUUID().toString());
			data.setDate(info.getDate());
			data.setSegment(info.getSegment());
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
}