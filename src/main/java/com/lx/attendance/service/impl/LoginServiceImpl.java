package com.lx.attendance.service.impl;

import com.lx.attendance.controller.LoginController;
import com.lx.attendance.model.UserDTO;
import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.service.LoginService;
import com.lx.attendance.utils.MD5Utils;
import com.lx.attendance.utils.ResultSet;
import com.lx.attendance.utils.constants.Constants;
import com.lx.attendance.utils.logControl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Service;

import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;

/**
 * 登录
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 登录表单提交
     * @param user
     * @return Object
     */
    @Override
    public ResultSet<String> authLogin(UserDTO user, RoleDO roleDo) {
        String username = user.getUsername();
        String password = MD5Utils.generatePasswordMD5( user.getPassword());
        try {
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            currentUser.login(token);
            Session session = SecurityUtils.getSubject().getSession();
            UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
            return success(roleDo.getRoleCode());
        } catch (AuthenticationException e) {
            logControl.logPrint(LoginServiceImpl.class,null,e.getMessage());
            return error("用户名或者密码错误!");
        }
    }
    /**
     * 退出登录
     * @return Object
     */
    @Override
    public ResultSet<String> logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            return success();
        } catch (Exception e) {
            return error("退出登录失败,请重试!");
        }
    }
}
