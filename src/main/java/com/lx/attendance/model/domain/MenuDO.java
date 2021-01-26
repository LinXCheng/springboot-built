package com.lx.attendance.model.domain;

public class MenuDO {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 级别
	 */
	private int level;
	/**
	 * 父级id
	 */
	private long pid;
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 菜单类型
	 * 
	 */
	private int menuType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
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

	public int getMenuType() {
		return menuType;
	}

	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}
}
