package com.lx.attendance.service.impl;


import com.lx.attendance.dao.ResourcesTypeDao;
import com.lx.attendance.model.domain.ResourcesTypeDO;
import com.lx.attendance.service.ResourcesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源类型管理
 */
@Service
public class ResourcesTypeServiceImpl implements ResourcesTypeService {

    @Autowired
    private ResourcesTypeDao resourcesTypeDao;

    /**
     * 查询资源类型
     * @return List<ResourcesTypeDO>
     */
    @Override
    public List<ResourcesTypeDO> findResourcesType(String code) {
        return resourcesTypeDao.findResourcesType(code);
    }

    /**
     * 根据id查询资源
     * @param id
     * @return ResourcesTypeDTO
     */
    public ResourcesTypeDO findResourcesTypeRecord(Long id) {
        return resourcesTypeDao.findResourcesTypeRecord(id);
    }

    ;
}
