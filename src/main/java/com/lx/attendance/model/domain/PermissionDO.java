package com.lx.attendance.model.domain;

public class PermissionDO {
	
    /**
     * 主键id
     */
    private Long id;
    
    /**
     * 资源集
     */
    private String resourcesId;
    
    /**
     * 角色id
     */
    private int roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
