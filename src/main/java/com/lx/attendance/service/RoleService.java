package com.lx.attendance.service;

import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.model.vo.RoleVO;

import java.util.List;

public interface RoleService {
    /**
     * 获取全部角色
     * @return
     */
    public List<RoleVO> findRoleList(Integer roleId);

    /**
     * 更新角色状态根据id
     * @return
     */
    public Integer updateRoleStateById(Integer id);

    /**
     * 更新角色
     * @return
     */
    public void updateRoleById(RoleDO roleDO);
    /**
     * 根据角色id查询内容
     * @param id
     * @return
     */
    public RoleDO findRoleById(Integer id);

    /**
     * 删除角色
     * @param id
     */
    public void deleteRoleId(Integer id);
 }
