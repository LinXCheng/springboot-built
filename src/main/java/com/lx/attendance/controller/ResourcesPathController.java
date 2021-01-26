package com.lx.attendance.controller;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.MenuDO;
import com.lx.attendance.model.domain.ResourcesPathDO;
import com.lx.attendance.model.domain.ResourcesTypeDO;
import com.lx.attendance.model.vo.MenuVO;
import com.lx.attendance.model.vo.ResourcesPathVO;
import com.lx.attendance.service.MenuService;
import com.lx.attendance.service.ResourcesPathService;
import com.lx.attendance.service.ResourcesTypeService;
import com.lx.attendance.utils.ResultSet;

import com.lx.attendance.utils.logControl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;
/**
 * 资源管理
 */
@Controller
@RequestMapping("/resourcesPath")
public class ResourcesPathController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private ResourcesTypeService resourcesTypeService;
    @Autowired
    private ResourcesPathService resoucesPathSercice;

    /**
     * 查询资源列表
     *
     * @param keyword
     * @return ModelAndView
     */
    @RequestMapping("/resourcesList")
    public ModelAndView index(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "7") int pageSize, String keyword) {
        ModelAndView model = new ModelAndView("resourcesPath/resourcesPath_list");
        PageInfo<ResourcesPathVO> resourcesList = resoucesPathSercice.findResourcesPath(pageNum, pageSize, keyword);
        model.addObject("resources", resourcesList);
        model.addObject("keyword", keyword);
        return model;
    }

    /**
     * 修改资源
     *
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value = "editResources", method = RequestMethod.GET)
    public ModelAndView form(Long id) {
        ModelAndView model = new ModelAndView("resourcesPath/resourcesPath_edit");
        if (id != null) {
            ResourcesPathDO resourcesPath = resoucesPathSercice.findResourcesPathById(Long.valueOf(id));
            resourcesPath.setCode(resourcesPath.getCode().split(":")[0]);
            model.addObject("resources", resourcesPath);
        }
        List<MenuDO> menuList = menuService.findParentsMenu(null);
        List<MenuVO> menuVOList = new ArrayList<MenuVO>();
        if (menuList.size() != 0) {
            menuVOList = menuService.menuList(menuList);
        }
        List<ResourcesTypeDO> typeList = resourcesTypeService.findResourcesType(null);
        model.addObject("typeList", typeList);
        model.addObject("menuList", menuVOList);
        return model;
    }

    /**
     * 保存
     *
     * @param resourcesPath
     * @return Object
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<String> save(ResourcesPathDO resourcesPath) {
        try {
            if (StringUtils.isNotBlank(resourcesPath.getCode()) && resourcesPath.getType() == 11) {
                resourcesPath.setCode(resourcesPath.getCode() + ":other");
            } else {
                resourcesPath.setCode(menuService.findMenuById(resourcesPath.getMenuId()).getCode() + ":" + resourcesTypeService.findResourcesTypeRecord(Long.valueOf(resourcesPath.getType())).getCode());
            }
            if (resourcesPath.getId() != null) {
                resoucesPathSercice.updateResourcesPathById(resourcesPath);
            } else {
                resoucesPathSercice.insertResources(resourcesPath);
            }
            return success();
        } catch (Exception e) {
            logControl.logPrint(ResourcesPathController.class, resourcesPath.getId(), e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("保存失败,请重新保存!");
        }
    }

    /**
     * 检测资源编码是否重复
     *
     * @param code
     * @param id
     * @param menuId
     * @param typeId
     * @return Object
     */
    @RequestMapping("/checkRepeat")
    @ResponseBody
    public ResultSet<?> checkRepeat(String code, Long id, String menuId, String typeId) {
        if (!"11".equals(typeId)) {
            if (StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(typeId)) {
                code = menuService.findMenuById(Long.valueOf(menuId)).getCode() + ":" + resourcesTypeService.findResourcesTypeRecord(Long.valueOf(typeId)).getCode();
            }
        } else {
            if (StringUtils.isBlank(code)) {
                return error();
            } else {
                code = code + ":other";
            }
        }
        if (resoucesPathSercice.checkCode(id, code) != null) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<?> delete(Long id) {
        try {
            int count = resoucesPathSercice.findUseResourcesRole(id);
            if (count == 0) {
                resoucesPathSercice.deleteResources(id);
                return success();
            } else {
                return error("该资源正在被某个角色所使用请先解除绑定后，在进行删除操作！");
            }
        } catch (Exception e) {
            logControl.logPrint(ResourcesPathController.class, id, e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("删除失败，请重试！");
        }
    }
}
