package ipower.studentbehaviors.action;

import ipower.studentbehaviors.service.IMenuService;

import java.io.IOException;

/**
 * 菜单服务Action。
 * @author 杨勇。
 * @since 2014-01-18。
 * */
public class MenuAction extends BaseAction {
	private IMenuService menuService;
	private String systemId,moduleId;
	/**
	 * 设置菜单服务。
	 * @param menuService
	 * 菜单服务。
	 * */
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	/**
	 * 设置系统ID。
	 * @param systemId
	 * 系统ID。
	 * */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	/**
	 * 设置模块ID。
	 * @param moduleId
	 *  模块ID。
	 * */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * 系统信息。
	 * @throws IOException 
	 * */
	public void system() throws IOException{
		if(this.menuService != null && (this.systemId != null && !this.systemId.trim().isEmpty())){
			this.writeJson(this.menuService.loadModuleSystem(this.systemId));
		}
	}
	/**
	 * 查找子模块信息。
	 * @throws IOException 
	 * */
	public void children() throws IOException{
		if(this.menuService != null && (this.systemId != null && !this.systemId.trim().isEmpty()) && (this.moduleId != null && !this.moduleId.trim().isEmpty())){
			this.writeJson(this.menuService.children(this.systemId, this.moduleId));
		}
	}
}