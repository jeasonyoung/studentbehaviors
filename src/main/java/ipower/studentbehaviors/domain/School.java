package ipower.studentbehaviors.domain;

import java.io.Serializable;

/**
 * 学校信息。
 * @author 杨勇.
 * @since 2014-02-20.
 * */
public class School implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,code,name,type;
	/**
	 * 获取学校ID。
	 * @return 学校ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置学校ID。
	 * @param id
	 * 	学校ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取学校代码。
	 * @return 学校代码。
	 * */
	public String getCode() {
		return code;
	}
	/**
	 * 设置学校代码。
	 * @param code
	 * 学校代码。
	 * */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取学校名称。
	 * @return 学校名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置学校名称。
	 * @param name
	 * 	学校名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取学校类型。
	 * @return 学校类型。
	 * */
	public String getType() {
		return type;
	}
	/**
	 * 设置学校类型。
	 * @param type
	 *  学校类型。
	 * */
	public void setType(String type) {
		this.type = type;
	}
}