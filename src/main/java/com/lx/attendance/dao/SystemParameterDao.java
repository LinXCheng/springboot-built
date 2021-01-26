package com.lx.attendance.dao;

import com.lx.attendance.model.domain.SystemParameterDO;

import java.util.List;


public interface SystemParameterDao {
    /**
     * 查询所有系统参数
     * @return
     */
    List<SystemParameterDO> findSystemParameter();

}
