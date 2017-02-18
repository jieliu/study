package com.tianma.api.controller;

import com.tianma.api.domain.oauthclient.ServiceInfo;
import com.tianma.api.pojo.Greeting;
import com.tianma.api.service.oauthclient.ServiceInfoService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by zhengpeiwei on 16/4/18.
 */
@RestController
@Api(value = "/serviceinfo",description = "来获取服务历史信息")
public class ServiceInfoController {

    @Autowired
    private ServiceInfoService serviceInfoService;

    private static final Logger log = LoggerFactory.getLogger(ServiceInfoController.class);

    @ApiOperation(value = "getServiceinfo", nickname = "getServiceinfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantid", value = "tenants of service info", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "agentid", value = "agent of service info", required = true, dataType = "string", paramType = "query", defaultValue="")
})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.GET, value="/serviceinfo", produces = "application/json")
    public ServiceInfo getInfo(@RequestParam(value="tennatid") String tennatid,@RequestParam(value="agentid") String agentid)
    {

        log.info("tenant is:" + tennatid + "and agent is:" + agentid);
        ServiceInfo info=serviceInfoService.loadByIds(tennatid,agentid);
        try {
            String label = URLDecoder.decode(info.getOpinion_lable(), "utf-8");
            info.setOpinion_lable(label);
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return info;
    }



    @ApiOperation(value = "putServiceinfo", nickname = "putServiceinfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantid", value = "tenants of service info", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "agentid", value = "agent of service info", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "good", value = "whether this service is good", required = true, dataType = "boolean", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "opinion_label", value = "opinion of client", required = true, dataType = "string", paramType = "query", defaultValue="")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="/putserviceinfo")
    public int putInfo(@RequestParam(value="tennatid") String tennatid,@RequestParam(value="agentid") String agentid,@RequestParam(value="good") Boolean good,@RequestParam(value="opinion_label") String opinion_label) throws Exception
    {
        log.info("opinion label is:"+opinion_label);
        //String label=new String(opinion_label.getBytes("ISO-8859-1"),"UTF-8");
        //log.info("label is:"+label);
        ServiceInfo info=new ServiceInfo();
        info.setAgentid(agentid);
        info.setOpinion_lable(URLEncoder.encode(opinion_label, "UTF-8"));
        info.setTenantid(tennatid);
        log.info("good is:"+good);
        return serviceInfoService.PutInfo(info,good);
    }


}


