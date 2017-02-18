package com.tianma.api.controller;

import com.tianma.api.domain.oauthclient.WorkOrder;
import com.tianma.api.pojo.Greeting;
import com.tianma.api.service.oauthclient.WorkOrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * Created by zhengpeiwei on 16/4/21.
 */
@Api(value = "/workorder",description = "操作工单信息的API")
@RestController
public class FsWorkOrderController {

    @Autowired
    WorkOrderService workOrderService;

    @ApiOperation(value = "出现在API简介条的右边 这个API参数应该三选二",notes = "这里会出现在实现备注里",httpMethod = "GET",response = WorkOrder.class,authorizations = {
            @Authorization(
                    value="myoauth",//OAuth2 authorization scheme,也不知道是个什么语义
                    scopes = {
                            @AuthorizationScope(
                                    scope = "greetingandgoodbye",
                                    description = "allows adding of pets")
                    }
            )
    }
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantid", value = "Tenant's name", required = false, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "agentid", value = "Agent's name", required = false, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "userid", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="")

            })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="fs/workorder",method = RequestMethod.GET)
    public WorkOrder getWorkOrder(@RequestParam(value="tenantid" ,required = false) String tenantid,@RequestParam(value="agentid",required = false) String agentid,@RequestParam(value="userid",required = false) String userid){
        if(tenantid!=null&&agentid!=null){
            return workOrderService.getWorkOrderAT(agentid,tenantid);

        }
        else if(userid!=null&&tenantid!=null){
            return workOrderService.getWorkOrderUT(userid,tenantid);

        }
        else return null;

    }


    @ApiOperation(value = "插入工单信息",notes = "给出工单的六个方面",httpMethod = "POST",authorizations = {
            @Authorization(
                    value="myoauth",//OAuth2 authorization scheme,也不知道是个什么语义
                    scopes = {
                            @AuthorizationScope(
                                    scope = "greetingandgoodbye",
                                    description = "allows adding of pets")
                    }
            )
    }
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantid", value = "Tenant's name", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "agentid", value = "Agent's name", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "userid", value = "User's name", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "remark", value = "opinion of workorder", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "label", value = "this is the label", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "time", value = "time that workorder created", required = true, dataType = "time", paramType = "query", defaultValue="")

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="fs/workorder",method = RequestMethod.POST)
    public int postWorkOrder(@RequestParam(value="tenantid") String tenantid,@RequestParam(value="agentid") String agentid,@RequestParam(value="userid") String userid,@RequestParam(value="remark") String remark,@RequestParam(value="label") String label,@RequestParam(value="time")
    String time){

        try {
            Timestamp ts=new Timestamp(System.currentTimeMillis());
            System.out.println(ts);
            ts = Timestamp.valueOf(time);
            System.out.println(ts);
            return workOrderService.postWorkOrder(agentid,userid,tenantid,ts,remark,label);
        } catch (Exception e) {
           e.printStackTrace();
            return -1;
        }

    }
}
