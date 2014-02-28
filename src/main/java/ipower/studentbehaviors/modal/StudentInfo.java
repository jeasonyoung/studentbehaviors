package ipower.studentbehaviors.modal;

import ipower.model.Paging;

/**
 * 学生信息。
 * @author yangyong.
 * @since 2014-02-28.
 * */
public class StudentInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String classId,className,id,code,name,gender,idCard;
	private Integer joinYear,status;
	/**
	 * 获取所属班级ID。
	 * @return 所属班级ID。
	 * */
	public String getClassId() {
		return classId;
	}
	/**
	 * 设置所属班级ID。
	 * @param classId
	 * 	所属班级ID。
	 * */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * 获取所属班级名称。
	 * @return 所属班级名称。
	 * */
	public String getClassName() {
		return className;
	}
	/**
	 * 设置所属班级名称。
	 * @param className
	 * 	所属班级名称。
	 * */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * 获取学生ID。
	 * @return 学生ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置学生ID。
	 * @param id
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取学生代码。
	 * @return 学生代码。
	 * */
	public String getCode() {
		return code;
	}
	/**
	 * 设置学生代码。
	 * @param code
	 * 学生代码。
	 * */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取学生姓名。
	 * @return 学生姓名。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置学生姓名。
	 * @param name
	 * 学生姓名。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取学生性别。
	 * @return 学生性别。
	 * */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置学生性别。
	 * @param gender
	 * 性别。
	 * */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取身份证号。
	 * @return 身份证号。
	 * */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置身份证号。
	 * @param idCard
	 * 身份证号。
	 * */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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
	 * 入学年份。
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