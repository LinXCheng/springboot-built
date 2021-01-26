package com.lx.attendance.controller;

import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.model.vo.MenuVO;
import com.lx.attendance.model.vo.RoleVO;
import com.lx.attendance.service.*;
import com.lx.attendance.utils.ResultSet;
import com.lx.attendance.model.domain.MenuDO;
import com.lx.attendance.model.domain.ResourcesTypeDO;
import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.utils.constants.Constants;
import com.lx.attendance.utils.logControl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;

@Controller
@ResponseBody
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourcesTypeService resourcesTypeService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;

    /**
     * 展示角色或权限列表
     * @return
     */
    @RequestMapping("/roleList")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("role/role_list");
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        List<RoleVO> rolelist=roleService.findRoleList(userInfo.getRoleId());
        List<ResourcesTypeDO> reourcesTypeList=resourcesTypeService.findResourcesType("other");
        List<MenuDO> menuList=menuService.findParentsMenu(null);
        List<MenuVO> menuVOList = new ArrayList<MenuVO>();
        if(menuList.size()!=0){
        	menuVOList=menuService.MenuAndTypeList(menuList);
        }
        model.addObject("rolelist",rolelist);
        model.addObject("menuList",menuVOList);
        model.addObject("reourcesTypeList",reourcesTypeList);
        return model;
    }

    /**
     * 禁用或者启用角色
     * @param id
     * @return
     */
    @RequestMapping("/roleDisable")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<?> roleDisable(Integer id) {
        try {
            if(id != null){
                Integer state = roleService.updateRoleStateById(id);
                if (state != null) {
                    return success(state);
                } else {
                    return error("切换失败，请重试!");
                }
            } else {
                return error("切换失败，请重试!");
            }
        } catch (Exception e) {
            logControl.logPrint(RoleController.class, Long.valueOf(id), e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("切换失败，请重试!");
        }
    }

    /**
     * 跳转到修改或者新增页面
     * @param id
     * @return
     */
    @RequestMapping("/roleEdit")
    public ModelAndView roleEdit(Integer id){
        ModelAndView model = new ModelAndView("role/role_edit");
        if(id != null){
            RoleDO role=roleService.findRoleById(id);
            model.addObject("roleModel",role);
        }
        return model;
    }

    /**
     * 保存角色
     * @param roleDO
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<String> save(RoleDO roleDO){
        try {
            roleService.updateRoleById(roleDO);
            return success();
        } catch (Exception e) {
            logControl.logPrint(RoleController.class, Long.valueOf(roleDO.getId()), e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("保存失败,请重试!");
        }
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<String> delete(Integer id){
        try {
            userService.updateUserRole(id);
            permissionService.deleteRolePermission(id);
            roleService.deleteRoleId(id);
            return success();
        } catch (Exception e) {
            logControl.logPrint(RoleController.class, Long.valueOf(id), e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("删除失败!");
        }
    }
}
