package ipower.studentbehaviors.domain;

import java.io.Serializable;

/**
 * 班级信息。
 * @author 杨勇.
 * @since 2014-02-20.
 * */
public class Class implements Serializable {
	private static final long serialVersionUID = 1L;
	private School school;
	private String id,code,name,level,grade;
	private Integer joinYear,status;
	/**
	 * 获取所属学校。
	 * @return 所属学校。
	 * */
	public School getSchool() {
		return school;
	}
	/**
	 * 设置所属学校。
	 * @param school
	 * 	所属学校。
	 * */
	public void setSchool(School school) {
		this.school = school;
	}
	/**
	 * 获取班级ID。
	 * @return 获取班级ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置班级ID。
	 * @param id
	 *  班级Id。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取班级代码。
	 * @return 班级代码。
	 * */
	public String getCode() {
		return code;
	}
	/**
	 * 设置班级代码。
	 * @param code
	 * 设置班级代码。
	 * */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取班级名称。
	 * @return 班级名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置班级名称。
	 * @param name
	 * 班级名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取学习阶段。
	 * @return 学习阶段。
	 * */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置学习阶段。
	 * @param level
	 * 学习阶段。
	 * */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取当前年级。
	 * @return 当前年级。
	 * */
	public String getGrade() {
		return grade;
	}
	/**
	 * 设置当前年级。
	 * @param grade
	 * 当前年级。
	 * */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 获取入学年份。
	 * @return 入学年份。
	 * */
	public Integer getJoinYear() {
		return joinYear;
	}
	/**
	 * 设置入学年份。
	 * @param joinYear
	 *  入学年份。
	 * */
	public void setJoinYear(Integer joinYear) {
		this.joinYear = joinYear;
	}
	/**
	 * 获取状态。
	 * @return 状态(0-不在校，1-在校)。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置状态。
	 * @param status
	 * 状态(0-不在校，1-在校)。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
}