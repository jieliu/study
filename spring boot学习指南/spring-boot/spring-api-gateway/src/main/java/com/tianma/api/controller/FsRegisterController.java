package com.tianma.api.controller;

import com.tianma.api.domain.oauthclient.DN;
import com.tianma.api.util.DNManager;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by zhengpeiwei on 16/4/20.
 */
@Api
@RestController
public class FsRegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FsRegisterController.class);



    @RequestMapping(value = "fs/register/getDN",method = RequestMethod.GET)
    public DN getDN(@RequestParam("tennatId")String tennatId){
        //find a dn for tennat and get it out and return DN
        Map map1=DNManager.map.get(tennatId);
        Integer i=new Integer(3);
        if(map1.containsKey(i)){
            DN dn=(DN)map1.get(i);
            DNManager.map.get(tennatId).remove(new Integer(3));
            System.out.print(DNManager.map.toString());
            return dn;
        }
        else{return null;}
    }

    @RequestMapping(value = "fs/register/releaseDN",method = RequestMethod.PUT)//put里面的参数也用@RequestParam即可
    public String releaseDN(@RequestParam String tennatId,@RequestParam String id,@RequestParam String password){
        //要做的是根据id,找到那个map,然后构造dn,往map里面放
        LOGGER.info(id);
        DN dn=new DN(id,password);
        Map map=DNManager.map.get(tennatId);
        System.out.print(DNManager.map.toString());
        //如果你要放的DN已经存在,那更加ok了..
        return "OK";
    }


}
