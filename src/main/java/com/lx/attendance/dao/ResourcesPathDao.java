package com.lx.attendance.dao;

import com.lx.attendance.model.domain.ResourcesPathDO;
import com.lx.attendance.model.vo.ResourcesPathVO;
import com.lx.attendance.model.vo.ResourcesTypeVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源管理
 */
public interface ResourcesPathDao {

    /**
     * 查询所有记录
     *
     * @param keyword
     * @return List<ResourcesPathDTO>
     */
    public List<ResourcesPathVO> findResourcesPath(String keyword);

    /**
     * 新增菜单
     *
     * @param resourcesPath
     */
    public void insertResources(ResourcesPathDO resourcesPath);

    /**
     * 根据Id查询记录
     *
     * @param id
     * @return ResourcesPathDTO
     */
    public ResourcesPathDO findResourcesById(Long id);

    /**
     * 更新资源根据id
     *
     * @param resourcesPath
     */
    public void updateResourcesPathById(ResourcesPathDO resourcesPath);

    /**
     * 检测资源编码是否重复
     *
     * @param
     * @return ResourcesPathDTO
     */
    public ResourcesPathDO checkCode(@Param("id") Long id, @Param("code") String code);


    /**
     * 根据父级菜单查询子级菜单
     *
     * @param menuId
     * @param typename
     * @return
     */
    public List<ResourcesTypeVO> findChildMenuByMenuId(@Param("menuId") Long menuId, @Param("typename") String typename);

    /**
     * 根据用户ID查询该用户所有的权限
     *
     * @param userId
     * @return
     */
    public List<ResourcesPathDO> findUserPermission(@Param("userId") Long userId);

    /**
     * 查询所有资源
     *
     * @return
     */
    public List<ResourcesPathDO> findAllResourcesList();
    
    /**
     * 查询所有编码集合
     * @return
     */
	public List<String> findAllCodeList(@Param("id") Long id);

    /**
     * 根据menuId查询是否存在绑定的资源
     * @param MenuId
     * @return
     */
    public List<ResourcesPathDO> findResourcesByMenuId(Long MenuId);

    /**
     * 判断该资源是否被某个角色所使用
     * @param id
     * @return
     */
    public int findUseResourcesRole(Long id);

    /**
     * 删除资源
     * @param id
     */
    public void deleteResources(Long id);
}