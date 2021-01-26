package com.lx.attendance.service;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.MenuDO;
import com.lx.attendance.model.vo.MenuVO;

import java.util.List;

public interface MenuService {
    /**
     * 新增菜单
     *
     * @param menu
     */
    public void insertMenu(MenuDO menu);

    /**
     * 获取菜单列表
     *
     * @param keyword
     * @return List<MenuDTO>
     */
	public PageInfo<MenuVO> findMenuList(int pageNum, int pageSize, String keyword);

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return MenuDTO
     */
    public MenuDO findMenuById(Long id);

    /**
     * 查询父级菜单
     *
     * @param id
     * @return List<MenuDTO>
     */
    public List<MenuDO> findParentsMenu(Long id);

    /**
     * 根据id修改菜单
     *
     * @param menu
     */
    public void updateMenuById(MenuDO menu);

    /**
     * 检测code是否重复
     *
     * @param menu
     * @return MenuDTO
     */
    public MenuDO checkCode(MenuDO menu);

    /**
     * 获取菜单列表（父子级并列）
     *
     * @param menuDOList
     * @return
     */
    public List<MenuVO> menuList(List<MenuDO> menuDOList);

    /**
     * 在角色管理中使用的菜单和对应的资源类型列表
     *
     * @param menuDTOList
     * @return
     */
    public List<MenuVO> MenuAndTypeList(List<MenuDO> menuDTOList);


    /**
     * 根据用户角色查询菜单
     *
     * @param userId
     * @return
     */
    public List<MenuVO> findMenuListByUserRole(Long userId);


    /**
     * 根据id查询是否有子级
     * @param id
     * @return
     */
    public  List<MenuDO> findWhetherChild(Long id);

    /**
     * 删除菜单
     * @param id
     */
    public void deleteMenuById(Long id);

}
