package ipower.studentbehaviors.service;

import ipower.configuration.ModuleDefineCollection;
import ipower.configuration.ModuleSystem;
/**
 * 菜单服务接口。
 * @author 杨勇。
 * @since 2014-01-18。
 * */
public interface IMenuService {
	/**
	 * 设置菜单文件。
	 * @param moduleFile
	 * 	菜单文件。
	 * */
	void setMenuFile(String menuFile);
	/**
	 * 加载系统数据。
	 * @param systemId
	 * 	系统ID。
	 * @return 系统信息。
	 * */
	ModuleSystem loadModuleSystem(String systemId);
	/**
	 * 加载给定系统模块下的子集合。
	 * @param systemId
	 * 	系统ID。
	 * @param moduleId
	 *  模块ID。
	 * @return 模块子集合。
	 * */
	ModuleDefineCollection children(String systemId, String moduleId);
}