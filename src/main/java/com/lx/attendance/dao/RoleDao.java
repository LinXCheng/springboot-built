package com.lx.attendance.dao;


import java.util.List;

import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.model.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */
    public List<RoleVO>  findRoleList(@Param("roleId") Integer roleId);

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    public RoleDO findRoleById(Integer id);

    /**
     * 更新角色
     * @param RoleVO
     */
    public void updateRoleById(RoleDO roleDO);

    /**
     * 新增角色
     * @param RoleVO
     */
    public void insertRole(RoleDO roleDO);

    /**
     * 删除角色
     * @param id
     */
    public void deleteRoleId(Integer id);
}
