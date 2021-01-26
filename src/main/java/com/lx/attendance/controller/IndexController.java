package com.lx.attendance.controller;

import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.model.vo.MenuVO;
import com.lx.attendance.model.vo.UserVO;
import com.lx.attendance.service.MenuService;
import com.lx.attendance.service.UserService;
import com.lx.attendance.utils.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * 首页
 */
@Controller
public class IndexController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    /**
     * 首页
     * @return ModelAndView
     */
    @RequestMapping(value = "index")
    public ModelAndView index() {
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        if (userInfo != null) {
        	ModelAndView model = new ModelAndView("/index");
        	UserVO user = userService.findUserInfo(userInfo.getId());
        	if(user != null){
                model.addObject("user", user);
                List<MenuVO> menuList = menuService.findMenuListByUserRole(userInfo.getId());
                if (menuList.size() != 0) {
                    model.addObject("menuList", menuList);
                }
                return model;
            } else {
                Subject currentUser = SecurityUtils.getSubject();
                currentUser.logout();
                return new ModelAndView("/login");
            }
        }
        else{
        	 Subject currentUser = SecurityUtils.getSubject();
             currentUser.logout();
        	 return new ModelAndView("/login");
        }
    }
}