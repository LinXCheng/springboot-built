package com.lx.attendance.controller;

import com.lx.attendance.config.shiro.UserRealm;
import com.lx.attendance.model.domain.PermissionDO;
import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.service.PermissionService;
import com.lx.attendance.service.ResourcesPathService;
import com.lx.attendance.utils.ResultSet;

import com.lx.attendance.utils.constants.Constants;
import com.lx.attendance.utils.logControl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;

@Controller
@RequestMapping("/permission")
@ResponseBody
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	@Resource(name = "userRealm")
	private UserRealm userRealm;
	@Autowired
	private ResourcesPathService resourcesPathService;

	/**
	 * 保存权限
	 * @param roleId
	 * @param resourcesId
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@Transactional(noRollbackFor={RuntimeException.class, Exception.class})
	@Caching(evict = {@CacheEvict(value = "menu_List", allEntries = true, beforeInvocation = true)})
	public ResultSet<String> save(Integer roleId, String resourcesId) {
		try {
			if (roleId != null) {
				if(StringUtils.isNotBlank(resourcesId) && !"null".equals(resourcesId)){
					PermissionDO permissionDO = new PermissionDO();
					permissionDO.setResourcesId(resourcesId);
					permissionDO.setRoleId(roleId);
					if (permissionService.rolePermission(roleId) != null) {
						permissionService.updateRolePermission(permissionDO);
						Session session = SecurityUtils.getSubject().getSession();
						UserDO userDO = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
						List<String> userCode = resourcesPathService.findAllCodeList(userDO.getId());
						session.setAttribute(Constants.SESSION_USER_CODE, userCode);
					} else {
						permissionService.insertRolePermission(permissionDO);
					}
				}else{
					permissionService.deleteRolePermission(roleId);
				}
				userRealm.clearCached();
				return success();
			} else {
				return error("角色权限保存失败!");
			}
		} catch (Exception e) {
			logControl.logPrint(PermissionController.class,Long.valueOf(roleId),e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return error("角色权限保存失败!");
		}
	}

	/**
	 * 展示权限
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/showPermission")
	@ResponseBody
	public ResultSet<?> showPermission(Integer roleId) {
		if (roleId != null) {
			PermissionDO permissionDO = permissionService.rolePermission(roleId);
			if (permissionDO != null) {
				String[] resourcesIdArr = permissionDO.getResourcesId().split(",");
				List<String> resourcesIdList = Arrays.asList(resourcesIdArr);
				return success(resourcesIdList);
			} else {
				return error("该角色还未绑定权限，请先为该角色绑定权限!");
			}
		} else {
			return error();
		}
	}
}
