package com.lx.attendance.controller;

import com.lx.attendance.model.UserDTO;
import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.service.LoginService;
import com.lx.attendance.service.RoleService;
import com.lx.attendance.service.UserService;
import com.lx.attendance.service.impl.MsgProducer;
import com.lx.attendance.utils.CaptchaUtil;
import com.lx.attendance.utils.ResultSet;

import com.lx.attendance.utils.ControllerResult;
import com.lx.attendance.utils.logControl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;

import static com.lx.attendance.utils.ControllerResult.error;

/**
 * 登录
 */
@Controller
public class LoginController{
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //private MsgProducer msgProducer = null;
    /**
     * 转入登录页面
     * @return ModelAndView
     */
    @RequestMapping(value="/login")
    public ModelAndView login(){
/*        if(msgProducer == null){
            synchronized (this){
                if(msgProducer == null) {
                    msgProducer = new MsgProducer(rabbitTemplate);
                }
            }
        }
        String context = "login";
        msgProducer.sendMsg(context);*/
        ModelAndView model = new ModelAndView("/login");
        return model;
    }

    /**
     * 验证账号与密码
     *
     * @param user 用户实体对象
     * @return Object
     */
    @RequestMapping(value = "/auth")
    @ResponseBody
    @Caching(evict = {@CacheEvict(value = "menu_List", allEntries = true, beforeInvocation = true), @CacheEvict(value = "authorization", allEntries = true, beforeInvocation = true)})
    public ResultSet<String> authLogin(UserDTO user, String code, HttpServletRequest request) {
        try {
        	if(CaptchaUtil.ver(code, request)){
				UserDO userDO = userService.queryExistUsername(user.getUsername(), null);
	            if (userDO != null) {
	                RoleDO roleDO = roleService.findRoleById(userDO.getRoleId());
	                if (userDO.getDeleteStatus() != 0) {// 判断用户是否有效
	
	                    return error("该用户已经失效!");
	
	                } else if (userDO.getRoleId() == null) {// 判断用户是否分配角色
	
	                    return error("该用户未分配用户角色!");
	
	                } else if (roleDO.getState() == 1) {// 判断角色是否禁用
	
	                    return error("该角色被禁用!");
	
	                } else {
	                	CaptchaUtil.clear(request);
						return loginService.authLogin(user, roleDO);
	                }
	            } else {
	                return error("该用户还未注册!");
	            }
        	}else{
        		CaptchaUtil.clear(request);
        		return error("验证码错误,请重新输入!");
        	}
        } catch (Exception e) {
            logControl.logPrint(LoginController.class,null,e.getMessage());
            return error(e.getMessage());
        }
    }

    /**
     * 退出登录
     * @return Object
     */
    @PostMapping("/logout")
    @ResponseBody
    public ResultSet<String> logout() {
        return loginService.logout();
    }
}
