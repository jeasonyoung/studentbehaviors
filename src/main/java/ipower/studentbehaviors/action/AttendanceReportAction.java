package ipower.studentbehaviors.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.thoughtworks.xstream.XStream;

import ipower.model.DataGrid;
import ipower.studentbehaviors.modal.AttendanceRecord;
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
	private String grade,date,start,end,classId,studentName;
	private Integer segment;
	private SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
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
	 * 设置班级ID。
	 * @param classId
	 * 	班级ID。
	 * */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * 设置学生姓名。
	 * @param studentName
	 * 	学生姓名。
	 * */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * 设置考勤段。
	 * @param segment
	 * 	考勤段。
	 * */
	public void setSegment(Integer segment) {
		this.segment = segment;
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
	 * 考勤异常状态列表。
	 * */
	public String statusReportList(){
		return "statusReportList";
	}
	/**
	 * 班级日报。
	 * @throws IOException 
	 * */
	public void classDailyReport() throws IOException{
		if(this.date == null || this.date.trim().isEmpty()){
			this.date =  this.dtFormat.format(new Date());
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
		if(this.date == null || this.date.trim().isEmpty()){
			this.date = this.dtFormat.format(new Date());
		}
		Date dt_date = this.dtFormat.parse(this.date),
			  dt_start = DateUtil.firstDayOfWeek(dt_date),
			  dt_end = DateUtil.lastDayOfWeek(dt_date);
		String[] result = { this.dtFormat.format(dt_start),this.dtFormat.format(dt_end)};
		this.writeJson(result);
	}
	/**
	 * 班级周报。
	 * @throws IOException 
	 * */
	public void classWeekReport() throws IOException{
		if(this.start == null || this.start.trim().isEmpty()){
			this.start = this.dtFormat.format(DateUtil.firstDayOfWeek(new Date()));
		}
		if(this.end == null || this.end.trim().isEmpty()){
			this.end = this.dtFormat.format(DateUtil.lastDayOfWeek(new Date()));
		}
		DataGrid<ClassAttendanceReport> grid = new DataGrid<>();
		List<ClassAttendanceReport> rows = this.service.classWeekReport(this.grade, this.start,this.end);
		grid.setRows(rows);
		grid.setTotal((long)rows.size());
		this.writeJson(grid);
	}
	/**
	 * 考勤异常状态。
	 * @throws IOException 
	 * */
	public void statusReport() throws IOException{
		if(this.start == null || this.start.trim().isEmpty()){
			this.start = this.dtFormat.format(DateUtil.firstDayOfWeek(new Date()));
		}
		if(this.end == null || this.end.trim().isEmpty()){
			this.end = this.dtFormat.format(DateUtil.lastDayOfWeek(new Date()));
		}
		this.writeJson( this.service.attendanceStatusReport(this.grade, this.classId, this.studentName, this.start, this.end));
 	}
	/**
	 * 加载考勤记录。
	 * */
	private List<AttendanceRecord> loadAttendanceRecords(){
		return this.service.loadAttendanceRecords(this.date, this.segment);
	}
	/**
	 * 考勤记录。
	 * */
	public void attendanceRecords() throws IOException{
		this.writeJson(this.loadAttendanceRecords());
	}
	/**
	 * 考勤记录XML。
	 * @throws IOException 
	 * */
	public void records() throws IOException{
			try {
				List<AttendanceRecord> list = this.loadAttendanceRecords();
				XStream xStream = new XStream();
				xStream.alias("xml", list.getClass());
				xStream.alias("item", new AttendanceRecord().getClass());
				
				HttpServletResponse response =ServletActionContext.getResponse();
				response.setContentType("text/xml;charset=utf-8");
				
				PrintWriter writer = response.getWriter();	
				writer.write(xStream.toXML(list));
				writer.flush();
				writer.close();
				
			} catch (IOException e) {
				e.printStackTrace();
				this.writeJson(e);
			}
	}
}