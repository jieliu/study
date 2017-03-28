package com.egoo.sso.server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zuowenxia on 2017/3/27.
 */
@Configuration
@EnableAuthorizationServer
@RestController
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").usernameParameter("userId").permitAll()
                .and()
                .requestMatchers()
                .antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
        //.passwordEncoder(new BCryptPasswordEncoder());
    }

}