package ipower.studentbehaviors.action;

import java.io.IOException;

import com.opensymphony.xwork2.ModelDriven;

import ipower.model.Json;
import ipower.studentbehaviors.domain.School;
import ipower.studentbehaviors.service.ISchoolService;

/**
 * 学校Action。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public class SchoolAction extends BaseAction implements ModelDriven<School> {
	private ISchoolService schoolService;
	private School data = new School();
	/**
	 * 
	 * */
	@Override
	public School getModel() {
		return this.data;
	}
	/**
	 * 设置学校服务。
	 * @param schoolService
	 * 学校服务接口。
	 * */
	public void setSchoolService(ISchoolService schoolService) {
		this.schoolService = schoolService;
	}
	/**
	 * 加载学校数据。
	 * @throws IOException 
	 * */
	public void load() throws IOException{
		School school = this.schoolService.load();
		this.writeJson(school);
	}
	/**
	 * 更新学校数据。 
	 * @throws IOException 
	 * */
	public void update() throws IOException{
		Json result = new Json();
		try{
			this.schoolService.update(this.getModel());
			result.setSuccess(true);
		}catch(Exception e){
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}finally{
			this.writeJson(result);
		}
	}
}