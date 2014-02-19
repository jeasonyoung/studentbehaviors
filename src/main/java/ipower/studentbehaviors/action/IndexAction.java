package ipower.studentbehaviors.action;
/**
 * 默认页面Action.
 * @author 杨勇.
 * @since 2013-11-27.
 * */
public class IndexAction extends BaseAction {
	private String systemId,moduleId;
	/**
	 * 获取系统ID。
	 * @return 系统ID。
	 * */
	public String getSystemId() {
		return systemId;
	}
	/**
	 * 设置系统ID。
	 * @param systemId
	 *  系统ID。
	 * */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	/**
	 * 获取模块ID。
	 * @return 模块ID。
	 * */
	public String getModuleId() {
		return moduleId;
	}
	/**
	 * 设置模块ID。
	 * @param moduleId
	 * 	设置模块ID。
	 * */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * 默认输出。
	 * */
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	/**
	 * 顶部Banner头。
	 * */
	public String top(){
		return "top";
	}
	/**
	 * 左边菜单。
	 * */
	public String leftmenu(){
		return "leftmenu";
	}
	/**
	 * 中间工作区域。
	 * */
	public String workspace(){
		return "workspace";
	}
	/**
	 * 底部footer。
	 * */
	public String footer(){
		return "footer";
	}
}