package ipower.studentbehaviors.action;

import java.io.IOException;

import ipower.studentbehaviors.modal.StudentAbnAttendanceInfo;
import ipower.studentbehaviors.service.IStudentAbnAttendanceService;

/**
 * 学生考勤异常Action.
 * @author yangyong.
 * @since 2014-03-12.
 * */
public class AbnAttendanceAction extends BaseDataAction<StudentAbnAttendanceInfo> {
	private StudentAbnAttendanceInfo info = new StudentAbnAttendanceInfo();
	
	@Override
	public StudentAbnAttendanceInfo getModel() {
		return this.info;
	}

	@Override
	public void update() throws IOException{
		throw new IOException("该方法未实现，请使用学生考勤Action");
	}
	
	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
	
	public void total() throws IOException{
		if(this.service instanceof IStudentAbnAttendanceService){
			this.writeJson(((IStudentAbnAttendanceService)this.service).total(this.getModel().getClassId(), 
																			  this.getModel().getDate(), 
																			  this.getModel().getSegment()));
		}
	}
}