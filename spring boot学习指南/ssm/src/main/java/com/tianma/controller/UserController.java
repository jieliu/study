package com.tianma.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zuowenxia on 2017/4/18.
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @GetMapping("/user/login.html")
    public String getIndex(ModelAndView modelAndView) {
        return "screen/user/login";
    }

}
