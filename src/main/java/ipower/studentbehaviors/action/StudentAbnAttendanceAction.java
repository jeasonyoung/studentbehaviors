package ipower.studentbehaviors.action;

import ipower.studentbehaviors.modal.StudentAbnAttendanceInfo;

/**
 * 学生异常考勤Action。
 * @author yangyong.
 * @since 2014-03-07.
 * */
public class StudentAbnAttendanceAction extends BaseDataAction<StudentAbnAttendanceInfo> {
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