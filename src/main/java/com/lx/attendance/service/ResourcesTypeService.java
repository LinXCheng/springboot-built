package com.lx.attendance.service;

import com.lx.attendance.model.domain.ResourcesTypeDO;

import java.util.List;

public interface ResourcesTypeService {
    /**
     * 查找权限资源类型列表
     * @param code
     * @return
     */
    List<ResourcesTypeDO> findResourcesType(String code);

    /**
     * 根据id查询权限资源类型
     * @param id
     * @return
     */
    ResourcesTypeDO findResourcesTypeRecord(Long id);
}
