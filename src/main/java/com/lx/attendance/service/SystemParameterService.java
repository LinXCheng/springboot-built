package com.lx.attendance.service;

import com.lx.attendance.model.domain.SystemParameterDO;

import java.util.List;

public interface SystemParameterService {
    /**
     * 查询所有系统参数
     * @return
     */
    List<SystemParameterDO> findSystemParameter();
}
