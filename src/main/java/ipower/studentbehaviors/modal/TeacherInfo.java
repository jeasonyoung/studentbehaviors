package ipower.studentbehaviors.modal;

import ipower.model.Paging;

/**
 * 教师信息。
 * @author yangyong.
 * @since 2014-02-26.
 * */
public class TeacherInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,account,name,sex,titles,phone,jobCategory;
	/**
	 * 获取教师ID。
	 * @return 教师ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置教师ID。
	 * @param id
	 * 	教师ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取教师账号。
	 * @return 教师账号。
	 * */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置教师账号。
	 * @param account
	 * 	教师账号。
	 * */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取教师姓名。
	 * @return 教师姓名。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置教师姓名。
	 * @param name
	 * 	教师姓名。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取教师性别。
	 * @return 教师性别。
	 * */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置教师性别。
	 * @param sex
	 * 	教师性别。
	 * */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取职称。
	 * @return 职称。
	 * */
	public String getTitles() {
		return titles;
	}
	/**
	 * 设置职称。
	 * @param titles
	 * 	设置职称。
	 * */
	public void setTitles(String titles) {
		this.titles = titles;
	}
	/**
	 * 获取电话。
	 * @return 电话。
	 * */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置电话。
	 * @param phone
	 * 	电话。
	 * */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取职务类别。
	 * @return 职务类别。
	 * */
	public String getJobCategory() {
		return jobCategory;
	}
	/**
	 * 设置职务类别。
	 * @param jobCategory
	 *  职务类别。
	 * */
	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}
}