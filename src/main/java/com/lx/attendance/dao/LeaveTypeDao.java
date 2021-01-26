package com.lx.attendance.dao;

import com.lx.attendance.model.domain.LeaveTypeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveTypeDao {
    /**
     * 查询所有请假原因
     * @return
     */
    public List<LeaveTypeDO> findLeaveType();

    /**
     * 查询本月所有类型请假总和
     * @return
     */
    public List<LeaveTypeDO> findLeaveBySystem(@Param("userId") Long userId);
}
