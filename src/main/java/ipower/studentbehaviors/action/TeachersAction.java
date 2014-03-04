package ipower.studentbehaviors.action;

import java.io.IOException;

import ipower.studentbehaviors.modal.TeacherInfo;
import ipower.studentbehaviors.service.ITeachersService;

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
	/**
	 * 获取全部教师数据。
	 * @throws IOException 
	 * */
	public void all() throws IOException{
		if(this.service instanceof ITeachersService){
			this.writeJson(((ITeachersService)this.service).all());
		}
	}
}