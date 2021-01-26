package com.lx.attendance.model;

import java.util.List;

import com.lx.attendance.model.vo.ResourcesTypeVO;

/**
 * 菜单实体类
 */
public class MenuDTO {
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
	/**
	 * 父级名称
	 */

	private String parentName;
	/**
	 * 对应的资源类型列表(除了其他功能外)
	 */
	private List<ResourcesTypeVO> typeList;
	/**
	 * 对应的资源类型列表(其他功能)
	 */
	private List<ResourcesTypeVO> otherList;

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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<ResourcesTypeVO> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<ResourcesTypeVO> typeList) {
		this.typeList = typeList;
	}

	public List<ResourcesTypeVO> getOtherList() {
		return otherList;
	}

	public void setOtherList(List<ResourcesTypeVO> otherList) {
		this.otherList = otherList;
	}
}
