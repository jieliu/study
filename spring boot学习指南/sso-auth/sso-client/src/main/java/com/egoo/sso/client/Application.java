package com.egoo.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * Created by zuowenxia on 2017/3/28.
 */
@SpringBootApplication
@EnableOAuth2Sso
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}