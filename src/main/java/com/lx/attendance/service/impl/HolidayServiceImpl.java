package com.lx.attendance.service.impl;

import com.lx.attendance.dao.HolidayDao;
import com.lx.attendance.model.domain.HolidayDO;
import com.lx.attendance.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lx.attendance.utils.CommonDate.strDateToDate;

@Service
public class HolidayServiceImpl implements HolidayService {
    @Autowired
    private HolidayDao holidayDao;

    /**
     * 定时批量导入节假日
     *
     * @param holidayDOList
     */
    @Override
    public void insertBatch(List<HolidayDO> holidayDOList) {
        holidayDao.insertBatch(holidayDOList);
    }

    /**
     * 查询某个时间（yyyy-mm-dd）是否是节假日
     *
     * @param date
     * @return
     */
    @Override
    public HolidayDO findHolidayByDate(Date date) {
        return holidayDao.findHolidayByDate(date);
    }

    @Cacheable(value = "ThisYearHoliday")
    @Override
    public Map<String, HolidayDO> findThisYearHolidayByDate(String startY) {
        List<HolidayDO> holidayDOList = holidayDao.findThisYearHolidayByDate(startY);
        Map<String, HolidayDO> map = new HashMap<>();
        for (HolidayDO holidayDO : holidayDOList) {
            map.put(strDateToDate(holidayDO.getDate()), holidayDO);
        }
        return map;
    }
}
