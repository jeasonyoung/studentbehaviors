package ipower.studentbehaviors.action;

import ipower.studentbehaviors.modal.StudentInfo;

/**
 * 学生Action。
 * @author yangyong.
 * @since 2014-03-01.
 * */
public class StudentAction extends BaseDataAction<StudentInfo> {
	private StudentInfo info = new StudentInfo();
	
	@Override
	public StudentInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}

}