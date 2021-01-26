package com.lx.attendance.model.vo;

/**
 * 资源实体类
 */
public class ResourcesPathVO {

	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 名称
	 */
	private String description;
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 资源编码
	 */
	private String code;
	/**
	 * 所属菜单id
	 */
	private Long menuId;
	/**
	 * 所属类型
	 */
	private int type;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 菜单名称
	 */
	private String menuName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
