package com.lx.attendance.service;

import com.lx.attendance.model.domain.HolidayDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HolidayService {
    /**
     * 定时批量导入节假日
     * @param holidayDOList
     */
    public void insertBatch(List<HolidayDO> holidayDOList);

    /**
     * 查询某个时间（yyyy-mm-dd）是否是节假日
     * @param date
     * @return
     */
    public HolidayDO findHolidayByDate(@Param("date") Date date);


    /**
     * 查询本年度所有的节假日
     * @return
     */
    public Map<String, HolidayDO> findThisYearHolidayByDate(String startY);

}
