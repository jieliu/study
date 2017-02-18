package com.tianma.api.service;

import org.springframework.stereotype.Service;

/**
 * Created by fiboliu on 16/8/4.
 */

@Service
public class DemoImpl implements Demo {

    public String sayHello(String name) {
        return "hello, " + name + " !";
    }
}
