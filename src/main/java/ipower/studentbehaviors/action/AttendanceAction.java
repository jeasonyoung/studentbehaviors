package ipower.studentbehaviors.action;
import java.io.IOException;

import ipower.studentbehaviors.modal.AttendanceInfo;
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
	
	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
}