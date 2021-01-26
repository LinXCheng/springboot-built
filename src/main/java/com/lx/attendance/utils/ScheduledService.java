package com.lx.attendance.utils;

import com.lx.attendance.model.domain.HolidayDO;
import com.lx.attendance.service.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.lx.attendance.utils.HolidayUtil.holidaySeason;

@Slf4j
@Component
public class ScheduledService {
    @Autowired
    private HolidayService holidayService;
    public static ScheduledService scheduledService;

    @PostConstruct
    public void init() {
        scheduledService = this;
        scheduledService.holidayService = this.holidayService;
    }

    /**
     * 定时任务在每年的 11.30 00:00:00 自动导入来年的节假日信息
     */
    @Scheduled(cron = "0 0 0 30 11 *")
    public void scheduled() {
        List<HolidayDO> holidaySeason = holidaySeason();
        if(holidaySeason.size() != 0 ){
            scheduledService.holidayService.insertBatch(holidaySeason);
        }
    }
}
