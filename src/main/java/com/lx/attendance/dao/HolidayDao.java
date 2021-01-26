package com.lx.attendance.dao;

import com.lx.attendance.model.domain.HolidayDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HolidayDao {
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
    public List<HolidayDO> findThisYearHolidayByDate(@Param("startY") String startY);
}
