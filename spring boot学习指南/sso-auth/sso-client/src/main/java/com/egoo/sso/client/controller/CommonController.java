package com.egoo.sso.client.controller;

import com.egoo.sso.client.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zuowenxia on 2017/3/28.
 */
@Controller
public class CommonController {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Value("${messages.url:http://localhost:7777}/api")
    String messagesUrl;

    @RequestMapping("/")
    String home(Model model) {
        List<Message> messages = Arrays.asList(restTemplate.getForObject(messagesUrl + "/messages", Message[].class));
        model.addAttribute("messages", messages);
        return "index";
    }

    @RequestMapping(path = "messages", method = RequestMethod.POST)
    String postMessages(@RequestParam String text) {
        Message message = new Message();
        message.setText(text);
        restTemplate.exchange(RequestEntity
                .post(UriComponentsBuilder.fromHttpUrl(messagesUrl).pathSegment("messages").build().toUri())
                .body(message), Message.class);
        return "redirect:/";
    }
}


