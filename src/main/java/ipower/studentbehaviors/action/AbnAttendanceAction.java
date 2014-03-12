package ipower.studentbehaviors.action;

import ipower.studentbehaviors.modal.StudentAbnAttendanceInfo;

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
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}

}