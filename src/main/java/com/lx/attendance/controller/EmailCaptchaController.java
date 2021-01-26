package com.lx.attendance.controller;

import com.lx.attendance.utils.ResultSet;
import com.lx.attendance.utils.constants.Constants;
import com.lx.attendance.utils.EmailTool;
import com.lx.attendance.utils.logControl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;

@Controller
public class EmailCaptchaController {
    @RequestMapping("/email/captcha")
    @ResponseBody
    public ResultSet sendCaptcha(String email, HttpServletRequest request) {
        if (email != null) {
            Session session = SecurityUtils.getSubject().getSession();
            String captcha = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            session.setAttribute(Constants.SESSION_EMAIL_CAPTCHA, captcha);
            String Text = "验证码为:" + captcha + ",5分钟内有效。感谢您使用LX服务，请填写验证码完成验证!";
            boolean flag = false;
            try {
                flag = EmailTool.sendSimpleMail(email, "LX注册验证码", Text);
                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(300000);
                            session.removeAttribute(Constants.SESSION_EMAIL_CAPTCHA);
                        } catch (InterruptedException e) {
                            session.removeAttribute(Constants.SESSION_EMAIL_CAPTCHA);
                            logControl.logPrint(EmailCaptchaController.class, null, e.getMessage());
                        }
                    }
                }.start();
            } catch (Exception e) {
                session.removeAttribute(Constants.SESSION_EMAIL_CAPTCHA);
                logControl.logPrint(EmailCaptchaController.class, null, e.getMessage());
                return error();
            }
            if (flag) {
                return success();
            } else {
                return error();
            }
        } else {
            return error();
        }
    }
    @RequestMapping("/email/captcha2")
    @ResponseBody
    public ResultSet sendCaptcha(String email, HttpServletRequest request,String username) {
        if (StringUtils.isNotBlank(email)){
            if(StringUtils.isNotBlank(username)){
                Session session = SecurityUtils.getSubject().getSession();
                String captcha = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
                session.setAttribute(Constants.SESSION_EMAIL_CAPTCHA, captcha);
                session.setAttribute(Constants.SESSION_USER_NAME, username);
                String Text = "验证码为:" + captcha + ",5分钟内有效。感谢您使用LX服务，请填写验证码完成验证!";
                boolean flag = false;
                try {
                    flag = EmailTool.sendSimpleMail(email, "LX注册验证码", Text);
                    new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(300000);
                                session.removeAttribute(Constants.SESSION_EMAIL_CAPTCHA);
                            } catch (InterruptedException e) {
                                session.removeAttribute(Constants.SESSION_EMAIL_CAPTCHA);
                                logControl.logPrint(EmailCaptchaController.class, null, e.getMessage());
                            }
                        }
                    }.start();
                } catch (Exception e) {
                    session.removeAttribute(Constants.SESSION_EMAIL_CAPTCHA);
                    logControl.logPrint(EmailCaptchaController.class, null, e.getMessage());
                    return error();
                }
                if (flag) {
                    return success();
                } else {
                    return error();
                }
            } else {
                return error();
            }
        } else {
            return error();
        }
    }
}
