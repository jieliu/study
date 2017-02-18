package com.tianma.api.controller;

import com.tianma.api.domain.oauthclient.Client;
import com.tianma.api.service.oauthclient.ClientService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengpeiwei on 16/4/19.
 */
@RestController
@Api
public class TokenController {
    @Autowired
    ClientService clientService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);


    @RequestMapping("/admin/confirmAuth")
    public HttpStatus confirmAuth(@RequestParam(value="cname") String cname,@RequestParam(value = "code")String code) {

        String authcode=clientService.findcode(code);
        if(authcode==null){
            int result = clientService.saveCodeforClient(cname, code);
            if (result == 1) return HttpStatus.OK;
            else return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        else if(authcode.equals(code))return HttpStatus.INTERNAL_SERVER_ERROR;
        else return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @RequestMapping(value = "/clienttoken",method = RequestMethod.POST)
    public List takeToken(@RequestParam String client_id,@RequestParam String client_secret){
        LOGGER.info("id is:"+client_id+"and sec is:"+client_secret);
        Client cli=clientService.findByIdAndSecret(client_id, client_secret);
        if (cli == null) {
            ArrayList<String> list=new ArrayList<String>();
            list.add("您的Client凭据与系统数据不符");
            return list;
        }
        else{

                return clientService.findCodeByClient(cli.getClient_id());
        }
    }
}
