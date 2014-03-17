package ipower.studentbehaviors.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import ipower.studentbehaviors.dao.ITeacherDao;
import ipower.studentbehaviors.dao.IUserDao;
import ipower.studentbehaviors.domain.Teacher;
import ipower.studentbehaviors.domain.User;
import ipower.studentbehaviors.modal.UserInfo;
import ipower.studentbehaviors.service.IUserService;

/**
 * 用户服务接口实现。
 * @author yangyong.
 * @since 2014-03-05.
 * */
public class UserServiceImpl extends DataServiceImpl<User,UserInfo> implements IUserService {
	private IUserDao userDao;
	private ITeacherDao teacherDao;
	
	@Override
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void setTeacherDao(ITeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Override
	public String[] roles(String teacherId) {
		final String hql = "from User u where u.teacher.id = :teacherId ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("teacherId", teacherId);
		List<User> list = this.userDao.find(hql, parameters, null, null);
		if(list == null || list.size() == 0) return null;
		List<String> rolesList = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++){
			User u = list.get(i);
			if(u == null || u.getRole() == null || u.getRole().trim().isEmpty()){
				continue;
			}
			if(!rolesList.contains(u.getRole())){
				rolesList.add(u.getRole());
			}
		}
		if(rolesList.size() == 0) return null;
		String[] roles = new String[rolesList.size()];
		for(int i = 0; i < rolesList.size(); i++){
			roles[i] = rolesList.get(i);
		}
		return roles;
	}

	@Override
	protected List<User> find(UserInfo info) {
		String hql = "from User u where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort().equalsIgnoreCase("teacherName")){
			info.setSort("teacher.name");
		}
		if(info.getSort() != null){
			hql += " order by u." + info.getSort() + " " + info.getOrder();
		}
		return this.userDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected UserInfo changeModel(User data) {
		if(data == null) return null;
		UserInfo info = new UserInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getTeacher() != null){
			info.setTeacherId(data.getTeacher().getId());
			info.setTeacherName(data.getTeacher().getName());
		}
		return info;
	}

	@Override
	protected Long total(UserInfo info) {
		String hql = "select count(*) from User u where 1=1 ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		hql = this.addWhere(info, hql, parameters);
		return this.userDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(UserInfo info, String hql, Map<String, Object> parameters) {
		if(info.getRole() != null && !info.getRole().trim().isEmpty()){
			hql += " and (u.role like :role) ";
			parameters.put("role", "%"+ info.getRole() +"%");
		}
		if(info.getTeacherName() != null && !info.getTeacherName().trim().isEmpty()){
			hql += " and (u.teacher.name like :teacherName) ";
			parameters.put("teacherName", "%"+ info.getTeacherName() +"%");
		}
		return hql;
	}

	@Override
	public UserInfo update(UserInfo info) {
		if(info != null){
			final String hql = "from User u where u.teacher.id = :teacherId and u.role = :role";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("teacherId", info.getTeacherId());
			parameters.put("role", info.getRole());
			List<User> list = this.userDao.find(hql, parameters, null, null);
			boolean isAdded = false;
			User data = null;
			if(list == null || list.size() == 0){
				data = new User();
				isAdded = true;
			}else {
				data = list.get(0);
			}
			if(info.getTeacherId() != null && (data.getTeacher() == null || !info.getTeacherId().equalsIgnoreCase(data.getTeacher().getId()))){
				Teacher teacher = this.teacherDao.load(Teacher.class, info.getTeacherId());
				if(teacher != null){
					data.setTeacher(teacher);
					info.setTeacherName(teacher.getName());
				}
			}
			if(data.getTeacher() != null && (info.getTeacherName() == null || info.getTeacherName().trim().isEmpty())){
				info.setTeacherName(data.getTeacher().getName());
			}
			
			BeanUtils.copyProperties(info, data);
			if(isAdded)this.userDao.save(data);
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			String[] arr = ids[i].split("#");
			if(arr != null){
				this.delete(arr[0], arr.length > 1 ? arr[1] : "");
			}
		}
	}
	
	private void delete(String teacherId,String role){
		final String hql = "from User u where  u.teacher.id = :teacherId and u.role = :role";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("teacherId", teacherId);
		parameters.put("role", role);
		List<User> list = this.userDao.find(hql, parameters, null, null);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				this.userDao.delete(list.get(i));
			}
		}
	}

	@Override
	public UserInfo loadUser(String account) {
		if(account == null || account.trim().isEmpty()) return null;
		final String hql = "from User u where u.teacher.account = :account ";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("account", account);
		List<User> list = this.userDao.find(hql, parameters, null, null);
		if(list == null || list.size() == 0) return null;
		UserInfo info = null;
		for(int i = 0; i < list.size(); i++){
			User u = list.get(i);
			if(u == null) continue;
			if(info == null && u.getTeacher() != null){
				info = new UserInfo();
				info.setTeacherId(u.getTeacher().getId());
				info.setTeacherName(u.getTeacher().getName());
			}
			if(info == null)continue;
			if(info.getRole() != null && !info.getRole().trim().isEmpty()){
				info.setRole(info.getRole() + ",");
			}
			info.setRole(info.getRole() + u.getRole());
		}
		return info;
	}
}