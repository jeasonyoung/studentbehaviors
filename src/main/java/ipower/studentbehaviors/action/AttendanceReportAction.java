package ipower.studentbehaviors.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ipower.model.DataGrid;
import ipower.studentbehaviors.modal.ClassAttendanceReport;
import ipower.studentbehaviors.service.IAttendanceService;

/**
 * 考勤统计Action。
 * @author yangyong.
 * @since 2014-03-18.
 * */
public class AttendanceReportAction extends BaseAction {
	private IAttendanceService service;
	private String grade,date;
	/**
	 * 设置考勤服务接口。
	 * @param attendanceService
	 * 	考勤服务接口。
	 * */
	public void setService(IAttendanceService service) {
		this.service = service;
	}
	/**
	 * 设置所属年级。
	 * @param grade
	 * 	所属年级。
	 * */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 设置日期。
	 * @param date
	 * 	日期。
	 * */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 日报表列表result。
	 * @return
	 * */
	public String dailyReportList(){
		return "dailyReportList";
	}
	/**
	 * 班级日期。
	 * @throws IOException 
	 * */
	public void classDailyReport() throws IOException{
		if(this.date == null || this.date.trim().isEmpty()){
			this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		DataGrid<ClassAttendanceReport> grid = new DataGrid<>();
		List<ClassAttendanceReport> rows = this.service.classDailyReport(this.grade, this.date);
		grid.setRows(rows);
		grid.setTotal((long)rows.size());
		this.writeJson(grid);
	}
}