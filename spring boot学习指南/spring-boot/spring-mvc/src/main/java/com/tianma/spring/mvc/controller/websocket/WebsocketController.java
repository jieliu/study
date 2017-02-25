package com.tianma.spring.mvc.controller.websocket;

import com.tianma.spring.mvc.domain.websocket.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 17-2-19.
 */
@Controller
public class WebsocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 前端js交互, post请求地址, 这里用于demo中的操作
     */
    @ResponseBody
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public void sendMessage() {
        List<MessageInfo> messages = getMessage();
        messagingTemplate.convertAndSend("/notify/message", messages);
    }

    private List<MessageInfo> getMessage() {
        List<MessageInfo> list = new ArrayList<>();
        list.add(new MessageInfo("Message-Text1"));
        list.add(new MessageInfo("Message-Text2"));
        list.add(new MessageInfo("Message-Text3"));
        list.add(new MessageInfo("Message-Text4"));
        return list;
    }
}