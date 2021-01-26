package com.lx.attendance.service.impl;

import java.util.Date;
import java.util.List;

import com.lx.attendance.dao.RoleDao;
import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.model.vo.RoleVO;
import com.lx.attendance.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 获取全部角色
     * @return
     */
    @Override
    public List<RoleVO> findRoleList(Integer roleId) {
        return roleDao.findRoleList(roleId);
    }
    /**
     * 更新角色状态根据id
     * @return
     */
    @Override
    public Integer updateRoleStateById(Integer id) {
        if(id != null){
            RoleDO roleDO = roleDao.findRoleById(id);
            if(roleDO.getState()==0){
            	roleDO.setState(1);
            }else{
            	roleDO.setState(0);
            }
            roleDO.setUpdateTime(new Date());
            roleDao.updateRoleById(roleDO);
            return roleDO.getState();
        }else{
            return null;
        }
    }

    /**
     * 修改或者新增角色
     * @param RoleVO
     */
    @Override
    public void updateRoleById(RoleDO roleDO) {
    	roleDO.setUpdateTime(new Date());
        if (roleDO.getId() != null) {
            roleDao.updateRoleById(roleDO);
        } else {
        	roleDO.setCreateTime(new Date());
            roleDao.insertRole(roleDO);
        }
    }

    /**
     * 根据角色id查询内容
     * @param id
     * @return
     */
    @Override
    public RoleDO findRoleById(Integer id) {
        return roleDao.findRoleById(id);
    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    public void deleteRoleId(Integer id) {
        roleDao.deleteRoleId(id);
    }
}
