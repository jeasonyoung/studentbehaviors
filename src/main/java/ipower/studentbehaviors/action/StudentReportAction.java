package ipower.studentbehaviors.action;

import java.io.IOException;

import ipower.studentbehaviors.modal.AbnAttendanceInfo;
import ipower.studentbehaviors.service.IAttendanceService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 学生报表Action。
 * @author yangyong.
 * @since 2014-03-26.
 * */
public class StudentReportAction extends BaseAction implements ModelDriven<AbnAttendanceInfo> {
	private AbnAttendanceInfo info = new AbnAttendanceInfo();
	private IAttendanceService service;
	
	@Override
	public AbnAttendanceInfo getModel() {
		return this.info;
	}
	/**
	 * 设置考勤服务接口。
	 * @param attendanceService
	 * 	考勤服务接口。
	 * */
	public void setService(IAttendanceService service) {
		this.service = service;
	}
	/**
	 * 学生异常信息。
	 * @throws IOException 
	 * */
	public void students() throws IOException{
		this.writeJson(this.service.students(this.getModel()));
	}
}