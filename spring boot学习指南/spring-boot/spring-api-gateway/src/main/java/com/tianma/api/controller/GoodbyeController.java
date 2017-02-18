package com.tianma.api.controller;

import com.tianma.api.pojo.Greeting;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Franco on 2016/3/4.
 */

@RestController
@Api(value = "/goodbye",description = "你用了这个API你就再见了")
public class GoodbyeController {

    private static final String template = "Goodbye, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ApiOperation(value = "出现在API简介条的右边",notes = "这里会出现在实现备注里",httpMethod = "GET",response = Greeting.class,authorizations = {
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
    @RequestMapping(method = RequestMethod.GET, value="/goodbye",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "令牌", required = false, dataType = "string", paramType = "query", defaultValue="我"),
            @ApiImplicitParam(name = "name", value = "用户的名字", required = false, dataType = "string", paramType = "query", defaultValue="我")
            //应该在此处把RequestParam编进去,不然会自动生成,自动生成的看着不那么友好
            //而且我们不在这里使用@APIParam标记来弄参数
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功", response = Greeting.class),//response定义返回type,这个要写
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "未找到"),
            @ApiResponse(code = 500, message = "服务器错误")})
    public Greeting greeting(@RequestParam(value="name") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }




}
