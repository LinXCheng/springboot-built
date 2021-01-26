package com.lx.attendance.service;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.ResourcesPathDO;
import com.lx.attendance.model.vo.ResourcesPathVO;

import java.util.List;

/**
 * 资源管理
 */
public interface ResourcesPathService {
    /**
     * 新增资源列表
     * @param resourcesPath
     */
    public void insertResources(ResourcesPathDO resourcesPath);

    /**
     * 根据id查询资源列表
     * @param id
     * @return
     */
    public ResourcesPathDO findResourcesPathById(Long id);

	/**
	 * 查询资源列表
	 * 
	 * @param keyword
	 * @return
	 */
	public PageInfo<ResourcesPathVO> findResourcesPath(int pageNum, int pageSize, String keyword);

    /**
     * 修改资源列表
     * @param resourcesPath
     */
    public void updateResourcesPathById(ResourcesPathDO resourcesPath);

    /**
     * 检测资源编码是否重复
     * @param
     * @return
     */
    public ResourcesPathDO checkCode(Long id, String code);

    /**
     * 根据用户Id查询其拥有的权限
     * @param userId
     * @return
     */
    public List<ResourcesPathDO> findUserPermission(Long userId);

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
    public List<String> findAllCodeList(Long id);

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
