package ipower.studentbehaviors.action;

import ipower.studentbehaviors.modal.TeacherInfo;

/**
 * 教师Action。
 * @author yangyong.
 * @since 2014-02-26.
 * */
public class TeachersAction extends BaseDataAction<TeacherInfo> {
	private TeacherInfo info = new TeacherInfo();

	@Override
	public TeacherInfo getModel() {
		return info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
}