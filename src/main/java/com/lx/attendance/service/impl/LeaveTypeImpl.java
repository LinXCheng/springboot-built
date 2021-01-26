package com.lx.attendance.service.impl;

import com.lx.attendance.dao.LeaveTypeDao;
import com.lx.attendance.model.domain.LeaveTypeDO;
import com.lx.attendance.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypeImpl implements LeaveTypeService {
    @Autowired
    private LeaveTypeDao leaveTypeDao;
    /**
     * 查询所有请假原因
     * @return
     */
    @Cacheable(value = "leave_type")
    @Override
    public List<LeaveTypeDO> findLeaveType() {
        return leaveTypeDao.findLeaveType();
    }

    /**
     * 查询本月所有类型请假总和
     * @return
     */
    @Override
    public List<LeaveTypeDO> findLeaveBySystem(Long userId) {
        return leaveTypeDao.findLeaveBySystem(userId);
    }
}
