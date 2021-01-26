package com.lx.attendance.dao;

import com.lx.attendance.model.domain.LeaveDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LeaveDao {
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
    public List<LeaveDO> findLeaveByUserId(@Param("userId") Long userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

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
    public List<LeaveDO> findAllLeave(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("keyword") String keyword);

    /**
     * 修改请假记录状态
     * @param id
     * @param type
     */
    public void updateStateById(@Param("id") Long id, @Param("type") int type,@Param("userId") Long userId);

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
    public LeaveDO findLeaveBySystemDate(@Param("userId") Long userId, @Param("systemDate") Date systemDate);

    /**
     * 查询某个月份的请假记录
     *
     * @param userId
     * @return
     */
    public List<LeaveDO> findLeaveByDateYM(@Param("userId") Long userId, @Param("dateYM") String dateYM);

}
