package com.lx.attendance.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lx.attendance.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CaptchaController {
	/**
	 * 图形验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
    @RequestMapping("/images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	CaptchaUtil.outPng(request, response);
    }
}
