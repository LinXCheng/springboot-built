package com.lx.attendance.dao;

import com.lx.attendance.model.domain.PermissionDO;
import org.apache.ibatis.annotations.Param;


/**
 * 权限管理
 */
public interface PermissionDao {

    /**
     * 新增用户角色权限
     * @param permissionDO
     */
    public void insertRolePermission(PermissionDO permissionDO);

    /**
     * 更新用户角色权限
     * @param permissionDO
     */
    public void updateRolePermission(PermissionDO permissionDO);

    /**
     * 查找角色权限根据id
     * @param id
     * @return
     */
    public PermissionDO findRolePermission(Integer id);

    /**
     * 删除角色权限
     * @param roleId
     */
    public void deleteRolePermission(@Param("roleId") Integer roleId);

}
