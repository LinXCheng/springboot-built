package com.lx.attendance.service.impl;


import com.lx.attendance.dao.ResourcesPathDao;
import com.lx.attendance.model.domain.ResourcesPathDO;
import com.lx.attendance.model.vo.ResourcesPathVO;
import com.lx.attendance.service.ResourcesPathService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源管理
 */
@Service
public class ResourcesPathServiceImpl  implements ResourcesPathService {
    @Autowired
    private ResourcesPathDao resourcesPathDao;

    /**
     * 新增资源
     * @param resourcesPath
     */
    @Override
    public void insertResources(ResourcesPathDO resourcesPath) {
        resourcesPathDao.insertResources(resourcesPath);
    }

    /**
     * 根据id查询资源
     * @param id
     * @return ResourcesPathDTO
     */
    @Override
    public ResourcesPathDO findResourcesPathById(Long id) {
        return resourcesPathDao.findResourcesById(id);
    }

	/**
	 * 查询资源列表
	 * 
	 * @param keyword
	 * @return
	 */
	@Override
	public PageInfo<ResourcesPathVO> findResourcesPath(int pageNum, int pageSize, String keyword) {
		 PageHelper.startPage(pageNum,pageSize);
		List<ResourcesPathVO> ResourcesPathVOList = resourcesPathDao.findResourcesPath(keyword);
		PageInfo<ResourcesPathVO> result = new PageInfo<ResourcesPathVO>(ResourcesPathVOList);
		return result;
	}

    /**
     * 根据id修改资源
     * @param resourcesPath
     */
    @Override
    public void updateResourcesPathById(ResourcesPathDO resourcesPath) {
        resourcesPathDao.updateResourcesPathById(resourcesPath);
    }

    /**
     * 检测资源编码是否重复
     * @param id
     * @param code
     * @return
     */
    @Override
    public ResourcesPathDO checkCode(Long id,String code) {
        return resourcesPathDao.checkCode(id,code);
    }

    /**
     * 根据用户Id查询其拥有的权限
     * @param userId
     * @return
     */
    @Override
    public List<ResourcesPathDO> findUserPermission(Long userId) {
        return resourcesPathDao.findUserPermission(userId);
    }

    @Override
    public List<ResourcesPathDO> findAllResourcesList() {
        return resourcesPathDao.findAllResourcesList();
    }
    
    /**
     * 查询所有编码集合
     */
	@Override
	public List<String> findAllCodeList(Long id) {
		return resourcesPathDao.findAllCodeList(id);
	}

    /**
     * 根据menuId查询是否存在绑定的资源
     * @param MenuId
     * @return
     */
    @Override
    public List<ResourcesPathDO> findResourcesByMenuId(Long MenuId) {
        return resourcesPathDao.findResourcesByMenuId(MenuId);
    }

    @Override
    public int findUseResourcesRole(Long id) {
        return resourcesPathDao.findUseResourcesRole(id);
    }

    /**
     * 删除资源
     * @param id
     */
    @Override
    public void deleteResources(Long id) {
        resourcesPathDao.deleteResources(id);
    }
}
