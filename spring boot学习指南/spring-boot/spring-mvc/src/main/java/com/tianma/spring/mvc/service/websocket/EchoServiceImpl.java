package com.tianma.spring.mvc.service.websocket;

/**
 * Created by jie on 17-2-19.
 */
public class EchoServiceImpl implements EchoService {
    private final String echoFormat;

    public EchoServiceImpl(String echoFormat) {
        this.echoFormat = (echoFormat != null) ? echoFormat : "%s";
    }

    @Override
    public String getMessage(String message) {
        return String.format(this.echoFormat, message);
    }
}