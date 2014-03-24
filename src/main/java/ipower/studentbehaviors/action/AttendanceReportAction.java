package ipower.studentbehaviors.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ipower.model.DataGrid;
import ipower.studentbehaviors.modal.ClassAttendanceReport;
import ipower.studentbehaviors.service.IAttendanceService;
import ipower.utils.DateUtil;

/**
 * 考勤统计Action。
 * @author yangyong.
 * @since 2014-03-18.
 * */
public class AttendanceReportAction extends BaseAction {
	private IAttendanceService service;
	private String grade,date,start,end;
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
	 * 设置开始日期。
	 * @param start
	 * 	开始日期。
	 * */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * 设置结束日期。
	 * @param end
	 * 	结束日期。
	 * */
	public void setEnd(String end) {
		this.end = end;
	}
	/**
	 * 日报表列表result。
	 * @return
	 * */
	public String dailyReportList(){
		return "dailyReportList";
	}
	/**
	 * 周报表列表result
	 * */
	public String weekReportList(){
		return "weekReportList";
	}
	/**
	 * 班级日报。
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
	/**
	 * 获取周日期。
	 * @throws ParseException 
	 * @throws IOException 
	 * */
	public void week() throws ParseException, IOException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(this.date == null || this.date.trim().isEmpty()){
			this.date = format.format(new Date());
		}
		Date dt_date = format.parse(this.date),
			  dt_start = DateUtil.firstDayOfWeek(dt_date),
			  dt_end = DateUtil.lastDayOfWeek(dt_date);
		String[] result = { format.format(dt_start),format.format(dt_end)};
		this.writeJson(result);
	}
	/**
	 * 班级周报。
	 * @throws IOException 
	 * */
	public void classWeekReport() throws IOException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(this.start == null || this.start.trim().isEmpty()){
			this.start = format.format(DateUtil.firstDayOfWeek(new Date()));
		}
		if(this.end == null || this.end.trim().isEmpty()){
			this.end = format.format(DateUtil.lastDayOfWeek(new Date()));
		}
		DataGrid<ClassAttendanceReport> grid = new DataGrid<>();
		List<ClassAttendanceReport> rows = this.service.classWeekReport(this.grade, this.start,this.end);
		grid.setRows(rows);
		grid.setTotal((long)rows.size());
		this.writeJson(grid);
	}
}