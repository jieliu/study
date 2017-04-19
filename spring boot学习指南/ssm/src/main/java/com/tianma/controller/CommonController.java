package com.tianma.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zuowenxia on 2017/4/18.
 */
@Controller
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @GetMapping("/")
    public String getIndex(ModelAndView modelAndView) {
        return "home";
    }

    @GetMapping("/index.html")
    public String getHome(ModelAndView modelAndView) {
        return "screen/index";
    }

}
