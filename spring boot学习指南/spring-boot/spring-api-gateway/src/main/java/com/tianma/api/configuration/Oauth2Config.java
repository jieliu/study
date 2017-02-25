package com.tianma.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.*;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;



//不同的scope定义同类资源的不同划分
//多资源服务器定义多类资源



@Configuration
public class Oauth2Config {


    private static final String FREELINK_API = "greetingandgoodbye";

    private static final String EGOO_API = "other";

    private static final String FREESWITCH_API = "morning";


    @Autowired
    @Qualifier("secondaryDS")
    private DataSource oauthDataSource;//dayasource



    //资源服务器1
    @Bean
    @Order(2)
    protected ResourceServerConfiguration MainResources() {

        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            // Switch off the Spring Boot @Autowired configurers
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };

        resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerConfigurerAdapter() {

            @Override
            public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                TokenStore tokenStore = new JdbcTokenStore(oauthDataSource);
                resources.resourceId(FREELINK_API).tokenStore(tokenStore);
            }

            @Override
            public void configure(HttpSecurity http) throws Exception {

                http
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .and()
                        .requestMatchers().antMatchers("/morning","/fs**")
                        .and()
                        .authorizeRequests()
                        .antMatchers("/fs**").access("#oauth2.hasScope('normal')")
                        //.antMatchers("/greeting**").access("#oauth2.hasScope('normal')")
                        .antMatchers("/morning**").access("#oauth2.hasScope('morning')");
            }
        }));
        resource.setOrder(3);


        return resource;

    }


    //资源服务器1
    @Bean
    @Order(2)
    protected ResourceServerConfiguration OrderResources() {

        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            // Switch off the Spring Boot @Autowired configurers
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };

        resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerConfigurerAdapter() {

            @Override
            public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                TokenStore tokenStore = new JdbcTokenStore(oauthDataSource);
                resources.resourceId(FREESWITCH_API).tokenStore(tokenStore);
            }

            @Override
            public void configure(HttpSecurity http) throws Exception {

                http
                        .requestMatchers().antMatchers("/goodbye")
                        .and()
                        .authorizeRequests()
                        .antMatchers("/goodbye**").access("#oauth2.hasScope('greeting')");

            }
        }));
        resource.setOrder(4);


        return resource;

    }




    //资源服务器2
    @Bean
    @Order(2)
    protected ResourceServerConfiguration FreelinkResource() {

        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            // Switch off the Spring Boot @Autowired configurers
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };

        resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerConfigurerAdapter() {

            @Override
            public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                TokenStore tokenStore = new JdbcTokenStore(oauthDataSource);
                resources.resourceId(EGOO_API).tokenStore(tokenStore);
            }


            @Override
            public void configure(HttpSecurity http) throws Exception {
                http
                        .requestMatchers().antMatchers("/egoo")
                        .and()
                        .authorizeRequests()
                        .antMatchers("/egoo").access("#oauth2.hasScope('hello')");
            }

        }));
        resource.setOrder(5);

        return resource;

    }


    /*
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        @Qualifier("secondaryDS")
        private DataSource oauthDataSource;//dayasource


        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            TokenStore tokenStore = new JdbcTokenStore(oauthDataSource);
            resources.resourceId(API_RESOURCE_ID).tokenStore(tokenStore);
        }//设置数据库tokenStore

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http

                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().antMatchers("/greeting**", "/goodbye**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/greeting**").authenticated()
                    .antMatchers("/goodbye**").authenticated()
                    .and()
                    .csrf()
                    .disable()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login.jsp");
                   // .and()
                    //.exceptionHandling()
                    //.accessDeniedPage("/login.jsp?authorization_error=true");
                    //.and()
                    //.formLogin()
                    //.loginProcessingUrl("/login")
                    //.failureUrl("/login.jsp?authentication_error=true")
                    //.loginPage("/login.jsp");

            // @formatter:on
        }

    }
    */




    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        @Qualifier("secondaryDS")
        private DataSource oauthDataSource;//dayasource


        @Autowired
        private TokenStore tokenStore;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(oauthDataSource);
        }//tokenstore



        @Bean(name = "jdbcclientdetail")
        public JdbcClientDetailsService clientDetailsService() {
            return new JdbcClientDetailsService(oauthDataSource);
        }//clientdetailService

        @Bean
        public ApprovalStore approvalStore() {
            return new JdbcApprovalStore(oauthDataSource);
        }

        @Bean
        public AuthorizationCodeServices authorizationCodeServices() {
            return new JdbcAuthorizationCodeServices(oauthDataSource);
        }

        @Autowired
        @Qualifier("authenticationManagerBean")//使用和security一样的认真服务器
        private AuthenticationManager authenticationManager;





        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetailsService());

        }


        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore)
                    .authorizationCodeServices(authorizationCodeServices())
                    .authenticationManager(authenticationManager).approvalStore(approvalStore());
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        }



    }









}
