package ipower.studentbehaviors.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ipower.cache.CacheEntity;
import ipower.cache.CacheListHandler;
import ipower.configuration.ModuleDefine;
import ipower.configuration.ModuleSystem;
import ipower.model.TreeNode;
import ipower.studentbehaviors.service.IMenuService;

/**
 * 菜单服务Action。
 * @author 杨勇。
 * @since 2014-01-18。
 * */
public class MenuAction extends BaseAction {
	private IMenuService menuService;
	private String systemId,roleAdmin,roleHeadmaster,roleView;
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
	 * 设置管理员角色菜单。
	 * @param roleAdmin
	 * 	管理员角色菜单。
	 * */
	public void setRoleAdmin(String roleAdmin) {
		this.roleAdmin = roleAdmin;
	}
	/**
	 * 设置班主任角色菜单。
	 * @param roleHeadmaster
	 * 	班主任角色菜单。
	 * */
	public void setRoleHeadmaster(String roleHeadmaster) {
		this.roleHeadmaster = roleHeadmaster;
	}
	/**
	 * 设置查看角色菜单。
	 * @param roleView
	 * 	查看角色菜单。
	 * */
	public void setRoleView(String roleView) {
		this.roleView = roleView;
	}
	/**
	 * 加载用户角色菜单。
	 * */
	private synchronized String loadUserRoleMenus(){
		if(this.getUserInfo() == null) return null;
		String key = this.getUserInfo().getTeacherId();
		if(key == null || key.trim().isEmpty()) return null;
		key = "Role_Menu_" + key;
		CacheEntity<?> entity = CacheListHandler.getCache(key);
		if(entity == null){
			 String role = this.getUserInfo().getRole();
			 if(role == null || role.trim().isEmpty()) return null;
			 List<String> list = new ArrayList<>();
			 role = role.toLowerCase();
			 if(role.indexOf("admin") > -1 && this.roleAdmin != null && !this.roleAdmin.trim().isEmpty()){
				 for(String m  : this.roleAdmin.split(",")){
					 if(m == null || m.trim().isEmpty()) continue;
					 if(!list.contains(m)) list.add(m);
				 }
			 }
			 if(role.indexOf("headmaster") > -1 && this.roleHeadmaster != null && !this.roleHeadmaster.trim().isEmpty()){
				 for(String m  : this.roleHeadmaster.split(",")){
					 if(m == null || m.trim().isEmpty()) continue;
					 if(!list.contains(m)) list.add(m);
				 }
			 }
			 if(role.indexOf("view") > -1 && this.roleView != null && !this.roleView.trim().isEmpty()){
				 for(String m  : this.roleView.split(",")){
					 if(m == null || m.trim().isEmpty()) continue;
					 if(!list.contains(m)) list.add(m);
				 }
			 }
			 if(list.size() == 0) return null;
			 entity = new CacheEntity<String>(key, StringUtils.join(list.toArray(new String[0]), ','));
			 CacheListHandler.addCache(key, entity, 60);
		}
		return (String)entity.getEntity();
	}
	/**
	 * 输出菜单树。
	 * @throws IOException 
	 * */
	public synchronized void tree() throws IOException{
		List<TreeNode> treeNodeList = new ArrayList<>();
		if(this.getUserInfo() != null){
			String key =  "ROLE_USER_" + this.getUserInfo().getTeacherId();
			CacheEntity<?> entity = CacheListHandler.getCache(key);
			if(entity == null && (this.menuService != null && (this.systemId != null && !this.systemId.trim().isEmpty()))){
				ModuleSystem ms = this.menuService.loadModuleSystem(this.systemId);
				String roleMenus = this.loadUserRoleMenus();
				if(roleMenus != null && !roleMenus.trim().isEmpty() && ms != null && ms.getModules() != null && ms.getModules().size() > 0){
					for(int i = 0; i < ms.getModules().size(); i++){
						TreeNode node = this.createTreeNode(ms.getModules().item(i), roleMenus);
						if(node != null) treeNodeList.add(node);
					}
				}
			}
			if(entity !=  null && entity.getEntity() instanceof NodeCache){
				treeNodeList = ((NodeCache)entity.getEntity()).getNodes();
			}
		}
		this.writeJson(treeNodeList);
	}
	/**
	 * 创建树结构节点。
	 * @param module
	 * 	菜单模块。
	 * @param roleMenus
	 *  角色菜单。
	 * */
	private synchronized TreeNode createTreeNode(ModuleDefine m, String roleMenus){
		if(m == null) return null;
		
		if(roleMenus.indexOf(m.getModuleID()) == -1) return null;
		
		TreeNode node = new TreeNode();
		node.setId(m.getModuleID());
		node.setText(m.getModuleName());
		Map<String,Object> attributes = new HashMap<String,Object>();
		attributes.put("url", m.getModuleUri());
		node.setAttributes(attributes);
		
		if(m.getModules() != null && m.getModules().size() > 0){
			node.setChildren(new ArrayList<TreeNode>());
			for(int i = 0; i < m.getModules().size(); i++){
				TreeNode n = this.createTreeNode(m.getModules().item(i), roleMenus);
				if(n != null) node.getChildren().add(n);
			}
		}
		
		return node;
	}
	
	class NodeCache implements Serializable{
		private static final long serialVersionUID = 1L;
		private List<TreeNode> nodes;
		/**
		 * 构造函数。
		 * @param nodes
		 * 缓存集合。
		 * */
		public NodeCache(List<TreeNode> nodes){
			this.setNodes(nodes);
		}
		public List<TreeNode> getNodes() {
			return nodes;
		}
		public void setNodes(List<TreeNode> nodes) {
			this.nodes = nodes;
		}
	}
}