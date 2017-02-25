package com.tianma.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by fiboliu on 16/8/4.
 */

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println("recive msg : " + demoEvent.getMsg());
    }
}
