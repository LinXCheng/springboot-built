package com.lx.attendance.controller;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.model.vo.RoleVO;
import com.lx.attendance.model.vo.UserVO;
import com.lx.attendance.service.RoleService;
import com.lx.attendance.service.UserService;

import com.lx.attendance.utils.MD5Utils;
import com.lx.attendance.utils.ResultSet;
import com.lx.attendance.utils.constants.Constants;
import com.lx.attendance.utils.logControl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;


/**
 * 用户注册
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    /**
     * 用户名是否重复
     */
    @RequestMapping(value = "/queryExist")
    @ResponseBody
    public ResultSet<?> queryExistUsername(String username, Long id) {
        // 判断用户名是否重复，重复的话前端提示用户用户名重复
        if (userService.queryExistUsername(username, id) != null) {
            return error();
        } else {
            return success();
        }
    }

    /**
     * 用户注册
     *
     * @param user
     * @return Object
     */
    @RequestMapping(value = "/registered")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<String> registered(UserDO user, String Captcha) {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            String EMAIL_CAPTCHA = (String) session.getAttribute(Constants.SESSION_EMAIL_CAPTCHA);
            // 检验邮箱验证码和使用者输入的是否相同，相同才能注册
            if(StringUtils.isNotBlank(EMAIL_CAPTCHA)){
                if(Captcha.equals(EMAIL_CAPTCHA)){
                    if (userService.queryExistUsername(user.getUsername(), null) != null) {
                        return error("用户名重复，请重新输入!");
                    } else {
                        user.setPassword(MD5Utils.generatePasswordMD5(user.getPassword()));
                        user.setDeleteStatus(1);//是否启用 0位启用  1为禁用
                        user.setType(1);//类型1是用户注册类型
                        user.setRoleId(3);//默认id=3角色是用户
                        userService.addUser(user);
                        session.removeAttribute(Constants.SESSION_EMAIL_CAPTCHA);// 注册成功清除验证码
                        return success();
                    }
                }else {
                    return error("验证码错误!");
                }
            } else {
                return error("请重新发送验证码!");
            }
        } catch (Exception e) {
            logControl.logPrint(UserController.class, null, e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("注册失败，请重试!");
        }
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "userList")
    public ModelAndView findAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "8") int pageSize,
                                String keyword) {
        ModelAndView model = new ModelAndView("user/user_list");
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO)session.getAttribute(Constants.SESSION_USER_INFO);
        PageInfo<UserVO> userList = userService.findAll(pageNum, pageSize, userInfo.getRoleId(), keyword);
        model.addObject("userList", userList);
        model.addObject("keyword", keyword);
        return model;
    }

    /**
     * 修改用户状态
     *
     * @param id
     * @return Object
     */
    @RequestMapping(value = "updateStatus")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<String> updateStatus(Long id, boolean state) {
        try {
            Integer deleteStatus = null;
            if (id != null) {
                if (state) {
                    deleteStatus = 0;// 启用
                } else {
                    deleteStatus = 1;// 禁用
                }
                userService.changeStatus(id, deleteStatus);
            }
            return success();
        } catch (Exception e) {
            logControl.logPrint(UserController.class, id, e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("修改失败，请重试!");
        }
    }

    /**
     * 根据用户id查询用户个人资料
     *
     * @param id 用户id
     * @return ModelAndView
     */
    @RequestMapping(value = "userInfo")
    public ModelAndView userInfo(Long id) {
        ModelAndView model = new ModelAndView("user/user_info");
        if (id != null) {
            UserVO userList = userService.findUserInfo(id);
            model.addObject("userList", userList);
        }
        return model;
    }

    /**
     * 进入修改页面
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "editUserView")
    public ModelAndView editUserView(Long id) {
        ModelAndView model = new ModelAndView("user/user_edit");
        if (id != null) {
            UserVO userList = userService.findUserInfo(id);
            model.addObject("userList", userList);
        }
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        List<RoleVO> rolelist = roleService.findRoleList(userInfo.getRoleId());
        model.addObject("rolelist", rolelist);
        return model;
    }

    /**
     * 修改密码页面
     */
    @RequestMapping(value = "editPassword")
    public ModelAndView editPassword() {
        ModelAndView model = new ModelAndView("user/user_password");
        return model;
    }

    /**
     * 修改密码页面
     */
    @RequestMapping(value = "savePassword")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<String> savePassword(String oldpass, String password) {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
            UserVO user = userService.findUserInfo(userInfo.getId());
            if (StringUtils.isNotBlank(oldpass)) {//判断旧密码是否为空，不为空要验证与数据库中的是否相同，两者都需要使用MD5加密
                if (MD5Utils.generatePasswordMD5(oldpass).equals(user.getPassword())) {
                    userService.updatePassword(userInfo.getId(), MD5Utils.generatePasswordMD5(password));
                    Subject currentUser = SecurityUtils.getSubject();
                    currentUser.logout();//修改完密码需要清除当前登录
                    return success();
                } else {
                    return error("原密码错误,请确认后重新提交!");
                }
            } else {
                return error("原密码不能为空,请重新输入!");
            }
        } catch (Exception e) {
            logControl.logPrint(UserController.class, null, e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("保存失败!");
        }
    }

    /**
     * 点击头像修改用户资料
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "saveUserInfo")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet saveUserInfo(UserDO user) {
        try {
            if (user.getId() != null) {
                userService.updateUserInfo(user);
            }
            return success();
        } catch (Exception e) {
            logControl.logPrint(UserController.class, user.getId(), e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error();
        }
    }

    /**
     * 修改用户信息
     *
     * @param user 用户实体对象
     * @return Object
     */
    @RequestMapping(value = "editUser")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<?> updateUser(UserDO user,String Captcha) {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            String EMAIL_CAPTCHA = (String) session.getAttribute(Constants.SESSION_EMAIL_CAPTCHA);
                if (user.getId() != null) {
                    if (user.getPassword() != null) {
                        if (!"********".equals(user.getPassword())) {
                            user.setPassword(MD5Utils.generatePasswordMD5(user.getPassword()));
                        }
                    }
                    userService.updateUser(user);
                    return success();
                } else {
                    if(StringUtils.isNotBlank(EMAIL_CAPTCHA)){
                        if(EMAIL_CAPTCHA.equals(Captcha)){
                            user.setType(0);
                            user.setPassword(MD5Utils.generatePasswordMD5(user.getPassword()));
                            user.setDeleteStatus(0);
                            userService.addUser(user);
                            return success();
                        } else {
                            return error("请输入正确的验证码!");
                        }
                    } else {
                        return error("请点击'发送验证码'!");
                    }
                }

        } catch (Exception e) {
            logControl.logPrint(UserController.class, user.getId(), e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error();
        }
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet delete(Long id) {
        try {
            userService.deleteUser(id);
            return success();
        } catch (Exception e) {
            logControl.logPrint(UserController.class, id, e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error();
        }
    }

    /**
     * 忘记密码页面
     */
    @RequestMapping(value = "forgotPass")
    public ModelAndView forgotPass() {
        ModelAndView model = new ModelAndView("user/user_forgotPass");
        return model;
    }


    /**
     * 验证用户名和邮箱验证码
     */
    @RequestMapping(value = "verifyInfo")
    @ResponseBody
    public ResultSet verifyInfo(String email,String username,String captcha) {
        if(StringUtils.isNotBlank(email) && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(captcha)){
            UserDO userDO = userService.queryExistUsername(username, null);
            Session session = SecurityUtils.getSubject().getSession();
            String EMAIL_CAPTCHA = (String) session.getAttribute(Constants.SESSION_EMAIL_CAPTCHA);
            String SESSION_USER_NAME = (String) session.getAttribute(Constants.SESSION_USER_NAME);
            if(SESSION_USER_NAME.equals(username)){
                if(null != userDO){
                    if(null != userDO.getEmail()){
                        if(userDO.getEmail().equals(email)){
                            if(StringUtils.isNotBlank(EMAIL_CAPTCHA)){
                                if(EMAIL_CAPTCHA.equals(captcha)){
                                    return success();
                                } else {
                                    return error("验证码错误！");
                                }
                            } else {
                                return error("请重新发送验证码！");
                            }
                        } else {
                            return error("该用户绑定的邮箱与您输入的邮箱不匹配，请确认后重新输入！");
                        }
                    } else {
                        return error("该用户邮箱不存在，请联系管理员！");
                    }
                } else {
                    return error("该用户不存在，请先去注册！");
                }
            } else {
                return error("该用户与先前发送验证码的用户不一致，请确认后在尝试！");
            }
        } else {
            return error("请补全资料后确认！");
        }
    }

    /**
     * 修改密码页面
     */
    @RequestMapping(value = "updatePassword")
    @ResponseBody
    @Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<String> updatePassword(String username, String password) {
        UserDO userDO = userService.queryExistUsername(username, null);
        try {
            if(null != userDO){
                if (StringUtils.isNotBlank(username)) {
                    userService.updatePassword(userDO.getId(), MD5Utils.generatePasswordMD5(password));
                    return success();
                } else {
                    return error("用户名不能为空!");
                }
            } else {
                return error("用户不存在,请先注册!");
            }
        } catch (Exception e) {
            logControl.logPrint(UserController.class, userDO.getId(), e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("保存失败!");
        }
    }
}
