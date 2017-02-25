package com.tianma.api.controller;

import com.tianma.api.domain.oauthclient.Message;
import com.tianma.api.pojo.Greeting;
import com.tianma.api.service.oauthclient.MessageService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengpeiwei on 16/4/11.
 */
@RestController
@Api
public class MessageController {

    @Autowired
    private MessageService messageService;

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @ApiOperation(value = "getMessage", nickname = "getMessage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "sender or receiver's name", required = true, dataType = "string", paramType = "query", defaultValue="")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.GET, value="/messages", produces = "application/json")
    public List<Message> getMessages(@RequestParam(value="name", required=false) String name){

        log.info("name is:"+name+"and type is:" + name.getClass().toString());

        ArrayList<Message> HistoryMessageList=(ArrayList<Message>)messageService.findMessages(name);
        //ArrayList<Message> HistoryMessageList=new ArrayList<Message>();

        return HistoryMessageList;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/messages")
    public int putMessages(@RequestParam(value = "message")Message message){
            return messageService.insertMessages(message);
    }
}
