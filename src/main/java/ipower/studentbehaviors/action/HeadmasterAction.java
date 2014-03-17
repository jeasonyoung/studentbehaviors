package ipower.studentbehaviors.action;

import java.io.IOException;

import ipower.studentbehaviors.modal.HeadmasterInfo;
import ipower.studentbehaviors.service.IHeadmasterService;
/**
 * 班主任Action。
 * @author yangyong.
 * @since 2014-03-05.
 * */
public class HeadmasterAction extends BaseDataAction<HeadmasterInfo> {
	private HeadmasterInfo info = new HeadmasterInfo();
	
	@Override
	public HeadmasterInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
	
	public void headmasters() throws IOException{
		if(this.service instanceof IHeadmasterService){
			this.writeJson(((IHeadmasterService)this.service).headmasters(this.getModel().getClassId(), this.getModel().getType()));
		}
	}
	
	public void classes() throws IOException{
		if(this.service instanceof IHeadmasterService){
			if(this.getUserInfo() != null){
				this.info.setTeacherId(this.getUserInfo().getTeacherId());
			}
			this.writeJson(((IHeadmasterService)this.service).headmasterClasses(this.getModel().getTeacherId()));
		}
	}
}