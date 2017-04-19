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
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @GetMapping("/common/tables.html")
    public String getCommonTablesIndex(ModelAndView modelAndView) {
        return "screen/demo/tables";
    }

    @GetMapping("/common/forms.html")
    public String getCommonForms(ModelAndView modelAndView) {
        return "screen/demo/forms";
    }

    @GetMapping("/common/blank.html")
    public String getBlankHtml(ModelAndView modelAndView) {
        return "screen/demo/blank";
    }
}
