package com.egoo.sso.rest.controller;

import com.egoo.sso.rest.model.Message;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zuowenxia on 2017/3/28.
 */
@RestController
public class MessageController {

    final List<Message> messages = Collections.synchronizedList(new LinkedList<Message>());

    @RequestMapping(path = "api/messages", method = RequestMethod.GET)
    List<Message> getMessages(Principal principal) {

        return messages;
    }

    @RequestMapping(path = "api/messages", method = RequestMethod.POST)
    Message postMessage(Principal principal, @RequestBody Message message) {

        message.setUsername(principal.getName());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        message.setCreatedAt(sf.format(new Date()));

        messages.add(0, message);
        return message;
    }
}