package com.lx.attendance.service;

import com.lx.attendance.model.domain.PermissionDO;

/**
 * 后台权限管理
 */
public interface PermissionService {
    /**
     * 用户权限
     * @param roleId
     * @return
     */
    public PermissionDO rolePermission(Integer roleId);

    /**
     * 新增角色权限
     * @param permissionDTO
     */
    public void insertRolePermission(PermissionDO permissionDTO);

    /**
     * 更新角色权限
     * @param permissionDTO
     */
    public void updateRolePermission(PermissionDO permissionDTO);

    /**
     * 删除角色权限
     * @param roleId
     */
    public void deleteRolePermission(Integer roleId);
}
