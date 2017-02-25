package com.tianma.spring.mvc.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by jie on 17-2-19.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebScoketNotifyConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/subscribe")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/notify");
        //全局使用的订阅前缀（客户端订阅路径上会体现出来）
        registry.setApplicationDestinationPrefixes("/app/");
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来）,不设置的话，默认也是/user/
        //registry.setUserDestinationPrefix("/user/");
    }

}
