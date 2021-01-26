package com.lx.attendance.service;

import com.lx.attendance.model.domain.LeaveTypeDO;

import java.util.List;

public interface LeaveTypeService {

    /**
     * 查询所有请假原因
     * @return
     */
    public List<LeaveTypeDO> findLeaveType();

    /**
     * 查询本月所有类型请假总和
     * @return
     */
    public List<LeaveTypeDO> findLeaveBySystem(Long userId);
}
