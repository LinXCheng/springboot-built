package com.lx.attendance.service;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.LeaveDO;

import java.util.Date;
import java.util.List;

public interface LeaveService {


    /**
     * 新增请假记录
     * @param leaveDO
     */
    public void insertLeave(LeaveDO leaveDO);

    /**
     * 员工查询请假记录，根据员工id
     * @param userId
     * @return
     */
    PageInfo<LeaveDO> findLeaveByUserId(int pageNum, int pageSize, Long userId, Date startTime, Date endTime);

    /**
     * 根据id查询请假记录
     * @param id
     * @return
     */
    public LeaveDO findLeaveById(Long  id);

    /**
     *
     * 修改请假记录
     * @param leaveDO
     */
    public void updateLeaveById(LeaveDO leaveDO);


    /**
     * 管理员或者超级管理员查看所有请假记录
     * @return
     */
    public PageInfo<LeaveDO> findAllLeave(int pageNum, int pageSize, Date startTime, Date endTime, String keyword);


    /**
     * 修改请假记录状态
     * @param id
     * @param type
     */
    public void updateStateById(Long id, int type, Long userId);

    /**
     * 删除请假记录必须是未通过申请的
     * @param id
     */
    public void deleteLeave(Long id);


    /**
     * 根据系统时间是否存于请假中
     *
     * @param userId
     * @return
     */
    public LeaveDO findLeaveBySystemDate(Long userId, Date systemDate);

    /**
     * 查询某个月份的请假记录
     *
     * @param userId
     * @return
     */
    public List<LeaveDO> findLeaveByDateYM(Long userId, String dateYM);
}
