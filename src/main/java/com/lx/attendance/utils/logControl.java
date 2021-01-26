package com.lx.attendance.utils;

import com.lx.attendance.utils.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class logControl {
    public static <T> void logPrint(Class<T> className, Long id, String msg) {
        Logger logger = LoggerFactory.getLogger(className);
        Session session = SecurityUtils.getSubject().getSession();
        String userName = (String) session.getAttribute(Constants.SESSION_USER_NAME);
        logger.info("用户名:"+userName+":异常原因："+msg+" 错误id："+id);
    }
}
