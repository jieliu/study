package com.egoo.sso.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by zuowenxia on 2017/3/27.
 */
@SpringBootApplication
@EnableResourceServer
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
