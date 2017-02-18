package com.tianma.client.common.network.retry;

import com.tianma.client.common.network.http.HttpClientUtil;
import com.tianma.client.common.network.http.HttpResponseCallbackHandler;
import com.tianma.client.common.network.http.impl.HttpResponseCallbackHandlerJsonHandler;
import com.tianma.client.common.retry.UnreliableInterface;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by fiboliu on 16-8-26.
 */
public class RestfulGet<T> implements UnreliableInterface {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RestfulGet.class);

    private HttpRequestBase request = null;
    private HttpResponseCallbackHandler<T> httpResponseCallbackHandler = null;

    public RestfulGet(Class<T> clazz, URL url) {

        HttpGet request = new HttpGet(url.toString());
        request.addHeader("content-type", "application/json");
        this.request = request;
        this.httpResponseCallbackHandler = new
                HttpResponseCallbackHandlerJsonHandler<T>(clazz);
    }

    /**
     * Get数据
     */
    public T call() throws Exception {

        T value = HttpClientUtil.execute(request, httpResponseCallbackHandler);

        return value;
    }
}