package com.egoo.sso.server.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zuowenxia on 2017/3/28.
 */
@Configuration
public class LoginConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("login").setViewName("login");
        registry.addViewController("/").setViewName("index");
    }
}