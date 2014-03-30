package ipower.studentbehaviors.action;
import java.io.IOException;

import ipower.studentbehaviors.modal.AttendanceInfo;
import ipower.studentbehaviors.modal.AttendanceRegisterInfo;
import ipower.studentbehaviors.service.IAttendanceService;
/**
 * 学生考勤Action。
 * @author yangyong.
 * @since 2014-03-09.
 * */
public class AttendanceAction extends BaseDataAction<AttendanceInfo> {
	private AttendanceInfo info = new AttendanceInfo();
	
	@Override
	public AttendanceInfo getModel() {
		return this.info;
	}
	
	@Override
	public void update() throws IOException{
		if(this.getUserInfo() != null){
			this.info.setCreateUserId(this.getUserInfo().getTeacherId());
			this.info.setCreateUserName(this.getUserInfo().getTeacherName());
		}
		super.update();
	}
	/**
	 * 班级全勤登记。
	 * @return
	 *  登记成功为True，否则为false。
	 * */
	public void register() throws IOException{
		boolean result = false;
		if(this.service instanceof IAttendanceService){
			 result = ((IAttendanceService)this.service).attendanceRegister(this.getModel().getClassId(), this.getModel().getDate(), this.getModel().getSegment(), this.getUserInfo());
		}
		this.writeJson(result);
	}
	/**
	 * 加载班级全勤登记信息。
	 * */
	public void loadregister() throws IOException{
		AttendanceRegisterInfo info = ((IAttendanceService)this.service).loadAttendanceRegister(this.getModel().getClassId(), this.getModel().getDate(), this.getModel().getSegment());
		if(info == null) info = new AttendanceRegisterInfo();
		 this.writeJson(info);
	}
	
	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
}