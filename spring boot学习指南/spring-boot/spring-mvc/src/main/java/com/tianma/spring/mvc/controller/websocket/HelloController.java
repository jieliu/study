package com.tianma.spring.mvc.controller.websocket;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fiboliu on 16/8/12.
 */
@Controller
public class HelloController {

    /**
     * 通过RequestMapping,浏览器可以向服务端发送信息,服务端信息可以通过订阅了SendTo中的路径的浏览器发送信息
     * @param greeting
     * @return
     * @throws Exception
     */
    @RequestMapping("/welcome")
    @SendTo("/topic/getResponse")
    public String say(String greeting) throws Exception {
        System.out.println(greeting);
        return "hello" + greeting;
    }
}
