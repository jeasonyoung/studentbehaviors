package ipower.studentbehaviors.action;

import ipower.studentbehaviors.modal.ClassInfo;

/**
 * 班级Action。
 * @author yangyong.
 * @since 2014-02-28.
 * */
public class ClassAction extends BaseDataAction<ClassInfo> {
	private ClassInfo info = new ClassInfo();
	
	@Override
	public ClassInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
}