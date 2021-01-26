package com.lx.attendance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.attendance.dao.LeaveDao;
import com.lx.attendance.model.domain.LeaveDO;
import com.lx.attendance.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveDao leaveDao;

    /**
     * 新增请假记录
     *
     * @param leaveDO
     */
    @Override
    public void insertLeave(LeaveDO leaveDO) {
        leaveDao.insertLeave(leaveDO);
    }

    /**
     * 员工查询请假记录，根据员工id
     *
     * @param userId
     * @return
     */
    @Override
    public PageInfo<LeaveDO> findLeaveByUserId(int pageNum, int pageSize, Long userId, Date startTime, Date endTime) {
        PageHelper.startPage(pageNum, pageSize);
        List<LeaveDO> LeaveDOS = leaveDao.findLeaveByUserId(userId, startTime, endTime);
        PageInfo<LeaveDO> result = new PageInfo<LeaveDO>(LeaveDOS);
        return result;
    }

    /**
     * 根据id查询请假记录
     *
     * @param id
     * @return
     */
    @Override
    public LeaveDO findLeaveById(Long id) {
        return leaveDao.findLeaveById(id);
    }

    /**
     * 修改请假记录
     *
     * @param leaveDO
     */
    @Override
    public void updateLeaveById(LeaveDO leaveDO) {
        leaveDao.updateLeaveById(leaveDO);
    }

    /**
     * 管理员或者超级管理员查看所有请假记录
     *
     * @return
     */
    @Override
    public PageInfo<LeaveDO> findAllLeave(int pageNum, int pageSize, Date startTime, Date endTime, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<LeaveDO> leaveDOS = leaveDao.findAllLeave(startTime, endTime, keyword);
        PageInfo<LeaveDO> result = new PageInfo<LeaveDO>(leaveDOS);
        return result;
    }

    /**
     * 修改请假记录状态
     *
     * @param id
     * @param type
     */
    @Override
    public void updateStateById(Long id, int type, Long userId) {
        leaveDao.updateStateById(id, type, userId);
    }

    /**s
     * 删除请假记录必须是未通过申请的
     *
     * @param id
     */
    @Override
    public void deleteLeave(Long id) {
        leaveDao.deleteLeave(id);
    }

    /**
     * 根据系统时间是否存于请假中
     *
     * @param userId
     * @return
     */
    @Override
    public LeaveDO findLeaveBySystemDate(Long userId, Date systemDate) {
        return leaveDao.findLeaveBySystemDate(userId, systemDate);
    }

    /**
     * 查询某个月份的请假记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<LeaveDO> findLeaveByDateYM(Long userId, String dateYM) {
        return leaveDao.findLeaveByDateYM(userId, dateYM);
    }
}
