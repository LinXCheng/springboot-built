package com.lx.attendance.service.impl;

import com.lx.attendance.dao.WageDao;
import com.lx.attendance.model.domain.WageDO;
import com.lx.attendance.service.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WageServiceImpl implements WageService {
    @Autowired
    private WageDao wageDao;

    /**
     * 新增用户工资
     *
     * @param wageDO
     */
    @Override
    public void insertWage(WageDO wageDO) {
        wageDao.insertWage(wageDO);
    }

    /**
     * 查询某个用户某个月的工资
     *
     * @param userId
     * @param yearMonth
     * @return
     */
    @Override
    public WageDO findWageByUserId(Long userId, String yearMonth) {
        return wageDao.findWageByUserId(userId, yearMonth);
    }

    /**
     * 查询某个月的所有工资记录
     *
     * @param yearMonth
     * @param keyword
     * @return
     */
    @Override
    public List<WageDO> findAllWage(String yearMonth, String keyword) {
        return wageDao.findAllWage(yearMonth, keyword);
    }

    /**
     * 查询某个月的工资记录
     *
     * @param yearMonth
     * @return
     */
    @Override
    public List<WageDO> findWage(String yearMonth) {
        return wageDao.findWage(yearMonth);
    }

    /**
     * 批量新增用户工资
     */
    @Override
    public void insertWageBatch(List<WageDO> wageDOS) {
        wageDao.insertWageBatch(wageDOS);
    }
}