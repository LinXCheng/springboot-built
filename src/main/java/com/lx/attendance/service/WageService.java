package com.lx.attendance.service;

import com.lx.attendance.model.domain.WageDO;

import java.util.List;


public interface WageService {
    /**
     * 新增用户工资
     *
     * @param wageDO
     */
    public void insertWage(WageDO wageDO);


    /**
     * 查询某个用户某个月的工资
     *
     * @param userId
     * @param yearMonth
     * @return
     */
    public WageDO findWageByUserId(Long userId, String yearMonth);

    /**
     * 查询某个月的所有工资记录
     *
     * @param yearMonth
     * @return
     */
    public List<WageDO> findAllWage(String yearMonth, String keyword);

    /**
     * 查询某个月的工资记录
     *
     * @param yearMonth
     * @return
     */
    public List<WageDO> findWage(String yearMonth);

    /**
     * 批量新增用户工资
     */
    public void insertWageBatch(List<WageDO> wageDOS);
}
