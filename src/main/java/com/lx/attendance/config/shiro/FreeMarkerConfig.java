package com.lx.attendance.config.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * FreeMarker配置类
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    /**
     * 添加共享变量
     */
    @PostConstruct
    public void setSharedVariable() {
        //setSharedVariable()方法向配置实例中添加共享变量
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
