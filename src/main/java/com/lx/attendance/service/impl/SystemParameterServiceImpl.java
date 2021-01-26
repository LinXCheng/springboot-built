package com.lx.attendance.service.impl;

import com.lx.attendance.dao.SystemParameterDao;
import com.lx.attendance.model.domain.SystemParameterDO;
import com.lx.attendance.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SystemParameterServiceImpl implements SystemParameterService {
    @Autowired
    private SystemParameterDao systemParameterDao;

    /**
     * 查询所有系统参数
     *
     * @return
     */
    @Override
    public List<SystemParameterDO> findSystemParameter() {
        return systemParameterDao.findSystemParameter();
    }
}
