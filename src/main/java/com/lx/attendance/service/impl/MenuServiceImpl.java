package com.lx.attendance.service.impl;


import com.lx.attendance.dao.ResourcesPathDao;
import com.lx.attendance.model.MenuDTO;
import com.lx.attendance.model.domain.MenuDO;
import com.lx.attendance.model.vo.MenuVO;
import com.lx.attendance.model.vo.ResourcesTypeVO;
import com.lx.attendance.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.attendance.dao.MenuDao;

import com.lx.attendance.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ResourcesPathDao resoucesPathDao;

    /**
     * 新增菜单
     * @param menu
     */
    @Override
    public void insertMenu(MenuDO menu) {
         menuDao.insertMenu(menu);
    }

	/**
	 * 获取菜单列表
	 * 
	 * @param keyword
	 * @return PageInfo<MenuVO>
	 */
	@Override
	public PageInfo<MenuVO> findMenuList(int pageNum, int pageSize, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		List<MenuVO> List = menuDao.findMenuList(keyword);
		PageInfo<MenuVO> result = new PageInfo<MenuVO>(List);
		return result;
	}

    /**
     * 根据id获取菜单
     * @param id
     * @return MenuDTO
     */
    @Override
    public MenuDO findMenuById(Long id) {
        return menuDao.findMenuById(id);
    }

    /**
     * 查询父级菜单
     * @param id
     * @return List<MenuDO>
     */
    @Override
    public List<MenuDO> findParentsMenu(Long id) {
        return menuDao.findParentsMenu(id);
    }

    /**
     * 根据id修改菜单
     * @param menu
     */
    @Override
    public void updateMenuById(MenuDO menu) {
        menuDao.updateMenuById(menu);
    }

    /**
     * 检测菜单编码是否重复
     * @param menu
     * @return MenuDTO
     */
    @Override
    public MenuDO checkCode(MenuDO menu) {
        return menuDao.checkCode(menu);
    }

    /**
     * 获取菜单列表（父子级并列）
     * @param menuDOList
     * @return
     */
    @Override
    public List<MenuVO> menuList(List<MenuDO> menuDOList) {
    	List<MenuVO> menuVOList = BeanMapper.mapList(menuDOList, MenuVO.class);
        List<MenuDTO> childMenuList=menuDao.findChildMenuByPid();
        for(MenuVO menuVO:menuVOList){
            List<MenuDTO> child = new ArrayList<MenuDTO>();
            for(MenuDTO childMenu:childMenuList){
                if(menuVO.getId()==childMenu.getPid()){
                    child.add(childMenu);
                }
            }
            menuVO.setChildmenu(child);
        }
        return menuVOList;
    }

    /**
     * 在角色管理中使用的菜单和对应的资源类型列表
     * @param menuDOList
     * @return
     */
    @Override
    public List<MenuVO> MenuAndTypeList(List<MenuDO> menuDOList) {
    	List<MenuVO> menuVOList = BeanMapper.mapList(menuDOList, MenuVO.class);
    	List<MenuDTO> childMenuList=menuDao.findChildMenuByPid();
        for(MenuVO menuVO:menuVOList){
            List<MenuDTO> child = new ArrayList<MenuDTO>();
            for(MenuDTO childMenu:childMenuList){
                if(menuVO.getId()==childMenu.getPid()){
                	List<ResourcesTypeVO> typeList=resoucesPathDao.findChildMenuByMenuId(childMenu.getId(),null);
                    List<ResourcesTypeVO> otherList=resoucesPathDao.findChildMenuByMenuId(childMenu.getId(),"other");
                    childMenu.setTypeList(typeList);
                    childMenu.setOtherList(otherList);
                    child.add(childMenu);
                }
            }
            menuVO.setSize(child.size());
            menuVO.setChildmenu(child);
        }
        return menuVOList;
    }

    /**
     * 根据用户角色查询菜单
     * @param userId
     * @return
     */
    @Cacheable(value = "menu_List")
    @Override
    public List<MenuVO> findMenuListByUserRole(Long userId) {
        if(userId != null){
        	// 获取一级菜单
            List<MenuVO> MenuList = menuDao.findMenuListByUserRole(userId);
            // 获取二级菜单
            List<MenuDTO> childMenuList = menuDao.findChildMenuListByUserRole(userId);
            for(MenuVO menuVO:MenuList){
                List<MenuDTO> child = new ArrayList<MenuDTO>();
                for(MenuDTO childMenu:childMenuList){
                    if(menuVO.getId()==childMenu.getPid()){
                        child.add(childMenu);
                    }
                }
                menuVO.setChildmenu(child);
            }
            return MenuList;
        } else {
            return null;
        }
    }

    /**
     * 根据id查询是否有子级
     * @param id
     * @return
     */
    @Override
    public List<MenuDO> findWhetherChild(Long id) {
        return menuDao.findWhetherChild(id);
    }

    /**
     * 删除菜单
     * @param id
     */
    @Override
    public void deleteMenuById(Long id) {
        menuDao.deleteMenuById(id);
    }
}
