package com.lx.attendance.controller;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.MenuDO;
import com.lx.attendance.model.domain.ResourcesPathDO;
import com.lx.attendance.model.vo.MenuVO;
import com.lx.attendance.service.MenuService;
import com.lx.attendance.service.ResourcesPathService;
import com.lx.attendance.utils.ResultSet;

import com.lx.attendance.utils.logControl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;

import java.util.List;

/**
 * 菜单管理
 */
@Controller
@RequestMapping("/menu")
@ResponseBody
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private ResourcesPathService resourcesPathService;
    /**
     * 查询菜单列表
     * @param keyword
     * @return ModelAndView
     */
	@RequestMapping(value = "menuList", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "7") int pageSize, String keyword) {
		ModelAndView model = new ModelAndView("menu/menu_list");
		PageInfo<MenuVO> result = menuService.findMenuList(pageNum, pageSize, keyword);
		model.addObject("menulist", result);
		model.addObject("keyword", keyword);
		return model;
	}

    /**
     * 修改菜单
     * @param id
     * @return ModelAndView
     */
    @CacheEvict(value = "menu_List")
    @RequestMapping(value = "editMenu", method = RequestMethod.GET)
    public ModelAndView form(Long id) {
        ModelAndView model = new ModelAndView("menu/menu_edit");
        if (id != null) {
        	MenuDO menu= menuService.findMenuById(id);
            model.addObject("menuRecord", menu);
        }
        List<MenuDO> parentsMenu = menuService.findParentsMenu(id);
        model.addObject("parentsMenu",parentsMenu);
        return model;
    }

    /**
     * 保存
     * @param menu
     * @return Object
     */
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public <T> ResultSet<T> save(MenuDO menu) {
        try {
            if (StringUtils.isBlank(menu.getCode())){
                menu.setCode(null);
            }
            if(StringUtils.isBlank(menu.getPath())){
                menu.setPath(null);
            }
			if (menu.getPid() != 0) {
				menu.setMenuType(menuService.findMenuById(menu.getPid()).getMenuType());
			}
            if (menu.getId() != null){
                menuService.updateMenuById(menu);
            }else{
                menuService.insertMenu(menu);
            }
        } catch (Exception e) {
            logControl.logPrint(MenuController.class,menu.getId(),e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error();
        }
        return success();
    }

    /**
     * 删除菜单
     * @param
     * @return Object
     */
   @RequestMapping(value = "delete",method = RequestMethod.GET)
   @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
   public ResultSet delete(Long id) {
       try {
           List<MenuDO> menuDOList = menuService.findWhetherChild(id);
           List<ResourcesPathDO> resourcesPathDOList = resourcesPathService.findResourcesByMenuId(id);
           if(menuDOList.size() != 0 ){
               return error("请先删除该菜单的子级菜单!");
           }else if(resourcesPathDOList.size() !=0 ){
               return error("请先删除该菜单下的所有权限!");
           }else{
               menuService.deleteMenuById(id);
               return success();
           }
       } catch (Exception e) {
           logControl.logPrint(MenuController.class,id,e.getMessage());
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return error("删除失败");
       }
   }

    /**
     * 检测菜单编码是否重复
     * @param code
     * @param id
     * @return Object
     */
    @RequestMapping(value = "checkCode",method = RequestMethod.POST)
    public <T>ResultSet<T> checkCode(String code, Long id) {
        MenuDO menu = new MenuDO();
        if(StringUtils.isBlank(code)){
            return error();
        }else{
            menu.setCode(code);
            menu.setId(id);
            if (menuService.checkCode(menu) != null) {
                return success();
            } else {
                return error();
            }
        }
    }
}
