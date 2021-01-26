package com.lx.attendance.dao;

import com.lx.attendance.model.domain.ResourcesTypeDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourcesTypeDao {

    /**
     * 查询所有资源，如果存在code则只查询id为11的记录否则查询其他数据
     * @param code
     * @return
     */
    List<ResourcesTypeDO> findResourcesType(@Param("code") String code);

    /**
     * 根据id查询资源类型
     * @param id
     * @return
     */
    ResourcesTypeDO findResourcesTypeRecord(Long id);
}
