package com.lx.attendance.dao;

import com.lx.attendance.model.domain.WageDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WageDao {
    /**
     * 新增用户工资
     * @param wageDO
     */
    public void insertWage(WageDO wageDO);

    /**
     * 查询某个用户某个月的工资
     * @param userId
     * @param yearMonth
     * @return
     */
    public WageDO findWageByUserId(@Param("userId") Long userId,@Param("yearMonth") String yearMonth);


    /**
     * 查询某个月的所有工资记录
     * @param yearMonth
     * @param keyword
     * @return
     */
    public List<WageDO> findAllWage(@Param("yearMonth") String yearMonth, @Param("keyword") String keyword);

    /**
     * 查询某个月的工资记录
     * @param yearMonth
     * @return
     */
    public List<WageDO> findWage(@Param("yearMonth") String yearMonth);

    /**
     * 批量新增用户工资
     */
    public void insertWageBatch(List<WageDO> wageDOS);
}
