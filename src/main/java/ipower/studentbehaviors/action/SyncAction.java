package ipower.studentbehaviors.action;

import java.io.IOException;

import ipower.model.Json;
import ipower.studentbehaviors.dao.IClassDao;
import ipower.studentbehaviors.domain.Class;
import ipower.studentbehaviors.service.ISyncDataService;

/**
 * 同步数据Action。
 * @author yangyong.
 * @since 2014-03-03.
 * */
public class SyncAction extends BaseAction {
	private ISyncDataService syncDataService;
	private IClassDao classDao;
	private String syncSchoolName,classId;
	
	public void setSyncDataService(ISyncDataService syncDataService){
		this.syncDataService = syncDataService;
	}
	
	public void setClassDao(IClassDao classDao) {
		this.classDao = classDao;
	}

	public void setSyncSchoolName(String syncSchoolName) {
		this.syncSchoolName = syncSchoolName;
	}
 
	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	public void teachers() throws IOException{
		Json json = new Json();
		try{
			this.syncDataService.syncTeachers(this.syncSchoolName);
			json.setSuccess(true);
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			e.printStackTrace();
		}finally{
			this.writeJson(json);
		}
	}
	
	public void classes() throws IOException{
		Json json = new Json();
		try{
			this.syncDataService.syncClasses(this.syncSchoolName);
			json.setSuccess(true);
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			e.printStackTrace();
		}finally{
			this.writeJson(json);
		}
	}
	
	public void students() throws IOException{
		Json json = new Json();
		try{
			String[] ids = this.classId.split("\\|");
			if(ids == null || ids.length == 0){
				json.setSuccess(false);
				json.setMsg("没有传入班级ID！");
				return;
			}
			for(String id : ids){
				if(id == null || id.trim().isEmpty()) continue;
				Class data = this.classDao.load(Class.class, id);
				if(data == null) continue;
				this.syncDataService.syncStudents(this.syncSchoolName, data);
			}
			json.setSuccess(true);
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			e.printStackTrace();
		}finally{
			this.writeJson(json);
		}
	}
}