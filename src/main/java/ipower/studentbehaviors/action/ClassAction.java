package ipower.studentbehaviors.action;

import java.io.IOException;

import ipower.studentbehaviors.modal.ClassInfo;
import ipower.studentbehaviors.service.IClassService;

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
	/**
	 * 获取全部班级数据。
	 * @throws IOException 
	 * */
	public void all() throws IOException{
		if(this.service instanceof IClassService){
			this.writeJson(((IClassService)this.service).all());
		}
	}
}