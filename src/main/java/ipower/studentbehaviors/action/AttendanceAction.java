package ipower.studentbehaviors.action;
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
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
}