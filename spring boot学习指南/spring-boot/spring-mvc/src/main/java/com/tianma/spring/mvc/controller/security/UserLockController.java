package com.tianma.spring.mvc.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fiboliu on 16-4-14.
 */
@Controller
public class UserLockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLockController.class);

    @RequestMapping(value = "/user/lock", method = RequestMethod.GET)
    public ModelAndView getUserLockPage(String error) {
        LOGGER.debug("Getting login page, error={}", error);
        return new ModelAndView("screen/user/lock_screen", "error", error);
    }
}
