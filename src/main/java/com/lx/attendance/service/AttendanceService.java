package com.lx.attendance.service;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.AttendanceDO;
import com.lx.attendance.model.domain.RetroactiveDO;

import java.util.Date;
import java.util.List;

public interface AttendanceService {

    /**
     * 添加考勤签到记录
     * @param attendanceDO
     */
    public void insertAttendance(AttendanceDO attendanceDO);

    /**
     * 根据日期查询是否存在打卡上班的记录
     * @param time
     * @return
     */
    public AttendanceDO findSignIn(Date time, Long userId);

    /**
     *  修改打卡记录（修改打卡记录只能是处于打卡下班场景）
     * @param attendanceDO
     */
    public void updateSignOut(AttendanceDO attendanceDO);

    /**
     * 根据用户id查找所有考勤记录
     * @param userId
     */
    public List<AttendanceDO> findAttendanceByUserId(Long userId, int state);

    /**
     * 新增补卡记录
     * @param retroactiveDOList
     */
    public void insertRetroactive(List<RetroactiveDO> retroactiveDOList);


    /**
     * 查询用户补签记录
     * @return
     */
    public PageInfo<RetroactiveDO> findRetroactiveById(int pageNum, int pageSize,Long userId, Date YearMonth);

    /**
     * 查询用户补签记录
     * @return
     */
    public List<RetroactiveDO> findRetroactiveById(Long userId, Date YearMonth);

    /**
     * 查询所有用户补签记录
     * @return
     */
    public PageInfo<RetroactiveDO> findRetroactive(int pageNum, int pageSize, Date YearMonth, String keyword);

    /**
     * 修改补签记录状态
     * @param idStr
     * @param state
     */
    public void updateRetroactiveState(String idStr,int state);

    /**
     * 根据补签id集合查询所有签到记录
     * @param idStr
     * @return
     */
    public List<AttendanceDO> findAttendanceByRetroactiveId(String idStr);

    /**
     * 补签
     * @param MorningTime
     * @param AfternoonTime
     * @param idStr
     * @param type
     */
    public void updateAttendance(Date MorningTime, Date AfternoonTime, String idStr, int type);


    /**
     * 删除补卡记录
     * @param id
     */
    public void deleteRetroactive(Long id);

    /**
     * 查询某月所有的签到记录
     * @param dateYM
     * @param userId
     * @return
     */
    public List<AttendanceDO> findAttendanceByTime(String dateYM, Long userId);

}
