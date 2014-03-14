package ipower.studentbehaviors.action;

import java.io.IOException;

import ipower.studentbehaviors.modal.StudentInfo;
import ipower.studentbehaviors.service.IStudentService;

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

	public void students() throws IOException{
		if(this.service instanceof IStudentService){
			this.writeJson(((IStudentService)this.service).loadStudents(this.getModel().getClassId()));
		}
	}
	
	public void number() throws IOException{
		if(this.service instanceof IStudentService){
			this.writeJson(((IStudentService)this.service).number(this.getModel().getClassId()));
		}
	}
}