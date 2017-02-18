package com.tianma.api.controller;

import com.tianma.api.domain.oauthclient.DN;
import com.tianma.api.service.oauthclient.UserRegisterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhengpeiwei on 16/4/22.
 */
@Api
public class UserRegisterController {

    @Autowired
    UserRegisterService userRegisterService;


    @RequestMapping(value = "user/register/getDN", method = RequestMethod.GET)
    public DN getDN(@RequestParam("tenantId") String tenantId) {

        return userRegisterService.loadDN(tenantId);
        //调用userRegisterService的方法读取数据库数据
    }

    @RequestMapping(value = "agent/register/releaseDN", method = RequestMethod.PUT)//put里面的参数也用@RequestParam即可
    public int releaseDN(@RequestParam String tenantId, @RequestParam String id, @RequestParam String password, @RequestParam String host) {

        return userRegisterService.putDN(tenantId, id, password, host);
        //调用userRegisterService的方法存放数据库数据


    }


}
