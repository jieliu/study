package com.tianma.client.common.network.retry;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by fiboliu on 16-8-26.
 */
public class RestfulGetTest {

    @org.junit.Test
    public void call() throws Exception {

        RestfulGet restfulGet = new RestfulGet(Response.class, new URL("http://www.baidu.com"));
    }

}

class Response {

}