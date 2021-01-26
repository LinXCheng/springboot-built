package com.lx.attendance.dao;

import com.lx.attendance.model.MenuDTO;
import com.lx.attendance.model.domain.MenuDO;
import com.lx.attendance.model.vo.MenuVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理
 */
public interface MenuDao {

    /**
     * 获取菜单列表
     * @param keyword
     * @return List<MenuDTO>
     */
    public List<MenuVO> findMenuList(String keyword);

    /**
     * 新增菜单
     * @param menuDO
     */
    public void insertMenu(MenuDO menuDO);

    /**
     * 根据Id查询记录
     * @param id
     * @return MenuDTO
     */
    public MenuDO findMenuById(Long id);

    /**
     * 查询父级菜单
     * @param id
     * @return List<MenuDTO>
     */
    public List<MenuDO> findParentsMenu(Long id);

    /**
     * 查询子级菜单
     * @param
     * @return List<MenuDTO>
     */
    public List<MenuDTO> findChildMenuByPid();

    /**
     * 根据id修改菜单
     * @param menu
     */
    public void updateMenuById(MenuDO menu);

    /**
     * 检测code是否重复
     * @param menu
     * @return MenuDTO
     */
    public MenuDO checkCode(MenuDO menu);

    /**
     * 根据用户角色查询菜单
     * @param userId
     * @return
     */
    public List<MenuVO> findMenuListByUserRole(@Param("userId") Long userId);

    /**
     * 根据用户Id和父菜单Id查询菜单
     * @param userId
     * @return
     */
    public List<MenuDTO> findChildMenuListByUserRole(@Param("userId") Long userId);

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
