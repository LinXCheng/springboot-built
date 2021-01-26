package com.lx.attendance.service.impl;

import com.lx.attendance.model.domain.PermissionDO;
import com.lx.attendance.service.PermissionService;
import com.lx.attendance.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * 角色、权限关联
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	/**
	 * 用户权限
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public PermissionDO rolePermission(Integer roleId) {
		return permissionDao.findRolePermission(roleId);
	}

	/**
	 * 新增角色权限
	 * 
	 * @param permissionDTO
	 */
	@Override
	public void insertRolePermission(PermissionDO permissionDTO) {
		permissionDao.insertRolePermission(permissionDTO);
	}

	/**
	 * 更新角色权限
	 * 
	 * @param permissionDO
	 */
	@Caching(evict = {@CacheEvict("menu_List"), @CacheEvict(value = "authorization")})
	@Override
	public void updateRolePermission(PermissionDO permissionDO) {
		permissionDao.updateRolePermission(permissionDO);
	}

	/**
	 * 删除角色权限
	 * @param roleId
	 */
	@Override
	public void deleteRolePermission(Integer roleId) {
		permissionDao.deleteRolePermission(roleId);
	}
}
