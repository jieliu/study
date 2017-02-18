package com.tianma.spring.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fiboliu on 16/8/2.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.tianma.spring.rest")
public class Application {

    public static void main(String args[]) {

        SpringApplication application = new SpringApplication(
                Application.class);
        application.run(args);
    }
}
