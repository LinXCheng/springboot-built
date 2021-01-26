package com.lx.attendance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.attendance.dao.AttendanceDao;
import com.lx.attendance.model.domain.AttendanceDO;
import com.lx.attendance.model.domain.RetroactiveDO;
import com.lx.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    /**
     * 添加考勤签到记录
     * @param attendanceDO
     */
    @Override
    public void insertAttendance(AttendanceDO attendanceDO) {
        attendanceDao.insertAttendance(attendanceDO);
    }

    /**
     *根据日期查询是否存在打卡上班的记录
     * @param time
     * @return
     */
    @Override
    public AttendanceDO findSignIn(Date time, Long userId) {
        return attendanceDao.findSignIn(time, userId);
    }

    @Override
    public void updateSignOut(AttendanceDO attendanceDO) {
        attendanceDao.updateSignOut(attendanceDO);
    }

    /**
     * 根据用户id查找所有考勤记录
     * @param userId
     */
    @Override
    public List<AttendanceDO> findAttendanceByUserId(Long userId, int state) {
        return attendanceDao.findAttendanceByUserId(userId, state);
    }

    /**
     * 新增补卡记录
     * @param retroactiveDOList
     */
    @Override
    public void insertRetroactive(List<RetroactiveDO> retroactiveDOList){
        attendanceDao.insertRetroactive(retroactiveDOList);
    }

    /**
     * 查询用户补签记录
     * @return
     */
    @Override
    public PageInfo<RetroactiveDO> findRetroactiveById(int pageNum, int pageSize, Long userId, Date YearMonth){
        PageHelper.startPage(pageNum, pageSize);
        List<RetroactiveDO> list = attendanceDao.findRetroactiveById(userId, YearMonth);
        PageInfo<RetroactiveDO> result = new PageInfo<>(list);
        return result;
    }

    @Override
    public List<RetroactiveDO> findRetroactiveById(Long userId, Date YearMonth) {
        return attendanceDao.findRetroactiveById(userId, YearMonth);
    }
    /**
     * 查询所有用户补签记录
     * @return
     */
    @Override
    public PageInfo<RetroactiveDO> findRetroactive(int pageNum, int pageSize, Date YearMonth, String keyword){
        PageHelper.startPage(pageNum, pageSize);
        List<RetroactiveDO> list = attendanceDao.findRetroactive(YearMonth, keyword);
        PageInfo<RetroactiveDO> result = new PageInfo<>(list);
        return result;
    }


    /**
     * 修改补签记录状态
     * @param idStr
     * @param state
     */
    @Override
    public void updateRetroactiveState(String idStr, int state) {
        attendanceDao.updateRetroactiveState(idStr,state);
    }

    /**
     * 根据补签id集合查询所有签到记录
     * @param idStr
     * @return
     */
    @Override
    public List<AttendanceDO> findAttendanceByRetroactiveId(String idStr) {
        return attendanceDao.findAttendanceByRetroactiveId(idStr);
    }

    /**
     * 补签
     * @param MorningTime
     * @param AfternoonTime
     * @param idStr
     * @param type
     */
    @Override
    public void updateAttendance(Date MorningTime, Date AfternoonTime, String idStr, int type) {
        attendanceDao.updateAttendance(MorningTime, AfternoonTime, idStr, type);
    }
    /**
     * 删除补卡记录
     * @param id
     */
    @Override
    public void deleteRetroactive(Long id) {
        attendanceDao.deleteRetroactive(id);
    }

    /**
     * 查询某月所有的签到记录
     * @param dateYM
     * @param userId
     * @return
     */
    @Override
    public List<AttendanceDO> findAttendanceByTime(String dateYM, Long userId) {
        return attendanceDao.findAttendanceByTime(dateYM, userId);
    }
}
