package ipower.studentbehaviors.action;

import ipower.studentbehaviors.modal.UserInfo;

/**
 * 用户Action。
 * @author yangyong.
 * @since 2014-03-06.
 * */
public class UserAction extends BaseDataAction<UserInfo> {
	private UserInfo info = new UserInfo();
	
	@Override
	public UserInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getTeacherId();
	}
}