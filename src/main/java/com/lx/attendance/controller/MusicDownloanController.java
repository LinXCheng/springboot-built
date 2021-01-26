package com.lx.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/music")
@Controller
public class MusicDownloanController {
    @RequestMapping("/musicDownloan")
    public ModelAndView music(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicDownloan");
        return modelAndView;
    }
}
