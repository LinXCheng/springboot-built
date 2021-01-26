package com.lx.attendance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot启动类
 */
@EnableCaching
@SpringBootApplication
@MapperScan("com.lx.attendance.dao")
@EnableScheduling
public class AttendanceApplication extends SpringBootServletInitializer  {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AttendanceApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AttendanceApplication.class);
    }
}


