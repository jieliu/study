package com.egoo.sso.client.controller;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Created by zuowenxia on 2017/3/28.
 */
@Configuration
public class OAuthConfigure {

    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

    @Bean
    OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }
}