package ipower.studentbehaviors.action;

import ipower.studentbehaviors.service.ISchoolService;

/**
 * 学校Action。
 * @author yangyong.
 * @since 2014-02-20.
 * */
public class SchoolAction extends BaseAction {
	private ISchoolService schoolService;
	/**
	 * 设置学校服务。
	 * @param schoolService
	 * 学校服务接口。
	 * */
	public void setSchoolService(ISchoolService schoolService) {
		this.schoolService = schoolService;
	}
	
}