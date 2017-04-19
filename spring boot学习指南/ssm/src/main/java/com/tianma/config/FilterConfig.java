package com.egoo.sso.saml.idp.config;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhengrui on 2016/9/26.
 */
@Configuration
public class FilterConfig {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    public CORSFilter corsFilter(){
        return new CORSFilter();
    }
}


/**
 * Spring Cors
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cors.html
 */

class CORSFilter extends CorsFilter {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(CORSFilter.class);

    public CORSFilter(){
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("POST");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        filterChain.doFilter(request, response);
    }
}